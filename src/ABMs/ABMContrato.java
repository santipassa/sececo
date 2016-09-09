package ABMs;

import Modelos.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author santiago
 */
public class ABMContrato {

    private Connection abrirBase() {
        Conexion conexion = new Conexion();
        return conexion.getConexion();
    }

    public boolean alta(Contrato c) {
        Connection base = abrirBase();
        try {
            String sql = "INSERT INTO contratos(numeroResolucion0,numeroResolucion1,fechaResolucion,tipoTarea,lugarTarea,fechaDesde,fechaHasta,pesosLetra,pesosNumero,viatico,pesosLetraViatico,pesosNumeroViatico,fechaContrato,id_persona,id_tresoluciones,numeroExpediente,areaPresupuestaria,impreso,subArea,borrado) VALUES (UPPER(?),UPPER(?),?,UPPER(?),UPPER(?),?,?,UPPER(?),?,?,UPPER(?),?,?,?,?,UPPER(?),UPPER(?),?,UPPER(?),?)";
            PreparedStatement query = base.prepareStatement(sql);
            query.setString(1, c.getNumeroResolucion0());
            query.setString(2, c.getNumeroResolucion1());
            query.setDate(3, c.getFechaResolucion());
            query.setString(4, c.getTipoTarea());
            query.setString(5, c.getLugarTarea());
            query.setDate(6, c.getFechaDesde());
            query.setDate(7, c.getFechaHasta());
            query.setString(8, c.getPesosLetra());
            query.setBigDecimal(9, c.getPesosNumero());
            query.setBoolean(10, c.getViatico());
            query.setString(11, c.getPesosLetraViatico());
            query.setBigDecimal(12, c.getPesosNumeroViatico());
            query.setDate(13, c.getFechaContrato());
            query.setInt(14, c.getPersona().getId());
            query.setInt(15, c.getTResolucion().getId());
            query.setString(16, c.getNumeroExpediente());
            query.setString(17, c.getAreaPresupuestaria());
            query.setBoolean(18, c.getImpreso());
            query.setString(19, c.getSubArea());
            query.setBoolean(20,false);
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean baja(Contrato c) {
        Connection base = abrirBase();
        try {

            String sql = "DELETE FROM contratos WHERE id =?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setInt(1, c.getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificar(Contrato c) {
        Connection base = abrirBase();
        try {
            String sql = "UPDATE contratos SET numeroResolucion0=UPPER(?),numeroResolucion1=UPPER(?),fechaResolucion=?,tipoTarea=UPPER(?),lugarTarea=UPPER(?),fechaDesde=?,fechaHasta=?,pesosLetra=UPPER(?),pesosNumero=?,viatico=?,pesosLetraViatico=UPPER(?),pesosNumeroViatico=?,fechaContrato=?,id_persona=?,id_tresoluciones=?,numeroExpediente=UPPER(?),areaPresupuestaria=UPPER(?),impreso=?,subArea=UPPER(?),borrado=?  WHERE id=?";
            PreparedStatement query = base.prepareStatement(sql);
            query.setString(1, c.getNumeroResolucion0());
            query.setString(2, c.getNumeroResolucion1());
            query.setDate(3, c.getFechaResolucion());
            query.setString(4, c.getTipoTarea());
            query.setString(5, c.getLugarTarea());
            query.setDate(6, c.getFechaDesde());
            query.setDate(7, c.getFechaHasta());
            query.setString(8, c.getPesosLetra());
            query.setBigDecimal(9, c.getPesosNumero());
            query.setBoolean(10, c.getViatico());
            query.setString(11, c.getPesosLetraViatico());
            query.setBigDecimal(12, c.getPesosNumeroViatico());
            query.setDate(13, c.getFechaContrato());
            query.setInt(14, c.getPersona().getId());
            query.setInt(15, c.getTResolucion().getId());
            query.setString(16, c.getNumeroExpediente());
            query.setString(17, c.getAreaPresupuestaria());
            query.setBoolean(18, c.getImpreso());
            query.setString(19, c.getSubArea());
            query.setBoolean(20,c.getBorrado());
            query.setInt(21, c.getId());
            query.executeUpdate();
            base.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
