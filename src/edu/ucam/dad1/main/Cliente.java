package edu.ucam.dad1.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public void ejecutar()
	{
		try {		
					
			System.out.println("Lanzando conexi�n...");
			Socket socket = new Socket("127.0.0.1", 5000);
			System.out.println("Conexi�n establecida... " + socket.getRemoteSocketAddress());
									
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
			Scanner keyboard = new Scanner(System.in);
			
			String line = "";
			
			while(!(line=keyboard.nextLine()).equalsIgnoreCase("QUIT")) {
				pw.println(line);
				pw.flush();
				
				System.out.println("\t" + br.readLine());
			}	
			
			pw.println(line);
			pw.flush();
			
			System.out.println("Finalizado");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		(new Cliente()).ejecutar();
		

	}

}
