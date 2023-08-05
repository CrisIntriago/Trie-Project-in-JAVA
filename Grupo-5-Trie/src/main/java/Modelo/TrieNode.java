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

    private HashMap<Character, Trie> MapaSubArboles;
    private boolean isPalabra;

    public TrieNode() {
        this.MapaSubArboles = new HashMap<Character, Trie>();
    }
    public HashMap<Character, Trie >getMapaSubArboles(){
        return MapaSubArboles;
        
    }

    public boolean insertarSubArbol(Character clave) { // ahopra solo  lo inserta, para dar el boleano
        
        if(clave!=null){
             CrearSubArbol(clave);
            return true;
      
        }
        return false;
       
    }


    public boolean isNuevaLetra(Character clave) {
        return !MapaSubArboles.containsKey(clave);
    }

    public boolean isPalabra() {
        return isPalabra;
    }


    public Trie CrearSubArbol(char key) {
        Trie nuevoArbol = new Trie();
        MapaSubArboles.put(key, nuevoArbol);
        return nuevoArbol;
    }

    public Trie buscarSubArbol(Character clave) {
        if (isNuevaLetra(clave)) {
            return null;
        } else {
            return MapaSubArboles.get(clave);
        }
    
    }

}
