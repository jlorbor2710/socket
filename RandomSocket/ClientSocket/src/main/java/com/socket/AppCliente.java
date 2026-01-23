package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 * Hello world!
 *
 */
public class AppCliente 
{
	static final int  PORT = 7777;
	
    public static void main( String[] args )
    {
    	
        try {
        	//conectamos con el servidor
        	Socket socket = new Socket("172.21.76.54",PORT);
        	
        	//Para enviar datos al server
        	PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        	
        	//Para recibir respuestas del servidor
        	BufferedReader entradaSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	//Leer los datos introducidos por consola
        	BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in));
        	
        	System.out.println("<Cliente>Inserte un número: ");
        	//Leemos de consola y enviamos al server
        	salida.println(entradaConsola.readLine());
        	
        	String datoRec;
        	int intentos = 0; // Mejora
        	while((datoRec = entradaSocket.readLine()) != null){
        		intentos++;
                System.out.println("Intento #" + intentos + ": " + datoRec);
                
                // Mejora
                if(datoRec.toLowerCase().contains("adivinado") || datoRec.toLowerCase().contains("correcto")){
                    System.out.println("¡Felicidades! Número adivinado. Cerrando conexión...");
                    salida.close();
                    entradaSocket.close();
                    entradaConsola.close();
                    socket.close();
                    break;
                }
        		salida.println(entradaConsola.readLine());
        	}
        	
        	
        	
        }catch(UnknownHostException ex) {
        	
        } catch (IOException e) {
			
			e.printStackTrace();
		}
    }
}
