package pl.put.poznan.pdrive.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.Location;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.entity.VehicleType;
import pl.put.poznan.pdrive.service.StationsService;
import pl.put.poznan.pdrive.service.VehicleService;
import pl.put.poznan.pdrive.service.VehicleTypeService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AdminPanel implements Initializable {
    private final StageInitializer stageInitializer;
    public final VehicleService vehicleService;
    public final StationsService stationsService;
    public final VehicleTypeService vehicleTypeService;
    public final CurrValues currValues;
    @FXML
    public ComboBox<VehicleType> vehicleTypeComboBox;
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
    @FXML
    public ComboBox<Station> stationBox;
    @FXML
    public ComboBox<Location>localizationComboBox;
    @FXML
    public TextField adressTextField;
    @Value("classpath:/login.fxml")
    Resource loginResource;


    public AdminPanel(StageInitializer stageInitializer, VehicleService vehicleService, StationsService stationsService, VehicleTypeService vehicleTypeService, CurrValues currValues) {
        this.stageInitializer = stageInitializer;
        this.vehicleService = vehicleService;
        this.stationsService = stationsService;
        this.vehicleTypeService = vehicleTypeService;
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
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSpinnerBattery();
        initializeSpinnerTrips();
        stations();
        vehicleTypes();
    }

    public void stations() {
        ObservableList<Station> stations = FXCollections.observableArrayList(stationsService.getAllStations());
        stationBox.setItems(stations);
        stationBox.getSelectionModel().selectFirst();
        currValues.setCurrentStation(stationBox.getValue());

        stationBox.valueProperty().addListener(new ChangeListener<Station>() {
            @Override
            public void changed(ObservableValue<? extends Station> observableValue, Station station, Station t1) {
                currValues.setCurrentStation(stationBox.getValue());
            }
        });
    }

    public void vehicleTypes(){
        ObservableList<VehicleType> vehicleTypes = FXCollections.observableArrayList(vehicleTypeService.getVehicleTypes());
        vehicleTypeComboBox.setItems(vehicleTypes);
        vehicleTypeComboBox.setCellFactory(new Callback<ListView<VehicleType>, ListCell<VehicleType>>() {
            @Override
            public ListCell<VehicleType> call(ListView<VehicleType> param) {
                return new ListCell<VehicleType>() {
                    @Override
                    public void updateItem(VehicleType vehicle, boolean empty) {
                        super.updateItem(vehicle, empty);
                        if (empty || vehicle == null) {
                            setText(null);
                        } else {
                            setText(vehicle.getName().value);
                        }
                    }
                };
            }
        });
        vehicleTypeComboBox.getSelectionModel().selectFirst();
        currValues.setCurrentVehicleType(vehicleTypeComboBox.getValue());

        vehicleTypeComboBox.valueProperty().addListener(new ChangeListener<VehicleType>() {
            @Override
            public void changed(ObservableValue<? extends VehicleType> observableValue, VehicleType t2, VehicleType t1) {
                currValues.setCurrentVehicleType(vehicleTypeComboBox.getValue());
                System.out.println(currValues.getCurrentVehicleType());
            }
        });
    }

    public void initializeSpinnerTrips() {
        SpinnerValueFactory<Integer> valueTripCount = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000);
        valueTripCount.setValue(0);
        tripCountSpinner.setValueFactory(valueTripCount);
        currValues.setCurrentValueTripCountSpinner(Long.valueOf(tripCountSpinner.getValue()));
        tripCountSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currValues.setCurrentValueTripCountSpinner(Long.valueOf(tripCountSpinner.getValue()));
            }
        });
    }

    public void initializeSpinnerBattery() {
        SpinnerValueFactory<Integer> valueBatteryCharge = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueBatteryCharge.setValue(100);
        batteryChargeSpinner.setValueFactory(valueBatteryCharge);
        currValues.setCurrentValueBatteryChargeSpinner(Long.valueOf(batteryChargeSpinner.getValue()));

        batteryChargeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currValues.setCurrentValueBatteryChargeSpinner(Long.valueOf(batteryChargeSpinner.getValue()));
            }
        });
    }

    public void OnEnterAdress(ActionEvent event) {
    }

    public void
    public void onAddStationButton(ActionEvent event) {
    }
}
