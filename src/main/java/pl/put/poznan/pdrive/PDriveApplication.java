package pl.put.poznan.pdrive;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PDriveApplication {

    public static void main(String[] args) {
        Application.launch(GuiApplication.class, args);
    }

}
