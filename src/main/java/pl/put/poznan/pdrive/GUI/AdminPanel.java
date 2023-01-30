package pl.put.poznan.pdrive.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.entity.VehicleTypeE;
import pl.put.poznan.pdrive.service.VehicleService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AdminPanel implements Initializable {
    private final StageInitializer stageInitializer;
    public final VehicleService vehicleService;
    public final CurrValues currValues;
    @FXML
    public ComboBox<VehicleTypeE> vehicleType;
    @FXML
    public Spinner<Integer> tripCountSpinner;
    @FXML
    public Spinner<Integer> batteryChargeSpinner;
    @FXML
    public Button logOutButton;
    @FXML
    public Button deleteVehicleButton;
    @FXML
    public Button addVehicleButton;
    @Value("classpath:/login.fxml")
    Resource loginResource;


    public AdminPanel(StageInitializer stageInitializer, VehicleService vehicleService, CurrValues currValues) {
        this.stageInitializer = stageInitializer;
        this.vehicleService = vehicleService;
        this.currValues = currValues;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSpinnerBattery();
        initializeSpinnerTrips();
    }

    public void initializeSpinnerTrips(){
        SpinnerValueFactory<Integer> valueTripCount = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000);
        valueTripCount.setValue(0);
        tripCountSpinner.setValueFactory(valueTripCount);
        currValues.setCurrentValueTripCountSpinner(Long.valueOf(tripCountSpinner.getValue()));
        System.out.println(currValues.getCurrentValueTripCountSpinner());
    }

    public void initializeSpinnerBattery(){
        SpinnerValueFactory<Integer> valueBatteryCharge = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueBatteryCharge.setValue(100);
        batteryChargeSpinner.setValueFactory(valueBatteryCharge);
        currValues.setCurrentValueBatteryChargeSpinner(Long.valueOf(batteryChargeSpinner.getValue()));
        System.out.println(currValues.getCurrentValueBatteryChargeSpinner());
    }
}
