package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.entity.VehicleType;
import pl.put.poznan.pdrive.repository.StationRepository;
import pl.put.poznan.pdrive.repository.VehicleRepository;
import pl.put.poznan.pdrive.repository.VehicleTypeRepository;
import pl.put.poznan.pdrive.service.VehicleService;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    public final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final StationRepository stationRepository;


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByStation(Station station) {
        return vehicleRepository.findByStationAndRenterNull(station);
    }

    @Override
    public List<Vehicle> getRentedVehicles(User user) {
        return vehicleRepository.findByRenter(user);
    }

    @Override
    public Vehicle addVehicle(VehicleType vehicleType, Long tripCount, Long batteryCharge, Station station) {
        VehicleType byName = vehicleTypeRepository.findByName(vehicleType.getName());
        Station byId = stationRepository.findById(station.getId()).orElseThrow();
        Vehicle vehicle = new Vehicle();
        vehicle.setStation(byId);
        vehicle.setVehicleType(byName);
        vehicle.setTripCount(tripCount);
        vehicle.setBatteryCharge(batteryCharge);

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle rentVehicle(Vehicle vehicle, User renter) {
        vehicle.setRenter(renter);
        return vehicleRepository.save(vehicle);
    }
}
