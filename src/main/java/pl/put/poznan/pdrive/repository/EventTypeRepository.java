package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
