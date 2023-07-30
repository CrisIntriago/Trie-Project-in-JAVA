package com.mycompany.grupo.trie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PrimaryController implements Initializable  {
    
    
    @FXML
    private VBox root; 
    
    @FXML
    private TextField texto;
    
    private ObservableList<String> sugerencias = FXCollections.observableArrayList("Cristian", "Raul", "Ismael");
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       ListView<String> suggestionListView = new ListView<>();
        suggestionListView.setMaxHeight(100);
        texto.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

            for (String suggestion : sugerencias) {
                if (suggestion.toLowerCase().startsWith(searchText)) {
                    filteredSuggestions.add(suggestion);
                }
            }

            suggestionListView.setItems(filteredSuggestions);
        });
        
        
        root.getChildren().add(suggestionListView);
    }
    
}
