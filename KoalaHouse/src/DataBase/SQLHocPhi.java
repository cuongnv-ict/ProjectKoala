/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import edu.com.ThongTin;
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
 * @author nguyen van cuong
 */
public class SQLHocPhi {

    private Connection connect;
    ResultSet rs1;
    Statement statement;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLHocPhi() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Loi truy nhap db
        }
    }

    public void BangDanhSachPhi(JTable table) {
        try {
            Object[] nameColumn = {"Tên", "Kì học", "Năm học", "Ngày bắt đầu", "Ngày kết thúc", "Giá", "Đánh dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from cost,semesters where cost.year = semesters.year and semesters.semesternumber=cost.semesters and semesters.Faculties_Id = cost.Faculties_Id and cost.Faculties_Id = " + ThongTin.trungtam);
            while (rs1.next()) {
                Object str[] = new Object[7];
                str[0] = rs1.getString(4);
                switch (rs1.getInt(3)) {
                    case 1:
                        str[1] = "Kỳ 1";
                        break;
                    case 2:
                        str[1] = "Kỳ 2";
                        break;
                    case 3:
                        str[1] = "Kỳ 3";
                        break;
                    case 4:
                        str[1] = "Kỳ hè";
                        break;
                    case 5:
                        str[1] = "Cả năm";
                        break;
                }
                str[2] = rs1.getString(6).substring(0, 4);
                str[3] = rs1.getString(10);
                str[4] = rs1.getString(11);
                str[5] = rs1.getString(5);
                if (((String) str[5]).charAt(0) == '-') {
                    str[5] = ((String) str[5]).substring(1);
                }
                str[6] = false;
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean xoaHocPhi(int ky, String name) {
        try {
            statement.execute("delete from cost where semesters = " + ky + " and namecost = \"" + name + "\"");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loai phí:" + name + " của kỳ " + ky + " này đã có học sinh được tính phí, không thể xóa", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean suaHocPhi(Vector vector, Vector oldVertor) {
        try {
            Pstate = connect.prepareStatement("select id from cost where namecost = ? and semesters = ? and year = ?");
            Pstate.setString(1, vector.get(0).toString());
            Pstate.setString(2, vector.get(1).toString());
            Pstate.setString(3, vector.get(2).toString());
            rs1 = Pstate.executeQuery();
            if (rs1.next()) {
                JOptionPane.showMessageDialog(null, "Loại phí đã tồn tại, bạn không thể thay đổi", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            Pstate = connect.prepareStatement("select year from semesters where year = ?");
            Pstate.setString(1, vector.get(2).toString());
            if (!rs1.next()) {
                JOptionPane.showMessageDialog(null, "Năm học bạn chọn chưa được thiết lập", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            rs1 = Pstate.executeQuery();
            Pstate = connect.prepareStatement("update cost set semesters=?,namecost=?,amount=?,year=? where year = ? and semesters = ? and namecost =?");
            Pstate.setString(1, vector.get(1).toString());
            Pstate.setString(2, vector.get(0).toString());
            Pstate.setString(3, vector.get(3).toString());
            Pstate.setString(4, vector.get(2).toString());
            Pstate.setString(5, oldVertor.get(2).toString());
            Pstate.setString(6, oldVertor.get(1).toString());
            Pstate.setString(7, oldVertor.get(0).toString());
            Pstate.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hãy chắc chắn rằng kì học đó đã được thiết lập", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean themHocPhi(Vector vector) {
        try {
            Pstate = connect.prepareStatement("select id from cost where namecost = ? and semesters = ? and year = ?");
            Pstate.setString(1, vector.get(0).toString());
            Pstate.setString(2, vector.get(1).toString());
            Pstate.setString(3, vector.get(2).toString());
            rs1 = Pstate.executeQuery();
            if (rs1.next()) {
                JOptionPane.showMessageDialog(null, "Loại phí này đã tồn tại rồi", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            rs1 = statement.executeQuery("select max(id) from cost");
            int x;
            if (rs1.next()) {
                x = rs1.getInt(1) + 1;
            } else {
                x = 1;
            }
            Pstate = connect.prepareStatement("insert into cost (id,Faculties_Id,semesters,namecost,amount,year) values (?,?,?,?,?,?)");
            Pstate.setInt(1, x);
            Pstate.setInt(2, ThongTin.trungtam);
            Pstate.setInt(3, Integer.parseInt(vector.get(1).toString()));
            Pstate.setString(4, vector.get(0).toString());
            Pstate.setDouble(5, Double.parseDouble(vector.get(3).toString()));
            Pstate.setString(6, vector.get(2).toString());
            Pstate.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLHocPhi.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Bạn hãy kiểm tra lại học phí bạn vừa nhập", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
