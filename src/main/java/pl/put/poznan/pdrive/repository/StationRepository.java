package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
}
