/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversor_de_monedas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sdes
 */
public class Moneda {

    private String name;
    private double precio;
    private double cantidad;
    private double resultado;
    private int op;
    private Map<String, Double> conversiones;

    public Moneda(String name,int op, double precio, double cantidad, Map<String, Double> conversiones) {
        this.name = name;
        this.op = op;
        this.precio = precio;
        this.cantidad = cantidad;
        this.conversiones = conversiones;
        
    }

    
    
    public Object[] getValueAtIndex(int index) {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(conversiones.entrySet());
        
        // Verificar que el índice esté dentro de los límites
        if (index >= 0 && index < entries.size()) {
            
            return  new Object[]{entries.get(index).getKey(), entries.get(index).getValue()};
            
        }
        return null; // O lanza una excepción si el índice no es válido
        
    }
    public void setConversiones(Map<String, Double> conversiones) {
        this.conversiones = conversiones;
    }

    public Map<String, Double> getConversionRates() {
        return conversiones;
    }
    
    public double US(){
        resultado = (precio*cantidad);
        return resultado;
    }
     public double Other(){
        resultado = (cantidad/precio);
        return resultado;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
   @Override
    public String toString() {
        
        if(op==1){
        return "Conversion de " 
                + "US a "+name
                + "\n"
                +"US::"+cantidad+" "+name+
                "::"+US();
        }
         return "Conversion de " 
                +name+ " a US"
                + "\n"
                +name+"::"+cantidad+" "
                +"US::"+Other();
        }
}
