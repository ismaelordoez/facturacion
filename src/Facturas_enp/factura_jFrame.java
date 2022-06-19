/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturas_enp;

import com.toedter.calendar.JCalendar;
import conexion.conexion_bdd;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.print.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ismael
 */
public class factura_jFrame extends javax.swing.JFrame {
JCalendar calendario = new JCalendar();
DefaultTableModel modelo = new DefaultTableModel();
String fechasqlalv = "";

    /**
     * Creates new form factura_jFrame
     */
    public factura_jFrame() {
        initComponents();
        cargartabla();
        cargarNfactura();
        //fecha_txtfield.setOpaque(false);
        guardarmodificacion_boton.setVisible(false);
        fecha_txtfield.setDateFormatString("dd/MM/yyyy");
        guardarfacturasistema_boton.setVisible(false);
    }
    
    private void cargartabla(){
        modelo.addColumn("Codigo ");
        modelo.addColumn("Medicamento ");
        modelo.addColumn("Cantidad ");
        modelo.addColumn("Valor Unitario ");
        modelo.addColumn("Total ");
        tablaprincipal.setShowGrid(false);
        tablaprincipal.setShowHorizontalLines(false);
        tablaprincipal.setShowVerticalLines(false);
        tablaprincipal.setOpaque(false);
        tablaprincipal.setModel(modelo);
        
    }
    
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
    
    
    private void agregarmedicamento(){
        String fecha, factura, nombre, paciente, ficha, medico, medicamento, cantidad, codigo, valor; 
        
       Date date = fecha_txtfield.getDate();
       
       long d = date.getTime();
       
       java.sql.Date fechasql = new java.sql.Date(d);
       
        System.out.println("la fecha es: "+fechasql);
        
        //fecha_txtfield.getDate().getYear()+"/"+fecha_txtfield.getDate().getMonth()+"/"+fecha_txtfield.getDate().getDay();
        //Recoleccion de datos
        fecha = "";
        factura = factura_txtfield.getText();
        nombre = nombre_txtfield.getText();
        paciente = paciente_txtfield.getText();
        ficha = ficha_txtfield.getText();
        medico = medico_txtfield.getText();
        medicamento = medicamento_txtfield.getText();
        cantidad = cantidad_txtfield.getText();
        codigo = codigo_txtfield.getText();
        valor = valormedicamento_txtfield.getText();        
        //Añadir los parametros al jTable
        Object [] fila = new Object[5];
        fila[0] = codigo;
        fila[1] = medicamento;
        fila[2] = cantidad;
        fila[3] = valor;
        fila[4] = Float.parseFloat(cantidad) * Float.parseFloat(valor);
        
        modelo.addRow(fila);
       
        //Calculo para ir agregando el valor total de la factura uwu
       // Object[] valores = new Object[tablaprincipal.getRowCount()];
        float canti = 0;
        float valuegg = 0;
        float total = 0;
        
        for(int a =0; a< tablaprincipal.getRowCount(); a++){
            
           //valores[a] =  tablaprincipal.getValueAt(a, 3);
           canti = Float.parseFloat(tablaprincipal.getValueAt(a,2).toString());
           valuegg = Float.parseFloat(tablaprincipal.getValueAt(a,3).toString());
           total += canti * valuegg;
        }
        
        totalfactura_label.setText(String.valueOf(total));
        
        medicamento_txtfield.setText("");
        cantidad_txtfield.setText("");
        codigo_txtfield.setText("");
        valormedicamento_txtfield.setText("");
    
    
    }
    
