/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import javafx.collections.ObservableList;

/**
 *
 * @author Administrador
 */
public class Trie <E> {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean insertarPalabra(String palabra, int indice) {
        if (!palabra.isBlank()) {
            insert(palabra, 0);
            return true;
        }
        System.out.println("No puede insertar una palabra invisible.");
        return false;
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
        boolean resultado = busquedaPalabra(palabra);
        if (resultado == true) {
            return "La palabra " + palabra + " está en el diccionario";
        }
        return "La palabra " + palabra + " no está en el diccionario";

    }

    public boolean busquedaPalabra(String palabra) {
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
    private LinkedList<String> ObtenerPalabras(){//n se crea este metodo apra obtener todas las palabras del arbol que se invoca (preferible de todo el arbol
        LinkedList<String> palabras= new LinkedList<>();
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
                palabras.add(palabra);
            }    
     } 
        return palabras;
         }
    public boolean eliminarPalabra(String palabra) {
        //Caso base
        if (palabra.length() == 0) {
            if (!this.getRoot().tienePalabra()) {
                return false;
            } else {
                this.getRoot().setContent(null);
                return true;
            }//Caso recursivo
        } else {
            String primera = palabra.substring(0, 1);
            String sobrante = palabra.substring(1);
            Trie subArbol = obtenerSubArbol(primera);
            if (subArbol == null) {
                return false;
            } else {
                boolean bool = subArbol.eliminarPalabra(sobrante);
                if (!subArbol.getRoot().tienePalabra() && subArbol.isLeaf()) {
                    subArbol.getRoot().setContent(null);

                }
                return bool;
            }

        }
    }

    public boolean isLeaf() {
        return this.getRoot().getMapaSubArboles().isEmpty();
    }
    
    //Averiguar si al eliminar asi una referencia se borran todas las referencias hijas y se vuelven null o queedan en memoria siendo inutiles
    public boolean clear(){
        this.root= new TrieNode();
        return true;
    }
    private int getDistancia(String palabra1, String palabra2){
        int m = palabra1.length();
        int n = palabra2.length();
 
        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j < n; j++) {
            T[0][j] = j;
        }
 
        int cost;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cost = palabra2.charAt(i - 1) == palabra2.charAt(j) ? 0: 1;
                T[i][j] = Integer.min(Integer.min(T[i ][j], T[i][j ] ),
                        T[i ][j ] + cost);
            }
        }
        return T[m][n];
    }
    public Stack<PalabraComparacion>Similitud(String palabra){
        Stack<PalabraComparacion> pila =new Stack<>();
        for(String palabras : this.ObtenerPalabras()){
             double maxLength = Double.max(palabra.length(), palabras.length());
        if (maxLength > 0) {
            // opcionalmente ignora el caso si es necesario
            double similitud=(maxLength - getDistancia(palabra, palabras)) / maxLength;
            PalabraComparacion pc= new PalabraComparacion(palabras,similitud);
            pila.add(pc);     
        } 
            }
                
      return pila;  
       
    }
    public List<String> palabras_terminacion(String palabra){//se coge las ultimas 2 letras para comparar de cada palabra
       LinkedList<String> list= this.ObtenerPalabras();
        LinkedList<String> lista= new  LinkedList<> ();
       String terminacion= "";
       terminacion+=palabra.charAt(palabra.length()-2);
       terminacion+=palabra.charAt(palabra.length()-1);
       for( String p: list){
           String terminacionP="";
       terminacionP+=p.charAt(p.length()-2);
       terminacionP+=p.charAt(p.length()-1);
          if(terminacionP.contains(terminacion)){
              lista.add(p);
              
          }
           
       }
       return lista;
         
    }
    
        
    }


