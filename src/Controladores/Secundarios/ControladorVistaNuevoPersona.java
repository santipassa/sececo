package Controladores.Secundarios;

import Controladores.Primarios.ControladorVistaPersonas;
import ABMs.ABMPersona;
import Busqueda.Busqueda;
import Modelos.Persona;
import Modelos.Persona;
import Modelos.TDocumento;
import Utilidades.ValidateCBU;
import Vistas.Secundarias.VistaNuevoModificarPersona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author santiago
 */
public class ControladorVistaNuevoPersona implements ActionListener {

    VistaNuevoModificarPersona vistaNuevoModificarPersona;
    ControladorVistaPersonas controladorVistaPersonas;
    ABMPersona abmPersona;
    Busqueda busqueda;

    public ControladorVistaNuevoPersona(VistaNuevoModificarPersona vistaNuevoModificarPersona, ControladorVistaPersonas controladorVistaPersonas) {
        this.vistaNuevoModificarPersona = vistaNuevoModificarPersona;
        this.controladorVistaPersonas = controladorVistaPersonas;
        vistaNuevoModificarPersona.setActionListener(this);
        abmPersona = new ABMPersona();
        busqueda = new Busqueda();
        inicializarCombos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vistaNuevoModificarPersona.getBtnGuardar())) {
            guardarAction();
        }
        if (e.getSource().equals(vistaNuevoModificarPersona.getBtnCancelar())) {
            vistaNuevoModificarPersona.dispose();
        }
    }

    public void guardarAction() {
        Persona p = new Persona();
        String nombre = vistaNuevoModificarPersona.getTxtNombre().getText();
        String apellido = vistaNuevoModificarPersona.getTxtApellido().getText();
        String doc = vistaNuevoModificarPersona.getTxtDoc().getText();
        String telefono = vistaNuevoModificarPersona.getTxtTelefono().getText();
        String direccion = vistaNuevoModificarPersona.getTxtDireccion().getText();
        String cbu = this.vistaNuevoModificarPersona.getTxtCbu().getText();
        TDocumento tdoc = (TDocumento) this.vistaNuevoModificarPersona.getComboTipoDoc().getSelectedItem();
        if (nombre.isEmpty() || apellido.isEmpty() || doc.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(vistaNuevoModificarPersona, "Por favor, complete todos los campos marcados con *.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else if (!cbu.isEmpty() && !ValidateCBU.getValidation(cbu)) {
            JOptionPane.showMessageDialog(vistaNuevoModificarPersona, "Formato de CBU inválido, debe contener 22 caracteres", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDocumento(doc);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setCbu(cbu);
            p.settDocumento(tdoc);
            if (abmPersona.alta(p)) {
                JOptionPane.showMessageDialog(vistaNuevoModificarPersona, "Agregada correctamente.");
            } else {
                JOptionPane.showMessageDialog(vistaNuevoModificarPersona, "Ocurrió un error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            vistaNuevoModificarPersona.dispose();
            this.controladorVistaPersonas.inicializarTabla();

        }
    }

    public void inicializarCombos() {
        ArrayList<TDocumento> resultDoc = busqueda.getTDocumento();
        this.vistaNuevoModificarPersona.getComboTipoDoc().removeAllItems();
        for (TDocumento d : resultDoc) {
            this.vistaNuevoModificarPersona.getComboTipoDoc().addItem(d);
        }

    }

}
