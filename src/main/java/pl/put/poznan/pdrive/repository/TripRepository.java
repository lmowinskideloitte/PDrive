package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Trip;
import pl.put.poznan.pdrive.entity.User;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByPayment_Card_User(User user);
}
