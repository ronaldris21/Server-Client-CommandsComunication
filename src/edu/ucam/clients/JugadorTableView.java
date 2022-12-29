package edu.ucam.clients;



import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

import edu.ucam.domain.Jugador;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;



public class JugadorTableView extends javax.swing.JFrame {


    ArrayList<Jugador> lista = new ArrayList<>();
    
    public JugadorTableView(ArrayList<Jugador> data) {
        initComponents();
        
        lista = data;
        DefaultTableModel model = new DefaultTableModel();
        this.mostrarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableView = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableView.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null},
        	},
        	new String[] {
        		"Id", "Nombre", "Apellido", "Goles"
        	}
        ));
        jTableView.getColumnModel().getColumn(0).setMaxWidth(75);
        jTableView.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTableView.getColumnModel().getColumn(2).setResizable(false);
        jTableView.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTableView.getColumnModel().getColumn(2).setMaxWidth(825252);
        jTableView.getColumnModel().getColumn(3).setMaxWidth(75);
        jScrollPane1.setViewportView(jTableView);
        
        JLabel lblNewLabel = new JLabel("JUGADORES REGISTRADOS");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(244)
        					.addComponent(lblNewLabel)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addContainerGap(12, Short.MAX_VALUE)
        			.addComponent(lblNewLabel)
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mostrarDatos(){
    	
    	
    	 DefaultTableModel model = (DefaultTableModel) this.jTableView.getModel(); 
    	
    	 if(lista!=null)
        for (Jugador o : lista) {
        	
        	model.addRow(
        			new Object[]
    					{
    							o.getId(),
    							o.getNombre(),
    							o.getApellidos(),
    							o.getGoles()
    					});
        }
        jTableView.setModel(model );
               
    }
            
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        	
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JugadorTableView(null).setVisible(true);
            }
        });
    }
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableView;
}