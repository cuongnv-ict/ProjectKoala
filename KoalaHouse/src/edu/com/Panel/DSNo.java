/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.DataTable;
import edu.com.CloseButton.CloseTabButton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;

/**
 *
 * @author Pham The Quyen
 */
public class DSNo extends javax.swing.JPanel {

    /**
     * Creates new form DSNo
     */
    public JTabbedPane center;
    public DSNo() {
        initComponents();
        new DataTable().BangDanhSachHocSinhNoPhi(DSHocSinhNo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_DSNo = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        DSHocSinhNo = new javax.swing.JTable();

        DSHocSinhNo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Học Sinh", "Tên Lớp", "Trình Độ", "Số Tiền Nợ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DSHocSinhNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DSHocSinhNoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(DSHocSinhNo);

        javax.swing.GroupLayout Panel_DSNoLayout = new javax.swing.GroupLayout(Panel_DSNo);
        Panel_DSNo.setLayout(Panel_DSNoLayout);
        Panel_DSNoLayout.setHorizontalGroup(
            Panel_DSNoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Panel_DSNoLayout.setVerticalGroup(
            Panel_DSNoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1169, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(Panel_DSNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1153, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(370, Short.MAX_VALUE)
                    .addComponent(Panel_DSNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(371, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DSHocSinhNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DSHocSinhNoMouseClicked
        if(evt.getClickCount()==2){
            //map cac truong
            String idStudent = DSHocSinhNo.getValueAt(DSHocSinhNo.getSelectedRow(), 0).toString();
            int id = Integer.parseInt(idStudent);
            DataTable a = new DataTable();
            ArrayList info,lateday;
            info = a.HocSinhA1(id);
            lateday = a.LateDay(id);
            HocSinhA.tenHS = (String) info.get(1);
            HocSinhA.tenNguoiDaiDien = (String) info.get(4);
            HocSinhA.ngaySinh = (String) info.get(2);
            HocSinhA.SDT = (String) info.get(3);
            HocSinhA.hinhThucHoc = (String) info.get(7);
            HocSinhA.lop = (String) info.get(6);
            HocSinhA.trinhDo = (String) info.get(5);
            HocSinhA.trungTam = (String) info.get(8);
            HocSinhA.soNgayTrongMuon = String.valueOf(lateday.size());
            HocSinhA aa = new HocSinhA();
            aa.id = id;
            aa.lateday = lateday;
            //add tab
            center.add((String) info.get(1), aa);
            center.setSelectedComponent(aa);
        new CloseTabButton(center,center.getComponentCount()-2);
        }
    }//GEN-LAST:event_DSHocSinhNoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DSHocSinhNo;
    private javax.swing.JPanel Panel_DSNo;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables
}
