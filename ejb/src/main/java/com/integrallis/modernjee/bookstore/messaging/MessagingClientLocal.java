package com.integrallis.modernjee.bookstore.messaging;

import javax.ejb.Local;
import javax.jms.JMSException;

@Local
public interface MessagingClientLocal {

    public void sendMessage(String text) throws JMSException;

    public String receiveMessage() throws JMSException;
}
