package DataType;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Inventario extends Medio{


	private int idInventario;
	private String ubicacion;
	private Date fechaMovimiento;
	private List<Medio> medios = new ArrayList<Medio>();
	private Medio med;


	public Inventario(List<Medio> medios, String ubicacion) {
		this.medios = medios;
		this.ubicacion = ubicacion;			
	}
	
	public Inventario(Medio med, Date fechaMovimiento) {
		this.setMed(med);
		this.fechaMovimiento = fechaMovimiento;;			
	}
	
	public Inventario(Medio med,String ubicacion, Date fechaMovimiento) {
		this.setMed(med);
		this.ubicacion = ubicacion;
		this.fechaMovimiento = fechaMovimiento;;			
	}
	
	public Inventario(int numMedio, String ubicacion, Date fechaMovimiento) {
		super(numMedio);
		this.ubicacion = ubicacion;
		this.fechaMovimiento = fechaMovimiento;
	}

	public int getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimientoDate(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public List<Medio> getMedios() {
		return medios;
	}
	public void setMedios(List<Medio> medios) {
		this.medios = medios;
	}

	public Medio getMed() {
		return med;
	}

	public void setMed(Medio med) {
		this.med = med;
	}



}
