package edu.ucam.dad1.models;

public class Usuario {
	private String name;
	private String pass;
	
	
	public Usuario() {
		
	}
	public Usuario(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}

}
