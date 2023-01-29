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
public class Login {

    private final StageInitializer stageInitializer;
    private final UserService userService;

    CurrValues currValues;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @Value("classpath:/register.fxml")
    Resource registerResource;

    @Value("classpath:/main_scene.fxml")
    Resource main_scene_Resource;

    public Login(StageInitializer stageInitializer, UserService userService) {
        this.stageInitializer = stageInitializer;
        this.userService = userService;
    }

    public void userLogin(ActionEvent event) {
        User user = userService.checkUser(usernameField.getText(), passwordField.getText());
        if (user != null) {
            loginLabel.setText("Success!");
            //currValues.setUserName(user.getUsername());
            switchToMainScene(event);
        } else {
            loginLabel.setText("Try again");
        }
    }

    public void switchToMainScene(ActionEvent event){
        stageInitializer.switchScene(main_scene_Resource);
    }

    public void switchToRegister(ActionEvent event) {
        stageInitializer.switchScene(registerResource);
    }
}
