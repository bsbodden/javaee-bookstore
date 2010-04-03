package com.integrallis.modernjee.bookstore.web.beans;

import javax.ejb.EJB;
import javax.jms.JMSException;

import com.integrallis.modernjee.bookstore.messaging.MessagingClientLocal;

public class Chat {

	@EJB
	private MessagingClientLocal chatClient;
	
	private String message;
	private String response;
	
    public String sendMessage() throws JMSException {
        chatClient.sendMessage(message);
        response = chatClient.receiveMessage();
        return "success";
    }

	public String getMessage() {
		return message;
	}
	

	public String getResponse() {
		return response;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
