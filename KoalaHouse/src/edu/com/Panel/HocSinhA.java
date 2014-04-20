/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.DataTable;
import DataBase.HocSinh.AStudentAndLateDay;
import DataBase.HocSinh.CostOfStudent;
import DataBase.HocSinh.RecieptManagerment;
import edu.com.Dialog.ChiTietNghiPhep;
import edu.com.Dialog.HoaDon;
import edu.com.Dialog.LichSuDongTien2;
import edu.com.Dialog.Themphi;
import edu.com.Dialog.ThongBao;
import edu.com.Dialog.chiTietTrongMuon2;
import edu.com.XuLy;
import edu.com.upbang.AddRowOfTable;
import edu.com.upbang.EditTable;
import edu.com.upbang.XuLiXau;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    public int idTrungTam;
    public ArrayList lateday;
    public String tenHS;
    public String tenNguoiDaiDien;
    public String ngaySinh;
    public String SDT;
    public String hinhThucHoc;
    public String lop;
    public String trungTam;
    public String trinhDo;
    public String soNgayTrongMuon;
    public int totalTime;
    private DefaultTableModel model;
    public String ten;
    /**
     * Creates new form HocSinhA
     */
    public HocSinhA(int idTemp) {
        initComponents();
        id = idTemp;
        AStudentAndLateDay a = new AStudentAndLateDay();
            ArrayList info;
            info = a.HocSinhA1(id);
            ten = (String) info.get(1);
            tenNguoiDaiDien = (String) info.get(4);
            ngaySinh = (String) info.get(2);
            SDT = (String) info.get(3);
            hinhThucHoc = (String) info.get(7);
            lop = (String) info.get(6);
            trinhDo = (String) info.get(5);
            trungTam = (String) info.get(8);
            String thoihoc = (String) info.get(9);
            if(thoihoc.equals("0")){
                thoiHoc.setText("Đã Thôi Học");
                thoiHoc.setEnabled(false);
            }
        edit_ten.setText(ten);
        edit_daidien.setText(tenNguoiDaiDien);
        NgaySinh.setText(ngaySinh);
        SoDT.setText(SDT);
        HinhThucHoc.setText(hinhThucHoc);
        Lop.setText(lop);
        TrinhDo.setText(trinhDo);
        TrungTam.setText(trungTam);
        int noPhi = new AStudentAndLateDay().GetDebt(id);
        debt.setText(XuLy.setMoney(String.valueOf(noPhi)));
        switch(TrungTam.getText().length()){
            case 20: idTrungTam = 1;break;
            case 22: idTrungTam = 2;break;
            case 24: idTrungTam = 3;break;
            case 25: idTrungTam = 4;break;
        }
        totalTime = a.LateDay(id, idTrungTam);//1 la ma Trung tam
        TimeTrongMuon.setText(String.valueOf(totalTime));
        new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
        model = (DefaultTableModel) DanhSachPhi.getModel();
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
            Total += Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 4).toString()));
        }
        tongSoPhi.setText(XuLy.setMoney(String.valueOf(Total)));
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
        thoiHoc = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        debt = new javax.swing.JTextField();
        Button_HocSinh_ThanhToan = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        TimeTrongMuon = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        Button_HocSinh_ThemPhi = new javax.swing.JButton();
        Button_HS_LichSuDongTien = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        Panel_hocsinhA.setFocusCycleRoot(true);
        Panel_hocsinhA.setRequestFocusEnabled(false);
        Panel_hocsinhA.setVerifyInputWhenFocusTarget(false);

        NgaySinh.setText("2013-03-08");

        edit_daidien.setText("Nguyễn Ngọc Lan");

        edit_ten.setText("Nguyễn Hải Anh\t");

        SoDT.setText("0169.437.9201");

        HinhThucHoc.setEditable(false);
        HinhThucHoc.setText("Chinh quy\t");

        Lop.setEditable(false);
        Lop.setText("SUNSHINE_1");

        TrinhDo.setEditable(false);
        TrinhDo.setText("NẮNG MAI (SUNSHINE)");

        TrungTam.setEditable(false);

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

        tongSoPhi.setEditable(false);

        Button_HS_CapNhat.setText("Cập nhật");
        Button_HS_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HS_CapNhatActionPerformed(evt);
            }
        });

        thoiHoc.setText("Thôi Học");
        thoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoiHocActionPerformed(evt);
            }
        });

        jLabel13.setText("Đang Nợ");

        debt.setEditable(false);
        debt.setText("0");
        debt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debtActionPerformed(evt);
            }
        });

        Button_HocSinh_ThanhToan.setText("Thanh Toán");
        Button_HocSinh_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HocSinh_ThanhToanActionPerformed(evt);
            }
        });

        jLabel16.setText("Thời gian trông muộn");

        TimeTrongMuon.setEditable(false);

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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/xoa.jpg"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jButton1.setText("Thông báo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Phút");

        jButton2.setText("Nghỉ Phép");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edit_daidien, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edit_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HinhThucHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tongSoPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(119, 119, 119)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(132, 132, 132)
                                            .addComponent(jLabel11))
                                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Button_HocSinh_ThemPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(235, 235, 235)
                                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(Button_HocSinh_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(Button_HS_LichSuDongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addGap(39, 39, 39))
                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                        .addComponent(TimeTrongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12))
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(thoiHoc, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
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
                            .addGap(31, 31, 31))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
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
                .addGap(35, 35, 35)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HinhThucHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_HocSinh_ThemPhi)
                    .addComponent(Button_HS_LichSuDongTien))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_HocSinh_ThanhToan)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongSoPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TimeTrongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_HS_CapNhat)
                    .addComponent(thoiHoc)
                    .addComponent(jButton2))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Panel_hocsinhA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_hocsinhA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_HS_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_CapNhatActionPerformed
        new AStudentAndLateDay().UpdataInfoStudent(id,idTrungTam,edit_ten.getText(),new XuLiXau().NamThangNgay(NgaySinh.getText()),edit_daidien.getText(),SoDT.getText());
        JOptionPane.showMessageDialog(Panel_hocsinhA, "Thông Tin Học Sinh Đã Được Cập Nhật");
    }//GEN-LAST:event_Button_HS_CapNhatActionPerformed

    private void thoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoiHocActionPerformed
        
        int click = JOptionPane.showConfirmDialog(null, "Bạn Chắc Muốn Cho Học Sinh Này Thôi Học?", "",JOptionPane.OK_CANCEL_OPTION);
        if(click == JOptionPane.YES_OPTION){
            new AStudentAndLateDay().ThoiHoc(id, idTrungTam);
            JOptionPane.showMessageDialog(Panel_hocsinhA, "Học Sinh Này Đã Bị Thôi Học");
            thoiHoc.setText("Đã Thôi Học");
            thoiHoc.setEnabled(false);
        }
    }//GEN-LAST:event_thoiHocActionPerformed

    private void Button_HocSinh_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HocSinh_ThanhToanActionPerformed
        HoaDon.tenHS = edit_ten.getText();
        HoaDon.tenNguoiDaiDien = edit_daidien.getText();
        HoaDon.trungTam = TrungTam.getText();
        HoaDon.idhs = id;
        HoaDon.lop = Lop.getText();
        HoaDon.idTT = idTrungTam;
        HoaDon receipts= new HoaDon(null, true);
        receipts.setLocation(360, 0);
        receipts.show();
        if(receipts.getButton()){
            //them cac phi vao lich su dong tien
            //xoa cac phi trong bang
            int k = model.getRowCount();
            for(int i = 0;i<k;i++){
                int idCost = Integer.parseInt(model.getValueAt(0,0).toString());
                new CostOfStudent().UpdatePhiCuaHs(id, idCost, idTrungTam);
                model.removeRow(0);
            }
            int a= new RecieptManagerment().PhiDatCoc(id);
            if(a!= 0){
                new CostOfStudent().UpdatePhiCuaHs(id,1,idTrungTam);
            }
            Total();
        }
        new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
        model = (DefaultTableModel) DanhSachPhi.getModel();
        int noPhi = new AStudentAndLateDay().GetDebt(id);
        debt.setText(XuLy.setMoney(String.valueOf(noPhi)));
    }//GEN-LAST:event_Button_HocSinh_ThanhToanActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        chiTietTrongMuon2.idStudent = id;
        chiTietTrongMuon2.idTrungTam = idTrungTam;
        chiTietTrongMuon2.Info = edit_ten.getText();
        chiTietTrongMuon2 chitiet = new chiTietTrongMuon2(null,true);
        chitiet.setLocation(500,130);
        chitiet.setVisible(true);
        if(chitiet.them){
            totalTime = new AStudentAndLateDay().LateDay(id, idTrungTam);
            TimeTrongMuon.setText(String.valueOf(totalTime));
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void Button_HocSinh_ThemPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HocSinh_ThemPhiActionPerformed
        // TODO add your handling code here:
        Themphi.idStudent = id;
        Themphi.idFac = idTrungTam;
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
            v.remove(5);
            String idCost = v.get(0).toString();
            if(check){//neu ko bi trung voi row nao thi them vao bang
            AddRowOfTable addrow = new AddRowOfTable();
            addrow.addRowOfTable(model, v);//them vector v vao bang
              new CostOfStudent().InsertDSPhiCuaHS(id,idCost, idTrungTam);
              //neu la phi trong muon
              //kiem tra xem co phai phi trong muon khong
              int num = model.getRowCount();
                String ten = model.getValueAt(num - 1, 1).toString();
                boolean check2 = false;
        ten = ten.toLowerCase();
        if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
            check2 = true;
        }
                if(check2){
                    String ki = "0";
                    String kiHoc = model.getValueAt(num - 1, 2).toString();
                    if(kiHoc.equals("Kỳ 1")){
                        ki = "1";
                    }
                    else if(kiHoc.equals("Kỳ 2")){
                        ki = "2";
                    }
                    else if(kiHoc.equals("Kỳ 3")){
                        ki = "3";
                    }
                    else if(kiHoc.equals("Kỳ hè")){
                        ki = "4";
                    }
                    else ki = "5";
                    String nam = model.getValueAt(num -1, 3).toString();
                    TimeTrongMuon.setText("0");
                    new AStudentAndLateDay().UpdateTrongMuon(id,ki,nam);
                }
                else{
                    
                }
            }
            else{
                JOptionPane.showMessageDialog(null,v.get(0)+" Kì "+v.get(1)+" Năm học "+v.get(2)+" đã bị trùng");
            }
        }
        Total();
        }
        new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
        model = (DefaultTableModel) DanhSachPhi.getModel();
        Total();
    }//GEN-LAST:event_Button_HocSinh_ThemPhiActionPerformed

    private void Button_HS_LichSuDongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_LichSuDongTienActionPerformed
        //lichSuDongTien ls = new lichSuDongTien(this, rootPaneCheckingEnabled);
        LichSuDongTien2.idStudent = id;
        LichSuDongTien2 lic= new LichSuDongTien2(null,true);
        lic.setLocation(380, 130);
        lic.id = id;
        lic.show();

    }//GEN-LAST:event_Button_HS_LichSuDongTienActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        if(DanhSachPhi.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null ,"Hãy chọn một dòng cần xóa");
        }
        else{
        int row  = DanhSachPhi.getSelectedRow();
        int idCost = Integer.parseInt(DanhSachPhi.getValueAt(row, 0).toString());
        //kiem tra xem co phai phi trong muon khong
              int num = DanhSachPhi.getSelectedRow();
                String ten = model.getValueAt(num, 1).toString();
                boolean check2 = false;
        ten = ten.toLowerCase();
        if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
            check2 = true;
        }
                if(check2){
                    String ki = "0";
                    String kiHoc = model.getValueAt(num, 2).toString();
                    if(kiHoc.equals("Kỳ 1")){
                        ki = "1";
                    }
                    else if(kiHoc.equals("Kỳ 2")){
                        ki = "2";
                    }
                    else if(kiHoc.equals("Kỳ 3")){
                        ki = "3";
                    }
                    else if(kiHoc.equals("Kỳ hè")){
                        ki = "4";
                    }
                    else ki = "5";
                    String nam = model.getValueAt(num, 3).toString();
                    TimeTrongMuon.setText("0");
                    new AStudentAndLateDay().XoaTrongMuon(id,ki,nam);
                }
        new CostOfStudent().DeleteDSPhiCuaHs(id, idCost, idTrungTam);
        EditTable edit = new EditTable();
        edit.removeRowOfTable(DanhSachPhi);
        Total();
        totalTime = new AStudentAndLateDay().LateDay(id, idTrungTam);
        TimeTrongMuon.setText(String.valueOf(totalTime));
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ThongBao.tenHS = edit_ten.getText();
        ThongBao.tenNguoiDaiDien = edit_daidien.getText();
        ThongBao.trungTam = idTrungTam;
        ThongBao.idhs = id;
        ThongBao.lop = Lop.getText();
        ThongBao tb = new ThongBao(null, true);
        tb.setLocation(360, 0);
        tb.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ChiTietNghiPhep.idStudent = id;
        ChiTietNghiPhep.idTrungTam = idTrungTam;
        ChiTietNghiPhep chitiet = new ChiTietNghiPhep(null,true);
        chitiet.setLocation(500,130);
        chitiet.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void debtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_HS_CapNhat;
    private javax.swing.JButton Button_HS_LichSuDongTien;
    private javax.swing.JButton Button_HocSinh_ThanhToan;
    private javax.swing.JButton Button_HocSinh_ThemPhi;
    private javax.swing.JTable DanhSachPhi;
    private javax.swing.JTextField HinhThucHoc;
    private javax.swing.JTextField Lop;
    private javax.swing.JTextField NgaySinh;
    private javax.swing.JPanel Panel_hocsinhA;
    private javax.swing.JTextField SoDT;
    private javax.swing.JTextField TimeTrongMuon;
    private javax.swing.JTextField TrinhDo;
    private javax.swing.JTextField TrungTam;
    private javax.swing.JTextField debt;
    private javax.swing.JTextField edit_daidien;
    private javax.swing.JTextField edit_ten;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JButton thoiHoc;
    private javax.swing.JTextField tongSoPhi;
    // End of variables declaration//GEN-END:variables
}
