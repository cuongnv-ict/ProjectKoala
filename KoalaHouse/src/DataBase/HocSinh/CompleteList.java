/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;
import DataBase.ConnectData;
import DataBase.DataTable;
import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pham The Quyen
 */
public class CompleteList{
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public CompleteList(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(CompleteList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList BangDanhSachHocSinhDuPhi(JTable table) {
        ArrayList listId = new ArrayList();
        int stt = 1;
        try {
            Object[] nameColumn = {"Số TT", "Họ Tên","Lớp"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select students.Id,fullname,NameClass,students.Faculties_Id\n" +
            "from students,classes,classes_has_students "
            + "where (students.id not in(select students_id from students_has_cost where isdebt = 1 \n" +
            "group by students_id) and debt = 0 and students.id not in(SELECT idStudents FROM buslist where IsActive = 1)) and students.isactive = 1 and classes.Id = classes_has_students.Classes_Id\n" +
            "and classes_has_students.Students_Id = students.Id");
            while (rs1.next()) {
                Object[] str = new Object[3];
                str[0] = stt;
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                //tinh tong tien can dong
                int idHS = rs1.getInt(1);
                listId.add(idHS);
                stt++;
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++){
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false, false,false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
                if(data.size() ==0){
                model = new DefaultTableModel(nameColumn, 0);
                table.setModel(model);
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompleteList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listId;
    }
public ArrayList GetIdStudent(){
    ArrayList data = new ArrayList();
        try {
            rs1 = statement.executeQuery("select * from students where (students.id in(select students_id from students_has_cost where isdebt = 0 group by students_id) and debt = 0 and students.id in(SELECT idStudents FROM buslist where IsActive = 1)) and isactive = 1");
            while (rs1.next()) {
                Object str = new Object();
                str = rs1.getString(1);
                int idHS = rs1.getInt(1);
                int idFac = rs1.getInt(2);
                int tongtien = new GetTotal().GetTotalMoney(idHS, idFac);
                if(tongtien!=0)
                    data.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompleteList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
