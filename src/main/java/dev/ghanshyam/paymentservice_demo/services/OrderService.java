package dev.ghanshyam.paymentservice_demo.services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.ghanshyam.paymentservice_demo.dtos.OrderDto;
import dev.ghanshyam.paymentservice_demo.models.Address;
import dev.ghanshyam.paymentservice_demo.models.Customer;
import dev.ghanshyam.paymentservice_demo.models.Orders;
import dev.ghanshyam.paymentservice_demo.models.Payments;
import dev.ghanshyam.paymentservice_demo.repositories.AddressRepo;
import dev.ghanshyam.paymentservice_demo.repositories.CustomerRepo;
import dev.ghanshyam.paymentservice_demo.repositories.OrderRepo;
import dev.ghanshyam.paymentservice_demo.repositories.PaymentRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class OrderService {
    private final CustomerRepo customerRepo;
    private final PaymentRepo paymentRepo;
    private final AddressRepo addressRepo;
    @Value("${key_id}")
    String key_id;

    @Value("${key_secret}")
    String key_secret;

    OrderRepo orderRepo;
    OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, PaymentRepo paymentRepo, AddressRepo addressRepo){
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.paymentRepo = paymentRepo;
        this.addressRepo = addressRepo;
    }

    public Orders createOrder(OrderDto orderDto) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(key_id, key_secret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",orderDto.getAmount()*100);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", orderDto.getEmail());

        Order razorpay_order = razorpay.orders.create(orderRequest);

        Orders our_order = convertOrderDtoToOrders(orderDto,razorpay_order);
        our_order.setRazorpay_order_id(razorpay_order.get("id"));
        our_order.setRazorpay_order_status((razorpay_order.get("status")));

        orderRepo.save(our_order);
// Add the current order also in the list of orders of the customer
        Customer customer = our_order.getCustomer();
        List<Orders>ordersListOfCustomer = customer.getOrdersList();

        if(ordersListOfCustomer!=null)
        customer.getOrdersList().add(our_order);
        else
        {
            List<Orders>newList = new ArrayList<>();
            newList.add(our_order);
            customer.setOrdersList(newList);
        }

        customerRepo.save(customer);

        return our_order;
    }

    public Orders convertOrderDtoToOrders(OrderDto orderDto,Order razorpay_order){
        Orders orders = new Orders();

        Customer customer = new Customer();
        Payments payments = new Payments();
        Address address = new Address();

        payments.setAmount(orderDto.getAmount()); // razorpay amount will be in paisa
        payments.setCurrency(razorpay_order.get("currency"));
        payments.setRazorpay_status(razorpay_order.get("status"));

        Payments savedPayments = paymentRepo.save(payments); // payment is saved

        orders.setPayments(savedPayments); // set in orders

        address.setAddressline1(orderDto.getAddressline1());
        address.setAddressline2(orderDto.getAddressline2());
        address.setCity(orderDto.getCity());
        address.setLandmark(orderDto.getLandmark());
        address.setPincode(orderDto.getPincode());
        address.setState(orderDto.getState());
        address.setCountry(orderDto.getCountry());

        Address checkAddressExist = addressRepo.checkAddressExist(address.getAddressline1(),address.getAddressline2(),address.getLandmark(),address.getPincode(),address.getCity(),address.getState(),address.getCountry());
        Address savedaddress = null;

        if(checkAddressExist==null)
            savedaddress = addressRepo.save(address); // address saved before saving customer
        else
            savedaddress = checkAddressExist;

        Customer checkExistingCustomer = customerRepo.getCustomerByNameAndAddress(orderDto.getName(),savedaddress);
        if(checkExistingCustomer!=null){
            customer = checkExistingCustomer;
        }else{
            customer.setName(orderDto.getName());
            customer.setEmail(orderDto.getEmail());
            customer.setAddress(savedaddress);
            customer.setMobile(orderDto.getMobile());
            Long latestCustomerId = customerRepo.getLatestCustomerId();
            if(latestCustomerId!=null)
            customer.setCustomer_id(customerRepo.getLatestCustomerId()+1l);
            else
            customer.setCustomer_id(1l);
        }

        Customer savedCustomer = customerRepo.save(customer); // if not saved it will saved

        orders.setCustomer(savedCustomer);
        orders.setCreated_at(new java.sql.Timestamp(System.currentTimeMillis()));

        Orders savedOrders = orderRepo.save(orders);
        return savedOrders;
    }

    public void updateOrderStatus(String razorpay_order_id){
        Orders orders = orderRepo.getByRazorpay_order_id(razorpay_order_id);
        orders.setRazorpay_order_status("Payment Completed");
        orders.getPayments().setRazorpay_status("Payment Completed");
        orderRepo.save(orders);

    }
}
