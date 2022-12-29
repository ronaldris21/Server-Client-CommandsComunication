package edu.ucam.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;
import edu.ucam.domain.Jugador;
import edu.ucam.domain.TipoRespuesta;
import edu.ucam.server.HiloServidorCanalDatos;

public class Cliente {

	
	///TODO: EXIT_ON_CLOSE         this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	
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
