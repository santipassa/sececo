package Utilidades;

/**
 *
 * @author santiago
 */
public class ValidateCuit {

    public static boolean getValidation(String cuit) {
        boolean result;
        try {
            result = cuit.length() == 13 && cuit.charAt(2) == '-' && cuit.charAt(11) == '-';
            return result;

        } catch (Exception e) {
            return false;

        }

    }

}
