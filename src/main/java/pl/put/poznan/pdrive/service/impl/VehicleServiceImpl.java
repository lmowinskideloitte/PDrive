package pl.put.poznan.pdrive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.repository.VehicleRepository;
import pl.put.poznan.pdrive.service.VehicleService;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    public final VehicleRepository vehicleRepository;


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
