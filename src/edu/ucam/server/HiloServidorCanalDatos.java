package edu.ucam.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;
import edu.ucam.domain.Jugador;
import edu.ucam.domain.TipoRespuesta;
/**
 * <p> 
 * Esta clase se encarga de enviar y/o recibir objetos serializados que se envian por el canal de datos del puerto especificado
 * </p>
 */
public class HiloServidorCanalDatos extends Thread {

	private HiloServidorComandos hiloComandos;
	private int puerto;
	private Object obj;
	private Boolean esperar;
	private String idPeticion;

	public HiloServidorCanalDatos(HiloServidorComandos hiloComandos, String idPeticion , Object obj, Boolean esperar) {
		this.hiloComandos =   hiloComandos;
		this.obj = obj;
		this.esperar = esperar;
		this.idPeticion = idPeticion;
		this.puerto = this.hiloComandos.getServidor().getPuertoCanalDatosDisponible();
	}

	
	/**
     * Recibe los comandos y manda sus respectivas respuestas.
     */
	public void run()
	{
		try 
		{
			this.hiloComandos.EnviarMensaje(TipoRespuesta.PREOK,idPeticion, CodigosRespuesta.OK , "localhost "+puerto);
			
			ServerSocket serverSocket =  new ServerSocket(puerto);
			Socket socket = serverSocket.accept();
			System.out.println("Conexion recibida datos" + socket.getRemoteSocketAddress());
			
			if(this.obj != null)
			{
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); 
				oos.writeObject(obj);
				oos.flush(); 
			}
			
			if(esperar) //Recibo datos
			{
				try 
				{
					ObjectInputStream ios =  new ObjectInputStream(socket.getInputStream());
					Object obj =  ios.readObject(); 
					
					if(obj instanceof Club )
					{
						Club c = (Club) obj;
						if(c.getId()!=null && !c.getId().equals(""))///SI TIENE ID es porque es un update
						{
							Club clubServidor = this.hiloComandos.getServidor().getClubById(c.getId());
							clubServidor.setNombre(c.getNombre());
						}
						else
						{
							this.hiloComandos.getServidor().agregarClubServidor(c.getNombre());
						}
					} 
					if(obj instanceof Jugador )
					{
						Jugador c = (Jugador) obj;
						this.hiloComandos.getServidor().agregarJugadorServidor(c);
					}
					
				} catch (Exception e1) {
					this.hiloComandos.EnviarMensaje(TipoRespuesta.FAILED, this.idPeticion, CodigosRespuesta.INTERNAL_SERVER_ERROR, "IOException");
					this.hiloComandos.getServidor().SetPuertoDisponible(puerto,true);
					e1.printStackTrace();
				} 
				
			}
			
			this.hiloComandos.EnviarMensaje(TipoRespuesta.OK, this.idPeticion, CodigosRespuesta.OK, "Transferencia terminada");
			this.hiloComandos.getServidor().SetPuertoDisponible(puerto,true);
			serverSocket.close();
		} catch (IOException e) {
			this.hiloComandos.EnviarMensaje(TipoRespuesta.FAILED, this.idPeticion, CodigosRespuesta.INTERNAL_SERVER_ERROR, "IOException");
			this.hiloComandos.getServidor().SetPuertoDisponible(puerto,true);
			
			e.printStackTrace();
		}
		
	}
}
