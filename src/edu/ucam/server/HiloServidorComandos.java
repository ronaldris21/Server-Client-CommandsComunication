package edu.ucam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import edu.ucam.domain.Club;
import edu.ucam.domain.CodigosRespuesta;
import edu.ucam.domain.TipoRespuesta;
import edu.ucam.domain.Jugador;

public class HiloServidorComandos extends Thread{

	private Servidor servidor;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	///TODO: manejar si el servidor se cierra abruptamente
	
	public PrintWriter getPrintWriter(){ return this.pw; }
	public Servidor getServidor() { return this.servidor; }

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
								EnviarMensaje(TipoRespuesta.OK, palabras[0], CodigosRespuesta.OK,cantidadSesiones+ " Sesiones");
							
							break;
							case "ADDCLUB":
								(new HiloServidorCanalDatos(this,palabras[0], null ,true)).start(); //envio el dato
								
								
							break;
							case "UPDATECLUB": //<number> UPDATECLUB <id>
								if(palabras.length<3)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else
								{
									Club clubbuscado = this.servidor.getClubById(palabras[2]);
									
									if(clubbuscado!=null)
										(new HiloServidorCanalDatos(this,palabras[0],clubbuscado,true)).start(); //envio el dato
									else
										this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0], CodigosRespuesta.NOTFOUND , "No se encontró el dato buscado");
								}
								break;
								
							case "GETCLUB": //<number> GETCLUB <id>
								
								if(palabras.length<3)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else
								{
									Club clubbuscado = this.servidor.getClubById(palabras[2]);
									
									if(clubbuscado!=null)
										(new HiloServidorCanalDatos(this,palabras[0],clubbuscado,false)).start(); //envio el dato
									else
										this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0], CodigosRespuesta.NOTFOUND , "No se encontró el dato buscado");
								}
								
								break;
							case "REMOVECLUB": //<number> REMOVECLUB <id> 
								if(palabras.length<3)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else 
								{
									if (this.servidor.getClubes().removeIf(c-> c.getId().equals(palabras[2]))) 
										EnviarMensaje(TipoRespuesta.OK, palabras[0], CodigosRespuesta.OK , "Club Eliminado");
									else
										EnviarMensaje(TipoRespuesta.FAILED, palabras[0], CodigosRespuesta.NOTFOUND , "No se encontro el club");
								}
								
								break;
							case "LISTCLUBES":
								(new HiloServidorCanalDatos(this,palabras[0], this.servidor.getClubes() ,false)).start(); //envio el dato
						
								break;
							case "COUNTCLUBES":
								EnviarMensaje(TipoRespuesta.OK,palabras[0],CodigosRespuesta.OK, String.valueOf(this.servidor.getClubes().size()));
								
								break;
							case "ADDJUGADOR": //<number> ADDJUGADOR
								
								(new HiloServidorCanalDatos(this, palabras[0], null, true)).start();
								
								break;
							case "GETJUGADOR": //<number> GETJUGADOR <id>
								
								
								if(palabras.length<3)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else 
								{
									boolean Bandera= false;
									for (int i=0; i<this.servidor.getJugadores().size();i++) {
									
										if(palabras[2].equals(this.servidor.getJugadores().get(i).getId())) {
											(new HiloServidorCanalDatos(this, palabras[0],this.servidor.getJugadores().remove(i), false)).start();
											Bandera= true;
											break;
										}
									}
									if(Bandera==false) 
										EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.NOTFOUND, "No se encontro el elemento");
								}
								
								
								break;
							case "REMOVEJUGADOR": //<number> REMOVEJUGADOR <id>
								if(palabras.length<3)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else 
								{
									boolean Bandera= false;
									for (int i=0; i<this.servidor.getJugadores().size();i++) {
									
										if(palabras[2].equals(this.servidor.getJugadores().get(i).getId())) {
											this.servidor.getJugadores().remove(i);
											EnviarMensaje(TipoRespuesta.OK,palabras[0],CodigosRespuesta.OK, "Borrado");
											Bandera= true;
											break;
										}
									}
									if(Bandera==false) 
										EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.NOTFOUND, "No se encontro el elemento");
								}
								
								
								break;
							case "LISTJUGADORES":
								(new HiloServidorCanalDatos(this, palabras[0], this.servidor.getJugadores(), false)).start();
								
								
								break;
							case "ADDJUGADOR2CLUB": //<number>  ADDJUGADOR2CLUB <idjugador> <idclub> 
								if(palabras.length<4)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								
								else 
								{
									
									Jugador j= this.servidor.getJugadorById(palabras[2]);
									Club c= this.servidor.getClubById(palabras[3]);
									
									if(j!=null && c!=null) 
									{
										c.addJugador(j.getId(), j);//hashtable
										this.servidor.getJugadores().remove(j);//arraylist
										EnviarMensaje(TipoRespuesta.OK, palabras[0],CodigosRespuesta.OK, "Jugador añadido");
									}
									else 
										EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "No se ha podido añadir");
								}
								
								break;
							case "REMOVEJUGFROMCLUB": //<number>  REMOVEJUGFROMCLUB <idjugador> <idclub>
								Boolean Bandera= false;
								if(palabras.length<4)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else
								{
									Club c = this.servidor.getClubById(palabras[3]);
									Jugador j = null;
									if(c!= null)
										for (int ii=0; ii<c.getJugadores().size();ii++) 
											if(palabras[2].equals( c.getJugadores().get(ii).getId()))
											{
												j = c.getJugadores().get(ii);
												Bandera= true;
												break;
											}
									
									if(!c.removeJugador(palabras[2])) 
										EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.NOTFOUND, "No es posible realizar la acción");
									else
									{
										servidor.getJugadores().add(j);
										EnviarMensaje(TipoRespuesta.OK, palabras[0],CodigosRespuesta.OK, "Jugador eliminado del equipo");
										
									}
								}
								
								break;
							case "LISTJUGFROMCLUB": //number>  LISTJUGFROMCLUB <idjugador>
								if(palabras.length<3)
									EnviarMensaje(TipoRespuesta.FAILED, palabras[0],CodigosRespuesta.BADREQUEST, "Faltan parametros");
								else
								{
									Club clubbuscado = this.servidor.getClubById(palabras[2]);
									if(clubbuscado!=null)
									{
										(new HiloServidorCanalDatos(this,palabras[0],clubbuscado.getJugadores(),false)).start(); //envio el dato
									}
									else
										this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0], CodigosRespuesta.NOTFOUND , "No se encontró el dato buscado");
								}
								
								break;
							default:
								EnviarMensaje(TipoRespuesta.FAILED, palabras[0], CodigosRespuesta.BADREQUEST,"Comando Invalido");
							break;
							
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Conexion finalizada abruptamente");
					return;
					
				}  
			}
		}
	}

	

	private boolean login() {

		while(true)
		{
			try
			{
				String comando = br.readLine();  //<number> USER <name>
				System.out.println(comando);
				String[] palabras = comando.split(" ");
				if(esExit(palabras))
					return false;
				
				if(palabras.length==3 && palabras[1].equals("USER"))
				{
					if(palabras[2].equals("admin"))
					{
						this.EnviarMensaje(TipoRespuesta.OK,palabras[0],CodigosRespuesta.OK, "Envie contraseña");
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
									this.EnviarMensaje(TipoRespuesta.OK,palabras[0],CodigosRespuesta.OK, "Welcome admin");
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
					this.EnviarMensaje(TipoRespuesta.FAILED,palabras[0],CodigosRespuesta.BADREQUEST, "Comando Invalido. Usa el comando USER");
				}
			} catch (Exception e) {
				System.out.println("Conexion finalizada abruptamente");
				return false;
			}
		}
	}
	
	public boolean esExit(String[] palabras)
	{
		if((palabras.length>=2 && palabras[1].equals("EXIT")) )
		{
			this.EnviarMensaje(TipoRespuesta.OK,palabras[0],CodigosRespuesta.OK, "Bye");
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
		String mensajeCompleto = String.format("%s %s %d %s", tipoRespuesta, IdPeticion, codigo.getCode(), informacionAdicional);
		pw.println(mensajeCompleto);
		pw.flush();
	}
}