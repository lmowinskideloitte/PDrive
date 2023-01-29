package pl.put.poznan.pdrive;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.pdrive.entity.Vehicle;
import pl.put.poznan.pdrive.service.VehicleService;

@SpringBootApplication
public class PDriveApplication {

    public static void main(String[] args) {
        Application.launch(GuiApplication.class, args);
    }

}
