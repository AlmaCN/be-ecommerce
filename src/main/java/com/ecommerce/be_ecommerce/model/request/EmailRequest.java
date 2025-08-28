package com.ecommerce.be_ecommerce.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String receiver;
    private String orderId;
    private String product;
    private double price;

}
