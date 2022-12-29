package edu.ucam.clients;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ucam.domain.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AnadirJugador extends JFrame {

	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txtapellidos;
	private Socket s;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnadirJugador frame = new AnadirJugador(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AnadirJugador getVista() {
		return this;
	}
	public Socket getSocket() {
		return this.s;
	}
	/**
	 * Create the frame.
	 */
	public AnadirJugador(Socket s ) {
		this.s= s;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 424, 248);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Añadir jugador");
		lblNewLabel.setBounds(165, 11, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(57, 72, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(52, 112, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Goles");
		lblNewLabel_3.setBounds(57, 154, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(113, 69, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtapellidos = new JTextField();
		txtapellidos.setBounds(113, 109, 86, 20);
		contentPane.add(txtapellidos);
		txtapellidos.setColumns(10);
		
		
		
		JSpinner spgoles = new JSpinner();
		spgoles.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spgoles.setBounds(136, 146, 46, 31);
		contentPane.add(spgoles);
		
		JButton btanadir = new JButton("Añadir");
		btanadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre= txtnombre.getText();
				String apellidos= txtapellidos.getText();
				int goles = spgoles.getComponentCount();
				if(txtnombre.getText().equals("")){
					System.out.println("Escribe el nombre");
					JOptionPane.showMessageDialog(getVista(), "Esribe el nombre");
					return;
				}
				if(txtapellidos.getText().equals("")) {
					System.out.println("Escribe los apellidos");
					JOptionPane.showMessageDialog(getVista(), "Esribe los apellidos");
					return;
				}
				
				/// validar appelido
				
				
				/// Crear el objeto del jugador
				Jugador j= new Jugador("",nombre, apellidos,goles);
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(j);
					oos.flush(); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				//Enviar datos al servidor
				//Cerrar la vista, sin que se cierre la aplicacion
				///Cerrar frame codigo
				dispose();
				
			}
		});
		btanadir.setBounds(266, 108, 89, 23);
		contentPane.add(btanadir);
	}
	
}
