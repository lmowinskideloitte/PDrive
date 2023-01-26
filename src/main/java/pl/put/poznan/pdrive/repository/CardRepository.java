package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
