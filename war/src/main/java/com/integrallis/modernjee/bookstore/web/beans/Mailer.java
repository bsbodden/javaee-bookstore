package com.integrallis.modernjee.bookstore.web.beans;

import javax.ejb.EJB;

import com.integrallis.modernjee.bookstore.service.MailService;

public class Mailer {
	@EJB
	private MailService mailService;

	private String to;
	private String subject;
	private String body;

	public String deliver() {
		mailService.sendMail(to, subject, body);
		return "success";
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
