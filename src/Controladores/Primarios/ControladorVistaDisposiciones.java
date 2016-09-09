package Controladores.Primarios;

import ABMs.ABMDisposicionDePago;
import Busqueda.Busqueda;
import Controladores.Secundarios.ControladorVistaNuevaDisposicionPago;
import Modelos.DisposicionDePago;
import Utilidades.ForcedListSelectionModel;
import Utilidades.SqlDateFormat;
import Vistas.Primarias.VistaDisposiciones;
import Vistas.Secundarias.VistaNuevaModificarDisposicionPago;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author santiago
 */
public class ControladorVistaDisposiciones implements ActionListener {

    VistaDisposiciones vistaDisposiciones;
    Busqueda busqueda;
    ABMDisposicionDePago abmDisposicionDePago;
    ControladorVistaNuevaDisposicionPago controladorVistaNuevaDisposicionPago;

    public ControladorVistaDisposiciones(VistaDisposiciones vistaDisposiciones) {
        this.vistaDisposiciones = vistaDisposiciones;
        this.busqueda = new Busqueda();
        this.abmDisposicionDePago = new ABMDisposicionDePago();
        this.vistaDisposiciones.setActionListener(this);
        this.vistaDisposiciones.getTableDisposiciones().setSelectionModel(new ForcedListSelectionModel());
        inicializarTabla();
        this.vistaDisposiciones.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }

        });
        this.vistaDisposiciones.getTableDisposiciones().addMouseListener(new MouseAdapter() {
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
        if (e.getSource() == this.vistaDisposiciones.getBtnNuevo()) {
            nuevoAction();

        }
    }

    public void inicializarTabla() {
        this.vistaDisposiciones.getTablaDisposicionesDefault().setRowCount(0);
        ArrayList<DisposicionDePago> listaDisposicionDePago = busqueda.getDisposicionPagoByPattern(this.vistaDisposiciones.getTxtBuscar().getText());
        for (DisposicionDePago d : listaDisposicionDePago) {
            Object row[] = new Object[3];
            row[0] = d.getId();
            row[1] = d.getNumeroExpediente();
            row[2] = SqlDateFormat.dateToMySQLDate(d.getFecha(), false);
            this.vistaDisposiciones.getTablaDisposicionesDefault().addRow(row);
        }

    }

    private void doubleClickAction(int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        this.vistaDisposiciones.getTablaDisposicionesDefault().setRowCount(0);
        ArrayList<DisposicionDePago> listaDisposicionDePago = busqueda.getDisposicionPagoByPattern(this.vistaDisposiciones.getTxtBuscar().getText());
        for (DisposicionDePago d : listaDisposicionDePago) {
            Object row[] = new Object[3];
            row[0] = d.getId();
            row[1] = d.getNumeroExpediente();
            row[2] = SqlDateFormat.dateToMySQLDate(d.getFecha(), false);
            this.vistaDisposiciones.getTablaDisposicionesDefault().addRow(row);
        }

    }

    private void nuevoAction() {
        VistaNuevaModificarDisposicionPago vistaNuevaModificarDisposicionPago = new VistaNuevaModificarDisposicionPago();
        vistaNuevaModificarDisposicionPago.setTitle("Nueva disposición de pago");
        vistaNuevaModificarDisposicionPago.setVisible(true);
        vistaNuevaModificarDisposicionPago.setLocationRelativeTo(null);
        controladorVistaNuevaDisposicionPago = new ControladorVistaNuevaDisposicionPago(vistaNuevaModificarDisposicionPago, this);

    }

}
