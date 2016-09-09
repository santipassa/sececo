package Controladores.Secundarios;

import ABMs.ABMContrato;
import Busqueda.Busqueda;
import Controladores.Primarios.ControladorVistaContratos;
import Modelos.Persona;
import Modelos.Contrato;
import Modelos.TResolucion;

import Utilidades.DoubleFormatOk;
import Utilidades.IntegerFormatOk;
import Utilidades.SqlDateFormat;
import Utilidades.NumeroALetra;
import Vistas.Secundarias.VistaCargarPersona;
import Vistas.Secundarias.VistaNuevoModificarContratoLoc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author santiago
 */
public class ControladorVistaNuevoContrato implements ActionListener {

    ControladorVistaContratos controladorVistaContratos;
    VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc;
    ABMContrato abmContrato;
    Busqueda busqueda;

    public ControladorVistaNuevoContrato(VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc, ControladorVistaContratos controladorVistaContratos) {
        this.abmContrato = new ABMContrato();
        this.controladorVistaContratos = controladorVistaContratos;
        this.vistaNuevoModificarContratoLoc = vistaNuevoModificarContratoLoc;
        this.vistaNuevoModificarContratoLoc.setActionListener(this);
        Calendar cal = Calendar.getInstance();
        vistaNuevoModificarContratoLoc.getDateFechaContrato().setDate(cal.getTime());
        this.vistaNuevoModificarContratoLoc.getDateFechaContrato().setEnabled(false);
        busqueda = new Busqueda();
        this.vistaNuevoModificarContratoLoc.getCheckViaticos().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = vistaNuevoModificarContratoLoc.getCheckViaticos().isSelected();
                if (isSelected) {
                    vistaNuevoModificarContratoLoc.getTxtMontoNumViat().setEnabled(isSelected);
                    vistaNuevoModificarContratoLoc.getTxtMontoPalViat().setEnabled(isSelected);

                } else {
                    vistaNuevoModificarContratoLoc.getTxtMontoNumViat().setEnabled(isSelected);
                    vistaNuevoModificarContratoLoc.getTxtMontoPalViat().setEnabled(isSelected);

                    vistaNuevoModificarContratoLoc.getLblGenerarViat().setText("");
                    vistaNuevoModificarContratoLoc.getTxtMontoPalViat().setText("");
                    vistaNuevoModificarContratoLoc.getTxtMontoNumViat().setText("");

                }
            }
        });
        inicializarCombos();
        this.vistaNuevoModificarContratoLoc.getLblGenerar().setText("");
        this.vistaNuevoModificarContratoLoc.getLblGenerarViat().setText("");
        this.vistaNuevoModificarContratoLoc.getTxtMontoPal().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                generarAction();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        this.vistaNuevoModificarContratoLoc.getTxtMontoPalViat().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                generarViatAction();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaNuevoModificarContratoLoc.getBtnGuardar()) {
            guardarAction();

        }
        if (e.getSource() == this.vistaNuevoModificarContratoLoc.getBtnCancelar()) {
            this.vistaNuevoModificarContratoLoc.dispose();

        }
        if (e.getSource() == this.vistaNuevoModificarContratoLoc.getBtnCargar()) {
            cargarAction();

        }

    }

    public void guardarAction() {
        Contrato contrato = new Contrato();
        Persona persona = (Persona) vistaNuevoModificarContratoLoc.getComboPersona().getSelectedItem();
        TResolucion res = (TResolucion) vistaNuevoModificarContratoLoc.getComboResolucion().getSelectedItem();
        String numRes0 = vistaNuevoModificarContratoLoc.getTxtNumeroRes0().getText();
        String numRes1 = vistaNuevoModificarContratoLoc.getTxtNumeroRes1().getText();
        String tarea = vistaNuevoModificarContratoLoc.getTxtTarea().getText();
        String lugar = vistaNuevoModificarContratoLoc.getTxtLugar().getText();
        Date desde = null;
        Date hasta = null;
        Date fechaRes = null;
        Date fechaContrato = null;
        Boolean fechaOk = false;

        try {
            desde = Date.valueOf(SqlDateFormat.dateToMySQLDate(vistaNuevoModificarContratoLoc.getDateDesde().getDate(), true));
            hasta = Date.valueOf(SqlDateFormat.dateToMySQLDate(vistaNuevoModificarContratoLoc.getDateHasta().getDate(), true));
            fechaRes = Date.valueOf(SqlDateFormat.dateToMySQLDate(vistaNuevoModificarContratoLoc.getDateFechaRes().getDate(), true));
            fechaContrato = Date.valueOf(SqlDateFormat.dateToMySQLDate(vistaNuevoModificarContratoLoc.getDateFechaContrato().getDate(), true));
            fechaOk = true;
        } catch (Exception e) {
            fechaOk = false;

        }
        String numeroExpediente = vistaNuevoModificarContratoLoc.getTxtNumExpediente().getText();
        String areaPresupuestaria = vistaNuevoModificarContratoLoc.getTxtAreaPresupuestaria().getText();
        String subArea = this.vistaNuevoModificarContratoLoc.getTxtSubArea().getText();
        String montoPal = vistaNuevoModificarContratoLoc.getTxtMontoPal().getText();
        String montoNum = vistaNuevoModificarContratoLoc.getTxtMontoNum().getText();
        Boolean viatico = vistaNuevoModificarContratoLoc.getCheckViaticos().isSelected();
        String montoPalViat = vistaNuevoModificarContratoLoc.getTxtMontoPalViat().getText();
        String montoNumViat = vistaNuevoModificarContratoLoc.getTxtMontoNumViat().getText();

        if (numRes0.isEmpty() || numRes1.isEmpty() || tarea.isEmpty() || lugar.isEmpty() || montoPal.isEmpty() || montoNum.isEmpty() || numeroExpediente.isEmpty() || areaPresupuestaria.isEmpty() || subArea.isEmpty()) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Por favor, complete todos los campos", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else if (viatico && (montoPalViat.isEmpty() || montoNumViat.isEmpty())) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Por favor, complete todos los campos", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else if (!IntegerFormatOk.FormatOK(numRes0) || !IntegerFormatOk.FormatOK(numRes0)) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Numero de resolucion inválido, recuerde poner solo números", "Atención!", JOptionPane.WARNING_MESSAGE);

        } else if (!DoubleFormatOk.FormatOK(montoNum)) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Error en el monto numérico. Solo se admiten números. Los decimales se escriben después de un . (punto)", "Atención", JOptionPane.WARNING_MESSAGE);

        } else if (!DoubleFormatOk.FormatOK(montoNumViat) && viatico) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Error en el monto numérico. Solo se admiten números. Los decimales se escriben después de un . (punto)", "Atención", JOptionPane.WARNING_MESSAGE);
        } else if (persona == null) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Error en la persona. Seleccione una persona", "Atención", JOptionPane.WARNING_MESSAGE);

        } else if (!fechaOk) {
            JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Error de fecha", "Atención", JOptionPane.WARNING_MESSAGE);
        } else {
            contrato.setPersona(persona);
            contrato.setAreaPresupuestaria(areaPresupuestaria);
            contrato.setNumeroExpediente(numeroExpediente);
            contrato.setFechaDesde(desde);
            contrato.setFechaHasta(hasta);
            contrato.setFechaResolucion(fechaRes);
            contrato.setFechaContrato(fechaContrato);
            contrato.setNumeroResolucion0(numRes0);
            contrato.setNumeroResolucion1(numRes1);
            contrato.setPesosLetra(montoPal);
            contrato.setPesosNumero(BigDecimal.valueOf(Double.valueOf(montoNum)).setScale(2, RoundingMode.CEILING));
            contrato.setViatico(viatico);
            contrato.setLugarTarea(lugar);

            contrato.settResolucion(res);
            contrato.setSubArea(subArea);
            contrato.setImpreso(false);

            contrato.setTipoTarea(tarea);
            if (viatico) {
                contrato.setPesosLetraViatico(montoPalViat);
                contrato.setPesosNumeroViatico(BigDecimal.valueOf(Double.valueOf(montoNumViat)).setScale(2, RoundingMode.CEILING));
            } else {
                contrato.setPesosLetraViatico(null);
                contrato.setPesosNumeroViatico(null);

            }

            if (this.abmContrato.alta(contrato)) {
                JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(vistaNuevoModificarContratoLoc, "Ocurrió un error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            vistaNuevoModificarContratoLoc.dispose();
            this.controladorVistaContratos.inicializarTabla();

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

    private void cargarAction() {

        VistaCargarPersona vistaCargarPersona = new VistaCargarPersona();
        vistaCargarPersona.setVisible(true);
        new ControladorCargarPersona(vistaCargarPersona, this.vistaNuevoModificarContratoLoc);

    }

    private void generarAction() {
        String numero = this.vistaNuevoModificarContratoLoc.getTxtMontoNum().getText();
        NumeroALetra nal = new NumeroALetra();
        String letras = nal.convertir(numero, true);
        if (letras == null) {
            this.vistaNuevoModificarContratoLoc.getLblGenerar().setText("Error de formato.");
        } else {
            this.vistaNuevoModificarContratoLoc.getTxtMontoPal().setText(letras);//mod esto
            this.vistaNuevoModificarContratoLoc.getLblGenerar().setText("");
        }
    }

    private void generarViatAction() {
        String numero = this.vistaNuevoModificarContratoLoc.getTxtMontoNumViat().getText();
        NumeroALetra nal = new NumeroALetra();
        String letras = nal.convertir(numero, true);
        if (letras == null) {
            this.vistaNuevoModificarContratoLoc.getLblGenerarViat().setText("Error de formato.");
        } else {
            this.vistaNuevoModificarContratoLoc.getTxtMontoPalViat().setText(letras);//mod esto
            this.vistaNuevoModificarContratoLoc.getLblGenerarViat().setText("");
        }
    }
}
