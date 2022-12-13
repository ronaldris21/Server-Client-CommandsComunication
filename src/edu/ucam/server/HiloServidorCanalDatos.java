package edu.ucam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloServidorCanalDatos extends Thread {

	private HiloServidorComandos hiloComandos;
	private int PUERTO;
	private Object obj;

	public HiloServidorCanalDatos(HiloServidorComandos hiloComandos, int puerto, Object obj) {
		this.hiloComandos =   hiloComandos;
		this.PUERTO = puerto;
		this.obj = obj;
	}

	
	
	public void run()
	{
		
		try 
		{
			ServerSocket serverSocket =  new ServerSocket(PUERTO);
			System.out.println("Esperando conexion Datos");
			Socket socket = serverSocket.accept();
			System.out.println("Conexion recibida datos" + socket.getRemoteSocketAddress());
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
