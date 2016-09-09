package Utilidades;

/**
 *
 * @author santiago
 */
public class DoubleFormatOk {

    public static boolean FormatOK(String s) {
        try {
            Double num = Double.valueOf(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
