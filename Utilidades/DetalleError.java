package Utilidades;
import java.io.Serializable;

public class DetalleError implements Serializable{
    private final String error;
    private final String definicion;

    public String getError() {
        return error;
    }

    public String getDefinicion() {
        return definicion;
    }

    public DetalleError(String error, String definicion) {
        this.error = error;
        this.definicion = definicion;
    }
}
