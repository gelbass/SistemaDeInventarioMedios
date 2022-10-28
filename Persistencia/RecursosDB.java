package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import net.ucanaccess.jdbc.UcanaccessDriver;

import Exepciones.ExepcionDB;

//@SuppressWarnings("unused")
public class RecursosDB { 
	
	static String filepath = "D:\\Proyectos_Java\\dbInventarioMedios\\dbInventarioMedios.accdb";

	static String dburl = "jdbc:ucanaccess://" + filepath;
		
	public static Connection obtenerConexion() throws ExepcionDB{
        Connection con = null;
        try {
            con = DriverManager.getConnection(dburl);
            return con;
        } catch (SQLException e) {
            throw new ExepcionDB("Error al conectar con la Base de Datos");
	        }
		//return con;   
    }
	
	public static void liberarRecursos(Statement stm,Connection con){
        liberarRecurso(stm);
        liberarRecurso(con);
    }
   public static void liberarRecuros(ResultSet rs,Statement stm){
       liberarRecurso(rs);
       liberarRecurso(stm);
   }
    public static void liberarRecuros(Statement stm){
       liberarRecurso(stm);
   }
    
    public static void liberarRecursos(ResultSet rs,Statement stm,Connection con){
        liberarRecurso(rs);
        liberarRecurso(stm);
        liberarRecurso(con);
    }
    
    public static void liberarRecurso(ResultSet rs){
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursosDB.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        } 
     }
    
    public static void liberarRecurso(Statement stm){
        if (stm!=null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursosDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void liberarRecurso(Connection con){
        if (con!=null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecursosDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	
}

