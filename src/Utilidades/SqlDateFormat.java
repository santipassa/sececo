package Utilidades;

import java.util.Date;

/**
 *
 * @author santiago
 */
public class SqlDateFormat {

    public static String dateToMySQLDate(Date fecha, boolean sqlFormat) {
        if (!sqlFormat) {
            //formato pantalla
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            //formato sql
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(fecha);
        }
    }

}
