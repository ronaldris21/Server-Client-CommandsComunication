package edu.ucam.clients;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import edu.ucam.domain.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VistaPrincipalCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtAceptar;
	
	private Socket s;
	String comando;

	/**
	 * Launch the application.
	 */
	
	public void ejecutar()
	{
		try {
			this.s =  new Socket("localhost",5000);
			
			PrintWriter pw =  new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			
					
				} catch (Exception e) {} //En caso que el servidor cierre el socket!
			}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipalCliente frame = new VistaPrincipalCliente(null );
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipalCliente(Socket s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 JFrame frame = new JFrame("Demo");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Container container = frame.getContentPane();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextPane tpEnter = new JTextPane();
		tpEnter.setText("");
		tpEnter.setBounds(30, 124, 221, 126);
		contentPane.add(tpEnter);
	    SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
        StyleConstants.setBold(attributeSet, true); 
        JScrollPane scrollPane = new JScrollPane(tpEnter);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        container.add(scrollPane, BorderLayout.CENTER);
        contentPane.setSize(500, 300);
        contentPane.setVisible(true);
       // JScrollPane sp= new JScrollPane(tpEnter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // contentPane.add(sp);
		
		JButton btAceptar = new JButton("Aceptar");
		btAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
					comando = txtAceptar.getText();
					pw.println(comando);
					pw.flush();
					  
					   
					SimpleAttributeSet attributeSet1  = new SimpleAttributeSet();  
				        StyleConstants.setItalic(attributeSet1, true);  
				        StyleConstants.setForeground(attributeSet1, Color.green);  
				        
				        
				        Document doc = tpEnter.getStyledDocument();  
						  try {
							doc.insertString(doc.getLength(),  comando + "\n" , attributeSet1);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
					
					
					
				
			}
		});
		
	
		btAceptar.setBounds(294, 81, 89, 23);
		contentPane.add(btAceptar);
		
		
		
		
		txtAceptar = new JTextField();
		txtAceptar.setForeground(new Color(0, 128, 0));
		txtAceptar.setBounds(81, 82, 86, 20);
		contentPane.add(txtAceptar);
		txtAceptar.setColumns(10);
		
		JButton btEnter = new JButton("Enter");
		btEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
					comando = txtAceptar.getText();
					pw.println(comando);
					pw.flush();
					  
					   
					SimpleAttributeSet attributeSet2  = new SimpleAttributeSet();  
				        StyleConstants.setItalic(attributeSet2, true);  
				        StyleConstants.setForeground(attributeSet2, Color.black);  
				        
				        
				        Document doc1 = tpEnter.getStyledDocument();  
						  try {
							doc1.insertString(doc1.getLength(),  comando + "\n" , attributeSet2);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btEnter.setBounds(294, 177, 89, 23);
		contentPane.add(btEnter);
		
		
	}
}
