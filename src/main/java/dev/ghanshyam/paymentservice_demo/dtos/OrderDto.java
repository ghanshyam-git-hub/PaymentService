package dev.ghanshyam.paymentservice_demo.dtos;

import dev.ghanshyam.paymentservice_demo.models.Address;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String name;
    private String mobile;
    private String email;

    private String addressline1;
    private String addressline2;
    private String landmark;
    private String city;
    private String pincode;
    private String state;
    private String country;

    private Integer amount;
    private String currency;

    private String razorpay_order_id;
    private String razorpay_status;


}
