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
import java.io.PrintWriter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;


public class MainFrame extends JFrame {

	/**
	 * 
	 *  
	 * <p>
	*Esta es la vista principal de la aplicacion.  
	*</p>
	*/
	
	public MainFrame getVista()
	{
		return this;
	}
	
	
	private static final long serialVersionUID = -4437042091596383165L;

	//Atributes
	private JPanel contentPane;
	private PrintWriter pw;
	
	private int counterCommand;

	private ClientApp cliente;

	private JTextPane txtConsolaTextPane;

	private JScrollPane scrollPane_1,scrolltxtComandosAEnviar;

	private JTextPane txtComandosAEnviar; 

	//Constructor
	/**
	 * 
	 * @param pw
	 * En ella se podran elegir los comandos visualmente o
	*escribirlos por teclado. Ademas se escribiran por pantalla todos los comandos usados y tambien se pueden ver las
	*las respuestas del servidor.
	 */
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
	            		System.exit(0);
	        		}
	        		catch(Exception t) {
	        		}
	            }
	        });
		
		setLocationRelativeTo(null);
		
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 665);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
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
		
		txtConsolaTextPane = new JTextPane();
		txtConsolaTextPane.setEditable(false);
		scrollPane_1 = new JScrollPane(txtConsolaTextPane);
		
		JButton btnEnviarComandos = new JButton("Enviar");
		btnEnviarComandos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnEnviarComandos.setEnabled(false);
				String[] lineasComandos = txtComandosAEnviar.getText().trim().split("\n") ;
				for(String linea : lineasComandos)
				{
					System.out.println("Linea: "+linea);
					if(!linea.trim().equals(""))
					{
						enviarComandoServidor(linea.trim());
						scrolltxtComandosAEnviar.repaint();
					}
						
				}
				btnEnviarComandos.setEnabled(true);
				txtComandosAEnviar.setText("");
				
			}
		});
		
		txtComandosAEnviar = new JTextPane();
		scrolltxtComandosAEnviar =  new JScrollPane(txtComandosAEnviar);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrolltxtComandosAEnviar, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEnviarComandos)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrolltxtComandosAEnviar, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnviarComandos, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
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
		try {
			//Thread.sleep()
			Thread.sleep(2000); ///Le doy tiempo suficiente para que funcione bien
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		this.pw.println(comando);
		this.pw.flush();
		
		
	}
	
	//TODO: METODO para mostrar lo que se recibe desde el socket
	public void escribirTextoConsolaVisual(String mensaje, Color color)
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
		vertical.setValue( vertical.getMaximum() +10);
		txtConsolaTextPane.repaint();
	  
	}
	
	/**
	 * 
	 * @param args
	 * Ejecuta la aplicacion en modo visual
	 */
	public static void main(String[] args) {
		(new ClientApp()).ejecutar();

	}
}

