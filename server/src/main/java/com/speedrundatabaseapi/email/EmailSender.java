package com.speedrundatabaseapi.email;

public interface EmailSender {
    void send(String to, String subject, String text);
}
