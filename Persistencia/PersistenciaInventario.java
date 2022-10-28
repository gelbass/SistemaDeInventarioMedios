package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataType.Inventario;
import DataType.Medio;
import Exepciones.ExepcionDB;
import Utilidades.ConvertirDatos;
import Utilidades.Mensajes;

public class PersistenciaInventario {

	public Integer agregar(Inventario inventario, String usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		Integer resultado = null;
		ResultSet rs = null;

		try {
			// Abrir conexion
			con = RecursosDB.obtenerConexion();
			// Configurar SQL
			ps = con.prepareStatement(
					"INSERT INTO movimientos (numMedio, ubicacion, fechaMovimiento, usuario) VALUES (?,?,?,?)");

			ps.setInt(1, inventario.getMed().getNumMedio());
			ps.setString(2, inventario.getMed().getUbicacion());
			ps.setDate(3, ConvertirDatos.getJavaSqlDate(inventario.getFechaMovimiento()));
			ps.setString(4, usuario);
			// Ejecutar Sentecia
			ps.executeUpdate();
			
			// Procesar Resultados
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(1);
			}
			Mensajes.confirmacion("Se agrego nuevo movimiento exitosamente");
			
		} catch (SQLException | ExepcionDB | NullPointerException ex) {
			Mensajes.errorDB("Error al ingresar nuevo movimiento");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			// Liberar recursos
			RecursosDB.liberarRecursos(rs, ps, con);
		}

		return resultado;
	}

	public static ArrayList<Inventario> listadoInventario() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Inventario> listado = new ArrayList<>();
		ArrayList<Medio> medios = new ArrayList<Medio>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT  medios.numMedio, medios.etiqueta, medios.tipo ,medios.status, medios.ubicacion FROM medios ORDER BY medios.numMedio;");
			
			rs = ps.executeQuery();

			while (rs.next()) {
				medios.add(new Medio(rs.getInt("medios.numMedio"),rs.getString("medios.etiqueta"), 
						rs.getString("medios.tipo"), rs.getString("medios.status")));
				
				
				listado.add(new Inventario(medios, rs.getString("medios.ubicacion")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al cargar el listado");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}
	
	public static ArrayList<Inventario> listadoInventarioTipo(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Inventario> listado = new ArrayList<>();
		List<Medio> medios = new ArrayList<Medio>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT  medios.numMedio, medios.etiqueta, medios.tipo ,medios.status, medios.ubicacion FROM medios"
					+ " WHERE medios.tipo = ? ORDER BY medios.numMedio;"
							+ "");
			ps.setString(1, valor);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				medios.add(new Medio(rs.getInt("medios.numMedio"),rs.getString("medios.etiqueta"), 
						rs.getString("medios.tipo"), rs.getString("medios.status")));
				
				
				listado.add(new Inventario(medios, rs.getString("medios.ubicacion")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al cargar el listado");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}
	
	public static ArrayList<Inventario> listadoInventarioStatus(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Inventario> listado = new ArrayList<>();
		List<Medio> medios = new ArrayList<Medio>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT  medios.numMedio, medios.etiqueta, medios.tipo ,medios.status, medios.ubicacion FROM medios "
					+ "WHERE medios.status = ? ORDER BY medios.numMedio;");
			ps.setString(1, valor);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				medios.add(new Medio(rs.getInt("medios.numMedio"),rs.getString("medios.etiqueta"), 
						rs.getString("medios.tipo"), rs.getString("medios.status")));
				
				
				listado.add(new Inventario(medios, rs.getString("medios.ubicacion")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al cargar el listado");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}


	public static Inventario buscarMedioNumMedio(int valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		Inventario resultado = null;

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT * FROM movimientos WHERE movimientos.numMedio = ?;");
			ps.setInt(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado = new Inventario(rs.getInt("numMedio"),
						rs.getString("ubicacion"),
						rs.getDate("fechaMovimiento"));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No existen movimientos registrados para este medio");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;
	}
	
	public static ArrayList<Inventario> listadoUbicacion(String valor, String status) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Inventario> listado = new ArrayList<>();
		ArrayList<Medio> medios = new ArrayList<Medio>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT  medios.numMedio, medios.etiqueta, medios.ubicacion FROM medios "
					+ "WHERE medios.ubicacion = ? AND medios.status = ?;");
			
			ps.setString(1, valor);
			ps.setString(2, status);
			rs = ps.executeQuery();

			while (rs.next()) {
				medios.add(new Medio(rs.getInt("medios.numMedio"),rs.getString("medios.etiqueta")));
								
				listado.add(new Inventario(medios, rs.getString("medios.ubicacion")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al cargar el listado");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}
	
	public static Inventario ultimoMovimientoNumInv(int valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Inventario listado = null;
		Medio medios = new Medio();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT medios.numMedio, medios.etiqueta, medios.ubicacion, medios.status, movimientos.fechaMovimiento, movimientos.idmovimiento FROM medios, movimientos"
					+ " WHERE medios.numMedio = movimientos.numMedio AND medios.numMedio = ? ;");
			ps.setInt(1, valor);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				medios =new Medio(rs.getString("medios.etiqueta"),rs.getInt("medios.numMedio"), 
						rs.getString("medios.ubicacion"),rs.getString("medios.status"));
				
				
				listado= new Inventario(medios, rs.getDate("movimientos.fechaMovimiento"));
			}

		} catch (SQLException | ExepcionDB | NullPointerException ex) {
			Mensajes.errorDB("No existen movimientos registrados para este medio");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}
	
	public static Inventario ultimoMovimientoEtq(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Inventario listado = null;
		Medio medios = new Medio();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT medios.numMedio, medios.etiqueta, medios.ubicacion, medios.status, movimientos.fechaMovimiento, movimientos.idmovimiento FROM medios, movimientos"
					+ " WHERE medios.numMedio = movimientos.numMedio AND medios.etiqueta = ? ;");
			ps.setString(1, valor);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				medios =new Medio(rs.getString("medios.etiqueta"),rs.getInt("medios.numMedio"), 
						rs.getString("medios.ubicacion"),rs.getString("medios.status"));
				
				
				listado= new Inventario(medios, rs.getDate("movimientos.fechaMovimiento"));
			}

		} catch (SQLException | ExepcionDB | NullPointerException ex) {
			Mensajes.errorDB("No existen movimientos registrados para este medio");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}

}

