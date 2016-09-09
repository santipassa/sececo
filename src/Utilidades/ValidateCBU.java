/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author santiago
 */
public class ValidateCBU {

    public static boolean getValidation(String cbu) {
        boolean result;
        try {
            result = cbu.length() == 22;
            return result;

        } catch (Exception e) {
            return false;

        }

    }

}
