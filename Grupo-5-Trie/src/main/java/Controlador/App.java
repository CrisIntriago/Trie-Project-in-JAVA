package Controlador;

import Modelo.Trie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import Modelo.Trie;

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
        Trie arbol=new Trie();
        System.out.println("Comprobar si se insertó la palabra hola");
        System.out.println("Insercion: "+arbol.insert("Hola",0));
        System.out.println("Comprobar si se encuentra la palabra hola");
        System.out.println("busqueda: "+ arbol.busquedaPalabra("Holadfasdfsadfs",0));

        
        
    }

}