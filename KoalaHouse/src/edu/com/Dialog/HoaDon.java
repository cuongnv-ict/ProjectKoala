/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Dialog;

import DataBase.HocSinh.AStudentAndLateDay;
import DataBase.HocSinh.Convert;
import DataBase.HocSinh.HistoryManagerment;
import DataBase.HocSinh.RecieptManagerment;
import edu.com.Panel.HocSinhA;
import edu.com.XuLy;
import edu.com.upbang.AddRowOfTable;
import edu.com.upbang.EditTable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pham The Quyen
 */
public class HoaDon extends javax.swing.JDialog implements Printable{
    public static int idhs;
    public static String tenHS;
    public static String tenNguoiDaiDien;
    public static String lop;
    public static int idTT;
    public static String trungTam;
    private DefaultTableModel model;
    private boolean button = false;
    public String lido = "Không Có";
    public int idTrungTam;
    public int sott;
    public int idHocSinh = 0;
    Convert convert = new Convert();
    
    /**
     * Creates new form NewJDialog
     */
    public HoaDon(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        idHS.setText(String.valueOf(idhs));
        TenHocSinh.setText(tenHS);
        NguoiDaiDien.setText(tenNguoiDaiDien);
        tenLop.setText(lop);
        sott = new RecieptManagerment().GetNumberReceipt();
        stt.setText(String.valueOf(sott));
        tenTrungTam.setText(trungTam);
        idTrungTam = HoaDon.idTT;
        idHocSinh = HoaDon.idhs;
        //lay ngay thang nam
            Date aDate = new Date(System.currentTimeMillis());
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(aDate);
            ngay.setSelectedIndex(calendar.get(Calendar.DATE) -1);
            thang.setSelectedIndex(calendar.get(Calendar.MONTH));
            nam.setSelectedIndex(calendar.get(Calendar.YEAR)-2012);
            
        switch(idTrungTam){
            case 1: {DiaChi.setText("340 Bà triệu, Quận Hai Bà Trưng");
                    DienThoaiTrungTam.setText("+(84.4) 3772.3060");
                    break;
            }
            case 2: {DiaChi.setText("261 Hoàng Ngân, Quận Cầu Giấy");
                    DienThoaiTrungTam.setText("+(84.4) 3772.3060");
                    break;
            }
            case 3: {DiaChi.setText("5 Phan Kế Bính, Quận Ba Đình");
                    DienThoaiTrungTam.setText("+(84.4) 3974.7617");
                    break;
            }
            case 4: {DiaChi.setText("3 Nguyễn Huy Tự, Quận Hai Bà Trưng");
                    DienThoaiTrungTam.setText("+ (84.4) 3972.8913");
                    break;
            }
                
        }
        //tinh tong tien 
        if(idHocSinh>0){
        new RecieptManagerment().BangDSPhiHoaDon(idhs,idTrungTam, jTable1);
        model = (DefaultTableModel) jTable1.getModel();
        Total();
        daThu.setText(TongTien.getText());
        }
        else{
            
        }
        //xoa du lieu static
        HoaDon.tenHS = "";
        HoaDon.tenNguoiDaiDien = "";
        HoaDon.trungTam = "";
        HoaDon.idhs = 0;
        HoaDon.lop = "";
        HoaDon.idTT = 0;
        if(idHocSinh>0){
        int x = Integer.parseInt(XuLy.getMoney(TongTien.getText()));
        if(x <0){
            jLabel23.setText("Số Tiền Hoàn Trả :");
            x = x* (-1);
            TongTien.setText(XuLy.setMoney(String.valueOf(x)));
            checkDaThu.setText("Hoàn Trả :");
            daThu.setText(XuLy.setMoney(String.valueOf(x)));
        }
        }
    }
    public boolean getButton()//lay xem la create hay cancle
    {
        return button;
    }
    public void Total(){
        if(idHocSinh >0){
        long Total = 0;
        try{
        for(int i=0;i<model.getRowCount();i++){
            if(hienChietKhau.isSelected()){
                long t = Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                int ck = 0;
                if(model.getValueAt(i, 4).toString().length()>0){
                    ck = Integer.parseInt(model.getValueAt(i, 4).toString());
                }
                long ti = (t*(100-ck))/100;
                Total += ti;
            }
            else{
            if(model.getValueAt(i, 3).toString().length()>0){
                    Total += Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
            }
            }
        }
        int phidatcoc = new RecieptManagerment().PhiDatCoc(idHocSinh);
        Total = Total + 2*phidatcoc;
        TongTien.setText(XuLy.setMoney(String.valueOf(Total)));
        daThu.setText(XuLy.setMoney(String.valueOf(Total)));
        int x = Integer.parseInt(XuLy.getMoney(TongTien.getText()));
        if(x <0){
            jLabel23.setText("Số Tiền Hoàn Trả :");
            x = x* (-1);
            TongTien.setText(XuLy.setMoney(String.valueOf(x)));
            checkDaThu.setText("Hoàn Trả :");
            daThu.setText(XuLy.setMoney(String.valueOf(x)));
        }
        String tienbc = convert.chuyentien(TongTien.getText());
        stbc.setText("Số tiền bằng chữ: "+tienbc);
        }
        catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Gặp Vấn Đề Khi Tính Tổng Số Tiền");
        }
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

        avatar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ThongTinCoSo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        DiaChi = new javax.swing.JTextField();
        DienThoaiTrungTam = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        NgayThangNam = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ngay = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        thang = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        nam = new javax.swing.JComboBox();
        ThongTinHoaDon = new javax.swing.JPanel();
        stt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ThongTinHocSinh = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        TenHocSinh = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        NguoiDaiDien = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        HinhThucDong = new javax.swing.JComboBox();
        idHS = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tenLop = new javax.swing.JTextField();
        tenTrungTam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        daThu = new javax.swing.JTextField();
        TongTien = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nguoiNop = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nguoiThu = new javax.swing.JTextField();
        checkDaThu = new javax.swing.JCheckBox();
        stbc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        hienChietKhau = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/ava.png"))); // NOI18N

        javax.swing.GroupLayout avatarLayout = new javax.swing.GroupLayout(avatar);
        avatar.setLayout(avatarLayout);
        avatarLayout.setHorizontalGroup(
            avatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avatarLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        avatarLayout.setVerticalGroup(
            avatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avatarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("KOALA HOUSE KINDERGARTEN");

        DiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiaChiActionPerformed(evt);
            }
        });

        DienThoaiTrungTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DienThoaiTrungTamActionPerformed(evt);
            }
        });

        jLabel3.setText("Điện Thoại");

        jLabel1.setText("Địa Chỉ");

        javax.swing.GroupLayout ThongTinCoSoLayout = new javax.swing.GroupLayout(ThongTinCoSo);
        ThongTinCoSo.setLayout(ThongTinCoSoLayout);
        ThongTinCoSoLayout.setHorizontalGroup(
            ThongTinCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinCoSoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ThongTinCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(ThongTinCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DienThoaiTrungTam)
                    .addComponent(DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinCoSoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );
        ThongTinCoSoLayout.setVerticalGroup(
            ThongTinCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinCoSoLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(ThongTinCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DienThoaiTrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Ngày");

        ngay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayActionPerformed(evt);
            }
        });

        jLabel7.setText("Tháng");

        thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel8.setText(" Năm");

        nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NgayThangNamLayout = new javax.swing.GroupLayout(NgayThangNam);
        NgayThangNam.setLayout(NgayThangNamLayout);
        NgayThangNamLayout.setHorizontalGroup(
            NgayThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NgayThangNamLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        NgayThangNamLayout.setVerticalGroup(
            NgayThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NgayThangNamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NgayThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        stt.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Số:");

        javax.swing.GroupLayout ThongTinHoaDonLayout = new javax.swing.GroupLayout(ThongTinHoaDon);
        ThongTinHoaDon.setLayout(ThongTinHoaDonLayout);
        ThongTinHoaDonLayout.setHorizontalGroup(
            ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stt, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ThongTinHoaDonLayout.setVerticalGroup(
            ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(37, 37, 37))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Họ tên hs:");

        TenHocSinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TenHocSinh.setText("Nguyễn Hải Anh");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Lớp:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Người TT:");

        NguoiDaiDien.setText("Nguyễn Ngọc Lan");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Hình Thức TT:");

        HinhThucDong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Chuyển khoản" }));

        idHS.setText("123");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Mã:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Cơ Sở:");

        javax.swing.GroupLayout ThongTinHocSinhLayout = new javax.swing.GroupLayout(ThongTinHocSinh);
        ThongTinHocSinh.setLayout(ThongTinHocSinhLayout);
        ThongTinHocSinhLayout.setHorizontalGroup(
            ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinHocSinhLayout.createSequentialGroup()
                .addGroup(ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ThongTinHocSinhLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(2, 2, 2)
                        .addComponent(TenHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idHS))
                    .addGroup(ThongTinHocSinhLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HinhThucDong, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenTrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        ThongTinHocSinhLayout.setVerticalGroup(
            ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinHocSinhLayout.createSequentialGroup()
                .addGroup(ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TenHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(tenTrungTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThongTinHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(HinhThucDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(NguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(tenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("HÓA ĐƠN (Receipt)");

        jLabel14.setText("NGƯỜI NỘP");

        daThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daThuActionPerformed(evt);
            }
        });

        jLabel23.setText("Tổng Tiền :");

        jLabel9.setText("NGƯỜI THU TIỀN");

        checkDaThu.setSelected(true);
        checkDaThu.setText("Đã Thu :");
        checkDaThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDaThuActionPerformed(evt);
            }
        });

        stbc.setText("Số Tiền Bằng Chữ: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(stbc, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(nguoiNop, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(checkDaThu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(daThu, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(nguoiThu, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkDaThu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stbc)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel9))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nguoiNop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nguoiThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Phí", "Kì Học", "Năm Học", "Giá"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("In Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton1)
                .addContainerGap())
        );

        hienChietKhau.setText("Chiết khấu");
        hienChietKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hienChietKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ThongTinCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NgayThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThongTinHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271))))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(hienChietKhau)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ThongTinCoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NgayThangNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ThongTinHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hienChietKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiaChiActionPerformed

    private void DienThoaiTrungTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DienThoaiTrungTamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DienThoaiTrungTamActionPerformed

    private void ngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngayActionPerformed

    private void namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        int a = Integer.parseInt(XuLy.getMoney(daThu.getText()));
        if((a > 0)&& nguoiThu.getText().length()>0 && nguoiNop.getText().length() >0)
        {
            //tinh no
        int can = Integer.parseInt(XuLy.getMoney(TongTien.getText()));
        int dong = Integer.parseInt(XuLy.getMoney(daThu.getText()));
        int debt = can - dong;
        if(debt<0){
            JOptionPane.showMessageDialog(rootPane, "Số Tiền Đóng Đã Nhiều Hơn Số Tiền Cần Thu");
            debt = 0;
        }
        //print
        PrinterJob pj = PrinterJob.getPrinterJob();
        
        //PageFormat pf = pj.defaultPage();
        //pf.setOrientation(PageFormat.LANDSCAPE);
        //pf.setPaper(Pa);
        //PageFormat pf = pj.pageDialog(pj.defaultPage());
        
        pj.setJobName("Print Details");
        //jButton1.setVisible(false);
        pj.setPrintable(this);
        PrintRequestAttributeSet printRequest = new HashPrintRequestAttributeSet();
        printRequest.add(MediaSizeName.ISO_A4);
        printRequest.add(OrientationRequested.LANDSCAPE);
        //printRequest.add(M)
        
        printRequest.add(new MediaPrintableArea((float)0.0, (float)0.0, 350, 500, MediaPrintableArea.MM));
        
        boolean toPrint = pj.printDialog(printRequest);
        
        if(toPrint) {
            try {
                pj.print(printRequest);
               // jButton1.setVisible(true);
            } catch (PrinterException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
               // jButton1.setVisible(true);
            }
        }
        
        if(toPrint){
        
        if(debt>=0){
            new AStudentAndLateDay().InsertDebt(idHocSinh, idTrungTam, debt);
        }
        button = true;
        //insert to database
        int idStudent = Integer.parseInt(idHS.getText());
        String idFac;
        idFac = String.valueOf(idTrungTam);
        String nguoidong = nguoiNop.getText();
        String nguoithu = nguoiThu.getText();
        String sotien = XuLy.getMoney(daThu.getText());
        String date = nam.getSelectedItem().toString()+"-"+thang.getSelectedItem().toString()+"-"+ngay.getSelectedItem().toString();
        int hinhthucdong = HinhThucDong.getSelectedIndex();
        String phantram = "0";
        new HistoryManagerment().InsertLSHoaDon(idStudent, idFac, nguoidong, nguoithu, sotien, date, hinhthucdong, phantram, lido);
        dispose();
        }
        jButton1.setVisible(true);
        }
        else if(nguoiThu.getText().length() ==0 || nguoiNop.getText().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "Hãy Nhập Đầy Đủ Người Thu Và Người Nộp");
        }
        else{
           JOptionPane.showMessageDialog(rootPane, "Hãy Nhập Lại Số Tiền Đã Thu"); 
        }
        }
        catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Nhập Sai Số Tiền");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Total();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void daThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daThuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daThuActionPerformed

    private void checkDaThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDaThuActionPerformed
        if(checkDaThu.isSelected()){
            daThu.setEnabled(true);
        }
        else daThu.setEnabled(false);
    }//GEN-LAST:event_checkDaThuActionPerformed

    private void hienChietKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hienChietKhauActionPerformed
        //co cho xuat hien chiet khau hay khong
        if(hienChietKhau.isSelected()){
            Object[] coldata = new Object[model.getRowCount()];
            for(int j=0;j<coldata.length;j++)
                coldata[j] = 0;
            model.addColumn("Chiết Khấu(%)",coldata);
        }
        else{
          TableColumn tcol = jTable1.getColumnModel().getColumn(4);
          jTable1.getColumnModel().removeColumn(tcol);
          model.setColumnCount(4);
        }
    }//GEN-LAST:event_hienChietKhauActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Total();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDon dialog = new HoaDon(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DiaChi;
    private javax.swing.JTextField DienThoaiTrungTam;
    private javax.swing.JComboBox HinhThucDong;
    private javax.swing.JPanel NgayThangNam;
    private javax.swing.JTextField NguoiDaiDien;
    private javax.swing.JTextField TenHocSinh;
    private javax.swing.JPanel ThongTinCoSo;
    private javax.swing.JPanel ThongTinHoaDon;
    private javax.swing.JPanel ThongTinHocSinh;
    private javax.swing.JTextField TongTien;
    private javax.swing.JPanel avatar;
    private javax.swing.JCheckBox checkDaThu;
    private javax.swing.JTextField daThu;
    private javax.swing.JCheckBox hienChietKhau;
    private javax.swing.JTextField idHS;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox nam;
    private javax.swing.JComboBox ngay;
    private javax.swing.JTextField nguoiNop;
    private javax.swing.JTextField nguoiThu;
    private javax.swing.JLabel stbc;
    private javax.swing.JTextField stt;
    private javax.swing.JTextField tenLop;
    private javax.swing.JTextField tenTrungTam;
    private javax.swing.JComboBox thang;
    // End of variables declaration//GEN-END:variables
