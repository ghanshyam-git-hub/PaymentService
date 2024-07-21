package dev.ghanshyam.paymentservice_demo.controllers;

import com.razorpay.RazorpayException;
import dev.ghanshyam.paymentservice_demo.dtos.OrderDto;
import dev.ghanshyam.paymentservice_demo.models.Orders;
import dev.ghanshyam.paymentservice_demo.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ViewController {
    OrderService orderService;

    ViewController (OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/")
        public String init(){
        return "index";
    }


    @PostMapping(value = "/order", produces = "application/json")
    @ResponseBody
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws RazorpayException {
        Orders orders = orderService.createOrder(orderDto);
        orderDto.setRazorpay_order_id(orders.getRazorpay_order_id());
        orderDto.setRazorpay_status(orders.getRazorpay_order_status());
        return new ResponseEntity<>(orderDto,HttpStatus.CREATED);
    }

    @PostMapping("/updatePayment")
    public String updatePaymentStatus(@RequestParam Map<String,String> razorpay_payload){
        String razorpay_order_id = razorpay_payload.get("razorpay_order_id");
        orderService.updateOrderStatus(razorpay_order_id);
        return "thank-you";
    }




}
