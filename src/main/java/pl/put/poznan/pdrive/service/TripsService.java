package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Trip;
import pl.put.poznan.pdrive.entity.User;

import java.util.List;

public interface TripsService {

    List<Trip> getTrips(Card card);
    List<Trip> getTrips(User user);
    Trip addTrip(Trip trip);
}
