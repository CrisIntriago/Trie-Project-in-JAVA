/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;
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
    

    public boolean insert(String palabra, int indice){ //metodo q requiere insertar pero poneido indice 0, que se se llama por recursividad
        boolean validacion=false;
        for(int i=indice;i< palabra.length();i++){
            char letra=palabra.charAt(i);
                 if(this.getRoot().isNuevaLetra(letra)){
           validacion= this.getRoot().insertarSubArbol(letra);   
            }else{
               this.getRoot().buscarSubArbol(letra).insert(palabra,i+1);
            
        }
        }
       return validacion; 
    }public TrieNode getRoot(){
        return root;
    }

    public boolean busquedaPalabra(String palabra) {
   
        Trie arbolSiguiente = this;

        for (int i = 0; i < palabra.length(); i++) {
            if (arbolSiguiente != null) {
                arbolSiguiente = arbolSiguiente.root.buscarSubArbol(palabra.charAt(i));
            }
        }
        if (arbolSiguiente != null) {
            if (arbolSiguiente.root.buscarSubArbol(palabra.charAt(0)) != null) {
                return true;
            }
        }
        return false;

    }
}
 

