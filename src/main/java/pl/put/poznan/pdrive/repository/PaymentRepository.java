package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.pdrive.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
