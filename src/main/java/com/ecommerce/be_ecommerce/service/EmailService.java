package com.ecommerce.be_ecommerce.service;

import com.ecommerce.be_ecommerce.model.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderEmail(EmailRequest req) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acnra12408@gmail.com");
        message.setTo(req.getReceiver());
        message.setSubject("Order Confirmation - " + req.getOrderId());
        message.setText("Hi,\n\nThank you for your order of " + req.getProduct() + ".\nTotal: $" + req.getPrice());

        mailSender.send(message);
    }
}

