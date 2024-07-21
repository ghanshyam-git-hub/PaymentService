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
public interface CustomerRepo extends JpaRepository<Customer, UUID> {

    @Query("select MAX(c.customer_id) from Customer c")
    public Long getLatestCustomerId();

    @Query("select c.ordersList from Customer c where c=:customer")
    public List<Orders>getOrderListOfTheCustomer(Customer customer);

    @Query("select c from Customer c where c.name=:cust_name and c.address=:cust_add")
    public Customer getCustomerByNameAndAddress(String cust_name,Address cust_add);
}
