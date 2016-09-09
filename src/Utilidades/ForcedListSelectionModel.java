/*
 *Clase para setear que en una tabla se pueda seleccionar una y solo una fila
 */
package Utilidades;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author santiago
 */
public class ForcedListSelectionModel extends DefaultListSelectionModel {

    public ForcedListSelectionModel() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }

}
