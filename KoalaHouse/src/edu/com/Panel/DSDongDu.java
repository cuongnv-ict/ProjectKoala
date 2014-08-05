/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.Panel;

import DataBase.DataTable;
import DataBase.HocSinh.CompleteList;
import DataBase.HocSinh.DebtList;
import edu.com.CloseButton.CloseTabButton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author Pham The Quyen
 */
public class DSDongDu extends javax.swing.JPanel {

    /**
     * Creates new form DSNo
     */
    public ArrayList ListId;
    public JTabbedPane center;
    public JTable BangDSDu;
    public DSDongDu() {
        initComponents();
        ListId = new CompleteList().BangDanhSachHocSinhDuPhi(DSDongDu);
        BangDSDu = DSDongDu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane7 = new javax.swing.JScrollPane();
        DSDongDu = new javax.swing.JTable();

        DSDongDu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        DSDongDu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Số TT", "Họ Tên", "Lớp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DSDongDu.setRowHeight(30);
        DSDongDu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DSDongDuMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(DSDongDu);
        if (DSDongDu.getColumnModel().getColumnCount() > 0) {
            DSDongDu.getColumnModel().getColumn(0).setPreferredWidth(40);
            DSDongDu.getColumnModel().getColumn(1).setPreferredWidth(200);
            DSDongDu.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DSDongDuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DSDongDuMouseClicked
        if(evt.getClickCount()==2){
            //map cac truong
            String sTT = DSDongDu.getValueAt(DSDongDu.getSelectedRow(), 0).toString();
            int stt = Integer.parseInt(sTT);
            int id = Integer.parseInt(ListId.get(stt - 1).toString());
            String tenHocSinh = DSDongDu.getValueAt(DSDongDu.getSelectedRow(), 1).toString();
            //HocSinhA.idTemp = id;
            int a=-1;
            for (int i = 0; i < center.getTabCount(); i++) {
                if (tenHocSinh.equals(center.getTitleAt(i))) {
                    a += 1;
                    center.setSelectedIndex(i);
                }
            }
            if (a == -1) {
           
            HocSinhA aa = new HocSinhA(id);
            //add tab
            
            center.add(tenHocSinh, aa);
            center.setSelectedComponent(aa);
            new CloseTabButton(center,center.getComponentCount()-2);
        }
        }
    }//GEN-LAST:event_DSDongDuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DSDongDu;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables
}