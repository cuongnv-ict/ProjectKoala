/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

import edu.com.ThongTin;
import edu.com.XuLy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenvan
 */
public class SQLHocSinhTheoThang {
    private Connection connect;
    ResultSet rs1;
    Statement statement;
    PreparedStatement Pstate;
    DefaultTableModel model;

    public SQLHocSinhTheoThang() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Loi truy nhap db
        }
    }
    public void DanhSachHocSinhTrongKi(JTable table,int thang){
         try {
            Object[] nameColumn = {"STT", "SS", "Tên học sinh", "Lớp", "Ngày nhập học", "Ngày nghỉ học", "Chú ý"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select Id,NameCost,Semesters,year,StartDate,EndDate,Amount from cost where cost.Faculties_Id = " + ThongTin.trungtam + " order by year,Semesters");
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[8];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    str[6] = "";
                    str[7] = false;
                    data.add(str);
                }
            } else {
                do {
                    Object str[] = new Object[8];
                    str[0] = rs1.getString(1);
                    str[1] = rs1.getString(2);
                    switch (rs1.getInt(3)) {
                        case 1:
                            str[2] = "Học Kỳ 1";
                            break;
                        case 2:
                            str[2] = "Học Kỳ 2";
                            break;
                        case 3:
                            str[2] = "Học Kỳ 3";
                            break;
                        case 4:
                            str[2] = "Học Kỳ Hè";
                            break;
                        case 5:
                            str[2] = "Cả Năm";
                            break;
                    }
                    String[] arr = rs1.getString(4).split("-");
                    str[3] = arr[0] + "-" + String.valueOf(Integer.parseInt(arr[0]) + 1);
                    if (rs1.getString(5) != null) {
                        str[4] = XuLy.getDate(rs1.getString(5));
                    } else {
                        str[4] = "";
                    }
                    if (rs1.getString(6) != null) {
                        str[5] = XuLy.getDate(rs1.getString(6));
                    } else {
                        str[5] = "";
                    }
                    str[6] = rs1.getString(7);
                    if (((String) str[6]).charAt(0) == '-') {
                        str[6] = XuLy.setMoney(((String) str[6]).substring(1));
                    } else {
                        str[6] = XuLy.setMoney((String) str[6]);
                    }
                    str[7] = false;
                    data.add(str);
                } while (rs1.next());
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
        }
    }
    public void DanhSachHocSinhKiHe(){
        
    }
}
