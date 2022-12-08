package edu.ucam.clients;


import java.io.BufferedReader;
import java.net.Socket;

public class HiloCliente extends Thread {

	private BufferedReader br;
	private Cliente cliente;

	public HiloCliente(Cliente cliente, BufferedReader br) {
		this.cliente = cliente;
		this.br =  br;
	}

	public void run()
	{
		while(cliente.isActive())
		{
			try {
				String lineaRespuesta = br.readLine();
				System.out.println(lineaRespuesta);
				
				String[] palabras = lineaRespuesta.split(" ");	
				if(palabras.length >= 5 && palabras[0] == "PREOK" ) 
				{
					///LANZO nuevo HILO COMANDOS!
				}
			} catch (Exception e) {
			}
		}
	}

}
