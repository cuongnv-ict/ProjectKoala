/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Dialog;

import DataBase.HocSinh.Get;
import edu.com.XuLy;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Venus
 */
public class ThemSuaHoanPhi extends javax.swing.JDialog {

    
    private boolean button;
    private boolean themorsua;
    private int oldIdStudents;
    private int idHoanPhi;
    /**
     * Creates new form ThemSuaHoanPhi
     */
    public ThemSuaHoanPhi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try
        {
        MakePopup();
        //add ten lop vao combobox
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
        }
        catch(Exception ex)
        {}
    }
    public ThemSuaHoanPhi(java.awt.Frame parent, boolean modal,Vector vector)
    {
        super(parent,modal);
        initComponents();
        try
        {
        MakePopup();
        //add ten lop vao combobox
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
     
        ten.setText(vector.get(1).toString());
        lop.setSelectedItem(vector.get(2).toString());
        tienxe.setText(vector.get(4).toString());
        String date = vector.get(5).toString();
        String a[];
        a=date.split("-");
        ngaybd.setSelectedItem(String.valueOf(Integer.parseInt(a[0])));
        thangbd.setSelectedItem(String.valueOf(Integer.parseInt(a[1])));
        nambd.setSelectedItem(String.valueOf(Integer.parseInt(a[2])));
        date = vector.get(6).toString();
        a= date.split("-");
        ngaykt.setSelectedItem(String.valueOf(Integer.parseInt(a[0])));
        thangkt.setSelectedItem(String.valueOf(Integer.parseInt(a[1])));
        namkt.setSelectedItem(String.valueOf(Integer.parseInt(a[2])));
        
        ghichu.setText(vector.get(7).toString());
            setOldIdStudents(vector.get(1).toString(), vector.get(2).toString());
        }
        catch(Exception ex)
            
        {}
    }
    public void setidxebus(int id)
    {
        this.idHoanPhi=id;
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

        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lop = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        tienxe = new javax.swing.JTextField();
        CancleButton = new javax.swing.JButton();
        OkButton = new javax.swing.JButton();
        thangbd = new javax.swing.JComboBox();
        ngaybd = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        nambd = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        namkt = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        ngaykt = new javax.swing.JComboBox();
        thangkt = new javax.swing.JComboBox();
        ten = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ghichu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Ngày");

        jLabel8.setText("Tên Lớp:");

        jLabel7.setText("Số Tiền:");

        tienxe.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tienxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tienxeActionPerformed(evt);
            }
        });

        CancleButton.setText("Cancle");
        CancleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancleButtonActionPerformed(evt);
            }
        });

        OkButton.setText("Ok");
        OkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OkButtonMouseClicked(evt);
            }
        });
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        thangbd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        ngaybd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel9.setText("Tháng");

        nambd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel10.setText("Năm");

        jLabel11.setText("Ngày Bắt Đầu:");

        jLabel13.setText("Ngày Kết Thúc:");

        jLabel12.setText("Ngày");

        jLabel14.setText("Năm");

        namkt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Thông Tin Hoàn Phí");

        ngaykt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        thangkt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        ten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel15.setText("Tháng");

        jLabel2.setText("Học Sinh:");

        ghichu.setColumns(20);
        ghichu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ghichu.setLineWrap(true);
        ghichu.setRows(5);
        ghichu.setMaximumSize(new java.awt.Dimension(20, 30));
        jScrollPane1.setViewportView(ghichu);

        jLabel5.setText("Ghi Chú:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CancleButton)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addComponent(namkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
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
                    .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel4)
                    .addComponent(ngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(thangbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nambd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(ngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(thangkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CancleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tienxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tienxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tienxeActionPerformed

    private void CancleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancleButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_CancleButtonActionPerformed

    private void OkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OkButtonMouseClicked
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
                    idstudent=new DataBase.SQLHoanPhi().getIdStudent(nameStudent, nameClass);
                    System.out.println("ma so là:" +idstudent);
                    //new DataBase.SQLXeBus().themxebus(idstudent,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(tienxe.getText()),diachi.getText());
                    if(idstudent!=0)
                    {
                        if((new DataBase.SQLHoanPhi().getIsActiveStudent(nameStudent, nameClass))==0)
                        {
                            new DataBase.SQLHoanPhi().themHoanPhi(idstudent, datebd, datekt, ghichu.getText(), Integer.parseInt(XuLy.getMoney(tienxe.getText())));
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
                        System.out.println("id xe bus la1:" + idHoanPhi);
                        new DataBase.SQLHoanPhi().suaHoanPhi(oldIdStudents, idstudent, idHoanPhi, datebd, datekt,ghichu.getText(), Integer.parseInt(XuLy.getMoney(tienxe.getText())));
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
    }//GEN-LAST:event_OkButtonMouseClicked

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        // TODO add your handling code here:
    //    try{
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
                    idstudent=new DataBase.SQLHoanPhi().getIdStudent(nameStudent, nameClass);
                    System.out.println("ma so là:" +idstudent);
                    //new DataBase.SQLXeBus().themxebus(idstudent,Integer.parseInt(luotdi.getSelectedItem().toString()),ghichu.getText() ,Integer.parseInt(tienxe.getText()),diachi.getText());
                    if(idstudent!=0)
                    {
                        if((new DataBase.SQLHoanPhi().getIsActiveStudent(nameStudent, nameClass))==0)
                        {
                            new DataBase.SQLHoanPhi().themHoanPhi(idstudent, datebd, datekt, ghichu.getText(),Integer.parseInt(XuLy.getMoney(tienxe.getText())));
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
                        new DataBase.SQLHoanPhi().suaHoanPhi(oldIdStudents, idstudent, idHoanPhi, datebd, datekt,ghichu.getText(), Integer.parseInt(XuLy.getMoney(tienxe.getText())));
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
    
//        }
/*
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Có Lỗi hệ thống bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

        }
  */  
    }//GEN-LAST:event_OkButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSuaHoanPhi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaHoanPhi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaHoanPhi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaHoanPhi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemSuaHoanPhi dialog = new ThemSuaHoanPhi(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton CancleButton;
    private javax.swing.JButton OkButton;
    private javax.swing.JTextArea ghichu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox lop;
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