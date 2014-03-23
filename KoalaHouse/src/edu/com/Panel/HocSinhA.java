/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.DataTable;
import edu.com.Dialog.HoaDon;
import edu.com.Dialog.LichSuDongTien2;
import edu.com.Dialog.Themphi;
import edu.com.Dialog.chiTietTrongMuon2;
import edu.com.upbang.AddRowOfTable;
import edu.com.upbang.EditTable;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pham The Quyen
 */
public class HocSinhA extends javax.swing.JPanel {
    public int id;
    public ArrayList lateday;
    public static String tenHS;
    public static String tenNguoiDaiDien;
    public static String ngaySinh;
    public static String SDT;
    public static String hinhThucHoc;
    public static String lop;
    public static String trungTam;
    public static String trinhDo;
    public static String soNgayTrongMuon;
    private DefaultTableModel model;
    public static JTable DSphi;
    /**
     * Creates new form HocSinhA
     */
    public HocSinhA() {
        initComponents();
        new DataBase.DataTable().BangHocPhiCuaHocSinh(1, DanhSachPhi);
        model = (DefaultTableModel) DanhSachPhi.getModel();
        edit_ten.setText(tenHS);
        edit_daidien.setText(tenNguoiDaiDien);
        NgaySinh.setText(ngaySinh);
        SoDT.setText(SDT);
        HinhThucHoc.setText(hinhThucHoc);
        Lop.setText(lop);
        TrinhDo.setText(trinhDo);
        TrungTam.setText(trungTam);
        SoNgayTrongMuon.setText(soNgayTrongMuon);
        LichSuDongTien2.ls.clear();
        Total();
    }
    public String getTenHS()// lay ten hs
    {
        return edit_ten.getText();
    }
    public String getDaiDien()// lay dai dien
    {
        return NgaySinh.getText();
    }
    public void Total(){
        int Total = 0;
        for(int i=0;i<model.getRowCount();i++){
            Total += Integer.parseInt(model.getValueAt(i, 3).toString());
        }
        tongSoPhi.setText(String.valueOf(Total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_hocsinhA = new javax.swing.JPanel();
        NgaySinh = new javax.swing.JTextField();
        edit_daidien = new javax.swing.JTextField();
        edit_ten = new javax.swing.JTextField();
        SoDT = new javax.swing.JTextField();
        HinhThucHoc = new javax.swing.JTextField();
        Lop = new javax.swing.JTextField();
        TrinhDo = new javax.swing.JTextField();
        TrungTam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DanhSachPhi = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        tongSoPhi = new javax.swing.JTextField();
        Button_HS_CapNhat = new javax.swing.JButton();
        Button_HS_Dong = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        debt = new javax.swing.JTextField();
        Button_HocSinh_ThanhToan = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        SoNgayTrongMuon = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        Button_HocSinh_ThemPhi = new javax.swing.JButton();
        Button_HS_LichSuDongTien = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        Panel_hocsinhA.setFocusCycleRoot(true);
        Panel_hocsinhA.setRequestFocusEnabled(false);
        Panel_hocsinhA.setVerifyInputWhenFocusTarget(false);

        NgaySinh.setText("2013-03-08");

        edit_daidien.setText("Nguyễn Ngọc Lan");

        edit_ten.setText("Nguyễn Hải Anh\t");

        SoDT.setText("0169.437.9201");

        HinhThucHoc.setText("Chinh quy\t");

        Lop.setText("SUNSHINE_1");

        TrinhDo.setText("NẮNG MAI (SUNSHINE)");

        jLabel1.setText("Họ tên");

        jLabel2.setText("Ngày sinh");

        jLabel3.setText("Phụ huynh");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Hình thức học");

        jLabel6.setText("Lớp");

        jLabel7.setText("Trình độ");

        jLabel8.setText("Trung tâm");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Danh Sách Phí");

        DanhSachPhi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên phí", "Kỳ học", "Năm học", "Số tiền", "Đánh dấu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(DanhSachPhi);

        jLabel10.setText("Tổng số phí");

        Button_HS_CapNhat.setText("Cập nhật");
        Button_HS_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HS_CapNhatActionPerformed(evt);
            }
        });

