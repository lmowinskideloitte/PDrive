package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Event;
import pl.put.poznan.pdrive.entity.Vehicle;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByVehicle(Vehicle vehicle);
}
