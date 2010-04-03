package com.integrallis.modernjee.bookstore.messaging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;


@Stateless
public class MessagingClientBean implements MessagingClientLocal {

    @Resource(mappedName = "ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "QuestionQueue", mappedName = "QuestionQueue")
    private Queue questionQueue;

    @Resource(name="AnswerQueue", mappedName = "AnswerQueue")
    private Queue answerQueue;


    public void sendMessage(String text) throws JMSException {
        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(questionQueue);
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

    public String receiveMessage() throws JMSException {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a MessageConsumer from the Session to the Topic or Queue
            consumer = session.createConsumer(answerQueue);
            
            // Wait for a message
            TextMessage message = (TextMessage) consumer.receive(1000);

            return message.getText();
        } finally {
            if (consumer != null) consumer.close();
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }
}