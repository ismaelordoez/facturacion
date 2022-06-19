/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturas_enp;

import conexion.conexion_bdd;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author ismael
 */
public class verreporte extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form verreporte
     */
    public verreporte() {
        initComponents();
       //cargardatostabla();
        modelo.addColumn("FECHA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("FICHA");
        modelo.addColumn("FACTURA");
        modelo.addColumn("MEDICAMENTO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO");
       cargar_tabla();
       // cargarmodelotabla();
    }

    private void cargarmodelotabla(){
        modelo.addColumn("FECHA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("FICHA");
        modelo.addColumn("FACTURA");
        modelo.addColumn("MEDICAMENTO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO");
        
       // tablaprincipal.setShowGrid(false);
       // tablaprincipal.setShowHorizontalLines(false);
       // tablaprincipal.setShowVerticalLines(false);
       // tablaprincipal.setOpaque(false);
        tablaprincipal.setModel(modelo);
    }
    public void cargar_tabla(){
        /*
        Esta funcion carga todos los datos en la tabla tTipo de mysql 
        dentro del jtable del programa
        */       
        String query = "select fecha, nombre, ficha, factura, medicamento, cantidad, precio from factura_sistema;";
        try{
        Statement st;
        cnn = cb.conectar_a_la_bdd();  st = cnn.createStatement();
        ResultSet rs = st.executeQuery(query); Object[] objeto = new Object[7];
        
       
        while(rs.next()){
            
            for(int a=0; a<7 ;a++){
                //objeto[a] = rs.getObject(a+1);
                objeto[a] = rs.getString(a+1);
               /*
               objeto[a] = rs.getString("fecha");
               objeto[a] = rs.getString("nombre");
               objeto[a] = rs.getString("ficha");
               objeto[a] = rs.getInt("factura");
               objeto[a] = rs.getString("medicamento");
               objeto[a] = rs.getInt("cantidad");
               objeto[a] = rs.getFloat("precio");
                */
            }
            
                modelo.addRow(objeto);
        
        }
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Hubo un error con la base de datos ERROR: "+e.getMessage());
        
        }
       tablaprincipal.setModel(modelo);
       // tablaprincipal.getModel(modelo);
        
    
    }
    
    private void cargardatostabla(){
        
        Object[] obj = new Object[7];
        String query = "select fecha, nombre, ficha, factura, medicamento, cantidad, precio from factura_sistema;";
        try {
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                
                for(int a = 0 ; a < obj.length ; a++){
                    obj[a] = rs.getObject(a+1);
                    
                    System.out.println(obj[a]);
                }
                modelo.addRow(obj);
               
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        tablaprincipal.setModel(modelo);
             
        
    
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
        tablaprincipal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ingresofacturas_jmenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaprincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaprincipal);

        jLabel1.setText("Ismael Simmons");

        jMenu1.setText("Facturacion");

        ingresofacturas_jmenu.setText("Ingresar facturas");
        ingresofacturas_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresofacturas_jmenuActionPerformed(evt);
            }
        });
        jMenu1.add(ingresofacturas_jmenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Eliminar este reporte");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresofacturas_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresofacturas_jmenuActionPerformed
        // TODO add your handling code here:
        factura_jFrame fj = new factura_jFrame();
        this.dispose();
        fj.setVisible(true);
        fj.setLocationRelativeTo(null);
        fj.setTitle("FACTURACION RECETAS AUTORIZADAS A FARMACIA");
    }//GEN-LAST:event_ingresofacturas_jmenuActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(verreporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(verreporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(verreporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(verreporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new verreporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ingresofacturas_jmenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaprincipal;
    // End of variables declaration//GEN-END:variables
conexion.conexion_bdd cb = new conexion_bdd();
Connection cnn = cb.conectar_a_la_bdd();        
}