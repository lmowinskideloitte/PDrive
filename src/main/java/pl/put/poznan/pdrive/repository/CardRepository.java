package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUser(User user);

}
