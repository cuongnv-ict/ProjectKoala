/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.HocSinh;

import DataBase.ConnectData;
import DataBase.DataTable;
import edu.com.ThongTin;
import edu.com.XuLy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenvan
 */
public class ThongKeHoaDon {

    private Connection connect;
    ResultSet rs1, rs;
    Statement statement;
    PreparedStatement Pstate;

    public ThongKeHoaDon() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
        }
    }

    public int getThongKeHoaDon(JTable table, String date) {
        int count = 0;
        try {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            Object[] nameColumn = {"No.", "Họ Tên Học Sinh", "Lớp Học", "Người Thu", "Số Tiền", "Hình Thức Thu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT No,NamePayer,NameCasher,Number,IsTransfer,Reason FROM projectkoala.receipts where CreateDate = \""+date+"\" and Faculties_Id=" + ThongTin.trungtam);
            if (rs1.next()) {
                do {
                    Object str[] = new Object[6];
                    switch (ThongTin.trungtam) {
                        case 1:
                            str[0] = "BT" + XuLy.getNumber4(String.valueOf(rs1.getString(1)));
                            break;
                        case 2:
                            str[0] = "DQ" + XuLy.getNumber4(String.valueOf(rs1.getString(1)));
                            break;
                        case 3:
                            str[0] = "KB" + XuLy.getNumber4(String.valueOf(rs1.getString(1)));
                            break;
                        case 4:
                            str[0] = "HT" + XuLy.getNumber4(String.valueOf(rs1.getString(1)));
                            break;
                    }
                    str[1] = rs1.getString(2);
                    str[2] = rs1.getString(6);
                    str[3] = rs1.getString(3);
                    str[4] = XuLy.setMoney(rs1.getString(4));
                    count += rs1.getInt(4);
                    if (rs1.getInt(5) == 0) {
                        str[5] = "Tiền mặt";
                    } else {
                        str[5] = "Chuyển khoản";
                    }
                    data.add(str);
                } while (rs1.next());
            } else {
                for (int i = 0; i < 5; i++) {
                    Object str[] = new Object[6];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    data.add(str);
                }
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                Object[] str = new Object[6];
                for (int j = 0; j < 6; j++) {
                    str[j] = (data.get(i))[j];
                }
                rowColumn[i] = str;
            }
            DefaultTableModel model = new DefaultTableModel(rowColumn, nameColumn) {
                public Class getColumnClass(int columnIndex) {
                    return java.lang.String.class;
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            table.setModel(model);
            statement.close();
            connect.close();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}
