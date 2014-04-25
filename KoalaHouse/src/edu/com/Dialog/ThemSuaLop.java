/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Dialog;

import DataBase.DataTable;
import edu.com.ThongTin;
import java.awt.TextField;
import java.awt.Toolkit;
import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyen van cuong
 */
public class ThemSuaLop extends javax.swing.JDialog {
    private boolean button;
    private boolean themorsua;//cho biet la them lop hay sua lop
    private String oldName;
    public int idtrungtam;
    /**
     * Creates new form ThemSuaLop
     */
    public ThemSuaLop(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
        trungtam.setSelectedIndex(ThongTin.trungtam-1);
        trungtam.setEnabled(false);

    }
     public ThemSuaLop(java.awt.Frame parent, boolean modal,Vector vector) {
        super(parent, modal);
        initComponents();
        Object B_date[] = vector.get(5).toString().split("-");
        Object E_date[] = vector.get(6).toString().split("-");
        trangthai.setEnabled(true);
        ten.setText(vector.get(0).toString());
        giaovien.setText(vector.get(4).toString());
        sohs.setValue(Integer.parseInt(vector.get(8).toString()));
        setSelectComboBox(ky, vector.get(3));
        setSelectComboBox(trangthai, vector.get(9));
        setSelectComboBox(trinhdo, vector.get(2)); 
        setSelectComboBox(B_day, B_date[0]);
        setSelectComboBox(B_month, B_date[1]);
        setSelectComboBox(B_year, B_date[2]);
        setSelectComboBox(E_day, B_date[0]);
        setSelectComboBox(E_month, B_date[1]);
        setSelectComboBox(E_year, B_date[2]);
        DongY.setText("Chỉnh sửa");
        title.setText("Chỉnh sửa thông tin lớp");
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
     }
    private void setSelectComboBox(JComboBox x,Object obj){
        for(int i =0;i<x.getItemCount();i++){
            if(x.getItemAt(i).toString().equalsIgnoreCase(obj.toString())){
              x.setSelectedIndex(i);
              trungtam.setEnabled(false);
              break;
            } 
        }     
    }
    //set gia tri
    public void setKhoiName(String khoiname)
    {
        int id=0;
        if(khoiname.equals("NẮNG MAI (SUNSHINE)")) id=0;
        else if(khoiname.equals("TỔ ONG (BEEHIVE)")) id=1;
        else if(khoiname.equals("TỔ KÉN (CHRYSALIS)")) id=2;
        else  id=3;
        this.trinhdo.setSelectedIndex(id);
    }
    public void setOldName(String oldname)
    {
        this.oldName=oldname;
    }
    public void setThemSuaLop(boolean a)
    {
        this.themorsua=a;
    }
    //get cac gia tri 
    public boolean getButton()
    {
        return button;
    }
    public String getTenLop()
    {
        return ten.getText();
    }
    public String getGiaoVien()
    {
        return giaovien.getText();
    }
    public String getHocKy()
    {
        return ky.getSelectedItem().toString();
    }
    public String getKhoiHoc()
    {
        return trinhdo.getSelectedItem().toString();
    }
    public String getTrungTam()
    {
        return trungtam.getSelectedItem().toString();
    }
    public String getSoHS()
    {
        return sohs.getValue().toString();
    }
    public String getBday(){
        return B_year.getSelectedItem().toString()+"-"+B_month.getSelectedItem().toString()+"-"+B_day.getSelectedItem().toString();
    }
    public String getEday(){
        return E_year.getSelectedItem().toString()+"-"+E_month.getSelectedItem().toString()+"-"+E_day.getSelectedItem().toString();
    }
    public String getTrangThai(){
        return trangthai.getSelectedItem().toString();
    }
    //kiem tra ngay ket thuc va ngay bat dau
    public boolean kiemtrangaythang()
    {   
        if(B_day.getSelectedItem().toString().equals("Ngày")||B_month.getSelectedItem().toString().equals("Tháng")||B_year.getSelectedItem().toString().equals("Năm")) return false;
        if(E_day.getSelectedItem().toString().equals("Ngày")||E_month.getSelectedItem().toString().equals("Tháng")||E_year.getSelectedItem().toString().equals("Năm")) return false;
        int ngaybd,thangbd,nambd,ngaykt,thangkt,namkt;
        ngaybd=Integer.parseInt(B_day.getSelectedItem().toString());
        thangbd=Integer.parseInt(B_month.getSelectedItem().toString());
        nambd=Integer.parseInt(B_year.getSelectedItem().toString());
        ngaykt=Integer.parseInt(E_day.getSelectedItem().toString());
        thangkt=Integer.parseInt(E_month.getSelectedItem().toString());
        namkt=Integer.parseInt(E_year.getSelectedItem().toString());
        
        if(namkt>nambd) return true;
        else if (namkt<nambd) return false;
        else if (namkt==nambd&&thangkt>thangbd) return true;
        else if (namkt==nambd&&thangkt<thangbd) return false;
        else if (namkt==nambd&&thangkt==thangbd&&ngaykt>ngaybd) return true;
        
        return false;
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
        jLabel2 = new javax.swing.JLabel();
        giaovien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sohs = new javax.swing.JSpinner();
        trinhdo = new javax.swing.JComboBox();
        ky = new javax.swing.JComboBox();
        trungtam = new javax.swing.JComboBox();
        DongY = new javax.swing.JButton();
        HuyBo = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        B_day = new javax.swing.JComboBox();
        B_month = new javax.swing.JComboBox();
        B_year = new javax.swing.JComboBox();
        E_month = new javax.swing.JComboBox();
        E_day = new javax.swing.JComboBox();
        E_year = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        trangthai = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tên Lớp");

        jLabel2.setText("Giáo Viên");

        jLabel3.setText("Trung Tâm");

        jLabel4.setText("Tổng Học Sinh");

        jLabel5.setText("Kì Học");

        jLabel6.setText("Khối Học");

        sohs.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        trinhdo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NẮNG MAI (SUNSHINE)", "TỔ ONG (BEEHEVE)", "TỔ KÉN (CHRYSARYS)", "MẪU GIÁO (KINDERGENTEN)" }));

        ky.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        trungtam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Koala House Bà Triệu", "Koala House Hoàng Ngân", "Koala House Phan Kế Bình", "Koala House Nguyễn Huy Tự" }));

        DongY.setText("Thêm lớp");
        DongY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DongYMouseClicked(evt);
            }
        });

        HuyBo.setText("Hủy bỏ");
        HuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HuyBoMouseClicked(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Thêm lớp học");

        B_day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        B_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tháng", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        B_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        E_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tháng", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        E_day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        jLabel8.setText("Ngày bắt đầu");

        jLabel9.setText("Ngày kết thúc");

        trangthai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang giảng dạy", "Đã kết thúc" }));
        trangthai.setEnabled(false);

        jLabel10.setText("Trạng thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ten)
                            .addComponent(sohs, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(giaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(DongY, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(10, 10, 10)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(trangthai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(trungtam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(trinhdo, 0, 1, Short.MAX_VALUE)
                                                .addComponent(ky, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(E_day, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(E_month, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(B_day, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B_month, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(B_year, 0, 67, Short.MAX_VALUE)
                            .addComponent(E_year, 0, 67, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(trinhdo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(giaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(ky, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(sohs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trungtam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_day, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_month, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_year, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(E_day, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_year, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_month, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DongY, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DongYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DongYMouseClicked
        // TODO add your handling code here:
        boolean kiemtrangay=kiemtrangaythang();
       if(kiemtrangay==true)
       {
        DataBase.DataTable a = new DataTable();
        String tenlop= ten.getText();
        String tengiaovien=giaovien.getText();
        String hocky=ky.getSelectedItem().toString();
        String tentrinhdo=trinhdo.getSelectedItem().toString();
        String tentrungtam=trungtam.getSelectedItem().toString();
        String tonghs=sohs.getValue().toString();
        String ngaybd=B_year.getSelectedItem().toString()+"-"+B_month.getSelectedItem().toString()+"-"+B_day.getSelectedItem().toString();
        String ngaykt=E_year.getSelectedItem().toString()+"-"+E_month.getSelectedItem().toString()+"-"+E_day.getSelectedItem().toString();
        String tentrangthai=trangthai.getSelectedItem().toString();
        //doi ten khoi thanh so
        if(tentrinhdo.equals("NẮNG MAI (SUNSHINE)")) tentrinhdo="1";
        else if(tentrinhdo.equals("TỔ ONG (BEEHEVE)")) tentrinhdo="2";
        else if(tentrinhdo.equals("TỔ KÉN (CHRYSARYS)")) tentrinhdo="3";
        else tentrinhdo="4";
        //doi ten trung tam thanh so 
        if(tentrungtam.equals("Koala House Bà Triệu")) tentrungtam="1";
        else if (tentrungtam.equals("Koala House Hoàng Ngân")) tentrungtam="2";
        else if (tentrungtam.equals("Koala House Phan Kế Bình")) tentrungtam="3";
        else tentrungtam="4";
        //doi ten trang thai sang so
        this.idtrungtam=Integer.parseInt(tentrungtam);
        if(tentrangthai.equals("Đang giảng dạy")) tentrangthai="0";
        else tentrangthai="1";
        // kiem tra xem la co cho nao chua dien khong 
        if(tengiaovien!=null&&tenlop!=null)
        {
            if(themorsua==true)//them lop
            {
                  DataTable data= new DataTable();
                  if(data.ThemLop(tentrungtam, hocky, tentrinhdo, tenlop, tengiaovien, ngaybd, ngaykt, tonghs))
                    {
                        String message =String.format( "Bạn đã tạo thành công lớp mới");
                        JOptionPane.showMessageDialog( null, message );
                        button=true;
                        dispose();
                    }
            }
            else
            {
                    DataTable data= new DataTable();
                    if(data.SuaLop(oldName,idtrungtam ,tentrungtam, hocky, tentrinhdo, tenlop, tengiaovien, ngaybd, ngaykt, tonghs, tentrangthai))
                    {
                        String message =String.format( "Bạn đã chinh sua thanh cong");
                        JOptionPane.showMessageDialog( null, message );
                        button=true;
                        dispose();
                    }
                    
             
            }
        }    
        else
        {
                String message =String.format( "lỗi tạo lớp, mời bạn xem lại dữ liệu");
                JOptionPane.showMessageDialog( null, message );
        }
        
       }
       else 
       {        
                 String message =String.format( "lỗi tạo lớp phần ngày tháng, mới bạn xem lại");
                JOptionPane.showMessageDialog( null, message );
       
       }
    }//GEN-LAST:event_DongYMouseClicked

    private void HuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HuyBoMouseClicked
        // TODO add your handling code here:
        button=false;
        dispose();
    }//GEN-LAST:event_HuyBoMouseClicked

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
            java.util.logging.Logger.getLogger(ThemSuaLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemSuaLop dialog = new ThemSuaLop(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox B_day;
    private javax.swing.JComboBox B_month;
    private javax.swing.JComboBox B_year;
    private javax.swing.JButton DongY;
    private javax.swing.JComboBox E_day;
    private javax.swing.JComboBox E_month;
    private javax.swing.JComboBox E_year;
    private javax.swing.JButton HuyBo;
    private javax.swing.JTextField giaovien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox ky;
    private javax.swing.JSpinner sohs;
    private javax.swing.JTextField ten;
    private javax.swing.JLabel title;
    private javax.swing.JComboBox trangthai;
    private javax.swing.JComboBox trinhdo;
    private javax.swing.JComboBox trungtam;
    // End of variables declaration//GEN-END:variables
}
