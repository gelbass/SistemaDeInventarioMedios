package Logica;

import java.util.ArrayList;

import DataType.Inventario;
import DataType.Medio;
import LogicaInterfaces.InterInventario;
import Persistencia.PersistenciaInventario;
import Persistencia.PersistenciaMedios;

public class OperacionesInventario implements InterInventario {

	@Override
	public Integer agregarMedio(Medio medio, String usuario) {
		PersistenciaMedios perMedios = new PersistenciaMedios();
		Integer m =null;
		m = perMedios.agregar(medio,usuario);
		return m;
	}

	@Override
	public Integer agregarInventario(Inventario inventario, String usuario) {
		PersistenciaInventario perInventario = new PersistenciaInventario();
		Integer inv =null;
		inv = perInventario.agregar(inventario,usuario);
		return inv; 
	}

	@Override
	public Integer modificarMedio(int valor, Medio medio, String usuario) {
		
		PersistenciaMedios perMedios = new PersistenciaMedios();
		Integer m = perMedios.modificar(valor, medio, usuario);
		return m;
	}

	@Override
	public String nuevoMovimiento(String valor, Medio medio, String usuario) {
		return null;
	}

	@Override
	public Medio buscarMedioEtiqueta(String valor) {
		
		return PersistenciaMedios.buscarMedioETQ(valor);
	}

	@Override
	public Medio buscarMedioInventario(int valor) {
		return PersistenciaMedios.buscarMedioNumMedio(valor);
	}

	@Override
	public Integer obtenerUltimoNumInventario() {
		return PersistenciaMedios.obtenerUltimoNumInventario();
	}

	@Override
	public Inventario buscarInventario(int valor) {
		
		return PersistenciaInventario.buscarMedioNumMedio(valor);
	}

	@Override
	public ArrayList<Inventario> verInventario() {
		return PersistenciaInventario.listadoInventario();
	}

	@Override
	public ArrayList<Inventario> listadoTipo(String valor) {
		return PersistenciaInventario.listadoInventarioTipo(valor);
	}

	@Override
	public ArrayList<Inventario> listadoStatus(String valor) {
		return PersistenciaInventario.listadoInventarioStatus(valor);
	}

	@Override
	public Inventario ultimoMovimientoNumInv(int valor) {
		return PersistenciaInventario.ultimoMovimientoNumInv(valor); 
	}

	@Override
	public Inventario ultimoMovimientoEtq(String valor) {
		return PersistenciaInventario.ultimoMovimientoEtq(valor);
	}

	@Override
	public Integer actualizarUbicacion(int valor, String ubicacion, String usuario) {
		PersistenciaMedios perMedios = new PersistenciaMedios();
		Integer m = perMedios.actualizarUbicacion(valor, ubicacion, usuario);
		return m;
	}

	@Override
	public ArrayList<Inventario> listadoUbicacion(String valor, String status) {
		return PersistenciaInventario.listadoUbicacion(valor, status); 
	} 

	@Override
	public ArrayList<Medio> SeleccionTipo(String valor, String status) {
		return PersistenciaMedios.buscarMedioTipo(valor, status);
	}

	@Override
	public Integer destruir(int valor, Medio medio, String usuario) {
		PersistenciaMedios perMedios = new PersistenciaMedios();
		Integer m = perMedios.destruir(valor, medio, usuario);
		return m;
	}

	@Override
	public ArrayList<Medio> busquedaComboEtq() {
		return PersistenciaMedios.buscarComboEtq();
	}

	@Override
	public ArrayList<Medio> busquedaComboNumInv() {
		return PersistenciaMedios.buscarComboNumInv();
	}


}
