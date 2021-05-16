package org.loose.fis.den.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.model.Appoint;
import org.loose.fis.den.model.loggedUser;
import org.loose.fis.den.services.AppointService;

import java.io.IOException;


public class AppointmentsController {

    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    private void updateListView() {
    }

    private static ObjectRepository<Appoint> AppointRepository = AppointService.getServicesRepository();
    @FXML
    public ListView<String> appointmentsList = new ListView<String>();



    public void deleteAction() throws Exception{
        if(appointmentsList.getSelectionModel().getSelectedItem()!=null)
            AppointService.deleteAppointAsAPacient(loggedUser.getActualUser(),appointmentsList.getSelectionModel().getSelectedItem().toString());
    }

    public void backAction(javafx.event.ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }
}
