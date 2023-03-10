package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.UserType;
import pl.put.poznan.pdrive.repository.UserRepository;
import pl.put.poznan.pdrive.service.UserService;

import java.util.List;

import static pl.put.poznan.pdrive.entity.UserTypeE.USER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(Card card) {
        return userRepository.findUserByCardsContaining(card);
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserType userType = new UserType();
        userType.setRole(USER);
        user.setUserType(userType);
        return userRepository.save(user);
    }
}
