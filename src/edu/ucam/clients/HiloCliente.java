package edu.ucam.clients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucam.clients.frames.AnadirJugador;
import edu.ucam.clients.frames.ClubTableView;
import edu.ucam.clients.frames.JugadorTableView;
import edu.ucam.domain.*;

/**
 * <p>
*Es el hilo que esta escuchando eternamente las respuestas del servidor
*</p>
*/
public class HiloCliente extends Thread {

	private BufferedReader br;
	private Cliente cliente;

	public HiloCliente(Cliente cliente, BufferedReader br) {
		this.cliente = cliente;
		this.br =  br;
	}
	
	public Cliente getCliente()
	{
		return this.cliente;
	}
	

	/**
	 *  Escucha los comandos que le manden.
	 */
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
				String comandoActual = this.cliente.getComando();
				if(lineaRespuesta!=null)
					this.cliente.mostrarMensajeInterfazVisual(lineaRespuesta); ///Muestra mensaje por consola o en interfaz grafica si el programa tiene interfaz visual
				
				String[] palabras = lineaRespuesta.split(" ");	
				if(palabras.length >= 5 && palabras[0].equals("PREOK") ) 
				{
					String host = palabras[3];
					int puerto = Integer.parseInt(palabras[4]); 
					Socket socketDatos =  new Socket(host, puerto);
					(new HiloDatosCliente(comandoActual,socketDatos,this)).start();
				}
			} catch (Exception e) {
			}
		}
		
	}
}
