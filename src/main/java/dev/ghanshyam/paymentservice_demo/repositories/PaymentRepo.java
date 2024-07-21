package dev.ghanshyam.paymentservice_demo.repositories;

import dev.ghanshyam.paymentservice_demo.models.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepo extends JpaRepository<Payments, UUID> {

}
