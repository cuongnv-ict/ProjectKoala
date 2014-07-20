/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Dialog;

import DataBase.HocSinh.Get;
import edu.com.CloseButton.CloseTabButton;
import edu.com.Panel.HocSinhA;
import edu.com.ThongTin;
import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Venus
 */
public class NhapNghiPhep extends javax.swing.JDialog {

    public JTabbedPane center;
    ArrayList<Integer> idNghiPhep;
    DataBase.HocSinh.NghiPhep a;
    /**
     * Creates new form NhapNghiPhep
     */
    public NhapNghiPhep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        a= new DataBase.HocSinh.NghiPhep();
        new DataBase.SQLBangNghiPhep().BangNghiPhep(bangDSNghỉPhep);
        idNghiPhep = new ArrayList<Integer>();
        try{
        if (!bangDSNghỉPhep.getValueAt(0, 0).toString().equals("")) {
            XuLy.setID(idNghiPhep, bangDSNghỉPhep, 0);
            //resize(jTable4);
        }
        }
        catch(Exception ex)
        {}
        MakePopup();
        //add ten lop vao combobox
        ArrayList nameClasses = new Get().GetNameClass();
        for(int i=0;i<nameClasses.size();i++)
            lop.addItem(nameClasses.get(i));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangDSNghỉPhep = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lop = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ngay = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        thang = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        nam = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ngay1 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        thang1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        nam1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        songayngay = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ten = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bangDSNghỉPhep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên HS", "Lớp", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Số Ngày"
            }
        ));
        bangDSNghỉPhep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangDSNghỉPhepMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bangDSNghỉPhep);

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lopActionPerformed(evt);
            }
        });

        jLabel3.setText("Lớp:");

        jLabel4.setText("Ngày bắt đầu:");

        ngay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel5.setText("Tháng");

        thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel6.setText("Năm");

        nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel7.setText("Ngày:");

        jLabel9.setText("Ngày bắt đầu:");

        jLabel10.setText("Ngày:");

        ngay1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel11.setText("Tháng");

        thang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel12.setText("Năm");

        nam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel13.setText("Số Ngày Nghỉ:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(songayngay)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lop, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(songayngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("    Nhập Ngày Nghỉ Phép");

        ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setText("Tên HS:");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/xoa.jpg"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bangDSNghỉPhepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangDSNghỉPhepMouseClicked
        if(evt.getClickCount()==2){
            //map cac truong
            //tinh id hoc sinh
            String tenHocSinh = bangDSNghỉPhep.getValueAt(bangDSNghỉPhep.getSelectedRow(), 1).toString();
            String classes = bangDSNghỉPhep.getValueAt(bangDSNghỉPhep.getSelectedRow(), 2).toString();
            ArrayList infoHS = new Get().GetIdAndFacStudent(tenHocSinh, classes);
            int idHS = Integer.parseInt(infoHS.get(0).toString());

            //HocSinhA.idTemp = id;
            int a=-1;
            for (int i = 0; i < center.getTabCount(); i++) {
                if (tenHocSinh.equals(center.getTitleAt(i))) {
                    a += 1;
                    center.setSelectedIndex(i);
                }
            }
            if (a == -1) {

                HocSinhA aa = new HocSinhA(idHS);
                //add tab

                center.add(tenHocSinh, aa);
                center.setSelectedComponent(aa);
                new CloseTabButton(center,center.getComponentCount()-2);
            }
        }
    }//GEN-LAST:event_bangDSNghỉPhepMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = ten.getText();
        String classes = lop.getSelectedItem().toString();
        ArrayList infoHS = new Get().GetIdAndFacStudent(name, classes);
        int idStudent = Integer.parseInt(infoHS.get(0).toString());
        int idTrungTam = Integer.parseInt(infoHS.get(1).toString());
        //them du lieu
            try{
                String datebd = ngay.getSelectedItem().toString() +"-"+ thang.getSelectedItem().toString() +"-"+nam.getSelectedItem().toString();
                String datekt = ngay1.getSelectedItem().toString() +"-"+ thang1.getSelectedItem().toString() +"-"+nam1.getSelectedItem().toString();
                a.InsertNghiPhep(idStudent, idTrungTam,new XuLiXau().NamThangNgay(datebd),new XuLiXau().NamThangNgay(datekt),String.valueOf(Integer.parseInt(songayngay.getText())));
                //load lai bang trong muon
                new DataBase.SQLBangNghiPhep().BangNghiPhep(bangDSNghỉPhep);
                idNghiPhep = new ArrayList<Integer>();
                try{
                if (!bangDSNghỉPhep.getValueAt(0, 0).toString().equals("")) {
                XuLy.setID(idNghiPhep, bangDSNghỉPhep, 0);
                //resize(jTable4);
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rootPane, "Nhập sai Số ngày");
            }
        }
        catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Nhập sai số ngày");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        if(bangDSNghỉPhep.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(rootPane ,"Hãy chọn một dòng cần xóa");
        }
        else{
            try
            {
            String name = bangDSNghỉPhep.getValueAt(bangDSNghỉPhep.getSelectedRow(), 1).toString();
            String classes = bangDSNghỉPhep.getValueAt(bangDSNghỉPhep.getSelectedRow(), 2).toString();
            
            ArrayList infoHS = new Get().GetIdAndFacStudent(name, classes);
            int idStudent = Integer.parseInt(infoHS.get(0).toString());
            int idTrungTam = Integer.parseInt(infoHS.get(1).toString());
            String datebd = bangDSNghỉPhep.getValueAt(bangDSNghỉPhep.getSelectedRow(), 3).toString();
            String datekt = bangDSNghỉPhep.getValueAt(bangDSNghỉPhep.getSelectedRow(), 4).toString();
            System.out.println(datebd+datekt+idStudent);
            a.XoaNghiPhep(idStudent, datebd, datekt);
                    
            new DataBase.SQLBangNghiPhep().BangNghiPhep(bangDSNghỉPhep);
            idNghiPhep = new ArrayList<Integer>();
                    try{
                     if (!bangDSNghỉPhep.getValueAt(0, 0).toString().equals("")) {
                         XuLy.setID(idNghiPhep, bangDSNghỉPhep, 0);
                    //resize(jTable4);
                     }
                    }
                    catch(Exception ex)
                    {}

                        }
            catch(Exception ex)
            {}
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lopActionPerformed
        MakePopup();
    }//GEN-LAST:event_lopActionPerformed

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
            java.util.logging.Logger.getLogger(NhapNghiPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapNghiPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapNghiPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapNghiPhep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhapNghiPhep dialog = new NhapNghiPhep(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable bangDSNghỉPhep;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JComboBox lop;
    private javax.swing.JComboBox nam;
    private javax.swing.JComboBox nam1;
    private javax.swing.JComboBox ngay;
    private javax.swing.JComboBox ngay1;
    private javax.swing.JTextField songayngay;
    private javax.swing.JTextField ten;
    private javax.swing.JComboBox thang;
    private javax.swing.JComboBox thang1;
    // End of variables declaration//GEN-END:variables
}
