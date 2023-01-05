package edu.ucam.domain;

public class Finca {

	private int id;
	private String nombreFinca;
	private String duenoFinca;
	
	public Finca(int id, String nombreFinca, String duenoFinca) {
		this.id =  id;
		this.duenoFinca =  duenoFinca;
		this.nombreFinca = nombreFinca;
	}

	public Finca( String nombreFinca, String duenoFinca) {
		
		this.duenoFinca =  duenoFinca;
		this.nombreFinca = nombreFinca;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreFinca() {
		return nombreFinca;
	}

	public void setNombreFinca(String nombreFinca) {
		this.nombreFinca = nombreFinca;
	}

	public String getDuenoFinca() {
		return duenoFinca;
	}

	public void setDuenoFinca(String duenoFinca) {
		this.duenoFinca = duenoFinca;
	}
	
	
	public String toString()
	{
		return String.format("Finca %d: %s - %d",this.id, this.nombreFinca, this.duenoFinca );
	}

	
}
