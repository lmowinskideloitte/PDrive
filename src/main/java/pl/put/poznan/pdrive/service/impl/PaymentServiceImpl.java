package pl.put.poznan.pdrive.service.impl;

import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Payment;
import pl.put.poznan.pdrive.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public Payment createPayment(Card card, Long cost) {
        Payment payment = new Payment();
        payment.setCard(card);
        payment.setCost(cost);
        return payment;
    }
}
