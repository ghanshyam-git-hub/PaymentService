CREATE TABLE address
(
    uuid         BINARY(16)   NOT NULL,
    addressline1 VARCHAR(255) NULL,
    addressline2 VARCHAR(255) NULL,
    landmark     VARCHAR(255) NULL,
    city         VARCHAR(255) NULL,
    pincode      VARCHAR(255) NULL,
    state        VARCHAR(255) NULL,
    country      VARCHAR(255) NULL,
    CONSTRAINT pk_address PRIMARY KEY (uuid)
);

CREATE TABLE customer
(
    uuid         BINARY(16)   NOT NULL,
    customer_id  BIGINT NULL,
    name         VARCHAR(255) NULL,
    mobile       VARCHAR(255) NULL,
    email        VARCHAR(255) NULL,
    address_uuid BINARY(16)   NULL,
    CONSTRAINT pk_customer PRIMARY KEY (uuid)
);

CREATE TABLE orders
(
    uuid                  BINARY(16)   NOT NULL,
    customer_uuid         BINARY(16)   NULL,
    payments_uuid         BINARY(16)   NULL,
    created_at            datetime NULL,
    razorpay_order_id     VARCHAR(255) NULL,
    razorpay_order_status VARCHAR(255) NULL,
    CONSTRAINT pk_orders PRIMARY KEY (uuid)
);

CREATE TABLE payments
(
    uuid            BINARY(16)   NOT NULL,
    amount          INT NULL,
    currency        VARCHAR(255) NULL,
    razorpay_status VARCHAR(255) NULL,
    CONSTRAINT pk_payments PRIMARY KEY (uuid)
);

ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_ADDRESS_UUID FOREIGN KEY (address_uuid) REFERENCES address (uuid);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CUSTOMER_UUID FOREIGN KEY (customer_uuid) REFERENCES customer (uuid);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_PAYMENTS_UUID FOREIGN KEY (payments_uuid) REFERENCES payments (uuid);