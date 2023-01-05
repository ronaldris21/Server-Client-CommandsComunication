package edu.ucam.domain;

public class Coche {
	private String marca;
	private String matricula;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Coche(String marca, String matricula) {
		super();
		this.marca = marca;
		this.matricula = matricula;
	}
	
}
