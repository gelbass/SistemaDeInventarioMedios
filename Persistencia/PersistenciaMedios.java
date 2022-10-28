package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataType.Medio;
import Exepciones.ExepcionDB;
import Utilidades.ConvertirDatos;
import Utilidades.Mensajes;
import Utilidades.ValidarDatos;

public class PersistenciaMedios {

	public Integer agregar(Medio medio,String usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		Integer resultado = null;
		ResultSet rs = null;

		try {
			// Abrir conexion
			con = RecursosDB.obtenerConexion();
			// Configurar SQL
			ps = con.prepareStatement(
					"INSERT INTO Medios (numMedio, fechaIngreso, status, descripcion, etiqueta, tipo, ubicacion, ubicacion) VALUES (?,?,?,?,?,?,?,?)");

			ps.setInt(1, medio.getNumMedio());
			ps.setDate(2, ConvertirDatos.getJavaSqlDate(medio.getFechaIngreso()));
			ps.setString(3, medio.getStatus());
			ps.setString(4, medio.getDescripcion());
			ps.setString(5, medio.getEtiqueta());
			ps.setString(6, medio.getTipo());
			ps.setString(7, medio.getUbicacion());
			ps.setString(8, usuario);

			// Ejecutar Sentecia
			ps.executeUpdate();

			// Procesar Resultados
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(1);
			}
			Mensajes.confirmacion("Medio agredado exitosamente");
			
		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al registrar nuevo medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			// Liberar recursos
			RecursosDB.liberarRecursos(rs, ps, con);
		}

