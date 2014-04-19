/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com;


import DataBase.ConnectData;
import edu.com.Dialog.DSChuaHoanThanhHocPhi;
import edu.com.Dialog.DSDatCoc;
import edu.com.Dialog.DSTaiKhoan;
import edu.com.Dialog.DangNhapVao;
import edu.com.Dialog.HoaDon;
import edu.com.Dialog.Nam;
import edu.com.Dialog.TaoTaiKhoan;
import edu.com.Dialog.ThemKhoi;
import edu.com.Dialog.ThemSuaLop;
import edu.com.Panel.CacLoaiPhi;
import edu.com.Panel.DSHS;
import edu.com.Panel.DSLop;
import edu.com.Panel.DSNo;
import edu.com.Panel.HocSinhA;
import edu.com.Panel.LopX;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author nguyen van cuong
 */
public class ListKoala extends javax.swing.JFrame {
    DefaultMutableTreeNode nodex;    
    
    ConnectData connectData;
    Connection connect;
    private JTable table;
    private String nameadmin="XuanNT";
    private int idtrungtam;
    public void setTable(JTable table) {
        this.table = table;
    }
    
    public JTable getTable() {
        return this.table;
    }
    /**
     * Creates new form List
     */
    public static JFrame frame;
    public ListKoala() {
        initComponents();
        taoTree();
        Panel_GDChinh.removeAll();
        
        Panel_GDChinh.add("Trung Tâm",Panel_trungtam);
        new CloseTabButton(Panel_GDChinh,0);
        frame = this;
       
    }// khoi tao
    public void taoTree()// tao ra tree dung co so vao
    {   
        String tentrungtam=null;
        String tenkhoi=null;
        String tenlop=null;
        DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Koala House");
        DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Koala House Bà Triệu");
        DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("NẮNG MAI (SUNSHINE)");
        DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("SUNSHINE_1");
        int idfali=0;
        int idlev=0;
        DataBase.DataTable data = new DataBase.DataTable();
        idfali=data.LayTenTrungTam(nameadmin);
        ThongTin.trungtam=idfali;
        this.idtrungtam=idfali;
        if (idfali==1)tentrungtam="Koala House Bà Triệu";
        else if(idfali==2) tentrungtam="Koala House Hoàng Ngân";   
        else if(idfali==3) tentrungtam="Koala House Phan Kế Bính";
        else if(idfali==4) tentrungtam="Koala House Nguyễn Huy Tự";
        else if(idfali==0) tentrungtam="Koala House Nguyễn Đức Sơn";
        
        System.out.println(tentrungtam);
        if(idfali!=5)
        {
            treeNode2= new javax.swing.tree.DefaultMutableTreeNode(tentrungtam);
            treeNode1.add(treeNode2);
            try {
                        // Them nguoi dung vao database.
                        ConnectData connectData = new ConnectData();
                        Connection connect;
                        ResultSet rs1,rs2;

                        connect = connectData.connectionDatabase();
                        Statement statements1 = connect.createStatement();
                        Statement statements2 = connect.createStatement();

                        rs1=(ResultSet) statements1.executeQuery("select DISTINCT Levels_Id from classes where Faculties_Id= '"+idfali+"'");
                        while(rs1.next())
                        {
                            idlev=rs1.getInt(1);
                            if (idlev==1) tenkhoi="NẮNG MAI (SUNSHINE)";
                            else if (idlev==2) tenkhoi="TỔ ONG (BEEHIVE)";
                            else if (idlev==3) tenkhoi="TỔ KIẾN (CHRYSALIS)";
                            else tenkhoi= tenkhoi="MẪU GIÁO (KINDERGARTEN)";
                            treeNode3= new javax.swing.tree.DefaultMutableTreeNode(tenkhoi);
                            treeNode2.add(treeNode3);
                            rs2=(ResultSet) statements2.executeQuery("select NameClass from classes where Levels_Id = '"+idlev+"' and Faculties_Id = '"+idfali+"' ");
                            while(rs2.next())
                            {
                               tenlop=rs2.getString(1);
                               treeNode4= new javax.swing.tree.DefaultMutableTreeNode(tenlop);
                               treeNode3.add(treeNode4);
                            }
                        }
                 } catch (SQLException ex) 
                        {

                        }


        }
        else if(idfali==5)
        {
            try 
            {
                    // Them nguoi dung vao database.
                    ConnectData connectData = new ConnectData();
                    Connection connect;
                    ResultSet rs1,rs2,rs3;
                    
                    connect = connectData.connectionDatabase();
                    Statement statements1 = connect.createStatement();
                    Statement statements2 = connect.createStatement();
                    Statement statements3 = connect.createStatement();
                    rs3=(ResultSet) statements3.executeQuery("select Id from faculties ");
                    
                    while(rs3.next())
                    {
                        
                        idfali=rs3.getInt(1);
                        if (idfali==1)tentrungtam="Koala House Bà Triệu";
                        else if(idfali==2) tentrungtam="Koala House Hoàng Ngân";   
                        else if(idfali==3) tentrungtam="Koala House Phan Kế Bính";
                        else if(idfali==4) tentrungtam="Koala House Nguyễn Huy Tự";
                        treeNode2= new javax.swing.tree.DefaultMutableTreeNode(tentrungtam);
                        treeNode1.add(treeNode2);
                        rs1=(ResultSet) statements1.executeQuery("select DISTINCT Levels_Id from classes where Faculties_Id= '"+idfali+"'");
                        while(rs1.next())
                        {
                            idlev=rs1.getInt(1);
                            if (idlev==1) tenkhoi="NẮNG MAI (SUNSHINE)";
                            else if (idlev==2) tenkhoi="TỔ ONG (BEEHIVE)";
                            else if (idlev==3) tenkhoi="TỔ KIẾN (CHRYSALIS)";
                            else tenkhoi= tenkhoi="MẪU GIÁO (KINDERGARTEN)";
                            treeNode3= new javax.swing.tree.DefaultMutableTreeNode(tenkhoi);
                            treeNode2.add(treeNode3);
                            rs2=(ResultSet) statements2.executeQuery("select NameClass from classes where Levels_Id = '"+idlev+"' and Faculties_Id = '"+idfali+"' ");
                            while(rs2.next())
                            {
                               tenlop=rs2.getString(1);
                               treeNode4= new javax.swing.tree.DefaultMutableTreeNode(tenlop);
                               treeNode3.add(treeNode4);
                            }
                        }
                        
                        
                    }
                    
            }    catch (SQLException ex) 
                    {
                        
                    }
        
                          
        
            
        }
       
        /*
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("SUNSHINE_2");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("SUNSHINE_3");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ ONG (BEEHIVE)");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("BEEHIVE_1");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("BEEHIVE_2");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("BEEHIVE_3");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ KÉN (CHRYSALIS)");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("CHRYSALIS_1");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("CHRYSALIS_2");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("MẪU GIÁO (KINDERGARTEN)");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("KINDERGARTEN_1");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("KINDERGARTEN_2");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Koala House Hoàng Ngân");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("NẮNG MAI (SUNSHINE)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ ONG (BEEHIVE)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ KÉN (CHRYSALIS)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("MẪU GIÁO (KINDERGARTEN)");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Koala House Phan Kế Bính");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("NẮNG MAI (SUNSHINE)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ ONG (BEEHIVE)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ KÉN (CHRYSALIS)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("MẪU GIÁO (KINDERGARTEN)");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Koala House Nguyễn Huy Tự");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("NẮNG MAI (SUNSHINE)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ ONG (BEEHIVE)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TỔ KÉN (CHRYSALIS)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("MẪU GIÁO (KINDERGARTEN)");
        treeNode2.add(treeNode3);
        */
        //treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setAutoscrolls(true);
        jTree1.setMinimumSize(new java.awt.Dimension(100, 80));
        jTree1.setPreferredSize(new java.awt.Dimension(300, 80));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            /*public void mouseReleased(java.awt.event.MouseEvent evt) {
                JtreeMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtreeMouseClicked(evt);
            }*/
        });
        jScrollPane1.setViewportView(jTree1);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tuychon = new javax.swing.JPopupMenu();
        them = new javax.swing.JMenuItem();
        chitiet = new javax.swing.JMenuItem();
        sua = new javax.swing.JMenuItem();
        xoa = new javax.swing.JMenuItem();
        tree = new javax.swing.JPopupMenu();
        trinhdo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        A = new javax.swing.JMenuItem();
        D = new javax.swing.JMenuItem();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        Panel_GDChinh = new javax.swing.JTabbedPane();
        Panel_trungtam = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Namhoc = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Menu_TaiKhoan = new javax.swing.JMenu();
        Menu_ThemTaiKhoan = new javax.swing.JMenuItem();
        Menu_SuaTaiKhoan = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        Menu_Update = new javax.swing.JMenuItem();
        Menu_DangXuat = new javax.swing.JMenuItem();
        Menu_thoat = new javax.swing.JMenuItem();
        CapNhatNamHoc = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Menu_DongBo = new javax.swing.JMenuItem();
        Menu_BackUp = new javax.swing.JMenuItem();

        them.setText("them");
        tuychon.add(them);

        chitiet.setText("chi tiet");
        tuychon.add(chitiet);

        sua.setText("sua");
        tuychon.add(sua);

        xoa.setText("xoa");
        tuychon.add(xoa);

        trinhdo.setText("Ra Trường");
        trinhdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatruongActionPerformed(evt);
            }
        });
        tree.add(trinhdo);

        jMenu1.setText("Thêm,Xóa");

        A.setText("Thêm Lớp");
        A.setToolTipText("");
        A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AActionPerformed(evt);
            }
        });
        jMenu1.add(A);

        D.setText("Xóa");
        D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DActionPerformed(evt);
            }
        });
        jMenu1.add(D);

        tree.add(jMenu1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jSplitPane2.setAutoscrolls(true);
        jSplitPane2.setEnabled(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTree1.setAutoscrolls(true);
        jTree1.setMinimumSize(new java.awt.Dimension(100, 80));
        jTree1.setPreferredSize(new java.awt.Dimension(300, 80));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JtreeMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtreeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel3);

        Panel_GDChinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_GDChinh.setPreferredSize(new java.awt.Dimension(1110, 570));
        Panel_GDChinh.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Panel_GDChinhStateChanged(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/800-IMG_7699.jpg"))); // NOI18N

        javax.swing.GroupLayout Panel_trungtamLayout = new javax.swing.GroupLayout(Panel_trungtam);
        Panel_trungtam.setLayout(Panel_trungtamLayout);
        Panel_trungtamLayout.setHorizontalGroup(
            Panel_trungtamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_trungtamLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
        );
        Panel_trungtamLayout.setVerticalGroup(
            Panel_trungtamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_trungtamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        Panel_GDChinh.addTab("Trung Tâm", Panel_trungtam);

        jScrollPane3.setViewportView(Panel_GDChinh);

        jSplitPane2.setRightComponent(jScrollPane3);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/List.jpg"))); // NOI18N
        jLabel21.setToolTipText("Danh Sách Học Sinh");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/images.jpg"))); // NOI18N
        jLabel22.setToolTipText("Danh Sách Lớp");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/Bill.png"))); // NOI18N
        jLabel24.setToolTipText("Các Loại Phí");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/DSHS.JPG"))); // NOI18N
        jLabel26.setToolTipText("Danh Sách Nợ");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/bill2.jpg"))); // NOI18N
        jLabel27.setToolTipText("Tạo Hóa Đơn");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/com/image/print.png"))); // NOI18N
        jLabel28.setToolTipText("Print");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        jLabel23.setText("DS Lớp");

        jLabel25.setText("DS HS");

        jLabel29.setText("Các Loại Phí");

        jLabel30.setText("DS Nợ");

        jLabel31.setText("Hóa Đơn");

        jLabel32.setText("Print");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel24))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel29)))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel30))
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel31))
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel32))
                .addContainerGap(902, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(1, 1, 1)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        Namhoc.setText("Hệ Thống");
        Namhoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamhocActionPerformed(evt);
            }
        });
        Namhoc.add(jSeparator1);

        Menu_TaiKhoan.setText("Tài khoản");

        Menu_ThemTaiKhoan.setText("Thêm");
        Menu_ThemTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_ThemTaiKhoanActionPerformed(evt);
            }
        });
        Menu_TaiKhoan.add(Menu_ThemTaiKhoan);

        Menu_SuaTaiKhoan.setText("Sửa");
        Menu_SuaTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_SuaTaiKhoanActionPerformed(evt);
            }
        });
        Menu_TaiKhoan.add(Menu_SuaTaiKhoan);

        jMenuItem10.setText("Xóa");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseClicked(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        Menu_TaiKhoan.add(jMenuItem10);

        Namhoc.add(Menu_TaiKhoan);

        Menu_Update.setText("Cập nhật hệ thống");
        Namhoc.add(Menu_Update);

        Menu_DangXuat.setText("Đăng Xuất");
        Menu_DangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_DangXuatActionPerformed(evt);
            }
        });
        Namhoc.add(Menu_DangXuat);

        Menu_thoat.setText("Thoát");
        Menu_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_thoatActionPerformed(evt);
            }
        });
        Namhoc.add(Menu_thoat);

        CapNhatNamHoc.setText("Cập Nhật Năm Học");
        CapNhatNamHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatNamHocActionPerformed(evt);
            }
        });
        Namhoc.add(CapNhatNamHoc);

        jMenuBar1.add(Namhoc);

        jMenu2.setText("Dữ Liệu");

        Menu_DongBo.setText("Đồng Bộ");
        jMenu2.add(Menu_DongBo);

        Menu_BackUp.setText("Backup");
        jMenu2.add(Menu_BackUp);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseReleased
        // TODO add your handling code here:
     
      
    }//GEN-LAST:event_jTree1MouseReleased

    private void Menu_HSDatCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_HSDatCocActionPerformed
        // TODO add your handling code here:
        DSDatCoc dSDat= new DSDatCoc(this,rootPaneCheckingEnabled);
        dSDat.setAlwaysOnTop(rootPaneCheckingEnabled);
        dSDat.setLocation(420, 130);
        dSDat.show();
    
    }//GEN-LAST:event_Menu_HSDatCocActionPerformed

    private void Menu_DanhSachLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu_DanhSachLopMouseClicked
        // TODO add your handling code here:
        DSLop dslop1 = new DSLop(idtrungtam);
        Panel_GDChinh.add("Danh Sach Lop", dslop1);
        Panel_GDChinh.setSelectedComponent(dslop1);
        new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
    }//GEN-LAST:event_Menu_DanhSachLopMouseClicked

    private void Menu_DanhSachLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_DanhSachLopActionPerformed
        // TODO add your handling code here:
        DSLop dslop2 = new DSLop(idtrungtam);
        Panel_GDChinh.add("Danh Sach Lop", dslop2);
        Panel_GDChinh.setSelectedComponent(dslop2);
        dslop2.center = Panel_GDChinh;
        new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
    }//GEN-LAST:event_Menu_DanhSachLopActionPerformed

    private void Menu_ThemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ThemLopActionPerformed
        // TODO add your handling code here:
        ThemSuaLop themSuaLop= new ThemSuaLop(this, rootPaneCheckingEnabled);
        themSuaLop.setLocation(420, 130);
        themSuaLop.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_Menu_ThemLopActionPerformed

    private void Menu_SuaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_SuaLopActionPerformed
        // TODO add your handling code here:
    ThemSuaLop themSuaLop= new ThemSuaLop(this, rootPaneCheckingEnabled);
        //themSuaLop.show();
    
        themSuaLop.setLocation(420, 130);
        themSuaLop.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_Menu_SuaLopActionPerformed

    private void Panel_GDChinhStateChanged(javax.swing.event.ChangeEvent evt) {                                           
        table = new JTable();
        JPanel panel, itemPanel;
        Component component;
        Component[] components, itemComponents;
        Component item;
        JScrollPane scrollPane;
        int selectedIndex = Panel_GDChinh.getSelectedIndex();
        System.out.println("Index: " + selectedIndex);
        if(selectedIndex >= 0) {
            component = Panel_GDChinh.getComponentAt(selectedIndex);
            System.err.println(component.toString());
            if(component instanceof JPanel) {
                System.out.println("OK");
                panel = (JPanel) component;
                components = panel.getComponents();
                for(int i = 0 ; i < components.length ; i++) {
                    item = components[i];
                    if(item instanceof JPanel) {
                        System.out.println("NGON");
                        itemPanel = (JPanel) item;
                        itemComponents = itemPanel.getComponents();
                        for(int j = 0 ; j < itemComponents.length ; j++) {
                            item = itemComponents[j];
                            if(item instanceof JScrollPane) {
                                System.out.println("DM NGON VAI");
                                scrollPane = (JScrollPane) item;
                                JViewport viewPort = scrollPane.getViewport();
                                table = (JTable) viewPort.getView();
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("DM L'");
        }
    }                                          

    private void Menu_ThemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ThemTaiKhoanActionPerformed
        // TODO add your handling code here:
               String result = "";
        try {
            // TODO add your handling code here:
            // Kiem tra xem user co duong quyen tao tai khoan moi hay khong
            connectDataBase();
            String user = ConnectData.user;
            Statement stmt = connect.createStatement();
            //String result;
            
            java.sql.ResultSet rs = stmt.executeQuery("SELECT IsRoot FROM projectkoala.accounts WHERE UserName = '" + user + "';");
            while(rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListKoala.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result.equals("0")) {
            JOptionPane.showMessageDialog(this, "Bạn không đủ quyền tạo tài khoản mới !", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            TaoTaiKhoan taoTaiKhoan= new TaoTaiKhoan(this,rootPaneCheckingEnabled);
            //TaoTaiKhoan.setChanged(false);
            TaoTaiKhoan.setChanged(true);
            taoTaiKhoan.setLocation(420, 130);
            taoTaiKhoan.show();
        }
        
    }//GEN-LAST:event_Menu_ThemTaiKhoanActionPerformed

    private void Menu_SuaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_SuaTaiKhoanActionPerformed
        // TODO add your handling code here:
        TaoTaiKhoan taoTaiKhoan= new TaoTaiKhoan(this);
        TaoTaiKhoan.setChanged(false);
        taoTaiKhoan.setLocation(420, 130);
        taoTaiKhoan.show();
    }//GEN-LAST:event_Menu_SuaTaiKhoanActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        int a=-1;    
        for(int i=0;i<Panel_GDChinh.getTabCount();i++)
            {
                if("Danh Sách HS".equals(Panel_GDChinh.getTitleAt(i)))
                {a+=1;
                Panel_GDChinh.setSelectedIndex(i);
                }
            }
        if(a==-1)
        {
        DSHS dshs = new DSHS();
        Panel_GDChinh.add("Danh Sách HS", dshs);
        Panel_GDChinh.setSelectedComponent(dshs);
        dshs.center = Panel_GDChinh;
        new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        int a=-1;    
        for(int i=0;i<Panel_GDChinh.getTabCount();i++)
            {
                if("Danh Sách Lớp".equals(Panel_GDChinh.getTitleAt(i)))
                {   a+=1;
                Panel_GDChinh.setSelectedIndex(i);
                }
            }
        if(a==-1)
        {
        DSLop dsl = new DSLop(idtrungtam);
        dsl.setIdTrungTam(idtrungtam);
        Panel_GDChinh.add("Danh Sách Lớp",dsl);
        Panel_GDChinh.setSelectedComponent(dsl);
        dsl.center = Panel_GDChinh;
        dsl.listkoala= this;
        
        new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
        }
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        int a=-1;    
        for(int i=0;i<Panel_GDChinh.getTabCount();i++)
            {
                if("Các Loại Phí".equals(Panel_GDChinh.getTitleAt(i)))
                { a+=1;
                Panel_GDChinh.setSelectedIndex(i);
                }
            }
        if(a==-1)
        {
        CacLoaiPhi loaiphi = new CacLoaiPhi();
        Panel_GDChinh.add("Các Loại Phí", loaiphi);
        Panel_GDChinh.setSelectedComponent(loaiphi);
        new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
        }
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        int a=-1;    
        for(int i=0;i<Panel_GDChinh.getTabCount();i++)
            {
                if("Danh Sách Nợ".equals(Panel_GDChinh.getTitleAt(i)))
                { a+=1;
                Panel_GDChinh.setSelectedIndex(i);
                }
            }
        if(a==-1)
        {
        DSNo dsno = new DSNo();
       Panel_GDChinh.add("Danh Sách Nợ",dsno);
       Panel_GDChinh.setSelectedComponent(dsno);
       dsno.center =Panel_GDChinh;
       new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
        }
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        HoaDon receipts= new HoaDon(this, rootPaneCheckingEnabled);
        receipts.setAlwaysOnTop(rootPaneCheckingEnabled);
        receipts.setLocation(420,20);
        receipts.show();
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jMenuItem10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem10MouseClicked

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
               String result = "";
        String user = ConnectData.user;
        try {
            // TODO add your handling code here:
            connectDataBase();
            Statement stmt = connect.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT IsRoot FROM projectkoala.accounts WHERE UserName = '" + user + "';" );
            while(rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListKoala.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result.equals("0")) {
            JOptionPane.showMessageDialog(this, "Bạn không đủ quyền thực hiện thao tác này !", "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            DSTaiKhoan dstaikhoan= new DSTaiKhoan(this, rootPaneCheckingEnabled);
            dstaikhoan.setAlwaysOnTop(rootPaneCheckingEnabled);
            dstaikhoan.setLocation(420, 20);
            dstaikhoan.show();
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void Menu_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_DangXuatActionPerformed
        // TODO add your handling code here:
        DangNhapVao d= new DangNhapVao(this, rootPaneCheckingEnabled);
        this.dispose();
        d.setLocation(420, 20);
        d.show();
        
        
    }//GEN-LAST:event_Menu_DangXuatActionPerformed

    private void Menu_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_thoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Menu_thoatActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        //JTextField headderFiled = new JTextField("Danh sach hoc sinh");
        int selectedInDex = Panel_GDChinh.getSelectedIndex();
        String label = Panel_GDChinh.getTitleAt(selectedInDex);
        MessageFormat headderField = new MessageFormat(label);
        MessageFormat footerField = null;
        try {
            boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, headderField, footerField, true, null, true, null);
            if(complete) {
                JOptionPane.showMessageDialog(this, "Printting complete", "Printting Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Printting Cancel", "Printting Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            Logger.getLogger(ListKoala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel28MouseClicked
// xu ly su kien kick chuot vao tree
    private void JtreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtreeMouseClicked
        // TODO add your handling code here:
        JTree node = (JTree)evt.getSource();
        TreePath path = node.getPathForLocation(evt.getX(), evt.getY());
        node.setSelectionPath(path);
        DefaultMutableTreeNode selNode=(DefaultMutableTreeNode)path.getLastPathComponent();
        if(selNode.isLeaf()){
            int a=0;
            LopX dslopa= new LopX(selNode.toString());
            for(int i=0;i<Panel_GDChinh.getTabCount();i++)
            {
                if(selNode.toString().equals(Panel_GDChinh.getTitleAt(i)))
                {    a+=1;
                    Panel_GDChinh.setSelectedIndex(i);
                }
            }
            if(a==0)
            {
            Panel_GDChinh.add(selNode.toString(),dslopa);
            Panel_GDChinh.setSelectedComponent(dslopa);
            dslopa.center = Panel_GDChinh;
            new CloseTabButton(Panel_GDChinh,Panel_GDChinh.getComponentCount()-2);
            }
        }
        else{}
    }//GEN-LAST:event_JtreeMouseClicked
// xu ly su kien chuot phai
    private void JtreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtreeMouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            JTree node = (JTree)evt.getSource();
            TreePath path = node.getPathForLocation(evt.getX(), evt.getY());
            if(path==null){
                return;
            }
            node.setSelectionPath(path);
            nodex=(DefaultMutableTreeNode)path.getLastPathComponent();
            tree.show(this,evt.getXOnScreen()-this.getX(),evt.getYOnScreen()-this.getY());
            
        }
    }//GEN-LAST:event_JtreeMouseReleased

/*
    private void Panel_GDChinhStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Panel_GDChinhStateChanged

    }//GEN-LAST:event_Panel_GDChinhStateChanged
*/
    private void AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AActionPerformed
        // TODO add your handling code here:
         if(nodex.getFirstChild().isLeaf())
        {
             ThemSuaLop lop = new ThemSuaLop(null,true);
             lop.setThemSuaLop(true);//day la them
             lop.setVisible(true);
             lop.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200);
             lop.show();
             String a=null;
             String b=nodex.toString();
             lop.setKhoiName(b);
             if(lop.getButton())
             {
                 a=lop.getTenLop();
                 DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
                 model.insertNodeInto(new DefaultMutableTreeNode(a),nodex , nodex.getChildCount());
             }
        }
        else 
        {
            
                String message =String.format( "Bạn Không Thể Tạo Lớp Ở Đây");
                JOptionPane.showMessageDialog( null, message );
        }
    }//GEN-LAST:event_AActionPerformed

    private void DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DActionPerformed
        // TODO add your handling code here:
        if(nodex.isLeaf())
        {
            int id=0;int sohs=0;
             try 
             {
                        ConnectData connectData = new ConnectData();
                        Connection connect;
                        ResultSet rs1,rs2;

                        connect = connectData.connectionDatabase();
                        Statement statements1 = connect.createStatement();
                        Statement statements2 = connect.createStatement();
                        String tentrungtam= nodex.getParent().getParent().toString();
                        if(tentrungtam.equals("Koala House Bà Triệu")) tentrungtam="1";
                        else if (tentrungtam.equals("Koala House Hoàng Ngân")) tentrungtam="2";
                        else if (tentrungtam.equals("Koala House Phan Kế Bình")) tentrungtam="3";
                        else tentrungtam="4";
                        System.out.println(tentrungtam);
                        rs1=(ResultSet) statements1.executeQuery("select * from classes where NameClass = '" + nodex.toString() + "' and Faculties_Id= '" + tentrungtam + "'");
                        System.out.println("select * from classes where NameClass = '" + nodex.toString() + "' and Faculties_Id= '" + tentrungtam + "'");
                        while(rs1.next())
                        {
                            id=rs1.getInt(1);
                        }
                        System.out.println(id);
                        rs2= (ResultSet) statements2.executeQuery("select count(Students_Id) from classes_has_students where Classes_Id ='"+id+"' and Faculties_Id= '" + tentrungtam + "'");
                        System.out.println("select count(Students_Id) from classes_has_students where Classes_Id ='"+id+"' and Faculties_Id= '" + tentrungtam + "'");
                        while(rs2.next())
                        {
                            sohs=rs2.getInt(1);
                        }
                        System.out.println(sohs);
                        if(sohs==0)
                        {
                            
                            String query;
                            PreparedStatement pstmt;
                            
                            query ="delete from classes_has_students where Classes_Id = '" + id + "' and Faculties_Id= '" + tentrungtam + "'";
                            System.out.println(query);
                            pstmt = connect.prepareStatement(query);
                            pstmt.executeUpdate();
                            query = "delete from classes where Id='" + id + "' and Faculties_Id= '" + tentrungtam + "'";
                            System.out.println(query);
                            pstmt = connect.prepareStatement(query);
                            pstmt.executeUpdate();
                            DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
                            model.removeNodeFromParent(nodex);
        
                        }
                        else
                        {
                            String message =String.format( "trong lớp vẫn còn hs, bạn cần thực hiện chuyển lớp hoặc xóa hs để có thể xóa lớp");
                            JOptionPane.showMessageDialog( null, message );
                        }
             }
             catch (SQLException ex) 
                        {
                            
                        }

            
        }
        else
        {
            String message =String.format( "Bạn Không Thể Tạo Lớp Ở Đây");
            JOptionPane.showMessageDialog( null, message );
        }
    }//GEN-LAST:event_DActionPerformed

    private void RatruongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatruongActionPerformed
        // TODO add your handling code here:
         if(nodex.isLeaf())
        {
            String tenlop=nodex.toString();
            int idtenlop;
            idtenlop= new DataBase.DataTable().LayIdTenLop(tenlop);
            new DataBase.SQLJTree().ratruong(idtenlop);
        }
        else{
            String message =String.format( "Bạn Không Thể Thực Hiện Thao Tác Ở Đây");
            JOptionPane.showMessageDialog( null, message );
        
                }        
        
    }//GEN-LAST:event_RatruongActionPerformed

    private void CapNhatNamHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatNamHocActionPerformed
        // TODO add your handling code here:
        Nam nam = new Nam(frame, rootPaneCheckingEnabled);
        nam.setVisible(true);
    }//GEN-LAST:event_CapNhatNamHocActionPerformed

    private void NamhocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamhocActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_NamhocActionPerformed
     
    public void connectDataBase() {
        connectData = new ConnectData();
        connect = connectData.connectionDatabase();
    }
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
            java.util.logging.Logger.getLogger(ListKoala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListKoala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListKoala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListKoala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListKoala().setVisible(true);
            }
        });
        
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem A;
    private javax.swing.JMenuItem CapNhatNamHoc;
    private javax.swing.JMenuItem D;
    private javax.swing.JMenuItem Menu_BackUp;
    private javax.swing.JMenuItem Menu_DangXuat;
    private javax.swing.JMenuItem Menu_DongBo;
    private javax.swing.JMenuItem Menu_SuaTaiKhoan;
    private javax.swing.JMenu Menu_TaiKhoan;
    private javax.swing.JMenuItem Menu_ThemTaiKhoan;
    private javax.swing.JMenuItem Menu_Update;
    private javax.swing.JMenuItem Menu_thoat;
    private javax.swing.JMenu Namhoc;
    private javax.swing.JTabbedPane Panel_GDChinh;
    private javax.swing.JPanel Panel_trungtam;
    private javax.swing.JMenuItem chitiet;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem sua;
    private javax.swing.JMenuItem them;
    private javax.swing.JPopupMenu tree;
    private javax.swing.JMenuItem trinhdo;
    private javax.swing.JPopupMenu tuychon;
    private javax.swing.JMenuItem xoa;
    // End of variables declaration//GEN-END:variables
}
class CloseTabButton extends JPanel implements ActionListener {
  private JTabbedPane pane;
  public CloseTabButton(JTabbedPane pane, int index) {
    this.pane = pane;
    setOpaque(false);
    add(new JLabel(
        pane.getTitleAt(index),
        pane.getIconAt(index),
        JLabel.LEFT));
    Icon closeIcon = new CloseIcon();
    JButton btClose = new JButton(closeIcon);
    btClose.setPreferredSize(new Dimension(
        closeIcon.getIconWidth(), closeIcon.getIconHeight()));
    add(btClose);
    btClose.addActionListener(this);
    pane.setTabComponentAt(index, this);
  }
  public void actionPerformed(ActionEvent e) {
    int i = pane.indexOfTabComponent(this);
    if (i != -1) {
      pane.remove(i);
    }
  }
}
class CloseIcon implements Icon {
  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(Color.RED);
    g.drawLine(6, 6, getIconWidth() - 7, getIconHeight() - 7);
    g.drawLine(getIconWidth() - 7, 6, 6, getIconHeight() - 7);
  }
  public int getIconWidth() {
    return 17;
  }
  public int getIconHeight() {
    return 17;
  }
 }
