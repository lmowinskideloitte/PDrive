package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Event;
import pl.put.poznan.pdrive.entity.Vehicle;

import java.util.List;

public interface EventService {

    List<Event> getEvent(Vehicle vehicle);
}
