/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conversor_de_monedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Sdes
 */
public class Conversor_de_monedas {

    public static void main(String[] args) {
       String urlApi="https://v6.exchangerate-api.com/" ;
       String APIKEY="APIKEY"; //ingresa tu apikey
       //link para generar apikey: https://www.exchangerate-api.com/
       String URL=""+urlApi+"v6/"+APIKEY+"/latest/USD";
       Scanner scanner = new Scanner(System.in);
       double cantidad;
       int opcion;
       int tipo;
       Map<String, Double> rates = null;
       
       ConversionOmdb conversion;
       Gson gson = new Gson();
       
       HttpClient client = HttpClient.newHttpClient();
         HttpResponse<String> response;
        try {
           HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
           
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            conversion = gson.fromJson(response.body(), ConversionOmdb.class); 
            rates = conversion.conversiones();
            
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
          
        do {
             int numero = 1;
            System.out.println("----------------Menu--------------------------");
            System.out.println("1- US a otra moneda");
            System.out.println("2- Otra moneda a US");
            System.out.println("0- Para Salir del programa");
            System.out.println("ingrese la opcion::");
            tipo=scanner.nextInt();
            if(tipo==0) break;
            System.out.println("Ingresa la cantidad a convertir::");
            cantidad = scanner.nextDouble();
            System.out.println("---------Opciones de monedas disponibles---------"); 
            try{
                for (Map.Entry<String, Double> entry : rates.entrySet()) {
                    System.out.println(numero + "::" + entry.getKey() + ": " + entry.getValue());
                    numero++;
                }

                System.out.print("Ingresa el numero de la opcion de la moneda que desea convertir :: ");
                opcion = scanner.nextInt();
                --opcion;
                Moneda moneda = new Moneda(null,tipo,0, cantidad, rates);
                Object[] valor = moneda.getValueAtIndex(opcion);
                if(null== valor)
                     System.err.println("Esa opcion no esta disponible"+ "opcion::"+opcion);
                else{
                    moneda.setName((String) valor[0]);
                    moneda.setPrecio((Double)valor[1] );
                    System.out.println("");
                    System.out.println("----------------------------------------------");
                    System.out.println(moneda.toString() );
                 }
            }catch(Exception e){System.out.println("No hay opciones disponibles, verifique que la ruta de la api sea correcta, tambien verifique que la apikey sea correcta ");}
        }while(true);
    }
}
