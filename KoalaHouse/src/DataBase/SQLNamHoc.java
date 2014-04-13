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
public class SQLNamHoc {

    private Connection connect;
    ResultSet rs1;
    Statement statement;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLNamHoc() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, null, "Đăng nhập không thành công !!! \n Xem lại user name hoặc "
                    + "password", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*
     * Bang lay thong tin ve nam hoc, kỳ học ngày bắt đầu ,ngày kết thúc 
     */

    public void getNamHoc(JTable table, int namhoc) {
        try {
            Object[] nameColumn = {"Học kỳ", "Ngày bắt đầu", "Ngày kết thúc"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT * FROM semesters where year = " + namhoc + " and Faculties_Id=" + ThongTin.trungtam);
            if (!rs1.next()) {
                for (int i = 0; i < 5; i++) {
                    Object str[] = new Object[3];
                    switch (i + 1) {
                        case 1:
                            str[0] = "Kỳ 1";
                            break;
                        case 2:
                            str[0] = "Kỳ 2";
                            break;
                        case 3:
                            str[0] = "Kỳ 3";
                            break;
                        case 4:
                            str[0] = "Kỳ hè";
                            break;
                        case 5:
                            str[0] = "Kỳ hè";
                            break;
                    }
                    str[1] = "";
                    str[2] = "";
                    data.add(str);
                }
            } else {
                do {
                    Object str[] = new Object[3];
                    switch (rs1.getInt(1)) {
                        case 1:
                            str[0] = "Kỳ 1";
                            break;
                        case 2:
                            str[0] = "Kỳ 2";
                            break;
                        case 3:
                            str[0] = "Kỳ 3";
                            break;
                        case 4:
                            str[0] = "Kỳ hè";
                            break;
                    }
                    str[1] = rs1.getString(4);
                    str[2] = rs1.getString(5);
                    data.add(str);
                } while (rs1.next());
                if(data.size()<5){
                    for (int i = data.size(); i < 5; i++) {
                    Object str[] = new Object[3];
                    switch (i + 1) {
                        case 1:
                            str[0] = "Kỳ 1";
                            break;
                        case 2:
                            str[0] = "Kỳ 2";
                            break;
                        case 3:
                            str[0] = "Kỳ 3";
                            break;
                        case 4:
                            str[0] = "Kỳ hè";
                            break;
                    }
                    str[1] = "";
                    str[2] = "";
                    data.add(str);
                }
                }
            }
            Object[][] rowColumn = new Object[data.size() - 1][];
            for (int i = 0; i < data.size() - 1; i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    boolean[] canEdit = new boolean[]{
                        false, true, true
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

    public boolean setNamHoc(Vector vector, int namhoc, int ky) {
        try {
            rs1 = statement.executeQuery("SELECT * FROM semesters where year = " + namhoc + " and SemesterNumber = " + ky);
            if (rs1.next()) {
                Pstate = connect.prepareStatement("UPDATE semesters SET startdate =?,enddate = ? where year = " + namhoc + " and SemesterNumber = " + ky);
                Pstate.setDate(1, Date.valueOf(vector.get(1).toString()));
                Pstate.setDate(2, Date.valueOf(vector.get(2).toString()));
                Pstate.execute();
                Pstate.close();
            } else {
                Pstate = connect.prepareStatement("INSERT INTO semesters (semesternumber,Faculties_Id,year,startdate,enddate) values(?,?,?,?,?)");
                Pstate.setInt(1, ky);
                Pstate.setInt(2, ThongTin.trungtam);
                Pstate.setInt(3, namhoc);
                Pstate.setDate(4, Date.valueOf(vector.get(1).toString()));
                Pstate.setDate(5, Date.valueOf(vector.get(2).toString()));
                Pstate.execute();
                Pstate.close();

            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiển tra lại thời gian học", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
