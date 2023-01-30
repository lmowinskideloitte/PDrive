package pl.put.poznan.pdrive.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.service.VehicleService;

@Controller
public class AdminPanel {
    private final StageInitializer stageInitializer;
    public final VehicleService vehicleService;
    @FXML
    public ComboBox<Vehicle> vehicleType;
    @FXML
    public Spinner<Long> tripCount;
    @FXML
    public Spinner<Long> batteryCharge;
    @FXML
    public Button logOutButton;
    @FXML
    public Button deleteVehicleButton;
    @FXML
    public Button addVehicleButton;
    @Value("classpath:/login.fxml")
    Resource loginResource;


    public AdminPanel(StageInitializer stageInitializer, VehicleService vehicleService) {
        this.stageInitializer = stageInitializer;
        this.vehicleService = vehicleService;
    }

    public void onVehicleType(ActionEvent event) {
    }

    public void onDeleteVehicleButton(ActionEvent event) {
    }

    public void onLogOutButton(ActionEvent event) {
        stageInitializer.switchScene(loginResource);
    }

    public void onAddVehicleButton(ActionEvent event) {
    }
}
