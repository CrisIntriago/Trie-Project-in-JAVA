/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Ismael
 */
public  class PalabraComparacion {
    private String palabra;
    private Double Porcentaje;

    public PalabraComparacion(String palabra, Double Porcentaje) {
        this.palabra = palabra;
        this.Porcentaje = Porcentaje;
    }

    
    
    public String getPalabra() {
        return palabra;
    }

    public Double getPorcentaje() {
        return Porcentaje;
    }

    
    
}
