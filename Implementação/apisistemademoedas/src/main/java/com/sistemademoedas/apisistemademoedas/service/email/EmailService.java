package com.sistemademoedas.apisistemademoedas.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String mailSenderAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailSenderAddress);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {
            log.info("[SENDING EMAIl - start] - \nMessage: " + String.valueOf(message));
            mailSender.send(message);
            log.info("[SENDING EMAIl - end]");
        } catch (MailException e) {
            log.error("[SENDING EMAIl - failed]");
            throw new MailSendException("Failed to send email. " + e.getMessage());
        }
    }
}
