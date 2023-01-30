package pl.put.poznan.pdrive.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.service.VehicleService;

@Controller
public class AdminPanel {
    private final StageInitializer stageInitializer;
    public final VehicleService vehicleService;
    public ComboBox vehicleType;
    public Spinner tripCount;
    public Spinner batteryCharge;

    public AdminPanel(StageInitializer stageInitializer, VehicleService vehicleService) {
        this.stageInitializer = stageInitializer;
        this.vehicleService = vehicleService;
    }

    public void onVehicleType(ActionEvent event) {
    }
}
