package edu.ucam.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import edu.ucam.domain.CodigosRespuesta;

public class Cliente {

	
	private Boolean isActive = true;
	private Socket socket;
	private String comando;
	
	public String getComando()
	{
		return this.comando;
	}
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public synchronized Boolean isActive()
	{
		return isActive;
	}
	
	public synchronized void SetisActive(Boolean status)
	{
		isActive = status;
	}
	
	
	
	
	
	
	public void ejecutar()
	{
		try {
			this.socket =  new Socket("localhost",5000);
			
			PrintWriter pw =  new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			///Pongo un hilo a escuchar!
			HiloCliente hiloEscuchar =  new HiloCliente(this, br);
			hiloEscuchar.start();
			
			
			///Cliente se queda escribiendo
			Scanner teclado =  new Scanner(System.in);
			
			while(true)
			{
				if(!isActive())
					return;
				try {
					comando =  teclado.nextLine();
					pw.println(comando);
					pw.flush();	
					
					String[] palabras = comando.split(" ");
					if(palabras.length>2 && palabras[1].equals("EXIT"))
						this.SetisActive(false);
					
					
				} catch (Exception e) {} //En caso que el servidor cierre el socket!
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		(new Cliente()).ejecutar();
		

	}

}
