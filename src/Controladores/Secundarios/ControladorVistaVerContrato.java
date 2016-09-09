package Controladores.Secundarios;

import ABMs.ABMContrato;
import Busqueda.Busqueda;
import Controladores.Primarios.ControladorVistaContratos;
import Modelos.Contrato;
import Modelos.TResolucion;
import Vistas.Secundarias.VistaNuevoModificarContratoLoc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author santiago
 */
public class ControladorVistaVerContrato implements ActionListener {

    ControladorVistaContratos controladorVistaContratos;
    VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc;
    ABMContrato abmContratoLocObra;
    Busqueda busqueda;
    Contrato contrato;

    public ControladorVistaVerContrato(VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc, ControladorVistaContratos controladorVistaContratos, Contrato contrato) {
        this.abmContratoLocObra = new ABMContrato();
        this.controladorVistaContratos = controladorVistaContratos;
        this.vistaNuevoModificarContratoLoc = vistaNuevoModificarContratoLoc;
        this.vistaNuevoModificarContratoLoc.setActionListener(this);
        this.contrato = contrato;
        busqueda = new Busqueda();
        inicializarCombos();
        this.vistaNuevoModificarContratoLoc.getComboResolucion().setSelectedItem(contrato.getTResolucion());
        this.vistaNuevoModificarContratoLoc.getCheckViaticos().setSelected(contrato.getViatico());
        this.vistaNuevoModificarContratoLoc.getComboPersona().addItem(contrato.getPersona());
        this.vistaNuevoModificarContratoLoc.getTxtAreaPresupuestaria().setText(contrato.getAreaPresupuestaria());
        this.vistaNuevoModificarContratoLoc.getTxtSubArea().setText(contrato.getSubArea());
        this.vistaNuevoModificarContratoLoc.getTxtNumExpediente().setText(contrato.getNumeroExpediente());
        this.vistaNuevoModificarContratoLoc.getTxtLugar().setText(contrato.getLugarTarea());
        this.vistaNuevoModificarContratoLoc.getTxtNumeroRes0().setText(contrato.getNumeroResolucion0());
        this.vistaNuevoModificarContratoLoc.getTxtNumeroRes1().setText(contrato.getNumeroResolucion1());
        this.vistaNuevoModificarContratoLoc.getTxtMontoNum().setText(contrato.getPesosNumero().toString());
        this.vistaNuevoModificarContratoLoc.getTxtMontoPal().setText(contrato.getPesosLetra());

        if (contrato.getViatico()) {
            this.vistaNuevoModificarContratoLoc.getTxtMontoNumViat().setText(contrato.getPesosNumeroViatico().toString());
            this.vistaNuevoModificarContratoLoc.getTxtMontoPalViat().setText(contrato.getPesosLetraViatico());
        }
        this.vistaNuevoModificarContratoLoc.getDateDesde().setDate(contrato.getFechaDesde());
        this.vistaNuevoModificarContratoLoc.getDateHasta().setDate(contrato.getFechaHasta());
        this.vistaNuevoModificarContratoLoc.getDateFechaContrato().setDate(contrato.getFechaContrato());
        this.vistaNuevoModificarContratoLoc.getDateFechaRes().setDate(contrato.getFechaResolucion());
        this.vistaNuevoModificarContratoLoc.getTxtTarea().setText(contrato.getTipoTarea());
        //desactivo todo
        this.vistaNuevoModificarContratoLoc.getComboResolucion().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getCheckViaticos().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getComboPersona().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtAreaPresupuestaria().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtSubArea().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtNumExpediente().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtLugar().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtNumeroRes0().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtNumeroRes1().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtMontoNum().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtMontoPal().setEnabled(false);

        this.vistaNuevoModificarContratoLoc.getLblGenerar().setText("");
        this.vistaNuevoModificarContratoLoc.getLblGenerarViat().setText("");
        if (contrato.getViatico()) {
            this.vistaNuevoModificarContratoLoc.getTxtMontoNumViat().setEnabled(false);
            this.vistaNuevoModificarContratoLoc.getTxtMontoPalViat().setEnabled(false);
        }
        this.vistaNuevoModificarContratoLoc.getDateDesde().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getDateHasta().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getDateFechaContrato().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getDateFechaRes().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getTxtTarea().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getBtnGuardar().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getBtnCargar().setEnabled(false);
        this.vistaNuevoModificarContratoLoc.getBtnCancelar().setText("Cerrar");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vistaNuevoModificarContratoLoc.getBtnCancelar()) {
            this.vistaNuevoModificarContratoLoc.dispose();

        }
    }

    public void inicializarCombos() {
        ArrayList<TResolucion> resultRes = busqueda.getTResolucion();
        vistaNuevoModificarContratoLoc.getComboResolucion().removeAllItems();
        for (TResolucion r : resultRes) {
            vistaNuevoModificarContratoLoc.getComboResolucion().addItem(r);
        }
        vistaNuevoModificarContratoLoc.getComboPersona().removeAllItems();

    }

}
