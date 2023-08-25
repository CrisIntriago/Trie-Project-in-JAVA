/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ManejadorArchivo;
import Modelo.Trie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Administrador
 */
public class JuegoController implements Initializable {

    private int contador;

    @FXML
    private VBox root2;

    @FXML
    private Trie diccionario2 = new Trie();

    @FXML
    private ListView<String> listaSugerencias2;

    @FXML
    private TextField texto2;

    @FXML
    private TextField historia;

    @FXML
    private Label txtInfo;

    private ArrayList<String> listaPrefijos = ManejadorArchivo.prefijosJuego;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        texto2.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

            diccionario2.CompletarPalabras(searchText, filteredSuggestions);

            listaSugerencias2.setItems(filteredSuggestions);
        });

        listaSugerencias2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String palabraEscogida = listaSugerencias2.getSelectionModel().getSelectedItem();
                if (palabraEscogida != null) {
                    historia.setText(historia.getText() + " " + palabraEscogida);
                }
            }
        });

        ManejadorArchivo.cargarDiccionarioJuego(diccionario2);
        ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();
        diccionario2.CompletarPalabras(texto2.getText(), filteredSuggestions);
        listaSugerencias2.getItems().clear();
        listaSugerencias2.setItems(filteredSuggestions);

        actualizarAutomaticamente();

    }

    @FXML
    public void regresar() throws IOException {
        System.out.println("Se clickeó en regresar");
        App.setRoot("/Vistas/primary");

    }

    public void actualizarAutomaticamente() {

        Random rd = new Random();
        Thread updateThread = new Thread(() -> {

            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            while (contador < 20) {
                try {
                    Thread.sleep(3000); // Sleep for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;

                // Update the label text on the JavaFX Application Thread
                String prefijoAleatorio = listaPrefijos.get(rd.nextInt(listaPrefijos.size()));
                Platform.runLater(() -> txtInfo.setText("El prefijo actual es: " + prefijoAleatorio));
                Platform.runLater(() -> texto2.setText(prefijoAleatorio));

            }
        });
        updateThread.setDaemon(true); // Set the thread as daemon (won't prevent the application from exiting)
        updateThread.start();

    }

    @FXML
    public void obtenerString(ActionEvent ae) {

    }
}
