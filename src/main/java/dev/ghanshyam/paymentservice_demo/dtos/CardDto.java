package dev.ghanshyam.paymentservice_demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDto {
    String id;
    String entity;
    String name;
    String last4;
    String network;
    String type;
    String issuer;
    Boolean international;
    Boolean emi;
    String sub_type;
    String token_iin;


}
