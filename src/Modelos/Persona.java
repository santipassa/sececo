package Modelos;

import java.util.ArrayList;

/**
 *
 * @author santiago
 */
public class Persona {

    Integer id;
    String nombre;
    String apellido;
    String direccion;
    String telefono;
    String documento;
    String cbu;
    TDocumento tDocumento;
    //ArrayList<ContratoLocObra> contratos;

    public Persona() {
    }

    public Integer getId() {
        return id;
    }

    public TDocumento gettDocumento() {
        return tDocumento;
    }

    public void settDocumento(TDocumento tDocumento) {
        this.tDocumento = tDocumento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /*
     public ArrayList<ContratoLocObra> getContratos() {
     return contratos;
     }

     public void setContratos(ArrayList<ContratoLocObra> contratos) {
     this.contratos = contratos;
     }
     */
    @Override
    public String toString() {
        return this.documento + " " + this.apellido + " " + this.nombre;
    }

    public String toStringPdf() {
        return this.apellido + " " + this.nombre;
    }

}
