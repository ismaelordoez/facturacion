/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturas_enp;

import conexion.conexion_bdd;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ismael
 */
public class reimprimir extends javax.swing.JFrame {
DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form reimprimir
     */
    public reimprimir() {
        initComponents();
        cargartabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        factura = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaprincipal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        paciente_label = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fecha_label = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ficha_label = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        totalfactura_label = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        facturanumero_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        imprimir_boton = new javax.swing.JButton();
        buscar_boton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        factura_textfield = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        reporte_jmenu = new javax.swing.JMenu();
        verreporte_jmenu = new javax.swing.JMenuItem();
        eliminarregistros_jmenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        factura.setBackground(new java.awt.Color(255, 255, 255));
        factura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factura.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tablaprincipal.setGridColor(new java.awt.Color(255, 255, 255));
        tablaprincipal.setOpaque(false);
        jScrollPane1.setViewportView(tablaprincipal);

        factura.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 177, 619, 371));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("PLAN DE SERVICIOS MEDICOS ENP - SITRAENP");
        factura.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel12.setText("PACIENTE");
        factura.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        paciente_label.setFont(new java.awt.Font("Arial Narrow", 0, 13)); // NOI18N
        factura.add(paciente_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 240, 20));

        jLabel13.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel13.setText("FECHA");
        factura.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        fecha_label.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        factura.add(fecha_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 133, 10));

        jLabel14.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel14.setText("FICHA");
        factura.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        ficha_label.setFont(new java.awt.Font("Arial Narrow", 0, 13)); // NOI18N
        factura.add(ficha_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 103, 20));

        jLabel15.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel15.setText("TOTAL FACTURA");
        factura.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        totalfactura_label.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        factura.add(totalfactura_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 115, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("FACTURA POR RECETAS DE MEDICAMENTOS PARA EMPLEADOS ENP");
        factura.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 400, -1));

        jLabel17.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel17.setText("FACTURA #");
        factura.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        facturanumero_label.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        factura.add(facturanumero_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 160, 20));

        jLabel3.setText("REIMPRESION");
        factura.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        imprimir_boton.setText("IMPRIMIR");
        imprimir_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimir_botonActionPerformed(evt);
            }
        });

        buscar_boton.setText("BUSCAR");
        buscar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_botonActionPerformed(evt);
            }
        });

        jLabel2.setText("FACTURA #");

        factura_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factura_textfieldActionPerformed(evt);
            }
        });

        reporte_jmenu.setText("REPORTE");

        verreporte_jmenu.setText("Ver reporte");
        verreporte_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verreporte_jmenuActionPerformed(evt);
            }
        });
        reporte_jmenu.add(verreporte_jmenu);

        eliminarregistros_jmenu.setText("Eliminar registros");
        eliminarregistros_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarregistros_jmenuActionPerformed(evt);
            }
        });
        reporte_jmenu.add(eliminarregistros_jmenu);

        jMenuBar1.add(reporte_jmenu);

        jMenu3.setText("FACTURAS");

        jMenuItem3.setText("REIMPRIMIR FACTURA");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("INGRESO FACTURAS");

        jMenuItem1.setText("INGRESAR FACTURAS ALV");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);
        jMenuBar1.add(jMenu2);

        jMenu1.setText("INFORMACION S/ PROGRAMA");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("ACERCA DE");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(imprimir_boton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(factura_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(buscar_boton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(factura_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(buscar_boton)
                        .addGap(162, 162, 162)
                        .addComponent(imprimir_boton)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imprimir(JPanel panel){
        
        PrinterJob pj = PrinterJob.getPrinterJob();
        
        pj.setJobName("Imprimir Factura");
        
        pj.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if(pageIndex > 0)
                {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D graphics2d = (Graphics2D)graphics;
                graphics2d.translate(pageFormat.getImageableX()*2,pageFormat.getImageableY()*2);
                graphics2d.scale(0.8, 0.8);
                
                factura.paint(graphics2d);
                return Printable.PAGE_EXISTS;
            }
        }); 
    
    boolean returningResult = pj.printDialog();
    if(returningResult){
        try {
            
            pj.print();
            
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null,"Error impresion :: "+ pe.getMessage());
        }
    
    }
    }
    private void verreporte_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verreporte_jmenuActionPerformed
        // TODO add your handling code here:
        
        seleccionarmesreporte smr = new seleccionarmesreporte();
        
        smr.setVisible(true);
        
    }//GEN-LAST:event_verreporte_jmenuActionPerformed

    private void eliminarregistros_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarregistros_jmenuActionPerformed
        // TODO add your handling code here:

        int resp = JOptionPane.showConfirmDialog(null,"¿REALMENTE DESEA ELIMINAR LOS REGISTROS DE LA BASE DE DATOS?");
        System.out.println("resp  "+resp);
        if(resp == 0){
            try {
                Statement st = cnn.createStatement();
                st.execute("delete from factura_sistema;");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_eliminarregistros_jmenuActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        informacion inf = new informacion();
        this.dispose();
        inf.setVisible(true);
        inf.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void imprimir_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimir_botonActionPerformed
        // TODO add your handling code here:
        imprimir(factura);
    }//GEN-LAST:event_imprimir_botonActionPerformed

    private void buscar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_botonActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        mostrarFactura();
        
        
    }//GEN-LAST:event_buscar_botonActionPerformed

    private void factura_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factura_textfieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_factura_textfieldActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        factura_jFrame fj = new factura_jFrame();
        fj.setVisible(true);
        fj.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public void limpiarTabla(){
    /* esta funcion limpia todos los datos de la tabla antes 
        para que al agregar nuevos no se dupliquen todos los valores
        */
    while(modelo.getRowCount()>0)modelo.removeRow(0);
    tablaprincipal.setModel(modelo);
    
    facturanumero_label.setText("");
    paciente_label.setText("");
    ficha_label.setText("");
    fecha_label.setText("");
    totalfactura_label.setText("");
    
    
    }
    private void cargartabla(){
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Medicamento");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Valor Unitario");
        modelo.addColumn("Total");
        tablaprincipal.setShowGrid(false);
        tablaprincipal.setShowHorizontalLines(false);
        tablaprincipal.setShowVerticalLines(false);
        tablaprincipal.setOpaque(false);
        tablaprincipal.setModel(modelo);
    }
    public void mostrarFactura(){
    int a = 0;
    int banderita = 0;
    String b = "";
    //try catch para obtener lo de la tabla factura
    try {
        Statement st = cnn.createStatement();
        
        ResultSet rs = st.executeQuery("select numero, format(valor,2) as valor from factura where numero = "+factura_textfield.getText()+";");
        
        if(rs.next()){
            
            a = rs.getInt("numero");
            b = String.valueOf(rs.getFloat("Valor"));
        }
        facturanumero_label.setText(String.valueOf(a));
        totalfactura_label.setText(String.valueOf(b));
        System.out.println(a);
        System.out.println(b);
        banderita = 1;
        st.close();
        
    } catch (SQLException e) {
        e.getMessage();
    }
    
    if(banderita == 1){
            //DATOS DE ENCABEZADO
        try {
            Statement ts = cnn.createStatement();
            ResultSet sr = ts.executeQuery("select fecha , paciente, ficha from factura_sistema where factura = "
            +factura_textfield.getText()+";");
            sr.next();
            String j ,k , l;
            j = sr.getString("paciente");
            paciente_label.setText(j);
            
            k = sr.getString("fecha");
            fecha_label.setText(k);
            
            l = sr.getString("ficha");
            ficha_label.setText(l);
            ts.close();
            
        } catch (SQLException e) {
            e.getMessage();
        }
        
        
        
        
        
        //DATOS DE LA TABLA
        try {
            Object[] fila = new Object[5];
            Statement ss = cnn.createStatement();
            
            //codigo,medicamento,cantidad,varlor unitario, total
ResultSet rr = ss.executeQuery("select codigo,medicamento, cantidad, round(precio+ 0.0000000001,2), format(total,2) "
        + "from factura_sistema where factura = "+factura_textfield.getText()+";");
            
         while(rr.next()){
             for(int q=0; q<5;q++){
                fila[q] = rr.getObject(q+1);
            } //final del for
            
            modelo.addRow(fila);
             
         } //final del while
         tablaprincipal.setModel(modelo);
            
        } catch (SQLException e) {
            e.getMessage();
        } //final del catch
       
    
    } // final del if
    
        

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reimprimir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar_boton;
    private javax.swing.JMenuItem eliminarregistros_jmenu;
    private javax.swing.JPanel factura;
    private javax.swing.JTextField factura_textfield;
    private javax.swing.JLabel facturanumero_label;
    private javax.swing.JLabel fecha_label;
    private javax.swing.JLabel ficha_label;
    private javax.swing.JButton imprimir_boton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel paciente_label;
    private javax.swing.JMenu reporte_jmenu;
    private javax.swing.JTable tablaprincipal;
    private javax.swing.JLabel totalfactura_label;
    private javax.swing.JMenuItem verreporte_jmenu;
    // End of variables declaration//GEN-END:variables
conexion.conexion_bdd cb = new conexion_bdd();
Connection cnn = cb.conectar_a_la_bdd(); 
}
