package Controladores.Secundarios;

import ABMs.ABMDisposicionDePago;
import ABMs.ABMRelacionDispPers;
import Busqueda.Busqueda;
import Controladores.Primarios.ControladorVistaDisposiciones;
import Modelos.DisposicionDePago;
import Modelos.Persona;
import Modelos.Relaciones.RelacionDispPers;
import Utilidades.DoubleFormatOk;
import Utilidades.ForcedListSelectionModel;
import Utilidades.IntegerFormatOk;
import Utilidades.NumeroALetra;
import Utilidades.SqlDateFormat;
import Vistas.Secundarias.VistaNuevaModificarDisposicionPago;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author santiago
 */
public class ControladorVistaNuevaDisposicionPago implements ActionListener {

    VistaNuevaModificarDisposicionPago vistaNuevaModificarDisposicionPago;
    Busqueda busqueda;
    ABMDisposicionDePago abmDisposicionDePago;
    ControladorVistaDisposiciones controladorVistaDisposiciones;
    ABMRelacionDispPers abmRelacionDispPers;

    public ControladorVistaNuevaDisposicionPago(VistaNuevaModificarDisposicionPago vistaNuevaModificarDisposicionPago, ControladorVistaDisposiciones controladorVistaDisposiciones) {
        this.vistaNuevaModificarDisposicionPago = vistaNuevaModificarDisposicionPago;
        this.controladorVistaDisposiciones = controladorVistaDisposiciones;
        busqueda = new Busqueda();
        abmRelacionDispPers = new ABMRelacionDispPers();
        abmDisposicionDePago = new ABMDisposicionDePago();
        this.vistaNuevaModificarDisposicionPago.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        this.vistaNuevaModificarDisposicionPago.getTablePersonas().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    doubleClickAction(row);
                }
            }
        });
        this.vistaNuevaModificarDisposicionPago.getTablePago().setSelectionModel(new ForcedListSelectionModel());
        this.vistaNuevaModificarDisposicionPago.getTablePersonas().setSelectionModel(new ForcedListSelectionModel());
        inicializarTablaPersonas();
        this.vistaNuevaModificarDisposicionPago.setActionListener(this);
        this.vistaNuevaModificarDisposicionPago.getLblGenerar().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaNuevaModificarDisposicionPago.getBtnCancelar()) {
            this.vistaNuevaModificarDisposicionPago.dispose();
        }
        if (e.getSource() == this.vistaNuevaModificarDisposicionPago.getBtnGenerar()) {
            generarAction();

        }
        if (e.getSource() == this.vistaNuevaModificarDisposicionPago.getBtnAgregar()) {
            agregarAction();
        }
        if (e.getSource() == this.vistaNuevaModificarDisposicionPago.getBtnQuitar()) {
            quitarAction();
        }
        if (e.getSource() == this.vistaNuevaModificarDisposicionPago.getBtnGuardar()) {
            guardarAction();

        }
    }

    private void inicializarTablaPersonas() {
        this.vistaNuevaModificarDisposicionPago.getTablaPersonasDefault().setRowCount(0);
        ArrayList<Persona> listaPersonas = busqueda.getPersonasByPattern(this.vistaNuevaModificarDisposicionPago.getTxtBuscar().getText());
        for (Persona p : listaPersonas) {
            Object row[] = new Object[4];
            row[0] = p.getId();
            row[1] = p.getNombre();
            row[2] = p.getApellido();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            this.vistaNuevaModificarDisposicionPago.getTablaPersonasDefault().addRow(row);
        }
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        this.vistaNuevaModificarDisposicionPago.getTablaPersonasDefault().setRowCount(0);
        ArrayList<Persona> listaPersonas = busqueda.getPersonasByPattern(this.vistaNuevaModificarDisposicionPago.getTxtBuscar().getText());
        for (Persona p : listaPersonas) {
            Object row[] = new Object[4];
            row[0] = p.getId();
            row[1] = p.getNombre();
            row[2] = p.getApellido();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            this.vistaNuevaModificarDisposicionPago.getTablaPersonasDefault().addRow(row);
        }

    }

    private void generarAction() {

        try {
            this.vistaNuevaModificarDisposicionPago.getTxtMontoTotalNum().setText(getSumatoria().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.vistaNuevaModificarDisposicionPago, "Error en el monto numérico. Solo se admiten números. Los decimales se escriben después de un . (punto)", "Atención", JOptionPane.WARNING_MESSAGE);

        }

        String numero = this.vistaNuevaModificarDisposicionPago.getTxtMontoTotalNum().getText();
        NumeroALetra nal = new NumeroALetra();
        String letras = nal.convertir(numero, true);
        if (letras == null) {
            this.vistaNuevaModificarDisposicionPago.getLblGenerar().setText("Error de formato.");
        } else {
            this.vistaNuevaModificarDisposicionPago.getTxtMontoTotalPal().setText(letras);
            this.vistaNuevaModificarDisposicionPago.getLblGenerar().setText("");
        }

    }

    private void doubleClickAction(int rowNum) {
        agregarAction(rowNum);

    }

    private Boolean isInTable(int id) {
        boolean result = false;
        int id_actual;
        for (int i = 0; i < this.vistaNuevaModificarDisposicionPago.getTablePago().getRowCount(); i++) {
            id_actual = (int) this.vistaNuevaModificarDisposicionPago.getTablePago().getValueAt(i, 0);
            result = result || (id == id_actual);
        }
        return result;
    }

    private Boolean montosOk() {
        try {
            BigDecimal sumatoria = BigDecimal.ZERO;
            String montoActualStr;
            BigDecimal montoActualBD;
            for (int i = 0; i < this.vistaNuevaModificarDisposicionPago.getTablePago().getRowCount(); i++) {
                montoActualStr = (String) this.vistaNuevaModificarDisposicionPago.getTablePago().getValueAt(i, 4);
                montoActualBD = BigDecimal.valueOf(Double.valueOf(montoActualStr)).setScale(2, RoundingMode.CEILING);
                sumatoria = sumatoria.add(montoActualBD).setScale(2, RoundingMode.CEILING);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private Boolean isEmptyTablePago() {
        return this.vistaNuevaModificarDisposicionPago.getTablePago().getRowCount() == 0;
    }

    private void agregarAction() {
        int rowNum = this.vistaNuevaModificarDisposicionPago.getTablePersonas().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Debe seleccionar un elemento.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            agregarAction(rowNum);

        }
    }

    private void quitarAction() {
        int rowNum = this.vistaNuevaModificarDisposicionPago.getTablePago().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(this.vistaNuevaModificarDisposicionPago, "Debe seleccionar el elemento a quitar.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            this.vistaNuevaModificarDisposicionPago.getTablaPagoDefault().removeRow(rowNum);
        }

    }

    private void agregarAction(int rowNum) {
        int id = (int) this.vistaNuevaModificarDisposicionPago.getTablaPersonasDefault().getValueAt(rowNum, 0);
        if (!isInTable(id)) {
            Persona p = busqueda.getPersonaById(id);
            Object row[] = new Object[4];
            row[0] = p.getId();
            row[1] = p.getNombre();
            row[2] = p.getApellido();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            this.vistaNuevaModificarDisposicionPago.getTablaPagoDefault().addRow(row);

        }

    }

    private BigDecimal getSumatoria() {
        BigDecimal sumatoria = BigDecimal.ZERO;
        String montoActualStr;
        BigDecimal montoActualBD;
        for (int i = 0; i < this.vistaNuevaModificarDisposicionPago.getTablePago().getRowCount(); i++) {
            montoActualStr = (String) this.vistaNuevaModificarDisposicionPago.getTablePago().getValueAt(i, 4);
            montoActualBD = BigDecimal.valueOf(Double.valueOf(montoActualStr)).setScale(2, RoundingMode.CEILING);
            sumatoria = sumatoria.add(montoActualBD).setScale(2, RoundingMode.CEILING);

        }
        return sumatoria;

    }

    private void guardarAction() {
        Date fecha = null;
        Boolean fechaOk = false;
        String expedienteNum = this.vistaNuevaModificarDisposicionPago.getTxtExpedienteNum().getText();
        String considerando = this.vistaNuevaModificarDisposicionPago.getTxtPeticionadoPor().getText();
        String numRes0 = this.vistaNuevaModificarDisposicionPago.getTxtNumeroRes0().getText();
        String numRes1 = this.vistaNuevaModificarDisposicionPago.getTxtNumeroRes1().getText();
        String montoTotal = this.vistaNuevaModificarDisposicionPago.getTxtMontoTotalNum().getText();
        String montoTotalPal = this.vistaNuevaModificarDisposicionPago.getTxtMontoTotalPal().getText();
        Boolean anticipoFinanciero = this.vistaNuevaModificarDisposicionPago.getCheckAnticipo().isSelected();
        Boolean transfBco = this.vistaNuevaModificarDisposicionPago.getCheckPagoTransf().isSelected();
        try {
            fecha = Date.valueOf(SqlDateFormat.dateToMySQLDate(this.vistaNuevaModificarDisposicionPago.getDateFecha().getDate(), true));
            fechaOk = true;
        } catch (Exception e) {
            fechaOk = false;
        }
        if (expedienteNum.isEmpty() || considerando.isEmpty() || montoTotal.isEmpty() || montoTotalPal.isEmpty()) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Por favor, complete todos los campos.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else if (!IntegerFormatOk.FormatOK(expedienteNum)) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Error en el N° de expediente. Solo se admiten números ", "Atención", JOptionPane.WARNING_MESSAGE);
        } else if (!DoubleFormatOk.FormatOK(montoTotal)) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Error en el monto total numérico. Solo se admiten números. Los decimales se escriben después de un . (punto)", "Atención", JOptionPane.WARNING_MESSAGE);
        } else if (!fechaOk) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Error de fecha.", "Atención", JOptionPane.WARNING_MESSAGE);
        } else if (isEmptyTablePago()) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Tabla de pagos vacia.", "Atención", JOptionPane.WARNING_MESSAGE);
        } else if (!montosOk()) {
            JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Error en los montos asignados", "Atención", JOptionPane.WARNING_MESSAGE);

        } else {
            DisposicionDePago d = new DisposicionDePago();
            d.setAticipoFinanciero(anticipoFinanciero);
            d.setConsiderando(considerando);
            d.setFecha(fecha);
            d.setMontoLetra(montoTotalPal);
            d.setMontoNum(BigDecimal.valueOf(Double.valueOf(montoTotal)).setScale(2, RoundingMode.CEILING));
            d.setTransfBancaria(transfBco);
            d.setNumeroExpediente(Integer.parseInt(expedienteNum));
            d.setNumeroResolucion0(Integer.parseInt(numRes0));
            d.setNumeroResolucion1(Integer.parseInt(numRes1));
            if (this.abmDisposicionDePago.alta(d)) {
                RelacionDispPers r;// = new RelacionDispPers();
                int idDisp = busqueda.getIdUltimaDisposicion();
                int idPersona;
                BigDecimal montoAsignado;
                String montoAsignadoString;
                for (int i = 0; i < this.vistaNuevaModificarDisposicionPago.getTablePago().getRowCount(); i++) {
                    idPersona = (Integer) this.vistaNuevaModificarDisposicionPago.getTablePago().getValueAt(i, 0);
                    montoAsignadoString = (String) this.vistaNuevaModificarDisposicionPago.getTablePago().getValueAt(i, 4);
                    montoAsignado = BigDecimal.valueOf(Double.valueOf(montoAsignadoString)).setScale(2, RoundingMode.CEILING);
                    r = new RelacionDispPers();
                    //r.setId_disposicion_pago(idDisp);
                    //r.setId_persona(idPersona);
                    r.setMontoAsignado(montoAsignado);
                    this.abmRelacionDispPers.alta(r);
                }
                JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Agregado correctamente");
                this.vistaNuevaModificarDisposicionPago.dispose();
                this.controladorVistaDisposiciones.inicializarTabla();
            } else {
                JOptionPane.showMessageDialog(vistaNuevaModificarDisposicionPago, "Ocurrio un error", "Atención", JOptionPane.WARNING_MESSAGE);

            }

        }
    }

}
