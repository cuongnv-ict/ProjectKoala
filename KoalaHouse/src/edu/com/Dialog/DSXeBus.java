/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Dialog;


import DataBase.HocSinh.AStudentAndLateDay;
import DataBase.HocSinh.Get;
import DataBase.HocSinh.GetTotal;
import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Venus
 */
public class DSXeBus extends javax.swing.JDialog {

    private boolean button;
    private boolean themorsua;
    private int oldIdStudents;
    private int idxebus;
    /**
     * Creates new form DSXeBus
     */
    public DSXeBus(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        try
        {
   //            new Get().BangTrongMuon(bangDSTrongMuon);
        MakePopup();
        //add ten lop vao combobox
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
        }
        catch(Exception ex)
        {}
    
    }
    
    public DSXeBus(java.awt.Frame parent, boolean modal,Vector vector) {
        super(parent, modal);
        initComponents();
   //            new Get().BangTrongMuon(bangDSTrongMuon);
        try
        {
        MakePopup();
        //add ten lop vao combobox
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
        ten.setText(vector.get(1).toString());
        setSelectComboBox(lop, vector.get(2));
        setSelectComboBox(luotdi, vector.get(5));
//        setSelectComboBox(hocky, vector.get(7));
        String datebd=vector.get(7).toString();
        String[] a = datebd.split("-");
        //result = a[2]+"-"+a[1]+"-"+a[0];
        setSelectComboBox(ngaybd, Integer.toString(Integer.parseInt(a[0])));
        setSelectComboBox(thangbd, Integer.toString(Integer.parseInt(a[1])));
        setSelectComboBox(nambd, a[2]);
        
        String datekt=vector.get(8).toString();
        String[] b= datekt.split("-");
        setSelectComboBox(ngaykt, Integer.toString(Integer.parseInt(b[0])));
        setSelectComboBox(thangkt, Integer.toString(Integer.parseInt(b[1])));
        setSelectComboBox(namkt, b[2]);
        
        
        diachi.setText(vector.get(3).toString());
        tienxe.setText(vector.get(6).toString());
        ghichu.setText(vector.get(9).toString());
        setOldIdStudents(vector.get(1).toString(),vector.get(2).toString());
    }
        catch(Exception ex)
        {}
    
    }
    public void setidxebus(int id)
    {
        this.idxebus=id;
    }
    public void setOldIdStudents(String nameStudent,String nameclass)
    {
        int idstudent;
        idstudent=new DataBase.SQLXeBus().getIdStudent(nameStudent, nameclass);
        this.oldIdStudents=idstudent;
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
    
    private void setSelectComboBox(JComboBox x,Object obj){
        for(int i =0;i<x.getItemCount();i++){
            if(x.getItemAt(i).toString().equalsIgnoreCase(obj.toString())){
              x.setSelectedIndex(i);
              //trungtam.setEnabled(false);
              break;
            } 
        }     
    }
    public void MakePopup(){
         AutoSuggestor autoSuggestor = new AutoSuggestor(ten, this, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {

                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
                Get get = new Get();
                String tenlop = lop.getSelectedItem().toString();
                ArrayList words = get.GetNameStudent(tenlop);
                setDictionary(words);
                //addToDictionary("bye");//adds a single word

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
    }
    public boolean sosanhngaythang( String ngaybd, String ngaykt)
    {
        String []bd;
        String []kt;
        kt = ngaykt.split("-");
        bd = ngaybd.split("-");
        int datebd= Integer.parseInt(bd[2]);
        int monthbd= Integer.parseInt(bd[1]);
        int yearbd= Integer.parseInt(bd[0]);
        int datekt = Integer.parseInt(kt[2]);
        int monthkt= Integer.parseInt(kt[1]);
        int yearkt= Integer.parseInt(kt[0]);
        if(yearkt>yearbd) return true;
        else if(yearkt==yearbd&&monthkt>monthbd) return true;
        else if(yearkt==yearbd&&monthkt==monthbd&&datekt>datebd) return true;
        else return false;
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
        jLabel2 = new javax.swing.JLabel();
        ten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        luotdi = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ghichu = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        diachi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lop = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        ngaybd = new javax.swing.JComboBox();
        thangbd = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        nambd = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        namkt = new javax.swing.JComboBox();
        ngaykt = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        thangkt = new javax.swing.JComboBox();
        tienxe = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Thông Tin Xe Bus");

        jLabel2.setText("Học Sinh:");

        ten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setText("Số Lượt Đi:");

        luotdi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));

        jLabel5.setText("Ghi Chú:");

        ghichu.setColumns(20);
        ghichu.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        ghichu.setRows(5);
        jScrollPane1.setViewportView(ghichu);

        jButton1.setText("Cancle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ok");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Địa Chỉ:");

        diachi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        diachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diachiActionPerformed(evt);
            }
        });

        jLabel7.setText("Tiền Xe:");

        jLabel8.setText("Tên Lớp:");

        jLabel4.setText("Ngày");

        ngaybd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        thangbd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel9.setText("Tháng");

        nambd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel10.setText("Năm");

        jLabel11.setText("Ngày Bắt Đầu:");

        jLabel13.setText("Ngày Kết Thúc:");

        jLabel12.setText("Ngày");

        jLabel14.setText("Năm");

        namkt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        ngaykt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel15.setText("Tháng");

        thangkt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        tienxe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tienxe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tienxeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(diachi)
                            .addComponent(tienxe))
                        .addGap(87, 87, 87)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(luotdi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ten, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addComponent(lop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thangbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nambd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thangkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(namkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lop, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(luotdi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(thangbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nambd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(ngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(thangkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(namkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void diachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diachiActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        try{
        if(themorsua)
        {
            int idstudent;
            String nameStudent,nameClass;
            nameStudent=ten.getText();
            nameClass= lop.getSelectedItem().toString();
            String datebd = nambd.getSelectedItem().toString() +"-"+ thangbd.getSelectedItem().toString() +"-"+ngaybd.getSelectedItem().toString();
            String datekt = namkt.getSelectedItem().toString() +"-"+ thangkt.getSelectedItem().toString() +"-"+ngaykt.getSelectedItem().toString();
            if(sosanhngaythang(datebd, datekt))
            {
            idstudent=new DataBase.SQLXeBus().getIdStudent(nameStudent, nameClass);
            System.out.println("ma so là:" +idstudent);
            //new DataBase.SQLXeBus().themxebus(idstudent,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(tienxe.getText()),diachi.getText());
            if(idstudent!=0)
            {
                if((new DataBase.SQLXeBus().getIsActiveStudent(nameStudent, nameClass))==0)
                {
                DataBase.SQLXeBus data = new DataBase.SQLXeBus();
                data.themxebus(idstudent,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(XuLy.getMoney(tienxe.getText())),diachi.getText(),datebd,datekt);
                button=true;
                dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Bạn không thể thêm học sinh đã có trong danh sách và chưa được thanh toán. \n Để thêm học sinh này cần xóa học sinh cũ hoặc thanh toán tiền còn đang nợ!",null,JOptionPane.INFORMATION_MESSAGE);

                }
            }
            else
            {
                
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tên học sinh bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

                
            }
            }
            else
            {
                            JOptionPane.showMessageDialog(null,"Có Lỗi ngày tháng, bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

            }
        }
        else
        {
            int idstudent;
            String nameStudent,nameClass;
            nameStudent=ten.getText();
            nameClass= lop.getSelectedItem().toString();
            
            idstudent=new DataBase.SQLXeBus().getIdStudent(nameStudent, nameClass);
            System.out.println("ma so là:" +idstudent);
            String datebd = nambd.getSelectedItem().toString() +"-"+ thangbd.getSelectedItem().toString() +"-"+ngaybd.getSelectedItem().toString();
            String datekt = namkt.getSelectedItem().toString() +"-"+ thangkt.getSelectedItem().toString() +"-"+ngaykt.getSelectedItem().toString();
            if(sosanhngaythang(datebd, datekt))
            {
            if(idstudent!=0)
            {
            System.out.println("id xe bus la1:" +idxebus);
            DataBase.SQLXeBus data = new DataBase.SQLXeBus();
            data.suaxebus(oldIdStudents,idstudent ,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(XuLy.getMoney(tienxe.getText())),diachi.getText(),datebd,datekt,idxebus);
            button=true;
            dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Có Lỗi thông tin học sinh, bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
   
            }
            }
            else
            {
                                JOptionPane.showMessageDialog(null,"Có Lỗi ngày tháng, bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

            }
        }
        }
        
        catch(Exception ex)
        {
                            JOptionPane.showMessageDialog(null,"Có Lỗi hệ thống bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                try{
        if(themorsua)
        {
            int idstudent;
            String nameStudent,nameClass;
            nameStudent=ten.getText();
            nameClass= lop.getSelectedItem().toString();
            String datebd = nambd.getSelectedItem().toString() +"-"+ thangbd.getSelectedItem().toString() +"-"+ngaybd.getSelectedItem().toString();
            String datekt = namkt.getSelectedItem().toString() +"-"+ thangkt.getSelectedItem().toString() +"-"+ngaykt.getSelectedItem().toString();
            if(sosanhngaythang(datebd, datekt))
            {
            idstudent=new DataBase.SQLXeBus().getIdStudent(nameStudent, nameClass);
            System.out.println("ma so là:" +idstudent);
            //new DataBase.SQLXeBus().themxebus(idstudent,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(tienxe.getText()),diachi.getText());
            if(idstudent!=0)
            {
                if((new DataBase.SQLXeBus().getIsActiveStudent(nameStudent, nameClass))==0)
                {
                DataBase.SQLXeBus data = new DataBase.SQLXeBus();
                data.themxebus(idstudent,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(XuLy.getMoney(tienxe.getText())),diachi.getText(),datebd,datekt);
                button=true;
                dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Bạn không thể thêm học sinh đã có trong danh sách và chưa được thanh toán. \n Để thêm học sinh này cần xóa học sinh cũ hoặc thanh toán tiền còn đang nợ!",null,JOptionPane.INFORMATION_MESSAGE);

                }
            }
            else
            {
                
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tên học sinh bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

                
            }
            }
            else
            {
                            JOptionPane.showMessageDialog(null,"Có Lỗi ngày tháng, bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

            }
        }
        else
        {
            int idstudent;
            String nameStudent,nameClass;
            nameStudent=ten.getText();
            nameClass= lop.getSelectedItem().toString();
            
            idstudent=new DataBase.SQLXeBus().getIdStudent(nameStudent, nameClass);
            System.out.println("ma so là:" +idstudent);
            String datebd = nambd.getSelectedItem().toString() +"-"+ thangbd.getSelectedItem().toString() +"-"+ngaybd.getSelectedItem().toString();
            String datekt = namkt.getSelectedItem().toString() +"-"+ thangkt.getSelectedItem().toString() +"-"+ngaykt.getSelectedItem().toString();
            if(sosanhngaythang(datebd, datekt))
            {
            if(idstudent!=0)
            {
            System.out.println("id xe bus la1:" +idxebus);
            DataBase.SQLXeBus data = new DataBase.SQLXeBus();
            data.suaxebus(oldIdStudents,idstudent ,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(XuLy.getMoney(tienxe.getText())),diachi.getText(),datebd,datekt,idxebus);
            button=true;
            dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Có Lỗi thông tin học sinh, bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
   
            }
            }
            else
            {
                                JOptionPane.showMessageDialog(null,"Có Lỗi ngày tháng, bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

            }
        }
        }
        
        catch(Exception ex)
        {
                            JOptionPane.showMessageDialog(null,"Có Lỗi hệ thống bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tienxeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tienxeKeyReleased
        // TODO add your handling code here:
        
                String text = tienxe.getText();
                tienxe.setText(XuLy.setMoney(text));
        
    }//GEN-LAST:event_tienxeKeyReleased

    
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
            java.util.logging.Logger.getLogger(DSXeBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DSXeBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DSXeBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DSXeBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DSXeBus dialog = null;
                try {
                    dialog = new DSXeBus(new javax.swing.JFrame(), true);
                } catch (ParseException ex) {
                    Logger.getLogger(DSXeBus.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JTextField diachi;
    private javax.swing.JTextArea ghichu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox lop;
    private javax.swing.JComboBox luotdi;
    private javax.swing.JComboBox nambd;
    private javax.swing.JComboBox namkt;
    private javax.swing.JComboBox ngaybd;
    private javax.swing.JComboBox ngaykt;
    private javax.swing.JTextField ten;
    private javax.swing.JComboBox thangbd;
    private javax.swing.JComboBox thangkt;
    private javax.swing.JTextField tienxe;
    // End of variables declaration//GEN-END:variables
}