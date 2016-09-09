package Modelos;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author santiago
 */
public class DisposicionDePago {

    Integer id;
    Date fecha;
    Integer numeroExpediente;
    Integer numeroResolucion0;//
    Integer numeroResolucion1;//
    ArrayList<Persona> personas;
    String montoLetra;
    BigDecimal montoNum;
    String considerando;
    Boolean aticipoFinanciero;
    Boolean transfBancaria;

    public DisposicionDePago() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(Integer numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Integer getNumeroResolucion0() {
        return numeroResolucion0;
    }

    public void setNumeroResolucion0(Integer numeroResolucion0) {
        this.numeroResolucion0 = numeroResolucion0;
    }

    public Integer getNumeroResolucion1() {
        return numeroResolucion1;
    }

    public void setNumeroResolucion1(Integer numeroResolucion1) {
        this.numeroResolucion1 = numeroResolucion1;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public String getMontoLetra() {
        return montoLetra;
    }

    public void setMontoLetra(String montoLetra) {
        this.montoLetra = montoLetra;
    }

    public BigDecimal getMontoNum() {
        return montoNum;
    }

    public void setMontoNum(BigDecimal montoNum) {
        this.montoNum = montoNum;
    }

    public String getConsiderando() {
        return considerando;
    }

    public void setConsiderando(String considerando) {
        this.considerando = considerando;
    }

    public Boolean getAticipoFinanciero() {
        return aticipoFinanciero;
    }

    public void setAticipoFinanciero(Boolean aticipoFinanciero) {
        this.aticipoFinanciero = aticipoFinanciero;
    }

    public Boolean getTransfBancaria() {
        return transfBancaria;
    }

    public void setTransfBancaria(Boolean transfBancaria) {
        this.transfBancaria = transfBancaria;
    }

}
