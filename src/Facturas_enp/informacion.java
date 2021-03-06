/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturas_enp;

/**
 *
 * @author ismael
 */
public class informacion extends javax.swing.JFrame {

    /**
     * Creates new form informacion
     */
    public informacion() {
        initComponents();
        areadetexto.setLineWrap(true);
        areadetexto.setText("Los parametros de conexion son a una BDD MySQL."
                + "\n BDD: Facturas \t Tabla: factura && factura_sistema"
                + "\n Direccion ip: localhost"
                + " \n Puerto de Conexion: 3306"
                + "\n Uso de SSL: false"
                + "\n Uso de TimeZoner: serverUTC"
                + "\n Uso de legacy Date/time: false"
                + "\n Las conexiones quedan abiertas en cada una de las transacciones por lo"
                + " que se utiliza un unico tunel con tres diferentes querys para cada solicitud a la BDD."
                + "Dentro de la clases de conexion es fiable programar una funcion heredada para el cierre de las"
                + "conexiones, o utilizar la misma dentro de la clase conexion_bdd."
                + "Siendo el caso a necesitar objetos tipo Pool para multiples conexiones en simultaneo"
                + "\n\n Motor de BDD: InnoDB"
                + "\n No se utilizaron Store_procedures para la manipulacion de datos"
                + "\n PROPIETARIO CODIGO FUENTE: ISMAEL O. SIMMONS (2019)"
                + "\n\n ***CAMBIOS EN LA VERSION 1.0.08*** \n\n"
                + "Se añadio a la BDD la columna fecha_nueva para corregir el tratamiento de las fechas cuando se hacen "
                + "las consultas a la BDD con parametros between dates."
                + "\n se anadio una nueva JFrame que selecciona las fechas de inicio y fin de los reportes"
                + "\n se anadio elementos que anaden presentacion al reporte dentro del reporte"
                + "\n se han hecho validaciones en la entrada de datos de las facturas como ser los valores y total, para "
                + "que solo sea permitido el ingreso de valores numericos y un unico punto '.' "
                + "\n Se corrigieron los tipos de datos en la bdd de las columnas precio, cantidad y total donde anteriormente "
                + "eran tratadas como un varchar, para ser de tipo float cada una."
                + "\n Se anadio la libreria de JCalendar para montar elementos de tipo date como el JDateChooser en el ingreso de "
                + "la fechas en los JFrame de ingreso de facturas y la filtracion de las fechas del reporte."
                + "\n Se anadio la posibilidad de exportar los reportes a formato XLS de Excel con el conjunto de imagenes o logo del Plan Medico."
                + "\n Se corrigio el error al eliminar una fila de la tabla del ingreso de facturas donde no se actualizaba el valor"
                + "total de la factura. Igualmente se corrigio lo mismo con el boton de modificar"
                + "\n Se corrigio el error de calculo al modificar una entrada de medicamento que no se multiplicaba la cantidad"
                + " por el precio."
                + "\n Se anadio la posibilidad de hacer saltos a siguientes campos mediante el boton ENTER en cada campo de texto"
                + " del JFrame de ingreso de facturas ");
        areadetexto.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areadetexto = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Autor");

        jLabel2.setText("Desarrollador");

        jLabel3.setText("Ismael O. Simmons");

        jLabel4.setText("Ismael O. Simmons");

        jLabel5.setText("Version");

        jLabel6.setText("1.0.08");

        jLabel7.setText("Documentacion al codigo fuente");

        areadetexto.setColumns(20);
        areadetexto.setRows(5);
        jScrollPane1.setViewportView(areadetexto);

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(60, 60, 60)
                                .addComponent(jLabel3)))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel5)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        factura_jFrame fj = new factura_jFrame();
        fj.setVisible(true);
        fj.setLocationRelativeTo(null);
        fj.setTitle("FACTURACION RECETAS AUTORIZADAS A FARMACIA");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new informacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areadetexto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
