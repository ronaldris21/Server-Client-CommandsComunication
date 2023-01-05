package edu.ucam.parcial2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class ConcesionarioServer {


	public ConcesionarioServer() {
		try {			
				System.setProperty("java.rmi.server.codebase","file:/C:/directorio/");
			
				Concesionario c = new ConcesionarioImp();
				
				Naming.rebind("rmi://localhost:8080/ConcesionarioService", c);
				
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}


	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		new ConcesionarioServer();
	}

}
