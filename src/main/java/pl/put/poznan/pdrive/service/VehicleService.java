package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesByStation(Station station);
    List<Vehicle> getRentedVehicles(User user);

    Vehicle addVehicle();
    Vehicle rentVehicle(Vehicle vehicle, User renter);
}
