package pl.put.poznan.pdrive.GUI;



import jakarta.annotation.PostConstruct;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.service.UserService;
import pl.put.poznan.pdrive.service.VehicleService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainScene implements Initializable{
    private final StageInitializer stageInitializer;
    private final UserService userService;
    public final VehicleService vehicleService;
    public final CurrValues currValues;
    public Button rentButton;
    public Button refreshButton;
    public String chosenVehicle;

    private Long chosenVehicleId;

    Vehicle currentVehicle;
    @FXML
    public javafx.scene.control.Label personal_label;
    @FXML
    private ListView<String>myListView;
    @FXML
    private javafx.scene.control.Button logOutButton;



    @Value("classpath:/login.fxml")
    Resource loginResource;

    public MainScene(StageInitializer stageInitializer, UserService userService, VehicleService vehicleService, CurrValues currValues){
        this.stageInitializer = stageInitializer;
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.currValues = currValues;
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

    public void rent(ActionEvent event){
        //TODO
    }

    public void setValues(ActionEvent event) {
        if (currValues.getCurrentUser() != null) {
            personal_label.setText(currValues.getCurrentUser().getUsername());
        }
    }
}
