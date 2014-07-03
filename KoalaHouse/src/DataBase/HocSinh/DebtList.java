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
public class DebtList {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public DebtList(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHocSinhNoPhi(JTable table) {
        int stt = 1;
        try {
            Object[] nameColumn = {"Số TT", "Họ Tên", "Ngày Sinh","Lớp", "Họ Tên Cha", "SĐT", "Họ Tên Mẹ", "SĐT","ĐT Nhà","Email","Tổng Tiền"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select students.Id,fullname,brithday,NameClass,Father,PhoneNumberFather,Mother,PhoneNumberMother,HomePhone,Email,students.Faculties_Id\n" +
            "from students,classes,classes_has_students where (students.id in(select students_id from students_has_cost where isdebt = 1 \n" +
            "group by students_id) or debt!=0) and students.isactive = 1 and classes.Id = classes_has_students.Classes_Id\n" +
            "and classes_has_students.Students_Id = students.Id");
            while (rs1.next()) {
                Object[] str = new Object[11];
                str[0] = stt;
                str[1] = rs1.getString(2);
                str[2] = new XuLiXau().NgayThangNam(rs1.getString(3));
                str[3] = rs1.getString(4);
                str[4] = rs1.getString(5);
                str[5] = rs1.getString(6);
                str[6] = rs1.getString(7);
                str[7] = rs1.getString(8);
                str[8] = rs1.getString(9);
                str[9] = rs1.getString(10);  
                //tinh tong tien can dong
                int idHS = rs1.getInt(1);
                int idFac = rs1.getInt(11);
                int tongtien = new GetTotal().GetTotalMoney(idHS, idFac);
                str[10] = XuLy.setMoney(String.valueOf(tongtien));
                if(tongtien >0){
                    stt++;
                    data.add(str);
                }
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false, false,false, false, false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public ArrayList GetIdStudent(){
    ArrayList data = new ArrayList();
        try {
            rs1 = statement.executeQuery("select * from students where (students.id in(select students_id from students_has_cost where isdebt = 1 group by students_id) or debt!=0) and isactive = 1");
            while (rs1.next()) {
                Object str = new Object();
                str = rs1.getString(1);
                int idHS = rs1.getInt(1);
                int idFac = rs1.getInt(2);
                int tongtien = new GetTotal().GetTotalMoney(idHS, idFac);
                if(tongtien>0)
                    data.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
