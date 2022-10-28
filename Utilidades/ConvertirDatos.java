package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertirDatos {

    public static Date StringAFecha(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        
            fechaDate = formato.parse(fecha);
            //java.sql.Date fechasql = new java.sql.Date(fechaDate.getTime());
       
        return fechaDate;
    }
    public static  String FechaAString(Date fecha){
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }
    

   public static java.sql.Date getJavaSqlDate(java.util.Date date) {
       return new java.sql.Date(date.getTime());
    }
}