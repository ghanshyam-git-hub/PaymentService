ALTER TABLE paymentservicedemo.orders
    ADD razorpay_payment_id VARCHAR(255) NULL;

ALTER TABLE paymentservicedemo.orders
    ADD razorpay_signature VARCHAR(255) NULL;