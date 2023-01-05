package edu.ucam.clients;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	
	public MainFrame getVista()
	{
		return this;
	}
	
	private static final long serialVersionUID = -4437042091596383165L;

	//Atributes
	private JPanel contentPane;
	private PrintWriter pw;
	JTextPane txtConsolaTextPane;
	JScrollPane scrollPane_1;
	
	private int counterCommand;

	private ClientApp cliente; 

	//Constructor
	public MainFrame(PrintWriter pw, ClientApp cliente) {
        counterCommand = 0;
        this.pw = pw;
        this.cliente = cliente;
        
        
		 addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent evt) {
	            	try {
	            		enviarComandoServidor("ClienteApp EXIT");
	        			//clientThreadCommands.setSuspended(true); //TODO: CHECK
	            		getVista().dispose();
	        		}
	        		catch(Exception t) {
	        		}
	            }
	        });
		
		setLocationRelativeTo(null);
		
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 665);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(3);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
		);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnPacientes = new JMenu("Sistema");
		menuBar.add(mnPacientes);
		
		JMenuItem mntmSistemaConectador = new JMenuItem("Conectados");
		mntmSistemaConectador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				//TODO: Conectados
			}
		});
		
		JMenuItem mntmSistemaSesiones = new JMenuItem("Sesiones");
		mntmSistemaSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//<number> SESIONES
				enviarComandoServidor(++counterCommand+" SESIONES");
			}
		});
		mnPacientes.add(mntmSistemaSesiones);
		mnPacientes.add(mntmSistemaConectador);
		
		JMenuItem mntmSistemaExit = new JMenuItem("Exit");
		mntmSistemaExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> EXIT
				enviarComandoServidor(++counterCommand + " EXIT");
			}
		});
		mnPacientes.add(mntmSistemaExit);
		
		JMenu mnMedicos = new JMenu("Clubes");
		menuBar.add(mnMedicos);
		
		JMenuItem mntmClubAnadir = new JMenuItem("A\u00F1adir");
		mntmClubAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//<number> ADDCLUB
				enviarComandoServidor(++counterCommand + " ADDCLUB");
			}
		});
		mnMedicos.add(mntmClubAnadir);
		
		JMenuItem mntmClubActualizar = new JMenuItem("Actualizar");
		mntmClubActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> UPDATECLUB <id>
				String idClub = JOptionPane.showInputDialog("Introduce el id del club a ACTUALIZAR: ");
				enviarComandoServidor(++counterCommand + " UPDATECLUB "+idClub);
			}
		});
		mnMedicos.add(mntmClubActualizar);
		
		JMenuItem mntmClubMostrar = new JMenuItem("Mostrar");
		mntmClubMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//<number> GETCLUB <id>
				String idClub = JOptionPane.showInputDialog("Introduce el id del club a MOSTRAR: ");
				enviarComandoServidor(++counterCommand + " GETCLUB "+idClub);
				
			}
		});
		mnMedicos.add(mntmClubMostrar);
		
		JMenuItem mntmClubEliminar = new JMenuItem("Eliminar");
		mntmClubEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//<number> REMOVECLUB <id>
				String idClub = JOptionPane.showInputDialog("Introduce el id del club a ELIMINAR: ");
				enviarComandoServidor(++counterCommand + " REMOVECLUB "+idClub);
			}
		});
		
		mnMedicos.add(mntmClubEliminar);
		
		JMenuItem mntmClubListar = new JMenuItem("Listar");
		mntmClubListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//number> LISTCLUBES
				enviarComandoServidor(++counterCommand + " LISTCLUBES");
			}
		});
		mnMedicos.add(mntmClubListar);
		
		JMenuItem mntmClubContar = new JMenuItem("Contar");
		mntmClubContar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> COUNTCLUBES
				enviarComandoServidor(++counterCommand + " COUNTCLUBES");
			}
		});
		mnMedicos.add(mntmClubContar);
		
		JMenu mnTratamientos = new JMenu("Jugadores");
		menuBar.add(mnTratamientos);
		
		JMenuItem mntmJugadorAnadir = new JMenuItem("A\u00F1adir");
		mntmJugadorAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//<number> ADDJUGADOR
				enviarComandoServidor(++counterCommand + " ADDJUGADOR");
			}
		});
		mnTratamientos.add(mntmJugadorAnadir);
		
		JMenuItem mntmJugadorAgregarJugadorClub = new JMenuItem("Agregar Jugador a Club");
		mntmJugadorAgregarJugadorClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> ADDJUGADOR2CLUB <idjugador> <idclub>
				
				String idJugador = JOptionPane.showInputDialog("Introduce el id del jugador: ");
				String idClub = JOptionPane.showInputDialog("Introduce el id del club: ");
				enviarComandoServidor(++counterCommand + " ADDJUGADOR2CLUB "+idJugador+" "+idClub);
			}
		});
		
		JMenuItem mntmJugadorMostrar = new JMenuItem("Mostrar");
		mntmJugadorMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> GETJUGADOR <id>
				String id = JOptionPane.showInputDialog("Introduce el id del jugador a MOSTRAR: ");
				enviarComandoServidor(++counterCommand + " GETJUGADOR "+id);
			}
		});
		mnTratamientos.add(mntmJugadorMostrar);
		
		JMenuItem mntmJugadorEliminar = new JMenuItem("Eliminar");
		mntmJugadorEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//<number> REMOVEJUGADOR <id>
				String id = JOptionPane.showInputDialog("Introduce el id del jugador a ELIMINAR: ");
				enviarComandoServidor(++counterCommand + " REMOVEJUGADOR "+id);
			}
		});
		mnTratamientos.add(mntmJugadorEliminar);
		
		JMenuItem mntmJugadorListar = new JMenuItem("Listar");
		mntmJugadorListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> LISTJUGADORES
				enviarComandoServidor(++counterCommand + " LISTJUGADORES");
			}
		});
		mnTratamientos.add(mntmJugadorListar);
		mnTratamientos.add(mntmJugadorAgregarJugadorClub);
		
		JMenuItem mntmJugadorEliminarJugadorClub = new JMenuItem("Eliminar Jugador de un Club");
		mntmJugadorEliminarJugadorClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//<number> REMOVEJUGFROMCLUB <idjugador> <idclub>
				String idJugador = JOptionPane.showInputDialog("Introduce el id del jugador: ");
				String idClub = JOptionPane.showInputDialog("Introduce el id del club: ");
				enviarComandoServidor(++counterCommand + " REMOVEJUGFROMCLUB "+idJugador+" "+idClub);
			}
		});
		mnTratamientos.add(mntmJugadorEliminarJugadorClub);
		
		JMenuItem mntmJugadorListarJugadoresClub = new JMenuItem("Listar jugadores de un club");
		mntmJugadorListarJugadoresClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//<number> LISTJUGFROMCLUB <idjugador>
				String idClub = JOptionPane.showInputDialog("Introduce el id del club: ");
				enviarComandoServidor(++counterCommand + " LISTJUGFROMCLUB "+idClub);
			}
		});
		mnTratamientos.add(mntmJugadorListarJugadoresClub);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panelData = new JPanel();
		tabbedPane.addTab("Data", null, panelData, null);
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_panelData = new GroupLayout(panelData);
		gl_panelData.setHorizontalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
		);
		gl_panelData.setVerticalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
		);
		
		txtConsolaTextPane = new JTextPane();
		scrollPane_1.setViewportView(txtConsolaTextPane);
		panelData.setLayout(gl_panelData);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Log", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
		);
		
		JEditorPane editorPaneLog = new JEditorPane();
		scrollPane.setViewportView(editorPaneLog);
		panel.setLayout(gl_panel);
		
		
		splitPane.setLeftComponent(desktopPane);
		splitPane.setDividerLocation(350);
		contentPane.setLayout(gl_contentPane);
	}

	
	
	
	//Getters & Setters
	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}

	

	///TODO: ver esto del hilo de comando de escucha
	public synchronized void enviarComandoServidor(String comando)
	{
		this.cliente.comando = comando;
		this.escribirTextoConsolaVisual(comando, Color.blue); 
		this.pw.println(comando);
		this.pw.flush();
		
		try {
			Thread.sleep(500); ///Le doy tiempo suficiente para que funcione bien
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	//TODO: METODO para mostrar lo que se recibe desde el socket
	public synchronized void escribirTextoConsolaVisual(String mensaje, Color color)
	{
		SimpleAttributeSet attributeSet1  = new SimpleAttributeSet();  
	    StyleConstants.setItalic(attributeSet1, true);  
	    StyleConstants.setForeground(attributeSet1, color);  
	    
	    
	    Document doc = txtConsolaTextPane.getStyledDocument();  
		  try {
			doc.insertString(doc.getLength(),  mensaje + "\n" , attributeSet1);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		  

		  JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
		  vertical.setValue( vertical.getMaximum() );
		  
		  
	  
	}
	

}

