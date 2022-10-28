package Utilidades;

import java.util.ArrayList;

import com.mxrck.autocompleter.TextAutoCompleter;

import DataType.Medio;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterInventario;

public class ValidarDatos {

    public static Integer actualizados() {
        int valor = 1;
        return valor;
    }

    public static void textomayuscula(java.awt.event.KeyEvent evt) {
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }

    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void cargarEtqTxt(TextAutoCompleter txtAutoC) {
        ArrayList<Medio> etq = new ArrayList<Medio>();
        InterInventario operacion = FabricaInterfaz.getInventario();
        etq = operacion.busquedaComboEtq();
        for (int i = 0; i < etq.size(); i++) {
            txtAutoC.addItem(etq.get(i).getEtiqueta());
        }

    }

    public static void cargarNumInvTxt(TextAutoCompleter txtAutoC) {
        ArrayList<Medio> numInv = new ArrayList<Medio>();
        InterInventario operacion = FabricaInterfaz.getInventario();
        numInv = operacion.busquedaComboNumInv();
        for (int i = 0; i < numInv.size(); i++) {
            txtAutoC.addItem(Integer.valueOf(numInv.get(i).getNumMedio()));
        }
    
    }

}
