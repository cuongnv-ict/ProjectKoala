/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Dialog;

import DataBase.HocSinh.Get;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Venus
 */
public class ThemSuaDKHocHe extends javax.swing.JDialog {

    public AutoSuggestor autoSuggestor1;
    public AutoSuggestor autoSuggestor;
    private int idhoche;
    private boolean button;
    private boolean themorsua;
    private int oldIdStudents;
    private int tongsotuan=0;
    /**
     * Creates new form ThemSuaDKHocHe
     */
    public ThemSuaDKHocHe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try{
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
        addComponentListener(new ComponentAdapter() {
               
            @Override
            public void componentMoved(ComponentEvent e) {
                autoSuggestor.showPopUpWindow2();
                super.componentMoved(e); //To change body of generated methods, choose Tools | Templates.
            }
                
        });
        MakePopup();
        }
        catch(Exception ex)
        {}
    }
    public ThemSuaDKHocHe(java.awt.Frame parent, boolean modal,Vector vector) {
        super(parent, modal);
        initComponents();
   //            new Get().BangTrongMuon(bangDSTrongMuon);
        try{
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
        addComponentListener(new ComponentAdapter() {
               
            @Override
            public void componentMoved(ComponentEvent e) {
                autoSuggestor.showPopUpWindow2();
                super.componentMoved(e); //To change body of generated methods, choose Tools | Templates.
            }
                
        });
        MakePopup();
        setOldIdStudents(vector.get(1).toString(),vector.get(2).toString());
        ten.setText(vector.get(1).toString());
        lop.setSelectedItem(vector.get(2).toString());
        tuanhoc.setText(vector.get(3).toString());
    }
        catch(Exception ex)
        {}
    
    }
    public void setIdhoche(int id)
    {
        this.idhoche=id;
    }
    public void setOldIdStudents(String nameStudent,String nameclass)
    {
        int idstudent;
        idstudent=new DataBase.SQLXeBus().getIdStudent(nameStudent, nameclass);
        this.oldIdStudents=idstudent;
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
    private boolean kiemtratuanhoc(String a)
    {
        a+=",";
        a+="/";
        String []chuoi;
        chuoi= a.split(",");
        int len=a.length();
        if(len==1)
        {
                try{
                    if(Integer.parseInt(chuoi[0])>8) return false;
                   tongsotuan=1;
                    return true;
                
                }
                catch(Exception ex)
                {
                    return false;
                }
        }
        else
        {
            for(int i=0;i<(len/2)+1;i++)
            {
                if(i>8) return false;
                if(chuoi[i].equals("/")) break;
                try{
                      
                    int so=Integer.parseInt(chuoi[i]);
                    for(int j=0;j<i;j++)
                    {
                       if (chuoi[i].equals(chuoi[j])) return false;
                    }
                    if(so>6||so<1)
                    {
                        
                        return false;
                    }
                    
                }
                catch(Exception ex)
                {
                    return false;
                }
                this.tongsotuan+=1;
            }
        }    
        return true;
    }
    public void setThemSuaLop(boolean a)
    {
        this.themorsua=a;
    }

    public void MakePopup(){
             autoSuggestor = new AutoSuggestor(ten, this, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        lop = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        tuanhoc = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ten = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setText("Tên Lớp:");

        tuanhoc.setText("chú ý các tuần cách nhau dấu \",\" ví dụ : 1,2");
        tuanhoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuanhocActionPerformed(evt);
            }
        });
        tuanhoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tuanhocFocusGained(evt);
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

        jButton1.setText("Cancle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Tuần học:");

        jLabel2.setText("Học Sinh:");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Đăng Ký Học Hè");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(tuanhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ten, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(lop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tuanhoc, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tuanhocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuanhocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tuanhocActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
         try{
            if(themorsua)
            {
                if(kiemtratuanhoc(tuanhoc.getText()))
                {
                    int idstudent;
                    String nameStudent,nameClass;
                    nameStudent=ten.getText();
                    nameClass= lop.getSelectedItem().toString();
                    if((new DataBase.SQLkyhe().getIsActiveStudent(nameStudent,nameClass))==0)
                    {
                        
                        if(new DataBase.SQLkyhe().checkSemester())
                        {
                            if(new DataBase.SQLkyhe().getIdStudent(ten.getText()+"", lop.getSelectedItem().toString())>0)
                            {
                                DataBase.SQLkyhe data = new DataBase.SQLkyhe();
                                idstudent=new DataBase.SQLkyhe().getIdStudent(nameStudent, nameClass);
                                
                                data.themDkHocHe(idstudent, tuanhoc.getText(), tongsotuan,String.valueOf(new DataBase.SQLkyhe().getYearActiv()+1));

                                dispose();
                            }
                            else
                            {
                                 JOptionPane.showMessageDialog(null,"Học sinh trên không có trong danh sách lớp",null,JOptionPane.INFORMATION_MESSAGE);
                                 tongsotuan=0;
                    
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Bạn cần thiết lập tuần trong năm nay trước khi thêm học sinh!",null,JOptionPane.INFORMATION_MESSAGE);
                            tongsotuan=0;
                    
                        }
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"Học sinh đã được đăng ký, làm ơn xóa đăng ký hoặc ấn sửa",null,JOptionPane.INFORMATION_MESSAGE);
                         tongsotuan=0;
                    }
                    
                
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tuần học bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
                    tongsotuan=0;
                }
            }
            else
            {
                if(kiemtratuanhoc(tuanhoc.getText()))
                {
                    
                int idstudent;
                String nameStudent,nameClass;
                nameStudent=ten.getText();
                nameClass= lop.getSelectedItem().toString();
                DataBase.SQLkyhe data = new DataBase.SQLkyhe();
                idstudent=new DataBase.SQLkyhe().getIdStudent(nameStudent, nameClass);
                if(idstudent!=0)
                {
                    if(new DataBase.SQLkyhe().getIdStudent(ten.getText()+"", lop.getSelectedItem().toString())>0)
                    {
                    data.suadkhoche(oldIdStudents, idstudent, tuanhoc.getText(),tongsotuan,idhoche);
                    dispose();
                    }
                    else
                    {
                                 JOptionPane.showMessageDialog(null,"Học sinh trên không có trong danh sách lớp",null,JOptionPane.INFORMATION_MESSAGE);
                                 tongsotuan=0;
                    
                    }
                            
                }
                else
                {
                    
                
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tuần học bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
                    tongsotuan=0;
                
                }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tuần học bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
                    tongsotuan=0;
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Có Lỗi hệ thống bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
            tongsotuan=0;
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try{
            if(themorsua)
            {
                if(kiemtratuanhoc(tuanhoc.getText()))
                {
                    int idstudent;
                    String nameStudent,nameClass;
                    nameStudent=ten.getText();
                    nameClass= lop.getSelectedItem().toString();
                    if((new DataBase.SQLkyhe().getIsActiveStudent(nameStudent,nameClass))==0)
                    {
                        
                        if(new DataBase.SQLkyhe().checkSemester())
                        {
                            if(new DataBase.SQLkyhe().getIdStudent(ten.getText()+"", lop.getSelectedItem().toString())>0)
                            {
                                DataBase.SQLkyhe data = new DataBase.SQLkyhe();
                                idstudent=new DataBase.SQLkyhe().getIdStudent(nameStudent, nameClass);
                                
                                data.themDkHocHe(idstudent, tuanhoc.getText(), tongsotuan,String.valueOf(new DataBase.SQLkyhe().getYearActiv()+1));

                                dispose();
                            }
                            else
                            {
                                 JOptionPane.showMessageDialog(null,"Học sinh trên không có trong danh sách lớp",null,JOptionPane.INFORMATION_MESSAGE);
                                 tongsotuan=0;
                    
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Bạn cần thiết lập tuần trong năm nay trước khi thêm học sinh!",null,JOptionPane.INFORMATION_MESSAGE);
                            tongsotuan=0;
                    
                        }
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"Học sinh đã được đăng ký, làm ơn xóa đăng ký hoặc ấn sửa",null,JOptionPane.INFORMATION_MESSAGE);
                         tongsotuan=0;
                    }
                    
                
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tuần học bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
                    tongsotuan=0;
                }
            }
            else
            {
                if(kiemtratuanhoc(tuanhoc.getText()))
                {
                    
                int idstudent;
                String nameStudent,nameClass;
                nameStudent=ten.getText();
                nameClass= lop.getSelectedItem().toString();
                DataBase.SQLkyhe data = new DataBase.SQLkyhe();
                idstudent=new DataBase.SQLkyhe().getIdStudent(nameStudent, nameClass);
                if(idstudent!=0)
                {
                    if(new DataBase.SQLkyhe().getIdStudent(ten.getText()+"", lop.getSelectedItem().toString())>0)
                    {
                    data.suadkhoche(oldIdStudents, idstudent, tuanhoc.getText(),tongsotuan,idhoche);
                    dispose();
                    }
                    else
                    {
                                 JOptionPane.showMessageDialog(null,"Học sinh trên không có trong danh sách lớp",null,JOptionPane.INFORMATION_MESSAGE);
                                 tongsotuan=0;
                    
                    }
                            
                }
                else
                {
                    
                
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tuần học bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
                    tongsotuan=0;
                
                }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tuần học bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
                    tongsotuan=0;
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Có Lỗi hệ thống bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);
            tongsotuan=0;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tuanhocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tuanhocFocusGained
        // TODO add your handling code here:
        tuanhoc.setText("1,2,3,4,5,6");
    }//GEN-LAST:event_tuanhocFocusGained

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
            java.util.logging.Logger.getLogger(ThemSuaDKHocHe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaDKHocHe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaDKHocHe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaDKHocHe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemSuaDKHocHe dialog = new ThemSuaDKHocHe(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JComboBox lop;
    private javax.swing.JTextField ten;
    private javax.swing.JTextField tuanhoc;
    // End of variables declaration//GEN-END:variables
}
