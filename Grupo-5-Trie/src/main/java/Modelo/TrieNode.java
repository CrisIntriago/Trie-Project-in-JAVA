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
    private String palabraCompleta;

    public TrieNode() {
        this.MapaSubArboles = new HashMap<String, Trie>();
    }

    public Trie getSubArbol(String clave) {

        Trie subArbol = null;
        if (isNuevaLetra(clave)) {
            subArbol = new Trie();
            MapaSubArboles.put(clave, subArbol);
        } else {
            subArbol = MapaSubArboles.get(clave);
        }
        return subArbol;
    }

    public boolean isLeaf() {
        return MapaSubArboles.isEmpty();
    }

    public boolean isNuevaLetra(String clave) {
        return !MapaSubArboles.containsKey(clave);
    }

    public boolean isPalabra() {
        return isPalabra;
    }

    public String getPalabraCompleta() {
        return palabraCompleta;
    }

    public void confirmoPalabra() {
        this.isPalabra = true;
    }
    
    public void setPalabraCompleta(String palabra){
        this.palabraCompleta=  palabra;
    }
    
    public Trie añadirSubArbol(String key){
        Trie nuevoArbol= new Trie();
        MapaSubArboles.put(key, nuevoArbol);
        return nuevoArbol;
    }
    
}
