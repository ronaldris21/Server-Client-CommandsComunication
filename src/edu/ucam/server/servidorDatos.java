package edu.ucam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorDatos extends Thread{
	public void run()
	{
		while(true)
		{
			///Recibir Multiples clientes de datos a la vez!!!
			
			try 
			{
				ServerSocket serverSocket =  new ServerSocket(5001);
				System.out.println("Esperando conexion Datos");
				Socket socket;
				socket = serverSocket.accept();
				System.out.println("Conexion recibida datos" + socket.getRemoteSocketAddress());
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				
				HiloServidorCanalDatos 	hilo = new HiloServidorCanalDatos(socket,br,pw);
				hilo.start(); 
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
