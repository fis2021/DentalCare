package org.loose.fis.den.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.model.Operations;
import org.loose.fis.den.model.User;
import org.loose.fis.den.model.loggedUser;
import org.loose.fis.den.services.OperationService;
import org.loose.fis.den.services.UserService;


import java.io.IOException;

public class DoctorController {
    @FXML
    private Text fullName;
    @FXML
    private Text email;
    @FXML
    private Text phoneNumber;

    @FXML
    public ListView<String> opList = new ListView<String>();

    private static ObjectRepository<Operations> operationsObjectRepository = OperationService.getOperationRepository();
    private static ObjectRepository<User> userRepository = UserService.getUserRepository();

    @FXML
    public void initialize() throws IOException {
        for(User doc : userRepository.find()){
            if(loggedUser.getActualUser().equals(doc.getUsername())){
                fullName.setText(doc.getName());
                email.setText(doc.getEmail());
                phoneNumber.setText(doc.getPhoneNumber());
            }
        }
        updateListView();
    }

    public void logoutAction(javafx.event.ActionEvent logout) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage)((Node)logout.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

    public void appointmentViewAction(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("doc-appointments.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

    public void operationEditor(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("lista.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

    public void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (User doc : userRepository.find()) {
            if (doc.getUsername().equals(loggedUser.getActualUser())) {
                for(Operations op : operationsObjectRepository.find()){
                    if(op.getDoctorname().equals(doc.getName())){
                        items.add(op.getOperation());
                        opList.setItems(items);
                    }
                }
            }
        }
    }

}
