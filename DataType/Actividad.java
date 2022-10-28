package DataType;

import java.sql.Date;

public class Actividad {

	private String usuario;
	private Date fecha;
	private int numMedio;
	private String etiqueta;
	private String ubicacion;
	private String status;
	private String actividad;
	
	
	
	
	public Actividad(String usuario, Date fecha, int numMedio, String etiqueta, String ubicacion, String status,
			String actividad) {
		this.usuario = usuario;
		this.fecha = fecha;
		this.numMedio = numMedio;
		this.etiqueta = etiqueta;
		this.ubicacion = ubicacion;
		this.status = status;
		this.actividad = actividad;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNumMedio() {
		return numMedio;
	}
	public void setNumMedio(int numMedio) {
		this.numMedio = numMedio;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	
	
}
