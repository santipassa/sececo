package Modelos;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author santiago
 */
public class Contrato {

    Integer id;
    String numeroResolucion0;//
    String numeroResolucion1;//
    Date fechaResolucion;//
    String tipoTarea;//
    String lugarTarea;//
    Date fechaDesde;//
    Date fechaHasta;//
    String pesosLetra;//
    BigDecimal pesosNumero;//
    Boolean viatico;//
    String pesosLetraViatico;
    BigDecimal pesosNumeroViatico;
    Date fechaContrato;//
    Persona persona;//
    TResolucion tResolucion;//
    String numeroExpediente;
    String areaPresupuestaria;
    Boolean impreso;
    String subArea;
    Boolean borrado;

    public Contrato() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroResolucion0() {
        return numeroResolucion0;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public void setNumeroResolucion0(String numeroResolucion0) {
        this.numeroResolucion0 = numeroResolucion0;
    }

    public String getNumeroResolucion1() {
        return numeroResolucion1;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public void setNumeroResolucion1(String numeroResolucion1) {
        this.numeroResolucion1 = numeroResolucion1;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getAreaPresupuestaria() {
        return areaPresupuestaria;
    }

    public void setAreaPresupuestaria(String areaPresupuestaria) {
        this.areaPresupuestaria = areaPresupuestaria;
    }

    public TResolucion getTResolucion() {
        return tResolucion;
    }

    public void settResolucion(TResolucion tResolucion) {
        this.tResolucion = tResolucion;
    }

    public Boolean getImpreso() {
        return impreso;
    }

    public void setImpreso(Boolean impreso) {
        this.impreso = impreso;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public String getLugarTarea() {
        return lugarTarea;
    }

    public void setLugarTarea(String lugarTarea) {
        this.lugarTarea = lugarTarea;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getPesosLetra() {
        return pesosLetra;
    }

    public void setPesosLetra(String pesosLetra) {
        this.pesosLetra = pesosLetra;
    }

    public BigDecimal getPesosNumero() {
        return pesosNumero;
    }

    public void setPesosNumero(BigDecimal pesosNumero) {
        this.pesosNumero = pesosNumero;
    }

    public Boolean getViatico() {
        return viatico;
    }

    public void setViatico(Boolean viatico) {
        this.viatico = viatico;
    }

    public String getPesosLetraViatico() {
        return pesosLetraViatico;
    }

    public void setPesosLetraViatico(String pesosLetraViatico) {
        this.pesosLetraViatico = pesosLetraViatico;
    }

    public BigDecimal getPesosNumeroViatico() {
        return pesosNumeroViatico;
    }

    public void setPesosNumeroViatico(BigDecimal pesosNumeroViatico) {
        this.pesosNumeroViatico = pesosNumeroViatico;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
