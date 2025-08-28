package com.ecommerce.be_ecommerce.service;

import com.ecommerce.be_ecommerce.model.request.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

//    public void sendOrderEmail(EmailRequest req) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("acnra12408@gmail.com");
//        message.setTo(req.getReceiver());
//        message.setSubject("Order Confirmation - " + req.getOrderId());
//        message.setText("Hi,\n\nThank you for your order of " + req.getProduct() + ".\nTotal: $" + req.getPrice());
//
//        mailSender.send(message);
//    }

    public void sendOrderEmail(EmailRequest req) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("acnra12408@gmail.com");
        helper.setTo(req.getReceiver());
        helper.setSubject("Confirmare Comanda - " + req.getOrderId());

        String htmlMsg = "<html><body style='font-family:Arial,sans-serif'>" +
                "<h2 style='color:#2c3e50'>Multumim pentru comanda!</h2>" +
                "<img src='cid:logoImage' style='width:150px;height:auto;margin:10px 0;'/>" +
                "<p><b>Comanda ID:</b> " + req.getOrderId() + "</p>" +
                "<p><b>Produs:</b> " + req.getProduct() + "</p>" +
                "<p><b>Total:</b> $" + req.getPrice() + "</p>" +
                "<p>Va anuntam odata ce comanda este gata.</p>" +
                "<br><p style='color:gray;font-size:12px'>URBAN Srl</p>" +
                "</body></html>";

        helper.setText(htmlMsg, true);

        ClassPathResource logo = new ClassPathResource("haine-logo.jpg");

        helper.addInline("logoImage", logo);

        mailSender.send(message);
    }
}

