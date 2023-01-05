package edu.ucam.clienteRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.domain.Cultivo;
import edu.ucam.domain.Finca;
import edu.ucam.domain.IFincasAdministrator;
import edu.ucam.parcial2.Concesionario;
import edu.ucam.serverRMI.FincasAdministrator;

public class ClienteRMI {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		IFincasAdministrator f = (FincasAdministrator) Naming.lookup("rmi://localhost:8080/Fincas");
		String op = "";
		Scanner teclado = new Scanner(System.in);
		String finca;
		int idFinca;
		String duenoFinca;
		String cultivo;
		int idCultivo;
		String precio;
		float presupuesto;
		Finca ff= null;
		Cultivo cc= null;
		do {
			System.out.println("*****MENU*****"
					+ "\n1- A�adir finca"
					+ "\n2- Borrar finca"
					+ "\n3- Actualizar finca"
					+ "\n4- Obtener finca por ID"
					+ "\n5- A�adir cultivo"
					+ "\n6- Borrar cultivo"
					+ "\n7- Actualizar cultivo"
					+ "\n8- Obtener cultivo por ID"
					+ "\n9-Salir"
					+"\nSeleccione una opcion:");
			op =  teclado.nextLine();
			
			
			switch (op) {
			case "1":
				System.out.println("Ingrese el nombre de la finca:");
				finca = teclado.nextLine();
				System.out.println("Ingrese el dueno de la finca:");
				duenoFinca = teclado.nextLine();
				ff= new Finca(finca, duenoFinca);
				System.out.println(f.addFinca(ff));
				break;
			case "2":
				System.out.println("Ingrese el ID de la finca:");
				idFinca = teclado.nextInt();
				
				System.out.println(f.deleteFinca(idFinca));
				break;
			case "3":
				System.out.println("Ingrese el id de la finca:");
				idFinca = teclado.nextInt();
				
				System.out.println(f.updateFinca(idFinca, ff));
				break;
			case "4":
				System.out.println("Ingrese la matricula del coche:");
				idFinca = teclado.nextInt();
				
				System.out.println(f.getFincabyID(idFinca));
				break;
			case "5":
				
				System.out.println("Para registrar un cultivo ingrese el ID  de la finca:");
				idFinca = teclado.nextInt();
				System.out.println("Ingrese el cultivo:");
				cultivo = teclado.nextLine();
				System.out.println("Ingrese el presupuesto:");
				presupuesto = teclado.nextFloat();
				cc= new Cultivo(idFinca, cultivo, presupuesto);
				System.out.println(f.addCultivoFinca(idFinca, cc));
				break;
			case "6":
				System.out.println("Para eliminar un cultivo ingrese el id del cultivo:");
				idCultivo = teclado.nextInt();
				
				System.out.println(f.removeCultivoFinca(idCultivo));
				break;
			case "7":
				System.out.println("Para modificar el cultivo ingrese el ID del cultivo:");
				idCultivo = teclado.nextInt();
				//System.out.println("Ingrese el precio de la venta:");
				//precio = teclado.nextLine();
				
				//System.out.println(c.modVenta(matricula, precio));
				break;
				
						}
	
			
			
		}while(!op.equals("9"));
		System.out.println("Hasta pronto");
	}
}
