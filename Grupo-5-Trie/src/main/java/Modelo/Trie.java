/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import javafx.collections.ObservableList;

/**
 *
 * @author Administrador
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean insert(String palabra, int indice) { //metodo q requiere insertar pero poneido indice 0, que se se llama por recursividad

        boolean Validacion = false;

        if (indice == palabra.length()) {
            this.getRoot().setContent(palabra);
            return true;
        }
        char Letra = palabra.charAt(indice);
        if (this.getRoot().isNuevaLetra(Letra)) {
            this.getRoot().insertarSubArbol(Letra);

        }
        if (!this.getRoot().isNuevaLetra(Letra)) {
            Trie SubArbol = this.getRoot().buscarSubArbol(Letra);
            Validacion = SubArbol.insert(palabra, indice + 1);
        }

        return Validacion;

    }

    public TrieNode getRoot() {
        return root;
    }

    public String buscarPalabra(String palabra) {
        boolean resultado = busquedaPalabra(palabra, 0);
        if (resultado == true) {
            return "La palabra " + palabra + " está en el diccionario";
        }
        return "La palabra " + palabra + " no está en el diccionario";

    }

    public boolean busquedaPalabra(String palabra, int indice) {
        Trie subArbolBuscado = obtenerSubArbol(palabra);
        if (subArbolBuscado != null) {
            if (subArbolBuscado.getRoot().tienePalabra()) {
                return true;
            }
        }
        return false;

    }

    public Trie obtenerSubArbol(String palabra) {

        char[] letras = palabra.toCharArray();

        int contador = 0;
        Trie arbolActual = this;
        Trie arbolRetornable = null;

        while (arbolActual != null) {
            if (contador == palabra.length()) {
                arbolRetornable = arbolActual;
                return arbolRetornable;
            }
            arbolActual = arbolActual.getRoot().buscarSubArbol(letras[contador]);
            contador++;
        }

        return arbolRetornable;

    }

    public void CompletarPalabras(String palabraIncompleta, ObservableList<String> listaActualizar) {

        listaActualizar.clear();

        if (obtenerSubArbol(palabraIncompleta) != null) {
            obtenerSubArbol(palabraIncompleta).completarPalabrasDesdeSubArbol(listaActualizar);
        } else {
            System.out.println("No hay palabras a recomendar");
        }

    }

    public void completarPalabrasDesdeSubArbol(ObservableList<String> listaActualizar) {

        Queue<Trie> cola = new LinkedList();

        cola.offer(this);

        while (!cola.isEmpty()) {
            Trie arbolTemporal = cola.poll();
            Set<Character> keys = arbolTemporal.getRoot().getMapaSubArboles().keySet();
            for (Character key : keys) {
                cola.offer(arbolTemporal.getRoot().buscarSubArbol(key));
            }
            if (arbolTemporal.getRoot().tienePalabra()) {
                String palabra = (String) arbolTemporal.getRoot().getContent();
                listaActualizar.add(palabra);
            }

        }

    }
}
