package edu.ucam.domain;


import java.rmi.*;
import java.util.ArrayList;

public interface IFincasAdministrator extends java.rmi.Remote{

	public String hola()
		throws RemoteException;
	
	public ArrayList<Finca> getFincas()
			throws RemoteException;
	
	public String addFinca(Finca finca)
			throws RemoteException;
		
	public String deleteFinca(int idFinca)
			throws RemoteException;
	
	public String updateFinca(int idFinca, Finca finca)
			throws RemoteException;
	
	public Finca getFincabyID(int idFinca)
			throws RemoteException;
	
	
	
	public ArrayList<Cultivo> getCultivosFinca(int idFinca)
			throws RemoteException;

	public String addCultivoFinca(int idFinca, Cultivo c)
			throws RemoteException;
	
	public String removeCultivoFinca(int idCultivo)
			throws RemoteException;

	public String updateCultivoFinca(int idCultivo, Cultivo c)
		throws RemoteException;
	
	public Cultivo getCultivobyId(int idCultivo)
		throws RemoteException;
	
}
