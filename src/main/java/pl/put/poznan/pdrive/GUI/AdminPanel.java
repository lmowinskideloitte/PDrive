package pl.put.poznan.pdrive.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.*;
import pl.put.poznan.pdrive.service.LocationService;
import pl.put.poznan.pdrive.service.StationsService;
import pl.put.poznan.pdrive.service.VehicleService;
import pl.put.poznan.pdrive.service.VehicleTypeService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AdminPanel implements Initializable {
    private final StageInitializer stageInitializer;
    public final VehicleService vehicleService;

    public final LocationService locationService;

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
    public ComboBox<Location> localizationComboBox;
    @FXML
    public TextField adressTextField;
    @FXML
    public Button addStationButton;
    @FXML
    public Label addVehicleLabel;
    @FXML
    public Label addStationLabel;
    @FXML
    public TableColumn<Vehicle, VehicleType> vehicleTypeTableColumn;
    @FXML
    public TableColumn<Vehicle, Long> vehicleTripCountColumn;
    @FXML
    public TableColumn<Vehicle, Long> vehicleBatteryChargeColumn;
    @FXML
    public TableColumn<Vehicle, Station> vehicleStationColumn;
    @FXML
    public TableView<Vehicle> table;
    @Value("classpath:/login.fxml")
    Resource loginResource;


    public AdminPanel(StageInitializer stageInitializer, VehicleService vehicleService, LocationService locationService, StationsService stationsService, VehicleTypeService vehicleTypeService, CurrValues currValues) {
        this.stageInitializer = stageInitializer;
        this.vehicleService = vehicleService;
        this.locationService = locationService;
        this.stationsService = stationsService;
        this.vehicleTypeService = vehicleTypeService;
        this.currValues = currValues;
    }

    public void onVehicleType(ActionEvent event) {
    }

    public void onDeleteVehicleButton(ActionEvent event) {
        Vehicle selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            vehicleService.deleteVehicle(selectedItem);
            populateTable();
        }
    }

    public void onLogOutButton(ActionEvent event) {
        stageInitializer.switchScene(loginResource);
    }

    public void onAddVehicleButton(ActionEvent event) {
        if (currValues.getCurrentVehicleType() != null && currValues.getCurrentStation() != null
                && currValues.getCurrentValueBatteryChargeSpinner() != null && currValues.getCurrentValueTripCountSpinner() != null) {
            Vehicle vehicle = vehicleService.addVehicle(currValues.getCurrentVehicleType(), currValues.getCurrentValueTripCountSpinner(),
                    currValues.getCurrentValueBatteryChargeSpinner(), currValues.getCurrentStation());
            addVehicleLabel.setText("Succeed! %d".formatted(vehicle.getId()));
            populateTable();
        } else {
            addVehicleLabel.setText("Wrong values!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSpinnerBattery();
        initializeSpinnerTrips();
        stations();
        vehicleTypes();
        localizationsComboBox();
        populateTable();
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

    public void vehicleTypes() {
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
        currValues.setCurrentAdress(adressTextField.getText());
    }

    public void localizationsComboBox() {
        ObservableList<Location> locations = FXCollections.observableArrayList(locationService.getAllLocations());
        localizationComboBox.setItems(locations);
        localizationComboBox.getSelectionModel().selectFirst();
        currValues.setCurrentLocation(localizationComboBox.getValue());

        localizationComboBox.valueProperty().addListener(new ChangeListener<Location>() {
            @Override
            public void changed(ObservableValue<? extends Location> observableValue, Location l1, Location l2) {
                currValues.setCurrentLocation(localizationComboBox.getValue());
            }
        });
    }

    public void onAddStationButton(ActionEvent event) {
        currValues.setCurrentAdress(adressTextField.getText());
        if (currValues.getCurrentLocation() != null && currValues.getCurrentAdress() != null) {
            stationsService.addStation(currValues.getCurrentLocation(), currValues.getCurrentAdress());
            addStationLabel.setText("succeed");
            stations();
        } else {
            addStationLabel.setText("Wrong data!");
        }
    }

    public void makeTable() {
        vehicleTypeTableColumn = new TableColumn<Vehicle, VehicleType>("type");
        vehicleTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

        vehicleTripCountColumn = new TableColumn<Vehicle, Long>("trips count");
        vehicleTripCountColumn.setCellValueFactory(new PropertyValueFactory<>("tripCount"));

        vehicleBatteryChargeColumn = new TableColumn<Vehicle, Long>("battery charge");
        vehicleBatteryChargeColumn.setCellValueFactory(new PropertyValueFactory<>("batteryCharge"));

        vehicleStationColumn = new TableColumn<Vehicle, Station>("Station");
        vehicleStationColumn.setCellValueFactory(new PropertyValueFactory<>("station"));

        table.getColumns().setAll(vehicleTypeTableColumn, vehicleTripCountColumn, vehicleBatteryChargeColumn, vehicleStationColumn);
    }

    public void populateTable() {
        makeTable();
        List<Vehicle> serviceVehicles = vehicleService.getAllVehicles();

        ObservableList<Vehicle> trips = FXCollections.observableArrayList();
        if (serviceVehicles != null) {
            trips.addAll(serviceVehicles);
        }
        table.setItems(trips);
    }
}
