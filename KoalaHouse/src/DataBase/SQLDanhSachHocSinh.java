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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen van cuong
 */
public class SQLDanhSachHocSinh {

    private Connection connect;
    ResultSet rs1;
    Statement statement;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLDanhSachHocSinh() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
        }
    }

    public void BangDanhSachHocSinh(JTable table, int stype) {
        try {
            Object[] nameColumn = {"STT", "Tên", "Ngay Sinh", "Trình Độ", "Lớp", "Hình Thức Học"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select students.id,fullname,brithday,levels_id,nameclass,isclient  from students,classes,classes_has_students where students.id = students_id and classes.id=classes_id and students.isactive = " + stype);
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    String str[] = new String[6];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    data.add(str);
                }
            } else {
                do {
                    String str[] = new String[6];
                    str[0] = rs1.getString(1);
                    str[1] = rs1.getString(2);
                    str[2] = rs1.getString(3);
                    switch (rs1.getInt(4)) {
                        case 1:
                            str[3] = "NẮNG MAI (SUNSHINE)";
                            break;
                        case 2:
                            str[3] = "TỔ ONG (BEEHIVE)";
                            break;
                        case 3:
                            str[3] = "TỔ KÉN (CHRYSALIS)";
                            break;
                        case 4:
                            str[3] = "MẪU GIÁO (KINDERGARTEN)";
                            break;
                    }
                    str[4] = rs1.getString(5);
                    switch (rs1.getInt(6)) {
                        case 1:
                            str[5] = "Chính Quy";
                            break;
                        case 0:
                            str[5] = "Chương Trình Bạn Là Khách";
                            break;
                    }
                    data.add(str);
                } while (rs1.next());
            }

            XuLy.SapXepTen(data, 1);
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false
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

    public void xoaHocSinh(int id) {
        try {
            statement.execute("delete from classes_has_students where students_id = " + id);
            statement.execute("delete from students_has_cost where students_id = " + id);
            statement.execute("delete from lateday where students_id = " + id);
            statement.execute("delete from receipts where students_id = " + id);
            statement.execute("delete from leaves where students_id = " + id);
            statement.execute("delete from students where id = " + id);
        } catch (SQLException ex) {
            Logger.getLogger(SQLDanhSachHocSinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void khoiphucHocSinh(int id) {
        try {
            statement.execute("update students set isactive = 1 where id = " + id);
        } catch (SQLException ex) {
            Logger.getLogger(SQLDanhSachHocSinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
