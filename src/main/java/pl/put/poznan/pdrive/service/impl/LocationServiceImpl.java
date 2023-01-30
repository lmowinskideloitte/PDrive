package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Location;
import pl.put.poznan.pdrive.repository.LocationRepository;
import pl.put.poznan.pdrive.service.LocationService;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
