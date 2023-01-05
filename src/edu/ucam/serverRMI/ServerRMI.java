package edu.ucam.serverRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import edu.ucam.domain.IFincasAdministrator;
import edu.ucam.parcial2.Concesionario;
import edu.ucam.parcial2.ConcesionarioImp;

public class ServerRMI {

	public ServerRMI()
	{
		try {			
			System.setProperty("java.rmi.server.codebase","file:/C:/directorio/");
		
			LocateRegistry.createRegistry(8080);
			IFincasAdministrator f = new FincasAdministrator();
			
			Naming.rebind("rmi://localhost:8080/FincasService", f);
			
	} catch (Exception e) {
		System.out.println("Trouble: " + e);
	}
	}

	public static void main(String args[])
	{
		new ServerRMI();
	}
}
