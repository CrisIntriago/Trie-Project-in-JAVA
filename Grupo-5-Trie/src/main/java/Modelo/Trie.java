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
    
    
    public Trie(){
          this.root= new TrieNode();
    }
    

    public void insert(String palabra){
        String[] palabraSeparada= palabra.split("");
        
        Trie arbolSiguiente= this;
        
        for(int i=0;i<palabraSeparada.length;i++){
            arbolSiguiente=this.root.getSubArbol(palabraSeparada[i]);
        }
        
        
        Trie arbolPalabra =arbolSiguiente.root.añadirSubArbol(palabra);
        arbolPalabra.root.confirmoPalabra();
        arbolPalabra.root.setPalabraCompleta(palabra);
        
    }
    
    public void busquedaPalabra(String palabra){
        String[] palabraSeparada= palabra.split("");
        Trie arbolSiguiente= this;
        for(int i=0;i<palabraSeparada.length;i++){
            arbolSiguiente=this.root.getSubArbol(palabraSeparada[i]);
        }
        Trie arbolPalabra =arbolSiguiente.root.añadirSubArbol(palabra);
        arbolPalabra.root.confirmoPalabra();
        arbolPalabra.root.setPalabraCompleta(palabra);
        
    }
    

    
}
