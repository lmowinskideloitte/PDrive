package pl.put.poznan.pdrive.service;

import pl.put.poznan.pdrive.entity.Location;
import pl.put.poznan.pdrive.entity.Station;

import java.util.List;

public interface StationsService {

    List<Station> getAllStations();

    Station addStation(Location location, String address);
}
