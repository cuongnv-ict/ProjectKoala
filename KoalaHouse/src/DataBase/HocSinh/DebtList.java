/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;
import DataBase.ConnectData;
import DataBase.DataTable;
import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pham The Quyen
 */
public class DebtList {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public DebtList(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHocSinhNoPhi(JTable table) {
        int stt = 1;
        try {
            Object[] nameColumn = {"Số TT", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Tiền Nợ"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where (students.id in(select students_id from students_has_cost where isdebt = 1 group by students_id) or debt!=0) and isactive = 1");
            while (rs1.next()) {
                Object[] str = new Object[7];
                str[0] = stt;stt++;
                str[1] = rs1.getString(3);
                str[2] = new XuLiXau().NgayThangNam(rs1.getString(4));
                switch (rs1.getInt(7)) {
                    case 1:
                        str[3] = "Chính Quy";
                        break;
                    case 0:
                        str[3] = "Chương Trình Bạn Là Khách";
                        break;
                }
                str[4] = rs1.getString(5);
                str[5] = rs1.getString(6);
                str[6] = XuLy.setMoney(rs1.getString(9));
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public ArrayList GetIdStudent(){
    ArrayList data = new ArrayList();
        try {
            rs1 = statement.executeQuery("select * from students where (students.id in(select students_id from students_has_cost where isdebt = 1 group by students_id) or debt!=0) and isactive = 1");
            while (rs1.next()) {
                Object str = new Object();
                str = rs1.getString(1);
                data.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
