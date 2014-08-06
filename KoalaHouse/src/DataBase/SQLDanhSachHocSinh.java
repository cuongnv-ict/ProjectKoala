/*
 * To change this template, choose Tools | Templates
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen van cuong
 */
public class SQLDanhSachHocSinh {

    private Connection connect;
    private ResultSet rs1;
    private Statement statement;
    private PreparedStatement Pstate;
    private DefaultTableModel model;
    private Object[][] rowColumn;

    public SQLDanhSachHocSinh() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
        }
    }

    public boolean xoaHocSinh(int id) {
        try {
            statement.execute("delete from classes_has_students where students_id = " + id);
            statement.execute("delete from students_has_cost where students_id = " + id);
            statement.execute("delete from lateday where students_id = " + id);
            statement.execute("delete from receipts where students_id = " + id);
            statement.execute("delete from leaves where students_id = " + id);
            statement.execute("delete from students where id = " + id);
            statement.execute("delete from buslist where idStudents = " + id);
            statement.execute("delete from learnsummer where idStudents = " + id);
            statement.execute("delete from refund where idStudent = " + id);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLDanhSachHocSinh.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<Object[]> DanhSachHocSinh(JTable table) {
        ArrayList<Object[]> arr_title = this.DanhSachLop();
        ArrayList<Object[]> info = new ArrayList<Object[]>();
        int column_size = arr_title.size() + 1;
        Object[] title = new Object[column_size];
        ArrayList<Object[]>[] arr_content = new ArrayList[column_size - 1];
        int max = 0;
        for (int i = 0; i < column_size; i++) {
            if (i == 0) {
                title[0] = "STT";
                continue;
            }
            title[i] = arr_title.get(i - 1)[1];
            arr_content[i - 1] = this.DanhSachHocSinh(Integer.parseInt(arr_title.get(i - 1)[0].toString()), 1);

            if (max < Integer.parseInt(arr_title.get(i - 1)[2].toString())) {
                max = Integer.parseInt(arr_title.get(i - 1)[2].toString());
            }
        }
        Object[][] rowColumn = new Object[max + 1][];
        for (int i = 0; i < max; i++) {
            Object[] o = new Object[column_size];
            for (int j = 0; j < column_size; j++) {
                if (j == 0) {
                    o[j] = i + 1;
                    continue;
                }
                if (i < arr_content[j - 1].size()) {
                    o[j] = arr_content[j - 1].get(i)[1];
                } else {
                    o[j] = "";
                }
            }
            rowColumn[i] = o;
        }
        Object[] name = new Object[column_size];
        for (int i = 0; i < column_size; i++) {
            if (i == 0) {
                name[i] = "Giáo Viên";
                continue;
            }
            name[i] = arr_title.get(i - 1)[3];
        }
        rowColumn[max] = name;
        model = new DefaultTableModel(rowColumn, title) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        table.setModel(model);
        for (int i = 0; i < column_size; i++) {
            Object[] in = new Object[max + 1];
            for (int j = 0; j <= max; j++) {
                if (i == 0) {
                    in[j] = "s";
                    continue;
                }
                if (j == max) {
                    in[j] = "s";
                    continue;
                }
                if (j < arr_content[i - 1].size()) {
                    if (arr_content[i - 1].get(j)[2] == null) {
                        in[j] = arr_content[i - 1].get(j)[0] + "-" + arr_title.get(i - 1)[0];
                        continue;
                    }
                    if (arr_content[i - 1].get(j)[2].toString().equals("1")) {
                        in[j] = arr_content[i - 1].get(j)[0] + "-" + arr_title.get(i - 1)[0];
                    } else {
                        in[j] = "-" + arr_content[i - 1].get(j)[0] + "-" + arr_title.get(i - 1)[0];
                    }
                } else {
                    if (j < Integer.parseInt(arr_title.get(i - 1)[2].toString())) {
                        in[j] = "0" + "-" + arr_title.get(i - 1)[0];
                    } else {
                        in[j] = "x";
                    }
                }
            }
            info.add(in);
        }
        if (max == 0) {
            Object[][] row = new Object[1][];

            Object[] o = new Object[column_size];
            for (int j = 0; j < column_size; j++) {
                o[j] = 1;
            }
            row[0] = o;
            XuLy.resizeColumnWidth(table, XuLy.getSize(row));
        } else {
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        }
        return info;
    }

    public ArrayList<Object[]> DanhSachHocSinh_Stype(JTable table, int stype) {
        ArrayList<Object[]> arr_title = this.DanhSachLop();
        ArrayList<Object[]> info = new ArrayList<Object[]>();
        int column_size = arr_title.size() + 1;
        Object[] title = new Object[column_size];
        ArrayList<Object[]>[] arr_content = new ArrayList[column_size - 1];
        int max = 0;
        for (int i = 0; i < column_size; i++) {
            if (i == 0) {
                title[0] = "STT";
                continue;
            }
            title[i] = arr_title.get(i - 1)[1];
            arr_content[i - 1] = this.DanhSachHocSinh(Integer.parseInt(arr_title.get(i - 1)[0].toString()), stype);

            if (max < arr_content[i - 1].size()) {
                max = arr_content[i - 1].size();
            }
        }
        Object[][] rowColumn = new Object[max + 1][];
        for (int i = 0; i < max; i++) {
            Object[] o = new Object[column_size];
            for (int j = 0; j < column_size; j++) {
                if (j == 0) {
                    o[j] = i + 1;
                    continue;
                }
                if (i < arr_content[j - 1].size()) {
                    o[j] = arr_content[j - 1].get(i)[1];
                } else {
                    o[j] = "";
                }
            }
            rowColumn[i] = o;
        }
        Object[] name = new Object[column_size];
        for (int i = 0; i < column_size; i++) {
            if (i == 0) {
                name[i] = "Giáo Viên";
                continue;
            }
            name[i] = arr_title.get(i - 1)[3];
        }
        rowColumn[max] = name;
        model = new DefaultTableModel(rowColumn, title) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        table.setModel(model);
        for (int i = 0; i < column_size; i++) {
            Object[] in = new Object[max + 1];
            for (int j = 0; j <= max; j++) {
                if (i == 0) {
                    in[j] = "s";
                    continue;
                }
                if (j == max) {
                    in[j] = "s";
                    continue;
                }
                if (j < arr_content[i - 1].size()) {
                    if (arr_content[i - 1].get(j)[2] == null) {
                        in[j] = arr_content[i - 1].get(j)[0] + "-" + arr_title.get(i - 1)[0];
                        continue;
                    }
                    if (arr_content[i - 1].get(j)[2].toString().equals("1")) {
                        in[j] = arr_content[i - 1].get(j)[0] + "-" + arr_title.get(i - 1)[0];
                    } else {
                        in[j] = "-" + arr_content[i - 1].get(j)[0] + "-" + arr_title.get(i - 1)[0];
                    }
                } else {
                    if (j < Integer.parseInt(arr_title.get(i - 1)[2].toString())) {
                        in[j] = "x";
                    } else {
                        in[j] = "x";
                    }
                }
            }
            info.add(in);
        }
        if (max == 0) {
            Object[][] row = new Object[1][];

            Object[] o = new Object[column_size];
            for (int j = 0; j < column_size; j++) {
                o[j] = 1;
            }
            row[0] = o;
            XuLy.resizeColumnWidth(table, XuLy.getSize(row));
        } else {
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        }
        return info;
    }

    public ArrayList<Object[]> DanhSachLop() {
        try {
            ArrayList<Object[]> arr = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select id,NameClass,maxnumber,TeacherClass from classes order by Levels_Id");
            while (rs1.next()) {
                Object[] o = new Object[4];
                o[0] = rs1.getString(1);
                o[1] = rs1.getString(2);
                o[2] = rs1.getString(3);
                o[3] = rs1.getString(4);
                arr.add(o);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(SQLDanhSachHocSinh.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Object[]> DanhSachHocSinh(int id_class, int stype) {
        try {
            ArrayList<Object[]> arr = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select students.Id,students.FullName,sex from students,classes_has_students where Students_Id = students.id and Classes_Id = " + id_class + " and students.isactive = " + stype);
            while (rs1.next()) {
                Object[] o = new Object[3];
                o[0] = rs1.getString(1);
                o[1] = rs1.getString(2);
                o[2] = rs1.getString(3);
                arr.add(o);
            }
            XuLy.SapXepTen(arr, 1);
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(SQLDanhSachHocSinh.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Vector HocSinh(int id_student) {
        try {
            Vector v = new Vector();
            rs1 = statement.executeQuery("select Id,FullName,BrithDay,IsClient,HomePhone,Father,PhoneNumberFather,Mother,PhoneNumberMother,Email,DaiDien,Sex from projectkoala.students where Id = " + id_student);
            rs1.next();
            for (int i = 0; i < 12; i++) {
                if (i == 2) {
                    v.add(XuLy.getDate(rs1.getString(i + 1)));
                    continue;
                }
                if (i == 3) {
                    if (rs1.getInt(4) == 0) {
                        v.add("Chính quy");
                    } else {
                        v.add("Khách");
                    }
                    continue;
                }
                if (i == 11) {
                    if (rs1.getString(12) == null) {
                        v.add(1);
                    } else {
                        v.add(rs1.getString(12));
                    }
                    continue;
                }
                v.add(rs1.getString(i + 1));
            }
            return v;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
