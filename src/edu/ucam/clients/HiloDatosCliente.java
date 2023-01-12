package edu.ucam.clients;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucam.clients.frames.AnadirJugador;
import edu.ucam.clients.frames.JugadorTableView;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
/**
 * <p>
*Esta es la clase que abreclas aplicaciones visuales
*</p>
*/
public class HiloDatosCliente extends Thread{

	private String comando;
	private Socket socketDatos;
	private HiloCliente hiloCliente;
	public HiloDatosCliente(String comando,Socket socketDatos,HiloCliente hiloCliente) {
		this.comando = comando;
		this.socketDatos = socketDatos;
		this.hiloCliente = hiloCliente;
	}
	/**
	 * Abre la aplicacion visual para anadir club, actualizar el club
	 *  obtener los clubes, listar clubes, anadir jugador,
	 *  obtener jugadores, listar jugadores y listar jugadores de un club.
	 */
	public void run()
	{
		String respuesta = "";
		Club c = null;
		Jugador j = null;
		ArrayList<Jugador> listaJ = null;
		
		switch(comando.split(" ")[1])
		{
			case "ADDCLUB":  //TODO: SHIT NOT WORKING ON CONSOLE
				do
				{
					respuesta = JOptionPane.showInputDialog("Ingresa el nuevo nombre del club");
				}while(respuesta.equals(""));
			    c =  new Club();
			    c.setNombre(respuesta);
			    
			    
			    this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(c.toString());
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
				
				this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(c.toString());
				(new ObjetosPorSocket<Club>()).enviarObjetoPorCanalDatos(socketDatos, c);
				break;
				
			case "GETCLUB": 
				c = (new ObjetosPorSocket<Club>()).recibirObjeto(socketDatos);
				
				this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(c.toString());
				break;
		
			case "LISTCLUBES":
				ArrayList<Club> listaC = (new ObjetosPorSocket<ArrayList<Club>>()).recibirObjeto(socketDatos);
				
				for(Club cc : listaC)
					respuesta += cc.toString();
				
				this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(listaC.toString());
				

				break;
			
			case "ADDJUGADOR":
				(new AnadirJugador(socketDatos)).setVisible(true);
				
				break;
			case "GETJUGADOR":
				///Receive Data
				j = (new ObjetosPorSocket<Jugador>()).recibirObjeto(socketDatos);
				///Openview
				System.out.println(j);
				(new JugadorTableView(j)).setVisible(true);
				this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(j.toString());
				
				
				break;
				
			
			case "LISTJUGADORES":
				///Receive Data
				listaJ = (new ObjetosPorSocket<ArrayList<Jugador>>()).recibirObjeto(socketDatos);
				///Openview
				this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(listaJ.toString());
				(new JugadorTableView(listaJ)).setVisible(true);
				break;
				
			case "LISTJUGFROMCLUB":
				///Receive Data
				listaJ = (new ObjetosPorSocket<ArrayList<Jugador>>()).recibirObjeto(socketDatos);
				///Openview
				this.hiloCliente.getCliente().mostrarMensajeInterfazVisual(listaJ.toString());
				(new JugadorTableView(listaJ)).setVisible(true);
				break;
				
			default:
				System.out.println("DEFAULT PREOK");
				
			break;
		}
	}

}
