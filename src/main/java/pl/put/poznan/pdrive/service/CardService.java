package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;

import java.util.List;

public interface CardService {

    List<Card> getCards(User user);

    Card topUpCard(Card card, Long transfer);
}
