package edu.ucam.server;

import java.util.ArrayList;
import java.util.Iterator;

import edu.ucam.clients.*;
import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;
import edu.ucam.domain.Jugador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ArrayList<HiloServidorComandos> hilosClientes;
	private ArrayList<Club> club;
	private ArrayList <Jugador> jugador;
	private Boolean[] lpuertosDisponibles;
	
	
	
	public Servidor() {
		///PUERTOS DISPONIBLES para canal de datos
		this.lpuertosDisponibles =  new Boolean[3000]; //5100+3000= 
		for (int i = 0; i < 3000; i++) {
			this.lpuertosDisponibles[i] = true;
		}
		//Puertos 5400, 5500, 5600, 5700, 5800 y 5900 Son usados por el programa VNC, que también sirve para controlar equipos remotamente.
		//Puertos 6881 y 6969: Son usados por el programa BitTorrent, que sirve para e intercambio de ficheros.
		//Puerto 8080 y 8000: es el puerto alternativo al puerto 80 TCP para servidores web, normalmente se utiliza este puerto en pruebas.
		this.lpuertosDisponibles[300] = false; //5400
		this.lpuertosDisponibles[400] = false; //5500
		this.lpuertosDisponibles[500] = false; //5600
		this.lpuertosDisponibles[600] = false; //5700
		this.lpuertosDisponibles[700] = false; //5800
		this.lpuertosDisponibles[800] = false; //5900
		this.lpuertosDisponibles[1781] = false; //6881
		this.lpuertosDisponibles[1869] = false; //6969
		this.lpuertosDisponibles[2900] = false; //8000
		this.lpuertosDisponibles[2980] = false; //8080
		
		
		
		
		
		
		this.hilosClientes =  new ArrayList<HiloServidorComandos>();
		setClub(new ArrayList<Club>());
		setJugador(new ArrayList<Jugador>());
		
		Club c1= new Club();
		c1.setId("0");
		c1.setNombre("Real Murcia");
		club.add(c1);
		Club c2= new Club();
		c2.setId("1");
		c2.setNombre("Real Madrid");
		club.add(c2);
		Jugador j1 = new Jugador();
		j1.setId("1");
		j1.setApellidos("Quintana");
		j1.setNombre("Dante");
		j1.setGoles(10);
		jugador.add(j1);
		Jugador j2 = new Jugador();
		j2.setId("2");
		j2.setApellidos("Tejada");
		j2.setNombre("Ronald");
		j2.setGoles(7);
		jugador.add(j2);
		Jugador j3 = new Jugador();
		j3.setId("0");
		j3.setApellidos("Cano");
		j3.setNombre("Pablo");
		j3.setGoles(6);
		jugador.add(j3);
		Jugador j4 = new Jugador();
		j4.setId("3");
		j4.setApellidos("Atreides");
		j4.setNombre("Paul");
		j4.setGoles(4);
		jugador.add(j4);

		
		
	}
	
	
	public void eliminarCliente(HiloServidorComandos cliente)
	{
		///EXIT
		this.hilosClientes.remove(cliente);
	}

	
	
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
			}catch(Exception e)
			{
				
			}
			
		}
		
		
	}
	
	public int getPuertoCanalDatos()
	{
		int puerto = 0;
		
		for (int i = 0; i < lpuertosDisponibles.length; i++) {
			if(this.lpuertosDisponibles[i])
			{
				this.lpuertosDisponibles[i] = false;
				return i+5100; 
			}
		}
		return -1; ///no encontrado
	}
	
	public void SetPuertoDisponible(int puerto, boolean status)
	{
		puerto = puerto - 5100;
		if(puerto>=0 && puerto<3000)
			this.lpuertosDisponibles[puerto] = status;
	}
	
	////GETTERS AND SETTERS
	public ArrayList<HiloServidorComandos> getHilosClientes() {
		return hilosClientes;
	}


	public void setHilosClientes(ArrayList<HiloServidorComandos> hilosClientes) {
		this.hilosClientes = hilosClientes;
	}
	
	
	


	public static void main(String[] args) {
		//Inicio el serverSocket de commandos
		(new Servidor()).ejecutar();
		
	}


	public ArrayList<Club> getClub() {
		return club;
	}


	public void setClub(ArrayList<Club> club) {
		this.club = club;
	}


	public ArrayList <Jugador> getJugador() {
		return jugador;
	}


	public void setJugador(ArrayList <Jugador> jugador) {
		this.jugador = jugador;
	}


	public Club getClubbyId(String idClub) {
		for(Club c : this.club)
		{
			if(c.getId().equals(idClub))
				return c;
		}
		return null;
	}
	
	
	

}
