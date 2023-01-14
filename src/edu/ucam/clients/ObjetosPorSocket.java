package edu.ucam.clients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p> 
 * Esta clase es una clase generica que sirve para enviar y recibir en un socket un objeto de cualquier tipo de datos(T).
 * </p>
 */
public class ObjetosPorSocket<T> {

	public T recibirObjeto(Socket socketDatos)
	{
		try {
			ObjectInputStream ios = new ObjectInputStream(socketDatos.getInputStream());
			try {
				T obj = (T) ios.readObject();
				return obj;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	/**
     * Envia los objetos por el canal de datos.
     */
	public void enviarObjetoPorCanalDatos(Socket s, Object dato)
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(dato);
			oos.flush(); 
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}

}
