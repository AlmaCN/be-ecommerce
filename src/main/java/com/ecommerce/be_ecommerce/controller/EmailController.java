package com.ecommerce.be_ecommerce.controller;

import com.ecommerce.be_ecommerce.command.EmailCommand;
import com.ecommerce.be_ecommerce.model.request.EmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private BeanFactory beanFactory;

    @PostMapping("/orderConfirmation")
    @Operation(summary = "Send email for order confirmation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email Sent")
    })
    public ResponseEntity<Void> orderConfirmation(@RequestBody EmailRequest req) {
        try {
            beanFactory.getBean(EmailCommand.class, req).doExecute();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessagingException e) {
            throw new RuntimeException("Email not sent", e.getCause());
        }
    }

}
