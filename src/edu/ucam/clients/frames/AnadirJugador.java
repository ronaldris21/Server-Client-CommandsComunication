package edu.ucam.clients.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ucam.domain.Jugador;
import edu.ucam.domain.ObjetosPorSocket;

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

/**
 * <p>
*Esta es la clase que genera la vista en la cual se puede anadir a un jugador
*</p>
*/
public class AnadirJugador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txtapellidos;
	private Socket s;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/**
			 * 	Se creal el frame
			 */
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
 * 
 * @param s
 * Se ponen el nombre del jugador, el apellido y el numero de goles.
 *  Despues se le da al boton de aceptar y se guarda en la lista de 
 * jugadores
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
				int goles = Integer.parseInt( spgoles.getValue() .toString());///TODO, CHECK THIS count goles
				///Valida nombres y apellidos
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
				
				/// Crear el objeto del jugador
				Jugador j= new Jugador("",nombre, apellidos,goles);
				(new ObjetosPorSocket<Jugador>()).enviarObjetoPorCanalDatos(s, j);
				
				dispose();
				
			}
		});
		btanadir.setBounds(266, 108, 89, 23);
		contentPane.add(btanadir);
	}
	
}
