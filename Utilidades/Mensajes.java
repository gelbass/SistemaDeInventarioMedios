package Utilidades;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Exepciones.Exepciones;

public class Mensajes {
	
	public static void errorDB(SQLException e){
        JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void errorDB(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void errorParsear(NumberFormatException e){
        JOptionPane.showMessageDialog(null, "Ingrese valor numerico", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void valornulo(Exepciones e){
        JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void confirmacion(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void errorGenerico(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "INFORMACION", JOptionPane.ERROR_MESSAGE);
    }

    
    public static void valornulo(NullPointerException e) {
        JOptionPane.showMessageDialog(null, "Ingrese algun critero para realizar la busqueda", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
