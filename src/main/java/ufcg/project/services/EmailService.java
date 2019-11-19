package ufcg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void registrationMail(String firstName, String recoverID, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Welcome to AJuDe!");
        message.setTo(email);

        mailSender.send(message);
    }

    public void recoverMail(String firstName, String recoverURL, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Password Reset Request");
        message.setText("Here is you recover password link " + firstName + ", if you haven't request this please just ignore this email.\nTo reset your password, click in the link bellow:\n" + recoverURL);
        message.setTo(email);
        mailSender.send(message);
    }
}
