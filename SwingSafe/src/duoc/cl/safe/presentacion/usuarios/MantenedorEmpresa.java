/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.presentacion.usuarios;

import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.herramientas.FormsController;
import duoc.cl.safe.negocio.SsfEmpresaBO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Nacho
 */
public class MantenedorEmpresa extends javax.swing.JFrame {

    /**
     * Creates new form MantenedorEmpresa
     */
    public MantenedorEmpresa() {
        initComponents();
        PropertyConfigurator.configure("log4j.properties");
        resizeTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfEmpresa = new javax.swing.JTextField();
        tfDireccion = new javax.swing.JTextField();
        tfTelefono = new javax.swing.JTextField();
        tfBuscar = new javax.swing.JTextField();
        bBuscar = new javax.swing.JButton();
        bAgregar = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();
        tbEstado = new javax.swing.JToggleButton();
        lExito = new javax.swing.JLabel();
        lError = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpresa = new javax.swing.JTable();
        bRefrescar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Nombre empresa:");

        jLabel2.setText("Dirección:");

        jLabel3.setText("Teléfono:");

        bBuscar.setText("Buscar Empresa");
        bBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarActionPerformed(evt);
            }
        });

        bAgregar.setText("Agregar");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });

        bModificar.setText("Modificar");
        bModificar.setEnabled(false);
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });

        bLimpiar.setText("Limpiar");
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
            }
        });

        tbEstado.setText("Activo");
        tbEstado.setEnabled(false);
        tbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbEstadoActionPerformed(evt);
            }
        });

        tblEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Dirección", "Teléfono", "Fecha creación", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.Short.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpresaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpresa);

        bRefrescar.setText("Refrescar");
        bRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRefrescarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("Mantenedor Empresa");

        jMenu1.setText("Cargando...");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 56, Short.MAX_VALUE)
                .addComponent(lExito, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lError, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                    .addComponent(tfDireccion))
                                .addGap(18, 18, 18)
                                .addComponent(tfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                                .addComponent(bAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bLimpiar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bRefrescar)
                        .addComponent(tfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAgregar)
                    .addComponent(bModificar)
                    .addComponent(bLimpiar)
                    .addComponent(tbEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lExito, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lError, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setJMenuBar(formsController.getMenu().getMenuBar());
        formsController.getMenu().setjFrame(this);
        this.setLocationRelativeTo(null);
        cargaTabla();
    }//GEN-LAST:event_formWindowOpened

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        tbEstado.setEnabled(false);
        bModificar.setEnabled(false);
        limpiarMsgs();
        tblEmpresa.clearSelection();
        tfEmpresa.setText("");
        tfDireccion.setText("");
        tfTelefono.setText("");
        tfBuscar.setText("");
    }//GEN-LAST:event_bLimpiarActionPerformed

    private void tblEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpresaMouseClicked
        limpiarMsgs();
        tbEstado.setEnabled(true);
        bModificar.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) tblEmpresa.getModel();
        if (Integer.parseInt(model.getValueAt(tblEmpresa.getSelectedRow(), 5).toString()) == 1) {
            tbEstado.setSelected(false);
            activarEstado();
        } else {
            tbEstado.setSelected(true);
            desactivarEstado();
        }
        if (model.getValueAt(tblEmpresa.getSelectedRow(), 1) != null) {
            tfEmpresa.setText(model.getValueAt(tblEmpresa.getSelectedRow(), 1).toString());
        } else {
            tfEmpresa.setText("");
        }
        if (model.getValueAt(tblEmpresa.getSelectedRow(), 2) != null) {
            tfDireccion.setText(model.getValueAt(tblEmpresa.getSelectedRow(), 2).toString());
        } else {
            tfDireccion.setText("");
        }
        if (model.getValueAt(tblEmpresa.getSelectedRow(), 3) != null) {
            tfTelefono.setText(model.getValueAt(tblEmpresa.getSelectedRow(), 3).toString());
        } else {
            tfTelefono.setText("");
        }
    }//GEN-LAST:event_tblEmpresaMouseClicked

    private void tbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbEstadoActionPerformed
        limpiarMsgs();
        DefaultTableModel model = (DefaultTableModel) tblEmpresa.getModel();
        ebo = new SsfEmpresaBO();

        if (tblEmpresa.getSelectedRow() == -1) {
            tbEstado.setEnabled(false);
            if (tblEmpresa.getRowCount() == 0) {
                lError.setText("Tabla vacía");
            } else {
                lError.setText("No hay fila seleccionada");
            }
        } else {
            int id = Short.parseShort(model.getValueAt(tblEmpresa.getSelectedRow(), 0).toString());
            if (!tbEstado.isSelected()) {
                if (ebo.activarSP(id)) {
                    activarEstado();
                    model.setValueAt("1", tblEmpresa.getSelectedRow(), 5);
                }
            } else {
                if (ebo.desactivarSP(id)) {
                    desactivarEstado();
                    model.setValueAt("0", tblEmpresa.getSelectedRow(), 5);
                }
            }
        }
    }//GEN-LAST:event_tbEstadoActionPerformed

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed
        String busqueda = tfBuscar.getText().trim();
        //split(Pattern.quote(".")) - str.split("\\s+");
        if (busqueda.equals("")) {
            tfBuscar.setText("Búsqueda no puede ser vacío");
        } else {
            String[] palabras = busqueda.split("\\s+");
            //String[] palabras2 = busqueda.split(Pattern.quote("."));
            List<SsfEmpresa> ee = new LinkedList<>();
            if (le==null) {
                ebo = new SsfEmpresaBO();
                le = ebo.getAllSP();
            }
            for (String s : palabras) {
                for (SsfEmpresa emp : le) {
                    if (emp.getNombre() != null) {
                        if (!ee.isEmpty()) {
                            if (!existeIdEmp(ee, emp) && emp.getNombre().toLowerCase().contains(s.toLowerCase())) {
                                ee.add(emp);
                            }
                        } else {
                            if (emp.getNombre().toLowerCase().contains(s.toLowerCase())) {
                                ee.add(emp);
                            }
                        }
                    }
                    if (emp.getDireccion() != null) {
                        if (!ee.isEmpty()) {
                            if (!existeIdEmp(ee, emp) && emp.getDireccion().toLowerCase().contains(s.toLowerCase())) {
                                ee.add(emp);
                            }
                        } else {
                            if (emp.getDireccion().toLowerCase().contains(s.toLowerCase())) {
                                ee.add(emp);
                            }
                        }
                    }
                    if (emp.getTelefono() != null) {
                        if (!ee.isEmpty()) {
                            if (!existeIdEmp(ee, emp) && emp.getTelefono().toString().contains(s)) {
                                ee.add(emp);
                            }
                        } else {
                            if (emp.getTelefono().toString().contains(s)) {
                                ee.add(emp);
                            }
                        }
                    }
                }
            }
            if (!ee.isEmpty()) {
                tblEmpresa.removeAll();
                cargaEmpresas(ee);
            } else {
                tfBuscar.setText("Búsqueda sin resultados");
                tblEmpresa.removeAll();
            }

        }
    }//GEN-LAST:event_bBuscarActionPerformed

    private void bRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRefrescarActionPerformed
        tbEstado.setEnabled(false);
        bModificar.setEnabled(false);
        limpiarMsgs();
        tblEmpresa.clearSelection();
        tfEmpresa.setText("");
        tfDireccion.setText("");
        tfTelefono.setText("");
        tfBuscar.setText("");
        cargaTabla();
    }//GEN-LAST:event_bRefrescarActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        ebo = new SsfEmpresaBO();
        limpiarMsgs();
        if (tfEmpresa.getText().trim().equals("")) {
            lError.setText("Ingrese un nombre para Empresa");
        } else {
            String dir, tel, nom;
            dir = tfDireccion.getText();
            nom = tfEmpresa.getText();
            tel = tfTelefono.getText();
            SsfEmpresa emp = new SsfEmpresa();
            try {
                if (!tel.equals("")) {
                    Long telbi = Long.valueOf(tel);
                    emp.setTelefono(telbi);
                }
            } catch (Exception e) {
                log.log(Level.ERROR, "Error en valor de telefono", e);
                lError.setText("Error en valor de telefono");
            }
            emp.setNombre(nom);
            emp.setDireccion(dir);
            if (ebo.addSP(emp)) {
                lExito.setText("Empresa agregada exitosamente.");
                cargaTabla();
            } else {
                lError.setText("No se pudo agregar");
            }
        }
    }//GEN-LAST:event_bAgregarActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblEmpresa.getModel();
        ebo = new SsfEmpresaBO();
        limpiarMsgs();
        if (tblEmpresa.getSelectedRow() == -1) {
            tbEstado.setEnabled(false);
            if (tblEmpresa.getRowCount() == 0) {
                lError.setText("Tabla vacía");
            } else {
                lError.setText("No hay fila seleccionada");
            }
        } else {
            if (tfEmpresa.getText().trim().equals("")) {
                lError.setText("Nombre Empresa no puede estar en blanco");
            } else {
                String dir, nom, tel, id;
                id = model.getValueAt(tblEmpresa.getSelectedRow(), 0).toString();
                nom = tfEmpresa.getText().trim();
                dir = tfEmpresa.getText();
                tel = tfTelefono.getText().trim();
                SsfEmpresa emp = new SsfEmpresa();

                try {
                    if (!tel.equals("")) {
                        Long telbi = Long.valueOf(tel);
                        emp.setTelefono(telbi);
                    }
                } catch (Exception e) {
                    log.log(Level.ERROR, "Error en valor de telefono", e);
                    lError.setText("Error en valor de telefono");
                    tel = "error";
                }
                emp.setId(BigDecimal.valueOf(Long.valueOf(id)));
                emp.setNombre(nom);
                emp.setDireccion(dir);
                if (ebo.updateSP(emp)) {
                    lExito.setText("Empresa modificada exitosamente.");
                    model.setValueAt(nom, tblEmpresa.getSelectedRow(), 1);
                    model.setValueAt(dir, tblEmpresa.getSelectedRow(), 2);
                    if (!tel.equals("error")) {
                        model.setValueAt(tel, tblEmpresa.getSelectedRow(), 3);
                    }
                } else {
                    lError.setText("No se pudo modificar");
                }
            }
        }
    }//GEN-LAST:event_bModificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MantenedorEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MantenedorEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MantenedorEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MantenedorEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MantenedorEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bRefrescar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lError;
    private javax.swing.JLabel lExito;
    private javax.swing.JToggleButton tbEstado;
    private javax.swing.JTable tblEmpresa;
    private javax.swing.JTextField tfBuscar;
    private javax.swing.JTextField tfDireccion;
    private javax.swing.JTextField tfEmpresa;
    private javax.swing.JTextField tfTelefono;
    // End of variables declaration//GEN-END:variables
    private SsfEmpresaBO ebo;
    private static Logger log = Logger.getLogger(MantenedorEmpresa.class.getName());
    private FormsController formsController;
    private List<SsfEmpresa> le;

    private void cargaTabla() {
        
        DefaultTableModel model = (DefaultTableModel) tblEmpresa.getModel();
        model.setRowCount(0);
        ebo = new SsfEmpresaBO();
        le = ebo.getAllSP();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        le.forEach((e) -> {
            model.addRow(new Object[]{e.getId(), e.getNombre(), e.getDireccion(), e.getTelefono(), sdf.format(e.getFechCreacion()), e.getEstado()});
        });
        tblEmpresa.setModel(model);

    }

    private void desactivarEstado() {
        tbEstado.setText("Desactivado");
        tbEstado.setBackground(new java.awt.Color(255, 51, 51));
    }

    private void activarEstado() {
        tbEstado.setText("Activo");
        tbEstado.setBackground(new java.awt.Color(0, 204, 102));
    }

    private void limpiarMsgs() {
        lExito.setText("");
        lError.setText("");
    }

    private boolean existeIdEmp(List<SsfEmpresa> ee, SsfEmpresa e) {
        for (SsfEmpresa emp : ee) {
            if (emp.getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    private void cargaEmpresas(List<SsfEmpresa> ee) {
        
        DefaultTableModel model = (DefaultTableModel) tblEmpresa.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (SsfEmpresa e : ee) {
            model.addRow(new Object[]{e.getId(), e.getNombre(),
                e.getDireccion(), e.getTelefono(),
                sdf.format(e.getFechCreacion()), e.getEstado()});
        }
        tblEmpresa.setModel(model);
    }

    public void setFormsController(FormsController formsController) {
        this.formsController = formsController;
    }

    private void resizeTabla() { 
        tblEmpresa.getColumnModel().getColumn(0).setMaxWidth(40);
        tblEmpresa.getColumnModel().getColumn(1).setMaxWidth(300);
        tblEmpresa.getColumnModel().getColumn(2).setMaxWidth(250);
        tblEmpresa.getColumnModel().getColumn(3).setMaxWidth(110);
        tblEmpresa.getColumnModel().getColumn(4).setMaxWidth(110);
        tblEmpresa.getColumnModel().getColumn(5).setMaxWidth(50);
    }
    
}
