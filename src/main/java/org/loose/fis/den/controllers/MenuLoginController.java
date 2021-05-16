package org.loose.fis.den.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import org.loose.fis.den.exceptions.IncorrectPasswordException;
import org.loose.fis.den.exceptions.RoleDoesNotMatchException;
import org.loose.fis.den.exceptions.UnknownUserException;
import org.loose.fis.den.services.UserService;


public class MenuLoginController {

    @FXML
    private Button cancelButton;
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    private String userRole;

    @FXML
    public void initialize() {
        role.getItems().addAll("Doctor", "Pacient");
    }

    @FXML
    public void LoginAction(javafx.event.ActionEvent login) throws Exception {
        try {
            UserService.checkUserData(usernameField.getText(), passwordField.getText(), (String) role.getValue());

            loginMessage.setText("Login successfully!");

            userRole = (String) role.getValue();

            if (userRole.equals("Pacient")) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
                Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
                window.setScene(new Scene(root, 1000, 667));
                window.show();
            } else {

            }
        } catch (UnknownUserException e) {
            loginMessage.setText(e.getMessage());
        } catch (RoleDoesNotMatchException e) {
            loginMessage.setText((e.getMessage()));
        } catch (IncorrectPasswordException e) {
            loginMessage.setText(e.getMessage());
        }
    }




    public void cancelButtonOnAction(ActionEvent event){
        Stage stage;
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



    public void goToRegisterAction(javafx.event.ActionEvent login) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage window = (Stage)((Node)login.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

}