public int print(Graphics grphcs, PageFormat pf, int page) throws PrinterException {
        getComponentsOfReceived();
        //setCoorOfNguoiThuNop();
        if(page > 0) {
            return Printable.NO_SUCH_PAGE;
        }
        
        Font font = new Font("Serif", Font.PLAIN, 12);
        Graphics2D g2d = (Graphics2D) grphcs;
        //pf.setOrientation(PageFormat.LANDSCAPE);
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        grphcs.setFont(font);
        
        grphcs.drawString(lbTitle, 150, 30);
        // ve image
        grphcs.drawImage(imageOfLabel, 10, 20, imageOfLabel.getWidth(this), imageOfLabel.getHeight(this), null);
        
        grphcs.drawString(lbDiaChi, 110, 50);
        grphcs.drawString(lbDienThoai, 110, 70);
        grphcs.drawString(tfDiaChi, 180, 50);
        grphcs.drawString(tfDienThoai, 180, 70);
        font = new Font("Serif", Font.BOLD, 14);
        grphcs.setFont(font);
        grphcs.drawString(lbHoaDon, 150, 110);
        font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
        
        grphcs.drawString(lbNgay, 20, 140);
        grphcs.drawString(cbNgay, 50, 140);
        grphcs.drawString(lbThang, 65, 140);
        grphcs.drawString(cbThang, 100, 140);
        grphcs.drawString(lbNam, 105, 140);
        grphcs.drawString(cbNam, 135, 140);
        grphcs.drawString(lbSo, 290, 140);
        grphcs.drawString(tfSo, 305, 140);
        grphcs.drawString(lbHoTen, 20, 160);
        grphcs.drawString(tfHoTen, 75, 160);
        grphcs.drawString(lbMa, 180, 160);
        grphcs.drawString(tfMa, 200, 160);
        grphcs.drawString(lbCoso, 290, 160);
        grphcs.drawString(tfCoso, 325, 160);
        grphcs.drawString(lbNguoiThanhToan, 20, 180);
        grphcs.drawString(tfNguoiThanhToan, 75, 180);
        grphcs.drawString(lbLop, 180, 180);
        grphcs.drawString(tfLop, 205, 180);
        grphcs.drawString(lbHinhThucThanhToan, 290, 180);
        grphcs.drawString(tfHinhThucThanhToan, 365, 180);
        
        String tableDataToString = "";
        grphcs.drawString("Tên Phí ", 30, 210);
        grphcs.drawString("Kỳ học ", 135, 210);
        grphcs.drawString("Năm học ", 235, 210);
        grphcs.drawString("Giá ", 335, 210);
        
        for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = (String) tableData[i][j];
                }
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 30, (210 + (i + 1) * 20));
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 135, (210 + (i + 1) * 20));
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 235, (210 + (i + 1) * 20));
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 335, (210 + (i + 1) * 20));
                }
            }
        }
        
        grphcs.drawString(lbTongTien, 30, 500);
        grphcs.drawString(tfTongTien, 90, 500);
        grphcs.drawString(lbDathu, 290, 500);
        grphcs.drawString(tfDaThu, 335, 500);
        grphcs.drawString(lbStbc, 30, 520);
        
        grphcs.drawString(lbNguoiNop, 50, 550);
        grphcs.drawString(lbNguoiThu, 280, 550);
        //grphcs.drawString(tfNguoiNop, coorOfNguoiNop, 580);
        //grphcs.drawString(tfNguoiThu, coorOfNguoiThu, 580);
        
        FontMetrics fontMetrics = grphcs.getFontMetrics(font);
        int lengthOfNguoiNop = fontMetrics.stringWidth(tfNguoiNop);
        int lengOfNguoiThu = fontMetrics.stringWidth(tfNguoiThu);
        
        lengthOfNguoiNop = lengthOfNguoiNop / 2;
        lengOfNguoiThu = lengOfNguoiThu / 2;
        
        coorOfNguoiNop = 81 - lengthOfNguoiNop;
        coorOfNguoiThu = 327 - lengOfNguoiThu;
        
        grphcs.drawString(tfNguoiNop, coorOfNguoiNop, 580);
        grphcs.drawString(tfNguoiThu, coorOfNguoiThu, 580);
        
        // print page 2.Cong them 400
        grphcs.drawString(lbTitle, 570, 30);
        // ve image
        grphcs.drawImage(imageOfLabel, 430, 20, imageOfLabel.getWidth(this), imageOfLabel.getHeight(this), null);
        
        grphcs.drawString(lbDiaChi, 530, 50);
        grphcs.drawString(lbDienThoai, 530, 70);
        grphcs.drawString(tfDiaChi, 600, 50);
        grphcs.drawString(tfDienThoai, 600, 70);
        font = new Font("Serif", Font.BOLD, 14);
        grphcs.setFont(font);
        grphcs.drawString(lbHoaDon, 570, 110);
        font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
        
        grphcs.drawString(lbNgay, 440, 140);
        grphcs.drawString(cbNgay, 470, 140);
        grphcs.drawString(lbThang, 485, 140);
        grphcs.drawString(cbThang, 520, 140);
        grphcs.drawString(lbNam, 525, 140);
        grphcs.drawString(cbNam, 555, 140);
        grphcs.drawString(lbSo, 710, 140);
        grphcs.drawString(tfSo, 725, 140);
        grphcs.drawString(lbHoTen, 440, 160);
        grphcs.drawString(tfHoTen, 495, 160);
        grphcs.drawString(lbMa, 600, 160);
        grphcs.drawString(tfMa, 620, 160);
        grphcs.drawString(lbCoso, 710, 160);
        grphcs.drawString(tfCoso, 745, 160);
        grphcs.drawString(lbNguoiThanhToan, 440, 180);
        grphcs.drawString(tfNguoiThanhToan, 495, 180);
        grphcs.drawString(lbLop, 600, 180);
        grphcs.drawString(tfLop, 625, 180);
        grphcs.drawString(lbHinhThucThanhToan, 710, 180);
        grphcs.drawString(tfHinhThucThanhToan, 785, 180);
        
        //String tableDataToString = "";
        grphcs.drawString("Tên Phí ", 450, 210);
        grphcs.drawString("Kỳ học ", 555, 210);
        grphcs.drawString("Năm học ", 655, 210);
        grphcs.drawString("Giá ", 755, 210);
        
        for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = (String) tableData[i][j];
                }
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 450, (210 + (i + 1) * 20));
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 555, (210 + (i + 1) * 20));
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 655, (210 + (i + 1) * 20));
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 755, (210 + (i + 1) * 20));
                }
            }
        }
        
        grphcs.drawString(lbTongTien, 450, 500);
        grphcs.drawString(tfTongTien, 510, 500);
        grphcs.drawString(lbDathu, 710, 500);
        grphcs.drawString(tfDaThu, 755, 500);
        grphcs.drawString(lbStbc, 450, 520);
        
        grphcs.drawString(lbNguoiNop, 470, 550);
        grphcs.drawString(lbNguoiThu, 700, 550);
        //grphcs.drawString(tfNguoiNop, coorOfNguoiNop, 580);
        //grphcs.drawString(tfNguoiThu, coorOfNguoiThu, 580);
        
        //FontMetrics fontMetrics = grphcs.getFontMetrics(font);
        //int lengthOfNguoiNop = fontMetrics.stringWidth(tfNguoiNop);
        //int lengOfNguoiThu = fontMetrics.stringWidth(tfNguoiThu);
        
        //lengthOfNguoiNop = lengthOfNguoiNop / 2;
        //lengOfNguoiThu = lengOfNguoiThu / 2;
        
        coorOfNguoiNop = 501 - lengthOfNguoiNop;
        coorOfNguoiThu = 747 - lengOfNguoiThu;
        
        grphcs.drawString(tfNguoiNop, coorOfNguoiNop, 580);
        grphcs.drawString(tfNguoiThu, coorOfNguoiThu, 580);
        
        return Printable.PAGE_EXISTS;
    }
