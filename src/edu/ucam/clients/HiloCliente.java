package edu.ucam.clients;


import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import edu.ucam.domain.Club;

public class HiloCliente extends Thread {

	private BufferedReader br;
	private Cliente cliente;

	public HiloCliente(Cliente cliente, BufferedReader br) {
		this.cliente = cliente;
		this.br =  br;
	}

	public void run()
	{
		while(true)
		{
			try 
			{
				if (!cliente.isActive())
				{
					this.cliente.getSocket().close();
					return;
				}
				
				
				String lineaRespuesta = br.readLine();
				if(lineaRespuesta!=null)
					System.out.println(lineaRespuesta);
				
				String[] palabras = lineaRespuesta.split(" ");	
				if(palabras.length >= 5 && palabras[0] == "PREOK" ) 
				{
					String host = palabras[3];
					int puerto = Integer.parseInt(palabras[4]); 
					Socket socketDatos =  new Socket(lineaRespuesta, puerto);
					
				}
			} catch (Exception e) {
			}
		}
		
	}

}
