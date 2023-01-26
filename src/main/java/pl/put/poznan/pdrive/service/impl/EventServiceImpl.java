package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Event;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.repository.EventRepository;
import pl.put.poznan.pdrive.service.EventService;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getEvent(Vehicle vehicle) {
        return eventRepository.findByVehicle(vehicle);
    }
}
