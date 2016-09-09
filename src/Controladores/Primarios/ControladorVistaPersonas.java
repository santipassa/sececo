package Controladores.Primarios;

import ABMs.ABMPersona;
import Busqueda.Busqueda;
import Controladores.Secundarios.ControladorVistaModificarPersona;
import Controladores.Secundarios.ControladorVistaNuevoPersona;
import Modelos.Persona;
import Utilidades.ForcedListSelectionModel;
import Vistas.Primarias.VistaPersonas;
import Vistas.Secundarias.VistaNuevoModificarPersona;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControladorVistaPersonas implements ActionListener {

    VistaPersonas vistaPersonas;
    Busqueda busqueda;
    ABMPersona abmPersona;
    ControladorVistaNuevoPersona controladorVistaNuevoPersona;
    ControladorVistaModificarPersona controladorVistaModificarPersona;

    public ControladorVistaPersonas(VistaPersonas vistaPersonas) {
        this.vistaPersonas = vistaPersonas;
        this.vistaPersonas.setActionListener(this);
        busqueda = new Busqueda();
        abmPersona = new ABMPersona();
        this.vistaPersonas.getTablePersonas().setSelectionModel(new ForcedListSelectionModel());
        inicializarTabla();
        vistaPersonas.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        this.vistaPersonas.getTablePersonas().addMouseListener(new MouseAdapter() {
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
        if (e.getSource().equals(vistaPersonas.getBtnEliminar())) {
            eliminarAction();

        }
        if (e.getSource().equals(vistaPersonas.getBtnNueva())) {
            nuevoAction();

        }
        if (e.getSource().equals(vistaPersonas.getBtnModificar())) {
            modificarAction();

        }
    }

    public void inicializarTabla() {
        this.vistaPersonas.getTablaPersonasDefault().setRowCount(0);
        ArrayList<Persona> listaPersonas = busqueda.getPersonasByPattern(vistaPersonas.getTxtBuscar().getText());
        for (Persona p : listaPersonas) {
            Object row[] = new Object[7];
            row[0] = p.getId();
            row[1] = p.getApellido();
            row[2] = p.getNombre();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            row[4] = p.getDireccion();
            row[5] = p.getTelefono();
            row[6] = p.getCbu();
            this.vistaPersonas.getTablaPersonasDefault().addRow(row);
        }

    }

    private void eliminarAction() {
        int rowNum = vistaPersonas.getTablePersonas().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaPersonas, "Debe seleccionar el elemento a eliminar.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(vistaPersonas, "¿Desea eliminar el elemento seleccionado?", "Confirmar borrado de datos", JOptionPane.YES_NO_OPTION)) {

            int id = (int) vistaPersonas.getTablaPersonasDefault().getValueAt(rowNum, 0);
            Persona p = busqueda.getPersonaById(id);
            vistaPersonas.getTablaPersonasDefault().removeRow(rowNum);
            abmPersona.baja(p);
            inicializarTabla();
        }

    }

    private void busquedaKeyReleased(KeyEvent evt) {
        ArrayList<Persona> listaPersonas = busqueda.getPersonasByPattern(vistaPersonas.getTxtBuscar().getText());
        this.vistaPersonas.getTablaPersonasDefault().setRowCount(0);
        for (Persona p : listaPersonas) {
            Object row[] = new Object[7];
            row[0] = p.getId();
            row[1] = p.getApellido();
            row[2] = p.getNombre();
            row[3] = p.gettDocumento().getNombre() + ": " + p.getDocumento();
            row[4] = p.getDireccion();
            row[5] = p.getTelefono();
            row[6] = p.getCbu();
            this.vistaPersonas.getTablaPersonasDefault().addRow(row);
        }

    }

    private void nuevoAction() {
        VistaNuevoModificarPersona vistaNuevoModificarPersona = new VistaNuevoModificarPersona();
        vistaNuevoModificarPersona.setTitle("Nueva persona");
        vistaNuevoModificarPersona.setVisible(true);
        vistaNuevoModificarPersona.setLocationRelativeTo(null);
        controladorVistaNuevoPersona = new ControladorVistaNuevoPersona(vistaNuevoModificarPersona, this);

    }

    private void modificarAction() {
        int rowNum = vistaPersonas.getTablePersonas().getSelectedRow();
        if (rowNum < 0) {
            JOptionPane.showMessageDialog(vistaPersonas, "Debe seleccionar el elemento a modificar.", "Atención!", JOptionPane.WARNING_MESSAGE);
        } else {

            int id = (int) vistaPersonas.getTablaPersonasDefault().getValueAt(rowNum, 0);

            Persona persona = busqueda.getPersonaById(id);
            VistaNuevoModificarPersona vistaNuevoModificarPersona = new VistaNuevoModificarPersona();
            vistaNuevoModificarPersona.setTitle("Modificar persona");
            vistaNuevoModificarPersona.setVisible(true);
            vistaNuevoModificarPersona.setLocationRelativeTo(null);
            controladorVistaModificarPersona = new ControladorVistaModificarPersona(vistaNuevoModificarPersona, this, persona);

        }

    }

    private void doubleClickAction(int rowNum) {
        int id = (int) vistaPersonas.getTablaPersonasDefault().getValueAt(rowNum, 0);

        Persona persona = busqueda.getPersonaById(id);
        VistaNuevoModificarPersona vistaNuevoModificarPersona = new VistaNuevoModificarPersona();
        vistaNuevoModificarPersona.setTitle("Modificar persona");
        vistaNuevoModificarPersona.setVisible(true);
        vistaNuevoModificarPersona.setLocationRelativeTo(null);
        controladorVistaModificarPersona = new ControladorVistaModificarPersona(vistaNuevoModificarPersona, this, persona);

    }

}
