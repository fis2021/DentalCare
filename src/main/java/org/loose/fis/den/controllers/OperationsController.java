package org.loose.fis.den.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.exceptions.EmptyTextException;
import org.loose.fis.den.exceptions.OperationExistsException;
import org.loose.fis.den.model.Appoint;
import org.loose.fis.den.model.Operations;
import org.loose.fis.den.model.loggedUser;
import org.loose.fis.den.services.AppointService;
import org.loose.fis.den.services.OperationService;

import java.io.IOException;
import java.util.Optional;

public class OperationsController {

    @FXML
    public TextField textField;

    @FXML
    public ListView<String> docOp = new ListView<String>();
    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    private static ObjectRepository<Operations> operationsObjectRepository = OperationService.getOperationRepository();

    public void addOp() throws Exception{
        try{
            OperationService.addOp(loggedUser.getActualUser(),textField.getText());
            updateListView();
        }
        catch (EmptyTextException e){
        }
        catch(OperationExistsException e){
        }
    }

    public void deleteOp() throws Exception{
        if (docOp.getSelectionModel().getSelectedItem()!=null){
            OperationService.deleteOp(loggedUser.getActualUser(),docOp.getSelectionModel().getSelectedItem().toString());
        }
        updateListView();
    }



    public void backAction(javafx.event.ActionEvent event) throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("doctor.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root1, 1000, 667));
        window.show();
    }


    public void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Operations op : operationsObjectRepository.find()) {
            if (op.getDoctorname().equals(loggedUser.getActualUser())) {
                items.add(op.getOperation());
                docOp.setItems(items);
            }
        }
    }
}
