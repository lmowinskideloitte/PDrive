package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Payment;

public interface PaymentService {

    Payment createPayment(Card card, Long cost);
}
