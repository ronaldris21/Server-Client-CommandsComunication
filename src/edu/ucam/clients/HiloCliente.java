package edu.ucam.clients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
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

				try {
					ios =  new ObjectInputStream(socketDatos.getInputStream());
					Club c = (Club) ios.readObject(); 
					(new ClubTableView(c)).setVisible(true);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				break;
		
			case "LISTCLUBES":
				try {
					ios = new ObjectInputStream(socketDatos.getInputStream());
					try {
						
						ArrayList<Club> lista = (ArrayList<Club>) ios.readObject();
						System.out.println(lista);
						
						//TODO: Hay que hacer que se muestre la vista
						(new ClubTableView(lista)).setVisible(true);
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				break;
			
			case "ADDJUGADOR":
				(new AnadirJugador(socketDatos)).setVisible(true);
				
				break;
			case "GETJUGADOR":
				System.out.println("FALTA");
				///Receive Data
				///Openview
				
				try {
					ios = new ObjectInputStream(socketDatos.getInputStream());
					try {
						
						Jugador obj = (Jugador) ios.readObject();
						
						(new JugadorTableView(obj)).setVisible(true);
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				
				break;
				
			
			case "LISTJUGADORES":
				System.out.println("FALTA");
				///Receive Data
				///Openview
				try {
					ios = new ObjectInputStream(socketDatos.getInputStream());
					try {
						
						ArrayList<Jugador> obj = (ArrayList<Jugador>) ios.readObject();
						
						(new JugadorTableView(obj)).setVisible(true);
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				break;
				
			case "LISTJUGFROMCLUB":
				try {
					ios = new ObjectInputStream(socketDatos.getInputStream());
					try {
						ArrayList<Jugador> obj = (ArrayList<Jugador>) ios.readObject();
						(new JugadorTableView(obj)).setVisible(true);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
				} catch (IOException e) {
					e.printStackTrace();
				} 
				break;
				
			default:
				System.out.println("DEFAULT PREOK");
				
			break;
			
		}
		
	}



}
