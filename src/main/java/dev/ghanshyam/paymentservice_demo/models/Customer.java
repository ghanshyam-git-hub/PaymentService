package dev.ghanshyam.paymentservice_demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseModel{
    Long customer_id;
    String name;
    String mobile;
    String email;
    @OneToOne
    Address address;

    @OneToMany(mappedBy = "customer")
    List<Orders> ordersList;

}
