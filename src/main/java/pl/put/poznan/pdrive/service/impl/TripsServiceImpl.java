package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.*;
import pl.put.poznan.pdrive.repository.TripRepository;
import pl.put.poznan.pdrive.service.PaymentService;
import pl.put.poznan.pdrive.service.TripsService;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class TripsServiceImpl implements TripsService {

    private final TripRepository tripRepository;
    private final PaymentService paymentService;

    @Override
    public List<Trip> getTrips(Card card) {
        return tripRepository.findByCard(card);
    }

    @Override
    public List<Trip> getTrips(User user) {
        return tripRepository.findByCard_User(user);
    }

    @Override
    public Trip addTrip(Vehicle vehicle, Station station, Card card) {
        Random random = new Random();
        Long distance = random.nextLong(1, 100);
        Trip trip = new Trip();
        trip.setOriginStation(vehicle.getStation());
        vehicle.setStation(station);
        vehicle.setRenter(null);
        trip.setDistance(distance);
        trip.setDestinationStation(station);
        trip.setPayment(paymentService.createPayment(card, distance*3));
        trip.setCard(trip.getPayment().getCard());
        trip.setVehicle(vehicle);
        return tripRepository.save(trip);
    }
}
