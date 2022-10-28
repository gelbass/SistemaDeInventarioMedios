package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenciaCBX {

	public static ArrayList<String> cargarTipoMedio(){
		
		Connection con = null;
        ResultSet resultado = null;
        Statement stm = null;
        ArrayList<String> combo = new ArrayList<>();
        //String consulta = ;

        try {
            //rs = stm.executeQuery(consulta);
            con = RecursosDB.obtenerConexion();
            stm = con.createStatement();
            resultado = stm.executeQuery("SELECT tipo FROM tipoMedio;");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCBX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                try {
            while (resultado.next()) {
                combo.add(resultado.getString("tipo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCBX.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		return combo;
	}
	
	public static ArrayList<String> cargarUbicacion(){
		Connection con = null;
        ResultSet resultado = null;
        Statement stm = null;
        ArrayList<String> combo = new ArrayList<>();
        //String consulta = ;

        try {
            //rs = stm.executeQuery(consulta);
            con = RecursosDB.obtenerConexion();
            stm = con.createStatement();
            resultado = stm.executeQuery("SELECT ubicacion FROM ubicacion;");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCBX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                try {
            while (resultado.next()) {
                combo.add(resultado.getString("ubicacion"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCBX.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		return combo;
	}
	
	public static ArrayList<String> cargarStatus(){
		Connection con = null;
        ResultSet resultado = null;
        Statement stm = null;
        ArrayList<String> combo = new ArrayList<>();
        //String consulta = ;

        try {
            //rs = stm.executeQuery(consulta);
            con = RecursosDB.obtenerConexion();
            stm = con.createStatement();
            resultado = stm.executeQuery("SELECT st FROM status;");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCBX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                try {
            while (resultado.next()) {
                combo.add(resultado.getString("st"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaCBX.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		return combo;
	}
}
