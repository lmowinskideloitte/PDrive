package pl.put.poznan.pdrive.service.impl;

import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.VehicleType;
import pl.put.poznan.pdrive.repository.VehicleTypeRepository;
import pl.put.poznan.pdrive.service.VehicleTypeService;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public List<VehicleType> getVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }
}
