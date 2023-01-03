package edu.ucam.clients;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucam.clients.frames.ClubTableView;
import edu.ucam.clients.frames.JugadorTableView;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
import edu.ucam.domain.ObjetosPorSocket;

public class HiloDatosCliente extends Thread{

	private String comando;
	private Socket socketDatos;
	public HiloDatosCliente(String comando,Socket socketDatos) {
		this.comando = comando;
		this.socketDatos = socketDatos;
	}
	
	public void run()
	{
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
				System.out.println(listaJ);
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
