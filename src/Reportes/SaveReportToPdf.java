package Reportes;

import ABMs.ABMContrato;
import Busqueda.Busqueda;
import Modelos.Contrato;
import Modelos.TMoneda;
import Utilidades.ArchivosProperties;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;


/**
 *
 * @author santiago
 */
public class SaveReportToPdf {

    private Connection getConexion() {
        Connection con = null;
        try {
            //traigo los datos del archivo de configuracion
            ArchivosProperties a = new ArchivosProperties();
            Properties p = a.getProperties("database");
            String ip = p.getProperty("ip");
            String puerto = p.getProperty("puerto");
            String schema = p.getProperty("schema");
            String usuario = p.getProperty("usuario");
            String passwd = p.getProperty("passwd");

            //FIN DE TRAIDA DE DATOS
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + schema, usuario, passwd);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    private String formatIdContrato(Integer id) {
        
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

    public boolean save(Contrato c) {
        ABMContrato abmContrato;
        Connection connection = null;
        try {
            abmContrato = new ABMContrato();
            int contrId = c.getId();
            String reportName;
            String pathJasperFile;
            String pathImagen;
            //LEVANTO EL ARCHIVO DE CONFIGURACIONES GENERALES
            ArchivosProperties a = new ArchivosProperties();
            Properties p = a.getProperties("general");
            /////////////////////////////////////////////////
            
            String ruta = p.getProperty("pathContratos") + "contrato_" + formatIdContrato(contrId);
            pathJasperFile = p.getProperty("pathJasperFile");
            pathImagen = p.getProperty("pathImagen");
            //SEGUN QUE DEBO GENERAR LEVANTO EL ARCHIVO QUE CORRESPONDE
            if (c.getViatico()) {
                reportName = pathJasperFile + "contrato_con_viatico.jasper";
            } else {
                reportName = pathJasperFile + "contrato_sin_viatico.jasper";
            }
            //MAPA DONDE VAN LOS PARAMETROS
            Map<String, Object> params = new HashMap<>();
            //CONEXION A LA BASE DE DATOS
            connection = getConexion();
            //TRAIGO LA MONEDA QUE SE ESTA USANDO
            Busqueda busqueda = new Busqueda();
            TMoneda moneda = busqueda.getTMoneda();
            ///////////////////////////////////////////////////
            //POBLAMOS EL MAPA DE PARAMETROS///////////////////
            ///////////////////////////////////////////////////
            params.put("contrId", contrId);
            params.put("monedaPlural", moneda.getPlural());
            params.put("monedaSimbolo", moneda.getSimbolo());
            params.put("pathImagen", pathImagen);
            ///////////////////////////////////////////////////
            JasperPrint print = JasperFillManager.fillReport(reportName, params, connection);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(ruta + ".pdf")); // your output goes here
            exporter.exportReport();
            /////////////////////////////////
            //SETEO EL CONTRATO COMO IMPRESO
            /////////////////////////////////
            c.setImpreso(true);
            abmContrato.modificar(c);
            return true;
        } catch (Exception e) {
               System.out.println(e.toString());
            return false;
        }
    }

}
