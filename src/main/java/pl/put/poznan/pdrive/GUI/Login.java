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

import java.util.Objects;


@Controller
public class Login {

    private final StageInitializer stageInitializer;
    private final UserService userService;
    private final CurrValues currValues;
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

    @Value("classpath:/AdminPanel.fxml")
    Resource adminSceneResource;

    public Login(StageInitializer stageInitializer, UserService userService, CurrValues currValues) {
        this.stageInitializer = stageInitializer;
        this.userService = userService;
        this.currValues = currValues;
    }

    public void userLogin(ActionEvent event) {
        User user = userService.checkUser(usernameField.getText(), passwordField.getText());
        if (user != null) {
            loginLabel.setText("Success!");
            currValues.setCurrentUser(user);
            if(Objects.equals(usernameField.getText(), "admin") && Objects.equals(passwordField.getText(), "admin")){ //TODO userService Find user by role
                switchToAdminScene(event);
            }
            else {
                switchToMainScene(event);
            }
        } else {
            loginLabel.setText("Try again");
        }
    }

    private void switchToAdminScene(ActionEvent event) {
        stageInitializer.switchScene(adminSceneResource);
    }

    public void switchToMainScene(ActionEvent event){
        stageInitializer.switchScene(main_scene_Resource);
    }

    public void switchToRegister(ActionEvent event) {
        stageInitializer.switchScene(registerResource);
    }

}
