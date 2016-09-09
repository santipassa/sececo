package secec;


import Controladores.Primarios.ControladorVistaPrincipal;
import Utilidades.*;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import javax.swing.UIManager;

/**
 *
 * @author santiago
 */
public class SecEc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, DocumentException, FileNotFoundException {

        //SETO LOOK AND FEEL
        try {
            for (UIManager.LookAndFeelInfo laf : UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    UIManager.getLookAndFeelDefaults().put(
                            "DesktopPane[Enabled].backgroundPainter",
                            new DesktopPainter());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ControladorVistaPrincipal controladorVistaPrincipal = new ControladorVistaPrincipal();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error, contacte al administrador del sistema " + e, "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}
