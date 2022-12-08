package edu.ucam.server;

import java.util.ArrayList;

import edu.ucam.clients.*;
import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ArrayList<HiloServidorComandos> hilosClientes;
	
	
	
	
	


	public Servidor() {
		this.hilosClientes =  new ArrayList<HiloServidorComandos>();
		
		
		
		
		///TODO CREATE INIT DATA 
	}
	
	
	public void eliminarCliente(HiloServidorComandos cliente)
	{
		///EXIT
		this.hilosClientes.remove(cliente);
	}

	
	
	public void ejecutar()
	{
		while(true)
		{
				
			try {
				///Recibiendo clientes
				ServerSocket serverSocket =  new ServerSocket(5000);
				System.out.println("Esperando conexion commandos");
				Socket socket = serverSocket.accept();
				System.out.println("Conexion recibida commandos" + socket.getRemoteSocketAddress());
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				///Recibir Multiples clientes a la vez!!!
				HiloServidorComandos 	hilo = new HiloServidorComandos(this,socket,br,pw);
				hilo.start();
				hilosClientes.add(hilo);
				
			}catch(Exception e)
			{
				
			}
			
		}
		
		
	}
	
	
	////GETTERS AND SETTERS
	public ArrayList<HiloServidorComandos> getHilosClientes() {
		return hilosClientes;
	}


	public void setHilosClientes(ArrayList<HiloServidorComandos> hilosClientes) {
		this.hilosClientes = hilosClientes;
	}
	
	
	


	public static void main(String[] args) {
		///Inicio el ServerSocket de datos
		(new servidorDatos()).start();
		
		//Inicio el serverSocket de commandos
		(new Servidor()).ejecutar();
		
	}

}
