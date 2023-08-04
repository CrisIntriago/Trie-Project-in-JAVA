/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class TrieNode<E> {

    private HashMap<String, Trie> MapaSubArboles;
    private boolean isPalabra;

    public TrieNode() {
        this.MapaSubArboles = new HashMap<String, Trie>();
    }

    public Trie insertarSubArbol(String clave) {
        Trie subArbol = null;

        if (isNuevaLetra(clave)) {
            subArbol = añadirSubArbol(clave);
        } else {
            subArbol = MapaSubArboles.get(clave);
        }
        return subArbol;
    }


    public boolean isNuevaLetra(String clave) {
        return !MapaSubArboles.containsKey(clave);
    }

    public boolean isPalabra() {
        return isPalabra;
    }

    public void confirmarPalabra() {
        this.isPalabra = true;
    }


    public Trie añadirSubArbol(String key) {
        Trie nuevoArbol = new Trie();
        MapaSubArboles.put(key, nuevoArbol);
        return nuevoArbol;
    }

    public Trie buscarSubArbol(String clave) {
        if (isNuevaLetra(clave)) {
            return null;
        } else {
            return MapaSubArboles.get(clave);
        }
    }

}
