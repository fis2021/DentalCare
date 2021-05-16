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
import org.loose.fis.den.model.User;
import org.loose.fis.den.model.selectedDoc;
import org.loose.fis.den.services.UserService;

import java.io.IOException;

public class DocListController {

    @FXML
    public ListView<String> docList = new ListView<String>();

    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    private static ObjectRepository<User> userRepository = UserService.getUserRepository();

    public void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (User doc : userRepository.find()) {
            if (doc.getRole().equals("Doctor")) {
                items.add(doc.getName());
                docList.setItems(items);
            }
        }
    }

    public void viewAction(javafx.event.ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("doc-profile.fxml"));

        if(docList.getSelectionModel().getSelectedItem()!=null) {
            selectedDoc.setDocName(docList.getSelectionModel().getSelectedItem().toString());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 1000, 667));
            window.show();
        }
    }

    public void backAction(javafx.event.ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Pacient.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root,1000,667));
        window.show();
    }

}