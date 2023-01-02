package edu.ucam.clients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucam.clients.frames.ClubTableView;
import edu.ucam.clients.frames.JugadorTableView;
import edu.ucam.domain.*;

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
				
				///TODO: use method to print on TextField when using GUI  too

				String lineaRespuesta = br.readLine();
				if(lineaRespuesta!=null)
					this.cliente.mostrarMensajeInterfazVisual(lineaRespuesta); ///Muestra mensaje por consola o en interfaz grafica si el programa tiene interfaz visual
				
				String[] palabras = lineaRespuesta.split(" ");	
				if(palabras.length >= 5 && palabras[0].equals("PREOK") ) 
				{
					String host = palabras[3];
					int puerto = Integer.parseInt(palabras[4]); 
					Socket socketDatos =  new Socket(host, puerto);
					this.manejarPREOK(this.cliente.getComando(), socketDatos);
				}
			} catch (Exception e) {
			}
		}
		
	}
	
	

	///TODO: Hacer que esto vaya en un hilo
	private void manejarPREOK(String comando,Socket socketDatos) {
		
		String respuesta = "";
		Club c = null;
		Jugador j = null;
		ArrayList<Jugador> listaJ = null;
		
		switch(comando.split(" ")[1])
		{
			case "ADDCLUB":
				do
				{
					respuesta = JOptionPane.showInputDialog("Ingresa el nuevo nombre del club");
				}while(respuesta.equals(""));
			    c =  new Club();
			    c.setNombre(respuesta);
			    
			    (new ObjetosPorSocket<Club>()).enviarObjetoPorCanalDatos(socketDatos,	c);
				
			break;
			case "UPDATECLUB": //<number> UPDATECLUB <id>
				do
				{
					respuesta = JOptionPane.showInputDialog("Ingresa el nuevo nombre del club");
				}while(respuesta.equals(""));
				c =  new Club();
				c.setNombre(respuesta);
				c.setId(comando.split(" ")[2]);
				 
				(new ObjetosPorSocket<Club>()).enviarObjetoPorCanalDatos(socketDatos, c);
				break;
				
			case "GETCLUB": 
				c = (new ObjetosPorSocket<Club>()).recibirObjeto(socketDatos);
				
				(new ClubTableView(c)).setVisible(true);
				break;
		
			case "LISTCLUBES":
				ArrayList<Club> listaC = (new ObjetosPorSocket<ArrayList<Club>>()).recibirObjeto(socketDatos);
				(new ClubTableView(listaC)).setVisible(true);

				break;
			
			case "ADDJUGADOR":
				(new AnadirJugador(socketDatos)).setVisible(true);
				
				break;
			case "GETJUGADOR":
				///Receive Data
				j = (new ObjetosPorSocket<Jugador>()).recibirObjeto(socketDatos);
				///Openview
				(new JugadorTableView(j)).setVisible(true);
				
				break;
				
			
			case "LISTJUGADORES":
				///Receive Data
				listaJ = (new ObjetosPorSocket<ArrayList<Jugador>>()).recibirObjeto(socketDatos);
				///Openview
				(new JugadorTableView(listaJ)).setVisible(true);
				break;
				
			case "LISTJUGFROMCLUB":
				///Receive Data
				listaJ = (new ObjetosPorSocket<ArrayList<Jugador>>()).recibirObjeto(socketDatos);
				///Openview
				(new JugadorTableView(listaJ)).setVisible(true);
				break;
				
			default:
				System.out.println("DEFAULT PREOK");
				
			break;
		}
		
	}

	
	


}
