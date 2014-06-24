/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import edu.com.Dialog.BangHienThiTuanHe;
import edu.com.Dialog.ThemSuaDKHocHe;
import edu.com.XuLy;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Venus
 */
public class DKHocHe extends javax.swing.JPanel {
    private boolean isadmin=true;
    ArrayList<Integer> id_hoche;
    DefaultTableModel model,modelgoc;
    /**
     * Creates new form DKHocHe
     */
    public DKHocHe() {
        initComponents();
        modelgoc = (DefaultTableModel) jTable4.getModel();
        new DataBase.SQLkyhe().BandDanhSachDangKyHocHe(jTable4);
        id_hoche = new ArrayList<Integer>();
        try{
            if (!jTable4.getValueAt(0, 1).toString().equals("")) {
            XuLy.setID(id_hoche, jTable4, 0);
        }
        }
        catch(Exception ex)
        {
            jTable4.setModel(modelgoc);
        }
        
        
    }
     public void setNotAdmin()
    {
       them.setEnabled(false);
       sua.setEnabled(false);
       xoa.setEnabled(false);
       this.isadmin=false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Panel_DSLop = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        them = new javax.swing.JLabel();
        sua = new javax.swing.JLabel();
        xoa = new javax.swing.JLabel();
        xemlich = new javax.swing.JLabel();

        jTable4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Học Sinh", "Lớp", "Tuần Học", "Tổng Số Tuần", "Đánh Dấu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setRowHeight(30);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable4);

        them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/them.jpg"))); // NOI18N
        them.setToolTipText("Thêm");
        them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themMouseClicked(evt);
            }
        });

        sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/sua.JPG"))); // NOI18N
        sua.setToolTipText("Sửa");
        sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suaMouseClicked(evt);
            }
        });

        xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/xoa.jpg"))); // NOI18N
        xoa.setToolTipText("Xóa");
        xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xoaMouseClicked(evt);
            }
        });

        xemlich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/chitiet.png"))); // NOI18N
        xemlich.setToolTipText("Xem Lịch Tuần Học");
        xemlich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xemlichMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_DSLopLayout = new javax.swing.GroupLayout(Panel_DSLop);
        Panel_DSLop.setLayout(Panel_DSLopLayout);
        Panel_DSLopLayout.setHorizontalGroup(
            Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLopLayout.createSequentialGroup()
                .addGroup(Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DSLopLayout.createSequentialGroup()
                        .addComponent(them)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xemlich)
                        .addGap(0, 948, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE))
                .addContainerGap())
        );
        Panel_DSLopLayout.setVerticalGroup(
            Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLopLayout.createSequentialGroup()
                .addGroup(Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them)
                    .addComponent(sua)
                    .addComponent(xoa)
                    .addComponent(xemlich))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_DSLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_DSLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1114, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1114, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable4MouseClicked

    private void themMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themMouseClicked
        // TODO add your handling code here:
        
        if(isadmin)
        {
            System.out.println("bawt dau tao dialog");
            ThemSuaDKHocHe dkhoche= new ThemSuaDKHocHe(null, true);
            dkhoche.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
            
            dkhoche.setThemSuaLop(true);
            dkhoche.setVisible(true);
            
            //receipts.setLocation(420, 20);
            //receipts.show();
            new DataBase.SQLkyhe().BandDanhSachDangKyHocHe(jTable4);
            id_hoche = new ArrayList<Integer>();
            try
            {
            if (!jTable4.getValueAt(0, 1).toString().equals("")) {
            XuLy.setID(id_hoche, jTable4, 0);
            }
            }
            catch(Exception ex)
            {}
        }
    }//GEN-LAST:event_themMouseClicked

    private void suaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suaMouseClicked
        if(isadmin)
        {
            int count =1, row=0;
            model=(DefaultTableModel) jTable4.getModel();
            for(int i = model.getRowCount()-1;i>=0;i--){
                if((Boolean)model.getValueAt(i, 5)==true){
                    count--;
                    row = i;
                }
            }
            if(count!=0){
                if(count==1){
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn Học Sinh cần chỉnh sửa",null,JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Hệ thống chỉ cho phép chỉnh sửa một đối tượng tại một thời điểm",null,JOptionPane.INFORMATION_MESSAGE);
                    for(int i = model.getRowCount()-1;i>=0;i--){
                        model.setValueAt(false,i,10);
                    }
                }
                return;
            }
            Vector vec =  (Vector) model.getDataVector().elementAt(row);
            ThemSuaDKHocHe dkhoche= new ThemSuaDKHocHe(null, true,vec);
            
            dkhoche.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
            dkhoche.setThemSuaLop(false);
            dkhoche.setVisible(true);

            //dkhoche.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
            new DataBase.SQLkyhe().BandDanhSachDangKyHocHe(jTable4);
            id_hoche = new ArrayList<Integer>();
            try
            {
            if (!jTable4.getValueAt(0, 1).toString().equals("")) {
            XuLy.setID(id_hoche, jTable4, 0);
            }
            }
            catch(Exception ex)
            {
            }
            //ThemSuaLop lop = new ThemSuaLop(null, true, vec);
            /*
            if(lop.getButton()){
                model.setValueAt(lop.getTenLop(), row, 0);
                model.setValueAt(lop.getTrungTam(), row, 1);
                model.setValueAt(lop.getKhoiHoc(), row, 2);
                model.setValueAt(lop.getHocKy(), row, 3);
                model.setValueAt(lop.getGiaoVien(), row, 4);
                model.setValueAt(lop.getSoHS(), row, 5);
                model.setValueAt(lop.getTrangThai(), row, 7);
                model.setValueAt(false, row,8);
            }
            */
        }
    }//GEN-LAST:event_suaMouseClicked

    private void xoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaMouseClicked
        // TODO add your handling code here:

        boolean xoaorkhong;
        DataBase.SQLkyhe data= new DataBase.SQLkyhe();
        xoaorkhong=data.xoaDkHe(jTable4, 5);

        new DataBase.SQLkyhe().BandDanhSachDangKyHocHe(jTable4);
        id_hoche = new ArrayList<Integer>();
            try
            {
            if (!jTable4.getValueAt(0, 1).toString().equals("")) {
            XuLy.setID(id_hoche, jTable4, 0);
            
            }
            }
            catch(Exception ex)
            {
            }

    }//GEN-LAST:event_xoaMouseClicked

    private void xemlichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xemlichMouseClicked
        // TODO add your handling code here:
        BangHienThiTuanHe banghoche= new BangHienThiTuanHe(null, true);
        banghoche.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
        banghoche.setVisible(true);
            
    }//GEN-LAST:event_xemlichMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_DSLop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable4;
    private javax.swing.JLabel sua;
    private javax.swing.JLabel them;
    private javax.swing.JLabel xemlich;
    private javax.swing.JLabel xoa;
    // End of variables declaration//GEN-END:variables
}
