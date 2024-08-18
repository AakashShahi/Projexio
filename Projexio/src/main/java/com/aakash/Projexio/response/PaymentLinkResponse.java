package com.aakash.Projexio.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {

     private String payment_link_url;

     public String  payment_link_id;

}
