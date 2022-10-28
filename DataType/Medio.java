package DataType;

import java.util.Date;

public class Medio {

	protected int numMedio;
	private Date fechaIngreso;
	protected Date fechaDestruccion;
	private String status;
	private String descripcion;
	protected String etiqueta;
	protected String tipo;
	private String ubicacion;
	

	public Medio(int numMedio, Date fechaIngreso, Date fechaDestruccion,
			String status, String descripcion, String etiqueta, String tipo) {
		this.numMedio = numMedio;
		this.fechaIngreso = fechaIngreso;
		this.fechaDestruccion = fechaDestruccion;
		this.status = status;
		this.descripcion = descripcion;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
	}
	
	
	
	public Medio(String etiqueta) {
		super();
		this.etiqueta = etiqueta;
	}


	public Medio(Date fechaDestruccion, String status) {
		
		this.fechaDestruccion = fechaDestruccion;
		this.status = status;
	}

	public Medio(int numMedio, String etiqueta) {
		
		this.numMedio = numMedio;
		this.etiqueta = etiqueta;
	}
	
	public Medio(int numMedio, Date fechaIngreso, Date fechaDestruccion,
			String status, String descripcion, String etiqueta, String tipo, String ubicacion) {
		this.numMedio = numMedio;
		this.fechaIngreso = fechaIngreso;
		this.fechaDestruccion = fechaDestruccion;
		this.status = status;
		this.descripcion = descripcion;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
	}
	
	
	public Medio(int numMedio,Date fechaIngreso, String status, String descripcion,
			String etiqueta, String tipo, String ubicacion) {
		this.numMedio = numMedio;
		this.fechaIngreso = fechaIngreso;
		this.status = status;
		this.descripcion = descripcion;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
	}
	
	public Medio(int numMedio, String etiqueta, String ubicacion,Date fechaIngreso, String status) {
		super();
		this.numMedio = numMedio;
		this.etiqueta = etiqueta;
		this.ubicacion = ubicacion;
		this.fechaIngreso = fechaIngreso;
		this.status = status;
	}



	public Medio(int numMedio, String etiqueta, String tipo, String status) {
		this.numMedio = numMedio;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
		this.status = status;
	}
	
	public Medio(String etiqueta, int numMedio, String ubicacion, String status) {
		this.etiqueta = etiqueta;
		this.numMedio = numMedio;
		this.ubicacion = ubicacion;
		this.status = status;
	}
	public Medio() {
		
	}


	public Medio(int numMedio) {
		this.numMedio = numMedio;
	}



	public int getNumMedio() {
		return numMedio;
	}

	public void setNumMedio(int numMedio) {
		this.numMedio = numMedio;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date date) {
		this.fechaIngreso = date;
	}

	public Date getFechaDestruccion() {
		return fechaDestruccion;
	}

	public void setFechaDestruccion(Date fechaDestruccion) {
		this.fechaDestruccion = fechaDestruccion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}


	@Override
	public String toString() {
		return "Medio [numMedio=" + numMedio + ", etiqueta=" + etiqueta
				+ ", tipo=" + tipo + "]";
	}



	public String getUbicacion() {
		return ubicacion;
	}



	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