		return resultado; 
	}
	
	public Integer modificar(int valor, Medio medio,String usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		Integer resultado = null;
		ResultSet rs = null;

		try {
			// Abrir conexion
			con = RecursosDB.obtenerConexion();
			// Configurar SQL
			ps = con.prepareStatement(
					"UPDATE Medios SET status = ?, descripcion = ?, etiqueta = ?, tipo = ?, ubicacion = ?, usuario = ?  WHERE numMedio = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);


			ps.setString(1, medio.getStatus());
			ps.setString(2, medio.getDescripcion());
			ps.setString(3, medio.getEtiqueta());
			ps.setString(4, medio.getTipo());
			ps.setString(5, medio.getUbicacion());
			ps.setString(6, usuario);
			ps.setInt(7, valor);
			

			// Ejecutar Sentecia
			ps.executeUpdate();

			// Procesar Resultados
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(valor);
			}
			Mensajes.confirmacion("Medio actualizado exitosamente");
			
		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al actualizar nuevo medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			// Liberar recursos
			RecursosDB.liberarRecursos(rs, ps, con);
		}

		return resultado; 
	}
	
	public Integer destruir(int valor, Medio medio, String usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		Integer resultado = null;
		ResultSet rs = null;

		try {
			// Abrir conexion
			con = RecursosDB.obtenerConexion();
			// Configurar SQL
			ps = con.prepareStatement(
					"UPDATE Medios SET fechaDestruccion = ?, status = ?, usuario = ? WHERE numMedio = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);


			ps.setDate(1, ConvertirDatos.getJavaSqlDate(medio.getFechaDestruccion()));
			ps.setString(2, medio.getStatus());
			ps.setString(3, usuario);
			ps.setInt(4, valor);

			// Ejecutar Sentecia
			ps.executeUpdate();

			// Procesar Resultados
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(valor);
			}
			ValidarDatos.actualizados();
			
		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al dar destruir medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			// Liberar recursos
			RecursosDB.liberarRecursos(rs, ps, con);
		}

		return resultado; 
	}
	
	public Integer actualizarUbicacion(int valor, String ubicacion, String usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		Integer resultado = null;
		ResultSet rs = null;
		
		try {
			// Abrir conexion
			con = RecursosDB.obtenerConexion();
			// Configurar SQL
			ps = con.prepareStatement(
					"UPDATE Medios SET ubicacion = ? , usuario = ? WHERE numMedio = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, ubicacion);
			ps.setString(2, usuario);
			ps.setInt(3, valor);


			// Ejecutar Sentecia
			ps.executeUpdate();

			// Procesar Resultados
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				resultado = rs.getInt(valor);
			}
			Mensajes.confirmacion("Medio actualizado");
			
		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("Error al actualizar la ubicación");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			// Liberar recursos
			RecursosDB.liberarRecursos(rs, ps, con);
		}

		return resultado; 
	}
	
	// Busqueda por numero de Inventario
	public static Medio buscarMedioNumMedio(int valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Medio resultado = null;

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT * FROM medios WHERE medios.numMedio = ?;");
			ps.setInt(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado = new Medio(rs.getInt("numMedio"),
						rs.getDate("fechaIngreso"),
						rs.getDate("fechaDestruccion"), rs.getString("status"),
						rs.getString("descripcion"), rs.getString("etiqueta"),
						rs.getString("tipo"),rs.getString("ubicacion"));
			}
			
			if (resultado.getNumMedio() != valor) {
				Mensajes.errorGenerico("No existe medio con el número de inventario ingresado");
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No se encontro el medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;
	}
	
	

	// Busqueda por Etiqueta
	public static Medio buscarMedioETQ(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Medio resultado = null;

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT * FROM medios WHERE medios.etiqueta = ?;");
			ps.setString(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				resultado = new Medio(rs.getInt("numMedio"),
						rs.getDate("fechaIngreso"),
						rs.getDate("fechaDestruccion"), rs.getString("status"),
						rs.getString("descripcion"), rs.getString("etiqueta"),
						rs.getString("tipo"),rs.getString("ubicacion"));
			}
			
			if (resultado.getEtiqueta() == null) {
				Mensajes.errorGenerico("No existe medio con la etiqueta ingresada");
			}

		} catch (SQLException  ex) {
			Mensajes.errorDB("No se encontro el medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;
	}
	
	//Busqueda Autopcompletada por Etiqueta
	public static ArrayList<Medio> buscarComboEtq() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Medio> resultado = new ArrayList<>();
		//String valorSql =valor+"*'";
		 
		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT medios.etiqueta FROM medios;");
			//ps.setString(1, "%"+valor+"%");
			 
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				resultado.add(new Medio(rs.getString("etiqueta")));
				}	 
			
		} catch (SQLException  ex) {
			Mensajes.errorDB("No se encontro el medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return resultado;
	}
	
	//Busqueda Autopcompletada por numero de Inventario
		public static ArrayList<Medio> buscarComboNumInv() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<Medio> resultado = new ArrayList<>();
			//String valorSql =valor+"*'";
			 
			try {
				con = RecursosDB.obtenerConexion();

				ps = con.prepareCall("SELECT medios.numMedio FROM medios;");
				//ps.setString(1, "%"+valor+"%");
				 
				rs = ps.executeQuery();
				
				while (rs.next()) {
					
					resultado.add(new Medio(rs.getInt("numMedio")));
					
					}	
								
			} catch (SQLException  ex) {
				Mensajes.errorDB("No se encontro el medio de respaldo");
				Logger.getLogger(PersistenciaMedios.class.getName())
						.log(Level.SEVERE, null, ex);
			} finally {
				RecursosDB.liberarRecurso(con);
			}
			return resultado;
		}
	

	// Busqueda por Tipo de medio
	public static ArrayList<Medio> buscarMedioTipo(String valor, String status) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Medio> listado = new ArrayList<>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall("SELECT * FROM medios WHERE medios.tipo = ? AND medios.status = ?;");
			ps.setString(1, valor);
			ps.setString(2, status);

			rs = ps.executeQuery();

			while (rs.next()) {
				listado.add(new Medio(rs.getInt("numMedio"),
						rs.getDate("fechaIngreso"),
						rs.getDate("fechaDestruccion"), rs.getString("status"),
						rs.getString("descripcion"), rs.getString("etiqueta"),
						rs.getString("tipo")));
				
			}
			
		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No se encontro el medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}

	// Busqueda por Estatus
	public static ArrayList<Medio> buscarMedioStatus(String valor) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Medio> listado = new ArrayList<>();

		try {
			con = RecursosDB.obtenerConexion();

			ps = con.prepareCall(
					"SELECT * FROM medios " + "WHERE medios.status = ?;");
			ps.setString(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				while (rs.next()) {
					listado.add(new Medio(rs.getInt("numMedio"),
							rs.getDate("fechaIngreso"),
							rs.getDate("fechaDestruccion"), rs.getString("status"),
							rs.getString("descripcion"), rs.getString("etiqueta"),
							rs.getString("tipo")));
				}
			}

		} catch (SQLException | ExepcionDB ex) {
			Mensajes.errorDB("No se encontro el medio de respaldo");
			Logger.getLogger(PersistenciaMedios.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			RecursosDB.liberarRecurso(con);
		}
		return listado;
	}
	
	public static Integer obtenerUltimoNumInventario() {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer resultado = null;

        try {
            //obtener conexion
            con = RecursosDB.obtenerConexion();

            //crear consulta
            ps = con.prepareCall("SELECT MAX(numMedio) as ultimo FROM medios;");

            //Ejecutar consulta
            rs = ps.executeQuery();
            //recorrer resultados
            while (rs.next()) {
                resultado= rs.getInt("ultimo");
            }

        } catch (SQLException | ExepcionDB ex) {
        	Mensajes.errorDB("Error al cargar nuevo numero de inventario");
            Logger.getLogger(PersistenciaMedios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Liberar recursos
            RecursosDB.liberarRecurso(con);
        }

        return resultado;
	}

}