public Image ConvertToImage(Icon icon) {
    if(icon instanceof ImageIcon) {
        return ((ImageIcon) icon).getImage();
    } else {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        return image;
    }
}

private int coorOfNguoiThu;
private int coorOfNguoiNop;
private Icon iconOfLabel;
private Image imageOfLabel;
private String lbTitle;
private String lbDiaChi;
private String lbDienThoai;
private String tfDiaChi;
private String tfDienThoai;
private String lbHoaDon;
private String lbNgay;
private String lbThang;
private String lbNam;
private String cbNgay;
private String cbThang;
private String cbNam;
private String lbSo;
private String tfSo;
private String lbHoTen;
private String lbMa;
private String lbCoso;
private String tfHoTen;
private String tfMa;
private String tfCoso;
private String lbNguoiThanhToan;
private String lbLop;
private String lbHinhThucThanhToan;
private String tfNguoiThanhToan;
private String tfLop;
private String tfHinhThucThanhToan;
private int nRow;
private int nCol;
private Object[][] tableData;
private String lbTongTien;
private String lbDathu;
private String tfTongTien;
private String tfDaThu;
private String lbNguoiNop;
private String lbNguoiThu;
private String tfNguoiNop;
private String tfNguoiThu;
private String lbStbc;

private void getTableData(JTable table) {
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    nRow = tableModel.getRowCount();
    nCol = tableModel.getColumnCount();
    
    tableData = new Object[nRow][nCol];
    for(int i = 0 ; i < nRow ; i++) {
        for(int j = 0 ; j < nCol ; j++) {
            tableData[i][j] = tableModel.getValueAt(i, j);
        }
    }
}

