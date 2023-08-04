/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.LinkedList;

/**
 *
 * @author Administrador
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String palabra) {
        String[] letras = palabra.split("");
        
        Trie arbolSiguiente = this;

        for (int i = 0; i < letras.length; i++) {
            arbolSiguiente = this.root.insertarSubArbol(letras[i]);
        }

        Trie arbolPalabra = arbolSiguiente.root.añadirSubArbol(palabra);
        arbolPalabra.root.confirmarPalabra();

    }

    public boolean busquedaPalabra(String palabra) {
        String[] letras = palabra.split("");
        Trie arbolSiguiente = this;

        for (int i = 0; i < letras.length; i++) {
            if (arbolSiguiente != null) {
                arbolSiguiente = arbolSiguiente.root.buscarSubArbol(letras[i]);
            }
        }
        if (arbolSiguiente != null) {
            if (arbolSiguiente.root.buscarSubArbol(palabra) != null) {
                return true;
            }
        }
        return false;

    }
 

