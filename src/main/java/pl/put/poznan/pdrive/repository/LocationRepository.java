package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
