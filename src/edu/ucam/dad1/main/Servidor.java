package edu.ucam.dad1.main;

import java.util.ArrayList;
import edu.ucam.domain.Club;
import edu.ucam.dad1.models.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ArrayList<Usuario> usuarios;
	//private ArrayList<Club> clubes;
	
	public void ejecutar()
	{
		try {
			ServerSocket serverSocket =  new ServerSocket(5000);
			System.out.println("Esperando conexxion");
			Socket socket = serverSocket.accept();
			System.out.println("Conexion recibida" + socket.getRemoteSocketAddress());
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
			
			String lineaLeida = "";
			
			lineaLeida=br.readLine();
			
			String[] palabras = lineaLeida.split("");
			if(palabras[2]=="admin") {
				pw.println("OK " + palabras[0] + CodigosRespuesta.Ok + "Envie contrasena");
				pw.flush();
			}
			while(!(lineaLeida=br.readLine()).equalsIgnoreCase("QUIT")) {
				System.out.println("Line received in server... " + lineaLeida);
				pw.println(lineaLeida);
				pw.flush();
			}			
			
			System.out.println("Finalizado");
			
			
			/*
			
			///Implemento protocolo de conexion 
			String comandoRecibido = "";
			comandoRecibido =  br.readLine();
			String[] palabrasComando = comandoRecibido.split(" ");
			pw.print("Se ha recibido el comando: "+ palabrasComando[0]);
			pw.flush();
			
			*/
			
			
			
			//serverSocket.close();
		}catch(Exception e)
		{
			
		}
		
		
		
	}
	

	public Servidor() {
		usuarios =  new ArrayList<>();
		//clubes =  new ArrayList<>();

		Usuario u1 =  new Usuario("admin", "admin");
		Usuario u2 =  new Usuario("Pablo", "Pablo");
		Usuario u3 =  new Usuario("Ronald", "Ronald");
		
		usuarios.add(u3);
		usuarios.add(u2);
		usuarios.add(u1);
		
	}

	public static void main(String[] args) {
		Servidor server =  new Servidor();
		server.ejecutar();
		
		Club part = new Club("1","Persona 5") ;
	}

}
