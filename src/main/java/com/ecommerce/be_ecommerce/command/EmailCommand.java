package com.ecommerce.be_ecommerce.command;

import com.ecommerce.be_ecommerce.model.request.EmailRequest;
import com.ecommerce.be_ecommerce.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmailCommand {

    @Autowired
    private EmailService emailService;

    private final EmailRequest req;

    EmailCommand (EmailRequest req) {
        this.req = req;
    }

    public void doExecute() {
        emailService.sendOrderEmail(req);
    }

}
