package edu.ucam.dad1.main;

import java.util.ArrayList;
import edu.ucam.dad1.models.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Club> clubes;
	
	public void ejecutar()
	{
		try {
			ServerSocket serverSocket =  new ServerSocket(5000);
			System.out.println("Esperando conexxion");
			Socket socket = serverSocket.accept();
			System.out.println("Conexion recibida" + socket.getRemoteSocketAddress());
			
			
			
			
		}catch(Exception e)
		{
			
		}
		
		
	}
	

	public Servidor() {
		usuarios =  new ArrayList<>();
		clubes =  new ArrayList<>();

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
	}

}
