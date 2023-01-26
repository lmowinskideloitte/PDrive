package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.repository.CardRepository;
import pl.put.poznan.pdrive.service.CardService;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Card getCard(User user) {
        return cardRepository.findByUser(user);
    }

    @Override
    public Card topUpCard(Card card, Long transfer) {
        card.setBalance(card.getBalance() + transfer);
        card.setExpirationDate(LocalDate.now().plusMonths(1));
        return card;
    }
}
