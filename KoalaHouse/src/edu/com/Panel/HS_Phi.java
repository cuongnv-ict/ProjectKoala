/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Panel;

import DataBase.DataTable;
import DataBase.SQLDanhSachHocSinh;
import edu.com.CloseButton.CloseTabButton;
import edu.com.Dialog.InfoHS;
import edu.com.Dialog.ThemHS;
import edu.com.Dialog.chuyenlop;
import edu.com.ListKoala;
import edu.com.XuLy;
import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pham The Quyen
 */
public class HS_Phi extends javax.swing.JPanel {

    /**
     * Creates new form DSHS
     */
    public JTabbedPane center;
    ArrayList<Integer> id_students;
    int[] arrRows;
    private boolean isadmin = true;
    private ArrayList<Object[]> info;
    private Object [] o; 

    public HS_Phi() {
        initComponents();
      
        o  = new DataBase.SQLHocPhi().HS_Phi();
        LoaiPhi.setModel((ComboBoxModel) o[1]);
        LoaiPhi.setSelectedIndex(0);
        new DataBase.SQLHocPhi().HocSinhTinhPhi(BangPhi, Integer.parseInt(((Vector)o[0]).get(0).toString()));
    }

    public void setNotAdmin() {
        this.isadmin = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        BangPhi = new javax.swing.JTable();
        LoaiPhi = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        BangPhi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        BangPhi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Trình Độ", "Lớp", "Hình Thức Học", "Ngay Sinh"
            }
        ));
        BangPhi.setRowHeight(30);
        jScrollPane8.setViewportView(BangPhi);

        LoaiPhi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Học sinh đang học", "Học sinh nghỉ học", "Học sinh ra trường" }));
        LoaiPhi.setMinimumSize(new java.awt.Dimension(114, 25));
        LoaiPhi.setPreferredSize(new java.awt.Dimension(114, 25));
        LoaiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoaiPhiActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/refresh_1.png"))); // NOI18N
        jLabel1.setToolTipText("Tải lại");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoaiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LoaiPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    /*
     * setID dung de chuyen id hoc sinh sang so thu tu, từ số thứ tự thì chỉ số i-1 sẽ giúp truy suất trong mang id
     * giá trị Id_student tương ứng.
     */
    private void LoaiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoaiPhiActionPerformed
        // TODO add your handling code here:
        new DataBase.SQLHocPhi().HocSinhTinhPhi(BangPhi, Integer.parseInt(((Vector)o[0]).get(LoaiPhi.getSelectedIndex()).toString()));
    }//GEN-LAST:event_LoaiPhiActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.LoaiPhiActionPerformed(null);
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangPhi;
    private javax.swing.JComboBox LoaiPhi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane8;
    // End of variables declaration//GEN-END:variables
}