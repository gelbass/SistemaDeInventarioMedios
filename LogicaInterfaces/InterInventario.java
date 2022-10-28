package LogicaInterfaces;

import java.util.ArrayList;

import DataType.Inventario;
import DataType.Medio;

public interface InterInventario {
	
	Integer agregarMedio (Medio medio, String usuario);
	Integer agregarInventario(Inventario inventario, String usuario);
	Integer modificarMedio(int valor, Medio medio,String usuario);
	Integer destruir(int valor, Medio medio, String usuario);
	Integer actualizarUbicacion(int valor, String ubicacion, String usuario);
	String nuevoMovimiento(String valor, Medio medio, String usuario);
	Medio buscarMedioEtiqueta (String valor);
	Medio buscarMedioInventario (int valor);
	Integer obtenerUltimoNumInventario();
	Inventario buscarInventario(int valor);
	ArrayList<Inventario> verInventario();
	ArrayList<Inventario> listadoTipo(String valor);
	ArrayList<Medio> SeleccionTipo(String valor, String status);
	ArrayList<Inventario> listadoStatus(String valor);
	Inventario ultimoMovimientoNumInv(int valor);
	Inventario ultimoMovimientoEtq(String valor);
	ArrayList<Inventario> listadoUbicacion(String valor,String status);
	ArrayList<Medio> busquedaComboEtq();
	ArrayList<Medio> busquedaComboNumInv();
}
