package ufcg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void registrationMail(String firstName, String lastName, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Welcome to AJuDe!");
        message.setTo(email);

        mailSender.send(message);

    }
}
