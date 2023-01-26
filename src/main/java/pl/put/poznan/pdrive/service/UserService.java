package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String username);
    User getUser(Card card);
    List<User> getAllUsers();
    User createUser(String username, String password);
}
