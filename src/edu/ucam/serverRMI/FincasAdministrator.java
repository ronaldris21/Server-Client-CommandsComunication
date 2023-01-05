package edu.ucam.serverRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import edu.ucam.domain.Cultivo;
import edu.ucam.domain.Finca;
import edu.ucam.domain.IFincasAdministrator;

public class FincasAdministrator extends UnicastRemoteObject implements IFincasAdministrator {

	int counterFincas, counterCultivos;
	private ArrayList<Finca > lFincas;
	private ArrayList<Cultivo> lCultivos;
	
	public FincasAdministrator() throws RemoteException{
		super();
		this.counterCultivos = 1;
		this.counterFincas = 1;
		this.lCultivos =  new ArrayList<Cultivo>();
		this.lFincas =  new ArrayList<Finca>();
	}
	
	
	@Override
	public String hola() throws RemoteException {
		return "Bienvenido - Conectado exitosamente";
	}

	@Override
	public ArrayList<Finca> getFincas() throws RemoteException {
		return this.lFincas;
	}

	@Override
	public String addFinca(Finca finca) throws RemoteException {
		Finca f = new Finca(counterCultivos++, finca.getNombreFinca(),finca.getDuenoFinca());
		this.lFincas.add(f);
		return "FINCA AGREGADA CON EXITO. " + f; 
	}

	@Override
	public String deleteFinca(int idFinca) throws RemoteException {
		if(this.lFincas.removeIf(f-> f.getId() == idFinca))
		{
			this.lCultivos.removeIf(c -> c.getIdFinca() == idFinca);
			return "Finca eliminada";
		}
		return "No existe la finca a eliminar";
	}

	@Override
	public String updateFinca(int idFinca, Finca finca) throws RemoteException {
		Finca f = this.getFincabyID(idFinca);
		if(f!=null)
		{
			f.setDuenoFinca(finca.getDuenoFinca());
			f.setNombreFinca(f.getNombreFinca());
			return "Datos de la finca actualizados";
		}
		return "No fue posible actualizar los datos de la finca, no se encontró";
	}

	@Override
	public Finca getFincabyID(int idFinca) throws RemoteException {
		for(Finca ff : this.lFincas)
			if(ff.getId() == idFinca)
			{
				return ff;
			}
		return null;
	}

	@Override
	public ArrayList<Cultivo> getCultivosFinca(int idFinca) throws RemoteException {
		ArrayList<Cultivo> data = new ArrayList<>();
		for (Cultivo c : this.lCultivos) 
			if(c.getIdFinca() == idFinca)
				data.add(c);
		
		return data;
	}

	@Override
	public String addCultivoFinca(int idFinca, Cultivo c) throws RemoteException {
		Finca f = this.getFincabyID(idFinca);
		if(f==null)
			return "No existe la finca a la que se quiere agregar el cultivo";
		
		c.setIdFinca(idFinca);
		c.setId(counterCultivos++);
		
		this.lCultivos.add(c);
		
		return "Cultivo agregado a la finca: "+ f.getNombreFinca();
	}


	@Override
	public String removeCultivoFinca(int idCultivo) throws RemoteException {
		
		if(this.lCultivos.removeIf(f-> f.getId() == idCultivo))
		{
			
			return "Cultivo eliminado";
		}
		return "No existe el cultivo a eliminar";
	}


	@Override
	public String updateCultivoFinca(int idCultivo, Cultivo c) throws RemoteException {
		Cultivo cultivo = this.getCultivobyId(idCultivo);
		if(cultivo!=null)
		{
			cultivo.setIdFinca(c.getIdFinca());
			cultivo.setNombreCultivo(c.getNombreCultivo());
			cultivo.setPresupuesto(c.getPresupuesto());

			return "Datos del cultivo actualizados";
		}
		return "No fue posible actualizar los datos del cultivo, no se encontró";
	}


	@Override
	public Cultivo getCultivobyId(int idCultivo) throws RemoteException {
		for(Cultivo c : this.lCultivos)
			if (c.getId() == idCultivo) 
				return c;
		
		return null;
	}

	

}
