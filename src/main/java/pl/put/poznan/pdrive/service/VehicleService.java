package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.entity.VehicleType;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesByStation(Station station);
    List<Vehicle> getRentedVehicles(User user);

    Vehicle addVehicle(VehicleType vehicleType, Long tripCount, Long batteryCharge, Station station);
    Vehicle rentVehicle(Vehicle vehicle, User renter);
    void deleteVehicle(Vehicle vehicle);
}
