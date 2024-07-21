package dev.ghanshyam.paymentservice_demo.dtos;

import dev.ghanshyam.paymentservice_demo.models.Orders;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    String id;
    String entity;
    Long amount;
    String currency;
    String status;
    String order_id;
    String invoice_id;
    Boolean international;
    String method;
    Long amount_refunded;
    String refund_status;
    String captured;
    String description;
    String card_id;
    CardDto cardDto;
    String bank;
    String wallet;
    String vpa;
    String email;
    String contact;
    String[]notes;
    Long fee;
    Long tax;
    String error_code;
    String error_description;
    String error_source;
    String error_step;
    String error_reason;
    AcquirerDto acquirer_data;
    String created_at;
}
