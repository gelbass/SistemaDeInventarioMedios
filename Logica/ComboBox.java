package Logica;

import java.util.ArrayList;

import Persistencia.PersistenciaCBX;

public class ComboBox {
	
	public static ArrayList<String> listarTipoMedio(){
		ArrayList<String> tipoMedio = PersistenciaCBX.cargarTipoMedio();
		return tipoMedio;
	}
	
	public static ArrayList<String> listarUbicacion(){
		ArrayList<String> ubicacion = PersistenciaCBX.cargarUbicacion();
		return ubicacion;
	}

	public static ArrayList<String> listarStatus(){
		ArrayList<String> status = PersistenciaCBX.cargarStatus();
		return status;
	}
	
	public static ArrayList<String> listarUsuario(){
		ArrayList<String> status = Persistencia.PersistenciaUsuarios.comboUsuario();
		return status;
	}
	
}
