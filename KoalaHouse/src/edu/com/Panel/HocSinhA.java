/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.DataTable;
import DataBase.HocSinh.AStudentAndLateDay;
import DataBase.HocSinh.CostOfStudent;
import DataBase.HocSinh.Get;
import DataBase.HocSinh.RecieptManagerment;
import edu.com.Dialog.ChiTietNghiPhep;
import edu.com.Dialog.HoaDon;
import edu.com.ThongBaoKyHe.ThongBaoKyGeVer2;
import edu.com.Dialog.LichSuDongTien2;
import edu.com.Dialog.NgayHoc;
import edu.com.Dialog.Themphi;
import edu.com.Dialog.ThongBao;
import edu.com.Dialog.chiTietTrongMuon2;
import edu.com.Dialog.chonThongBao;
import edu.com.Dialog.selectThongBao;
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
    public String father;
    public String mother;
    public String sdtbo;
    public String sdtme;
    public String homephone;
    public String thudt;
    public String trungTam;
    public String trinhDo;
    public String soNgayTrongMuon;
    public String ngDaiDien;
    public int totalTime;
    public String nhaphoc;
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
            ngaySinh = (String) info.get(2);
            father = (String) info.get(3);
            sdtbo = (String) info.get(4);
            mother = (String) info.get(5);
            sdtme = (String) info.get(6);
            homephone = (String) info.get(7);
            thudt = (String) info.get(8);
            hinhThucHoc = (String) info.get(9);
            lop = (String) info.get(10);
            trungTam = (String) info.get(11);
            String thoihoc = (String) info.get(12);
            ngDaiDien = (String) info.get(13);
            nhaphoc = new Get().getNgayNhapHoc(id);
            //kiem tra co phai admin khong
            boolean isAdmin = new DataTable().CheckAdmin();
            if(!isAdmin){
                thoiHoc.setEnabled(false);
                Button_HS_CapNhat.setEnabled(false);
                Button_HocSinh_ThanhToan.setEnabled(false);
                Button_HocSinh_ThemPhi.setEnabled(false);
            }
            if(thoihoc.equals("0")){
                thoiHoc.setText("Đã Thôi Học");
                thoiHoc.setEnabled(false);
            }
        edit_ten.setText(ten);
        cha.setText(father);
        NgaySinh.setText(ngaySinh);
        dtcha.setText(sdtbo);
        me.setText(mother);
        dtme.setText(sdtme);
        dtnha.setText(homephone);
        email.setText(thudt);
        HinhThucHoc.setText(hinhThucHoc);
        Lop.setText(lop);
        TrungTam.setText(trungTam);
        NguoiDaiDien.setText(ngDaiDien);
        //nhapHoc.setText(nhaphoc);
        String[] NTN = nhaphoc.split("-");
        try{
            NHDay.setSelectedIndex(Integer.parseInt(NTN[0])-1);
            NHMonth.setSelectedIndex(Integer.parseInt(NTN[1])-1);
            NHYear.setSelectedIndex(Integer.parseInt(NTN[2])-2010);
        }catch(Exception e){
            
        }
        int noPhi = new AStudentAndLateDay().GetDebt(id);
        debt.setText(XuLy.setMoney(String.valueOf(noPhi)));
        idTrungTam = new Get().GetIDFac();
        totalTime = new AStudentAndLateDay().LateDay(id, idTrungTam);//1 la ma Trung tam
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
        model = (DefaultTableModel) DanhSachPhi.getModel();
        int Total = 0;
        for(int i=0;i<model.getRowCount();i++){
            String tenPhi = model.getValueAt(i, 1).toString().toLowerCase();
            if(tenPhi.indexOf("hoàn")== -1)
                Total += Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 4).toString()));
            else
                Total -= Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 4).toString()));
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
        cha = new javax.swing.JTextField();
        edit_ten = new javax.swing.JTextField();
        dtme = new javax.swing.JTextField();
        HinhThucHoc = new javax.swing.JTextField();
        Lop = new javax.swing.JTextField();
        TrungTam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DanhSachPhi = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        TimeTrongMuon = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dtcha = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        me = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        dtnha = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Button_HS_LichSuDongTien = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        debt = new javax.swing.JTextField();
        Button_HocSinh_ThanhToan = new javax.swing.JButton();
        tongSoPhi = new javax.swing.JTextField();
        Button_HocSinh_ThemPhi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        NguoiDaiDien = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Button_HS_CapNhat = new javax.swing.JButton();
        thoiHoc = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        NHDay = new javax.swing.JComboBox();
        NHYear = new javax.swing.JComboBox();
        NHMonth = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        Panel_hocsinhA.setFocusCycleRoot(true);
        Panel_hocsinhA.setRequestFocusEnabled(false);
        Panel_hocsinhA.setVerifyInputWhenFocusTarget(false);
        Panel_hocsinhA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Panel_hocsinhAMouseEntered(evt);
            }
        });

        NgaySinh.setText("2013-03-08");

        edit_ten.setText("Nguyễn Hải Anh\t");

        HinhThucHoc.setEditable(false);
        HinhThucHoc.setText("Chinh quy\t");

        Lop.setEditable(false);
        Lop.setText("SUNSHINE_1");

        TrungTam.setEditable(false);

        jLabel1.setText("Họ tên");

        jLabel2.setText("Ngày sinh");

        jLabel3.setText("Họ tên cha");

        jLabel4.setText("Số ĐT mẹ");

        jLabel5.setText("Hình thức học");

        jLabel6.setText("Lớp");

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

        jLabel16.setText("Thời gian trông muộn");

        TimeTrongMuon.setEditable(false);

        jButton10.setText("Chi Tiết");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/xoa.jpg"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setText("Phút");

        jLabel14.setText("Số ĐT cha");

        jLabel15.setText("Họ tên mẹ");

        jLabel17.setText("ĐT nhà");

        jLabel18.setText("Email");

        Button_HS_LichSuDongTien.setText("Lịch sử đóng tiền");
        Button_HS_LichSuDongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HS_LichSuDongTienActionPerformed(evt);
            }
        });

        jLabel10.setText("Tổng số phí");

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

        tongSoPhi.setEditable(false);

        Button_HocSinh_ThemPhi.setText("Thêm Phí");
        Button_HocSinh_ThemPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HocSinh_ThemPhiActionPerformed(evt);
            }
        });

        jButton1.setText("Thông báo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tongSoPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Button_HocSinh_ThemPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(235, 235, 235)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Button_HocSinh_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Button_HS_LichSuDongTien)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_HocSinh_ThemPhi)
                    .addComponent(Button_HS_LichSuDongTien))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_HocSinh_ThanhToan)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tongSoPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(debt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/refresh25.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel19.setText("Đại Diện");

        jButton2.setText("Nghỉ Phép");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Button_HS_CapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(thoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_HS_CapNhat)
                    .addComponent(thoiHoc)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jLabel20.setText("Ngày nhập học");

        NHDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        NHYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040" }));

        NHMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel21.setText("  /  ");

        jLabel22.setText("  /  ");

        javax.swing.GroupLayout Panel_hocsinhALayout = new javax.swing.GroupLayout(Panel_hocsinhA);
        Panel_hocsinhA.setLayout(Panel_hocsinhALayout);
        Panel_hocsinhALayout.setHorizontalGroup(
            Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(edit_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(cha, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(dtcha, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(me, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(dtme, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(dtnha, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(NguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TrungTam)
                                    .addComponent(Lop)
                                    .addComponent(HinhThucHoc)
                                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                                .addComponent(NHDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(NHMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(TimeTrongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12)
                                                .addGap(20, 20, 20)
                                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(jLabel22)
                                                .addGap(18, 18, 18)
                                                .addComponent(NHYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                        .addGap(14, 14, 14)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_hocsinhALayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)
                                .addComponent(jLabel11))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))))
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
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dtcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(15, 15, 15)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dtme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtnha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(13, 13, 13)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_hocsinhALayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HinhThucHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TimeTrongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(Panel_hocsinhALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(NHDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NHYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NHMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Panel_hocsinhA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_hocsinhA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Panel_hocsinhAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_hocsinhAMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Panel_hocsinhAMouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        chonThongBao chonTb = new chonThongBao(id, idTrungTam);
        chonTb.setLocation(170, 145);
        chonTb.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                String nameCost = v.get(1).toString();
                //kiem tra xem co trung ten voi phi da co
                for(int j=0;j<count;j++){
                    try{
                        String temp = model.getValueAt(j, 1).toString();
                    temp = temp.toLowerCase();
                    boolean checkTrongMuon = false;
                    boolean checkDatCoc= false;
                    boolean checkXeBus= false;
                    boolean checkHocHe= false;
                    if(((temp.indexOf("trong")!= -1)&&(temp.indexOf("muon")!= -1))||((temp.indexOf("trông")!= -1)&&(temp.indexOf("muộn")!= -1))){
                        checkTrongMuon = true;
                    }
                    if(((temp.indexOf("dat")!= -1)&&(temp.indexOf("coc")!= -1))||((temp.indexOf("đặt")!= -1)&&(temp.indexOf("cọc")!= -1))){
                        checkDatCoc = true;
                    }
                    if((temp.indexOf("he")!=-1)||(temp.indexOf("hè")!=-1)){
                        checkHocHe = true;
                    }
                    String nameCost1 = nameCost.toLowerCase();
                    if(temp.equals(nameCost1))
                        check = false;
                    if(check){
                        if(checkTrongMuon){
                            if(((nameCost1.indexOf("trong")!= -1)&&(nameCost1.indexOf("muon")!= -1))||((nameCost1.indexOf("trông")!= -1)&&(nameCost1.indexOf("muộn")!= -1))){
                                check = false;
                            }
                        }
                        if(checkHocHe)
                            if((nameCost1.indexOf("he")!=-1)||(nameCost1.indexOf("hè")!=-1)){
                                check = false;
                    }
                        if(checkDatCoc)
                            if(((nameCost1.indexOf("dat")!= -1)&&(nameCost1.indexOf("coc")!= -1))||((nameCost1.indexOf("đặt")!= -1)&&(nameCost1.indexOf("cọc")!= -1))){
                                check = false;
                    }
                    }
                    }
                    catch(java.lang.ArrayIndexOutOfBoundsException e){
                    }
                }
                    AddRowOfTable addrow = new AddRowOfTable();
                    addrow.addRowOfTable(model, v);//them vector v vao bang
                    
                    //neu la phi trong muon
                    //kiem tra xem co phai phi trong muon khong
                    int num = model.getRowCount();
                    String ten = model.getValueAt(num - 1, 1).toString();
                    boolean check2 = false;
                    boolean kiHe = false;
                    boolean hoanHocPhi = false;
                    ten = ten.toLowerCase();
                    System.out.println(ten);
                    if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    check2 = true;
                }
                 if(((ten.indexOf("hoc")!= -1)&&(ten.indexOf("he")!= -1))||((ten.indexOf("học")!= -1)&&(ten.indexOf("hè")!= -1))){
                    kiHe = true;
                }
                 if(ten.indexOf("hoan hoc phi")!= -1||ten.indexOf("hoàn học phí")!= -1){
                    hoanHocPhi = true;
                }
                    
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
                        String nam = model.getValueAt(num -1, 3).toString().substring(0, 4);
                        if(check2){
                        //update trong muon o day
                        new AStudentAndLateDay().UpdateTrongMuon(id,ki,nam);
                            System.out.println("được thực hiện mà");
                        totalTime = new AStudentAndLateDay().LateDay(id, idTrungTam);//1 la ma Trung tam
                        TimeTrongMuon.setText(String.valueOf(totalTime));
                    }
                    else{

                    }
                        if(kiHe){
                            new AStudentAndLateDay().UpdateKiHe(id,ki,nam);
                        }
                         if(hoanHocPhi){
                            new AStudentAndLateDay().UpdateHoanHocPhi(id,ki,nam);
                        }
                if(check){//neu ko bi trung voi row nao thi them vao bang
                    new CostOfStudent().InsertDSPhiCuaHS(id,idCost, idTrungTam);
                    new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
                    model = (DefaultTableModel) DanhSachPhi.getModel();
                    count++;
                }
                else{
                    int click = JOptionPane.showConfirmDialog(null,v.get(1)+" "+v.get(2)+" Năm "+v.get(3)+" đã tồn tại phí tương tự, Bạn có chắc muốn thêm?", "",JOptionPane.OK_CANCEL_OPTION);
                    if(click == JOptionPane.YES_OPTION){
                        new CostOfStudent().InsertDSPhiCuaHS(id,idCost, idTrungTam);
                        new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
                        model = (DefaultTableModel) DanhSachPhi.getModel();
                        count++;
                    }
                }
            }
            Total();
        }
        new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
        model = (DefaultTableModel) DanhSachPhi.getModel();
        Total();
    }//GEN-LAST:event_Button_HocSinh_ThemPhiActionPerformed

    private void Button_HocSinh_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HocSinh_ThanhToanActionPerformed
        HoaDon.tenHS = edit_ten.getText();
        HoaDon.tenNguoiDaiDien = cha.getText();
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
            boolean checkPhiGiuCho = false;
            boolean checkHoanPhiGiuCho = false;
            int k = model.getRowCount();
            for(int i = 0;i<k;i++){
                int idCost = Integer.parseInt(model.getValueAt(0,0).toString());
                String nameCost = model.getValueAt(0, 1).toString().toLowerCase();
                //----------chuan hoa nameCost------
                //loai bo dau cach o dau string
                //loai bo dau cach o cuoi string
                nameCost = nameCost.replaceAll(" ","");
                //----------------------------------
                if(nameCost.equals("phígiữchỗ")) checkPhiGiuCho = true;
                if(nameCost.equals("hoànphígiữchỗ")) checkHoanPhiGiuCho = true;
                System.out.println(checkHoanPhiGiuCho+"--"+checkPhiGiuCho);
                new CostOfStudent().UpdatePhiCuaHs(id, idCost, idTrungTam);
                model.removeRow(0);
            }
            if(checkPhiGiuCho){//neu la co dong phi giu cho
                    int idCost = new RecieptManagerment().GetIdPhiDatCoc(id);
                    new AStudentAndLateDay().setDangGiuDatCoc(String.valueOf(id), idTrungTam);
            }
            if(checkHoanPhiGiuCho){//neu co phi hoan giu cho
                int idCost = new RecieptManagerment().GetIdPhiDatCoc(id);
                new AStudentAndLateDay().setDatCoc(id, idTrungTam);
            }
            Total();
        }
        new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
        model = (DefaultTableModel) DanhSachPhi.getModel();
        int noPhi = new AStudentAndLateDay().GetDebt(id);
        debt.setText(XuLy.setMoney(String.valueOf(noPhi)));
    }//GEN-LAST:event_Button_HocSinh_ThanhToanActionPerformed

    private void debtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debtActionPerformed

    private void Button_HS_LichSuDongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_LichSuDongTienActionPerformed
        //lichSuDongTien ls = new lichSuDongTien(this, rootPaneCheckingEnabled);
        LichSuDongTien2.idStudent = id;
        LichSuDongTien2.idFac = idTrungTam;
        LichSuDongTien2 lic= new LichSuDongTien2(null,true);
        lic.setLocation(380, 130);
        lic.id = id;
        lic.show();
    }//GEN-LAST:event_Button_HS_LichSuDongTienActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ChiTietNghiPhep.idStudent = id;
        ChiTietNghiPhep.idTrungTam = idTrungTam;
        ChiTietNghiPhep chitiet = new ChiTietNghiPhep(null,true);
        chitiet.setLocation(500,130);
        chitiet.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            boolean kiHe = false;
            boolean hoanHocPhi = false;
            ten = ten.toLowerCase();
            if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                check2 = true;
            }
                 if(((ten.indexOf("hoc")!= -1)&&(ten.indexOf("he")!= -1))||((ten.indexOf("học")!= -1)&&(ten.indexOf("hè")!= -1))){
                    kiHe = true;
                }
                 if(ten.indexOf("hoan hoc phi")!= -1||ten.indexOf("hoàn học phí")!= -1){
                    hoanHocPhi = true;
                }
            
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
                String nam = model.getValueAt(num, 3).toString().substring(0, 4);
                if(check2){
                TimeTrongMuon.setText("0");
                new AStudentAndLateDay().XoaTrongMuon(id,ki,nam);
            }
                if(kiHe){
                    new AStudentAndLateDay().XoaPhiHocHe(id,ki,nam);
                }
                if(hoanHocPhi){
                    new AStudentAndLateDay().XoaHoanHocPhi(id,ki,nam);
                }
            new CostOfStudent().DeleteDSPhiCuaHs(id, idCost, idTrungTam);
            EditTable edit = new EditTable();
            edit.removeRowOfTable(DanhSachPhi);
            Total();
            totalTime = new AStudentAndLateDay().LateDay(id, idTrungTam);
            TimeTrongMuon.setText(String.valueOf(totalTime));
        }
    }//GEN-LAST:event_jLabel11MouseClicked

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
            new CostOfStudent().BangHocPhiCuaHocSinh(id,idTrungTam,DanhSachPhi);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void thoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoiHocActionPerformed

        int click = JOptionPane.showConfirmDialog(null, "Bạn Chắc Muốn Cho Học Sinh Này Thôi Học?", "",JOptionPane.OK_CANCEL_OPTION);
        if(click == JOptionPane.YES_OPTION){
//            new AStudentAndLateDay().ThoiHoc(id, idTrungTam);
//            JOptionPane.showMessageDialog(Panel_hocsinhA, "Học Sinh Này Đã Bị Thôi Học");
            NgayHoc ngayhoc = new NgayHoc(null, true, false);
                ngayhoc.setVisible(true);
                if (ngayhoc.getFlags()) {
                    new DataBase.SQLLopX().xoaHocSinh(id, ngayhoc.getDate());
                    thoiHoc.setText("Đã Thôi Học");
                    thoiHoc.setEnabled(false);
                }
        }
    }//GEN-LAST:event_thoiHocActionPerformed

    private void Button_HS_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HS_CapNhatActionPerformed
        new AStudentAndLateDay().UpdataInfoStudent(id,idTrungTam,edit_ten.getText(),new XuLiXau().NamThangNgay(NgaySinh.getText()),cha.getText(),
            dtcha.getText(),me.getText(),dtme.getText(),dtnha.getText(),email.getText(),NguoiDaiDien.getText());
        JOptionPane.showMessageDialog(Panel_hocsinhA, "Thông Tin Học Sinh Đã Được Cập Nhật");
        String ngay = NHYear.getSelectedItem().toString() + "-"+NHMonth.getSelectedItem().toString()+"-"+NHDay.getSelectedItem().toString();
        new AStudentAndLateDay().UpdateNgayNhapHoc(id, idTrungTam, ngay);
    }//GEN-LAST:event_Button_HS_CapNhatActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
         AStudentAndLateDay a = new AStudentAndLateDay();
        ArrayList info;
        info = new AStudentAndLateDay().HocSinhA1(id);
        ten = (String) info.get(1);
        ngaySinh = (String) info.get(2);
        father = (String) info.get(3);
        sdtbo = (String) info.get(4);
        mother = (String) info.get(5);
        sdtme = (String) info.get(6);
        homephone = (String) info.get(7);
        thudt = (String) info.get(8);
        hinhThucHoc = (String) info.get(9);
        lop = (String) info.get(10);
        trungTam = (String) info.get(11);
        ngDaiDien = (String) info.get(13);
        String thoihoc = (String) info.get(12);
        nhaphoc = new Get().getNgayNhapHoc(id);
        edit_ten.setText(ten);
        cha.setText(father);
        NgaySinh.setText(ngaySinh);
        dtcha.setText(sdtbo);
        me.setText(mother);
        dtme.setText(sdtme);
        dtnha.setText(homephone);
        email.setText(thudt);
        HinhThucHoc.setText(hinhThucHoc);
        Lop.setText(lop);
        TrungTam.setText(trungTam);
        NguoiDaiDien.setText(ngDaiDien);
        int noPhi = new AStudentAndLateDay().GetDebt(id);
        debt.setText(XuLy.setMoney(String.valueOf(noPhi)));
        idTrungTam = new Get().GetIDFac();
         //nhapHoc.setText(nhaphoc);
        String[] NTN = nhaphoc.split("-");
        NHDay.setSelectedIndex(Integer.parseInt(NTN[0])-1);
        NHMonth.setSelectedIndex(Integer.parseInt(NTN[1])-1);
        NHYear.setSelectedIndex(Integer.parseInt(NTN[2])-2010);
        totalTime = new AStudentAndLateDay().LateDay(id, idTrungTam);//1 la ma Trung tam
        TimeTrongMuon.setText(String.valueOf(totalTime));
        new CostOfStudent().BangHocPhiCuaHocSinh(id, idTrungTam, DanhSachPhi);
        Total();
    }//GEN-LAST:event_jLabel7MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_HS_CapNhat;
    private javax.swing.JButton Button_HS_LichSuDongTien;
    private javax.swing.JButton Button_HocSinh_ThanhToan;
    private javax.swing.JButton Button_HocSinh_ThemPhi;
    private javax.swing.JTable DanhSachPhi;
    private javax.swing.JTextField HinhThucHoc;
    private javax.swing.JTextField Lop;
    private javax.swing.JComboBox NHDay;
    private javax.swing.JComboBox NHMonth;
    private javax.swing.JComboBox NHYear;
    private javax.swing.JTextField NgaySinh;
    private javax.swing.JTextField NguoiDaiDien;
    private javax.swing.JPanel Panel_hocsinhA;
    private javax.swing.JTextField TimeTrongMuon;
    private javax.swing.JTextField TrungTam;
    private javax.swing.JTextField cha;
    private javax.swing.JTextField debt;
    private javax.swing.JTextField dtcha;
    private javax.swing.JTextField dtme;
    private javax.swing.JTextField dtnha;
    private javax.swing.JTextField edit_ten;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField me;
    private javax.swing.JButton thoiHoc;
    private javax.swing.JTextField tongSoPhi;
    // End of variables declaration//GEN-END:variables
}
