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

    public ArrayList<Integer> DanhSachHocSinhTrongKi(JTable table, int thang, int nam) {
        try {
            ArrayList<Integer> info = new ArrayList<Integer>();
            Object[] nameColumn = {"STT", "SS", "Tên học sinh", "Lớp", "Ngày nhập học", "Ngày nghỉ học", "Chú ý"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT students.FullName,classes.NameClass,students.isactive,students.NhapHoc,students.NghiHoc,sex from students,classes,classes_has_students where students.Id = classes_has_students.Students_Id and classes_has_students.Classes_Id = classes.Id and students.isactive != -1");
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
                    str[6] = "";
                    data.add(str);
                    info.add(1);
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
                        str1[6] = "";
                        data.add(str1);
                        temp = rs1.getString(2);
                        count_ss = 1;
                        info.add(-1);

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
                            str[6] = "";
                            data.add(str);
                            count_ss++;
                            count_stt++;
                            temp = rs1.getString(2);
                            if (rs1.getString(6) == null) {
                                info.add(1);
                            } else {
                                info.add(rs1.getInt(6));
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
                            str[6] = "";
                            data.add(str);
                            count_ss++;
                            count_stt++;
                            temp = rs1.getString(2);
                            if (rs1.getString(6) == null) {
                                info.add(1);
                            } else {
                                info.add(rs1.getInt(6));
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
                            str[4] = "";
                            str[5] = XuLy.getDate(rs1.getString(5));
                            str[6] = "";
                            data.add(str);
                            if (rs1.getString(6) == null) {
                                info.add(1);
                            } else {
                                info.add(rs1.getInt(6));
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
                                str[6] = "";
                                data.add(str);
                                count_ss++;
                                count_stt++;
                                temp = rs1.getString(2);
                                info.add(0);
                                if (rs1.getString(6) == null) {
                                    info.add(1);
                                } else {
                                    info.add(rs1.getInt(6));
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
                                str[6] = "";
                                data.add(str);
                                count_ss++;
                                count_stt++;
                                temp = rs1.getString(2);
                                if (rs1.getString(6) == null) {
                                    info.add(1);
                                } else {
                                    info.add(rs1.getInt(6));
                                }
                            }
                        }
                    }
                } while (rs1.next());
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
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
            Object[] nameColumn = {"STT", "Tên học sinh", "Lớp cũ","Lớp mới", "Ngày nhập học", "Ngày nghỉ học", "Số tuần hẹ học"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT students.FullName,classes.NameClass,students.isactive,students.NhapHoc,students.NghiHoc,sex from students,classes,classes_has_students where students.Id = classes_has_students.Students_Id and classes_has_students.Classes_Id = classes.Id and students.isactive != -1");
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
                    str[6] = "";
                    data.add(str);
                    info.add(1);
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
                        str1[6] = "";
                        data.add(str1);
                        temp = rs1.getString(2);
                        count_ss = 1;
                        info.add(-1);

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
                            str[6] = "";
                            data.add(str);
                            count_ss++;
                            count_stt++;
                            temp = rs1.getString(2);
                            if (rs1.getString(6) == null) {
                                info.add(1);
                            } else {
                                info.add(rs1.getInt(6));
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
                            str[6] = "";
                            data.add(str);
                            count_ss++;
                            count_stt++;
                            temp = rs1.getString(2);
                            if (rs1.getString(6) == null) {
                                info.add(1);
                            } else {
                                info.add(rs1.getInt(6));
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
                            str[4] = "";
                            str[5] = XuLy.getDate(rs1.getString(5));
                            str[6] = "";
                            data.add(str);
                            if (rs1.getString(6) == null) {
                                info.add(1);
                            } else {
                                info.add(rs1.getInt(6));
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
                                str[6] = "";
                                data.add(str);
                                count_ss++;
                                count_stt++;
                                temp = rs1.getString(2);
                                info.add(0);
                                if (rs1.getString(6) == null) {
                                    info.add(1);
                                } else {
                                    info.add(rs1.getInt(6));
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
                                str[6] = "";
                                data.add(str);
                                count_ss++;
                                count_stt++;
                                temp = rs1.getString(2);
                                if (rs1.getString(6) == null) {
                                    info.add(1);
                                } else {
                                    info.add(rs1.getInt(6));
                                }
                            }
                        }
                    }
                } while (rs1.next());
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
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
            return info;
//            XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi nạp dữ liệu", null, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
