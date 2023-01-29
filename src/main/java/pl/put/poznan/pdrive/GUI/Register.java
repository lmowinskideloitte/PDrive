package pl.put.poznan.pdrive.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.StageInitializer;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.service.UserService;

@Controller
public class Register {

    private final StageInitializer stageInitializer;
    private final UserService userService;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registerLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @Value("classpath:/login.fxml")
    Resource loginResource;

    public Register(StageInitializer stageInitializer, UserService userService) {
        this.stageInitializer = stageInitializer;
        this.userService = userService;
    }

    public void userRegister(ActionEvent event) {
        User user = userService.checkUser(usernameField.getText(), passwordField.getText());
        if (user != null) {
            registerLabel.setText("Success!");
        } else {
            registerLabel.setText("Try again");
        }
    }

    public void switchToLogin(ActionEvent event) {
        stageInitializer.switchScene(loginResource);
    }
}
