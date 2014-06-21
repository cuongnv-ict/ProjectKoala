/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

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
            Object[] nameColumn = {"STT", "Tên", "Kì học", "Năm học", "Ngày bắt đầu", "Ngày kết thúc", "Giá", ""};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select Id,NameCost,Semesters,year,StartDate,EndDate,Amount from cost where cost.Faculties_Id = " + ThongTin.trungtam);
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
                while (rs1.next()) {
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
                    str[4] = XuLy.getDate(rs1.getString(5));
                    str[5] = XuLy.getDate(rs1.getString(6));
                    str[6] = rs1.getString(7);
                    if (((String) str[6]).charAt(0) == '-') {
                        str[6] = XuLy.setMoney(((String) str[6]).substring(1));
                    } else {
                        str[6] = XuLy.setMoney((String) str[6]);
                    }
                    str[7] = false;
                    data.add(str);
                    XuLy.SapXepTen(data, 1);
                }
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

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        }
    }

    public boolean xoaHocPhi(String ky, String name, int id) {
        try {
            statement.execute("delete from cost where id = " + id);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Bạn không thể xóa phí " + name + " khi đang được áp dụng cho một số học sinh trong trường.", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean suaHocPhi(Vector vector, int id) {
        try {
            Pstate = connect.prepareStatement("select id from cost where namecost = ? and semesters = ? and year = ? and Faculties_Id=? and id != ?");
            Pstate.setString(1, vector.get(0).toString());
            Pstate.setString(2, vector.get(1).toString());
            Pstate.setString(3, vector.get(2).toString());
            Pstate.setInt(4, ThongTin.trungtam);
            Pstate.setInt(5, id);
            rs1 = Pstate.executeQuery();
            if (rs1.next()) {
                JOptionPane.showMessageDialog(null, "Loại phí đã tồn tại, bạn không thể thay đổi", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            Pstate = connect.prepareStatement("select * from semesters where year = ? and Faculties_Id=" + ThongTin.trungtam);
            Pstate.setString(1, vector.get(2).toString());
            rs1 = Pstate.executeQuery();
            if (!rs1.next()) {
                JOptionPane.showMessageDialog(null, "Năm học bạn chọn chưa được thiết lập", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            rs1 = Pstate.executeQuery();
            Pstate = connect.prepareStatement("update cost set NameCost =?,Semesters = ?,year = ?,StartDate = ?,EndDate = ?,Amount = ? where id = " + id);
            Pstate.setString(1, vector.get(0).toString());
            Pstate.setString(2, vector.get(1).toString());
            Pstate.setString(3, vector.get(2).toString());
            Pstate.setString(4, vector.get(3).toString());
            Pstate.setString(5, vector.get(4).toString());
            Pstate.setString(6, String.valueOf(Integer.parseInt(vector.get(5).toString())));
            Pstate.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hãy chắc chắn rằng kì học đó đã được thiết lập", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean themHocPhi(Vector vector) {
        try {
            Pstate = connect.prepareStatement("select id from cost where namecost = ? and semesters = ? and year = ? and Faculties_Id =" + ThongTin.trungtam);
            Pstate.setString(1, vector.get(0).toString());
            Pstate.setString(2, vector.get(1).toString());
            Pstate.setString(3, vector.get(2).toString());
            rs1 = Pstate.executeQuery();
            if (rs1.next()) {
                JOptionPane.showMessageDialog(null, "Loại phí này đã tồn tại rồi", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            Pstate = connect.prepareStatement("select * from semesters where year = ? and Faculties_Id =" + ThongTin.trungtam);
            Pstate.setString(1, vector.get(2).toString());
            rs1 = Pstate.executeQuery();
            if (!rs1.next()) {
                JOptionPane.showMessageDialog(null, "Năm học bạn chọn chưa được thiết lập", null, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            rs1 = statement.executeQuery("select max(id) from cost");
            int x;
            if (rs1.next()) {
                x = rs1.getInt(1) + 1;
            } else {
                x = 5;
            }
            Pstate = connect.prepareStatement("insert into cost (Id,Faculties_Id,NameCost,Semesters,year,StartDate,EndDate,Amount) values (?,?,?,?,?,?,?,?)");
            Pstate.setInt(1, x);
            Pstate.setInt(2, ThongTin.trungtam);
            Pstate.setString(3, vector.get(0).toString());
            Pstate.setString(4, vector.get(1).toString());
            Pstate.setString(5, vector.get(2).toString());
            Pstate.setString(6, vector.get(3).toString());
            Pstate.setString(7, vector.get(4).toString());
            Pstate.setString(8, String.valueOf(Integer.parseInt(vector.get(5).toString())));
            Pstate.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLHocPhi.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Giá phí bạn nhập phải là số !", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<Integer> BangDanhSachHSDongDuPhi(int classes_id, JTable table) {
        try {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "Họ Tên", "Ngày Sinh", "Hình thức học", "Số điện thoại nhà", "Tên cha", "Số điện thoại", "Tên mẹ", "Số điện thoại", "Email", ""};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where  debt=0 and isactive = 1 and students.id in(select Students_Id from classes_has_students where classes_id = " + classes_id + " and Students_Id not in(select students_has_cost.students_id from students_has_cost,classes_has_students where isdebt = 1 and students_has_cost.students_id=classes_has_students.students_id and classes_id = " + classes_id + " group by students_has_cost.students_id))");
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[12];
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
                    str[10] = false;
                    str[11] = 1;
                    data.add(str);
                }
            } else {
                do {
                    Object[] str = new Object[12];
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
                    str[10] = false;
                    str[11] = rs1.getInt(15);
                    data.add(str);
                } while (rs1.next());
                XuLy.SapXepTen(data, 1);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                Object[] str = new Object[11];
                for (int j = 0; j < 11; j++) {
                    str[j] = (data.get(i))[j];
                }
                arr.add((Integer) (data.get(i))[11]);
                rowColumn[i] = str;

            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            statement.close();
            connect.close();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Integer> BangDanhSachHocSinhNoPhi(int classes_id, JTable table) {
        try {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "Họ Tên", "Ngày Sinh", "Hình thức học", "Số điện thoại nhà", "Tên cha", "Số điện thoại", "Tên mẹ", "Số điện thoại", "Email", ""};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where (isactive= 1 and students.id in(select students_has_cost.students_id from students_has_cost,classes_has_students where isdebt = 1 and students_has_cost.students_id=classes_has_students.students_id and classes_id = " + classes_id + " group by students_has_cost.students_id))or(isactive= 1 and debt!=0 and id in(SELECT Students_Id FROM projectkoala.classes_has_students where Classes_Id=" + classes_id + "))");
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[12];
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
                    str[10] = false;
                    str[11] = 1;
                    data.add(str);
                }
            } else {
                do {
                    Object[] str = new Object[12];
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
                    str[10] = false;
                    str[11] = rs1.getInt(15);
                    data.add(str);
                } while (rs1.next());
                XuLy.SapXepTen(data, 1);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                Object[] str = new Object[11];
                for (int j = 0; j < 11; j++) {
                    str[j] = (data.get(i))[j];
                }
                arr.add((Integer) (data.get(i))[11]);
                rowColumn[i] = str;

            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            statement.close();
            connect.close();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean getSelected(int id) {
        try {
            rs1 = statement.executeQuery("select amount from cost where id = " + id);
            rs1.next();
            String str = rs1.getString(1);
            if (((String) str).charAt(0) == '-') {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHocPhi.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
