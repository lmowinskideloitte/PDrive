package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Trip;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.repository.TripRepository;
import pl.put.poznan.pdrive.service.TripsService;

import java.util.List;

@Service
@AllArgsConstructor
public class TripsServiceImpl implements TripsService {

    private final TripRepository tripRepository;

    @Override
    public List<Trip> getTrips(Card card) {
        return tripRepository.findByCard(card);
    }

    @Override
    public List<Trip> getTrips(User user) {
        return tripRepository.findByCard_User(user);
    }

    @Override
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }
}
