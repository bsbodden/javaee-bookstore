package com.integrallis.modernjee.bookstore.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

@Stateless
public class MailServiceBean implements MailService {
	@Resource(mappedName = "java:/Mail")
	private Session session;
	
	private static Logger logger = Logger.getLogger(MailServiceBean.class);

	public void sendMail(String email, String subject, String body) {
		try {
			Address[] to = InternetAddress.parse(email, false);

			// create message
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom();
			message.setRecipients(javax.mail.Message.RecipientType.TO, to);
			message.setSubject(MailServiceBean.class.getSimpleName());
			message.setSentDate(new Date());
			message.setText(body);

			// Send message
			Transport.send(message);
		} catch (AddressException ae) {
			logger.error("There is something wrong with the email " + email, ae);
		} catch (MessagingException me) {
			logger.error("There is something wrong with the server's email configuration", me);
		}
	}

}
