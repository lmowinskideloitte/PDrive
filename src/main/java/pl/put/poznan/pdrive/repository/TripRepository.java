package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
