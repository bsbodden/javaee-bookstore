package com.integrallis.modernjee.bookstore.messaging;

import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.DeliveryMode;
import javax.jms.TextMessage;
import javax.jms.Queue;
import javax.jms.JMSException;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;

import org.apache.log4j.Logger;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName="destination", propertyValue="QuestionQueue")
    }
)
public class ChatBean implements MessageListener {

    @Resource(mappedName = "ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name="AnswerQueue", mappedName="AnswerQueue")
    private Queue answerQueue;
    
    private static Logger logger = Logger.getLogger(ChatBean.class);

    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String question = textMessage.getText();
            logger.info("Received message =========> " + question);

            if (question.equals("Shall we play a game?")){
                respond("Love to. How about Global Thermonuclear War?");
            } else if (question.equals("Do you read me, HAL?")){
                respond("Affirmative, Dave. I read you");
            } else if (question.equals("Open the pod bay doors, HAL.")){
                respond("I'm sorry, Dave. I'm afraid I can't do that.");
            } else {
            	respond("Why don't you ask me that later!");
            }
        } catch (JMSException e) {
            throw new IllegalStateException(e);
        }
    }

    private void respond(String text) throws JMSException {
    	logger.info("Placing response in the answer queue =========> " + text);
        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(answerQueue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a message
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            producer.send(message);
        } finally {
            // Clean up
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }
}
