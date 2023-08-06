package Controlador;

import Modelo.Trie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import Modelo.Trie;
import java.util.LinkedList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/Vistas/primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        Trie diccionario = new Trie();

        System.out.println(diccionario.insert("Cristiancito", 0));
        System.out.println(diccionario.busquedaPalabra("Cristiancito", 0));

        System.out.println(diccionario.insert("Cristiancitop", 0));
        System.out.println(diccionario.busquedaPalabra("Cristiancitop", 0));

        LinkedList<String> recomendaciones = new LinkedList();
        diccionario.CompletarPalabras("Cr", recomendaciones);
        System.out.println(recomendaciones);
        System.out.println(diccionario.insert("Crack", 0));
        System.out.println(diccionario.insert("Covid", 0));
        diccionario.CompletarPalabras("Co", recomendaciones);
        System.out.println(recomendaciones);

        diccionario.CompletarPalabras("C", recomendaciones);
        System.out.println(recomendaciones);
        
        diccionario.CompletarPalabras("CovidIsnooooorial", recomendaciones);
        System.out.println(recomendaciones);

    }
        
    }

