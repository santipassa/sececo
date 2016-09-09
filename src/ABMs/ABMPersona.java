package ABMs;

import Modelos.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author santiago
 */
public class ABMPersona {

    //genera la conexion
    private Connection abrirBase() {
        Conexion conexion = new Conexion();
        return conexion.getConexion();
    }

    public boolean alta(Persona p) {
        Connection base = abrirBase();
        try {
            String sql = "INSERT INTO personas(nombre,apellido,direccion,telefono,documento,cbu,id_tdocumentos) VALUES (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),?)";
            PreparedStatement query = base.prepareStatement(sql);
            query.setString(1, p.getNombre());
            query.setString(2, p.getApellido());
            query.setString(3, p.getDireccion());
            query.setString(4, p.getTelefono());
            query.setString(5, p.getDocumento());
            query.setString(6, p.getCbu());
            query.setInt(7, p.gettDocumento().getId());

            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean baja(Persona p) {
        Connection base = abrirBase();
        try {

            String sql = "DELETE FROM personas WHERE id = ?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setInt(1, p.getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean modificar(Persona p) {
        Connection base = abrirBase();
        try {
            String sql = "UPDATE personas SET nombre=UPPER(?),apellido=UPPER(?),direccion=UPPER(?),telefono=UPPER(?),documento=UPPER(?),cbu=UPPER(?),id_tdocumentos=? WHERE id=?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setString(1, p.getNombre());
            query.setString(2, p.getApellido());
            query.setString(3, p.getDireccion());
            query.setString(4, p.getTelefono());
            query.setString(5, p.getDocumento());
            query.setString(6, p.getCbu());

            query.setInt(7, p.gettDocumento().getId());
            query.setInt(8, p.getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
