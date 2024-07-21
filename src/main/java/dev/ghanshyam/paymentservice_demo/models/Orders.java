package dev.ghanshyam.paymentservice_demo.models;

import com.razorpay.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders extends BaseModel {
    @ManyToOne
    Customer customer;

    @OneToOne
    Payments payments;

    Timestamp created_at;

    String razorpay_order_id;
    String razorpay_order_status;

}
