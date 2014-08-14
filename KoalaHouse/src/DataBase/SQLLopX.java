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

    public ArrayList<Integer> BangDanhHSLop(int classes_id, JTable table) {
        try {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "Họ Tên", "Ngày Sinh", "Hình thức học", "Số điện thoại nhà", "Tên cha", "Số điện thoại", "Tên mẹ", "Số điện thoại", "Email", "Người đại diện", ""};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from classes_has_students where classes_id = " + classes_id + " ) and isActive = 1");
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[13];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    str[6] = "";
                    str[7] = "";
                    str[8] = "";
                    str[9] = "";
                    str[10] = "";
                    str[11] = false;
                    str[12] = 1;
                    data.add(str);
                }
            } else {
                do {
                    Object[] str = new Object[13];
                    str[0] = rs1.getInt(1);
                    str[1] = rs1.getString(3);
                    str[2] = XuLy.getDate(rs1.getString(4));
                    if (rs1.getInt(7) == 1) {
                        str[3] = "Khách";
                    } else {
                        str[3] = "Chính quy";
                    }
                    str[4] = rs1.getString(13);
                    str[5] = rs1.getString(6);
                    str[6] = rs1.getString(5);
                    str[7] = rs1.getString(11);
                    str[8] = rs1.getString(12);
                    str[9] = rs1.getString(14);
                    str[10] = rs1.getString(19);
                    str[11] = false;
                    if (rs1.getString(15) == null) {
                        str[12] = 1;
                    } else {
                        str[12] = rs1.getInt(15);
                    }

                    data.add(str);
                } while (rs1.next());
                XuLy.SapXepTen(data, 1);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                Object[] str = new Object[12];
                for (int j = 0; j < 12; j++) {
                    str[j] = (data.get(i))[j];
                }
                arr.add((Integer) (data.get(i))[12]);
                rowColumn[i] = str;

            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            statement.close();
            connect.close();
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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
            rs1 = statement.executeQuery("select maxnumber from classes where id = " + id_class);
            rs1.next();
            int max = rs1.getInt(1);
            rs1 = statement.executeQuery("select count(Students_Id) from classes_has_students,students where students.id = classes_has_students.Students_Id and  Classes_Id= " + id_class+ " and students.isactive = 1");
            rs1.next();
            
            if (max == rs1.getInt(1)) {
                JOptionPane.showMessageDialog(null, "Số học sinh trong lớp đã đạt tối đa, không thể thêm học sinh", null, JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        
            rs1 = statement.executeQuery("select students.id from classes_has_students,students where students.id = classes_has_students.Students_Id and  FullName= '" + vector.get(0).toString()+ "' and students.isactive = 1");
            if(rs1.next()){
                 JOptionPane.showMessageDialog(null, "Học sinh được thêm trung tên với 1 học sinh khác trong lớp\nhãy thêm ký tự đặc biệt để phân biệt", null, JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            
            Pstate = connect.prepareStatement("insert into students(id,Faculties_Id,FullName,BrithDay,PhoneNumberFather,Father,IsClient,Debt,isactive,Mother,PhoneNumberMother,HomePhone,Email,Sex,DaiDien,NhapHoc)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            Pstate.setString(1, String.valueOf(x));
            Pstate.setString(2, String.valueOf(ThongTin.trungtam));
            Pstate.setString(3, vector.get(0).toString());
            Pstate.setString(4, vector.get(1).toString());
            Pstate.setString(5, vector.get(2).toString());
            Pstate.setString(6, vector.get(3).toString());
            Pstate.setString(7, vector.get(4).toString());
            Pstate.setString(8, "0");
            Pstate.setString(9, "1");
            Pstate.setString(10, vector.get(5).toString());
            Pstate.setString(11, vector.get(6).toString());
            Pstate.setString(12, vector.get(7).toString());
            Pstate.setString(13, vector.get(8).toString());
            Pstate.setString(14, vector.get(9).toString());
            Pstate.setString(15, vector.get(10).toString());
            Pstate.setString(16, vector.get(11).toString());
            Pstate.execute();
            Pstate = connect.prepareStatement("insert into classes_has_students(Classes_Id,Students_Id,Faculties_Id) values(?,?,?)");
            Pstate.setString(1, String.valueOf(id_class));
            Pstate.setString(2, String.valueOf(x));
            Pstate.setString(3, String.valueOf(ThongTin.trungtam));
            Pstate.execute();
            statement.close();
            connect.close();
            JOptionPane.showMessageDialog(null, "Thêm học sinh thành công", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xảy ra lỗi trong quá trình thêm học sinh.\n Bạn hãy thử lại", null, JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
            try {
                statement.close();
                connect.close();
            } catch (SQLException ex1) {
                Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex1);
                return false;
            }
            return false;
        }
    }

    public boolean suaHocSinh(Vector vector, int id_student) {
        try {
            rs1 = statement.executeQuery("select students.id from classes_has_students,students where students.id = classes_has_students.Students_Id and  FullName= '" + vector.get(0).toString()+ "' and students.isactive = 1");
//            if(rs1.next()&& rs1.getInt(1)!=id_student){
//                 JOptionPane.showMessageDialog(null, "Học sinh được thêm trung tên với 1 học sinh khác trong lớp\nhãy thêm ký tự đặc biệt để phân biệt", null, JOptionPane.INFORMATION_MESSAGE);
//                return false;
//            }
//            Pstate = connect.prepareStatement("update students set FullName=?,BrithDay=?,PhoneNumber=?,Representative=?,IsClient=? where id = " + id_student);
            Pstate = connect.prepareStatement("update students set FullName=?,BrithDay=?,PhoneNumberFather=?,Father=?,IsClient=?,Mother=?,PhoneNumberMother=?,HomePhone=?,Email=?,Sex=?, DaiDien=? where id = " + id_student);
            Pstate.setString(1, vector.get(0).toString());
            Pstate.setString(2, vector.get(1).toString());
            Pstate.setString(3, vector.get(2).toString());
            Pstate.setString(4, vector.get(3).toString());
            Pstate.setString(5, vector.get(4).toString());
            Pstate.setString(6, vector.get(5).toString());
            Pstate.setString(7, vector.get(6).toString());
            Pstate.setString(8, vector.get(7).toString());
            Pstate.setString(9, vector.get(8).toString());
            Pstate.setString(10, vector.get(9).toString());
            Pstate.setString(11, vector.get(10).toString());
            Pstate.execute();
            JOptionPane.showMessageDialog(null, "Chỉnh sửa học sinh thành công", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoaHocSinh(int id_student, String date) {
        try {
            System.out.println(date);
            statement.execute("Update students set isactive = 0,NghiHoc  = '"+date+"' where id = " + id_student);
            statement.close();
            connect.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean chuyenlopHocSinh(int id, String name_class,String old_class) {
        try {
            int id_classes;
            id_classes = new DataTable().LayIdTenLop(name_class);

            String query = "update classes_has_students set Classes_Id='" + id_classes +"',Class_Id_Old = '"+old_class +"' where Students_Id='" + id + "'";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public String getNameClass(int id){
        try {
            rs1 = statement.executeQuery("select NameClass from classes where id = "+id);
            if(rs1.next()){
                return rs1.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLLopX.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public ArrayList<Integer> timhocsinh(String tenhs, JTable table, int classes_id) {
        try {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "Họ Tên", "Ngày Sinh", "Hình thức học", "Số điện thoại nhà", "Tên cha", "Số điện thoại", "Tên mẹ", "Số điện thoại", "Email", "Người đại diện", ""};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            System.out.println(String.valueOf(tenhs));
            System.out.println(String.valueOf(classes_id));
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from classes_has_students where classes_id = " + classes_id + " ) and isActive = 1 and FullName like '%" + tenhs + "%' ");
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[13];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    str[6] = "";
                    str[7] = "";
                    str[8] = "";
                    str[9] = "";
                    str[10] = "";
                    str[11] = false;
                    str[12] = 1;
                    data.add(str);
                }
            } else {
                do {
                    Object[] str = new Object[13];
                    str[0] = rs1.getInt(1);
                    str[1] = rs1.getString(3);
                    str[2] = XuLy.getDate(rs1.getString(4));
                    if (rs1.getInt(7) == 1) {
                        str[3] = "Khách";
                    } else {
                        str[3] = "Chính quy";
                    }
                    str[4] = rs1.getString(13);
                    str[5] = rs1.getString(6);
                    str[6] = rs1.getString(5);
                    str[7] = rs1.getString(11);
                    str[8] = rs1.getString(12);
                    str[9] = rs1.getString(14);
                    str[10] = rs1.getString(19);
                    str[11] = false;
                    if (rs1.getString(15) == null) {
                        str[12] = 1;
                    } else {
                        str[12] = rs1.getInt(15);
                    }

                    data.add(str);
                } while (rs1.next());
                XuLy.SapXepTen(data, 1);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                Object[] str = new Object[12];
                for (int j = 0; j < 12; j++) {
                    str[j] = (data.get(i))[j];
                }
                arr.add((Integer) (data.get(i))[12]);
                rowColumn[i] = str;

            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            statement.close();
            connect.close();
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Integer> BangDanhHSLop_iType(int classes_id, JTable table, int isClient) {
        try {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "Họ Tên", "Ngày Sinh", "Hình thức học", "Số điện thoại nhà", "Tên cha", "Số điện thoại", "Tên mẹ", "Số điện thoại", "Email", "Người đại diện", ""};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            Pstate = connect.prepareStatement("select * from students where students.id in(select students_id from classes_has_students where classes_id = ?) and isActive = 1 and isClient = ?");
            Pstate.setInt(1, classes_id);
            Pstate.setInt(2, isClient);
            rs1 = Pstate.executeQuery();
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[13];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    str[6] = "";
                    str[7] = "";
                    str[8] = "";
                    str[9] = "";
                    str[10] = "";
                    str[11] = false;
                    str[12] = 1;
                    data.add(str);
                }
            } else {
                do {
                    Object[] str = new Object[13];
                    str[0] = rs1.getInt(1);
                    str[1] = rs1.getString(3);
                    str[2] = XuLy.getDate(rs1.getString(4));
                    if (rs1.getInt(7) == 1) {
                        str[3] = "Khách";
                    } else {
                        str[3] = "Chính quy";
                    }
                    str[4] = rs1.getString(13);
                    str[5] = rs1.getString(6);
                    str[6] = rs1.getString(5);
                    str[7] = rs1.getString(11);
                    str[8] = rs1.getString(12);
                    str[9] = rs1.getString(14);
                    str[10] = rs1.getString(19);
                    str[11] = false;
                    if (rs1.getString(15) == null) {
                        str[12] = 1;
                    } else {
                        str[12] = rs1.getInt(15);
                    }

                    data.add(str);
                } while (rs1.next());
                XuLy.SapXepTen(data, 1);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                Object[] str = new Object[12];
                for (int j = 0; j < 12; j++) {
                    str[j] = (data.get(i))[j];
                }
                arr.add((Integer) (data.get(i))[12]);
                rowColumn[i] = str;

            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            statement.close();
            connect.close();
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
