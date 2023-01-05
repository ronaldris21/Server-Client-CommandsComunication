package edu.ucam.parcial2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ConcesionarioCliente {
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		Concesionario c = (Concesionario) Naming.lookup("rmi://localhost:8080/ConcesionarioService");
		String op = "";
		Scanner teclado = new Scanner(System.in);
		String matricula;
		String marca;
		String precio;
		do {
			System.out.println("*****MENU*****"
					+ "\n1- A�adir coche"
					+ "\n2- Modificar coche"
					+ "\n3- Obtener coche"
					+ "\n4- Eliminar coche"
					+ "\n5- A�adir venta"
					+ "\n6- Modificar venta"
					+ "\n7- Obtener venta"
					+ "\n8- Eliminar venta"
					+ "\n9-Salir"
					+"\nSeleccione una opcion:");
			op =  teclado.nextLine();
			
			switch (op) {
			case "1":
				System.out.println("Ingrese la marca del coche:");
				marca = teclado.nextLine();
				System.out.println("Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				
				System.out.println(c.addCoche(matricula, marca));
				break;
			case "2":
				System.out.println("Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				System.out.println("Ingrese la marca del coche:");
				marca = teclado.nextLine();
				
				System.out.println(c.modCoche(matricula, marca));
				break;
			case "3":
				System.out.println("Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				
				System.out.println(c.getCoche(matricula));
				break;
			case "4":
				System.out.println("Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				
				System.out.println(c.delCoche(matricula));
				break;
			case "5":
				
				System.out.println("Para registrar una venta Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				System.out.println("Ingrese el precio de la venta:");
				precio = teclado.nextLine();
				System.out.println(c.addVenta(matricula, precio));
				break;
			case "6":
				System.out.println("Para modificar la venta Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				System.out.println("Ingrese el precio de la venta:");
				precio = teclado.nextLine();
				
				System.out.println(c.modVenta(matricula, precio));
				break;
			case "7":
				System.out.println("Para consultar la venta Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				
				System.out.println(c.getVenta(matricula));
				break;
			case "8":
				System.out.println("Para eliminar la venta Ingrese la matricula del coche:");
				matricula = teclado.nextLine();
				
				System.out.println(c.delVenta(matricula));
				break;
			}
			
		}while(!op.equals("9"));
		System.out.println("Hasta pronto");
	}
	
	
}
