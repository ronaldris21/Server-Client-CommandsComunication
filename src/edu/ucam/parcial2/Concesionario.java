package edu.ucam.parcial2;

public interface Concesionario extends java.rmi.Remote{
	public String addCoche(String matricula, String marca) 
		throws java.rmi.RemoteException;
	public String getCoche(String matricula) 
		throws java.rmi.RemoteException;
	public String modCoche(String matricula, String marca) 
		throws java.rmi.RemoteException;
	public String delCoche(String matricula) 
		throws java.rmi.RemoteException;
	public String addVenta(String matricula, String precioVenta) 
		throws java.rmi.RemoteException;
	public String getVenta(String matricula) 
		throws java.rmi.RemoteException;
	public String modVenta(String matricula, String precioVenta) 
		throws java.rmi.RemoteException;
	public String delVenta(String matricula) 
		throws java.rmi.RemoteException;
}
