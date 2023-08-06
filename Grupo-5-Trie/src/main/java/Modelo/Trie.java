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

    public boolean busquedaPalabra(String palabra,int indice) {

        boolean Validacion = false;
        
        char Letra = palabra.charAt(indice);
        
        if (this.getRoot().isNuevaLetra(Letra)) {
            return false;
        }
        
        if (!this.getRoot().isNuevaLetra(Letra)) {
            Trie SubArbol = this.getRoot().buscarSubArbol(Letra);
            Validacion = SubArbol.insert(palabra, indice + 1);
        }

        return Validacion;
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
    public void CompletarPalabras(String palabraIncompleta, LinkedList<String> listaActualizar) {
        
        listaActualizar.clear();
        
        if (obtenerSubArbol(palabraIncompleta) != null) {
            obtenerSubArbol(palabraIncompleta).completarPalabrasDesdeSubArbol(listaActualizar);
        }else{
            System.out.println("No hay palabras a recomendar");
        }

    }
    
    public void completarPalabrasDesdeSubArbol(LinkedList<String> listaActualizar) {

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
