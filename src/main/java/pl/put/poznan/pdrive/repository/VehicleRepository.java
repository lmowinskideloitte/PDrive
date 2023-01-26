package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
