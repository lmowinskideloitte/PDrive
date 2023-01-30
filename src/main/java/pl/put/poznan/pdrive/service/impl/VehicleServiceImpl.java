package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.repository.VehicleRepository;
import pl.put.poznan.pdrive.service.VehicleService;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    public final VehicleRepository vehicleRepository;


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByStation(Station station) {
        return vehicleRepository.findByStation(station);
    }

    @Override
    public List<Vehicle> getRentedVehicles(User user) {
        return vehicleRepository.findByRenterAndStationNull(user);
    }

    @Override
    public Vehicle addVehicle() {
        return vehicleRepository.save(new Vehicle());
    }

    @Override
    public Vehicle rentVehicle(Vehicle vehicle, User renter) {
        vehicle.setStation(null);
        vehicle.setRenter(renter);
        return vehicleRepository.save(vehicle);
    }
}
