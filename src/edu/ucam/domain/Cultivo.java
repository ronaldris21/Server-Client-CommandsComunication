package edu.ucam.domain;

public class Cultivo {
	
	private int id;
	private int idFinca;
	private String nombreCultivo;
	private float presupuesto;

	public Cultivo(int id, int idFinca, String nombreCultivo, float presupuesto) {
		this.id = id;
		this.idFinca = idFinca;
		this.nombreCultivo = nombreCultivo;
		this.presupuesto =  presupuesto;
		
	}
	public Cultivo( int idFinca, String nombreCultivo, float presupuesto) {
	
		this.idFinca = idFinca;
		this.nombreCultivo = nombreCultivo;
		this.presupuesto =  presupuesto;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdFinca() {
		return idFinca;
	}

	public void setIdFinca(int idFinca) {
		this.idFinca = idFinca;
	}

	public String getNombreCultivo() {
		return nombreCultivo;
	}

	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}

	public float getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(float presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public String toString()
	{
		return String.format("Cultivo %d: %s - %f EUR (Finca %d)",this.id, this.nombreCultivo, this.presupuesto, this.idFinca);
	}

}
