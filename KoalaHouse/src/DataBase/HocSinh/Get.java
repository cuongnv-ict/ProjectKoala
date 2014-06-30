/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;

/**
 *
 * @author Pham The Quyen
 */
import DataBase.ConnectData;
import DataBase.DataTable;
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
public class Get{
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public Get(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList GetNameStudent(String classes){
    int idClass =0;
    ArrayList names = new ArrayList();
        try {
            rs1 = statement.executeQuery("SELECT Id from classes where NameClass = '"+ classes +"'");
            if(rs1!= null)
            while(rs1.next()){
                idClass = rs1.getInt(1);
            }
            rs1 = statement.executeQuery("SELECT FullName FROM students, classes, classes_has_students where classes.Id = "+idClass+" and classes.Id = classes_has_students.Classes_Id and students.Id = classes_has_students.Students_Id"
                    + " and students.isactive = 1;");
            while(rs1.next()){
                String name = rs1.getString(1);
                names.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return names;
}
    public ArrayList GetIdAndFacStudent(String name, String classes){
    int idTrungTam = 0;
    int idStudent =0;
    int idClass = 0;
    ArrayList info = new ArrayList();
        try {
            rs1 = statement.executeQuery("SELECT Id from classes where NameClass = '"+ classes +"'");
            if(rs1!= null)
            while(rs1.next()){
                idClass = rs1.getInt(1);
            }
            System.out.println(""+name+" "+idClass);
            rs1 = statement.executeQuery("SELECT students.Id,students.Faculties_Id FROM students,classes_has_students where FullName = \""+name+"\" and Classes_Id = "+idClass+" and Id = Students_Id");
            if(rs1!= null)
            while(rs1.next()){
                idStudent = rs1.getInt(1);
                idTrungTam = rs1.getInt(2);
            }
            info.add(idStudent);
            info.add(idTrungTam);
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return info;
}
    public void BangTrongMuon(JTable table){
        try {
            Object [] nameColumn = {"Tên HS","Lớp","Ngày Trông Muộn","Số Phút"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            String query = "SELECT students.FullName, classes.NameClass,lateday.LateDate ,lateday.Time ";
            query +="FROM students, classes_has_students, classes, lateday ";
            query += "where students.Id = classes_has_students.Students_Id and classes.Id = classes_has_students.Classes_Id ";
            query +="and lateday.Students_Id = students.Id and lateday.isActive = 1";
            rs1 = statement.executeQuery(query);
            while(rs1.next()){
                Object str[] = new Object[4];
                //str[0]= new XuLiXau().NgayThangNam(rs1.getString(4));
                str[0]= rs1.getString(1);
                str[1] = rs1.getString(2);
                str[2]= new XuLiXau().NgayThangNam(rs1.getString(3));
                str[3] = rs1.getInt(4);
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++){
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
            if(data.size() ==0){
                model = new DefaultTableModel(nameColumn, 0);
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList GetNameClass(){
    ArrayList nameClasses = new ArrayList();
        try {
            rs1 = statement.executeQuery("SELECT NameClass FROM classes;");
            while(rs1.next()){
                String name = rs1.getString(1);
                nameClasses.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nameClasses;
}
    public int GetNumberSummerWeek(int idStudent){
        int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT soTuanHoc FROM projectkoala.learnsummer where idStudents = "+idStudent+" and IsActive = 1");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
        
    }
     public int GetYear(int idFac,String date){
        int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT min(Year) FROM projectkoala.semesters where Faculties_Id = "+idFac+" and StartDate <= '"+date+"' and EndDate >= '"+date+"'");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }
     public int GetIsDatCoc(int idStudent,int idFac){
        int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT IsDatCoc FROM projectkoala.students Where Id = "+idStudent+" and Faculties_Id = "+idFac+"");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }
}
