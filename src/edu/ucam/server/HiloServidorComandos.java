package edu.ucam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;
import edu.ucam.domain.TipoRespuesta;

public class HiloServidorComandos extends Thread{

	private Servidor servidor;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public HiloServidorComandos(Servidor servidor, Socket socket, BufferedReader br, PrintWriter pw) {
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
					String comando = br.readLine();
					System.out.println(comando);
					String[] palabras = comando.split(" ");
					
					if(esExit(palabras))
						return;
					
					
					
					if(palabras.length<2)///COMANDO INVALIDO
					{
						EnviarMensaje(TipoRespuesta.FAILED, palabras[0], CodigosRespuesta.BADREQUEST, "Faltan parametros");
					}
					else
					{
						switch(palabras[1])
						{
							case "SESIONES":
								
								int cantidadSesiones = this.servidor.getHilosClientes().size();
								EnviarMensaje(TipoRespuesta.Ok, palabras[0], CodigosRespuesta.OK,cantidadSesiones+ " Sesiones");
							
							break;
							case "ADDCLUB":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
							break;
							case "UPDATECLUB":
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
								
							case "GETCLUB": //<number> GETCLUB <id>
								
								if(palabras.length<3)///COMANDO INVALIDO
								{
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.BADREQUEST + " Comando Invalido. Usa el comando PASS");
									pw.flush();
								}
								else
								{
									int puerto = this.servidor.getPuertoCanalDatos();
									
									if(puerto==-1)
									{
										//TODO: maybe check + try with only 2/1 ports
										pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.NOTFOUND + " no hay puertos disponibles de datos");
										pw.flush();
									}
									else
									{
										
										
										Club clubbuscado = this.servidor.getClubbyId(palabras[2]);
										
										if(clubbuscado==null)
											pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.NOTFOUND + " No se encontró el dato buscado");
										else
											pw.println("PREOK "+ palabras[0] + " "+ CodigosRespuesta.OK + "127.0.0.1 "+puerto);
										pw.flush();
										
									
										if(clubbuscado!=null)
											(new HiloServidorCanalDatos(this, puerto,clubbuscado)).start(); //envio el dato
									}
								}
								
								
								
								
								
								
								
								break;
								
							case "REMOVECLUB":
								if(palabras.length<3)///COMANDO INVALIDO
								{
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0], CodigosRespuesta.BADREQUEST, "Faltan parametros");
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
										if(Bandera==false) {
											pw.println("No se ha podido borrar");
											pw.flush();
										}
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
									pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.BADREQUEST + "Comando Invalido. Usa el comando PASS");
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
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								
								break;
								
							case "REMOVEJUGFROMCLUB":
								
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								break;

							case "LISTJUGFROMCLUB":
								
								pw.println("comando no hecho todavia");
								pw.flush();
								
								
								break;
								
								 
								
							default:
								
								pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.BADREQUEST + "Comando Invalido. Usa un comando valido (SESIONES)");
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
						this.EnviarMensaje(TipoRespuesta.Ok,palabras[0],CodigosRespuesta.OK, "Envie contraseña");
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
									this.EnviarMensaje(TipoRespuesta.Ok,palabras[0],CodigosRespuesta.OK, "Welcome admin");
									return true; ///LOGIN EXITOSO
								}
								else
								{
									this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.NOTFOUND , "Prueba de nuevo");
								}
							}
							else
							{
								////TODO comando invalido
								this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.BADREQUEST, "Comando Invalido. Usa el comando PASS");
							}
						}
					}
					else
					{
						this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.NOTFOUND, "Not user");
					}
				}
				else
				{
					//TODO Comando invalido
					this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.BADREQUEST, "Comando Invalido. Usa el comando USER");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public boolean esExit(String[] palabras)
	{
		if(palabras.length>=2 && palabras[1].equals("EXIT") )
		{
			this.EnviarMensaje(TipoRespuesta.Ok,palabras[0],CodigosRespuesta.OK, "Bye");
			
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
	
	public void EnviarMensaje(TipoRespuesta tipoRespuesta, String IdPeticion, CodigosRespuesta codigo, String informacionAdicional)
	{
		String mensajeCompleto = String.format("{0} {1} {2} {3}", tipoRespuesta, IdPeticion, codigo.getCode(), informacionAdicional);
		pw.println(mensajeCompleto);
		pw.flush();
	}
}
			
