package edu.ucam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import edu.ucam.domain.CodigosRespuesta;

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
								pw.println("FAILED "+ palabras[0] + " "+ CodigosRespuesta.FAILED + "Comando Invalido. Usa el comando PASS");
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
			
