package pl.put.poznan.pdrive.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.Trip;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.service.TripsService;
import pl.put.poznan.pdrive.service.UserService;
import pl.put.poznan.pdrive.service.VehicleService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MainScene implements Initializable {
    private final StageInitializer stageInitializer;
    private final UserService userService;

    private final TripsService tripsService;
    public final VehicleService vehicleService;
    public final CurrValues currValues;
    public Button rentButton;
    public Button refreshButton;
    public String chosenVehicle;
    @FXML
    public TableColumn<Trip, Long> paymentIdkolumn;
    @FXML
    public TableColumn<Trip, Station> originStationIdKolumn;
    @FXML
    public TableColumn<Trip, Station> destinationStationKolumn;
    @FXML
    public TableColumn<Trip, Long> distanceKolumn;
    @FXML
    public TableColumn<Trip, Vehicle> vehicleIdKolumn;

    @FXML
    public TableView<Trip> table;

    private Long chosenVehicleId;

    Vehicle currentVehicle;
    @FXML
    public javafx.scene.control.Label personal_label;
    @FXML
    private ListView<String> myListView;
    @FXML
    private javafx.scene.control.Button logOutButton;


    @Value("classpath:/login.fxml")
    Resource loginResource;

    public MainScene(StageInitializer stageInitializer, UserService userService, TripsService tripsService, VehicleService vehicleService, CurrValues currValues) {
        this.stageInitializer = stageInitializer;
        this.userService = userService;
        this.tripsService = tripsService;
        this.vehicleService = vehicleService;
        this.currValues = currValues;
    }

    public void makeTable() {
        paymentIdkolumn = new TableColumn<Trip, Long>("payment");
        paymentIdkolumn.setCellValueFactory(new PropertyValueFactory<>("payment"));

        originStationIdKolumn = new TableColumn<Trip, Station>("origin Station");
        originStationIdKolumn.setCellValueFactory(new PropertyValueFactory<>("originStation"));

        destinationStationKolumn = new TableColumn<Trip, Station>("destination Station");
        destinationStationKolumn.setCellValueFactory(new PropertyValueFactory<>("destinationStation"));

        distanceKolumn = new TableColumn<Trip, Long>("distance");
        distanceKolumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        vehicleIdKolumn = new TableColumn<Trip, Vehicle>("Vehicle id");
        vehicleIdKolumn.setCellValueFactory(new PropertyValueFactory<>("vehicle"));

        table.getColumns().setAll(paymentIdkolumn, originStationIdKolumn, destinationStationKolumn, distanceKolumn, vehicleIdKolumn);
    }

    public void populateTable() {
        makeTable();
        List<Trip> serviceTrips = null;
        if (currValues.getCurrentUser() != null) {
            serviceTrips = tripsService.getTrips(currValues.getCurrentUser());
        }

        ObservableList<Trip> trips = FXCollections.observableArrayList();
        if (serviceTrips != null) {
            trips.addAll(serviceTrips);
        }
        table.setItems(trips);
    }


    public void setLogOutButton(ActionEvent event) {
        stageInitializer.switchScene(loginResource);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(
                vehicleService.getAllVehicles().stream()
                        .map(Object::toString).toList()
        );

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                chosenVehicle = myListView.getSelectionModel().getSelectedItem();
                chosenVehicleId = Long.parseLong(chosenVehicle.split("\\s+")[0]);
            }
        });
    }

    public void rent(ActionEvent event) {
        //TODO
    }

    public void setValues(ActionEvent event) {
        if (currValues.getCurrentUser() != null) {
            personal_label.setText(currValues.getCurrentUser().getUsername());
            populateTable();
        }
    }
}
