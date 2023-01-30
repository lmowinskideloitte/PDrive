package pl.put.poznan.pdrive.service.impl;

import jakarta.transaction.Transactional;
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
    public List<Trip> getTrips(User user) {
        return tripRepository.findByPayment_Card_User(user);
    }

    @Override
    @Transactional
    public Trip addTrip(Vehicle vehicle, Station station, Card card) {
        Random random = new Random();
        Long distance = random.nextLong(1, 100);
        Station originStation = vehicle.getStation();
        vehicle.setRenter(null);
        vehicle.setStation(station);

        Trip trip = new Trip(paymentService.createPayment(card, distance), vehicle, originStation, station, distance, null);
        return tripRepository.save(trip);
    }
}
