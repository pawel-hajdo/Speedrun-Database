package com.speedrundatabaseapi.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class for sending emails.
 *
 * <p>This class provides a method to send emails using the configured JavaMailSender.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private static String fromEmail = System.getenv("EMAIL");

    /**
     * Constructs an EmailService with the specified JavaMailSender.
     *
     * @param mailSender The JavaMailSender used for sending emails.
     */
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends an email.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param text    The content of the email.
     */
    public void send(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
