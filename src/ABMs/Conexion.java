package ABMs;

import Utilidades.ArchivosProperties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author santiago
 */
public class Conexion {

    public Conexion() {
    }
    //IMPLEMENTACION CON MYSQL

    public Connection getConexion() {
        Connection con = null;
        try {
            //traigo los datos del archivo de configuracion
            ArchivosProperties a = new ArchivosProperties();
            Properties p = a.getProperties("database");
            String ip = p.getProperty("ip");
            String puerto = p.getProperty("puerto");
            String schema = p.getProperty("schema");
            String usuario = p.getProperty("usuario");
            String passwd = p.getProperty("passwd");

            //FIN DE TRAIDA DE DATOS
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + schema, usuario, passwd);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

}
