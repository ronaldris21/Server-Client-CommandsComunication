package edu.ucam.parcial2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import edu.ucam.domain.*;;

public class ConcesionarioImp extends UnicastRemoteObject implements Concesionario {

	private static Hashtable<String,Coche> coches;
	private static Hashtable<String,Venta> ventas;
	protected ConcesionarioImp() throws RemoteException {
		super();
		coches = new Hashtable<String,Coche>();
		ventas = new Hashtable<String,Venta>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addCoche(String matricula, String marca) throws RemoteException {
		// TODO Auto-generated method stub
		Coche coche = new Coche(marca, matricula);
		coches.put(matricula, coche);
		return "Se ha a�adido el coche";
	}

	@Override
	public String getCoche(String matricula) throws RemoteException {
		String res;
		Coche coche =  coches.get(matricula);
		if (coche == null)
			res = "Coche no existe";
		else {
			res = "Marca coche: " + coche.getMarca() + ", Matricula: " + coche.getMatricula();
		}
		return res;
		// TODO Auto-generated method stub

	}

	@Override
	public String modCoche(String matricula, String marca) throws RemoteException {
		String res;
		Coche coche =  coches.get(matricula);
		if (coche == null)
			res = "Coche no existe, no se puede modificar";
		else {
			coche.setMarca(marca);
			coches.put(matricula, coche);
			res = "Coche modificado";
		}
		return res;
		// TODO Auto-generated method stub

	}

	@Override
	public String delCoche(String matricula) throws RemoteException {
		String res;
		Coche coche =  coches.get(matricula);
		if (coche == null)
			res = "Coche no existe, no se puede eliminar";
		else {
			coches.remove(matricula);
			res = "Coche eliminado";
		}
		return res;
		// TODO Auto-generated method stub

	}

	@Override
	public String addVenta(String matricula, String precioVenta) throws RemoteException {
		String res;
		Coche coche =  coches.get(matricula);
		if (coche == null)
			res = "Coche no existe, no se puede vender";
		else {
			Venta venta = new Venta(precioVenta, coche);
			ventas.put(matricula, venta);
			res = "Venta guardada correctamente";
		}
		return res;
		// TODO Auto-generated method stub

	}

	@Override
	public String getVenta(String matricula) throws RemoteException {
		// TODO Auto-generated method stub
		String res;
		Venta venta =  ventas.get(matricula);
		if (venta == null)
			res = "Venta de este coche no se ha realizado";
		else {
			res = "El coche con matricula " + venta.getCoche().getMatricula() + " se ha vendido por " + venta.getPrecioVenta();
		}
		return res;
	}

	@Override
	public String modVenta(String matricula, String precioVenta) throws RemoteException {
		String res;
		Venta venta =  ventas.get(matricula);
		if (venta == null)
			res = "Venta de este coche no se ha realizado, no se puede modificar";
		else {
			venta.setPrecioVenta(precioVenta);
			ventas.put(matricula, venta);
			res = "Venta modificada correctamente";
		}
		return res;
		// TODO Auto-generated method stub

	}

	@Override
	public String delVenta(String matricula) throws RemoteException {
		String res;
		Venta venta =  ventas.get(matricula);
		if (venta == null)
			res = "Venta de este coche no se ha realizado";
		else {
			ventas.remove(matricula);
			res = "Venta eliminada correctamente";
		}
		return res;
		// TODO Auto-generated method stub

	}

}
