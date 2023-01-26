package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
