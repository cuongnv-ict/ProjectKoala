/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.ThongBaoKyHe;
        
import DataBase.HocSinh.AStudentAndLateDay;
import DataBase.HocSinh.Get;
import DataBase.HocSinh.RecieptManagerment;
import static edu.com.Dialog.HoaDon.idhs;
import edu.com.XuLy;
import java.awt.Component;
import java.awt.Font;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;
//import org.apache.poi.;

/**
 *
 * @author BaoNC
 */
public class ThongBaoKy2 extends javax.swing.JFrame implements Printable {
    private Printable delegate;
    private Icon iconOfLabel;
    private Image imageOfLabel;
    private String lbOfTT;
    private String lbOfNamhoc;
    private String lbNamHoc1;
    private String lbNamHoc2;
    private String lbStraight;
    private String lbHovaTen;
    private String lbLop;
    private String lbCoSo;
    private String lbChuongTrinh;
    private String tfHovaTen;
    private String tfLop;
    private String tfCoSo;
    private String tfChuongTrinh;
    private String lbDVT;
    private String cbDVT;
    private String lbNgay;
    private String lbThang;
    private String lbNam;
    private String cbNgay;
    private String cbThang;
    private String cbNam;
    private String lbTuanHoc;
    private String lbTuan1;
    private String lbTuan2;
    private String lbTuan3;
    private String lbTuan4;
    private String lbTuan5;
    private String lbTuan6;
    private String tfTuan1;
    private String tfTuan2;
    private String tfTuan3;
    private String tfTuan4;
    private String tfTuan5;
    private String tfTuan6;
    private String[] linesOfLuuy;
    private String lineOfLuuy;
    private int nRow;
    private int nCol;
    private Object[][] tableData;
    public int pageBreak;
    public int idStudent;
    public static String hanNop_sta = "";
    public static String luuY_sta = "";
    public static String luuY1_sta = "";
    public static String tuan1_sta = "Tuần 1:";
    public static String tuan2_sta = "Tuần 2:";
    public static String tuan3_sta = "Tuần 3:";
    public static String tuan4_sta = "Tuần 4:";
    public static String tuan5_sta = "Tuần 5:";
    public static String tuan6_sta = "Tuần 6:";
    
    //int pageBreak[];
    
    /**
     * Creates new form ThongBaoKyGeVer2
     */
    public ThongBaoKy2(int idHS,int idTrungTam) {
        initComponents();
        //jComboBox1.setSelectedIndex(5);
        //jComboBox1.setSelectedItem("2015");
        getCurrentDate();
        jComboBox3.setSelectedItem(date);
        jComboBox4.setSelectedItem(month);
        jComboBox5.setSelectedItem(year);
        jComboBox2.setSelectedItem(year);
        jComboBox1.setSelectedItem(year + 1);
        idStudent = idHS;
        AStudentAndLateDay a = new AStudentAndLateDay();
            ArrayList info;
            info = a.HocSinhA1(idStudent);
        tenHS.setText((String) info.get(1));
        tenChuongTrinh.setText("Cả ngày - 5 ngày/tuần");
        tenLop.setText((String) info.get(10));
        tenCoSo.setText((String) info.get(11));
        jTextField11.setText(hanNop_sta);
        if(luuY_sta.length()>0)
            jTextArea1.setText(luuY_sta);
        if(luuY1_sta.length()>0)
            jTextArea2.setText(luuY1_sta);
        int CurrentYear = new Get().getYearActive(idTrungTam);
        if(CurrentYear > 2012){
            jComboBox1.setSelectedIndex(CurrentYear - 2013);
            jComboBox2.setSelectedIndex(CurrentYear - 2012);
        }
        new RecieptManagerment().BangDSPhiThongBaoTrongNam(idStudent,idTrungTam, jTable1,false);
        Total();
        readFile();
    }
    public void Total(){
        DefaultTableModel model;
        model = (DefaultTableModel) jTable1.getModel();
        long Total = 0;
        try{
            for(int i=0;i<model.getRowCount()-1;i++){
                String tenPhi = model.getValueAt(i, 1).toString().toLowerCase();
                if(tenPhi.indexOf("hoàn")== -1)
                    Total += Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
                else
                    Total -= Integer.parseInt(XuLy.getMoney(model.getValueAt(i, 3).toString()));
            }
            String tongphi = XuLy.setMoney(String.valueOf(Total));
            model.setValueAt(tongphi,model.getRowCount()-1 , 3);
        }catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Gặp Vấn Đề Khi Tính Tổng Số Tiền");
        }
    }
