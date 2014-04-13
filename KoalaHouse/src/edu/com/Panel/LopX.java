/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Panel;

import DataBase.DataTable;
import DataBase.SQLLopX;
import edu.com.CloseButton.CloseTabButton;
import edu.com.Dialog.NghiPhep;
import edu.com.Dialog.ThemHS;
import edu.com.Dialog.chuyenlop;
import edu.com.Dialog.trongmuon;
import edu.com.ListKoala;
import edu.com.XuLy;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pham The Quyen
 */
public class LopX extends javax.swing.JPanel {

    private DefaultTableModel model;
    public JTabbedPane center;
    private int malop;
    ArrayList<Integer> id_students;

    /**
     * Creates new form LopX
     */
    public LopX(String tenlop) {
        initComponents();
        malop = new DataBase.DataTable().LayIdTenLop(tenlop);
        new DataBase.SQLLopX().BangDanhHSLop(malop, lopx);
        model = (DefaultTableModel) lopx.getModel();
        id_students = new ArrayList<Integer>();
        if (!lopx.getValueAt(0, 0).toString().equals("")) {
            XuLy.setID(id_students, lopx, 0);
        }
    }

    public void setMaLop(int malop) {
        this.malop = malop;
    }

    public int getMaLop() {
        return malop;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        lopx = new javax.swing.JTable();
        them = new javax.swing.JLabel();
        sua = new javax.swing.JLabel();
        xoa = new javax.swing.JLabel();
        chuyenlop = new javax.swing.JButton();
        loc = new javax.swing.JComboBox();
        textfield_timkiem = new javax.swing.JTextField();
        chitiet = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        lopx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        lopx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lopxMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lopx);

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

