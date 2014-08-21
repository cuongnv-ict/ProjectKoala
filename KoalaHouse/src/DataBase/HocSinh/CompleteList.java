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
        ArrayList<Object[]> list = new ArrayList<Object[]>();
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
                Object[] str1 = new Object[3];
                str[0] = stt;
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                //tinh tong tien can dong
                int idHS = rs1.getInt(1);
                //listId.add(idHS);
                str1[0]= rs1.getInt(1);
                str1[1] = rs1.getString(2);
                str1[2] = rs1.getString(3);
                list.add(str1);
                stt++;
                data.add(str);
            }
             //sap xep lai
            Collections.sort(data,new DebtListComparator());
            for(int i=0;i<data.size();i++){
            Object[] st;
            st = (Object[]) data.get(i);
            st[0] = i+1;
            }
            //lay id
            Collections.sort(list,new DebtListComparator());
            for(int i=0;i<list.size();i++){
            Object[] str = new Object[3];
            str = list.get(i);
            int x = Integer.parseInt(str[0].toString());
            listId.add(x);
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
    System.out.println("the eo nao");
    ArrayList<Object[]> data = new ArrayList<Object[]>();
        try {
            rs1 = statement.executeQuery("select students.Id,fullname,NameClass,students.Faculties_Id\n" +
            "from students,classes,classes_has_students "
            + "where (students.id not in(select students_id from students_has_cost where isdebt = 1 \n" +
            "group by students_id) and debt = 0 and students.id not in(SELECT idStudents FROM buslist where IsActive = 1)) and students.isactive = 1 and classes.Id = classes_has_students.Classes_Id\n" +
            "and classes_has_students.Students_Id = students.Id");
            while (rs1.next()) {
                Object[] str = new Object[3];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                int idHS = rs1.getInt(1);
                int idFac = rs1.getInt(4);
                int tongtien = new GetTotal().GetTotalMoney(idHS, idFac);
                if(tongtien!=0)
                    data.add(str);
            }
            for(int i=0;i<data.size();i++){
            Object[] str = new Object[3];
            str = data.get(i);
            System.out.println(str[0]+" "+str[1]+" "+str[2]);
            int x = Integer.parseInt(str[0].toString());
            
        }
        Collections.sort(data,new DebtListComparator());    
        } catch (SQLException ex) {
            Logger.getLogger(CompleteList.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList a= new ArrayList();
        for(int i=0;i<data.size();i++){
            
            Object[] str = new Object[3];
            str = data.get(i);
            System.out.println(str[0]+" "+str[1]+" "+str[2]);
            int x = Integer.parseInt(str[0].toString());
            a.add(x);
        }
        return a;
    }
}
class CompleteComparator implements Comparator<Object[]> {

 public int compare(Object[] o1, Object[] o2) {
  if(!o1[2].equals(o2[2])){
      return 0;
  }
  String age1 = (String) o1[1];
  String[] x= age1.split(" ");
  String age2 = (String) o2[1];
  String[] y = age2.split(" ");
  String name1 = x[x.length-1];
  String name2 = y[y.length-1];
  if(name1.compareTo(name2)>=1){
   return 1;
  }else if(name1.compareTo(name2)==0){
      if(age1.compareTo(age2)>=1)
          return 1;
      else if(age1.compareTo(age2)==0)
          return 0;
      else
          return -1;
  }else{
   return -1;
  }
 }
}