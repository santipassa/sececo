package ABMs;

import Modelos.DisposicionDePago;
import Modelos.Persona;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author santiago
 */
public class ABMDisposicionDePago {

    private Connection abrirBase() {
        Conexion conexion = new Conexion();
        return conexion.getConexion();
    }

    public boolean alta(DisposicionDePago d) {
        Connection base = abrirBase();
        try {
            String sql = "INSERT INTO disposiciones_de_pago(fecha,numeroExpediente,numeroResolucion0,numeroResolucion1,montoLetra,montoNumero,considerando,anticipoFinanciero,transfBancaria) VALUES (?,?,?,?,UPPER(?),?,UPPER(?),?,?)";
            PreparedStatement query = base.prepareStatement(sql);
            query.setDate(1, d.getFecha());
            query.setInt(2, d.getNumeroExpediente());
            query.setInt(3, d.getNumeroResolucion0());
            query.setInt(4, d.getNumeroResolucion1());
            query.setString(5, d.getMontoLetra());
            query.setBigDecimal(6, d.getMontoNum());
            query.setString(7, d.getConsiderando());
            query.setBoolean(8, d.getAticipoFinanciero());
            query.setBoolean(9, d.getTransfBancaria());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean baja(DisposicionDePago d) {
        Connection base = abrirBase();
        try {

            String sql = "DELETE FROM disposiciones_de_pago WHERE id =?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setInt(1, d.getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificar(DisposicionDePago d) {
        Connection base = abrirBase();
        try {
            String sql = "UPDATE disposiciones_de_pago SET fecha=?,numeroExpediente=?,numeroResolucion0=?,numeroResolucion1=?,montoLetra=UPPER(?),montoNumero=?,considerando=UPPER(?),anticipoFinanciero=?,transfBancaria=?  WHERE id=?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setDate(1, d.getFecha());
            query.setInt(2, d.getNumeroExpediente());
            query.setInt(3, d.getNumeroResolucion0());
            query.setInt(4, d.getNumeroResolucion1());
            query.setString(5, d.getMontoLetra());
            query.setBigDecimal(6, d.getMontoNum());
            query.setString(7, d.getConsiderando());
            query.setBoolean(8, d.getAticipoFinanciero());
            query.setBoolean(9, d.getTransfBancaria());
            query.setInt(10, d.getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getUltimaId() {
        Connection base = abrirBase();
        String sql = "SELECT * FROM disposiciones_de_pago ORDER BY (id) DESC";
        int id = -1;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);

            }
            base.close();
            return id;
        } catch (Exception e) {
            return id;
        }

    }

}