        chuyenlop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chuyenlop.setText("Chuyển Lớp");
        chuyenlop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chuyenlopActionPerformed(evt);
            }
        });

        loc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tât cả", "Chính quy", "Khách", "Đóng tiền đầy đủ", "Nợ" }));
        loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locActionPerformed(evt);
            }
        });

        textfield_timkiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textfield_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_timkiemActionPerformed(evt);
            }
        });
        textfield_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textfield_timkiemKeyTyped(evt);
            }
        });

        chitiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/chitiet.png"))); // NOI18N
        chitiet.setToolTipText("Xem chi tiết");
        chitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chitietMouseClicked(evt);
            }
        });

        jButton1.setText("Tìm kiếm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(them)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chitiet)
                .addGap(319, 319, 319)
                .addComponent(chuyenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(textfield_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jScrollPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sua)
                    .addComponent(xoa)
                    .addComponent(them, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chitiet)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chuyenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textfield_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lopxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lopxMouseClicked
        // TODO add your handling code here:
        if (lopx.getValueAt(0, 0).toString().equals("")) {
            return;
        }
        if (evt.getClickCount() == 2) {
            int row = lopx.getSelectedRow();
            model.setValueAt(false, row, 6);
            //map cac truong
            int id = id_students.get(row);
            String tenHocSinh = lopx.getValueAt(lopx.getSelectedRow(), 1).toString();
            //HocSinhA.idTemp = id;
            HocSinhA aa = new HocSinhA(id);
            center.add(model.getValueAt(row, 1).toString(), aa);
            center.setSelectedComponent(aa);
            new CloseTabButton(center, center.getComponentCount() - 2);
        }
    }//GEN-LAST:event_lopxMouseClicked

    private void themMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themMouseClicked
        // TODO add your handling code here:
        ThemHS hs = new ThemHS(ListKoala.frame, true, malop);
        hs.setVisible(true);
        if (hs.getButton()) {
            new DataBase.SQLLopX().BangDanhHSLop(malop, lopx);
            model = (DefaultTableModel) lopx.getModel();
            id_students = new ArrayList<Integer>();
            if (!lopx.getValueAt(0, 0).toString().equals("")) {
                XuLy.setID(id_students, lopx, 0);
            }
        }
    }//GEN-LAST:event_themMouseClicked

    private void suaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suaMouseClicked
        // TODO add your handling code here:
        int count = 1, row = 0;
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 6) == true) {
                count--;
                row = i;
            }
        }
        if (count != 0) {
            if (count == 1) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn hoc sinh cần chỉnh sửa", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Hệ thống chỉ cho phép chỉnh sửa một đối tượng tại một thời điểm", null, JOptionPane.INFORMATION_MESSAGE);
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.setValueAt(false, i, 6);
                }
            }
            return;
        }
        Vector vec = (Vector) model.getDataVector().elementAt(row);
        ThemHS hs = new ThemHS(null, true, vec, id_students.get(row));
        hs.setVisible(true);
        if (hs.getButton()) {
            new DataBase.SQLLopX().BangDanhHSLop(malop, lopx);
            model = (DefaultTableModel) lopx.getModel();
            XuLy.setID(id_students, lopx, 0);
        }
    }//GEN-LAST:event_suaMouseClicked

    private void chuyenlopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chuyenlopActionPerformed
        // TODO add your handling code here:
        int count = 0;
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 6) == true) {
                count++;
            }
        }
        if (count == 0) {
            return;
        }
        chuyenlop chuyen = new chuyenlop(null, true, getName());
        chuyen.setVisible(true);
        String tenlop = chuyen.getTenLop();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 6) == true) {
                new SQLLopX().chuyenlopHocSinh(id_students.get(i), tenlop);
                model.removeRow(i);
            }
        }

    }//GEN-LAST:event_chuyenlopActionPerformed

    private void xoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaMouseClicked
        // TODO add your handling code here:
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 6) == true) {
                new DataBase.SQLLopX().xoaHocSinh(id_students.get(i));
            }
        }
        new DataBase.SQLLopX().BangDanhHSLop(malop, lopx);
        model = (DefaultTableModel) lopx.getModel();
        if (!lopx.getValueAt(0, 0).toString().equals("")) {
            XuLy.setID(id_students, lopx, 0);
        }
    }//GEN-LAST:event_xoaMouseClicked

    private void chitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chitietMouseClicked
        // TODO add your handling code here:
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if ((Boolean) model.getValueAt(i, 6) == true) {
                model.setValueAt(false, i, 6);
                int id = id_students.get(i);
                String tenHocSinh = lopx.getValueAt(lopx.getSelectedRow(), 1).toString();
                //HocSinhA.idTemp = id;
                HocSinhA aa = new HocSinhA(id);
                center.add(model.getValueAt(i, 1).toString(), aa);
                center.setSelectedComponent(aa);
                new CloseTabButton(center, center.getComponentCount() - 2);
            }
        }
    }//GEN-LAST:event_chitietMouseClicked

    private void textfield_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_timkiemActionPerformed
        // TODO add your handling code here:
        if (textfield_timkiem.getText().equals("")) {
            this.locActionPerformed(evt);
        }
    }//GEN-LAST:event_textfield_timkiemActionPerformed

    private void locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locActionPerformed
        // TODO add your handling code here:
        switch (loc.getSelectedIndex()) {
            case 0:
                new DataBase.SQLLopX().BangDanhHSLop(malop, lopx);
                model = (DefaultTableModel) lopx.getModel();
                break;
            case 1:
                new DataBase.SQLLopX().BangDanhHSLop_iType(malop, lopx, 0);
                model = (DefaultTableModel) lopx.getModel();
                break;
            case 2:
                new DataBase.SQLLopX().BangDanhHSLop_iType(malop, lopx, 1);
                model = (DefaultTableModel) lopx.getModel();
                break;
            case 3:
                new DataBase.SQLHocPhi().BangDanhSachHSDongDuPhi(malop, lopx);
                model = (DefaultTableModel) lopx.getModel();
                break;
            case 4:
                new DataBase.SQLHocPhi().BangDanhSachHocSinhNoPhi(malop, lopx);
                model = (DefaultTableModel) lopx.getModel();
                break;
        }
        if (!lopx.getValueAt(0, 0).toString().equals("")) {
            XuLy.setID(id_students, lopx, 0);
        }
    }//GEN-LAST:event_locActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if (lopx.getValueAt(0, 0).toString().equals("")) {
            return;
        }
        String thongtin = textfield_timkiem.getText() + "";
        if (thongtin.equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin tìm kiếm", null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            boolean a = new DataBase.SQLLopX().timhocsinh(thongtin, lopx, malop);
            if (a == false) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin vừa nhận!", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!lopx.getValueAt(0, 0).toString().equals("")) {
                    XuLy.setID(id_students, lopx, 0);
                }
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void textfield_timkiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_timkiemKeyTyped
        // TODO add your handling code here:
        if (textfield_timkiem.getText().equals("")) {
            switch (loc.getSelectedIndex()) {
                case 0:
                    new DataBase.SQLLopX().BangDanhHSLop(malop, lopx);
                    model = (DefaultTableModel) lopx.getModel();
                    break;
                case 1:
                    new DataBase.SQLLopX().BangDanhHSLop_iType(malop, lopx, 0);
                    model = (DefaultTableModel) lopx.getModel();
                    break;
                case 2:
                    new DataBase.SQLLopX().BangDanhHSLop_iType(malop, lopx, 1);
                    model = (DefaultTableModel) lopx.getModel();
                    break;
                case 3:
                    new DataBase.SQLHocPhi().BangDanhSachHSDongDuPhi(malop, lopx);
                    model = (DefaultTableModel) lopx.getModel();
                    break;
                case 4:
                    new DataBase.SQLHocPhi().BangDanhSachHocSinhNoPhi(malop, lopx);
                    model = (DefaultTableModel) lopx.getModel();
                    break;
            }
            if (!lopx.getValueAt(0, 0).toString().equals("")) {
                XuLy.setID(id_students, lopx, 0);
            }
        }
    }//GEN-LAST:event_textfield_timkiemKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chitiet;
    private javax.swing.JButton chuyenlop;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox loc;
    private javax.swing.JTable lopx;
    private javax.swing.JLabel sua;
    private javax.swing.JTextField textfield_timkiem;
    private javax.swing.JLabel them;
    private javax.swing.JLabel xoa;
    // End of variables declaration//GEN-END:variables
}
