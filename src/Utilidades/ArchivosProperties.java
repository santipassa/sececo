/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author santiago
 */
public class ArchivosProperties {

    public ArchivosProperties() {
    }

    public Properties getProperties(String fileName) {
        try {
            Properties propiedades = new Properties();
            propiedades.load(getClass().getResourceAsStream("/config/" + fileName + ".properties"));
            if (!propiedades.isEmpty()) {
                return propiedades;
            } else {
                return null;
            }
        } catch (IOException ex) {

            return null;
        }
    }

}
