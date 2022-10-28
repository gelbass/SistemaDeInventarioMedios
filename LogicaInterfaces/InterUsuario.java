package LogicaInterfaces;

import java.util.Date;
import java.util.ArrayList;

import DataType.Actividad;
import DataType.Medio;
import DataType.Usuarios;

public interface InterUsuario {

	boolean login (Usuarios usuario);
	Usuarios conectado(Usuarios usuario);
	Usuarios activo(String valor);
	Integer nuevo(Usuarios usuario);
	String cambiarContreasena(Usuarios usuario);
	boolean validarUsuario(String valor);
	Integer addActividad(Usuarios usuario, Medio medio);
	ArrayList<Actividad> busquedaActNumInv(int valor);
	ArrayList<Actividad> busquedaActEtq(String valor);
	ArrayList<Actividad> busquedaActUsurio(String valor);
	ArrayList<Actividad> busquedaFecha(Date desde, Date hasta);
	ArrayList<String> comboUsuario();
}
