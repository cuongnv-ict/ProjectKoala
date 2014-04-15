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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen van cuong
 */
public class SQLNam {

    private Connection connect;
    ResultSet rs1;
    Statement statement;
    PreparedStatement Pstate;

    public SQLNam() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
        } catch (SQLException ex) {
        }
    }

    public Vector[] getNamHoc(int nam) {
        try {
            rs1 = statement.executeQuery("SELECT startdate,enddate FROM semesters where year = " + nam + " and Faculties_Id=" + ThongTin.trungtam);
            if (rs1.next()) {
                Vector e[] = new Vector[4];
                e[0] = new Vector();
                e[1] = new Vector();
                e[2] = new Vector();
                e[3] = new Vector();
                for (int i = 0; i < 4; i++) {
                    String bi[] = rs1.getString(1).split("-");
                    String en[] = rs1.getString(2).split("-");
                    e[i].add(bi[2]);
                    e[i].add(bi[1]);
                    e[i].add(bi[0]);
                    e[i].add(en[2]);
                    e[i].add(en[1]);
                    e[i].add(en[0]);
                    rs1.next();
                }
                return e;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", null, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public boolean setNamHoc(Vector vector, int namhoc, int ky) {
        try {
            rs1 = statement.executeQuery("SELECT * FROM semesters where year = " + namhoc + " and SemesterNumber = " + ky);
            if (rs1.next()) {
                Pstate = connect.prepareStatement("UPDATE semesters SET startdate =?,enddate = ? where year = " + namhoc + " and SemesterNumber = " + ky + " and Faculties_Id=" + ThongTin.trungtam);
                Pstate.setDate(1, Date.valueOf(vector.get(0).toString()));
                Pstate.setDate(2, Date.valueOf(vector.get(1).toString()));
                Pstate.execute();
                Pstate.close();
            } else {
                Pstate = connect.prepareStatement("INSERT INTO semesters (semesternumber,Faculties_Id,year,startdate,enddate) values(?,?,?,?,?)");
                Pstate.setInt(1, ky);
                Pstate.setInt(2, ThongTin.trungtam);
                Pstate.setInt(3, namhoc);
                Pstate.setDate(4, Date.valueOf(vector.get(0).toString()));
                Pstate.setDate(5, Date.valueOf(vector.get(1).toString()));
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

    public boolean getIsActiveSemester(int nam) {
        try {
            rs1 = statement.executeQuery("SELECT * FROM semesters where year = " + nam + " and Faculties_Id=" + ThongTin.trungtam + " and isactivity = 1");
            if (rs1.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLNam.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean setIsActiveSemester(int nam, boolean active) {
        try {
            if (active) {
                statement.execute("Update semesters set isactivity = 0");
                statement.execute("Update semesters set isactivity = 1 where year = " + nam + " and Faculties_Id=" + ThongTin.trungtam);
            } else {
                statement.execute("Update semesters set isactivity = 0 where year = " + nam + " and Faculties_Id=" + ThongTin.trungtam);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLNam.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
