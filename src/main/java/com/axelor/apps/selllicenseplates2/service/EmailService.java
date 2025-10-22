package com.axelor.apps.selllicenseplates2.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendFeedbackEmail(String to, String subject, String body);
}
