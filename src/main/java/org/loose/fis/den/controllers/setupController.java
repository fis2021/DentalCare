package org.loose.fis.den.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.model.Operations;
import org.loose.fis.den.model.selectedDoc;
import org.loose.fis.den.model.loggedUser;
import org.loose.fis.den.services.AppointService;
import org.loose.fis.den.services.OperationService;

import java.io.IOException;

public class setupController {

    @FXML
    public TextField dateandtime;

    @FXML
    public ListView<String> docOp = new ListView<String>();

    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    private static ObjectRepository<Operations> operationsObjectRepository = OperationService.getOperationRepository();

    public void backAction(javafx.event.ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("set-up-an-appointment.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

    public void sendAction(javafx.event.ActionEvent event){
        AppointService.addAppointment(loggedUser.getActualUser(),selectedDoc.getDocName(),docOp.getSelectionModel().getSelectedItem().toString(),dateandtime.getText());
    }

    public void updateListView(){

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Operations op : operationsObjectRepository.find()) {
            if (op.getDoctorname().equals(selectedDoc.getDocName())) {
                items.add(op.getOperation());
                docOp.setItems(items);
            }
        }
    }
}