package pl.put.poznan.pdrive.GUI;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.repository.VehicleRepository;
import pl.put.poznan.pdrive.service.UserService;
import pl.put.poznan.pdrive.service.VehicleService;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainScene implements Initializable{
    private final StageInitializer stageInitializer;
    private final UserService userService;
    public final VehicleService vehicleService;

    Vehicle currentVehicle;
    @FXML
    public javafx.scene.control.Label personal_label;
    @FXML
    private ListView<Vehicle> myListView;
    @FXML
    private javafx.scene.control.Button logOutButton;



    @Value("classpath:/login.fxml")
    Resource loginResource;

    public MainScene(StageInitializer stageInitializer, UserService userService, VehicleService vehicleService){
        this.stageInitializer = stageInitializer;
        this.userService = userService;
        this.vehicleService = vehicleService;
        //personal_label = userService.getUser();
    }

    public void setLogOutButton(ActionEvent event) {
        stageInitializer.switchScene(loginResource);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(vehicleService.getAllVehicles());
    }

    public void rent(ActionEvent event){

    }
}
