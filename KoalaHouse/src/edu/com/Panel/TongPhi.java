/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.DataTable;
import DataBase.HocSinh.DebtList;
import DataBase.HocSinh.TotalFeeManagerment;
import edu.com.CloseButton.CloseTabButton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author Pham The Quyen
 */
public class TongPhi extends javax.swing.JPanel {

    /**
     * Creates new form DSNo
     */
    public ArrayList ListId;
    public JTabbedPane center;
    public JTable BangDSNo;
    public TongPhi() {
        initComponents();
//        ListId = new DebtList().GetIdStudent();
        new TotalFeeManagerment().BangDanhSachTongPhi(1, tongPhi);
    }
    public void reLoad(){
        new TotalFeeManagerment().BangDanhSachTongPhi(1, tongPhi);
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
        tongPhi = new javax.swing.JTable();

        tongPhi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tongPhi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1772, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tongPhi;
    // End of variables declaration//GEN-END:variables
}
