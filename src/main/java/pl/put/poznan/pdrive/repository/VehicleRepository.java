package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByRenterAndStationNull(User renter);
    List<Vehicle> findByStation(Station station);
}
