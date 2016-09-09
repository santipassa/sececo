package Vistas.Primarias;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

/**
 *
 * @author santiago
 */
public class VistaPrincipal extends javax.swing.JFrame {

    public VistaPrincipal() {
        initComponents();
    }

    public void setActionListener(ActionListener lis) {
        this.btnContratos.addActionListener(lis);
        this.btnDisposiciones.addActionListener(lis);
        this.btnSalir.addActionListener(lis);
        this.btnPersonas.addActionListener(lis);

    }

    public JButton getBtnContratos() {
        return btnContratos;
    }

    public JButton getBtnPersonas() {
        return btnPersonas;
    }

    public void setBtnPersonas(JButton btnPersonas) {
        this.btnPersonas = btnPersonas;
    }

    public void setBtnContratos(JButton btnContratos) {
        this.btnContratos = btnContratos;
    }

    public JButton getBtnDisposiciones() {
        return btnDisposiciones;
    }

    public void setBtnDisposiciones(JButton btnDisposiciones) {
        this.btnDisposiciones = btnDisposiciones;
    }

    public JDesktopPane getjDesktopPane1() {
        return jDesktopPane1;
    }

    public void setjDesktopPane1(JDesktopPane jDesktopPane1) {
        this.jDesktopPane1 = jDesktopPane1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnContratos = new javax.swing.JButton();
        btnDisposiciones = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnPersonas = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnContratos.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnContratos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/file-icon.png"))); // NOI18N
        btnContratos.setText("Contratos");
        btnContratos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContratos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnDisposiciones.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnDisposiciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/stat-icon.png"))); // NOI18N
        btnDisposiciones.setText("Disposiciones");
        btnDisposiciones.setEnabled(false);
        btnDisposiciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDisposiciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/lock-icon.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnPersonas.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnPersonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contacts-icon.png"))); // NOI18N
        btnPersonas.setText("Personas");
        btnPersonas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDisposiciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnContratos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPersonas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnContratos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisposiciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContratos;
    private javax.swing.JButton btnDisposiciones;
    private javax.swing.JButton btnPersonas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
