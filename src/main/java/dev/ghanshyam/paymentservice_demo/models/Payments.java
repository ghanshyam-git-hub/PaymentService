package dev.ghanshyam.paymentservice_demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payments extends BaseModel{
    Integer amount;
    String currency;
    String razorpay_status;
}
