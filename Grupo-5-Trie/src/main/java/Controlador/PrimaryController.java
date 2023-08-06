package Controlador;

import Modelo.Trie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    private Trie diccionario = new Trie();

    @FXML
    private VBox root;

    @FXML
    private TextField texto;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    private ObservableList<String> sugerencias = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ListView<String> listaSugerencias = new ListView<>();
        listaSugerencias.setMaxHeight(100);
        texto.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

            diccionario.CompletarPalabras(searchText, filteredSuggestions);

            listaSugerencias.setItems(filteredSuggestions);
        });

        root.getChildren().add(listaSugerencias);
    }

    @FXML
    void insertarDic(ActionEvent ae) {
        System.out.println("Se ingresó la palabra: " + texto.getText().toLowerCase() + " " + diccionario.insert(texto.getText().toLowerCase(), 0));
    }

}
