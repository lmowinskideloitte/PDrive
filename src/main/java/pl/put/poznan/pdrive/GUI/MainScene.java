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
import pl.put.poznan.pdrive.entity.Card;
import pl.put.poznan.pdrive.entity.Station;
import pl.put.poznan.pdrive.entity.Trip;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.service.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MainScene implements Initializable {
    private final StageInitializer stageInitializer;

    private final CardService cardService;

    private final TripsService tripsService;
    public final VehicleService vehicleService;
    public final CurrValues currValues;
    public final StationsService stationsService;
    public Button rentButton;
    public Button refreshButton;
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
    @FXML
    public ComboBox<Station> stationBox;
    @FXML
    public ComboBox<Card> cardBox;
    @FXML
    public javafx.scene.control.Label personal_label;
    @FXML
    public ListView<Vehicle>rentedList;
    @FXML
    private ListView<Vehicle> availableVehiclesList;
    @FXML
    private javafx.scene.control.Button logOutButton;


    @Value("classpath:/login.fxml")
    Resource loginResource;

    public MainScene(StageInitializer stageInitializer, CardService cardService, TripsService tripsService, VehicleService vehicleService, CurrValues currValues, StationsService stationsService) {
        this.stageInitializer = stageInitializer;
        this.cardService = cardService;
        this.tripsService = tripsService;
        this.vehicleService = vehicleService;
        this.currValues = currValues;
        this.stationsService = stationsService;
    }

    public void makeTable() {
        paymentIdkolumn = new TableColumn<Trip, Long>("payment [zł]");
        paymentIdkolumn.setCellValueFactory(new PropertyValueFactory<>("payment"));

        originStationIdKolumn = new TableColumn<Trip, Station>("origin Station");
        originStationIdKolumn.setCellValueFactory(new PropertyValueFactory<>("originStation"));

        destinationStationKolumn = new TableColumn<Trip, Station>("destination Station");
        destinationStationKolumn.setCellValueFactory(new PropertyValueFactory<>("destinationStation"));

        distanceKolumn = new TableColumn<Trip, Long>("distance [km]");
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
        ObservableList<Station> stations = FXCollections.observableArrayList(stationsService.getAllStations());
        stationBox.setItems(stations);

        ObservableList<Card> cards = FXCollections.observableArrayList(cardService.getCards(currValues.getCurrentUser()));
        cardBox.setItems(cards);


        populateTable();




        availableVehiclesList.setCellFactory(new Callback<ListView<Vehicle>, ListCell<Vehicle>>() {
            @Override
            public ListCell<Vehicle> call(ListView<Vehicle> param) {
                return new ListCell<Vehicle>() {
                    @Override
                    public void updateItem(Vehicle vehicle, boolean empty) {
                        super.updateItem(vehicle, empty);
                        if (empty || vehicle == null) {
                            setText(null);
                        } else {
                            setText(vehicle.getVehicleType().getName().value + " " + vehicle.getBatteryCharge());
                        }
                    }
                };
            }
        });
        availableVehiclesList.getItems().addAll(vehicleService.getVehiclesByStation(stationBox.getValue()));


        availableVehiclesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {
            @Override
            public void changed(ObservableValue<? extends Vehicle> observableValue, Vehicle s, Vehicle t1) {
                System.out.println(availableVehiclesList.getSelectionModel().getSelectedItem());
            }
        });


        rentedList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Vehicle> call(ListView<Vehicle> param) {
                return new ListCell<Vehicle>() {
                    @Override
                    public void updateItem(Vehicle vehicle, boolean empty) {
                        super.updateItem(vehicle, empty);
                        if (empty || vehicle == null) {
                            setText(null);
                        } else {
                            setText(vehicle.getVehicleType().getName().value + " " + vehicle.getBatteryCharge());
                        }
                    }
                };
            }
        });
        rentedList.getItems().addAll(vehicleService.getRentedVehicles(currValues.getCurrentUser()));


        rentedList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {
            @Override
            public void changed(ObservableValue<? extends Vehicle> observableValue, Vehicle s, Vehicle t1) {
                System.out.println(rentedList.getSelectionModel().getSelectedItem());
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

    public void getCurrentStation(ActionEvent event) {
        Station station = stationBox.getValue();
        currValues.setCurrentStation(station);
        availableVehiclesList.getItems().setAll(vehicleService.getVehiclesByStation(stationBox.getValue()));

    }

    public void getCurrentCard(ActionEvent event) {
        Card card = cardBox.getValue();
        currValues.setCurrentCard(card);
    }
}
