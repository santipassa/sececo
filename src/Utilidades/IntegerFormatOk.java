package Utilidades;

/**
 *
 * @author santiago
 */
public class IntegerFormatOk {

    public static boolean FormatOK(String s) {
        try {
            int num = Integer.valueOf(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
