package edu.ucam.clients;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import edu.ucam.domain.CodigosRespuesta;


public class ClientApp extends Cliente{

	private MainFrame vistaPrincipal;
	HiloCliente hiloEscuchar;
	
	public ClientApp() {
		this.vistaPrincipal = null;
		
	}
	
	///TODO: login con interfaz visual
	public void mostrarMensajeInterfazVisual(String mensaje)
	{
		System.out.println(mensaje);
		if(vistaPrincipal!=null)
		{
			
			return;
		}
		///TODO: login view
		
	}
	
	public void ejecutar()
	{
		try {
			this.socket =  new Socket("localhost",5000);
			
			PrintWriter pw =  new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			System.out.println(br.readLine()); //Mensaje de bienvenida
			
			
			///TODO: LOGIN USANDO JOPTIONPANES!!!
			String respuesta = "";
			do
			{
				String username = JOptionPane.showInputDialog("Ingresa el USERNAME de tu usuario: (EXIT si deseas salir): ");
				if(username==null || username.toLowerCase().equals("exit"))
				{
					pw.println("00 EXIT");
					pw.flush();
					return;
				}
				pw.println("LOGINApp USER "+username);
				pw.flush();
				
				respuesta = br.readLine();
				System.out.println(respuesta);
				
			}while(!respuesta.split(" ")[0].equals("OK"));
			
			respuesta = "";
			do
			{
				String password = JOptionPane.showInputDialog("Ingresa el PASSWORD de tu usuario: (EXIT si deseas salir): ");
				if(password==null || password.toLowerCase().equals("exit"))
				{
					pw.println("00 EXIT");
					pw.flush();
					return;
				}
				pw.println("LOGINApp PASS "+password);
				pw.flush();
				
				respuesta = br.readLine();
				System.out.println(respuesta);
			}while(!respuesta.split(" ")[0].equals("OK"));
			
			
			
			///Pongo un hilo a escuchar!
			this.hiloEscuchar =  new HiloCliente(this, br);
			hiloEscuchar.start();
			
			
			///Ejecuto interfaz
			vistaPrincipal = new MainFrame(pw);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						vistaPrincipal.setVisible(true);
					} catch(Exception t) {
						t.printStackTrace();
					}
				}
			});
			
			///Cliente se queda escribiendo
			Scanner teclado =  new Scanner(System.in);
			
			while(true)
			{
				if(!isActive())
					return;
				try {
					comando =  teclado.nextLine();
					pw.println(comando);
					pw.flush();	
					
					String[] palabras = comando.split(" ");
					if(palabras.length>2 && palabras[1].equals("EXIT"))
						this.SetisActive(false);
					
				} catch (Exception e) {} //En caso que el servidor cierre el socket!
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		(new ClientApp()).ejecutar();

	}


}
