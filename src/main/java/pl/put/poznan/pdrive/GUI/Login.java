package pl.put.poznan.pdrive.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;
import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.service.UserService;


@Controller
public class Login {

    private final UserService userService;

    @FXML
    private Button loginButton;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public Login(UserService userService) {
        this.userService = userService;
    }

    public void userLogin(ActionEvent event) {
        User user = userService.checkUser(usernameField.getText(), passwordField.getText());
        if (user != null) {
            loginLabel.setText("Success!");
        } else {
            loginLabel.setText("Try again");
        }
    }

}
