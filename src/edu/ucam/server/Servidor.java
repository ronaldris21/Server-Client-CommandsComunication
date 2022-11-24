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

	private ArrayList<String> comandos;
	
	
	public Servidor() {
		this.comandos = new ArrayList<String>();
		comandos.add("USER");
		comandos.add("PASS");
		comandos.add("EXIT");
		comandos.add("SESIONES");
		comandos.add("USER");
	}

	
	
	public void ejecutar()
	{
		try {
			ServerSocket serverSocket =  new ServerSocket(5000);
			System.out.println("Esperando conexion");
			Socket socket = serverSocket.accept();
			System.out.println("Conexion recibida" + socket.getRemoteSocketAddress());
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
			
			String lineaLeida = "";
			lineaLeida=br.readLine();
			String[] palabras = lineaLeida.split("");
			Boolean salir= false;
			
			
			try
			{
				if(palabras.length>3 && (palabras[2]=="admin" || palabras[1] == "USER")) ///USUARIO
				{
					
					pw.println("OK "+ palabras[0] + " "+ CodigosRespuesta.Ok + " Envie contraseña");
					pw.flush();
					
					
					////Comprobar contraseña

					Boolean escorrectoOSalir = false;
					while(!escorrectoOSalir) {
						
						lineaLeida = br.readLine();
						palabras = lineaLeida.split(" ");
						//5 PASS admin
						if(palabras.length>3 && palabras[1] == "PASS" && palabras[2]=="admin" )//CONTRASEÑA
						{
							pw.println("OK "+ palabras[0] + " "+ CodigosRespuesta.Ok + " Welcome  "+ palabras[2]);
							pw.flush();
							escorrectoOSalir = true;
						}
						else 
						{
							pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Prueba de nuevo "+ palabras[2]);
							pw.flush();
						}
						
						if(palabras.length>2 && palabras[1] == "EXIT" )
							escorrectoOSalir = true;
					}
					
					
				}
				else
				{
					pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Not user.");
					pw.flush();
					
				}
			}
			catch(Exception e){
				pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Not user.");
				pw.flush();
			}
			
			
			///return
			
			
			
			while(!(lineaLeida=br.readLine()).equalsIgnoreCase("QUIT")) {
				System.out.println("Line received in server... " + lineaLeida);
				pw.println(lineaLeida);
				pw.flush();
			}			
			
			System.out.println("Finalizado");
			
			
			
		}catch(Exception e)
		{
			
		}
		
		
		
	}
	


	public static void main(String[] args) {
		Servidor server =  new Servidor();
		server.ejecutar();
		
	}

}