private void readFile(){
        try{
            FileReader fr = new FileReader("thongbaoky.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();jTextField11.setText(line);
            line = br.readLine();
            jTextArea1.setText("");
            while((line = br.readLine())!=null){
                if(line.equals("#00$#")) break;
                jTextArea1.append(line + "\n");
            }
            jTextArea2.setText("");
            while((line = br.readLine())!=null){
                if(line.equals("#00$#")) break;
                jTextArea2.append(line + "\n");
            }
            br.close();
            fr.close();
        }
        catch(Exception e){
        }
    }
    private void writeFileClose(){
        try{
            File file = new File("thongbaoky.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fw);
            //----------bat dau ghi file-------------- 
            //dau tien la ghi han nop
            String hannop = jTextField11.getText()+ "\r\n";
            br.write(hannop);
            br.write("#00$#"+"\r\n");
            //sau di ghi luu y 1
            String xau = jTextArea1.getText();
            String[] line;
            int size = xau.length() - 1;
            if(xau.charAt(size)=='\n'){
                xau = xau.substring(0, size);
            }
            int count= jTextArea1.getLineCount();
            int i=1;
            int vitri;
            while(i != count){
                vitri=xau.indexOf('\n');
                if(vitri==-1) {
                    break;
                }
                String xaucon= xau.substring(0, vitri);
                br.write(xaucon + "\r\n");
                xau = xau.substring(vitri + 1);
                i++;
            }
            vitri=xau.indexOf('\n');
            if(vitri==-1){
                String xaucon = new String(xau);
                br.write(xaucon);
            }
            else{ 
            }
            br.write("\r\n"+"#00$#"+"\r\n");
            //sau do ghi luu y 2
            xau = jTextArea2.getText();
            size = xau.length() - 1;
            if(xau.charAt(size)=='\n'){
                xau = xau.substring(0, size);
            }
            count= jTextArea2.getLineCount();
            i=1;
            while(i != count){
                vitri=xau.indexOf('\n');
                if(vitri==-1) {
                    break;
                }
                String xaucon= xau.substring(0, vitri);
                br.write(xaucon + "\r\n");
                xau = xau.substring(vitri + 1);
                i++;
            }
            vitri=xau.indexOf('\n');
            if(vitri==-1){
                String xaucon = new String(xau);
                br.write(xaucon);
            }
            else{ 
            }
        //--------ket thuc ghi file----------------    
            br.close();
            fw.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error to save file");
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbTT1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tenHS = new javax.swing.JTextField();
        tenLop = new javax.swing.JTextField();
        tenCoSo = new javax.swing.JTextField();
        tenChuongTrinh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField11 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox();
        jComboBox8 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(787, 600));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/ThongBaoKyHe/Untitled.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbTT1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lbTT1.setText(" THÔNG BÁO NỘP HỌC PHÍ KỲ II ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("NĂM HỌC");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel4.setText("  -");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTT1)
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTT1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 839));

        jLabel5.setText("Ngày");

        jLabel6.setText("tháng");

        jLabel7.setText("năm");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));

        jLabel8.setText("Họ và  tên học sinh");

        jLabel9.setText("Lớp");

        jLabel10.setText("Cơ sở");

        jLabel11.setText("Chương trình");

        jLabel12.setText("Đơn vị tính");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VND", "USD" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Học phí Kỳ 2", null, null},
                {"2", "Cơ sở vật chất Kỳ 2", null, null},
                {"3", "Phí xe buýt Kỳ 2", null, null},
                {"4", "Học phí các môn ngoại khóa Kỳ 2", null, null},
                {"5", "Phí trông muộn", null, null},
                {"6", "Các khoản phải nộp khác", null, null},
                {"7", "Các khoản hoàn phí", null, null},
                {"8", "Tổng cộng", null, null}
            },
            new String [] {
                "STT", "Nội dung", "Thời gian", "Số tiền"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setMinWidth(120);
        }

        jLabel20.setText("Hạn nộp");

        jLabel21.setText("Loại tiền thanh toán");

        jLabel22.setText("Hình thức thanh toán");

        jLabel23.setText("Thông tin chuyển khoản");

        jLabel24.setText("Tên tài khoản");

        jLabel25.setText("Số tài khoản");

        jLabel26.setText("Tên ngân hàng");

        jLabel27.setText("Lưu ý");

        jTextArea1.setColumns(10);
        jTextArea1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("* Trong trường hợp thanh toán bằng chuyển khoản, đề nghị phụ huynh cung cấp văn phòng nhà trường bản sao Ủy nhiệm chi của Ngân hàng ngay sau khi tiền được chuyển. Đề nghị ghi rõ họ tên học sinh, lớp, cơ sở, và chi tiết học phí.\n * Trường Koala House chấp nhận thanh toán bằng thẻ tín dụng tại văn phòng trường, phí thanh toán qua thẻ do phụ huynh trả theo biểu phi của mỗi loại thẻ được ngân hàng quy đinh.\n");
        jTextArea1.setToolTipText("");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea1);

        jTextField15.setText("Công ty Cổ phần trường mầm non Koala House");

        jTextField16.setText("002100201083");

        jTextField17.setText("Vietcombank Hà Nội");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VND", "USD" }));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Thanh toán bằng tiền mặt", "Chuyển khoản" }));

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("* Học phí và các khoản phụ phí khác phụ huynh đòng bằng tiền VND. Hình thức thanh toánbằng chuyển khoản qua ngân hàng hoặc nộp tiền mặt tại văn phòng trường.\n\n* Nhà trường sẽ không gửi thông báo học phí lần thứ hai. nếu học phí không được nộp \nđúng hạn, việc giữ chỗ cho con em vị trong kỳ học sẽ không được bảo đảm");
        jScrollPane1.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(496, 496, 496)
                        .addComponent(jLabel5)
                        .addGap(9, 9, 9)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel8)
                        .addGap(23, 23, 23)
                        .addComponent(tenHS, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(tenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(tenCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(tenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(634, 634, 634)
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addComponent(jLabel20)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21)
                        .addGap(124, 124, 124)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22)
                        .addGap(118, 118, 118)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel24)
                        .addGap(95, 95, 95)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel25)
                        .addGap(101, 101, 101)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel26)
                        .addGap(88, 88, 88)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(tenHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(tenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(tenCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11))
                    .addComponent(tenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12))
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel17)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel18)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel23)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel27)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setViewportView(jPanel2);

        btnPrint.setText("Print");
        btnPrint.setToolTipText("In thông báo ");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(btnPrint)))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //luu thong tin cho thong bao sau
        writeFileClose();
        hanNop_sta = jTextField11.getText();
        luuY_sta = jTextArea1.getText();
        luuY1_sta = jTextArea2.getText();
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Printting detailt");
        pj.setPrintable(this);
        PrintRequestAttributeSet printRequest = new HashPrintRequestAttributeSet();
        printRequest.add(MediaSizeName.ISO_A4);
        printRequest.add(new MediaPrintableArea((float)0.0, (float)0.0, 350, 500, MediaPrintableArea.MM));
        
        boolean toPrint = pj.printDialog(printRequest);
        
        if(toPrint) {
            try {
                pj.print(printRequest);
            } catch (PrinterException ex) {
                Logger.getLogger(ThongBaoKyGeVer2.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Total();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
            Total();
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ThongBaoKyGeVer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongBaoKyGeVer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongBaoKyGeVer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongBaoKyGeVer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //ThongBaoKyGeVer2 tt = new ThongBaoKyGeVer2(, idStudent)
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JLabel lbTT1;
    private javax.swing.JTextField tenChuongTrinh;
    private javax.swing.JTextField tenCoSo;
    private javax.swing.JTextField tenHS;
    private javax.swing.JTextField tenLop;
    // End of variables declaration//GEN-END:variables

    private static int number = 0;
    @Override
    public int print(Graphics grphcs, PageFormat pf, int page) throws PrinterException {
        getComponenets();
        editTextArea();
        //pageBreak = new int[4];
        double pageHeight = pf.getImageableHeight();
        int linePerPage = ((int)pageHeight/20);
        int lines = 0;
        
        if(page > 1) {
            return NO_SUCH_PAGE;
        }
        if(page == 0) {
        Font font = new Font("Serif", Font.PLAIN, 16);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        // ve cho tieu de
        grphcs.setFont(font);
        //grphcs.drawImage(imageOfLabel, 0, 0, null);
        grphcs.drawImage(imageOfLabel, 10, 0, imageOfLabel.getWidth(this), imageOfLabel.getHeight(this), null);
        grphcs.drawString(lbOfTT, 220, 40);
        
        // ve cho nam hoc
        font = new Font("Serif", Font.PLAIN, 14);
        grphcs.setFont(font);
        grphcs.drawString(lbOfNamhoc, 270, 65);
        grphcs.drawString(lbNamHoc1, 350, 65);
        grphcs.drawString(" - ", 375, 65);
        grphcs.drawString(lbNamHoc2, 385, 65);
        
        // ve o ben ngoai
        font = new Font("Serif", Font.PLAIN, 14);
        grphcs.setFont(font);
        grphcs.drawRect(195, 20, 305, 60);
        font = new Font("Serif", Font.BOLD, 14);
        grphcs.setFont(font);
        grphcs.drawRect(193, 18, 309, 64);
        
        font = new Font("Serif", Font.PLAIN, 13);
        grphcs.setFont(font);
        grphcs.drawString(lbNgay, 340, 100);
        grphcs.drawString(cbNgay, 370, 100);
        grphcs.drawString(lbThang, 385, 100);
        grphcs.drawString(cbThang, 415, 100);
        grphcs.drawString(lbNam, 430, 100);
        grphcs.drawString(cbNam, 455, 100);
        
        grphcs.drawString(lbHovaTen + ": ", 20, 140);
        grphcs.drawString(tfHovaTen, 150, 140);
        grphcs.drawString(lbLop + ": ", 20, 160);
        grphcs.drawString(tfLop, 150, 160);
        grphcs.drawString(lbCoSo, 20, 180);
        grphcs.drawString(tfCoSo, 150, 180);
        grphcs.drawString(lbChuongTrinh, 20, 200);
        grphcs.drawString(tfChuongTrinh, 150, 200);
        
        grphcs.drawString(lbDVT + ": ", 320, 230);
        grphcs.drawString(cbDVT, 390, 230);
        
        // ve bang
        String tableDataToString = "";
        tableDataToString = tableData[0][0].toString();
       // grphcs.drawString(tableDataToString, 450, 250);
        //System.out.println("table data : " + tableDataToString);
        //System.out.println("table data ver 2 : " + tableData[0][0]);
        font = new Font("Serif", Font.BOLD, 14);
        grphcs.setFont(font);
        grphcs.drawString("STT", 25, 260);
        grphcs.drawString("Nội dung", 75, 260);
        grphcs.drawString("Thời gian", 305, 260);
        grphcs.drawString("Số tiền", 455, 260);
        //grphcs.drawString("Ghi chú", 500, 260);
        font = new Font("Serif", Font.PLAIN, 13);
        grphcs.setFont(font);
        lines = 11;
        for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                if(tableData[i][j] == null) {
                    tableDataToString = "     ";
                } else {
                    tableDataToString = tableData[i][j].toString();
                }
                //tableDataToString = (String) tableData[i][j];
                //grphcs.drawString(tableDataToString, (70 + ((j + 1) * 110)), (240 + (i + 1) * 20));
                //System.out.println("TableData : " + tableDataToString);
                if(j == 0) {
                    grphcs.drawString(tableDataToString, 25, (260 + (i + 1) * 20));
                    //grphcs.drawLine(20, i, i, i);
                    lines++;
                } else if(j == 1) {
                    grphcs.drawString(tableDataToString, 75, (260 + (i + 1) * 20));
                    lines++;
                } else if(j == 2) {
                    grphcs.drawString(tableDataToString, 305, (260 + (i + 1) * 20));
                    lines++;
                } else if(j == 3) {
                    grphcs.drawString(tableDataToString, 455, (260 + (i + 1) * 20));
                    lines++;
               // } else if(j ==4) {
                 //   grphcs.drawString(tableDataToString, 500, (260 + (i + 1) * 20));
                }
            }
            grphcs.drawLine(20, (260 + (i + 1) * 20 + 5), 525, (260 + (i + 1) * 20 + 5));
        }
        grphcs.drawRect(20, 245, 505, (nRow + 1) * 20);
        grphcs.drawLine(20, 265, 525, 265);
        grphcs.drawLine(70, 245, 70, 245 + (nRow + 1) * 20);
        grphcs.drawLine(300, 245, 300, 245 + (nRow + 1) * 20);
        grphcs.drawLine(450, 245, 450, 245 + (nRow + 1) * 20);
        int line = 260 + (nRow)  * 20;
        line = line + 20;
        // ve cac tuan hoc
       /*
        grphcs.drawString(lbTuanHoc, 20, line + 20);
        grphcs.drawString(lbTuan1, 20, line + 40);
        grphcs.drawString(lbTuan2, 20, line + 60);
        grphcs.drawString(lbTuan3, 20, line + 80);
        grphcs.drawString(lbTuan4, 290, line + 40);
        grphcs.drawString(lbTuan5, 290, line + 60);
        grphcs.drawString(lbTuan6, 290, line + 80);
        grphcs.drawString(tfTuan1, 80, line + 40);
        grphcs.drawString(tfTuan2, 80, line + 60);
        grphcs.drawString(tfTuan3, 80, line + 80);
        grphcs.drawString(tfTuan4, 350, line + 40);
        grphcs.drawString(tfTuan5, 350, line + 60);
        grphcs.drawString(tfTuan6, 350, line + 80);
        */
        //line = line + 100;
        // ve luu y
        //int linesOfTextArea = jTextArea1.getLineCount();
      
        //grphcs.drawString(taLuuy, 70, 520);
        font = new Font("Serif", Font.BOLD, 13);
        grphcs.setFont(font);
        grphcs.drawString(lbHannop + ": ", 20, line + 20);
        grphcs.drawString(lbLoaiTien + ": ", 20, line + 40);
        grphcs.drawString(lbHinhThuc + ": ", 20, line + 60);
        grphcs.drawString(lbThongTin, 20, line + 80);
        font = new Font("Serif", Font.PLAIN, 13);
        grphcs.setFont(font);
        grphcs.drawString(lbTenTK + ": ", 50, line + 100);
        grphcs.drawString(lbSTK + ": ", 50, line + 120);
        grphcs.drawString(lbTenNH + ": ", 50, line + 140);
        
        grphcs.drawString(tfHannop, 80, line + 20);
        grphcs.drawString(tfLoaiTien, 150, line + 40);
        grphcs.drawString(tfHinhThuc, 150, line + 60);
        lines = lines + 7;
        font = new Font("Serif", Font.BOLD, 13);
        grphcs.setFont(font);
        grphcs.drawString(tfTenTK, 140, line + 100);
        grphcs.drawString(tfSTK, 140, line + 120);
        grphcs.drawString(tfTenNH, 140, line + 140);
        font = new Font("Serif", Font.PLAIN, 13);
        grphcs.setFont(font);
        line = line + 140;
        
        font = new Font("Serif", Font.BOLD, 13);
        grphcs.setFont(font);
        grphcs.drawString(lbLuuy, 20, line + 40);
        line = line + 40;
        lines++;
        font = new Font("Serif", Font.PLAIN, 13);
        grphcs.setFont(font);
        linePerPage = 48;
        lines = linePerPage - lines;
            System.out.println("LINE PER PAGE" + linePerPage);
            System.out.println("LINE " + lines);
        if(lines > 0) {
            if(lines < linesOfLuuy.length) {
            for(int k = 0; k < lines; k++ ){
                grphcs.drawString(linesOfLuuy[k], 30, (line + (k + 1) * 20));
            }
            number = lines;
            } else {
                for(int k = 0; k < linesOfLuuy.length; k++ ){
                grphcs.drawString(linesOfLuuy[k], 30, (line + (k + 1) * 20));
                number = lines;
            }
            }
        }
//        if(linesOfLuuy.length >= 6) {
//        for(int k = 0 ; k < lines ; k++) {
//            
//            if(page == 1) {
//                break;
//            } else {
//                grphcs.drawString(linesOfLuuy[k], 30, (line + (k + 1) * 20));
//            }
//            
//        }
//        //if(page == 1) {
//          //  font = new Font("Serif", Font.PLAIN, 13);
//            //grphcs.setFont(font);
//            //System.out.println("Page break :  " + pageBreak);
//            //int line = 0;
//            //for(int k = 6 ; k < linesOfLuuy.length ; k++) {
//              //  grphcs.drawString(linesOfLuuy[k], 80, (40 + (line+ 1) * 20));
//               // line++;
//           // }
//        //}
//        } else {
//            System.err.println("sfsdfsfs");
//            for(int k = 0 ; k < linesOfLuuy.length ; k++) {
//                grphcs.drawString(linesOfLuuy[k], 30, (line + (k + 1) * 20));
//            }
//        }
        }
        if(page == 1) {
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        lines = number;
           System.err.println("LINE " + lines);
        Font font = new Font("Serif", Font.PLAIN, 13);
            grphcs.setFont(font);
            System.out.println("Page break :  " + pageBreak);
            int line = 0;
            if(lines < 0) {
                System.err.println("LINE " + lines);
                for(int k = 0; k < linesOfLuuy.length; k++) {
                    grphcs.drawString(linesOfLuuy[k], 30, (40 + (line + 1) * 20));
                    line++;
                }
            } else {
                if(lines < linesOfLuuy.length) {
            for(int m = lines ; m < linesOfLuuy.length ; m++) {
                grphcs.drawString(linesOfLuuy[m], 30, (40 + (line+ 1) * 20));
                line++;
            }
                }
            }
           
        }
        return PAGE_EXISTS;
    }
    
    public void editTextArea() {
        if(linesOfLuuy != null) {
        for(int i = 0 ; i < linesOfLuuy.length ; i++) {
            char[] linesChar;
            if(linesOfLuuy[i].length() >= 85) {
                linesChar = linesOfLuuy[i].toCharArray();
                int k = 1;
                for(int j = 0 ; j < linesChar.length ; j++) {
                    if(linesChar[j] == ' ' && j >= 85 * k) {
                        linesChar[j] = '\n';
                        k++;
                    }
                }
                linesOfLuuy[i] = String.valueOf(linesChar);
            }
        }
        lineOfLuuy = "";
        for(int i = 0 ; i < linesOfLuuy.length ; i++) {
            lineOfLuuy += linesOfLuuy[i] + "\n";
        }
        linesOfLuuy = lineOfLuuy.split("\\n");
    } else {
            linesOfLuuy = new String[1];
            linesOfLuuy[0] = " ";
        }
    }
    
    // convert from Icon to Image
    public Image convertToImage(Icon icon) {
        //getComponenets();
        if(icon instanceof ImageIcon) {
            return ((ImageIcon)icon).getImage();
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
    
    private String lbHannop;
    private String tfHannop;
    private String lbLoaiTien;
    private String tfLoaiTien;
    private String lbHinhThuc;
    private String tfHinhThuc;
    private String lbThongTin;
    private String lbTenTK;
    private String tfTenTK;
    private String lbSTK;
    private String tfSTK;
    private String lbTenNH;
    private String tfTenNH;
    private String lbLuuy;
    
    // get components of frame
    public void getComponenets() {
        iconOfLabel = jLabel1.getIcon();
        imageOfLabel = convertToImage(iconOfLabel);
        lbOfTT = lbTT1.getText();
        lbOfNamhoc = jLabel3.getText();
        lbNamHoc1 = String.valueOf(jComboBox1.getSelectedItem());
        lbNamHoc2 = String.valueOf(jComboBox2.getSelectedItem());
        lbNgay = jLabel5.getText();
        lbThang = jLabel6.getText();
        lbNam = jLabel7.getText();
        cbNgay = String.valueOf(jComboBox3.getSelectedItem());
        cbThang = String.valueOf(jComboBox4.getSelectedItem());
        cbNam = String.valueOf(jComboBox5.getSelectedItem());
        lbStraight = jLabel4.getText();
        lbHovaTen = jLabel8.getText();
        lbLop = jLabel9.getText();
        lbCoSo = jLabel10.getText();
        lbChuongTrinh = jLabel11.getText();
        tfHovaTen = tenHS.getText();
        tfLop = tenLop.getText();
        tfCoSo = tenCoSo.getText();
        tfChuongTrinh = tenChuongTrinh.getText();
        lbDVT = jLabel12.getText();
        cbDVT = String.valueOf(jComboBox6.getSelectedItem());
        //tableData = new Object[nRow][nCol];
        //tableData = getTableData(jTable1);
        getTableData(jTable1);
        lbTuanHoc = jLabel13.getText();
        lbTuan1 = jLabel14.getText();
        lbTuan2 = jLabel15.getText();
        lbTuan3 = jLabel16.getText();
        lbTuan4 = jLabel17.getText();
        lbTuan5 = jLabel18.getText();
        lbTuan6 = jLabel19.getText();
        /*
        tfTuan1 = jTextField5.getText();
        tfTuan2 = jTextField6.getText();
        tfTuan3 = jTextField7.getText();
        tfTuan4 = jTextField8.getText();
        tfTuan5 = jTextField9.getText();
        tfTuan6 = jTextField10.getText();
        */
        lbHannop = jLabel20.getText();
        lbLoaiTien = jLabel21.getText();
        lbHinhThuc = jLabel22.getText();
        lbThongTin = jLabel23.getText();
        lbTenTK = jLabel24.getText();
        lbSTK = jLabel25.getText();
        lbTenNH = jLabel26.getText();
        lbLuuy = jLabel27.getText();
        tfHannop = jTextField11.getText();
        tfLoaiTien = String.valueOf(jComboBox7.getSelectedItem());
        tfHinhThuc = String.valueOf(jComboBox8.getSelectedItem());
        tfTenTK = jTextField15.getText();
        tfSTK = jTextField16.getText();
        tfTenNH = jTextField17.getText();
        // luu y
        //int linesOfLuuy = jTextArea1.getLineCount();
        String area2 = jTextArea2.getText();
        String area1 = jTextArea1.getText();
        String area = area2 + "\n" + area1;
        //linesOfLuuy = jTextArea1.getText().split("\\n");
        linesOfLuuy = area.split("\\n");    
        //taLuuy = jTextArea1.getText();
    }
    
    // get table data
    public void getTableData(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        nRow = tableModel.getRowCount();
        nCol = tableModel.getColumnCount();
        
        //Object[][] data = new Object[nRow][nCol];
        tableData = new Object[nRow][nCol];
        for(int i = 0 ; i < nRow ; i++) {
            for(int j = 0 ; j < nCol ; j++) {
                //tableData[i][j] = tableModel.getValueAt(i, j);
                //System.out.println(data[i][j]);
                tableData[i][j] = tableModel.getValueAt(i, j);
                System.out.println(tableData[i][j]);
            }
        }
        
        //table data;
    }
    
    private String date = "";
    private String month = "";
    private String year = "";
    public void getCurrentDate() {
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DATE) + "";
        month = (calendar.get(Calendar.MONTH) + 1) + "";
        year = calendar.get(Calendar.YEAR) + "";
    }
}
