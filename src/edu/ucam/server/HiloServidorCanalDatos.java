package edu.ucam.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidorCanalDatos extends Thread {

	
	
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	public HiloServidorCanalDatos(Socket socket, BufferedReader br, PrintWriter pw) {
		this.socket = socket;
		this.br = br;
		this.pw = pw;
	}

	public void run()
	{
		
		
		
		
		
	}
}
