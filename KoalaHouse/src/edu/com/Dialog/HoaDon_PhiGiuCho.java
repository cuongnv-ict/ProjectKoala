/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Dialog;

import DataBase.HocSinh.AStudentAndLateDay;
import DataBase.HocSinh.Convert;
import DataBase.HocSinh.Get;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
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
public class HoaDon_PhiGiuCho extends javax.swing.JDialog implements Printable{
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
    String[] str;
    public boolean inLai = false;
    public String shdTemp;
    private boolean isPrintAgain = false;
    public static ArrayList<String> noteContent;
    Convert convert = new Convert();
    
    /**
     * Creates new form NewJDialog
     */
    public HoaDon_PhiGiuCho(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if(noteContent != null && noteContent.size() != 0){
            for(String str : noteContent){
                NoteCostText.append(str+"\n");
            }
        }
        TenHocSinh.setText(tenHS);
        NguoiDaiDien.setText(tenNguoiDaiDien);
        tenLop.setText(lop);
        //lay ngay thang nam
            Date aDate = new Date(System.currentTimeMillis());
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(aDate);
            ngay.setSelectedIndex(calendar.get(Calendar.DATE) -1);
            thang.setSelectedIndex(calendar.get(Calendar.MONTH));
            nam.setSelectedIndex(calendar.get(Calendar.YEAR)-2012);
            nam.setEnabled(false);
            ArrayList infoFac = new Get().getInFoFac();
            DiaChi.setText(infoFac.get(0).toString());
            DienThoaiTrungTam.setText(infoFac.get(1).toString());
        //set so hoa don
        sott = new RecieptManagerment().GetNextNumberReceipt(aDate.toString());
        String soHoaDon = null;
        int id_Fac = HoaDon_PhiGiuCho.idTT;
        if(HoaDon_PhiGiuCho.idhs==0){
            id_Fac = new Get().GetIDFac();
        }
        switch(id_Fac){
            case 1: soHoaDon = "BT"+XuLy.getNumber4(String.valueOf(sott));break;
            case 2: soHoaDon = "DQ"+XuLy.getNumber4(String.valueOf(sott));break;
            case 3: soHoaDon = "KB"+XuLy.getNumber4(String.valueOf(sott));break;
            case 4: soHoaDon = "HT"+XuLy.getNumber4(String.valueOf(sott));break;
        }
        stt.setText(String.valueOf(soHoaDon));
        shdTemp = soHoaDon;
        tenTrungTam.setText(trungTam);
        idTrungTam = HoaDon_PhiGiuCho.idTT;
        if(idTrungTam==0){
            idTrungTam = new Get().GetIDFac();
        }
        idHocSinh = HoaDon_PhiGiuCho.idhs;
        //tinh tong tien 
        if(idHocSinh>0){
        new RecieptManagerment().BangDSPhiHoaDon(idhs,idTrungTam, jTable1);
        model = (DefaultTableModel) jTable1.getModel();
        Total();
        daThu.setText(TongTien.getText());
        }
        else{
            model = (DefaultTableModel) jTable1.getModel();
            daThu.setText("0");
        }
        //xoa du lieu static
        HoaDon_PhiGiuCho.tenHS = "";
        HoaDon_PhiGiuCho.tenNguoiDaiDien = "";
        HoaDon_PhiGiuCho.trungTam = "";
        HoaDon_PhiGiuCho.idhs = 0;
        HoaDon_PhiGiuCho.lop = "";
        HoaDon_PhiGiuCho.idTT = 0;
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
        if(idHocSinh>0){
            //luu lai trang thai bang ban dau
        str = new String[model.getRowCount()];
        for(int i=0;i<model.getRowCount();i++){
            str[i] = model.getValueAt(i, 3).toString();
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
            String tenPhi = model.getValueAt(i, 1).toString().toLowerCase();
            if(hienChietKhau.isSelected()){
                long t = Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                int ck = 0;
                if(model.getValueAt(i, 4).toString().length()>0){
                    ck = Integer.parseInt(model.getValueAt(i, 4).toString());
                }
                long ti = (t*(100-ck))/100;
                if(tenPhi.indexOf("hoàn")== -1)
                    Total += ti;
                else
                    Total -= ti;
            }
            else{
            if(model.getValueAt(i, 3).toString().length()>0){
                if(tenPhi.indexOf("hoàn")== -1)
                    Total += Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                else
                    Total -= Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
            }
            }
        }
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
        //viet hoa chu cai dau
        char[] head = new char[1];
        head[0] = tienbc.charAt(0);
        String h = new String(head);
        h = h.toUpperCase();
        tienbc = tienbc.substring(1);
        tienbc = h + tienbc;
        stbc.setText("Số tiền bằng chữ: "+tienbc);
        }
        catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Gặp Vấn Đề Khi Tính Tổng Số Tiền");
        }
        //tinh lai so tien
        if(hienChietKhau.isSelected()){
            for(int i=0;i<model.getRowCount();i++){
                long t = Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                int ck = 0;
                if(model.getValueAt(i, 4).toString().length()>0){
                    ck = Integer.parseInt(model.getValueAt(i, 4).toString());
                }
                long ti = (t*(100-ck))/100;
                String sotien = XuLy.setMoney(String.valueOf(ti));
                model.setValueAt(sotien, i, 3);
            }
        }
        else{
            
        }
        
    }
    }
    public void totalWhenNoStudent(){
        model = (DefaultTableModel) jTable1.getModel();
                long Total = 0;
        try{
        for(int i=0;i<model.getRowCount();i++){
            String tenPhi = "";
            if(model.getValueAt(i, 1)!=null)
                tenPhi = model.getValueAt(i, 1).toString();
            if(tenPhi.length()==0)
                break;
            tenPhi = tenPhi.toLowerCase();
            if(hienChietKhau.isSelected()){
                long t = Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                int ck = 0;
                if(model.getValueAt(i, 4).toString().length()>0){
                    ck = Integer.parseInt(model.getValueAt(i, 4).toString());
                }
                long ti = (t*(100-ck))/100;
                if(tenPhi.indexOf("hoàn")== -1)
                    Total += ti;
                else
                    Total -= ti;
            }
            else{
            if(model.getValueAt(i, 3).toString().length()>0){
                if(tenPhi.indexOf("hoàn")== -1)
                    Total += Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                else
                    Total -= Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
            }
            }
        }
        TongTien.setText(XuLy.setMoney(String.valueOf(Total)));
        int x = Integer.parseInt(XuLy.getMoney(TongTien.getText()));
        if(x <0){
            jLabel23.setText("Số Tiền Hoàn Trả :");
            x = x* (-1);
            TongTien.setText(XuLy.setMoney(String.valueOf(x)));
            checkDaThu.setText("Hoàn Trả :");
            daThu.setText(XuLy.setMoney(String.valueOf(x)));
        }   
        String tienbc = convert.chuyentien(TongTien.getText());
        //viet hoa chu cai dau
        char[] head = new char[1];
        head[0] = tienbc.charAt(0);
        String h = new String(head);
        h = h.toUpperCase();
        tienbc = tienbc.substring(1);
        tienbc = h + tienbc;
        stbc.setText("Số tiền bằng chữ: "+tienbc);
        }
        catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Gặp Vấn Đề Khi Tính Tổng Số Tiền");
        }
        //tinh lai so tien
        if(hienChietKhau.isSelected()){
            for(int i=0;i<model.getRowCount();i++){
                long t = Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                int ck = 0;
                if(model.getValueAt(i, 4).toString().length()>0){
                    ck = Integer.parseInt(model.getValueAt(i, 4).toString());
                }
                long ti = (t*(100-ck))/100;
                String sotien = XuLy.setMoney(String.valueOf(ti));
                model.setValueAt(sotien, i, 3);
            }
        }
        else{
            
        }
    }
    private void writeFileSave(){
        try{
            File dir1 = new File(System.getProperty("user.dir")+"/storageHD");
            if(!dir1.exists()){
                dir1.mkdir();
            }
            File dir2 = new File(System.getProperty("user.dir")+"/storageHD/"+nam.getSelectedItem().toString());
            if(!dir2.exists()){
                dir2.mkdir();
            }
            int count = 0;
            count = new HistoryManagerment().GetSoHoaDon(nam.getSelectedItem().toString());
            File file = new File(System.getProperty("user.dir")+"/storageHD/"+nam.getSelectedItem().toString()+"/el"+count+".rep");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(file), "UTF8"));
            //----------bat dau ghi file-------------- 
            //dau tien ghi thong tin linh tinh
            String conten1 = ngay.getSelectedIndex()+ "#00#" + thang.getSelectedIndex()+ "#00#" + nam.getSelectedIndex()+ "#00#";
            conten1 = conten1 +stt.getText()+"#00#"+TenHocSinh.getText()+"#00#"+ tenTrungTam.getText()+ "#00#" +NguoiDaiDien.getText()+ "#00#" +tenLop.getText()+ "#00#";
            conten1 = conten1 + HinhThucDong.getSelectedIndex()+"#00#"+TongTien.getText()+"#00#"+daThu.getText()+"#00#"+nguoiNop.getText()+"#00#"+nguoiThu.getText()+"#00#"+stbc.getText()+"\r\n";
            br.write(conten1);
            br.write("#000000$#"+"\r\n");
            //bay gio la ghi du lieu cua bang
            for(int i=0;i<jTable1.getRowCount();i++){
                String dong = "";
                for(int j=0;j<jTable1.getColumnCount();j++){
                    dong = dong + jTable1.getValueAt(i, j)+"#00#";
                }
                if(dong.equals("null#00#null#00#null#00#null#00#")||dong.equals("null#00#null#00#null#00#null#00#null#00#"))
                    continue;
                else
                    br.write(dong +"\r\n");
            }
            br.write("#000000$#"+"\r\n");
        //--------ket thuc ghi file----------------    
            br.flush();
            br.close();
            fw.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error to save file");
        }
    }
    public void readFile(String year,int soHoaDon){
        try{
            //FileReader fr = new FileReader(System.getProperty("user.dir")+"\\storageHD\\"+year+"\\el"+soHoaDon+".rep");
            File fileDir = new File(System.getProperty("user.dir")+"\\storageHD\\"+year+"\\el"+soHoaDon+".rep");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir),"UTF8"));
            String line;
            //doc thong tin linh tinh
            line = br.readLine();
            String[] thongTin = line.split("\\#00\\#");
            ngay.setSelectedIndex(Integer.parseInt(thongTin[0]));ngay.setEnabled(false);
            thang.setSelectedIndex(Integer.parseInt(thongTin[1]));thang.setEnabled(false);
            nam.setSelectedIndex(Integer.parseInt(thongTin[2]));
            stt.setText(thongTin[3]);
            isPrintAgain = true;
            TenHocSinh.setText(thongTin[4]);TenHocSinh.setEnabled(false);
            tenTrungTam.setText(thongTin[5]);tenTrungTam.setEnabled(false);
            NguoiDaiDien.setText(thongTin[6]);NguoiDaiDien.setEnabled(false);
            tenLop.setText(thongTin[7]);tenLop.setEnabled(false);
            HinhThucDong.setSelectedIndex(Integer.parseInt(thongTin[8]));
            TongTien.setText(thongTin[9]);TongTien.setEnabled(false);
            daThu.setText(thongTin[10]);daThu.setEnabled(false);
            nguoiNop.setText(thongTin[11]);nguoiNop.setEnabled(false);
            nguoiThu.setText(thongTin[12]);nguoiThu.setEnabled(false);
            stbc.setText(thongTin[13]);
            line = br.readLine();
            int rowIndex = 0;
            ArrayList cot5 = new ArrayList();
            while((line = br.readLine())!=null){
                if(line.equals("#000000$#")) break;
                String[] dong = line.split("\\#00\\#");
                System.out.println(line);
                System.out.println(dong[0]);
                jTable1.setValueAt(dong[0], rowIndex,0);
                jTable1.setValueAt(dong[1], rowIndex,1);
                jTable1.setValueAt(dong[2], rowIndex,2);
                jTable1.setValueAt(dong[3], rowIndex,3);
                rowIndex++;
                if(dong.length==5){
                    cot5.add(dong[4]);
                }
            }
            hienChietKhau.setEnabled(false);
            if(cot5.size()>0){
               hienChietKhau.setSelected(true);
                model = (DefaultTableModel) jTable1.getModel();
                Object[] coldata = new Object[rowIndex];
                for(int j=0;j<coldata.length;j++)
                    coldata[j] = cot5.get(j).toString();
                model.addColumn("Chiết Khấu(%)",coldata);
            }
            jTable1.setEnabled(false);
            br.close();
            inLai = true;
            totalWhenNoStudent();
        }
        catch(Exception e){
            System.out.println("Error!!");
            JOptionPane.showMessageDialog(null, "Hóa đơn này chưa được lưu lại!");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        NoteCostText = new javax.swing.JTextArea();

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

        jLabel7.setText("tháng");

        thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel8.setText(" năm");

        nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));
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
                .addContainerGap()
                .addGroup(ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(37, 37, 37))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Họ tên học sinh:");

        TenHocSinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TenHocSinh.setText("Nguyễn Hải Anh");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Lớp:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Phụ Huynh:");

        NguoiDaiDien.setText("Nguyễn Ngọc Lan");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Hình Thức TT:");

        HinhThucDong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Chuyển khoản" }));
        HinhThucDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HinhThucDongActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TenHocSinh))
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

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setText("NGƯỜI NỘP");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 51, -1, -1));

        daThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daThuActionPerformed(evt);
            }
        });
        jPanel4.add(daThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 3, 169, -1));
        jPanel4.add(TongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 3, 190, -1));

        jLabel23.setText("Tổng Tiền :");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, -1, -1));
        jPanel4.add(nguoiNop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 66, 258, -1));

        jLabel9.setText("NGƯỜI THU TIỀN");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 51, -1, -1));
        jPanel4.add(nguoiThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 66, 250, -1));

        checkDaThu.setSelected(true);
        checkDaThu.setText("Đã Thu :");
        checkDaThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDaThuActionPerformed(evt);
            }
        });
        jPanel4.add(checkDaThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 2, -1, -1));

        stbc.setText("Số Tiền Bằng Chữ: ");
        jPanel4.add(stbc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, -1, -1));

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
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                {null, null, null, null}
            },
            new String [] {
                "Tên Phí", "Từ Ngày", "Đến Ngày", "Thành Tiền"
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

        NoteCostText.setColumns(20);
        NoteCostText.setRows(5);
        jScrollPane2.setViewportView(NoteCostText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ThongTinCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NgayThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ThongTinHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(hienChietKhau))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                Logger.getLogger(HoaDon_PhiGiuCho.class.getName()).log(Level.SEVERE, null, ex);
               // jButton1.setVisible(true);
            }
        }
        
        if(toPrint){
        if(!inLai){
        writeFileSave();
        if(debt>=0){
            new AStudentAndLateDay().InsertDebt(idHocSinh, idTrungTam, debt);
        }
        button = true;
        //insert to database
        int idStudent = idHocSinh;
        String idFac;
        idFac = String.valueOf(idTrungTam);
        String nguoidong = TenHocSinh.getText();
        String nguoithu = nguoiThu.getText();
        String sotien = XuLy.getMoney(daThu.getText());
        String date = nam.getSelectedItem().toString()+"-"+thang.getSelectedItem().toString()+"-"+ngay.getSelectedItem().toString();
        int hinhthucdong = HinhThucDong.getSelectedIndex();
        String phantram = "";
        if(hienChietKhau.isSelected())
            phantram = "1";
        else
            phantram = "0";
        if(HinhThucDong.getSelectedIndex()==1){
            sott = sott - 1;
        }
        if(idStudent>0)
            new HistoryManagerment().InsertLSHoaDon(idStudent, idFac,sott, nguoidong, nguoithu, sotien, date, hinhthucdong, phantram, tenLop.getText());
        else
            new HistoryManagerment().InsertLSHoaDonNoIDStudent(idFac,sott, nguoidong, nguoithu, sotien, date, hinhthucdong, phantram, tenLop.getText());
        //update xe bus
        new RecieptManagerment().UpdateXeBus(idStudent);
        }
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
        if(evt.getClickCount()==1){
            String nameCost =jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
            System.out.println("have : "+nameCost);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
//         for(int i=0;i<model.getRowCount();i++){
//                model.setValueAt(str[i], i, 3);
//            }
//        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
//            Total();
//        }
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
        try{
        if(hienChietKhau.isSelected()){
            Object[] coldata = new Object[model.getRowCount()];
            for(int j=0;j<coldata.length;j++)
                coldata[j] = 0;
            model.addColumn("Chiết Khấu(%)",coldata);
        }
        else{
          for(int i=0;i<model.getRowCount();i++){
                model.setValueAt(str[i], i, 3);
            }
          TableColumn tcol = jTable1.getColumnModel().getColumn(4);
          jTable1.getColumnModel().removeColumn(tcol);
          model.setColumnCount(4);
        }
        Total();
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_hienChietKhauActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if(idHocSinh>0){
           for(int i=0;i<model.getRowCount();i++){
                model.setValueAt(str[i], i, 3);
            } 
        }
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Total();
            if(idHocSinh==0){
                totalWhenNoStudent();
            }
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void HinhThucDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HinhThucDongActionPerformed
        // TODO add your handling code here:
        if(HinhThucDong.getSelectedIndex()==0){
        if(!isPrintAgain)        
            stt.setText(String.valueOf(shdTemp));
        isPrintAgain = false;
        }else{
        isPrintAgain = false;
            stt.setText("CK");
        }
    }//GEN-LAST:event_HinhThucDongActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDon_PhiGiuCho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon_PhiGiuCho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon_PhiGiuCho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon_PhiGiuCho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDon_PhiGiuCho dialog = new HoaDon_PhiGiuCho(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea NoteCostText;
    private javax.swing.JTextField TenHocSinh;
    private javax.swing.JPanel ThongTinCoSo;
    private javax.swing.JPanel ThongTinHoaDon;
    private javax.swing.JPanel ThongTinHocSinh;
    private javax.swing.JTextField TongTien;
    private javax.swing.JPanel avatar;
    private javax.swing.JCheckBox checkDaThu;
    private javax.swing.JTextField daThu;
    private javax.swing.JCheckBox hienChietKhau;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane2;
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
        
        grphcs.drawString(lbTitle, 140, 10);
        // ve image
        grphcs.drawImage(imageOfLabel, 0, 0, imageOfLabel.getWidth(this), imageOfLabel.getHeight(this), null);
        
        grphcs.drawString(lbDiaChi, 100, 30);
        grphcs.drawString(lbDienThoai, 100, 50);
        grphcs.drawString(tfDiaChi, 170, 30);
        grphcs.drawString(tfDienThoai, 170, 50);
        font = new Font("Serif", Font.BOLD, 14);
        grphcs.setFont(font);
        grphcs.drawString(lbHoaDon, 140, 90);
        font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
        
        grphcs.drawString(lbNgay, 10, 120);
        grphcs.drawString(cbNgay, 40, 120);
        grphcs.drawString(lbThang, 55, 120);
        grphcs.drawString(cbThang, 85, 120);
        grphcs.drawString(lbNam, 95, 120);
        grphcs.drawString(cbNam, 125, 120);
        grphcs.drawString(lbSo, 280, 120);
        grphcs.drawString(tfSo, 295, 120);
        grphcs.drawString(lbHoTen, 10, 140);
        grphcs.drawString(tfHoTen, 80, 140);
        //grphcs.drawString(lbMa, 170, 140);
        //grphcs.drawString(tfMa, 190, 140);
        grphcs.drawString(lbCoso, 280, 140);
        grphcs.drawString(tfCoso, 315, 140);
        grphcs.drawString(lbNguoiThanhToan, 10, 160);
        grphcs.drawString(tfNguoiThanhToan, 65, 160);
        grphcs.drawString(lbLop, 160, 160);
        grphcs.drawString(tfLop, 185, 160);
        grphcs.drawString(lbHinhThucThanhToan, 280, 160);
        grphcs.drawString(tfHinhThucThanhToan, 355, 160);
        
        String tableDataToString = "";
        if(hienChietKhau.isSelected()) {
            font = new Font("Serif", Font.BOLD, 10);
        grphcs.setFont(font);
            grphcs.drawString("STT ", 15, 190);
            grphcs.drawString("Tên phí ", 45, 190);
            grphcs.drawString("Thời gian ", 190, 190);
            grphcs.drawString("Thành tiền ", 280, 190);
            grphcs.drawString("Chiết khấu", 340, 190);
            font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
            for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = tableData[i][j].toString();
                }
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 15, (190 + (i + 1) * 20));
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 45, (190 + (i + 1) * 20));
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 160, (190 + (i + 1) * 20));
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 280, (190 + (i + 1) * 20));
                } else if(j == 4) {
                    grphcs.drawString(tableDataToString + " (%)", 340, (190 + (i + 1) * 20));
                }
            }
        }
            grphcs.drawRect(10, 180, 383, (nRow + 1) * 20);
            grphcs.drawLine(10, 195, 393, 195);
            grphcs.drawLine(40, 180, 40, 180 + (nRow + 1) * 20);
            grphcs.drawLine(155, 180, 155, 180 + (nRow + 1) * 20);
            grphcs.drawLine(275, 180, 275, 180 + (nRow + 1) * 20);
            grphcs.drawLine(335, 180, 335, 180 + (nRow + 1) * 20);
        } else {
            font = new Font("Serif", Font.BOLD, 10);
        grphcs.setFont(font);
        grphcs.drawString("STT ", 20, 190);
        grphcs.drawString("Tên phí", 50, 190);
        grphcs.drawString("Thời gian", 230, 190);
        //grphcs.drawString("Đến ngày ", 225, 190);
        grphcs.drawString("Thành tiền ", 325, 190);
        font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
        //int rowIndex = 190;
        for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = tableData[i][j].toString();
                }
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 20, (190 + (i + 1) * 20));
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 50, (190 + (i + 1) * 20));
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 200, (190 + (i + 1) * 20));
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 325, (190 + (i + 1) * 20));
                }
            }
        }
        grphcs.drawRect(10, 180, 373, (nRow + 1) * 20);
        grphcs.drawLine(10, 195, 383, 195);
        grphcs.drawLine(45, 180, 45, 180 + (nRow + 1) * 20);
        grphcs.drawLine(195, 180, 195, 180 + (nRow + 1) * 20);
        grphcs.drawLine(320, 180, 320, 180 + (nRow + 1) * 20);
        }
        int rowIndex = 190 + nRow * 20;
        rowIndex = rowIndex + 40;
        //here to printf note about cost "phi giu cho"----page 1-----
        HoaDon_PhiGiuCho.noteContent = new ArrayList<String>();
        for (String line : NoteCostText.getText().split("\\n")){
            grphcs.drawString(line, 20, rowIndex);
            noteContent.add(line);
            rowIndex+= 20;
        }
        //finish printf note------------------------------------
