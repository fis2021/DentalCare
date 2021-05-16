package org.loose.fis.den.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.den.exceptions.InvalidNumberException;
import org.loose.fis.den.exceptions.NameTakenException;
import org.loose.fis.den.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.den.services.UserService;
public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private TextField fullnameField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField numberField;
    @FXML
    public void initialize() {
        role.getItems().addAll("Doctor", "Pacient");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(), fullnameField.getText(), mailField.getText(), numberField.getText() );
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException | InvalidNumberException | NameTakenException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
    public void goToMenuAction(javafx.event.ActionEvent login) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage)((Node)login.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

}