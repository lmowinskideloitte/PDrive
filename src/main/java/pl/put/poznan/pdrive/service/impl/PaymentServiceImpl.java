package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Payment;
import pl.put.poznan.pdrive.repository.PaymentRepository;
import pl.put.poznan.pdrive.service.PaymentService;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Card card, Long cost) {
        Payment payment = new Payment();
        payment.setCard(card);
        payment.setCost(cost);
        return paymentRepository.save(payment);
    }
}
