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
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author PhamTheQuyen
 */
public class DatCocManagerment {
    private Connection connect;
    ResultSet rs1, rs2;
    Statement statement;
    DefaultTableModel model;
    Object[][] rowColumn;

    public DatCocManagerment() {
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList BangDanhSachHocSinhDatCoc(JTable table,String idClass) {
        ArrayList idStudents = new ArrayList();
        int stt = 1;
        try {
            Object[] nameColumn = {"Số TT", "Họ Tên", "Đang Giữ", "Đã Hoàn Trả","Chọn HS Muốn Chỉnh Sửa"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            ArrayList<Object[]> list = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT hs.Id,hs.FullName,hs.IsDatCoc FROM projectkoala.students hs, classes_has_students class\n" +
            "where hs.isactive = 1 and hs.Id = class.Students_Id and class.Classes_Id = "+idClass+";");
            while (rs1.next()) {
                Object[] str = new Object[4];
                str[0] = stt;
                str[1] = rs1.getString(2);
                int isDatCoc = rs1.getInt(3);
                str[2]="";
                if(isDatCoc == -1)
                    str[2] = "X";
                str[3]="";
                if(isDatCoc==1)
                    str[3] = "X";
                //----------------
                Object[] strList = new Object[2];
                strList[0] = rs1.getInt(1);
                strList[1] = rs1.getString(2);
                list.add(strList);
                //-------------------
                    stt++;
                    data.add(str);
            }
            //sap xep lai
            Collections.sort(data, new DatCocListComparator());
            Collections.sort(list, new DatCocListComparator());
            for (int i = 0; i < data.size(); i++) {
                Object[] st;
                st = (Object[]) data.get(i);
                st[0] = i + 1;
            }
            //lay list id hoc sinh
            for (int i = 0; i < list.size(); i++) {
                idStudents.add(list.get(i)[0]);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
            }
            model = new DefaultTableModel(rowColumn, nameColumn) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            table.setModel(model);
            if (data.size() == 0) {
                model = new DefaultTableModel(nameColumn, 0);
                table.setModel(model);
            }
//            if (data.size() == 0) {
//                Object[][] name = new Object[][]{nameColumn};
//                XuLy.resizeColumnWidth(table, XuLy.getSize(name));
//            } else {
//                XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
//            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idStudents;
    }
    public void hehe(){
        
    }
}
class DatCocListComparator implements Comparator<Object[]> {

    public int compare(Object[] o1, Object[] o2) {

        String age1 = (String) o1[1];
        String[] x = age1.split(" ");
        String age2 = (String) o2[1];
        String[] y = age2.split(" ");
        String name1 = x[x.length - 1];
        String name2 = y[y.length - 1];
        if (name1.compareTo(name2) >= 1) {
            return 1;
        } else if (name1.compareTo(name2) == 0) {
            if (age1.compareTo(age2) >= 1) {
                return 1;
            } else if (age1.compareTo(age2) == 0) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}