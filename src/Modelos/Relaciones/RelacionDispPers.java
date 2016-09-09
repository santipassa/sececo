package Modelos.Relaciones;

import Modelos.DisposicionDePago;
import Modelos.Persona;
import java.math.BigDecimal;

/**
 *
 * @author santiago
 */
public class RelacionDispPers {

    Persona persona;
    DisposicionDePago disposicion;
    BigDecimal montoAsignado;

    public RelacionDispPers() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public DisposicionDePago getDisposicion() {
        return disposicion;
    }

    public void setDisposicion(DisposicionDePago disposicion) {
        this.disposicion = disposicion;
    }

    public BigDecimal getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(BigDecimal montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

}
