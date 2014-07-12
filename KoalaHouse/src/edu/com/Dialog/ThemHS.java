/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Dialog;

import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Venus
 */
public class ThemHS extends javax.swing.JDialog {

    private boolean button;
    private int id_classes, id_student;

    /**
     * Creates new form ThemHS
     */
    public ThemHS(java.awt.Frame parent, boolean modal, int id_classes) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 350);
        this.id_classes = id_classes;
    }

    public ThemHS(java.awt.Frame parent, boolean modal, Vector vector, int id_students, int gioitinh) {
        super(parent, modal);
        initComponents();
        DongY.setText("Chỉnh sửa");
        title.setText("Chỉnh sửa thông tin học sinh");

        ten.setText(vector.get(1).toString());
        Object obj[] = vector.get(2).toString().split("-");
        setSelectComboBox(nam, obj[2]);
        setSelectComboBox(thang, obj[1]);
        setSelectComboBox(ngay, obj[0]);
        sex.setSelectedIndex(gioitinh);
        setSelectComboBox(hinhthuc, vector.get(3).toString());
        if (vector.get(5) == null) {
            ten_cha.setText("");
        } else {
            ten_cha.setText(vector.get(5).toString());
        }
        if (vector.get(6) == null) {
            So_cha.setText("");
        } else {
            So_cha.setText(vector.get(6).toString());
        }
        if (vector.get(7) == null) {
            ten_me.setText("");
        } else {
            ten_me.setText(vector.get(7).toString());
        }
        if (vector.get(8) == null) {
            so_me.setText("");
        } else {
            so_me.setText(vector.get(8).toString());
        }
        if (vector.get(9) == null) {
            email.setText("");
        } else {
            email.setText(vector.get(9).toString());
        }
        if (vector.get(4) == null) {
            so_nha.setText("");
        } else {
            so_nha.setText(vector.get(4).toString());
        }
        daidien.setText(String.valueOf(vector.get(10)));
        this.id_student = id_students;
        this.setResizable(false);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);

    }

    private void setSelectComboBox(JComboBox x, Object obj) {
        for (int i = 0; i < x.getItemCount(); i++) {
            if (x.getItemAt(i).toString().equals(obj.toString().trim())) {
                x.setSelectedIndex(i);
                break;
            }
        }
    }
    //getdulieu

    public boolean getButton()//lay xem la create hay cancle
    {
        return button;
    }

    public String getNgaySinh()// lay ngay sinh
    {
        return nam.getSelectedItem().toString() + "-" + thang.getSelectedItem().toString() + "-" + ngay.getSelectedItem().toString();
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
        ten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ten_cha = new javax.swing.JTextField();
        HuyBo = new javax.swing.JButton();
        DongY = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sex = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        thang = new javax.swing.JComboBox();
        nam = new javax.swing.JComboBox();
        ngay = new javax.swing.JComboBox();
        title = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        So_cha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ten_me = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        so_me = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        hinhthuc = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        so_nha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        daidien = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Họ Và Tên:");

        ten.setPreferredSize(new java.awt.Dimension(83, 25));
        ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên cha:");

        ten_cha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ten_chaActionPerformed(evt);
            }
        });

        HuyBo.setText("Hủy bỏ");
        HuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HuyBoMouseClicked(evt);
            }
        });

        DongY.setText("Thêm học sinh");
        DongY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DongYMouseClicked(evt);
            }
        });

        jLabel4.setText("Giới tính:");

        sex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nữ", "Nam" }));

        jLabel6.setText("Ngày Sinh:");

        thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tháng", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        ngay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Thêm học sinh");

        jLabel5.setText("Số điện thoại:");

        So_cha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                So_chaActionPerformed(evt);
            }
        });

        jLabel7.setText("Tên mẹ:");

        ten_me.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ten_meActionPerformed(evt);
            }
        });

        jLabel9.setText("Số điện thoại:");

        so_me.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_meActionPerformed(evt);
            }
        });

        jLabel10.setText("Hình Thức Học:");

        hinhthuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chính quy", "Khách" }));

        jLabel11.setText("Email:");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel12.setText("Số điện nhà:");

        so_nha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_nhaActionPerformed(evt);
            }
        });

        jLabel2.setText("Người đại diện");

        daidien.setColumns(20);
        daidien.setRows(5);
        jScrollPane1.setViewportView(daidien);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(ten_me, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ten_cha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(So_cha, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(so_me, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(so_nha, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(222, 222, 222)
                                .addComponent(DongY, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane1)))))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ten_cha, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(So_cha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(so_me, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ten_me))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(so_nha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DongY, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ten_chaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ten_chaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ten_chaActionPerformed

    private void ngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngayActionPerformed

    private void tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenActionPerformed

    private void DongYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DongYMouseClicked
        // TODO add your handling code here:
        if (ten.getText().equals("") || ngay.getSelectedIndex() == 0 || thang.getSelectedIndex() == 0 || nam.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa hoàn thành thông tin cơ bản của học sinh", null, JOptionPane.INFORMATION_MESSAGE);
            button = false;
            return;
        }
        Vector v = new Vector();
        v.add(ten.getText());
        v.add(getNgaySinh());
        v.add(So_cha.getText());
        v.add(ten_cha.getText());
        v.add(hinhthuc.getSelectedIndex());
        v.add(ten_me.getText());
        v.add(so_me.getText());
        v.add(so_nha.getText());
        v.add(email.getText());
        v.add(sex.getSelectedIndex());
        v.add(daidien.getText());
        if (!DongY.getText().equals("Chỉnh sửa")) {
            NgayHoc ngayhoc = new NgayHoc(null, true, true);
            ngayhoc.setVisible(true);
            if (ngayhoc.getFlags()) {
                v.add(ngayhoc.getDate());
                if (new DataBase.SQLLopX().themHocSinh(v, id_classes)) {
                    button = true;
                    dispose();
                }
            }

        } else if (DongY.getText().equals("Chỉnh sửa") && new DataBase.SQLLopX().suaHocSinh(v, id_student)) {
            button = true;
            dispose();
        }
    }//GEN-LAST:event_DongYMouseClicked

    private void HuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HuyBoMouseClicked
        // TODO add your handling code here:
        button = false;
        dispose();
    }//GEN-LAST:event_HuyBoMouseClicked

    private void So_chaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_So_chaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_So_chaActionPerformed

    private void ten_meActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ten_meActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ten_meActionPerformed

    private void so_meActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_meActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_so_meActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void so_nhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_nhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_so_nhaActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DongY;
    private javax.swing.JButton HuyBo;
    private javax.swing.JTextField So_cha;
    private javax.swing.JTextArea daidien;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox hinhthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox nam;
    private javax.swing.JComboBox ngay;
    private javax.swing.JComboBox sex;
    private javax.swing.JTextField so_me;
    private javax.swing.JTextField so_nha;
    private javax.swing.JTextField ten;
    private javax.swing.JTextField ten_cha;
    private javax.swing.JTextField ten_me;
    private javax.swing.JComboBox thang;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
