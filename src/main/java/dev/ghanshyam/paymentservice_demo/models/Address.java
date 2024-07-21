package dev.ghanshyam.paymentservice_demo.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Address extends BaseModel {
    private String addressline1;
    private String addressline2;
    private String landmark;
    private String city;
    private String pincode;
    private String state;
    private String country;
}
