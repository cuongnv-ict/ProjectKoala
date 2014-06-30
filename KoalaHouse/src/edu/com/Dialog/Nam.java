/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.Dialog;

import edu.com.XuLy;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyen van cuong
 */
public class Nam extends javax.swing.JDialog {

    /**
     * Creates new form Nam
     */
    public Nam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 300, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        this.setResizable(false);
        int h = new DataBase.SQLNam().getID();
        setComBoBox(NamHoc, String.valueOf(h)+"-"+String.valueOf(h+1));
        Vector e[] = new DataBase.SQLNam().getNamHoc(h);
        if (e == null) {
            int x = NamHoc.getSelectedIndex();
            B_Nam1.setSelectedIndex(x);
            B_Nam2.setSelectedIndex(x);
            B_Nam3.setSelectedIndex(x);
            B_Nam4.setSelectedIndex(x);
            E_Nam1.setSelectedIndex(x);
            E_Nam2.setSelectedIndex(x);
            E_Nam3.setSelectedIndex(x);
            E_Nam4.setSelectedIndex(x);
            active.setSelected(false);
        } else {
            setComBoBox(B_Ngay1, e[0].get(0).toString());
            setComBoBox(B_Thang1, e[0].get(1).toString());
            setComBoBox(B_Nam1, e[0].get(2).toString());
            setComBoBox(E_Ngay1, e[0].get(3).toString());
            setComBoBox(E_Thang1, e[0].get(4).toString());
            setComBoBox(E_Nam1, e[0].get(5).toString());

            setComBoBox(B_Ngay2, e[1].get(0).toString());
            setComBoBox(B_Thang2, e[1].get(1).toString());
            setComBoBox(B_Nam2, e[1].get(2).toString());
            setComBoBox(E_Ngay2, e[1].get(3).toString());
            setComBoBox(E_Thang2, e[1].get(4).toString());
            setComBoBox(E_Nam2, e[1].get(5).toString());

            setComBoBox(B_Ngay3, e[2].get(0).toString());
            setComBoBox(B_Thang3, e[2].get(1).toString());
            setComBoBox(B_Nam3, e[2].get(2).toString());
            setComBoBox(E_Ngay3, e[2].get(3).toString());
            setComBoBox(E_Thang3, e[2].get(4).toString());
            setComBoBox(E_Nam3, e[2].get(5).toString());

            setComBoBox(B_Ngay4, e[3].get(0).toString());
            setComBoBox(B_Thang4, e[3].get(1).toString());
            setComBoBox(B_Nam4, e[3].get(2).toString());
            setComBoBox(E_Ngay4, e[3].get(3).toString());
            setComBoBox(E_Thang4, e[3].get(4).toString());
            setComBoBox(E_Nam4, e[3].get(5).toString());
            active.setSelected(new DataBase.SQLNam().getIsActiveSemester(h));
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

        NamHoc = new javax.swing.JComboBox();
        B_Ngay1 = new javax.swing.JComboBox();
        B_Thang1 = new javax.swing.JComboBox();
        B_Nam1 = new javax.swing.JComboBox();
        E_Ngay1 = new javax.swing.JComboBox();
        E_Thang1 = new javax.swing.JComboBox();
        E_Nam1 = new javax.swing.JComboBox();
        B_Thang2 = new javax.swing.JComboBox();
        B_Nam2 = new javax.swing.JComboBox();
        B_Ngay2 = new javax.swing.JComboBox();
        E_Ngay2 = new javax.swing.JComboBox();
        E_Thang2 = new javax.swing.JComboBox();
        E_Nam2 = new javax.swing.JComboBox();
        B_Thang3 = new javax.swing.JComboBox();
        B_Nam3 = new javax.swing.JComboBox();
        B_Ngay3 = new javax.swing.JComboBox();
        E_Ngay3 = new javax.swing.JComboBox();
        E_Thang3 = new javax.swing.JComboBox();
        E_Nam3 = new javax.swing.JComboBox();
        B_Thang4 = new javax.swing.JComboBox();
        B_Nam4 = new javax.swing.JComboBox();
        B_Ngay4 = new javax.swing.JComboBox();
        E_Ngay4 = new javax.swing.JComboBox();
        E_Thang4 = new javax.swing.JComboBox();
        E_Nam4 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        CapNhat = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        active = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        NamHoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013-2014", "2014-2015", "2015-2016", "2016-2017", "2017-2018", "2018-2019", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024", "2024-2025", "2025-2026", "2026-2027", "2027-2028", "2028-2029", "2029-2030" }));
        NamHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamHocActionPerformed(evt);
            }
        });

        B_Ngay1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        B_Thang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        B_Nam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        E_Ngay1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Thang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        E_Nam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        B_Thang2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        B_Nam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        B_Ngay2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Ngay2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Thang2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        E_Nam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        B_Thang3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        B_Nam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        B_Ngay3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Ngay3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Thang3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        E_Nam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        B_Thang4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        B_Nam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        B_Ngay4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Ngay4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        E_Thang4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        E_Nam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Kỳ 1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Kỳ 2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Kỳ 3");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Kỳ hè");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Năm học");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Ngày bắt đầu");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Ngày kết thúc");

        CapNhat.setText("Cập nhật");
        CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatActionPerformed(evt);
            }
        });

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        active.setText("Năm học đang giảng dạy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(B_Ngay3, 0, 49, Short.MAX_VALUE)
                                .addComponent(B_Ngay2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B_Ngay1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(B_Thang1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(B_Thang2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(B_Nam2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(B_Nam1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(B_Thang3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(B_Nam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(B_Ngay4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B_Thang4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B_Nam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(active))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(E_Ngay4, 0, 49, Short.MAX_VALUE)
                                        .addComponent(E_Ngay3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(E_Ngay2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(E_Ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(E_Thang2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(E_Thang1, javax.swing.GroupLayout.Alignment.LEADING, 0, 49, Short.MAX_VALUE)
                                    .addComponent(E_Thang3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(E_Thang4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(E_Nam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E_Nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E_Nam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E_Nam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CapNhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Thang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Nam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Nam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Thang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Ngay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Thang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Nam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Nam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Thang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E_Ngay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B_Ngay4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Thang4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Nam4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(E_Nam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(E_Thang4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(E_Ngay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CapNhat)
                    .addComponent(jButton1)
                    .addComponent(active))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NamHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamHocActionPerformed
        // TODO add your handling code here:
        Vector e[] = new DataBase.SQLNam().getNamHoc(Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]));
        if (e == null) {
            int x = NamHoc.getSelectedIndex();
            B_Nam1.setSelectedIndex(x);
            B_Nam2.setSelectedIndex(x);
            B_Nam3.setSelectedIndex(x);
            B_Nam4.setSelectedIndex(x);
            E_Nam1.setSelectedIndex(x);
            E_Nam2.setSelectedIndex(x);
            E_Nam3.setSelectedIndex(x);
            E_Nam4.setSelectedIndex(x);
            B_Ngay1.setSelectedIndex(0);
            B_Ngay2.setSelectedIndex(0);
            B_Ngay3.setSelectedIndex(0);
            B_Ngay4.setSelectedIndex(0);
            E_Ngay1.setSelectedIndex(0);
            E_Ngay2.setSelectedIndex(0);
            E_Ngay3.setSelectedIndex(0);
            E_Ngay4.setSelectedIndex(0);
            B_Thang1.setSelectedIndex(0);
            B_Thang2.setSelectedIndex(0);
            B_Thang3.setSelectedIndex(0);
            B_Thang4.setSelectedIndex(0);
            E_Thang1.setSelectedIndex(0);
            E_Thang2.setSelectedIndex(0);
            E_Thang3.setSelectedIndex(0);
            E_Thang4.setSelectedIndex(0);
            active.setSelected(false);
        } else {
            setComBoBox(B_Ngay1, e[0].get(0).toString());
            setComBoBox(B_Thang1, e[0].get(1).toString());
            setComBoBox(B_Nam1, e[0].get(2).toString());
            setComBoBox(E_Ngay1, e[0].get(3).toString());
            setComBoBox(E_Thang1, e[0].get(4).toString());
            setComBoBox(E_Nam1, e[0].get(5).toString());

            setComBoBox(B_Ngay2, e[1].get(0).toString());
            setComBoBox(B_Thang2, e[1].get(1).toString());
            setComBoBox(B_Nam2, e[1].get(2).toString());
            setComBoBox(E_Ngay2, e[1].get(3).toString());
            setComBoBox(E_Thang2, e[1].get(4).toString());
            setComBoBox(E_Nam2, e[1].get(5).toString());

            setComBoBox(B_Ngay3, e[2].get(0).toString());
            setComBoBox(B_Thang3, e[2].get(1).toString());
            setComBoBox(B_Nam3, e[2].get(2).toString());
            setComBoBox(E_Ngay3, e[2].get(3).toString());
            setComBoBox(E_Thang3, e[2].get(4).toString());
            setComBoBox(E_Nam3, e[2].get(5).toString());

            setComBoBox(B_Ngay4, e[3].get(0).toString());
            setComBoBox(B_Thang4, e[3].get(1).toString());
            setComBoBox(B_Nam4, e[3].get(2).toString());
            setComBoBox(E_Ngay4, e[3].get(3).toString());
            setComBoBox(E_Thang4, e[3].get(4).toString());
            setComBoBox(E_Nam4, e[3].get(5).toString());
            active.setSelected(new DataBase.SQLNam().getIsActiveSemester(Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0])));
        }
    }//GEN-LAST:event_NamHocActionPerformed

    private void CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatActionPerformed
        // TODO add your handling code here:
        boolean flags = false;
        Vector vector = new Vector();
        String begin, end;
        if(!(XuLy.getTime(B_Nam1.getSelectedItem().toString() + "-" + B_Thang1.getSelectedItem().toString() + "-" + B_Ngay1.getSelectedItem().toString(),E_Nam1.getSelectedItem().toString() + "-" + E_Thang1.getSelectedItem().toString() + "-" + E_Ngay1.getSelectedItem().toString())
           &&XuLy.getTime(B_Nam2.getSelectedItem().toString() + "-" + B_Thang2.getSelectedItem().toString() + "-" + B_Ngay2.getSelectedItem().toString(),E_Nam2.getSelectedItem().toString() + "-" + E_Thang2.getSelectedItem().toString() + "-" + E_Ngay2.getSelectedItem().toString())
           &&XuLy.getTime(B_Nam3.getSelectedItem().toString() + "-" + B_Thang3.getSelectedItem().toString() + "-" + B_Ngay3.getSelectedItem().toString(),E_Nam3.getSelectedItem().toString() + "-" + E_Thang3.getSelectedItem().toString() + "-" + E_Ngay3.getSelectedItem().toString())
           &&XuLy.getTime(B_Nam4.getSelectedItem().toString() + "-" + B_Thang4.getSelectedItem().toString() + "-" + B_Ngay4.getSelectedItem().toString(),E_Nam4.getSelectedItem().toString() + "-" + E_Thang4.getSelectedItem().toString() + "-" + E_Ngay4.getSelectedItem().toString())
           &&XuLy.getTime(E_Nam1.getSelectedItem().toString() + "-" + E_Thang1.getSelectedItem().toString() + "-" + E_Ngay1.getSelectedItem().toString(),B_Nam2.getSelectedItem().toString() + "-" + B_Thang2.getSelectedItem().toString() + "-" + B_Ngay2.getSelectedItem().toString())
           &&XuLy.getTime(E_Nam2.getSelectedItem().toString() + "-" + E_Thang2.getSelectedItem().toString() + "-" + E_Ngay2.getSelectedItem().toString(),B_Nam3.getSelectedItem().toString() + "-" + B_Thang3.getSelectedItem().toString() + "-" + B_Ngay3.getSelectedItem().toString())
           &&XuLy.getTime(E_Nam3.getSelectedItem().toString() + "-" + E_Thang3.getSelectedItem().toString() + "-" + E_Ngay3.getSelectedItem().toString(),B_Nam4.getSelectedItem().toString() + "-" + B_Thang4.getSelectedItem().toString() + "-" + B_Ngay4.getSelectedItem().toString()))){
            JOptionPane.showMessageDialog(rootPane, "Thiết lập thời gian không chính xác.\nHãy kiển tra lại thời gian bắt đầu và kết thúc của một kì\nvà thời gian kết thúc của kì trước và thời gian bắt đầu cảu kì sau.", null, JOptionPane.ERROR_MESSAGE);
            return;
        }
        begin = B_Nam1.getSelectedItem().toString() + "-" + B_Thang1.getSelectedItem().toString() + "-" + B_Ngay1.getSelectedItem().toString();
        end = E_Nam1.getSelectedItem().toString() + "-" + E_Thang1.getSelectedItem().toString() + "-" + E_Ngay1.getSelectedItem().toString();
        vector.add(begin);
        vector.add(end);
        flags = new DataBase.SQLNam().setNamHoc(vector, Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]), 1);
        if (!flags) {
            return;
        }
        begin = B_Nam2.getSelectedItem().toString() + "-" + B_Thang2.getSelectedItem().toString() + "-" + B_Ngay2.getSelectedItem().toString();
        end = E_Nam2.getSelectedItem().toString() + "-" + E_Thang2.getSelectedItem().toString() + "-" + E_Ngay2.getSelectedItem().toString();
        vector.removeAll(vector);
        vector.add(begin);
        vector.add(end);
        flags = new DataBase.SQLNam().setNamHoc(vector, Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]), 2);
        if (!flags) {
            return;
        }
        begin = B_Nam3.getSelectedItem().toString() + "-" + B_Thang3.getSelectedItem().toString() + "-" + B_Ngay3.getSelectedItem().toString();
        end = E_Nam3.getSelectedItem().toString() + "-" + E_Thang3.getSelectedItem().toString() + "-" + E_Ngay3.getSelectedItem().toString();
        vector.removeAll(vector);
        vector.add(begin);
        vector.add(end);
        flags = new DataBase.SQLNam().setNamHoc(vector, Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]), 3);
        if (!flags) {
            return;
        }
        begin = B_Nam4.getSelectedItem().toString() + "-" + B_Thang4.getSelectedItem().toString() + "-" + B_Ngay4.getSelectedItem().toString();
        end = E_Nam4.getSelectedItem().toString() + "-" + E_Thang4.getSelectedItem().toString() + "-" + E_Ngay4.getSelectedItem().toString();
        vector.removeAll(vector);
        vector.add(begin);
        vector.add(end);
        flags = new DataBase.SQLNam().setNamHoc(vector, Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]), 4);
        if (!flags) {
            return;
        }
        begin = B_Nam1.getSelectedItem().toString() + "-" + B_Thang1.getSelectedItem().toString() + "-" + B_Ngay1.getSelectedItem().toString();
        end = E_Nam4.getSelectedItem().toString() + "-" + E_Thang4.getSelectedItem().toString() + "-" + E_Ngay4.getSelectedItem().toString();
        vector.removeAll(vector);
        vector.add(begin);
        vector.add(end);
        flags = new DataBase.SQLNam().setNamHoc(vector, Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]), 5);
         if (!flags) {
            return;
        }
        if (new DataBase.SQLNam().setIsActiveSemester(Integer.parseInt(NamHoc.getSelectedItem().toString().split("-")[0]), active.isSelected())) {
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_CapNhatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void setComBoBox(JComboBox cb, String value) {
        for (int i = 0; i < cb.getItemCount(); i++) {
            if (cb.getItemAt(i).toString().equals(value)) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox B_Nam1;
    private javax.swing.JComboBox B_Nam2;
    private javax.swing.JComboBox B_Nam3;
    private javax.swing.JComboBox B_Nam4;
    private javax.swing.JComboBox B_Ngay1;
    private javax.swing.JComboBox B_Ngay2;
    private javax.swing.JComboBox B_Ngay3;
    private javax.swing.JComboBox B_Ngay4;
    private javax.swing.JComboBox B_Thang1;
    private javax.swing.JComboBox B_Thang2;
    private javax.swing.JComboBox B_Thang3;
    private javax.swing.JComboBox B_Thang4;
    private javax.swing.JButton CapNhat;
    private javax.swing.JComboBox E_Nam1;
    private javax.swing.JComboBox E_Nam2;
    private javax.swing.JComboBox E_Nam3;
    private javax.swing.JComboBox E_Nam4;
    private javax.swing.JComboBox E_Ngay1;
    private javax.swing.JComboBox E_Ngay2;
    private javax.swing.JComboBox E_Ngay3;
    private javax.swing.JComboBox E_Ngay4;
    private javax.swing.JComboBox E_Thang1;
    private javax.swing.JComboBox E_Thang2;
    private javax.swing.JComboBox E_Thang3;
    private javax.swing.JComboBox E_Thang4;
    private javax.swing.JComboBox NamHoc;
    private javax.swing.JCheckBox active;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
