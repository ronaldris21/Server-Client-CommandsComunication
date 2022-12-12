package edu.ucam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;
import edu.ucam.domain.Jugador;

public class HiloServidorComandos extends Thread{

	private Servidor servidor;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public HiloServidorComandos(Servidor servidor, Socket socket, BufferedReader br, PrintWriter pw) {
		// TODO Auto-generated constructor stub
		this.servidor = servidor;
		this.socket =  socket;
		this.br = br;
		this.pw = pw;
	}

	
	public void run()
	{
		pw.println("Conectado al servidor - Bienvenido al SISTEMA- Inicia sesion");
		pw.flush();
		
		if(login())
		{
			////Aqui vamos a gestionar los comandos cuando ya inicio sesion
			while(true)
			{
				try {
					System.out.println("CLUBES");
					for (Club c1 :  this.servidor.getClub()) {
						System.out.println(c1.getId()+ " "+ c1.getNombre());
						for (Jugador j1 : c1.getJugadores()) {
							System.out.println("\t"+j1.getId()+ " "+ j1.getNombre());
						}
					}
					
					System.out.println();
					System.out.println("JUGADORES");
					
					for (Jugador j1 : this.servidor.getJugador()) {
						System.out.println(j1.getId()+ " "+ j1.getNombre());
					}
					
					
					String comando = br.readLine();
					System.out.println(comando);
					String[] palabras = comando.split(" ");
					
					if(esExit(palabras))
						return;
					
					
					
					
					
					
					if(palabras.length<2)///COMANDO INVALIDO
					{
						pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa el comando PASS");
						pw.flush();
					}
					else
					{
						switch(palabras[1])
						{
							case "SESIONES":
								
								int cantidadSesiones = this.servidor.getHilosClientes().size();
								pw.println("OK "+ palabras[0] + " "+ CodigosRespuesta.Ok + " "+cantidadSesiones+" Sesiones");
								pw.flush();
								
							
							break;
							case "ADDCLUB":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
							break;
							case "UPDATECLUB":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
								
							case "GETCLUB":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
								
							case "REMOVECLUB":
								if(palabras.length<3)///COMANDO INVALIDO
								{
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa el comando PASS");
									pw.flush();
								} 
								else {
									boolean Bandera= false;
									for (int i=0; i<this.servidor.getClub().size();i++) {
									
										if(palabras[2].equals(this.servidor.getClub().get(i).getId())) {
											this.servidor.getClub().remove(i);
											pw.println("Borrado");
											pw.flush();
											Bandera= true;
										}
										
									}
									if(Bandera==false) {
										pw.println("No se ha podido borrar");
										pw.flush();
										}
								}
								
								break;
							case "LISTCLUBES":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
							case "COUNTCLUBES":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
							case "ADDJUGADOR":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
							case "GETJUGADOR":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
								
							case "REMOVEJUGADOR":
								if(palabras.length<3)///COMANDO INVALIDO
								{
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa el comando PASS");
									pw.flush();
								} 
								else {
									boolean Bandera= false;
									for (int i=0; i<this.servidor.getJugador().size();i++) {
									
										if(palabras[2].equals(this.servidor.getJugador().get(i).getId())) {
											this.servidor.getJugador().remove(i);
											pw.println("Borrado");
											pw.flush();
											Bandera= true;
										}
										
									}
									if(Bandera==false) {
										pw.println("No se ha podido borrar");
										pw.flush();
									}
								}
								
								
								break;
								
							case "LISTJUGADORES":
								
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								break;
								
							case "ADDJUGADOR2CLUB":
								if(palabras.length<4)///COMANDO INVALIDO
								{
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa el comando PASS");
									pw.flush();
								} 
								else {
									
									Jugador j= null;
									Club c= null;
									
									for (int i=0; i<this.servidor.getClub().size();i++) {
								
										if(palabras[2].equals(this.servidor.getClub().get(i).getId())){
											
											c= this.servidor.getClub().get(i);
											
										}
										
									}
								
									
									for (int i=0; i<this.servidor.getJugador().size();i++) {
										
										if(palabras[3].equals(this.servidor.getJugador().get(i).getId())){
											
											j= this.servidor.getJugador().get(i);
											
										}
										
									}
									
									if(j!=null&&c!=null) {
										c.addJugador(j.getId(), j);
										this.servidor.getJugador().remove(j);
										pw.println("Añadido");
										pw.flush();
									}else {
										pw.println("No se ha podido añadir");
										pw.flush();
									}
								}
								
								
								
								break;
								
							case "REMOVEJUGFROMCLUB":
								Boolean Bandera= false;
								if(palabras.length<4)///COMANDO INVALIDO
								{
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa el comando PASS");
									pw.flush();
								} 
								else {
									
									
									Club c= null;
									
									for (int i=0; i<this.servidor.getClub().size();i++) {
								
										if(palabras[2].equals(this.servidor.getClub().get(i).getId())){
											
											c= this.servidor.getClub().get(i);
											for (int ii=0; ii<c.getJugadores().size();ii++) {
												
												if(palabras[3].equals( c.getJugadores().get(ii).getId())){
													
													c.getJugadores().remove(ii);
													pw.println("Borrado");
													pw.flush();
													Bandera= true;
													break;
												}
												
											
										}
										
											
										}
										
									}
									
								}
								if(Bandera==false) {
									pw.println("No se ha podido borrar");
									pw.flush();
								}
								break;

							case "LISTJUGFROMCLUB":
								
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								break;
								
								 
								
							default:
								
								pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa un comando valido (SESIONES)");
								pw.flush();
								
							break;
							
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}  
			}
		}
	}

	

	private boolean login() {

		while(true)
		{
			try {
				String comando = br.readLine();  //<number> USER <name>
				System.out.println(comando);
				String[] palabras = comando.split(" ");
				if(esExit(palabras))
					return false;
				
				if(palabras.length==3 && palabras[1].equals("USER"))
				{
					if(palabras[2].equals("admin"))
					{
						pw.println("OK "+ palabras[0] + " "+ CodigosRespuesta.Ok + " Envie contraseña");
						pw.flush();
						while(true)
						{
							
							////Ahora valido contraseña
							comando = br.readLine();  //<number> PASS <pass>
							palabras = comando.split(" ");
							
							if(esExit(palabras))
								return false;
							
							if(palabras.length==3 && palabras[1].equals("PASS"))
							{
								if(palabras[2].equals("admin"))
								{
									pw.println("OK "+ palabras[0] + " "+ CodigosRespuesta.Ok + " Welcome admin");
									pw.flush();
									return true; ///LOGIN EXITOSO
									
								}
								else
								{
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Prueba de nuevo");
									pw.flush();
								}
							}
							else
							{
								////TODO comando invalido
								pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Comando Invalido. Usa el comando PASS");
								pw.flush();
							}
						}
					}
					else
					{
						pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Not user.");
						pw.flush();
					}
				}
				else
				{
					//TODO Comando invalido
					pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + " Comando Invalido. Usa el comando USER");
					pw.flush();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	///TODO: Decidir forma de hacer exit!
	public boolean esExit(String[] palabras)
	{
		if(palabras.length>=2 && palabras[1].equals("EXIT") )
		{
			pw.println("OK "+ palabras[0] + " "+ CodigosRespuesta.Ok + " Bye");
			pw.flush();
			
			this.servidor.eliminarCliente(this);  ///Elimino de la lista de clientes del servidor
			
			
			try {
				socket.shutdownOutput();
				this.socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
}
			
