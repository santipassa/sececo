package Controladores.Secundarios;

import Busqueda.Busqueda;
import Modelos.Persona;
import Utilidades.ForcedListSelectionModel;
import Vistas.Secundarias.VistaCargarPersona;
import Vistas.Secundarias.VistaNuevoModificarContratoLoc;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author santiago
 */
public class ControladorCargarPersona implements ActionListener {

    VistaCargarPersona vistaCargarPersona;
    VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc;
    Busqueda busqueda;

    public ControladorCargarPersona(VistaCargarPersona vistaCargarPersona, VistaNuevoModificarContratoLoc vistaNuevoModificarContratoLoc) {
        this.vistaCargarPersona = vistaCargarPersona;
        this.vistaCargarPersona.setActionListener(this);
        this.vistaNuevoModificarContratoLoc = vistaNuevoModificarContratoLoc;
        this.busqueda = new Busqueda();
        this.vistaCargarPersona.getTablePersonas().setSelectionModel(new ForcedListSelectionModel());
        vistaCargarPersona.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        inicializarTabla();
        this.vistaCargarPersona.getTablePersonas().addMouseListener(new MouseAdapter() {
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
        if (e.getSource() == vistaCargarPersona.getBtnSeleccionar()) {
            seleccionarAction();
        }
    }

    public void inicializarTabla() {
        this.vistaCargarPersona.getTablaPersonasDefault().setRowCount(0);
        ArrayList<Persona> listaPersonas = busqueda.getPersonasByPattern(vistaCargarPersona.getTxtBuscar().getText());
        for (Persona p : listaPersonas) {
            Object row[] = new Object[7];
            row[0] = p.getId();
            row[1] = p.getNombre();
            row[2] = p.getApellido();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            row[4] = p.getDireccion();
            row[5] = p.getTelefono();
            row[6] = p.getCbu();
            this.vistaCargarPersona.getTablaPersonasDefault().addRow(row);
        }
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        ArrayList<Persona> listaPersonas = busqueda.getPersonasByPattern(vistaCargarPersona.getTxtBuscar().getText());
        this.vistaCargarPersona.getTablaPersonasDefault().setRowCount(0);
        for (Persona p : listaPersonas) {
            Object row[] = new Object[7];
            row[0] = p.getId();
            row[1] = p.getNombre();
            row[2] = p.getApellido();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            row[4] = p.getDireccion();
            row[5] = p.getTelefono();
            row[6] = p.getCbu();
            this.vistaCargarPersona.getTablaPersonasDefault().addRow(row);
        }

    }

    private void seleccionarAction() {
        vistaNuevoModificarContratoLoc.getComboPersona().removeAllItems();
        int rowNum = vistaCargarPersona.getTablePersonas().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaCargarPersona, "Debe seleccionar un elemento.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            int id = (int) vistaCargarPersona.getTablaPersonasDefault().getValueAt(rowNum, 0);
            this.vistaNuevoModificarContratoLoc.getComboPersona().addItem(busqueda.getPersonaById(id));
            this.vistaCargarPersona.dispose();

        }

    }

    private void doubleClickAction(int rowNum) {
        vistaNuevoModificarContratoLoc.getComboPersona().removeAllItems();
        int id = (int) vistaCargarPersona.getTablaPersonasDefault().getValueAt(rowNum, 0);
        this.vistaNuevoModificarContratoLoc.getComboPersona().addItem(busqueda.getPersonaById(id));
        this.vistaCargarPersona.dispose();

    }

}
