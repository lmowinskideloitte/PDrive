package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Location;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.repository.LocationRepository;
import pl.put.poznan.pdrive.repository.StationRepository;
import pl.put.poznan.pdrive.service.StationsService;

import java.util.List;

@Service
@AllArgsConstructor
public class StationsServiceImpl implements StationsService {

    private final StationRepository stationRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station addStation(Location location, String address) {
        Location found = locationRepository.findById(location.getId()).orElseThrow();
        Station station = new Station();
        station.setLocation(found);
        station.setAddress(address);

        return stationRepository.save(station);
    }
}
