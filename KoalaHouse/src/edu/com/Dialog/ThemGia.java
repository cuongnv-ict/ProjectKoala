/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Dialog;

import edu.com.XuLy;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
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
    private boolean flags;
    private int h;

    public boolean isFlags() {
        return flags;
    }

    /**
     * Creates new form ThemGia
     */
    public ThemGia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        button = false;
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        this.setResizable(false);
        h = new DataBase.SQLNam().getID();
        if (h == -1) {
            flags = false;
        } else {
            Nam.addItem(String.valueOf(h) + "-" + String.valueOf(h + 1));
            Nam.setSelectedItem(String.valueOf(h) + "-" + String.valueOf(h + 1));
            flags = true;
            B_nam.addItem(h);
            B_nam.addItem(h + 1);
            E_nam.addItem(h);
            E_nam.addItem(h + 1);
        }
    }

    public ThemGia(java.awt.Frame parent, boolean modal, Vector vector, int id) {
        super(parent, modal);
        initComponents();
        button = false;
        this.setResizable(false);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        DongY.setText("Cập nhật");
        title.setText("Chỉnh sửa học phí");
        datcoc.setSelected(new DataBase.SQLHocPhi().getSelected(id));
        if (datcoc.isSelected()) {
            TenPhi.setEditable(false);
            Ky.setEnabled(false);
        }
        TenPhi.setText(vector.get(1).toString());
        trongmuon.setSelected(vector.get(1).toString().equals("Phí Trông Muộn"));
        if (trongmuon.isSelected()) {
            TenPhi.setEditable(false);
        }
        hocHe.setSelected(vector.get(1).toString().equals("Phí Học Hè"));
        if (hocHe.isSelected()) {
            TenPhi.setEditable(false);
            Ky.setEnabled(false);
        }
        hoanhocphi.setSelected(vector.get(1).toString().equals("Hoàn Học Phí"));
        if (hoanhocphi.isSelected()) {
            TenPhi.setEditable(false);
        }
        Gia.setText(vector.get(6).toString());
        setComboBox(Ky, vector.get(2).toString());
        setComboBox(Nam, vector.get(3).toString());
        Nam.addItem(vector.get(3).toString());
        Nam.setSelectedItem(vector.get(3).toString());
        String date[] = vector.get(3).toString().split("-");
        B_nam.addItem(date[0]);
        B_nam.addItem(date[1]);
        E_nam.addItem(date[0]);
        E_nam.addItem(date[1]);
        String[] arr = vector.get(4).toString().split("-");
        if (arr.length == 3) {
            setComboBox(B_ngay, arr[0]);
            setComboBox(B_thang, arr[1]);
            setComboBox(B_nam, arr[2]);
        }
        arr = vector.get(5).toString().split("-");
        if (arr.length == 3) {
            setComboBox(E_ngay, arr[0]);
            setComboBox(E_thang, arr[1]);
            setComboBox(E_nam, arr[2]);
        }
        if (B_ngay.getSelectedIndex() != 0) {
            IsTime.setSelected(true);
            B_ngay.setEnabled(true);
            B_thang.setEnabled(true);
            B_nam.setEnabled(true);
            E_ngay.setEnabled(true);
            E_thang.setEnabled(true);
            E_nam.setEnabled(true);
        }
        this.id = id;

    }

    public ThemGia(java.awt.Frame parent, boolean modal, boolean samefee, Vector vector, int id) {
        super(parent, modal);
        initComponents();
        button = false;
        this.setResizable(false);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        DongY.setText("Thêm");
        title.setText("Thêm Phí Tương Tự");
        datcoc.setSelected(new DataBase.SQLHocPhi().getSelected(id));
        if (datcoc.isSelected()) {
            TenPhi.setEditable(false);
            Ky.setEnabled(false);
        }
        TenPhi.setText(vector.get(1).toString());
        trongmuon.setSelected(vector.get(1).toString().equals("Phí Trông Muộn"));
        if (trongmuon.isSelected()) {
            TenPhi.setEditable(false);
        }
        hocHe.setSelected(vector.get(1).toString().equals("Phí Học Hè"));
        if (hocHe.isSelected()) {
            TenPhi.setEditable(false);
            Ky.setEnabled(false);
        }
        hoanhocphi.setSelected(vector.get(1).toString().equals("Hoàn Học Phí"));
        if (hoanhocphi.isSelected()) {
            TenPhi.setEditable(false);
        }
        Gia.setText(vector.get(6).toString());
        setComboBox(Ky, vector.get(2).toString());
        setComboBox(Nam, vector.get(3).toString());
        Nam.addItem(vector.get(3).toString());
        Nam.setSelectedItem(vector.get(3).toString());
        String date[] = vector.get(3).toString().split("-");
        B_nam.addItem(date[0]);
        B_nam.addItem(date[1]);
        E_nam.addItem(date[0]);
        E_nam.addItem(date[1]);
        String[] arr = vector.get(4).toString().split("-");
        if (arr.length == 3) {
            setComboBox(B_ngay, arr[0]);
            setComboBox(B_thang, arr[1]);
            setComboBox(B_nam, arr[2]);
        }
        arr = vector.get(5).toString().split("-");
        if (arr.length == 3) {
            setComboBox(E_ngay, arr[0]);
            setComboBox(E_thang, arr[1]);
            setComboBox(E_nam, arr[2]);
        }
        this.id = id;

    }

    //lay gia tri cua 
    public void setComboBox(JComboBox c, String s) {
        for (int i = 0; i < c.getItemCount(); i++) {
            if (c.getItemAt(i).toString().equals(s)) {
                c.setSelectedIndex(i);
                break;
            }
        }
    }

    public boolean getButton() {
        return button;
    }

    public String getTenPhi() {
        return TenPhi.getText();
    }

    public String getGia() {
        return XuLy.getMoney(Gia.getText());
    }

    public String getNamHoc() {
        return Nam.getSelectedItem().toString().split("-")[0];
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
        jLabel5 = new javax.swing.JLabel();
        B_ngay = new javax.swing.JComboBox();
        B_nam = new javax.swing.JComboBox();
        B_thang = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        E_thang = new javax.swing.JComboBox();
        E_ngay = new javax.swing.JComboBox();
        E_nam = new javax.swing.JComboBox();
        hocHe = new javax.swing.JCheckBox();
        hoanhocphi = new javax.swing.JCheckBox();
        IsTime = new javax.swing.JCheckBox();

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

        Ky.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Học Kỳ 1", "Học Kỳ 2", "Học Kỳ 3", "Học Kỳ Hè", "Cả Năm" }));

        Gia.setPreferredSize(new java.awt.Dimension(48, 25));
        Gia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GiaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GiaKeyReleased(evt);
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

        jLabel4.setText("Năm Học:");

        datcoc.setText("Phí Giữ Chỗ");
        datcoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datcocActionPerformed(evt);
            }
        });

        trongmuon.setText("Phí Trông Muộn");
        trongmuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trongmuonActionPerformed(evt);
            }
        });

        jLabel5.setText("Ngày Bắt Đầu:");
        jLabel5.setPreferredSize(new java.awt.Dimension(43, 25));

        B_ngay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        B_ngay.setEnabled(false);
        B_ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ngayActionPerformed(evt);
            }
        });

        B_nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm" }));
        B_nam.setEnabled(false);

        B_thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tháng", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        B_thang.setEnabled(false);

        jLabel6.setText("Ngày Kết Thúc:");
        jLabel6.setPreferredSize(new java.awt.Dimension(43, 25));

        E_thang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tháng", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        E_thang.setEnabled(false);
        E_thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_thangActionPerformed(evt);
            }
        });

        E_ngay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        E_ngay.setEnabled(false);

        E_nam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm" }));
        E_nam.setEnabled(false);

        hocHe.setText("Phí Học Hè");
        hocHe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hocHeActionPerformed(evt);
            }
        });

        hoanhocphi.setText("Hoàn Học Phí");
        hoanhocphi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoanhocphiActionPerformed(evt);
            }
        });

        IsTime.setText("Xác định thời gian thu phí");
        IsTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IsTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Gia, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .addComponent(TenPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(trongmuon)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(DongY, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Ky, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hocHe))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(datcoc)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(hoanhocphi))))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(IsTime)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(B_ngay, 0, 90, Short.MAX_VALUE)
                                        .addComponent(E_ngay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(46, 46, 46)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(B_thang, 0, 83, Short.MAX_VALUE)
                                        .addComponent(E_thang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(51, 51, 51)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(B_nam, 0, 79, Short.MAX_VALUE)
                                        .addComponent(E_nam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Gia, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(IsTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ky, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datcoc)
                    .addComponent(hocHe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trongmuon)
                    .addComponent(hoanhocphi))
                .addGap(21, 21, 21)
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
        if (getTenPhi().equals("") || getGia().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa điền đủ thông tin học phí !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (IsTime.isSelected()
                && (B_ngay.getSelectedIndex() == 0 || B_thang.getSelectedIndex() == 0
                || B_nam.getSelectedIndex() == 0 || E_ngay.getSelectedIndex() == 0
                || E_thang.getSelectedIndex() == 0 || E_nam.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa xác định thời gian nộp phí!", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (IsTime.isSelected() && !XuLy.getTime(B_nam.getSelectedItem().toString() + "-" + B_thang.getSelectedItem().toString() + "-" + B_ngay.getSelectedItem().toString(), E_nam.getSelectedItem().toString() + "-" + E_thang.getSelectedItem().toString() + "-" + E_ngay.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(rootPane, "Bạn điền hạn nộp tiền không chính xác !", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        vec.add(getTenPhi());
        vec.add(Ky.getSelectedIndex() + 1);
        vec.add(getNamHoc());
        if (IsTime.isSelected()) {
            vec.add(B_nam.getSelectedItem().toString() + "-" + B_thang.getSelectedItem().toString() + "-" + B_ngay.getSelectedItem().toString());
            vec.add(E_nam.getSelectedItem().toString() + "-" + E_thang.getSelectedItem().toString() + "-" + E_ngay.getSelectedItem().toString());
        } else {
            vec.add(-1);
            vec.add(-1);
        }
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
//                        String str = Gia.getText();
//                        Gia.setText(XuLy.setMoney(str));
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
        if (datcoc.isSelected()) {
            trongmuon.setSelected(false);
            TenPhi.setEditable(false);
            hoanhocphi.setSelected(false);
            TenPhi.setText("Phí Giữ Chỗ");
            Ky.setSelectedIndex(4);
            Ky.setEnabled(false);
            hocHe.setSelected(false);
        } else {
            TenPhi.setEditable(true);
            Ky.setEnabled(true);
        }
    }//GEN-LAST:event_datcocActionPerformed

    private void trongmuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trongmuonActionPerformed
        // TODO add your handling code here:
        if (trongmuon.isSelected()) {
            datcoc.setSelected(false);
            TenPhi.setEditable(false);
            TenPhi.setText("Phí Trông Muộn");
            Ky.setEnabled(true);
            hocHe.setSelected(false);
            hoanhocphi.setSelected(false);
        } else {
            TenPhi.setEditable(true);
        }
    }//GEN-LAST:event_trongmuonActionPerformed

    private void B_ngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ngayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_ngayActionPerformed

    private void hocHeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hocHeActionPerformed
        // TODO add your handling code here:
        if (hocHe.isSelected()) {
            datcoc.setSelected(false);
            trongmuon.setSelected(false);
            TenPhi.setText("");
            TenPhi.setText("Phí Học Hè");
            TenPhi.setEditable(false);
            Ky.setSelectedIndex(3);
            Ky.setEnabled(false);
        } else {
            TenPhi.setEditable(true);
            Ky.setEnabled(true);
        }
    }//GEN-LAST:event_hocHeActionPerformed

    private void GiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GiaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            return;
        }
        Gia.setText(XuLy.setMoney(Gia.getText()));
    }//GEN-LAST:event_GiaKeyReleased

    private void E_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_thangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_thangActionPerformed

    private void hoanhocphiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoanhocphiActionPerformed
        // TODO add your handling code here:
        if (hoanhocphi.isSelected()) {
            datcoc.setSelected(false);
            trongmuon.setSelected(false);
            hocHe.setSelected(false);
            TenPhi.setText("Hoàn Học Phí");
            TenPhi.setEditable(false);
            Ky.setSelectedIndex(4);

        } else {
            TenPhi.setEditable(true);
            Ky.setEnabled(true);
        }
    }//GEN-LAST:event_hoanhocphiActionPerformed

    private void IsTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IsTimeActionPerformed
        // TODO add your handling code here:
        boolean time = true;
        if (IsTime.isSelected()) {
            time = true;
        } else {
            time = false;
        }
        B_ngay.setEnabled(time);
        B_thang.setEnabled(time);
        B_nam.setEnabled(time);
        E_ngay.setEnabled(time);
        E_thang.setEnabled(time);
        E_nam.setEnabled(time);
    }//GEN-LAST:event_IsTimeActionPerformed
    /**
     *
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox B_nam;
    private javax.swing.JComboBox B_ngay;
    private javax.swing.JComboBox B_thang;
    private javax.swing.JButton DongY;
    private javax.swing.JComboBox E_nam;
    private javax.swing.JComboBox E_ngay;
    private javax.swing.JComboBox E_thang;
    private javax.swing.JTextField Gia;
    private javax.swing.JButton HuyBo;
    private javax.swing.JCheckBox IsTime;
    private javax.swing.JComboBox Ky;
    private javax.swing.JComboBox Nam;
    private javax.swing.JTextField TenPhi;
    private javax.swing.JCheckBox datcoc;
    private javax.swing.JCheckBox hoanhocphi;
    private javax.swing.JCheckBox hocHe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel title;
    private javax.swing.JCheckBox trongmuon;
    // End of variables declaration//GEN-END:variables
}
