package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.User;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByUser(User user);
}
