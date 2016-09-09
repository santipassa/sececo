/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMs;

import Modelos.Relaciones.RelacionDispPers;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author santiago
 */
public class ABMRelacionDispPers {

    //genera la conexion
    private Connection abrirBase() {
        Conexion conexion = new Conexion();
        return conexion.getConexion();
    }

    public boolean alta(RelacionDispPers r) {
        Connection base = abrirBase();
        try {
            String sql = "INSERT INTO relacion_disposiciones_pago_personas(id_disposicion_pago,id_persona,montoAsignado) VALUES (?,?,?)";
            PreparedStatement query = base.prepareStatement(sql);

            query.setInt(1, r.getDisposicion().getId());
            query.setInt(2, r.getPersona().getId());
            query.setBigDecimal(3, r.getMontoAsignado());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean baja(RelacionDispPers r) {
        Connection base = abrirBase();
        try {

            String sql = "DELETE FROM relacion_disposiciones_pago_personas WHERE id_disposicion_pago=? AND id_persona=?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setInt(1, r.getDisposicion().getId());
            query.setInt(2, r.getPersona().getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean modificar(RelacionDispPers r) {
        Connection base = abrirBase();
        try {
            String sql = "UPDATE relacion_disposiciones_pago_personas SET montoAsingado=? WHERE id_disposicion_pago=? AND id_persona=?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setBigDecimal(1, r.getMontoAsignado());
            query.setInt(2, r.getDisposicion().getId());
            query.setInt(3, r.getPersona().getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
