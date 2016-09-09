package Busqueda;

import ABMs.Conexion;
import Modelos.Contrato;
import Modelos.DisposicionDePago;

import Modelos.Persona;
import Modelos.Relaciones.RelacionDispPers;
import Modelos.TDocumento;
import Modelos.TMoneda;
import Modelos.TResolucion;
import Utilidades.ArchivosProperties;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author santiago
 */
public class Busqueda {

    private Connection abrirBase() {
        Conexion conexion = new Conexion();
        return conexion.getConexion();
    }

    public Persona getPersonaById(int id) {
        Connection base = abrirBase();
        Persona p = null;
        String sql = "SELECT * FROM personas WHERE id=" + id;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setDocumento(rs.getString(6));
                p.setCbu(rs.getString(7));
                p.settDocumento(this.getTDocumentoById(rs.getInt(8)));
            }
            base.close();
            return p;
        } catch (Exception e) {
            return null;

        }

    }

    public ArrayList<Persona> getPersonasByPattern(String comodin) {
        Connection base = abrirBase();
        ArrayList<Persona> result = null;

        String sql = "SELECT * FROM personas WHERE documento LIKE '%" + comodin + "%' OR nombre LIKE '%" + comodin + "%' OR apellido LIKE '%" + comodin + "%' OR id='" + comodin + "';";

        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<Persona>();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setDocumento(rs.getString(6));
                p.setCbu(rs.getString(7));
                p.settDocumento(this.getTDocumentoById(rs.getInt(8)));
                result.add(p);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    public ArrayList<Contrato> getContratosByPattern(String comodin) {
        Connection base = abrirBase();
        ArrayList<Contrato> result = null;

        String sql = "SELECT * FROM contratos INNER JOIN personas ON contratos.id_persona=personas.id WHERE personas.documento LIKE '%" + comodin + "%' OR personas.nombre LIKE '%" + comodin + "%' OR personas.apellido LIKE '%" + comodin + "%' OR tipoTarea LIKE '%" + comodin + "%' OR lugarTarea LIKE '%" + comodin + "%' OR CONCAT(numeroResolucion0,'/',numeroResolucion1) LIKE '%" + comodin + "%' OR numeroExpediente LIKE '%" + comodin + "%' OR CONCAT(areaPresupuestaria,'/',subArea) LIKE '%" + comodin + "%' OR contratos.id='" + comodin + "' ORDER BY(contratos.id);";

        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<Contrato>();
            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rs.getInt(1));
                contrato.setNumeroResolucion0(rs.getString(2));
                contrato.setNumeroResolucion1(rs.getString(3));
                contrato.setFechaResolucion(rs.getDate(4));
                contrato.setTipoTarea(rs.getString(5));
                contrato.setLugarTarea(rs.getString(6));
                contrato.setFechaDesde(rs.getDate(7));
                contrato.setFechaHasta(rs.getDate(8));
                contrato.setPesosLetra(rs.getString(9));
                contrato.setPesosNumero(rs.getBigDecimal(10));
                contrato.setViatico(rs.getBoolean(11));
                contrato.setPesosLetraViatico(rs.getString(12));
                contrato.setPesosNumeroViatico(rs.getBigDecimal(13));
                contrato.setFechaContrato(rs.getDate(14));
                contrato.setPersona(this.getPersonaById(rs.getInt(15)));
                contrato.settResolucion(this.getTResolucionById(rs.getInt(16)));
                contrato.setNumeroExpediente(rs.getString(17));
                contrato.setAreaPresupuestaria(rs.getString(18));
                contrato.setImpreso(rs.getBoolean(19));
                contrato.setSubArea(rs.getString(20));
                contrato.setBorrado(rs.getBoolean(21));
                result.add(contrato);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;

        }

    }

    public ArrayList<TResolucion> getTResolucion() {
        Connection base = abrirBase();
        ArrayList<TResolucion> result = null;
        String sql = "SELECT * FROM tresoluciones";
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<TResolucion>();
            while (rs.next()) {
                TResolucion tRes = new TResolucion();
                tRes.setId(rs.getInt(1));
                tRes.setNombre(rs.getString(2));
                result.add(tRes);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;

        }

    }

    public TResolucion getTResolucionById(int id) {
        Connection base = abrirBase();
        TResolucion r = null;
        String sql = "SELECT * FROM tresoluciones WHERE id=" + id;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                r = new TResolucion();
                r.setId(rs.getInt(1));
                r.setNombre(rs.getString(2));
            }
            base.close();
            return r;
        } catch (Exception e) {
            return null;

        }

    }

    public Contrato getContratoById(int id) {
        Connection base = abrirBase();
        Contrato contrato = null;
        String sql = "SELECT * FROM contratos WHERE id=" + id;

        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                contrato = new Contrato();
                contrato.setId(rs.getInt(1));
                contrato.setNumeroResolucion0(rs.getString(2));
                contrato.setNumeroResolucion1(rs.getString(3));
                contrato.setFechaResolucion(rs.getDate(4));
                contrato.setTipoTarea(rs.getString(5));
                contrato.setLugarTarea(rs.getString(6));
                contrato.setFechaDesde(rs.getDate(7));
                contrato.setFechaHasta(rs.getDate(8));
                contrato.setPesosLetra(rs.getString(9));
                contrato.setPesosNumero(rs.getBigDecimal(10));
                contrato.setViatico(rs.getBoolean(11));
                contrato.setPesosLetraViatico(rs.getString(12));
                contrato.setPesosNumeroViatico(rs.getBigDecimal(13));
                contrato.setFechaContrato(rs.getDate(14));
                contrato.setPersona(this.getPersonaById(rs.getInt(15)));
                contrato.settResolucion(this.getTResolucionById(rs.getInt(16)));
                contrato.setNumeroExpediente(rs.getString(17));
                contrato.setAreaPresupuestaria(rs.getString(18));
                contrato.setImpreso(rs.getBoolean(19));
                contrato.setSubArea(rs.getString(20));
                contrato.setBorrado(rs.getBoolean(21));

            }
            base.close();
            return contrato;
        } catch (Exception e) {
            return null;

        }

    }

   

    

    public ArrayList<TDocumento> getTDocumento() {
        Connection base = abrirBase();
        ArrayList<TDocumento> result = null;
        String sql = "SELECT * FROM tdocumentos";
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<TDocumento>();
            while (rs.next()) {
                TDocumento doc = new TDocumento();
                doc.setId(rs.getInt(1));
                doc.setNombre(rs.getString(2));
                result.add(doc);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;

        }

    }

    public TDocumento getTDocumentoById(int id) {
        Connection base = abrirBase();
        TDocumento doc = null;
        String sql = "SELECT * FROM tdocumentos WHERE id=" + id;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                doc = new TDocumento();
                doc.setId(rs.getInt(1));
                doc.setNombre(rs.getString(2));
            }
            base.close();
            return doc;
        } catch (Exception e) {
            return null;

        }

    }

    public TMoneda getTMoneda() {
        Connection base = abrirBase();
        ArchivosProperties a = new ArchivosProperties();
        Properties p = a.getProperties("general");
        String iso4217 = p.getProperty("moneda");

        TMoneda mon = null;
        String sql = "SELECT * FROM tmoneda WHERE iso4217=" + "'" + iso4217 + "';";
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                mon = new TMoneda();
                mon.setId(rs.getInt(1));
                mon.setIso4217(rs.getString(2));
                mon.setSimbolo(rs.getString(3));
                mon.setNombre(rs.getString(4));
                mon.setPlural(rs.getString(5));
                mon.setPais(rs.getString(6));
            }
            base.close();
            return mon;
        } catch (Exception e) {

            return null;

        }

    }

    public ArrayList<Persona> getPersonasDeDisposicionPago(int idDisp) {
        Connection base = abrirBase();
        ArrayList<Persona> result = null;
        String sql = "SELECT * FROM relacion_disposiciones_pago_personas WHERE id_disposicion_pago=" + idDisp;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<Persona>();
            while (rs.next()) {
                Persona p = this.getPersonaById(rs.getInt(2));
                result.add(p);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;

        }

    }

    public BigDecimal getMontoAsignadoDispPago(int id_disp, int id_persona) {
        Connection base = abrirBase();
        BigDecimal monto = null;
        String sql = "SELECT * FROM relacion_disposiciones_pago_personas WHERE id_disposicion_pago=" + id_disp + " AND id_oersona=" + id_persona;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                monto = rs.getBigDecimal(3);

            }
            base.close();
            return monto;
        } catch (Exception e) {
            return null;

        }

    }

    public ArrayList<DisposicionDePago> getDisposicionPagoByPattern(String comodin) {
        Connection base = abrirBase();
        ArrayList<DisposicionDePago> result = null;

        String sql = "SELECT * FROM disposiciones_de_pago WHERE numeroExpediente LIKE '%" + comodin + "%' OR considerando LIKE '%" + comodin + "%' OR id='" + comodin + "';";

        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<DisposicionDePago>();
            while (rs.next()) {
                DisposicionDePago d = new DisposicionDePago();
                d.setId(rs.getInt(1));
                d.setFecha(rs.getDate(2));
                d.setNumeroExpediente(rs.getInt(3));
                d.setNumeroResolucion0(rs.getInt(4));
                d.setNumeroResolucion1(rs.getInt(5));
                d.setMontoLetra(rs.getString(6));
                d.setMontoNum(rs.getBigDecimal(7));
                d.setConsiderando(rs.getString(8));
                d.setAticipoFinanciero(rs.getBoolean(9));
                d.setTransfBancaria(rs.getBoolean(10));
                result.add(d);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    public DisposicionDePago getDisposicionPagoById(int id) {
        Connection base = abrirBase();
        DisposicionDePago d = null;

        String sql = "SELECT * FROM disposiciones_de_pago WHERE id=" + id;

        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                d = new DisposicionDePago();
                d.setId(rs.getInt(1));
                d.setFecha(rs.getDate(2));
                d.setNumeroExpediente(rs.getInt(3));
                d.setNumeroResolucion0(rs.getInt(4));
                d.setNumeroResolucion1(rs.getInt(5));
                d.setMontoLetra(rs.getString(6));
                d.setMontoNum(rs.getBigDecimal(7));
                d.setConsiderando(rs.getString(8));
                d.setAticipoFinanciero(rs.getBoolean(9));
                d.setTransfBancaria(rs.getBoolean(10));

            }
            base.close();
            return d;
        } catch (Exception e) {
            return null;
        }

    }

    public int getIdUltimaDisposicion() {
        Connection base = abrirBase();
        String sql = "select * from disposiciones_de_pago order by (id) desc";
        int id = -1;
        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);

            } else {
                id = -1;
            }
            base.close();
            return id;
        } catch (Exception e) {

            return id;

        }

    }

    public ArrayList<RelacionDispPers> getRelacionDispPersByDispID(int dispId) {
        Connection base = abrirBase();
        ArrayList<RelacionDispPers> result = null;

        String sql = "SELECT * FROM relacion_disposiciones_pago_personas WHERE id_disposicion_pago=" + dispId;

        try {
            PreparedStatement query = base.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            result = new ArrayList<RelacionDispPers>();
            while (rs.next()) {
                RelacionDispPers r = new RelacionDispPers();

                r.setDisposicion(this.getDisposicionPagoById(rs.getInt(1)));
                r.setPersona(this.getPersonaById(rs.getInt(2)));
                r.setMontoAsignado(rs.getBigDecimal(3));

                result.add(r);
            }
            base.close();
            return result;
        } catch (Exception e) {
            return null;
        }

    }
}
