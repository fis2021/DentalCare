package org.loose.fis.den.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class DocAppointController {

    @FXML
    public ListView<String> docApp = new ListView<String>();


    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    private static ObjectRepository<Appoint> AppointRepository = AppointService.getAppointsRepository();

    public void cancelAction() throws Exception{
        if(docApp.getSelectionModel().getSelectedItem()!=null)
            AppointService.deleteAppointAsADoc(loggedUser.getActualUser(),docApp.getSelectionModel().getSelectedItem().toString());
    }

    public void backAction(javafx.event.ActionEvent event) throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("doctor.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root1, 1000, 667));
        window.show();
    }

    public void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Appoint app : AppointRepository.find()) {
            if (app.getDoctorname().equals(loggedUser.getActualUser())) {
                items.add(app.getPacientname()+app.getOperation()+app.getDoctorname()+app.getDateandtime());
                docApp.setItems(items);
            }
        }
    }
}
