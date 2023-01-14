package edu.ucam.server;

import java.util.ArrayList;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p> 
 * Esta es la clase que hace de servidor. En esta instancia de guardar y recibir las conexiones de los 
 * clientes que se conectan, puertos disponibles y los datos almacenados(jugadores, clubes).
 * 
 * </p>
 */
public class Servidor {
	
	private ArrayList<HiloServidorComandos>  hilosClientes;
	private ArrayList<Club> club;
	private ArrayList <Jugador> jugador;
	private Boolean[] lpuertosDisponibles;
	private int counterJugadores;
	private int counterClubes;
	static int PUERTOINICIALDATOS = 5001;
	
	
	
	public Servidor() {

		this.hilosClientes =  new ArrayList<HiloServidorComandos>();
		this.club =  new ArrayList<Club>();
		this.jugador = new ArrayList<Jugador>();
		
		this.counterClubes = 0;
		this.counterJugadores = 0;
		
		this.agregarClubServidor("Real Murcia");
		this.agregarClubServidor("Real Madrid");
		
		
		this.agregarJugadorServidor("Quintana","Dante", 10);
		this.agregarJugadorServidor("Ronald","Tejada", 7);
		this.agregarJugadorServidor("Paul","Atreides", 4);
		
		///INIT PUERTOS
		///PUERTOS DISPONIBLES para canal de datos
		this.lpuertosDisponibles =  new Boolean[5000]; //5000 puertos disponibles
		for (int i = 0; i < 3000; i++) {
			this.lpuertosDisponibles[i] = true;
		}
		//Quito puertos importantes
		//Puertos 5400, 5500, 5600, 5700, 5800 y 5900 Son usados por el programa VNC, que también sirve para controlar equipos remotamente.
		//Puertos 6881 y 6969: Son usados por el programa BitTorrent, que sirve para e intercambio de ficheros.
		//Puerto 8080 y 8000: es el puerto alternativo al puerto 80 TCP para servidores web, normalmente se utiliza este puerto en pruebas.
		this.lpuertosDisponibles[5400 -  PUERTOINICIALDATOS] = false; //5400
		this.lpuertosDisponibles[5500 - PUERTOINICIALDATOS] = false; //5500
		this.lpuertosDisponibles[5600 - PUERTOINICIALDATOS] = false; //5600
		this.lpuertosDisponibles[5700 - PUERTOINICIALDATOS] = false; //5700
		this.lpuertosDisponibles[5800 - PUERTOINICIALDATOS] = false; //5800
		this.lpuertosDisponibles[5900 - PUERTOINICIALDATOS] = false; //5900
		this.lpuertosDisponibles[6881 - PUERTOINICIALDATOS] = false; //6881
		this.lpuertosDisponibles[6969 - PUERTOINICIALDATOS] = false; //6969
		this.lpuertosDisponibles[8000 - PUERTOINICIALDATOS] = false; //8000
		this.lpuertosDisponibles[8080 - PUERTOINICIALDATOS] = false; //8080
	}
	
	/**
     * Elimina un cliente
     */
	public void eliminarCliente(HiloServidorComandos cliente)
	{
		///EXIT
		this.hilosClientes.remove(cliente);
	}

	
	/**
     * Lanza el hilo del servidor que se queda recibiendo clientes eternamente.
     */
	public void ejecutar()
	{
		while(true)
		{
			try {
				///Recibiendo clientes
				ServerSocket serverSocket =  new ServerSocket(5000);
				System.out.println("Esperando conexion commandos");
				Socket socket = serverSocket.accept();
				System.out.println("Conexion recibida commandos" + socket.getRemoteSocketAddress());
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				///Recibir Multiples clientes a la vez!!!
				HiloServidorComandos 	hilo = new HiloServidorComandos(this,socket,br,pw);
				hilo.start();
				hilosClientes.add(hilo);
			}
			catch(Exception e)
			{
				
				
			}
		}
		
		
	}
	
	public int getPuertoCanalDatosDisponible()
	{
		
		for (int i = 0; i < lpuertosDisponibles.length; i++) {
			if(this.lpuertosDisponibles[i])
			{
				this.lpuertosDisponibles[i] = false;
				return i+PUERTOINICIALDATOS; 
			}
		}
		///Si no hay disponibles, por defecto que se vaya en el primer canal
		return PUERTOINICIALDATOS; ///no encontrado
	}
	
	public void SetPuertoDisponible(int puerto, boolean status)
	{
		puerto = puerto - PUERTOINICIALDATOS;
		if(puerto>=0 && puerto<lpuertosDisponibles.length)
			this.lpuertosDisponibles[puerto] = status;
	}
	/**
     * Esta función agrega un jugador por medio de sus parámetros.
     */
	public void agregarJugadorServidor(String nombre, String apellidos, int goles)
	{
		Jugador j = new Jugador(String.valueOf(++this.counterJugadores), nombre, apellidos, goles);
		this.jugador.add(j);
	}
	/**
     * Esta función agrega un jugador por medio de otra instancia de jugador.
     */
	public void agregarJugadorServidor(Jugador j)
	{
		this.agregarJugadorServidor(j.getNombre(), j.getApellidos(), j.getGoles());
		
	}
	
	
	/**
     * Esta función agrega un club por medio de sus parámetros.
     */
	public void agregarClubServidor(String nombre)
	{
		Club c = new Club(String.valueOf(++this.counterClubes), nombre);
		this.club.add(c);
	}
	/**
     * Obtiene un club por el ID
     */
	public Club getClubById(String idClub) {
		for(Club c : this.club)
		{
			if(c.getId().equals(idClub))
				return c;
		}
		return null;
	}
	/**
     * Obtiene un jugador por el ID
     */
	public Jugador getJugadorById(String idJugador)
	{
		for(Jugador j : this.jugador)
		{
			if(j.getId().equals(idJugador))
				return j;
		}
		return null;
	}
	

	
	
	
	/**
     * Ejecuta el servidor
     */
	public static void main(String[] args) {
		//Inicio el serverSocket de commandos
		(new Servidor()).ejecutar();
		
	}




	////GETTERS AND SETTERS
	public ArrayList<HiloServidorComandos> getHilosClientes() { return hilosClientes;}
	public ArrayList<Club> getClubes() { return club;}
	public ArrayList <Jugador> getJugadores() { return jugador;}
	
	
	public void setClubes(ArrayList<Club> club) {
		this.club = club;
	}

	public void setJugadores(ArrayList <Jugador> jugador) {
		this.jugador = jugador;
	}

	
	
	

}