    private void cargarNfactura(){
        int nfac =0;
        
        try {
            Statement st = cnn.createStatement();
            ResultSet rs =st.executeQuery("select numero from factura order by numero desc");
            rs.next();
            nfac = rs.getInt("numero")+1;
            factura_txtfield.setText(String.valueOf(nfac));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void insertarBDD(){
    String fecha, facturan, nombre, paciente, ficha, medico, medicamento, codigo, cantidad, precio, totalmedicamento;  
    
    //Query para insertar primero la factura uwu
    String queryfacturas = "insert into factura values(?,?);";
    
    

    boolean siguiente = false;
        try {
            PreparedStatement ps = cnn.prepareStatement(queryfacturas);
            ps.setInt(1,Integer.valueOf(factura_txtfield.getText()));
            ps.setFloat(2,Float.parseFloat(totalfactura_label.getText()));
            ps.executeUpdate();
            siguiente = true;
                       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Hubo un error en la peticion MYSQL >>"+e.getMessage());
        }
        
        //Ahora insertamos los valores restantes uwu
   
String query = "insert into factura_sistema  values (?,?,?,?,?,?,?,?,?,?,?,?);";
//(fecha,factura,nombre,paciente,ficha,medico,medicamento,codigo,cantidad,precio,total medicamento)
    if(siguiente){
        try {
            PreparedStatement pss = cnn.prepareStatement(query);
            int fila = tablaprincipal.getRowCount();
            for(int s = 0 ; s < fila ; s++){
                pss.setString(1,fecha_label.getText());
                pss.setInt(2, Integer.valueOf(facturanumero_label.getText())); //nfactura
                pss.setString(3,nombre_txtfield.getText());
                pss.setString(4, paciente_txtfield.getText());
                pss.setString(5,ficha_txtfield.getText());
                pss.setString(6,medico_txtfield.getText());
                pss.setString(7,tablaprincipal.getValueAt(s, 1).toString()); //medicamento 
                pss.setString(8,tablaprincipal.getValueAt(s,0).toString()); //codigo
                pss.setInt(9, Integer.valueOf(tablaprincipal.getValueAt(s,2).toString())); //cantidad
                pss.setFloat(10,Float.parseFloat(tablaprincipal.getValueAt(s, 3).toString()));//precio
                pss.setFloat(11,Float.parseFloat(tablaprincipal.getValueAt(s,4).toString()));//total medicamento
                pss.setString(12, fecha_label.getText());
                
                pss.executeUpdate();
//fecha, facturan, nombre, paciente, ficha, medico, medicamento, codigo, cantidad, precio, totalmedicamento;                  
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Hubo un error en la peticion MYSQL #2 >>"+e.getMessage());
        }
    
    }else{
    JOptionPane.showMessageDialog(null,"No se puede proceder con la insercion de datos \n"
            + "consulte con el administrador su codigo fuente 7u7");
    }
    paciente_label.setText("");
    fecha_label.setText("");
    ficha_label.setText("");
    totalfactura_label.setText("");
    facturanumero_label.setText("");
    limpiarModeloTabla();
    cargarNfactura();
    
    }
    
    private void limpiarModeloTabla(){
        
        while(modelo.getRowCount() > 0){
            
            modelo.removeRow(0);
        
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
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
        imprimir_boton = new javax.swing.JButton();
        agregar_boton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombre_txtfield = new javax.swing.JTextField();
        medicamento_txtfield = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        paciente_txtfield = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        medico_txtfield = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        codigo_txtfield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cantidad_txtfield = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        valormedicamento_txtfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        factura_txtfield = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ficha_txtfield = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        eliminar_boton = new javax.swing.JButton();
        modificar_boton = new javax.swing.JButton();
        guardarmodificacion_boton = new javax.swing.JButton();
        guardarfacturasistema_boton = new javax.swing.JButton();
        fecha_txtfield = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        reporte_jmenu = new javax.swing.JMenu();
        verreporte_jmenu = new javax.swing.JMenuItem();
        eliminarregistros_jmenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(674, 630));
        setPreferredSize(new java.awt.Dimension(1280, 630));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tablaprincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tablaprincipal.setOpaque(false);
        jScrollPane1.setViewportView(tablaprincipal);

        factura.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 177, 619, 371));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("PLAN DE SERVICIOS MEDICOS ENP - SITRAENP");
        factura.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel12.setText("PACIENTE");
        factura.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        paciente_label.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        factura.add(paciente_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 240, 10));

        jLabel13.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel13.setText("FECHA");
        factura.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        fecha_label.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        factura.add(fecha_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 133, 10));

