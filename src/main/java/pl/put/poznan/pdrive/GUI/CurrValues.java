package pl.put.poznan.pdrive.GUI;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;

@Service
@Setter
@Getter
public class CurrValues {
    User currentUser;
    Vehicle currentVehicle;
}
