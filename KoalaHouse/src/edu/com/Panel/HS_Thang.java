/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Panel;

import DataBase.DataTable;
import DataBase.SQLDanhSachHocSinh;
import edu.com.CloseButton.CloseTabButton;
import edu.com.Dialog.InfoHS;
import edu.com.Dialog.ThemHS;
import edu.com.Dialog.chuyenlop;
import edu.com.ListKoala;
import edu.com.XuLy;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pham The Quyen
 */
public class HS_Thang extends javax.swing.JPanel {

    /**
     * Creates new form DSHS
     */
    private boolean isadmin = true;
    public JTabbedPane center;
    ArrayList<Integer> info;
    DefaultCellEditor combo;
    JComboBox c = null;
    int row_, col_;
    boolean flags ;
    public HS_Thang() {
        initComponents();
        String date[] = new SimpleDateFormat("dd-MM-yyyy").format((new Date()).getTime()).split("-");
        c = new DataBase.SQLHocSinhTheoThang().getNameClass();
        combo = new DefaultCellEditor(c);
        setSelectComboBox(thang, date[1]);
        setSelectComboBox(nam, date[2]);
        flags = true;
        c.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(flags){
                 
                    new DataBase.SQLHocSinhTheoThang().update(Math.abs(info.get(row_)), c.getSelectedItem().toString());
                    flags = false;
                }else{
                    flags = true;
                }
            }
        });
        this.getinfo();

    }
    public void reload(){
        if(c==null){
            
        }
        getinfo();
    }
    private void setSelectComboBox(JComboBox x, Object obj) {
        for (int i = 0; i < x.getItemCount(); i++) {
            if (x.getItemAt(i).toString().equals(obj.toString().trim())) {
                x.setSelectedIndex(i);
                break;
            }
        }
    }

    private void getinfo() {
        if (thang.getSelectedIndex() == 5 || thang.getSelectedIndex() == 6 || thang.getSelectedIndex() == 7) {
            info = new DataBase.SQLHocSinhTheoThang().DanhSachHocSinhKiHe(danhsach, Integer.valueOf(thang.getSelectedItem().toString()), Integer.valueOf(nam.getSelectedItem().toString()));
        } else {
            info = new DataBase.SQLHocSinhTheoThang().DanhSachHocSinhTrongKi(danhsach, Integer.valueOf(thang.getSelectedItem().toString()), Integer.valueOf(nam.getSelectedItem().toString()));
        }
    }

    public void setNotAdmin() {
        this.isadmin = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        danhsach = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){

                Component c = super.prepareRenderer(renderer, row, column);
                if(info.get(row)>0){
                    c.setFont(new Font("Arial", Font.BOLD, 12));
                }
                else{
                    c.setFont(new Font("Arial", Font.PLAIN, 12));
                }
                return c;

            }
            public TableCellEditor getCellEditor(int row, int column)
            {
                if (column==2&&(thang.getSelectedIndex() == 5 || thang.getSelectedIndex() == 6 || thang.getSelectedIndex() == 7)){
                    row_ = row;
                    col_ = column;
                    return combo;
                }else{
                    return super.getCellEditor(row, column);
                }
            }
        };
        thang = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        nam = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));

        danhsach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        danhsach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Trình Độ", "Lớp", "Hình Thức Học", "Ngay Sinh"
            }
        ));
        danhsach.setRowHeight(30);
        jScrollPane8.setViewportView(danhsach);

        thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        thang.setMinimumSize(new java.awt.Dimension(114, 25));
        thang.setPreferredSize(new java.awt.Dimension(114, 25));
        thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thangActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Danh sách học sinh theo tháng :");

        nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        nam.setMinimumSize(new java.awt.Dimension(114, 25));
        nam.setPreferredSize(new java.awt.Dimension(114, 25));
        nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("/");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    /*
     * setID dung de chuyen id hoc sinh sang so thu tu, từ số thứ tự thì chỉ số i-1 sẽ giúp truy suất trong mang id
     * giá trị Id_student tương ứng.
     */
    private void thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thangActionPerformed
        // TODO add your handling code here:
        getinfo();
    }//GEN-LAST:event_thangActionPerformed

    private void namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namActionPerformed
        // TODO add your handling code here:
        getinfo();
    }//GEN-LAST:event_namActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable danhsach;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JComboBox nam;
    private javax.swing.JComboBox thang;
    // End of variables declaration//GEN-END:variables
}
