package Controlador;

import Modelo.ManejadorArchivo;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    private Trie diccionario = new Trie();

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCargar;

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

    @FXML
    private Label notificaciones;

    private ListView<String> listaSugerencias = new ListView<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        texto.clear();
    }

    @FXML
    void buscarEnDic(ActionEvent ae) {
        System.out.println("Se buscó la palabra: " + texto.getText());
        notificaciones.setText(diccionario.buscarPalabra(texto.getText()));
    }

    @FXML
    void eliminarDeDic(ActionEvent ae) {
        boolean resultadoEliminar = diccionario.eliminarPalabra(texto.getText());
        if (resultadoEliminar) {
            notificaciones.setText(("Se eliminó correctamente la palabra " + texto.getText()));
            texto.clear();
        } else {
            System.out.println("Esa palabra no está en el diccionario");
            notificaciones.setText("Esa palabra no está en el diccionario");
        }

    }

    @FXML
    void cargarDiccionario(ActionEvent ae) {
        ManejadorArchivo.cargarDiccionario(diccionario);
        
        texto.clear();
        ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();
        diccionario.CompletarPalabras(texto.getText(), filteredSuggestions);
        listaSugerencias.setItems(filteredSuggestions);


    }

    @FXML
    void guardarDiccionario(ActionEvent ae) {
        texto.clear();
        ObservableList<String> guardables = FXCollections.observableArrayList();
        diccionario.CompletarPalabras(texto.getText(), guardables);
        for (String palabra : guardables) {
            ManejadorArchivo.escribir(palabra);
            System.out.println("Se guardaró correctamente " + palabra);
        }

    }

}
