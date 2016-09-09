package Controladores.Primarios;

import ABMs.ABMContrato;
import Busqueda.Busqueda;
import Controladores.Secundarios.ControladorVistaModificarContrato;
import Controladores.Secundarios.ControladorVistaNuevoContrato;
import Controladores.Secundarios.ControladorVistaVerContrato;
import Modelos.Contrato;
import Reportes.SaveReportToPdf;
import Utilidades.ArchivosProperties;
import Utilidades.ForcedListSelectionModel;
import Utilidades.SqlDateFormat;
import Vistas.Primarias.VistaContratos;
import Vistas.Secundarias.VistaNuevoModificarContratoLoc;
import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author santiago
 */
public class ControladorVistaContratos implements ActionListener {

    VistaContratos vistaContratos;
    Busqueda busqueda;
    ABMContrato abmContrato;
    ControladorVistaNuevoContrato controladorVistaNuevoContrato;
    ControladorVistaModificarContrato controladorVistaModificarContrato;
    ControladorVistaVerContrato controladorVistaVerContrato;

    public ControladorVistaContratos(VistaContratos vistaContratos) {
        this.vistaContratos = vistaContratos;
        this.vistaContratos.setActionListener(this);
        this.vistaContratos.getTableContratos().setSelectionModel(new ForcedListSelectionModel());
        busqueda = new Busqueda();
        abmContrato = new ABMContrato();
        inicializarTabla();
        this.vistaContratos.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }

        });
        this.vistaContratos.getTableContratos().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    doubleClickAction(row);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vistaContratos.getBtnEliminar())) {
            eliminarAction();

        }
        if (e.getSource().equals(vistaContratos.getBtnNuevo())) {
            nuevoAction();

        }
        if (e.getSource().equals(vistaContratos.getBtnModificar())) {
            modificarAction();

        }
        if (e.getSource().equals(vistaContratos.getBtnGenerarPdf())) {
            try {
                generarPdfAction();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVistaContratos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ControladorVistaContratos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource().equals(vistaContratos.getBtnVerDetalle())) {
            verDetalleAction();

        }
        if (e.getSource().equals(vistaContratos.getBtnVerArchivo())) {
           verArchivoAction();

        }

    }

    private void eliminarAction() {
        int rowNum = vistaContratos.getTableContratos().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaContratos, "Debe seleccionar el elemento a eliminar.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            int id = (int) vistaContratos.getTablaContratosDefault().getValueAt(rowNum, 0);
            Contrato c = busqueda.getContratoById(id);
            if (c.getImpreso()) {
                JOptionPane.showMessageDialog(vistaContratos, "No es posible eliminar un contrato ya impreso.", "Atención!", JOptionPane.WARNING_MESSAGE);
            } else if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(vistaContratos, "¿Desea eliminar el elemento seleccionado?", "Confirmar borrado de datos", JOptionPane.YES_NO_OPTION)) {
                vistaContratos.getTablaContratosDefault().removeRow(rowNum);
                abmContrato.baja(c);
                inicializarTabla();

            }

        }

    }

    public void inicializarTabla() {
        vistaContratos.getTablaContratosDefault().setRowCount(0);
        ArrayList<Contrato> listaContratos = busqueda.getContratosByPattern(vistaContratos.getTxtBuscar().getText());
        for (Contrato c : listaContratos) {
            Object row[] = new Object[8];
            row[0] = c.getId();
            row[1] = c.getNumeroResolucion0() + "/" + c.getNumeroResolucion1();
            row[2] = c.getNumeroExpediente();
            row[3] = c.getAreaPresupuestaria() + "-" + c.getSubArea();
            row[4] = c.getPersona().getApellido() + " " + c.getPersona().getNombre();
            row[5] = SqlDateFormat.dateToMySQLDate(c.getFechaContrato(), false);
            row[6] = "Desde " + SqlDateFormat.dateToMySQLDate(c.getFechaDesde(), false) + " Hasta " + SqlDateFormat.dateToMySQLDate(c.getFechaHasta(), false);
            row[7] = c.getBorrado() != false ? "Baja" : "Activo";
            this.vistaContratos.getTablaContratosDefault().addRow(row);
        }

    }

    private void nuevoAction() {
        VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc = new VistaNuevoModificarContratoLoc();
        vistaNuevoModificarContratoLoc.setTitle("Nuevo contrato");
        vistaNuevoModificarContratoLoc.setVisible(true);
        vistaNuevoModificarContratoLoc.setLocationRelativeTo(null);
        controladorVistaNuevoContrato = new ControladorVistaNuevoContrato(vistaNuevoModificarContratoLoc, this);

    }

    private void busquedaKeyReleased(KeyEvent evt) {
        ArrayList<Contrato> listaContratos = busqueda.getContratosByPattern(vistaContratos.getTxtBuscar().getText());
        this.vistaContratos.getTablaContratosDefault().setRowCount(0);
        for (Contrato c : listaContratos) {
            Object row[] = new Object[8];
            row[0] = c.getId();
            row[1] = c.getNumeroResolucion0() + "/" + c.getNumeroResolucion1();
            row[2] = c.getNumeroExpediente();
            row[3] = c.getAreaPresupuestaria() + "-" + c.getSubArea();
            row[4] = c.getPersona().getApellido() + " " + c.getPersona().getNombre();
            row[5] = SqlDateFormat.dateToMySQLDate(c.getFechaContrato(), false);
            row[6] = "Desde " + SqlDateFormat.dateToMySQLDate(c.getFechaDesde(), false) + " Hasta " + SqlDateFormat.dateToMySQLDate(c.getFechaHasta(), false);
            row[7] = c.getBorrado() != false ? "Baja" : "Activo";
            this.vistaContratos.getTablaContratosDefault().addRow(row);
        }

    }

    private void modificarAction() {
        int rowNum = vistaContratos.getTableContratos().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaContratos, "Debe seleccionar el elemento a modificar.", "Atención!", JOptionPane.WARNING_MESSAGE);

        } else {

            int id = (int) this.vistaContratos.getTablaContratosDefault().getValueAt(rowNum, 0);
            Contrato contrato = busqueda.getContratoById(id);
            if (contrato.getImpreso()) {
                JOptionPane.showMessageDialog(vistaContratos, "No es posible modificar un contrato ya impreso.", "Atención!", JOptionPane.WARNING_MESSAGE);
            } else {
                VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc = new VistaNuevoModificarContratoLoc();
                vistaNuevoModificarContratoLoc.setTitle("Modificar contrato");
                vistaNuevoModificarContratoLoc.setVisible(true);
                vistaNuevoModificarContratoLoc.setLocationRelativeTo(null);
                controladorVistaModificarContrato = new ControladorVistaModificarContrato(vistaNuevoModificarContratoLoc, this, contrato);
            }
        }

    }

    private void generarPdfAction() throws FileNotFoundException, DocumentException {
        int rowNum = vistaContratos.getTableContratos().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaContratos, "Debe seleccionar un elemento.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                int id = (int) this.vistaContratos.getTablaContratosDefault().getValueAt(rowNum, 0);
                Contrato contrato = busqueda.getContratoById(id);
                SaveReportToPdf saver = new SaveReportToPdf();
                ArchivosProperties a = new ArchivosProperties();
                Properties p = a.getProperties("general");

                String ruta = p.getProperty("pathContratos") + "contrato_" + formatIdContrato(id);
                boolean stat = saver.save(contrato);
                if (!stat) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    File path = new File(ruta + ".pdf");
                    Desktop.getDesktop().open(path);
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Ocurrió un error " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void verDetalleAction() {
        int rowNum = vistaContratos.getTableContratos().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaContratos, "Debe seleccionar un elemento.", "Atención!", JOptionPane.WARNING_MESSAGE);

        } else {

            int id = (int) this.vistaContratos.getTablaContratosDefault().getValueAt(rowNum, 0);
            Contrato contrato = busqueda.getContratoById(id);

            VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc = new VistaNuevoModificarContratoLoc();
            vistaNuevoModificarContratoLoc.setTitle("Ver contrato");
            vistaNuevoModificarContratoLoc.setVisible(true);
            vistaNuevoModificarContratoLoc.setLocationRelativeTo(null);

            controladorVistaVerContrato = new ControladorVistaVerContrato(vistaNuevoModificarContratoLoc, this, contrato);

        }

    }

    private void doubleClickAction(int rowNum) {
        int id = (int) this.vistaContratos.getTablaContratosDefault().getValueAt(rowNum, 0);
        Contrato contrato = busqueda.getContratoById(id);

        VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc = new VistaNuevoModificarContratoLoc();
        vistaNuevoModificarContratoLoc.setTitle("Ver contrato");
        vistaNuevoModificarContratoLoc.setVisible(true);
        vistaNuevoModificarContratoLoc.setLocationRelativeTo(null);

        controladorVistaVerContrato = new ControladorVistaVerContrato(vistaNuevoModificarContratoLoc, this, contrato);

    }

    public String formatIdContrato(Integer id) {
        String idStr = Integer.toString(id);
        Integer largoStr = idStr.length();
        switch (largoStr) {
            case 1:
                idStr = "00000" + idStr;
                break;
            case 2:
                idStr = "0000" + idStr;
                break;
            case 3:
                idStr = "000" + idStr;
                break;
            case 4:
                idStr = "00" + idStr;
                break;
            case 5:
                idStr = "0" + idStr;
                break;
            case 6:
                idStr = idStr;
                break;

            default:
                idStr = idStr;
                break;
        }
        return idStr;
    }
    private void verArchivoAction() {
        int rowNum = this.vistaContratos.getTableContratos().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(this.vistaContratos, "Debe seleccionar un elemento.", "Atenci\u00f3n!", 2);
        } else {
            int id = (Integer)this.vistaContratos.getTablaContratosDefault().getValueAt(rowNum, 0);
            Contrato contrato = this.busqueda.getContratoById(id);
            String contratName = "contrato_" + this.formatIdContrato(id) + ".pdf";
            ArchivosProperties archivo = new ArchivosProperties();
            Properties p = archivo.getProperties("general");
            String path = p.getProperty("pathContratos");
            path = path + contratName;
            try {
                File pdf = new File(path);
                Desktop.getDesktop().open(pdf);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurri\u00f3 un error al abrir el archivo", "Error", 0);
            }
        }
    }

}
