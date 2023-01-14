package edu.ucam.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;


/**
 * <p>
*Esta es la clase que inicia el cliente.
*</p>
*/

public class Cliente {

	
	///TODO: EXIT_ON_CLOSE         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	protected Boolean isActive = true;
	protected Socket socket;
	protected String comando;
	
	///ARRAYLIST DE LOS COMANDOS ENVIADOS. ASI OBTENGO EL COMANDO ENVIADO EN BASE A LOS ALMACENADOS Y MANDO ESE COMO PARAMETRO
	
	public synchronized String getComando()
	{
		return this.comando;
	}
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public synchronized Boolean isActive()
	{
		return isActive;
	}
	
	public synchronized void SetisActive(Boolean status)
	{
		isActive = status;
	}
	/**
	 *  Ejecuta la aplicacion del cliente. Se conecta el socket al servidor.
	 */
	public void ejecutar()
	{
		try {
			this.socket =  new Socket("localhost",5000);
			
			PrintWriter pw =  new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			///Pongo un hilo a escuchar!
			HiloCliente hiloEscuchar =  new HiloCliente(this, br);
			hiloEscuchar.start();
			//(new VistaPrincipalCliente(socket)).setVisible(true);
			
			///Cliente se queda escribiendo
			Scanner teclado =  new Scanner(System.in);						
			
			while(true)
			{
				if(!isActive())
				{
					teclado.close();
					return;
				}
				try {
					comando =  teclado.nextLine();
					pw.println(comando);
					pw.flush();	
					Thread.sleep(500);
					
					String[] palabras = comando.split(" ");
					if(palabras.length>2 && palabras[1].equals("EXIT"))
						this.SetisActive(false);
					
				} catch (Exception e) {} //En caso que el servidor cierre el socket!
			}
		} catch (UnknownHostException e) {
			int result = JOptionPane.showConfirmDialog(null, "No es posible conectar con el servidor. ¿Quieres volver a intentar conectarte?");
			if (result == JOptionPane.YES_OPTION) 
			{
				(new Cliente()).ejecutar();
				return;
			}
			e.printStackTrace();
		} catch (IOException e) {
			int result = JOptionPane.showConfirmDialog(null, "No es posible conectar con el servidor. ¿Quieres volver a intentar conectarte?");
			if (result == JOptionPane.YES_OPTION) 
			{
				(new Cliente()).ejecutar();
				return;
			}
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  Muestra un mensaje en la interfaz visual o en la consola segun se este ejecutando.
	 */
	public void mostrarMensajeInterfazVisual(String mensaje)
	{
		System.out.println(mensaje);
	}

	/**
	 * 
	 * @param args
	 * Ejecuta la aplicacion en modo consola
	 */
	public static void main(String[] args) {
		(new Cliente()).ejecutar();
	}

}