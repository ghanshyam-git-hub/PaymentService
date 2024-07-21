package dev.ghanshyam.paymentservice_demo.repositories;

import dev.ghanshyam.paymentservice_demo.models.Address;
import dev.ghanshyam.paymentservice_demo.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepo extends JpaRepository<Address, UUID> {

    @Query("select a from Address a where a.addressline1=:addressline1 and a.addressline2=:addressline2 and a.landmark = :landmark and a.pincode = :pincode and a.city=:city and a.state = :state and a.country=:country")
    Address checkAddressExist(String addressline1,String addressline2,String landmark,String pincode,String city,String state,String country);
}
