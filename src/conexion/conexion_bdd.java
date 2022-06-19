/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ismael
 */
public class conexion_bdd {
    Connection con;
    
    public Connection conectar_a_la_bdd(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
            String usuario = "root";
            String contrasena = "Macbook2011";
            String url = "jdbc:mysql://localhost:3306/facturas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
            
            con = DriverManager.getConnection(url, usuario, contrasena);
           // JOptionPane.showMessageDialog(null,"La conexion fue totalmente exitosa");
            
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"La conexion fallo error en/#:: "+e.getMessage());
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(conexion_bdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return con;
    }
    
    public Connection cerrar_conexion(){
        
        try {
            con.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(conexion_bdd.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error en>> "+ex.getMessage());
        }
        return con;
    }
    
}
