package pl.put.poznan.pdrive.GUI;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.entity.Vehicle;

@Service
@Setter
@Getter
public class CurrValues {
    private User currentUser;
    private Vehicle currentVehicle;
    private Vehicle rentedVehicle;
    private Station currentStation;
    private Card currentCard;
    private Long currentValueTripCountSpinner;

    private Long currentValueBatteryChargeSpinner;
}
