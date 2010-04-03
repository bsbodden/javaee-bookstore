package com.integrallis.modernjee.bookstore.service;

import javax.ejb.Local;

@Local
public interface MailService {
    void sendMail(String email, String subject, String body);
}
