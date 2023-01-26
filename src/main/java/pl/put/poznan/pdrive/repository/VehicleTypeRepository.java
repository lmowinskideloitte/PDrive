package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
