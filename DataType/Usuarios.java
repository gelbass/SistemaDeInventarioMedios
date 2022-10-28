package DataType;

public class Usuarios {
	
	private String user;
	private String pass;
	private String nom_ape;
	private String actividadLog;
	
	public Usuarios(String user, String pass, String nom_ape) {
		super();
		this.user = user;
		this.pass = pass;
		this.nom_ape = nom_ape;
	}
	
	public Usuarios(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	public Usuarios(String user) {
		this.user = user;
	}
	
	public Usuarios() {
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getNom_ape() {
		return nom_ape;
	}


	public void setNom_ape(String nom_ape) {
		this.nom_ape = nom_ape;
	}

	public String getActividadLog() {
		return actividadLog;
	}

	public void setActividadLog(String actividadLog) {
		this.actividadLog = actividadLog;
	}

	

}