public void getComponentsOfReceived() {
    iconOfLabel = jLabel4.getIcon();
    imageOfLabel = ConvertToImage(iconOfLabel);
    lbTitle = jLabel2.getText();
    lbDiaChi = jLabel1.getText();
    lbDienThoai = jLabel3.getText();
    tfDiaChi = DiaChi.getText();
    tfDienThoai = DienThoaiTrungTam.getText();
    lbHoaDon = jLabel5.getText();
    lbNgay = jLabel6.getText();
    lbThang = jLabel7.getText();
    lbNam = jLabel8.getText();
    cbNgay = String.valueOf(ngay.getSelectedItem());
    cbThang = String.valueOf(thang.getSelectedItem());
    cbNam = String.valueOf(nam.getSelectedItem());
    lbSo = jLabel10.getText();
    tfSo = stt.getText();
    lbHoTen = jLabel11.getText();
    lbMa = jLabel12.getText();
    lbCoso = jLabel13.getText();
    tfHoTen = TenHocSinh.getText();
    tfMa = idHS.getText();
    tfCoso = tenTrungTam.getText();
    lbNguoiThanhToan = jLabel17.getText();
    lbLop = jLabel18.getText();
    lbHinhThucThanhToan = jLabel19.getText();
    tfNguoiThanhToan = NguoiDaiDien.getText();
    tfLop = tenLop.getText();
    tfHinhThucThanhToan = String.valueOf(HinhThucDong.getSelectedItem());
    getTableData(jTable1);
    lbTongTien = jLabel23.getText();
    tfTongTien = TongTien.getText();
    
    if(checkDaThu.isSelected()) {
        lbDathu = checkDaThu.getText();
        tfDaThu = daThu.getText();
    }
    
    lbNguoiNop = jLabel14.getText();
    lbNguoiThu = jLabel9.getText();
    tfNguoiNop = nguoiNop.getText();
    tfNguoiThu = nguoiThu.getText();
    //lbStbc = stbc.getText();
    lbStbc = stbc.getText();
}
}
