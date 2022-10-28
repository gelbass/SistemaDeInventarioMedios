package Logica;

import java.util.Date;
import java.util.ArrayList;

import DataType.Actividad;
import DataType.Medio;
import DataType.Usuarios;
import LogicaInterfaces.InterUsuario;
import Persistencia.PersistenciaUsuarios;

public class OperacionesUsuario implements InterUsuario {

	@Override
	public boolean login(Usuarios usuarios) {
		
		return PersistenciaUsuarios.login(usuarios.getUser(), usuarios.getPass());

	}

	@Override
	public Usuarios conectado(Usuarios usuarios) {
		return PersistenciaUsuarios.conectado(usuarios.getUser(), usuarios.getPass());
	}

	@Override
	public Integer nuevo(Usuarios usuario) {
		PersistenciaUsuarios perUsuarios = new PersistenciaUsuarios();
		Integer u = null;
		u = perUsuarios.nuevo(usuario);
		return u;
	}

	@Override
	public String cambiarContreasena(Usuarios usuario) {
		PersistenciaUsuarios perUsuarios = new PersistenciaUsuarios();
		String u = null;
		u = perUsuarios.cambiarContrasena(usuario);
		return u;
		
	}

	@Override
	public boolean validarUsuario(String valor) {
		return PersistenciaUsuarios.validarUsuario(valor);
	}
	
	@Override
	public Usuarios activo(String valor) {
		
		return PersistenciaUsuarios.activo(valor);
		
	}
	@Override
	public Integer addActividad(Usuarios usuario, Medio medio) {
		PersistenciaUsuarios perUsuarios = new PersistenciaUsuarios();
		Integer log = null;
		log = perUsuarios.addActividad(usuario, medio);
		return log;
	}

	@Override
	public ArrayList<Actividad> busquedaActNumInv(int valor) {
		return PersistenciaUsuarios.busquedaActNumInv(valor);
	}

	@Override
	public ArrayList<Actividad> busquedaActEtq(String valor) {
		return PersistenciaUsuarios.busquedaActEtq(valor);
	}

	@Override
	public ArrayList<Actividad> busquedaActUsurio(String valor) {
		return PersistenciaUsuarios.busquedaActUsurio(valor);
	}
	
	@Override
	public ArrayList<String> comboUsuario() {
		return PersistenciaUsuarios.comboUsuario();
	}

	@Override
	public ArrayList<Actividad> busquedaFecha(Date desde, Date hasta) {
		return PersistenciaUsuarios.busquedaFecha(desde, hasta);
	}



}
