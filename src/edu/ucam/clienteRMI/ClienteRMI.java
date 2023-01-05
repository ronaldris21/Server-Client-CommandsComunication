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
		IFincasAdministrator service = (IFincasAdministrator) Naming.lookup("rmi://localhost:8080/FincasService");
		System.out.println(service.hola());
		
		
		String op = "";
		Scanner teclado = new Scanner(System.in);
		String respuesta;
		int id;
		float presupuesto;
		boolean esCorrecto=false;
		
		
		do {
			Finca f = null;
			Cultivo c = null;
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
			
			

			///preguntas
			///lees
			///validas condiciones-tipo de dato
			///asignas
			switch (op) {
			
			case "1": //validado
				f= new Finca();
				do
				{
					System.out.println("Ingrese el nombre de la finca:");
					respuesta = teclado.nextLine();
				}while(respuesta!=null);
				f.setNombreFinca(respuesta);
				
				
				do
				{
					System.out.println("Ingrese el dueno de la finca:");
					respuesta = teclado.nextLine();
				}while(respuesta!=null);
				f.setDuenoFinca(respuesta);
				
				
				System.out.println(service.addFinca(f));
				
				break;
			case "2": 
				System.out.println("Ingrese el ID de la finca:");
				idFinca = teclado.nextInt();
				
				System.out.println(service.deleteFinca(idFinca));
				break;
			case "3": //validado
				f =  new Finca();
				///Para leer enteros!
				esCorrecto=false;
				do
				{
					try {
						System.out.println("Ingrese el id de la finca:");
						id = Integer.parseInt(teclado.nextLine());
						esCorrecto=true;
					} catch (Exception e) {
					}
				}while(esCorrecto==false);
				f.setDuenoFinca(respuesta);
				
				do
				{
					System.out.println("Ingrese el nombre de la finca:");
					respuesta = teclado.nextLine();
				}while(respuesta!=null);
				f.setNombreFinca(respuesta);
				
				do
				{
					System.out.println("Ingrese el dueno de la finca:");
					respuesta = teclado.nextLine();
				}while(respuesta!=null);
				f.setDuenoFinca(respuesta);
				System.out.println(service.updateFinca(id, f));
				break;
			case "4":
				System.out.println("Ingrese la matricula del coche:");
				idFinca = teclado.nextInt();
				
				System.out.println(service.getFincabyID(idFinca));
				break;
			case "5":
				
				System.out.println("Para registrar un cultivo ingrese el ID  de la finca:");
				idFinca = teclado.nextInt();
				System.out.println("Ingrese el cultivo:");
				cultivo = teclado.nextLine();
				System.out.println("Ingrese el presupuesto:");
				presupuesto = teclado.nextFloat();
				cc= new Cultivo(idFinca, cultivo, presupuesto);
				System.out.println(service.addCultivoFinca(idFinca, cc));
				break;
			case "6":
				System.out.println("Para eliminar un cultivo ingrese el id del cultivo:");
				idCultivo = teclado.nextInt();
				
				System.out.println(service.removeCultivoFinca(idCultivo));
				break;
			case "7":
				System.out.println("Para modificar el cultivo ingrese el ID del cultivo:");
				idCultivo = teclado.nextInt();
				System.out.println("Ingrese el ID de la finca:");
				idFinca = teclado.nextInt();
				System.out.println("Ingrese el cultivo:");
				cultivo = teclado.nextLine();
				System.out.println("Ingrese el presupuesto:");
				presupuesto = teclado.nextFloat();
				cc= new Cultivo(idFinca, cultivo, presupuesto);
				System.out.println(service.updateCultivoFinca(idCultivo, cc));
				break;
			case "8":
				System.out.println("Para consultar el cultivo ingrese el ID  del cultivo:");
				idCultivo = teclado.nextInt();
				System.out.println(service.getCultivobyId(idCultivo));
				break;
						}
	
		}while(!op.equals("9"));
		System.out.println("Hasta pronto");
	}
}
