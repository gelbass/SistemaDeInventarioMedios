package Persistencia;

import java.sql.Connection;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataType.Actividad;
import DataType.Inventario;
import DataType.Medio;
import DataType.Usuarios;
import Exepciones.ExepcionDB;
import Utilidades.ConvertirDatos;
import Utilidades.Mensajes;

public class PersistenciaUsuarios {

	public static boolean login(String user, String pass) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT usuarios.usuario, usuarios.passw0rd, nombre_apellido FROM usuarios "
					+ "WHERE usuarios.usuario = ? AND usuarios.passw0rd = ?;");
			ps.setString(1, user);
			ps.setString(2, pass);

			rs = ps.executeQuery();
			
			while (rs.next()) {

				if (rs.getString("usuarios.usuario").equals(user) && rs.getString("usuarios.passw0rd").equals(pass)) {					
					Mensajes.confirmacion("Ingreso Exitoso");
					return true;
				}
			} 
			

		} catch (SQLException ex) {
			Mensajes.errorDB("Usuario no encontrado");
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		Mensajes.errorGenerico("Error en ingreso de usuario o contrase�a");
		return false ;
		
	}
	
	public static Usuarios conectado(String user, String pass) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuarios usuario = null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT usuarios.usuario, usuarios.passw0rd, usuarios.nombre_apellido FROM usuarios "
					+ "WHERE usuarios.usuario = ? AND usuarios.passw0rd = ?;");
			ps.setString(1, user);
			ps.setString(2, pass);

			rs = ps.executeQuery();
			
			while (rs.next()) {

				if (rs.getString("usuarios.usuario").equals(user) && rs.getString("usuarios.passw0rd").equals(pass)) {
					usuario = new Usuarios(rs.getString("usuarios.usuario"), rs.getString("usuarios.passw0rd"), rs.getString("usuarios.nombre_apellido"));
					return usuario;
				}
			} 
			

		} catch (SQLException ex) {
			Mensajes.errorDB("Usuario no encontrado");
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return usuario;
		
	}
	
	public static Usuarios activo(String user) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuarios usuario = null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT usuarios.usuario, usuarios.passw0rd, usuarios.nombre_apellido FROM usuarios "
					+ "WHERE usuarios.usuario = ?;");
			ps.setString(1, user);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {

				if (rs.getString("usuarios.usuario").equals(user)) {
					usuario = new Usuarios(rs.getString("usuarios.usuario"), rs.getString("usuarios.passw0rd"), rs.getString("usuarios.nombre_apellido"));
					return usuario;
				}
			} 
			

		} catch (SQLException ex) {
			Mensajes.errorDB("Usuario no encontrado");
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return usuario;
		
	}
	
	public Integer nuevo(Usuarios u) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer resultado= null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareStatement(
					"INSERT INTO usuarios (usuario, passw0rd, nombre_apellido) VALUES (?,?,?)");
			ps.setString(1, u.getUser());
			ps.setString(2, u.getPass());
			ps.setString(3, u.getNom_ape());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(1);
			}
			Mensajes.confirmacion("Usuario creado exitosamente");

		} catch (SQLException ex) {
			Mensajes.errorDB("Error al crear nuevo usuario");
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecursos(rs, ps, con);
		}
		return resultado;
	}
	
	public  String cambiarContrasena(Usuarios u) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String resultado= null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareStatement("UPDATE usuarios SET usuarios.passw0rd = ? WHERE usuarios.usuario = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, u.getPass());
			ps.setString(2, u.getUser());

			ps.executeUpdate();

			// Procesar Resultados
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getString(u.getUser());
			}
			Mensajes.confirmacion("Contrase�a cambiada exitosamente");

		} catch (SQLException ex) {
			Mensajes.errorDB("Error al cambiar contrase�a");
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecursos(rs, ps, con);
		}
		return resultado;
	}
	
	public static boolean validarUsuario(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT usuarios.usuario FROM usuarios WHERE usuarios.usuario = ? ;");
			ps.setString(1, valor);

			rs = ps.executeQuery();
			
			while (rs.next()) {

				if (rs.getString("usuarios.usuario").equals(valor)) {	
					Mensajes.errorGenerico("El nombre de usuario ya existe");
					return true;
				}
			} 
			

		} catch (SQLException ex) {
			Mensajes.errorDB("Usuario no encontrado");
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		
		return false ;
		
	}
	
	public Integer addActividad(Usuarios usuario, Medio medio) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer resultado= null;
		
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareStatement(
					"INSERT INTO logActividad (usuario, fecha, numMedio, etiqueta, ubicacion, status, actividad) VALUES (?,?,?,?,?,?,?)");
			
			ps.setString(1, usuario.getUser());
			ps.setDate(2, ConvertirDatos.getJavaSqlDate(medio.getFechaIngreso()));
			ps.setInt(3, medio.getNumMedio());
			ps.setString(4, medio.getEtiqueta());
			ps.setString(5, medio.getUbicacion());
			ps.setString(6, medio.getStatus());
			ps.setString(7, usuario.getActividadLog());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(1);
			}
			System.out.println("se agrego nueva actividad de usuario");

		} catch (SQLException ex) {
			Logger.getLogger(PersistenciaUsuarios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecursos(rs, ps, con);
		}
		return resultado;
	}
	
	public static ArrayList<Actividad> busquedaActNumInv(Integer valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT * FROM logActividad WHERE logActividad.numMedio = ?;");
			ps.setInt(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado.add(new Actividad(rs.getString("usuario"), rs.getDate("fecha"), rs.getInt("numMedio"), 
						rs.getString("etiqueta"), rs.getString("ubicacion"), rs.getString("status"), rs.getString("actividad")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No existe actividad registrada para este medio");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;	
	}
	
	public static ArrayList<Actividad> busquedaActEtq(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT * FROM logActividad WHERE logActividad.etiqueta = ?;");
			ps.setString(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado.add(new Actividad(rs.getString("usuario"), rs.getDate("fecha"), rs.getInt("numMedio"), 
						rs.getString("etiqueta"), rs.getString("ubicacion"), rs.getString("status"), rs.getString("actividad")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No existe actividad registrada para esta etiqueta");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;	
	}
	
	public static ArrayList<Actividad> busquedaActUsurio(String valor) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT * FROM logActividad WHERE logActividad.usuario = ?;");
			ps.setString(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado.add(new Actividad(rs.getString("usuario"), rs.getDate("fecha"), rs.getInt("numMedio"), 
						rs.getString("etiqueta"), rs.getString("ubicacion"), rs.getString("status"), rs.getString("actividad")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No existe actividad registrada para este usuario");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;	
	}
	
	public static ArrayList<Actividad> busquedaFecha(Date desde, Date hasta) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT * FROM logActividad WHERE logActividad.fecha BETWEEN ? AND ?;");
			ps.setDate(1, ConvertirDatos.getJavaSqlDate(desde));
			ps.setDate(2, ConvertirDatos.getJavaSqlDate(hasta));
			
			rs = ps.executeQuery();

			while (rs.next()) {
				resultado.add(new Actividad(rs.getString("usuario"), rs.getDate("fecha"), rs.getInt("numMedio"), 
						rs.getString("etiqueta"), rs.getString("ubicacion"), rs.getString("status"), rs.getString("actividad")));
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No existe actividad registrada para este medio");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;	
	}
	
	public static ArrayList<String> comboUsuario() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		ArrayList<String> resultado = new ArrayList<String>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT usuario FROM usuarios;");

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado.add(rs.getString("usuario"));
			}

		} catch (SQLException | ExepcionDB ex) {
			//Mensajes.errorDB("No existe actividad registrada para este medio");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;	
	}
}
