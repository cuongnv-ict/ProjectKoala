/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Dialog;

import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen van cuong
 */
public class NamHoc extends javax.swing.JDialog {

    /**
     * Creates new form NamHoc
     */
    private DefaultTableModel model;

    public NamHoc(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        new DataBase.SQLNamHoc().getNamHoc(BangHocKy, Integer.parseInt(namhoc.getSelectedItem().toString()));
        this.setResizable(false);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        BangHocKy = new javax.swing.JTable();
        CapNhat = new javax.swing.JButton();
        namhoc = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BangHocKy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Học kỳ", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(BangHocKy);

        CapNhat.setText("Cập nhật ");
        CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatActionPerformed(evt);
            }
        });

        namhoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        namhoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namhocActionPerformed(evt);
            }
        });

        jLabel1.setText("Năm học");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CapNhat)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(namhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CapNhat)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void namhocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namhocActionPerformed
        // TODO add your handling code here:
        new DataBase.SQLNamHoc().getNamHoc(BangHocKy, Integer.parseInt(namhoc.getSelectedItem().toString()));
    }//GEN-LAST:event_namhocActionPerformed

    private void CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) BangHocKy.getModel();
        boolean flags = false;
        Vector vector1 = (Vector) model.getDataVector().elementAt(0);
        flags = new DataBase.SQLNamHoc().setNamHoc(vector1, Integer.parseInt(namhoc.getSelectedItem().toString()), 1);
        if (!flags) {
            return;
        }
        Vector vector2 = (Vector) model.getDataVector().elementAt(1);
        flags = new DataBase.SQLNamHoc().setNamHoc(vector2, Integer.parseInt(namhoc.getSelectedItem().toString()), 2);
        if (!flags) {
            return;
        }
        Vector vector3 = (Vector) model.getDataVector().elementAt(2);
        flags = new DataBase.SQLNamHoc().setNamHoc(vector3, Integer.parseInt(namhoc.getSelectedItem().toString()), 3);
        if (!flags) {
            return;
        }
        Vector vector4 = (Vector) model.getDataVector().elementAt(3);
        if (!flags) {
            return;
        }
        flags = new DataBase.SQLNamHoc().setNamHoc(vector4, Integer.parseInt(namhoc.getSelectedItem().toString()), 4);
        if (!flags) {
            return;
        }
        Vector vector5 = new Vector();
        vector5.add("Cả năm");
        vector5.add(((Vector) model.getDataVector().elementAt(0)).elementAt(1));
        vector5.add(((Vector) model.getDataVector().elementAt(3)).elementAt(2));
        flags = new DataBase.SQLNamHoc().setNamHoc(vector5, Integer.parseInt(namhoc.getSelectedItem().toString()), 5);
        if(!flags){
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_CapNhatActionPerformed

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
            java.util.logging.Logger.getLogger(NamHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NamHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NamHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NamHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NamHoc dialog = new NamHoc(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangHocKy;
    private javax.swing.JButton CapNhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox namhoc;
    // End of variables declaration//GEN-END:variables
}