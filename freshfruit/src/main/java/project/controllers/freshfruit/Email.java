package project.controllers.freshfruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class Email {

    @Autowired
    public JavaMailSender emailSender;

    public String sendSimpleEmail(String email, double price) {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(email);
        message.setSubject("Cam on ban da mua hang tai website cua chung toi !");
        message.setText("Tri gia don hang la : "+ price +". Xin chan thanh cam on quy khach !");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}