        Button_HS_Dong.setText("Thôi Học");
        Button_HS_Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HS_DongActionPerformed(evt);
            }
        });

        jLabel13.setText("Đang Nợ");

        debt.setText("0");

        Button_HocSinh_ThanhToan.setText("Thanh Toán");
        Button_HocSinh_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HocSinh_ThanhToanActionPerformed(evt);
            }
        });

        jLabel16.setText("Số ngày trông muộn");

        jButton10.setText("Chi Tiết");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        Button_HocSinh_ThemPhi.setText("Thêm Phí");
        Button_HocSinh_ThemPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HocSinh_ThemPhiActionPerformed(evt);
            }
        });

        Button_HS_LichSuDongTien.setText("Lịch sử đóng tiền");
        Button_HS_LichSuDongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HS_LichSuDongTienActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon("D:\\version 23\\ProjectKoala\\ProjectKoala\\KoalaHouse\\src\\edu\\com\\image\\xoa.jpg")); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_hocsinhALayout = new javax.swing.GroupLayout(Panel_hocsinhA);
        Panel_hocsinhA.setLayout(Panel_hocsinhALayout);
        Panel_hocsinhALayout.setHorizontalGroup(
            Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edit_daidien, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edit_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HinhThucHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)
                                        .addComponent(jLabel11))
                                    .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(tongSoPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                            .addComponent(Button_HocSinh_ThanhToan)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Button_HocSinh_ThemPhi)
                                            .addGap(73, 73, 73)
                                            .addComponent(Button_HS_LichSuDongTien))
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39))
                            .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Button_HS_Dong, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                    .addComponent(SoNgayTrongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton10)))))
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addComponent(Button_HS_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        Panel_hocsinhALayout.setVerticalGroup(
            Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                            .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(edit_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(19, 19, 19))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit_daidien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_HocSinh_ThemPhi)
                    .addComponent(Button_HocSinh_ThanhToan)
                    .addComponent(Button_HS_LichSuDongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HinhThucHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongSoPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(SoNgayTrongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_HS_CapNhat)
                    .addComponent(Button_HS_Dong))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_hocsinhA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_hocsinhA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_HS_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_CapNhatActionPerformed

    }//GEN-LAST:event_Button_HS_CapNhatActionPerformed

    private void Button_HS_DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_DongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_HS_DongActionPerformed

    private void Button_HocSinh_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HocSinh_ThanhToanActionPerformed
        HoaDon.tenHS = edit_ten.getText();
        HoaDon.tenNguoiDaiDien = edit_daidien.getText();
        HoaDon.trungTam = TrungTam.getText();
        HoaDon.idhs = id;
        HoaDon.lop = Lop.getText();
        DSphi = DanhSachPhi;
        HoaDon receipts= new HoaDon(null, true);
        receipts.setLocation(360, 0);
        receipts.show();
        if(receipts.getButton()){
            EditTable edit = new EditTable();
            LichSuDongTien2.temp = edit.getAllRow(DanhSachPhi);
            for(int i=0;i< LichSuDongTien2.temp.size();i++){
                LichSuDongTien2.ls.add(LichSuDongTien2.temp.get(i));
            }
            int k = model.getRowCount();
            for(int i = 0;i<k;i++)
                model.removeRow(0);
            Total();
        }
        
    }//GEN-LAST:event_Button_HocSinh_ThanhToanActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(lateday.size()>0){
            chiTietTrongMuon2.vt.removeAllElements();
            for(int i=0;i<lateday.size();i++){
                chiTietTrongMuon2.vt.add(lateday.get(i));
            }
        }
        chiTietTrongMuon2.Info = edit_ten.getText();
        chiTietTrongMuon2 chitiet = new chiTietTrongMuon2(null,true);
        chitiet.setLocation(500,130);
        Integer i = new Integer(SoNgayTrongMuon.getText());
        if(i >0) {
            chitiet.show();
        } else {
            JOptionPane.showMessageDialog(Panel_hocsinhA, "Hoc Sinh này không có trông muộn");
        }
       
    }//GEN-LAST:event_jButton10ActionPerformed

    private void Button_HocSinh_ThemPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HocSinh_ThemPhiActionPerformed
        // TODO add your handling code here:
        Themphi themphi= new Themphi(null,true);
        themphi.setLocation(320, 130);
        themphi.show();
        if(themphi.getButton())
        {
        int count = model.getRowCount();// lay so Row hien co
        EditTable edit = new EditTable();
        ArrayList a = new ArrayList();
        a = edit.getCost(themphi.jTable2);//lay tat ca cac row ma dc tich danh dau
        for(int i=0;i<a.size();i++){
            boolean check = true;//bien kiem tra xem row dc them co trung voi row da co
            Vector v= new Vector();
            v = (Vector) a.get(i);
            v.remove(4);
            for(int j=0;j<count;j++){
                Vector v1 = (Vector) model.getDataVector().elementAt(j);
                System.out.println(v.equals(v1));
                if(v.equals(v1)){
                    check = false;
                    break;
                }
            }
            if(check){//neu ko bi trung voi row nao thi them vao bang
            AddRowOfTable addrow = new AddRowOfTable();
            addrow.addRowOfTable(model, v);//them vector v vao bang
            }
            else{
                JOptionPane.showMessageDialog(null,v.get(0)+" Kì "+v.get(1)+" Năm học "+v.get(2)+" đã bị trùng");
            }
        }
        Total();
        }
    }//GEN-LAST:event_Button_HocSinh_ThemPhiActionPerformed

    private void Button_HS_LichSuDongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_LichSuDongTienActionPerformed
        //lichSuDongTien ls = new lichSuDongTien(this, rootPaneCheckingEnabled);
        LichSuDongTien2 lic= new LichSuDongTien2(null,true);
        lic.setLocation(380, 130);
        lic.show();

    }//GEN-LAST:event_Button_HS_LichSuDongTienActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        EditTable edit = new EditTable();
        edit.removeRowOfTable(DanhSachPhi);
        Total();
    }//GEN-LAST:event_jLabel11MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_HS_CapNhat;
    private javax.swing.JButton Button_HS_Dong;
    private javax.swing.JButton Button_HS_LichSuDongTien;
    private javax.swing.JButton Button_HocSinh_ThanhToan;
    private javax.swing.JButton Button_HocSinh_ThemPhi;
    private javax.swing.JTable DanhSachPhi;
    private javax.swing.JTextField HinhThucHoc;
    private javax.swing.JTextField Lop;
    private javax.swing.JTextField NgaySinh;
    private javax.swing.JPanel Panel_hocsinhA;
    private javax.swing.JTextField SoDT;
    private javax.swing.JTextField SoNgayTrongMuon;
    private javax.swing.JTextField TrinhDo;
    private javax.swing.JTextField TrungTam;
    private javax.swing.JTextField debt;
    private javax.swing.JTextField edit_daidien;
    private javax.swing.JTextField edit_ten;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField tongSoPhi;
    // End of variables declaration//GEN-END:variables
}
