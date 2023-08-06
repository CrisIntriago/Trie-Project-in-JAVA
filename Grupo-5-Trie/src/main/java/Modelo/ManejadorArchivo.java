/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Administrador
 */
public class ManejadorArchivo {

    private static final String ubicacion = "src/main/resources/palabrasDiccionario.txt";

    
    //ESTOS 2 MÉTODOS NO SE USAN, ESCRIBIR Y ELIMINAR PALABRA, LEYENDO EL PDF dice que solo se guarda y borra al dar click en el boton
    
    public static void escribir(String palabra) {
        try {
            FileWriter fileWriter = new FileWriter(ManejadorArchivo.ubicacion, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(palabra);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void eliminarPalabra(String palabra) {
        try (BufferedReader br = new BufferedReader(new FileReader(ManejadorArchivo.ubicacion))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(palabra)) {
                    lines.add(line);
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ubicacion))) {
                for (String updatedLine : lines) {
                    bw.write(updatedLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            // Handle any potential IOException
            e.printStackTrace();
        }

    }

    public static void cargarDiccionario(Trie dic) {
        try (BufferedReader br = new BufferedReader(new FileReader(ManejadorArchivo.ubicacion))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                dic.insert(line, 0);
            }
        } catch (IOException e) {
            // Handle any potential IOException
            e.printStackTrace();
        }

    }

    public static void guardarDiccionario(ObservableList<String> guardables) {
        try {
            FileWriter fileWriter = new FileWriter(ManejadorArchivo.ubicacion, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String palabra : guardables) {
                bufferedWriter.write(palabra);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
