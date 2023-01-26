package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.repository.StationRepository;
import pl.put.poznan.pdrive.service.StationsService;

import java.util.List;

@Service
@AllArgsConstructor
public class StationsServiceImpl implements StationsService {

    private final StationRepository stationRepository;

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
}