        jLabel14.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel14.setText("FICHA");
        factura.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        ficha_label.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        factura.add(ficha_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 103, 10));

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

        getContentPane().add(factura, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 656, 550));

        imprimir_boton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        imprimir_boton.setText("IMPRIMIR FACTURA");
        imprimir_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimir_botonActionPerformed(evt);
            }
        });
        getContentPane().add(imprimir_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, 25));

        agregar_boton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agregar_boton.setText("AGREGAR MEDICAMENTO");
        agregar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_botonActionPerformed(evt);
            }
        });
        getContentPane().add(agregar_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("NOMBRE");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("MEDICAMENTO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        nombre_txtfield.setBorder(null);
        nombre_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombre_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(nombre_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 188, 20));

        medicamento_txtfield.setBorder(null);
        medicamento_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                medicamento_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(medicamento_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 190, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("PACIENTE");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        paciente_txtfield.setBorder(null);
        paciente_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paciente_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(paciente_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 188, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("MEDICO");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        medico_txtfield.setBorder(null);
        medico_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                medico_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(medico_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 188, 21));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("CODIGO");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        codigo_txtfield.setBorder(null);
        codigo_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigo_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(codigo_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 190, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("FECHA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("CANTIDAD");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        cantidad_txtfield.setBorder(null);
        cantidad_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidad_txtfieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidad_txtfieldKeyTyped(evt);
            }
        });
        getContentPane().add(cantidad_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 120, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("VALOR MEDICAMENTO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        valormedicamento_txtfield.setBorder(null);
        valormedicamento_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valormedicamento_txtfieldKeyTyped(evt);
            }
        });
        getContentPane().add(valormedicamento_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 175, 22));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("FACTURA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        factura_txtfield.setBorder(null);
        factura_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                factura_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(factura_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 130, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("FICHA");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        ficha_txtfield.setBorder(null);
        ficha_txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ficha_txtfieldKeyPressed(evt);
            }
        });
        getContentPane().add(ficha_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 123, 20));

        jLabel18.setText("Ismael Simmons");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, -1, -1));

        eliminar_boton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminar_boton.setText("ELIMINAR MEDICAMENTO");
        eliminar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_botonActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 25));

        modificar_boton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        modificar_boton.setText("MODIFICAR MEDICAMENTO");
        modificar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificar_botonActionPerformed(evt);
            }
        });
        getContentPane().add(modificar_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, -1, 25));

        guardarmodificacion_boton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardarmodificacion_boton.setText("GUARDAR MODIFICACION");
        guardarmodificacion_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarmodificacion_botonActionPerformed(evt);
            }
        });
        getContentPane().add(guardarmodificacion_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, -1, 25));

        guardarfacturasistema_boton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardarfacturasistema_boton.setText("GUARDAR FACTURA EN EL SISTEMA");
        guardarfacturasistema_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarfacturasistema_botonActionPerformed(evt);
            }
        });
        getContentPane().add(guardarfacturasistema_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, 25));
        getContentPane().add(fecha_txtfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 180, -1));

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imprimir_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimir_botonActionPerformed
        // TODO add your handling code here:
       
        imprimir(factura);
        insertarBDD();
        limpiardatos();
    }//GEN-LAST:event_imprimir_botonActionPerformed

    private void agregar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_botonActionPerformed
        // TODO add your handling code here:
        Date date = fecha_txtfield.getDate();
       
       long d = date.getTime();
       java.sql.Date fechasql = new java.sql.Date(d);
       
        paciente_label.setText(paciente_txtfield.getText());
        ficha_label.setText(ficha_txtfield.getText());
        fecha_label.setText(fechasql.toString());

        facturanumero_label.setText(factura_txtfield.getText());
       
        
        agregarmedicamento();
        
    }//GEN-LAST:event_agregar_botonActionPerformed

    private void factura_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_factura_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      nombre_txtfield.requestFocus();
   }
    }//GEN-LAST:event_factura_txtfieldKeyPressed

    private void nombre_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      ficha_txtfield.requestFocus();
      paciente_txtfield.setText(nombre_txtfield.getText());
      
   }
    }//GEN-LAST:event_nombre_txtfieldKeyPressed

    private void ficha_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ficha_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      medico_txtfield.requestFocus();
   }
    }//GEN-LAST:event_ficha_txtfieldKeyPressed

    private void medico_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medico_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      medicamento_txtfield.requestFocus();
   }
    }//GEN-LAST:event_medico_txtfieldKeyPressed

    private void medicamento_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medicamento_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      cantidad_txtfield.requestFocus();
   }
    }//GEN-LAST:event_medicamento_txtfieldKeyPressed

    private void cantidad_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidad_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      codigo_txtfield.requestFocus();
   }
    }//GEN-LAST:event_cantidad_txtfieldKeyPressed

    private void codigo_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigo_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      valormedicamento_txtfield.requestFocus();
   }
    }//GEN-LAST:event_codigo_txtfieldKeyPressed
    public void generarReporte() throws net.sf.jasperreports.engine.JRException{
String ff = "C:\\Users\\ismael\\Desktop\\facturas_enp\\src\\Facturas_enp\\reporte.jasper";
            try {
        JasperReport reporte = (JasperReport) JRLoader.loadObject("src\\Facturas_enp\\reporte.jasper");
        
        Map parametros = new HashMap();
        
        parametros.put("fechainicio","01/07/2019");
        parametros.put("fechafinal","15/07/2019");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros, cnn);
        
        JasperViewer jasperview = new JasperViewer(jasperPrint,false);
        
        jasperview.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        jasperview.setVisible(true);
    } catch (JRException e) {
        e.printStackTrace();
    }
    
    
    
}
    private void verreporte_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verreporte_jmenuActionPerformed
    seleccionarmesreporte smr = new seleccionarmesreporte();
    
    smr.setVisible(true);
        
        /*
        try {
        // TODO add your handling code here:
        
        String fuente = "src\\Facturas_enp\\ReporteENP.jrxml";
        
        generarReporte();
    } catch (JRException ex) {
        Logger.getLogger(factura_jFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
   */
   
        
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

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        informacion inf = new informacion();
        this.dispose();
        inf.setVisible(true);
        inf.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void modificarregistro(){
        int filaseleccionada = tablaprincipal.getSelectedRow();
        
                
                //Insercion de datos en la tabla
                //object, row, column
                tablaprincipal.setValueAt(codigo_txtfield.getText(),filaseleccionada,0);
                tablaprincipal.setValueAt(medicamento_txtfield.getText(),filaseleccionada,1);
                tablaprincipal.setValueAt(cantidad_txtfield.getText(),filaseleccionada,2);
                tablaprincipal.setValueAt(valormedicamento_txtfield.getText(),filaseleccionada,3);
                int totalenlafila = Integer.valueOf(tablaprincipal.getValueAt(filaseleccionada, 2).toString()) *
                        Integer.valueOf(tablaprincipal.getValueAt(filaseleccionada,3).toString());
                tablaprincipal.setValueAt(totalenlafila,filaseleccionada, 4);
        
        guardarmodificacion_boton.setVisible(false);
        
    }
    
    private void eliminarmedicamento(){
        
        modelo = (DefaultTableModel) tablaprincipal.getModel();
        modelo.removeRow(tablaprincipal.getSelectedRow());
        int sinfilas = tablaprincipal.getRowCount();
        if(sinfilas == 0){
            totalfactura_label.setText("00.00");
        }
        
        int suma =0;
        for(int u = 0; u <tablaprincipal.getRowCount(); u++){
                
            suma += Integer.parseInt(tablaprincipal.getValueAt(u, 4).toString());
            totalfactura_label.setText(String.valueOf(suma));
            
        }   
        tablaprincipal.setModel(modelo);
    }
    
    public void limpiardatos(){
        fecha_txtfield.setCalendar(null);
        nombre_txtfield.setText("");
        ficha_txtfield.setText("");
        paciente_txtfield.setText("");
        medico_txtfield.setText("");
        medicamento_txtfield.setText("");
        cantidad_txtfield.setText("");
        codigo_txtfield.setText("");
        valormedicamento_txtfield.setText("");
    
    }
    private void modificar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar_botonActionPerformed
        // TODO add your handling code here:
        codigo_txtfield.setText(tablaprincipal.getValueAt(tablaprincipal.getSelectedRow(),0).toString());
        medicamento_txtfield.setText(tablaprincipal.getValueAt(tablaprincipal.getSelectedRow(),1).toString());
        cantidad_txtfield.setText(tablaprincipal.getValueAt(tablaprincipal.getSelectedRow(),2).toString());
        valormedicamento_txtfield.setText(tablaprincipal.getValueAt(tablaprincipal.getSelectedRow(),3).toString());
        
        guardarmodificacion_boton.setVisible(true);
    }//GEN-LAST:event_modificar_botonActionPerformed

    private void guardarmodificacion_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarmodificacion_botonActionPerformed
        // TODO add your handling code here:
        modificarregistro();
        
        int suma =0;
        for(int u = 0; u <tablaprincipal.getRowCount(); u++){
                
            suma += Integer.parseInt(tablaprincipal.getValueAt(u, 4).toString());
            totalfactura_label.setText(String.valueOf(suma));
            
        } 
        guardarmodificacion_boton.setVisible(false);
        
        /*
        int totalfilas = tablaprincipal.getRowCount();
        int suma =0;
        while(totalfilas>0){
            
            suma += Integer.parseInt(tablaprincipal.getValueAt(totalfilas, 4).toString());
        }
        
        totalfactura_label.setText(String.valueOf(suma));
        */
    }//GEN-LAST:event_guardarmodificacion_botonActionPerformed

    private void eliminar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_botonActionPerformed
        // TODO add your handling code here:
        eliminarmedicamento();
    }//GEN-LAST:event_eliminar_botonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        reimprimir ri = new reimprimir();
        this.dispose();
        ri.setVisible(true);
        ri.setLocationRelativeTo(null);
        ri.setTitle("FACTURACION RECETAS AUTORIZADAS A FARMACIA");
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void cantidad_txtfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidad_txtfieldKeyTyped
        // TODO add your handling code here:
        
        char caracter = evt.getKeyChar();
                    if (((caracter < '0') || (caracter > '9')) 
        && (caracter != KeyEvent.VK_BACK_SPACE)
        && (caracter != '.' || cantidad_txtfield.getText().contains(".")) ) {
            evt.consume();
            }
                
        
    }//GEN-LAST:event_cantidad_txtfieldKeyTyped

    private void valormedicamento_txtfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valormedicamento_txtfieldKeyTyped
        // TODO add your handling code here:
        
        char caracter = evt.getKeyChar();
                    if (((caracter < '0') || (caracter > '9')) 
        && (caracter != KeyEvent.VK_BACK_SPACE)
        && (caracter != '.' || valormedicamento_txtfield.getText().contains(".")) ) {
            evt.consume();
                    }       
    }//GEN-LAST:event_valormedicamento_txtfieldKeyTyped

    private void paciente_txtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paciente_txtfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // Enter was pressed. Your code goes here.
                medico_txtfield.requestFocus();
            }
        }
    }//GEN-LAST:event_paciente_txtfieldKeyPressed

    private void fecha_txtfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_txtfieldKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      medico_txtfield.requestFocus();
   }
    }//GEN-LAST:event_fecha_txtfieldKeyTyped

    private void guardarfacturasistema_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarfacturasistema_botonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_guardarfacturasistema_botonActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(factura_jFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new factura_jFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_boton;
    private javax.swing.JTextField cantidad_txtfield;
    private javax.swing.JTextField codigo_txtfield;
    private javax.swing.JButton eliminar_boton;
    private javax.swing.JMenuItem eliminarregistros_jmenu;
    private javax.swing.JPanel factura;
    private javax.swing.JTextField factura_txtfield;
    private javax.swing.JLabel facturanumero_label;
    private javax.swing.JLabel fecha_label;
    private com.toedter.calendar.JDateChooser fecha_txtfield;
    private javax.swing.JLabel ficha_label;
    private javax.swing.JTextField ficha_txtfield;
    private javax.swing.JButton guardarfacturasistema_boton;
    private javax.swing.JButton guardarmodificacion_boton;
    private javax.swing.JButton imprimir_boton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField medicamento_txtfield;
    private javax.swing.JTextField medico_txtfield;
    private javax.swing.JButton modificar_boton;
    private javax.swing.JTextField nombre_txtfield;
    private javax.swing.JLabel paciente_label;
    private javax.swing.JTextField paciente_txtfield;
    private javax.swing.JMenu reporte_jmenu;
    private javax.swing.JTable tablaprincipal;
    private javax.swing.JLabel totalfactura_label;
    private javax.swing.JTextField valormedicamento_txtfield;
    private javax.swing.JMenuItem verreporte_jmenu;
    // End of variables declaration//GEN-END:variables
conexion.conexion_bdd cb = new conexion_bdd();
Connection cnn = cb.conectar_a_la_bdd();
}
