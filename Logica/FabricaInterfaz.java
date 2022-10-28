package Logica;

import LogicaInterfaces.InterInventario;
import LogicaInterfaces.InterUsuario;

public class FabricaInterfaz {

	public static InterInventario getInventario() {
		return new OperacionesInventario();
	}
	
	public static InterUsuario getUsuarios() {
		return new OperacionesUsuario();
	}
}
