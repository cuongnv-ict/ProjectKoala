/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import edu.com.XuLy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen van cuong
 */
public class SQLLopX {

    private Connection connect;
    ResultSet rs1;
    Statement statement;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLLopX() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Loi truy nhap db
        }
    }

    public void BangDanhHSLop(int classes_id, JTable table) {
        try {
            Object[] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from classes_has_students where classes_id = " + classes_id + " ) and isActive = 1");
            while (rs1.next()) {
                Object[] str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch (rs1.getInt(7)) {
                    case 0:
                        str[3] = "Chính Quy";
                        break;
                    case 1:
                        str[3] = "Chương Trình Bạn Là Khách";
                        break;
                }
                str[4] = rs1.getString(5);
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            XuLy.SapXepTen(data, 1);
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean themHocSinh(Vector vector, int id_class) {
        try {
            rs1 = statement.executeQuery("select max(id) from students");
            int x;
            if (rs1.next()) {
                x = rs1.getInt(1) + 1;
            } else {
                x = 1;
            }
            Pstate = connect.prepareStatement("insert into students(id,Faculties_Id,FullName,BrithDay,PhoneNumber,Representative,IsClient,Debt,IsActive)"
                    + " values (?,?,?,?,?,?,?,?,?)");
            Pstate.setString(1, String.valueOf(x));
            Pstate.setString(2, vector.get(0).toString());
            Pstate.setString(3, vector.get(1).toString());
            Pstate.setString(4, vector.get(2).toString());
            Pstate.setString(5, vector.get(3).toString());
            Pstate.setString(6, vector.get(4).toString());
            Pstate.setString(7, vector.get(5).toString());
            Pstate.setString(8, vector.get(6).toString());
            Pstate.setString(9, vector.get(7).toString());
            Pstate.execute();
            Pstate = connect.prepareStatement("insert into classes_has_students(Classes_Id,Students_Id,Faculties_Id) values(?,?,?)");
            Pstate.setString(1, String.valueOf(x));
            Pstate.setString(2, vector.get(0).toString());
            Pstate.setString(3, vector.get(1).toString());
            Pstate.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean suaHocSinh(Vector vector, int id_student) {
        return true;
    }

    public boolean xoaHocSinh(int id_student) {
        try {
            statement.execute("Update students set isactive = 0 where id = " + id_student);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean chuyenlopHocSinh(int []id,String name_class){
        return true;
    }
}