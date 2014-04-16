/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Dialog;

import edu.com.XuLy;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Venus
 */
public class ThemGia extends javax.swing.JDialog {

    private boolean button;// set gia tri cua button
    private int id;
    String str;

    /**
     * Creates new form ThemGia
     */
    public ThemGia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        this.setResizable(false);
       
    }

    public ThemGia(java.awt.Frame parent, boolean modal, Vector vector, int id) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        DongY.setText("Cập nhật");
        title.setText("Chỉnh sửa học phí");
        datcoc.setSelected(new DataBase.SQLHocPhi().getSelected(id));
        TenPhi.setText(vector.get(1).toString());
        trongmuon.setSelected(vector.get(1).toString().equals("Phí trông muộn"));
        Gia.setText(vector.get(6).toString());
        for (int i = 0; i < Ky.getItemCount(); i++) {
            if (Ky.getItemAt(i).toString().equals(vector.get(2).toString())) {
                Ky.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < Nam.getItemCount(); i++) {
            if (Nam.getItemAt(i).toString().equals(vector.get(3).toString())) {
                Nam.setSelectedIndex(i);
                break;
            }
        }
        this.id = id;
        
    }
    //lay gia tri cua 

    public boolean getButton() {
        return button;
    }

    public String getTenPhi() {
        return TenPhi.getText();
    }

    public String getGia() {
        return Gia.getText();
    }

    public String getHocKy() {
        return Ky.getSelectedItem().toString();
    }

    public String getNamHoc() {
        return Nam.getSelectedItem().toString();
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
        jLabel3 = new javax.swing.JLabel();
        TenPhi = new javax.swing.JTextField();
        Ky = new javax.swing.JComboBox();
        Gia = new javax.swing.JTextField();
        HuyBo = new javax.swing.JButton();
        DongY = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nam = new javax.swing.JComboBox();
        datcoc = new javax.swing.JCheckBox();
        trongmuon = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText(" Tên Phí :");
        jLabel1.setPreferredSize(new java.awt.Dimension(45, 20));

        jLabel2.setText(" Học Kỳ :");
        jLabel2.setPreferredSize(new java.awt.Dimension(43, 25));

        jLabel3.setText(" Giá:");
        jLabel3.setPreferredSize(new java.awt.Dimension(22, 20));

        TenPhi.setToolTipText("");
        TenPhi.setMinimumSize(new java.awt.Dimension(6, 25));
        TenPhi.setPreferredSize(new java.awt.Dimension(6, 25));

        Ky.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kỳ 1", "Kỳ 2", "Kỳ 3", "Kỳ hè", "Cả năm" }));

        Gia.setPreferredSize(new java.awt.Dimension(48, 25));
        Gia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GiaKeyTyped(evt);
            }
        });

        HuyBo.setText("Hủy bỏ");
        HuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyBoActionPerformed(evt);
            }
        });

        DongY.setText("Thêm");
        DongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DongYActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Thêm học phí");

        jLabel4.setText("Năm học");

        Nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        datcoc.setText("Phí đặt cọc");
        datcoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datcocActionPerformed(evt);
            }
        });

        trongmuon.setText("Phí trông muộn");
        trongmuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trongmuonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Gia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TenPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Ky, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(DongY, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(datcoc)
                                .addGap(37, 37, 37)
                                .addComponent(trongmuon)))))
                .addGap(34, 48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Gia, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ky, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datcoc)
                    .addComponent(trongmuon))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DongY)
                    .addComponent(HuyBo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DongYActionPerformed
        // TODO add your handling code here:
        Vector vec = new Vector();
        if (getTenPhi().equals("")||getGia().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên điền đủ thông tin", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        vec.add(getTenPhi());
        vec.add(Ky.getSelectedIndex() + 1);
        vec.add(getNamHoc());
        if (datcoc.isSelected()) {
            vec.add("-" + XuLy.getMoney(getGia()));
        } else {
            vec.add(XuLy.getMoney(getGia()));
        }
        if (!DongY.getText().equals("Cập nhật") && new DataBase.SQLHocPhi().themHocPhi(vec)) {
            button = true;
            dispose();
        } else if (DongY.getText().equals("Cập nhật") && new DataBase.SQLHocPhi().suaHocPhi(vec, id)) {
            button = true;
            dispose();
        }

    }//GEN-LAST:event_DongYActionPerformed

    private void HuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyBoActionPerformed
        // TODO add your handling code here:
        button = false;
        dispose();
    }//GEN-LAST:event_HuyBoActionPerformed

    private void GiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GiaKeyTyped
//         new Thread() {
//            public void run() {
//               
//                     try {
//                        Thread.sleep(200);
                        String str = Gia.getText();
                        Gia.setText(XuLy.setMoney(str));
//  
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(ThemGia.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                
//            }
//        }.start();
    }//GEN-LAST:event_GiaKeyTyped

    private void datcocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datcocActionPerformed
        // TODO add your handling code here:
        if(datcoc.isSelected()){
            trongmuon.setSelected(false);
            TenPhi.setEditable(false);
            TenPhi.setText("Phí đặt cọc");
        }
        else{
             TenPhi.setEditable(true);
        }
    }//GEN-LAST:event_datcocActionPerformed

    private void trongmuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trongmuonActionPerformed
        // TODO add your handling code here:
        if(trongmuon.isSelected()){
            datcoc.setSelected(false);
            TenPhi.setEditable(false);
            TenPhi.setText("Phí trông muộn");
        }
        else{
              TenPhi.setEditable(true);
        }
    }//GEN-LAST:event_trongmuonActionPerformed
    /**
     * 
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DongY;
    private javax.swing.JTextField Gia;
    private javax.swing.JButton HuyBo;
    private javax.swing.JComboBox Ky;
    private javax.swing.JComboBox Nam;
    private javax.swing.JTextField TenPhi;
    private javax.swing.JCheckBox datcoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel title;
    private javax.swing.JCheckBox trongmuon;
    // End of variables declaration//GEN-END:variables
}
