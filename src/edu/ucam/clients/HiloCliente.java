package edu.ucam.clients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.ucam.domain.*;

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
		
		ObjectInputStream ios;
		
		
		switch(comando.split(" ")[1])
		{
			
			case "ADDCLUB":
				System.out.println("FALTA");
				///Open view 
				///Send Data
				
			break;
			case "UPDATECLUB":

				System.out.println("FALTA");
				///Receive data
				///Open view
				///Send Data to save
				
				
				try {
					ios =  new ObjectInputStream(socketDatos.getInputStream());
					Club c = (Club) ios.readObject(); 
					System.out.println(c.getNombre());
					System.out.println(c.getId());
					System.out.println(c.getJugadores().size());
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				
				break;
				
			case "GETCLUB": 

				System.out.println("FALTA");
				
				
				
				try {
					ios =  new ObjectInputStream(socketDatos.getInputStream());
					Club c = (Club) ios.readObject(); 
					System.out.println(c.getNombre());
					System.out.println(c.getId());
					System.out.println(c.getJugadores().size());
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				///Receive Data
				///Open view
				
				
				
				
				
				break;
		
			case "LISTCLUBES":

				System.out.println("FALTA");
			
			try {
				ios = new ObjectInputStream(socketDatos.getInputStream());
				try {
					
					ArrayList<Club> lista = (ArrayList<Club>) ios.readObject();
					System.out.println(lista);
					
					
					(new ClubTableView(lista)).setVisible(true);;
					
					
					
					
					
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
				///Receive Data
				///Openview
				
				break;
			
			case "ADDJUGADOR":
				System.out.println("FALTA");
				
				///Openview
				(new AnadirJugador(socketDatos)).setVisible(true);;
				///Send Data
				
				break;
			case "GETJUGADOR":
				System.out.println("FALTA");
				///Receive Data
				///Openview
				
				
				break;
				
			
			case "LISTJUGADORES":
				System.out.println("FALTA");
				///Receive Data
				///Openview
				
				
				break;
				
			default:
				System.out.println("DEFAULT PREOK");
				
			break;
			
		}
		
	}



}
