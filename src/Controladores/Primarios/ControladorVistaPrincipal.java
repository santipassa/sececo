package Controladores.Primarios;

import Vistas.Primarias.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author santiago
 */
public class ControladorVistaPrincipal implements ActionListener {

    VistaPrincipal vistaPrincipal;
    VistaPersonas vistaPersonas;
    ControladorVistaPersonas controladorVistaPersonas;
    VistaContratos vistaContratos;
    VistaDisposiciones vistaDisposiciones;
    ControladorVistaDisposiciones controladorVistaDisposiciones;
    ControladorVistaContratos controladorVistaContratos;

    public ControladorVistaPrincipal() {
        vistaPrincipal = new VistaPrincipal();
        //TITULO Y VERSION DEL PROGRAMA
        String titulo = "Secretaría económica UNRC - Contratos y disposiciones";
        String version = "v1.0";
        //
        vistaPrincipal.setTitle(titulo + " " + version);

        vistaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vistaContratos = new VistaContratos();
        controladorVistaContratos = new ControladorVistaContratos(vistaContratos);
        vistaPersonas = new VistaPersonas();
        controladorVistaPersonas = new ControladorVistaPersonas(vistaPersonas);
        vistaDisposiciones = new VistaDisposiciones();
        controladorVistaDisposiciones = new ControladorVistaDisposiciones(vistaDisposiciones);
        vistaPrincipal.setActionListener(this);
        vistaPrincipal.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

        vistaPrincipal.getjDesktopPane1().add(vistaPersonas);
        vistaPrincipal.getjDesktopPane1().add(vistaContratos);
        vistaPrincipal.getjDesktopPane1().add(vistaDisposiciones);
        vistaPrincipal.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getBtnPersonas()) {
            vistaPersonas.setVisible(true);
            vistaPersonas.toFront();
            controladorVistaPersonas.inicializarTabla();
        }
        if (e.getSource() == vistaPrincipal.getBtnContratos()) {
            this.vistaContratos.setVisible(true);
            this.vistaContratos.toFront();
            this.controladorVistaContratos.inicializarTabla();

        }
        if (e.getSource() == vistaPrincipal.getBtnDisposiciones()) {
            this.vistaDisposiciones.setVisible(true);
            this.vistaDisposiciones.toFront();
            this.controladorVistaDisposiciones.inicializarTabla();

        }
        if (e.getSource() == vistaPrincipal.getBtnSalir()) {
            close();

        }
    }

    private void close() {
        if (JOptionPane.showConfirmDialog(vistaPrincipal, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }

}
