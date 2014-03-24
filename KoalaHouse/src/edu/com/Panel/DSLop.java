/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.ConnectData;
import edu.com.CloseButton.CloseTabButton;
import edu.com.Dialog.ThemSuaLop;
import edu.com.upbang.EditTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import edu.com.upbang.AddRowOfTable;
import java.awt.Toolkit;

/**
 *
 * @author Pham The Quyen
 */
public class DSLop extends javax.swing.JPanel {

    /**
     * Creates new form DSLop
     */
   public JTabbedPane center;
   private DefaultTableModel model;
   public DSLop() {
        initComponents();
         new DataBase.DataTable().BangDanhSachLop(jTable4);
        model = (DefaultTableModel) jTable4.getModel();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_DSLop = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        Button_DSLop_ThemLop = new javax.swing.JLabel();
        Button_DSLop_SuaLop = new javax.swing.JLabel();
        Button_DSLop_Xoa = new javax.swing.JLabel();

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Lớp", "Trình độ", "Học kì", "Giáo Viên", "Ngày bắt đầu", "Ngày kết thúc", "Số học sinh", "Tối đa", "Trạng thái"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable4);

        Button_DSLop_ThemLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/them.jpg"))); // NOI18N
        Button_DSLop_ThemLop.setToolTipText("Thêm");
        Button_DSLop_ThemLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_DSLop_ThemLopMouseClicked(evt);
            }
        });

        Button_DSLop_SuaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/sua.JPG"))); // NOI18N
        Button_DSLop_SuaLop.setToolTipText("Sửa");
        Button_DSLop_SuaLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_DSLop_SuaLopMouseClicked(evt);
            }
        });

        Button_DSLop_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/xoa.jpg"))); // NOI18N
        Button_DSLop_Xoa.setToolTipText("Xóa");
        Button_DSLop_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_DSLop_XoaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_DSLopLayout = new javax.swing.GroupLayout(Panel_DSLop);
        Panel_DSLop.setLayout(Panel_DSLopLayout);
        Panel_DSLopLayout.setHorizontalGroup(
            Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLopLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Panel_DSLopLayout.createSequentialGroup()
                .addComponent(Button_DSLop_ThemLop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_DSLop_SuaLop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_DSLop_Xoa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_DSLopLayout.setVerticalGroup(
            Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLopLayout.createSequentialGroup()
                .addGroup(Panel_DSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_DSLop_ThemLop)
                    .addComponent(Button_DSLop_SuaLop)
                    .addComponent(Button_DSLop_Xoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_DSLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_DSLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_DSLop_ThemLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_DSLop_ThemLopMouseClicked
        // TODO add your handling code here:
        ThemSuaLop lop = new ThemSuaLop(null,true);
        lop.setVisible(true);
        lop.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
        Vector vector = new Vector();
        if(lop.getButton()){
            vector.add(lop.getTenLop());
            vector.add(lop.getKhoiHoc());
            vector.add(lop.getHocKy());
            vector.add(lop.getGiaoVien());
            vector.add(lop.getBday());
            vector.add(lop.getEday());
            vector.add(lop.getSoHS());
            vector.add(0);
            vector.add("Đang giảng dậy");
            vector.add(false);
            model.addRow(vector);
        }
    }//GEN-LAST:event_Button_DSLop_ThemLopMouseClicked

    private void Button_DSLop_SuaLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_DSLop_SuaLopMouseClicked
              int count =1, row=0;
        for(int i = model.getRowCount()-1;i>=0;i--){
            if((Boolean)model.getValueAt(i, 9)==true){
                count--;
                row = i;
            }
        }
        if(count!=0){
            if(count==1){
                JOptionPane.showMessageDialog(null,"Bạn chưa chọn lớp cần chỉnh sửa",null,JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"Hệ thống chỉ cho phép chỉnh sửa một đối tượng tại một thời điểm",null,JOptionPane.INFORMATION_MESSAGE);
                for(int i = model.getRowCount()-1;i>=0;i--){
                    model.setValueAt(false,i,9);
               }
            }
            return;
        }
        Vector vec =  (Vector) model.getDataVector().elementAt(row);
        ThemSuaLop lop = new ThemSuaLop(null, true, vec);
        lop.setVisible(true);
        lop.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
        if(lop.getButton()){
            model.setValueAt(lop.getTenLop(), row, 0);
            model.setValueAt(lop.getKhoiHoc(), row, 1);
            model.setValueAt(lop.getHocKy(), row, 2);
            model.setValueAt(lop.getGiaoVien(), row, 3);
            model.setValueAt(lop.getBday(), row, 4);
            model.setValueAt(lop.getEday(), row, 5);
            model.setValueAt(lop.getSoHS(), row, 7);
            model.setValueAt(lop.getTrangThai(), row, 8);
            model.setValueAt(false, row,9);
        }
    }//GEN-LAST:event_Button_DSLop_SuaLopMouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            String tenlop=jTable4.getValueAt(jTable4.getSelectedRow(),0).toString();
            LopX aa = new LopX(tenlop);
            aa.setName(tenlop);
            center.add(tenlop, aa);
            center.setSelectedComponent(aa);
            aa.center = center;
        new CloseTabButton(center,center.getComponentCount()-2);
           
            
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void Button_DSLop_XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_DSLop_XoaMouseClicked
        // TODO add your handling code here:
        EditTable edit= new EditTable();
        edit.removeManyRowOfTable(jTable4, 9);
    }//GEN-LAST:event_Button_DSLop_XoaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Button_DSLop_SuaLop;
    private javax.swing.JLabel Button_DSLop_ThemLop;
    private javax.swing.JLabel Button_DSLop_Xoa;
    private javax.swing.JPanel Panel_DSLop;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
