package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.*;

import java.util.List;

public interface TripsService {

    List<Trip> getTrips(Card card);
    List<Trip> getTrips(User user);
    Trip addTrip(Vehicle vehicle, Station station, Card card);
}
