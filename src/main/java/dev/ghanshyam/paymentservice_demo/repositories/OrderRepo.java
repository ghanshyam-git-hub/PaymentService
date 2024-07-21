package dev.ghanshyam.paymentservice_demo.repositories;

import dev.ghanshyam.paymentservice_demo.models.Address;
import dev.ghanshyam.paymentservice_demo.models.Customer;
import dev.ghanshyam.paymentservice_demo.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Orders, UUID> {

    @Query("select o from Orders o JOIN Customer c ON o.customer=c where o.customer.name=:customer_name AND o.customer.address =:address")
    List<Orders> getOrderListOfCustomer(String customer_name, Address address);

    @Query("select o from Orders o where o.razorpay_order_id=:razorpay_order_id")
    Orders getByRazorpay_order_id(String razorpay_order_id);
}
