package org.loose.fis.den.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PacientController {

    public void logoutAction(javafx.event.ActionEvent login) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage)((Node)login.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

    public void setUpAppointment(javafx.event.ActionEvent login) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("set-up-an-appointment.fxml"));
        Stage window = (Stage)((Node)login.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

    public void seeYourAppointments(javafx.event.ActionEvent login) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("appointments.fxml"));
        Stage window = (Stage)((Node)login.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }
}