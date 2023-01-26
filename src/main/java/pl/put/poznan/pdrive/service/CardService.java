package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;

public interface CardService {

    Card getCard(User user);

    Card topUpCard(Card card, Long transfer);
}
