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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenvan
 */
public class SQLHocSinhTheoThang {

    private Connection connect;
    ResultSet rs1, rs2;
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

    public ArrayList<Integer> DanhSachHocSinhTrongKi(JTable table, int thang, int nam) {
        try {
            ArrayList<Integer> info = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "SS", "Tên học sinh", "Lớp", "Ngày nhập học", "Ngày nghỉ học"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT students.FullName,classes.NameClass,students.isactive,students.NhapHoc,students.NghiHoc,sex,students.id from students,classes,classes_has_students where students.Id = classes_has_students.Students_Id and classes_has_students.Classes_Id = classes.Id and students.isactive != -1 order by classes.Levels_Id,classes.NameClass ASC");
            int count_stt = 1, count_ss = 1;
            String temp = "a-b-c-b-a";
            if (!rs1.next()) {
                for (int i = 0; i < 10; i++) {
                    Object[] str = new Object[7];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    str[4] = "";
                    str[5] = "";
                    str[6] = 0;
                    data.add(str);                   
                }
            } else {
                do {
                    Object[] str = new Object[7];
                    if (!temp.equals("a-b-c-b-a") && !temp.equals(rs1.getString(2))) {
                        Object[] str1 = new Object[7];
                        str1[0] = "";
                        str1[1] = "";
                        str1[2] = "";
                        str1[3] = "";
                        str1[4] = "";
                        str1[5] = "";
                        str1[6] = 0;
                        data.add(str1);
                        temp = rs1.getString(2);
                        count_ss = 1;

                    }
                    if (rs1.getInt(3) == 1) {
                        String date[] = rs1.getString(4).split("-");
                        if (Integer.parseInt(date[0]) < nam && Integer.parseInt(date[0]) != 0) {
                            str[0] = count_stt;
                            str[1] = count_ss;
                            str[2] = rs1.getString(1);
                            str[3] = rs1.getString(2);
                            str[4] = "";
                            str[5] = "";
                            data.add(str);
                            count_ss++;
                            count_stt++;
                            temp = rs1.getString(2);
                            if (rs1.getString(6) == null) {
                               str[6] = 0;
                            } else {
                                if (rs1.getInt(6) == 1) {                                    
                                    str[6] = rs1.getInt(7);
                                } else {
                                    str[6] = -rs1.getInt(7);
                                }
                            }
                        } else if (Integer.parseInt(date[0]) == nam && Integer.parseInt(date[0]) != 0 && Integer.parseInt(date[1]) <= thang && Integer.parseInt(date[1]) != 0) {
                            str[0] = count_stt;
                            str[1] = count_ss;
                            str[2] = rs1.getString(1);
                            str[3] = rs1.getString(2);
                            if (Integer.parseInt(date[1]) == thang) {
                                str[4] = XuLy.getDate(rs1.getString(4));
                            } else {
                                str[4] = "";
                            }
                            str[5] = "";
                            data.add(str);
                            count_ss++;
                            count_stt++;
                            temp = rs1.getString(2);
                            if (rs1.getString(6) == null) {
                               str[6] = 0;
                            } else {
                                if (rs1.getInt(6) == 1) {                                    
                                    str[6] = rs1.getInt(7);
                                } else {
                                    str[6] = -rs1.getInt(7);
                                }
                            }
                        }
                    } else {
                        String date[] = rs1.getString(5).split("-");
                        if (Integer.parseInt(date[1]) == thang && Integer.parseInt(date[0]) == nam) {
                            //them hoc sinh nghi
                            str[0] = "";
                            str[1] = "";
                            str[2] = rs1.getString(1);
                            str[3] = rs1.getString(2);
                            String ask[] = rs1.getString(4).split("-");
                            if (Integer.parseInt(ask[1]) == thang) {
                                str[4] = XuLy.getDate(rs1.getString(4));
                            } else {
                                str[4] = "";
                            }
                            str[5] = XuLy.getDate(rs1.getString(5));
                            data.add(str);
                             if (rs1.getString(6) == null) {
                               str[6] = 0;
                            } else {
                                if (rs1.getInt(6) == 1) {                                    
                                    str[6] = rs1.getInt(7);
                                } else {
                                    str[6] = -rs1.getInt(7);
                                }
                            }
                            temp = rs1.getString(2);
                        }
                        if ((Integer.parseInt(date[1]) == thang && Integer.parseInt(date[0]) < nam) || (Integer.parseInt(date[1]) < thang && Integer.parseInt(date[0]) == nam)) {
                            //Loai bo
                        }
                        if ((Integer.parseInt(date[1]) == thang && Integer.parseInt(date[0]) > nam) || (Integer.parseInt(date[1]) > thang && Integer.parseInt(date[0]) == nam)) {
                            //xet trang thai xem con dang hoc hay khong
                            String date1[] = rs1.getString(4).split("-");
                            if (Integer.parseInt(date1[0]) < nam && Integer.parseInt(date1[0]) != 0) {
                                str[0] = count_stt;
                                str[1] = count_ss;
                                str[2] = rs1.getString(1);
                                str[3] = rs1.getString(2);
                                str[4] = "";
                                str[5] = "";
                                data.add(str);
                                count_ss++;
                                count_stt++;
                                temp = rs1.getString(2);
                                if (rs1.getString(6) == null) {
                                   str[6] = 0;
                            } else {
                                if (rs1.getInt(6) == 1) {                                    
                                    str[6] = rs1.getInt(7);
                                } else {
                                    str[6] = -rs1.getInt(7);
                                }
                            }
                            } else if (Integer.parseInt(date1[0]) == nam && Integer.parseInt(date1[0]) != 0 && Integer.parseInt(date1[1]) <= thang && Integer.parseInt(date1[1]) != 0) {
                                str[0] = count_stt;
                                str[1] = count_ss;
                                str[2] = rs1.getString(1);
                                str[3] = rs1.getString(2);
                                if (Integer.parseInt(date[1]) == thang) {
                                    str[4] = XuLy.getDate(rs1.getString(4));
                                } else {
                                    str[4] = "";
                                }
                                str[5] = "";
                                data.add(str);
                                count_ss++;
                                count_stt++;
                                temp = rs1.getString(2);
                                data.add(str);
                             if (rs1.getString(6) == null) {
                               str[6] = 0;
                            } else {
                                if (rs1.getInt(6) == 1) {                                    
                                    str[6] = rs1.getInt(7);
                                } else {
                                    str[6] = -rs1.getInt(7);
                                }
                            }
                            }
                        }
                    }
                } while (rs1.next());
            }
            XuLy.SapXepTen(data, 2, 3);
            info.removeAll(info);
            Object[][] rowColumn = new Object[data.size()][];
            count_stt = 1;
            count_ss = 1;
            temp =  "";
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                info.add((Integer)rowColumn[i][6]);
                 if(rowColumn[i][2].toString().equals("")&&rowColumn[i][3].toString().equals("")){
                    count_ss = 1;
                    continue;
                }
                rowColumn[i][1] = count_ss;
                count_ss++;
                if(!rowColumn[i][5].toString().equals("")){
                    continue;
                }
                rowColumn[i][0] = count_stt;
                count_stt++;               
            }
            
            model = new DefaultTableModel(rowColumn, nameColumn) {
                public Class getColumnClass(int columnIndex) {
                    return java.lang.String.class;
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            table.setModel(model);
            if (data.size() == 0) {
                Object[][] name = new Object[][]{nameColumn};
                int arr[] = XuLy.getSize(name);
                XuLy.resizeColumnWidth(table, XuLy.getSize(name));
            } else {
                XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            }
            return info;
//            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<Integer> DanhSachHocSinhKiHe(JTable table, int thang, int nam) {
        try {
            ArrayList<Integer> info = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "Tên học sinh", "Lớp cũ", "Lớp hè", "Ngày nhập học", "Ngày nghỉ học", "Số tuần học hè"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT students.FullName,Class_Id_Old,classes.NameClass,students.isactive,students.NhapHoc,students.NghiHoc,sex,students.Id from students,classes,classes_has_students where students.Id = classes_has_students.Students_Id and classes_has_students.Classes_Id = classes.Id and students.isactive != -1 order by classes.Levels_Id,classes.NameClass ASC");
            int count_stt = 1;
            String temp = "a-b-c-b-a";
            String old = "a-b-c-b-a";
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
                    str[7] = 1;
                    data.add(str);

                }
            } else {
                do {
                    Object[] str = new Object[8];
                    if (rs1.getInt(4) == 1) {
                        String date[] = rs1.getString(5).split("-");
                        if (Integer.parseInt(date[0]) < nam && Integer.parseInt(date[0]) != 0) {
                            str[0] = count_stt;
                            str[1] = rs1.getString(1);
                            str[2] = rs1.getString(2);
                            str[3] = rs1.getString(3);
                            str[4] = "";
                            str[5] = "";
                            str[6] = HocHe(rs1.getInt(8), nam);
                            data.add(str);
                            count_stt++;
                            if (rs1.getString(7) == null) {
                                str[7] = 0;
                            } else {
                                if (rs1.getInt(7) == 1) {
                                    str[7] = rs1.getInt(8);
                                } else {
                                    str[7] = -rs1.getInt(8);
                                }

                            }
                        } else if (Integer.parseInt(date[0]) == nam && Integer.parseInt(date[0]) != 0 && Integer.parseInt(date[1]) <= thang && Integer.parseInt(date[1]) != 0) {
                            str[0] = count_stt;
                            str[1] = rs1.getString(1);
                            str[2] = rs1.getString(2);
                            str[3] = rs1.getString(3);
                            if (Integer.parseInt(date[1]) == thang) {
                                str[4] = XuLy.getDate(rs1.getString(5));
                            } else {
                                str[4] = "";
                            }
                            str[5] = "";
                            str[6] = HocHe(rs1.getInt(8), nam);
                            data.add(str);
                            count_stt++;
                            if (rs1.getString(7) == null) {
                                str[7] = 0;
                            } else {
                                if (rs1.getInt(7) == 1) {
                                    str[7] = rs1.getInt(8);
                                } else {
                                    str[7] = -rs1.getInt(8);
                                }
                            }
                        }
                    } else {
                        String date[] = rs1.getString(6).split("-");
                        if (Integer.parseInt(date[1]) == thang && Integer.parseInt(date[0]) == nam) {
                            //them hoc sinh nghi
                            str[0] = "";
                            str[1] = rs1.getString(1);
                            str[2] = rs1.getString(3);
                            str[3] = "";
                            String ask[] = rs1.getString(5).split("-");
                            if (Integer.parseInt(ask[1]) == thang) {
                                str[4] = XuLy.getDate(rs1.getString(5));
                            } else {
                                str[4] = "";
                            }
                            str[5] = XuLy.getDate(rs1.getString(6));
                            str[6] = "Không học hè";
                            data.add(str);
                             if (rs1.getString(7) == null) {
                                 str[7] = 0;
                            } else {
                                if (rs1.getInt(7) == 1) {
                                     str[7] = rs1.getInt(8);
                                } else {
                                    str[7] = -rs1.getInt(8);
                                }
                            }
                        }
                        if ((Integer.parseInt(date[1]) == thang && Integer.parseInt(date[0]) > nam) || (Integer.parseInt(date[1]) > thang && Integer.parseInt(date[0]) == nam)) {
                            //xet trang thai xem con dang hoc hay khong
                            date = rs1.getString(5).split("-");
                            if (Integer.parseInt(date[0]) < nam && Integer.parseInt(date[0]) != 0) {
                                str[0] = count_stt;
                                str[1] = rs1.getString(1);
                                str[2] = rs1.getString(2);
                                str[3] = rs1.getString(3);
                                str[4] = "";
                                str[5] = "";
                                str[6] = HocHe(rs1.getInt(8), nam);
                                data.add(str);
                                count_stt++;
                                if (rs1.getString(7) == null) {
                                    info.add(0);
                                } else {
                                    if (rs1.getInt(7) == 1) {
                                        info.add(rs1.getInt(8));
                                    } else {
                                        info.add(-rs1.getInt(8));
                                    }

                                }
                            } else if (Integer.parseInt(date[0]) == nam && Integer.parseInt(date[0]) != 0 && Integer.parseInt(date[1]) <= thang && Integer.parseInt(date[1]) != 0) {
                                str[0] = count_stt;
                                str[1] = rs1.getString(1);
                                str[2] = rs1.getString(2);
                                str[3] = rs1.getString(3);
                                if (Integer.parseInt(date[1]) == thang) {
                                    str[4] = XuLy.getDate(rs1.getString(5));
                                } else {
                                    str[4] = "";
                                }
                                str[5] = "";
                                str[6] = HocHe(rs1.getInt(8), nam);
                                data.add(str);
                                count_stt++;
                                if (rs1.getString(7) == null) {
                                    str[7] = 0;
                                } else {
                                    if (rs1.getInt(7) == 1) {
                                        str[7] = rs1.getInt(8);
                                    } else {
                                        str[7] = -rs1.getInt(8);
                                    }
                                }
                            }
                        }
                    }
                } while (rs1.next());
            }
            XuLy.SapXepTen(data, 1, 3);
            Object[][] rowColumn = new Object[data.size()][];
            info.removeAll(info);
            count_stt = 1;
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                info.add((Integer)rowColumn[i][7]);
                if (rowColumn[i][5].toString().equalsIgnoreCase("")) {
                    rowColumn[i][0] = count_stt;
                    count_stt++;
                }
            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                public Class getColumnClass(int columnIndex) {
                    return java.lang.String.class;
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if (columnIndex == 2) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            table.setModel(model);
            if (data.size() == 0) {
                Object[][] name = new Object[][]{nameColumn};
                XuLy.resizeColumnWidth(table, XuLy.getSize(name));
            } else {
                XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            }
            return info;
//            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public String HocHe(int id, int year) {
        try {
            Pstate = connect.prepareStatement("SELECT soTuanHoc FROM learnsummer where year = " + year + " and idStudents = " + id);
            rs2 = Pstate.executeQuery();
            if (rs2.next()) {
                return rs2.getString(1) + " tuần";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHocSinhTheoThang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Không học hè";
    }

    public JComboBox getNameClass() {
        JComboBox c = new JComboBox();
        try {
            Pstate = connect.prepareStatement("SELECT NameClass FROM classes order by Levels_Id,NameClass");
            rs2 = Pstate.executeQuery();
            c.addItem("");
            while (rs2.next()) {
                c.addItem(rs2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHocSinhTheoThang.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return c;
        }
    }

    public void update(int id, String name) {
        try {
            Pstate = connect.prepareStatement("update classes_has_students set Class_Id_Old = '" + name + "' where students_id = " + id);
            Pstate.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SQLHocSinhTheoThang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DanhSachSinhNhat(JTable table, int thang) {
        try {
            int count = 0;
            Object[] nameColumn = {"STT", "Tên", "Lớp học", "Ngày sinh"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT students.FullName,classes.NameClass,students.BrithDay FROM students,classes_has_students,classes where students.id= classes_has_students.Students_Id and classes.Id = classes_has_students.Classes_Id and students.isactive= 1");
            if (!rs1.next()) {
                for (int i = 0; i < 1; i++) {
                    Object[] str = new Object[4];
                    str[0] = "";
                    str[1] = "";
                    str[2] = "";
                    str[3] = "";
                    data.add(str);
                }
            } else {
                do {
                    String m[] = rs1.getString(3).split("-");
                    if (Integer.parseInt(m[1]) == thang) {
                        Object str[] = new Object[4];
                        count++;
                        str[0] = count;
                        str[1] = rs1.getString(1);
                        str[2] = rs1.getString(2);
                        str[3] = XuLy.getDate(rs1.getString(3));
                        data.add(str);
                    }

                } while (rs1.next());
                if (count == 0) {
                    for (int i = 0; i < 1; i++) {
                        Object[] str = new Object[4];
                        str[0] = "";
                        str[1] = "";
                        str[2] = "";
                        str[3] = "";
                        data.add(str);
                    }
                } else {
                    XuLy.SapXepThang(data, 3);
                }
            }
            
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                if (count != 0) {
                    rowColumn[i][0] = i + 1;
                }
            }
            model = new DefaultTableModel(rowColumn, nameColumn) {

                public Class getColumnClass(int columnIndex) {
                    return java.lang.String.class;
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            table.setModel(model);
            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
        }
    }
}
