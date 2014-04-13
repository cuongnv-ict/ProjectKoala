/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Panel;

import edu.com.Dialog.ThemGia;
import edu.com.XuLy;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pham The Quyen
 */
public class CacLoaiPhi extends javax.swing.JPanel {

    /**
     * Creates new form CacLoaiPhi
     */
    private DefaultTableModel model;
    ArrayList<Integer> id_cost;

    public CacLoaiPhi() {
        initComponents();
        new DataBase.SQLHocPhi().BangDanhSachPhi(BangPhi);
        model = (DefaultTableModel) BangPhi.getModel();
        id_cost = new ArrayList<Integer>();
        if (!BangPhi.getValueAt(0, 0).toString().equals("")) {
            XuLy.setID(id_cost, BangPhi, 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane7 = new javax.swing.JScrollPane();
        BangPhi = new javax.swing.JTable();
        ThemPhi = new javax.swing.JLabel();
        Sua = new javax.swing.JLabel();
        Xoa = new javax.swing.JLabel();

        BangPhi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên", "Kì học", "Năm học", "Giá"
            }
        ));
        BangPhi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BangPhiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(BangPhi);

        ThemPhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/them.jpg"))); // NOI18N
        ThemPhi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemPhiMouseClicked(evt);
            }
        });

        Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/sua.JPG"))); // NOI18N
        Sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuaMouseClicked(evt);
            }
        });

        Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/xoa.jpg"))); // NOI18N
        Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ThemPhi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Xoa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThemPhi)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Xoa)
                        .addComponent(Sua)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ThemPhiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemPhiMouseClicked
        // TODO add your handling code here:
        ThemGia cost = new ThemGia(null, true);
        cost.setVisible(true);
        Vector vec = new Vector();
        if (cost.getButton()) {
            new DataBase.SQLHocPhi().BangDanhSachPhi(BangPhi);
            model = (DefaultTableModel) BangPhi.getModel();
            if (!BangPhi.getValueAt(0, 0).toString().equals("")) {
                XuLy.setID(id_cost, BangPhi, 0);
            }
        }
    }//GEN-LAST:event_ThemPhiMouseClicked

    private void SuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuaMouseClicked
        // TODO add your handling code here:
        int count = 1, row = 0;
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 7) == true) {
                count--;
                row = i;
            }
        }
        if (count != 0) {
            if (count == 1) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn phí cần chỉnh sửa", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Hệ thống chỉ cho phép chỉnh sửa một đối tượng tại một thời điểm", null, JOptionPane.INFORMATION_MESSAGE);
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.setValueAt(false, i, 7);
                }
            }
            return;
        }
        Vector vec = (Vector) model.getDataVector().elementAt(row);
        ThemGia cost = new ThemGia(null, true, vec, id_cost.get(row));
        cost.setVisible(true);
        if (cost.getButton()) {
            new DataBase.SQLHocPhi().BangDanhSachPhi(BangPhi);
            model = (DefaultTableModel) BangPhi.getModel();
            if (!BangPhi.getValueAt(0, 0).toString().equals("")) {
                XuLy.setID(id_cost, BangPhi, 0);
            }
        }
    }//GEN-LAST:event_SuaMouseClicked

    private void BangPhiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangPhiMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            Vector vec = (Vector) model.getDataVector().elementAt(BangPhi.getSelectedRow());
            ThemGia cost = new ThemGia(null, true, vec, id_cost.get(BangPhi.getSelectedRow()));
            cost.setVisible(true);
            if (cost.getButton()) {
                new DataBase.SQLHocPhi().BangDanhSachPhi(BangPhi);
                model = (DefaultTableModel) BangPhi.getModel();
                if (!BangPhi.getValueAt(0, 0).toString().equals("")) {
                    XuLy.setID(id_cost, BangPhi, 0);
                }
            }
        }
    }//GEN-LAST:event_BangPhiMouseClicked

    private void XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaMouseClicked
        // TODO add your handling code here:

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 7) == true) {
                if (new DataBase.SQLHocPhi().xoaHocPhi(BangPhi.getValueAt(i, 2).toString(), BangPhi.getValueAt(i, 2).toString(), id_cost.get(i))) {
                    model.removeRow(i);
                };
            }
        }
    }//GEN-LAST:event_XoaMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangPhi;
    private javax.swing.JLabel Sua;
    private javax.swing.JLabel ThemPhi;
    private javax.swing.JLabel Xoa;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables
}
