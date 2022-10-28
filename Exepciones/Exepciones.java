package Exepciones;

import java.util.List;

import Utilidades.DetalleError;

public class Exepciones extends RuntimeException{
	private final List<DetalleError> errores;

	public Exepciones(List<DetalleError> errores) {
        this.errores = errores;
    }

    public Exepciones(String message,List<DetalleError> errores) {
        super(message);
       this.errores = errores;
    }

    public Exepciones(Throwable cause,List<DetalleError> errores) {
        super(cause);
        this.errores = errores;
    }

    public Exepciones(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,List<DetalleError> errores) {
        super(message, cause, enableSuppression, writableStackTrace);
       this.errores = errores;
    }
    
    public List<DetalleError> getErrors(){
        return errores;
    }
}