//        System.out.println(rowIndex);
        grphcs.drawString(lbTongTien, 20, rowIndex + 20);
        grphcs.drawString(tfTongTien, 80, rowIndex + 20);
        if(checkDaThu.isSelected()) {
        grphcs.drawString(lbDathu, 280, rowIndex + 20);
        grphcs.drawString(tfDaThu, 325, rowIndex + 20);
        }
        grphcs.drawString(lbStbc, 20, rowIndex + 40);
        
        grphcs.drawString(lbNguoiNop, 40, rowIndex + 70);
        grphcs.drawString(lbNguoiThu, 270, rowIndex + 70);
        //grphcs.drawString(tfNguoiNop, coorOfNguoiNop, 580);
        //grphcs.drawString(tfNguoiThu, coorOfNguoiThu, 580);
        
        FontMetrics fontMetrics = grphcs.getFontMetrics(font);
        int lengthOfNguoiNop = fontMetrics.stringWidth(tfNguoiNop);
        int lengOfNguoiThu = fontMetrics.stringWidth(tfNguoiThu);
        
        lengthOfNguoiNop = lengthOfNguoiNop / 2;
        lengOfNguoiThu = lengOfNguoiThu / 2;
        
        coorOfNguoiNop = 81 - lengthOfNguoiNop;
        coorOfNguoiThu = 327 - lengOfNguoiThu;
        
        grphcs.drawString(tfNguoiNop, coorOfNguoiNop - 10, rowIndex + 140);
        grphcs.drawString(tfNguoiThu, coorOfNguoiThu - 10, rowIndex + 140);
        
        // print page 2.Cong them 400
        grphcs.drawString(lbTitle, 560, 10);
        // ve image
        grphcs.drawImage(imageOfLabel, 420, 0, imageOfLabel.getWidth(this), imageOfLabel.getHeight(this), null);
        
        font = new Font("Serif", Font.PLAIN, 12);
        grphcs.setFont(font);
        grphcs.drawString(lbDiaChi, 520, 30);
        grphcs.drawString(lbDienThoai, 520, 50);
        grphcs.drawString(tfDiaChi, 590, 30);
        grphcs.drawString(tfDienThoai, 590, 50);
        font = new Font("Serif", Font.BOLD, 14);
        grphcs.setFont(font);
        grphcs.drawString(lbHoaDon, 560, 90);
        font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
        
        grphcs.drawString(lbNgay, 430, 120);
        grphcs.drawString(cbNgay, 460, 120);
        grphcs.drawString(lbThang, 475, 120);
        grphcs.drawString(cbThang, 505, 120);
        grphcs.drawString(lbNam, 515, 120);
        grphcs.drawString(cbNam, 545, 120);
        grphcs.drawString(lbSo, 690, 120);
        grphcs.drawString(tfSo, 705, 120);
        grphcs.drawString(lbHoTen, 430, 140);
        grphcs.drawString(tfHoTen, 500, 140);
        //grphcs.drawString(lbMa, 590, 140);
        //grphcs.drawString(tfMa, 610, 140);
        grphcs.drawString(lbCoso, 690, 140);
        grphcs.drawString(tfCoso, 725, 140);
        grphcs.drawString(lbNguoiThanhToan, 430, 160);
        grphcs.drawString(tfNguoiThanhToan, 485, 160);
        grphcs.drawString(lbLop, 580, 160);
        grphcs.drawString(tfLop, 605, 160);
        grphcs.drawString(lbHinhThucThanhToan, 690, 160);
        grphcs.drawString(tfHinhThucThanhToan, 765, 160);
        if(hienChietKhau.isSelected()) {
            font = new Font("Serif", Font.BOLD, 10);
            grphcs.setFont(font);
            grphcs.drawString("STT ", 435, 190);
            grphcs.drawString("Tên phí ", 465, 190);
            grphcs.drawString("Thời gian ", 610, 190);
            grphcs.drawString("Thành tiền ", 700, 190);
            grphcs.drawString("Chiết khấu", 760, 190);
            font = new Font("Serif", Font.PLAIN, 10);
            grphcs.setFont(font);
            for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = tableData[i][j].toString();
                }
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 435, (190 + (i + 1) * 20));
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 465, (190 + (i + 1) * 20));
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 580, (190 + (i + 1) * 20));
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 700, (190 + (i + 1) * 20));
                } else if(j == 4) {
                    grphcs.drawString(tableDataToString + " (%)", 760, (190 + (i + 1) * 20));
                }
            }
        }
            grphcs.drawRect(430, 180, 383, (nRow + 1) * 20);
        grphcs.drawLine(430, 195, 813, 195);
        grphcs.drawLine(460, 180, 460, 180 + (nRow + 1) * 20);
        grphcs.drawLine(575, 180, 575, 180 + (nRow + 1) * 20);
        grphcs.drawLine(695, 180, 695, 180 + (nRow + 1) * 20);
        grphcs.drawLine(755, 180, 755, 180 + (nRow + 1) * 20);
        } else {
            font = new Font("Serif", Font.BOLD, 10);
        grphcs.setFont(font);
        //String tableDataToString = "";
        grphcs.drawString("STT ", 440, 190);
        grphcs.drawString("Tên phí ", 470, 190);
        grphcs.drawString("Thời gian ", 650, 190);
        grphcs.drawString("Thành tiền ", 745, 190);
        font = new Font("Serif", Font.PLAIN, 10);
        grphcs.setFont(font);
        for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = tableData[i][j].toString();
                }
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 440, (190 + (i + 1) * 20));
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 470, (190 + (i + 1) * 20));
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 620, (190 + (i + 1) * 20));
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 745, (190 + (i + 1) * 20));
                }
            }
        }
        grphcs.drawRect(430, 180, 373, (nRow + 1) * 20);
        grphcs.drawLine(430, 195, 803, 195);
        grphcs.drawLine(465, 180, 465, 180 + (nRow + 1) * 20);
        grphcs.drawLine(615, 180, 615, 180 + (nRow + 1) * 20);
        grphcs.drawLine(740, 180, 740, 180 + (nRow + 1) * 20);
        }
        
        int rowIndexLeft = 190 + nRow * 20;
        rowIndexLeft = rowIndexLeft + 40;
        //here to printf note about cost "phi giu cho"--- page 2 ------
        for (String line : NoteCostText.getText().split("\\n")){
            grphcs.drawString(line, 440, rowIndexLeft);
            rowIndexLeft+= 20;
        }
        //finish printf note------------------------------------
        
        grphcs.drawString(lbTongTien, 440, rowIndexLeft + 20);
        grphcs.drawString(tfTongTien, 500, rowIndexLeft + 20);
        if(checkDaThu.isSelected()) {
        grphcs.drawString(lbDathu, 700, rowIndexLeft + 20);
        grphcs.drawString(tfDaThu, 745, rowIndexLeft + 20);
        }
        grphcs.drawString(lbStbc, 440, rowIndexLeft + 40);
        
        grphcs.drawString(lbNguoiNop, 460, rowIndexLeft + 70);
        grphcs.drawString(lbNguoiThu, 690, rowIndexLeft + 70);
        //grphcs.drawString(tfNguoiNop, coorOfNguoiNop, 580);
        //grphcs.drawString(tfNguoiThu, coorOfNguoiThu, 580);
        
        //FontMetrics fontMetrics = grphcs.getFontMetrics(font);
        //int lengthOfNguoiNop = fontMetrics.stringWidth(tfNguoiNop);
        //int lengOfNguoiThu = fontMetrics.stringWidth(tfNguoiThu);
        
        //lengthOfNguoiNop = lengthOfNguoiNop / 2;
        //lengOfNguoiThu = lengOfNguoiThu / 2;
        
        coorOfNguoiNop = 501 - lengthOfNguoiNop;
        coorOfNguoiThu = 747 - lengOfNguoiThu;
        
        grphcs.drawString(tfNguoiNop, coorOfNguoiNop - 10, rowIndexLeft + 140);
        grphcs.drawString(tfNguoiThu, coorOfNguoiThu - 10, rowIndexLeft + 140);
        
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
    lbMa = "";
    lbCoso = jLabel13.getText();
    tfHoTen = TenHocSinh.getText();
    tfMa = "";
    tfCoso = tenTrungTam.getText();
    lbNguoiThanhToan = jLabel18.getText();
    //lbLop = jLabel18.getText();
    lbLop = "";
    lbHinhThucThanhToan = jLabel19.getText();
    tfNguoiThanhToan = tenLop.getText();
    //tfLop = tenLop.getText();
    tfLop = "";
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
