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
 * @author nguyen van cuong
 */
public class AStudentAndLateDay {
     private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public AStudentAndLateDay(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList HocSinhA1(int ID){
    ArrayList infoStudent = new ArrayList();
    String str[] = new String[10];
        try {
            rs1 = statement.executeQuery("select students.id,fullname,brithday,PhoneNumber,Representative,levels_id,nameclass,isclient,faculties.name,students.isactive from students,classes,classes_has_students,faculties where students.id = students_id and classes.id=classes_id and students.id = "+ID+" and faculties.Id = students.Faculties_Id");
            while(rs1.next()){
                    str[0] = rs1.getString(1);
                    str[1] = rs1.getString(2);
                    str[2] = new XuLiXau().NgayThangNam(rs1.getString(3));
                    str[3] = rs1.getString(4);
                    str[4] = rs1.getString(5);
                   switch(rs1.getInt(6)){
                       case 1:
                           str[5] = "NẮNG MAI (SUNSHINE)";
                           break;
                       case 2:
                            str[5] = "TỔ ONG (BEEHIVE)";
                           break;
                       case 3:
                            str[5] = "TỔ KÉN (CHRYSALIS)";
                           break;
                       case 4:
                            str[5] = "MẪU GIÁO (KINDERGARTEN)";
                           break;
                   }
                   
                 
                   str[6] = rs1.getString(7);
                   switch(rs1.getInt(8)){
                       case 1:
                           str[7] = "Chính Quy";
                           break;
                       case 0:
                            str[7] = "Chương Trình Bạn Là Khách";
                           break;
                 }
                   str[8] = rs1.getString(9);
                   str[9] = rs1.getString(10);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0;i<10;i++){
            infoStudent.add(str[i]);
            }
        
        return infoStudent;
}
    public int LateDay(int idStudent,int idFac){//sua thanh TimeLateDay va return Time
    int count = 0;
    int totalTime = 0;
    ArrayList infoStudent = new ArrayList();
    String str[] = new String[365];
        try {
            rs1 = statement.executeQuery("select * from lateday where Students_Id = "+idStudent+" and isActive =1 and Faculties_Id = "+idFac);
            if(rs1!= null)
            while(rs1.next()){
                    str[count] = rs1.getString(5);
                    count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0;i<count;i++){
            totalTime += Integer.parseInt(str[i]);
            }
        return totalTime;
}
    public int LateDay(int idStudent,int idFac, String ki, String nam){//sua thanh TimeLateDay va return Time
    int count = 0;
    int totalTime = 0;
    ArrayList infoStudent = new ArrayList();
    String str[] = new String[365];
        try {
            rs1 = statement.executeQuery("select * from lateday where Students_Id = "+idStudent+" and Semester = "+ki+" and year = "+nam+" and Faculties_Id = "+idFac);
            if(rs1!= null)
            while(rs1.next()){
                    str[count] = rs1.getString(5);
                    count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0;i<count;i++){
            totalTime += Integer.parseInt(str[i]);
            }
        return totalTime;
}
    public void LateDayInfo(int idStudent,JTable table){
        try {
            Object [] nameColumn = {"Ngày","Số phút trông muộn"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from lateday where Students_Id = "+idStudent);
            while(rs1.next()){
                Object str[] = new Object[2];
                str[0]= new XuLiXau().NgayThangNam(rs1.getString(4));
                str[1] = rs1.getString(5);
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++){
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            };
            table.setModel(model);
        }
            if(data.size() ==0){
                model = new DefaultTableModel(nameColumn, 0);
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdataInfoStudent(int id,int fac,String name,String birthDay,String representative,String phoneNumber){
        String query = "UPDATE `projectkoala`.`students` SET `FullName`='"+name+"', `BrithDay`='"+birthDay+"', `PhoneNumber`='"+phoneNumber+"', `Representative`='"+representative+"' WHERE `Id`='"+String.valueOf(id)+"' and`Faculties_Id`='"+String.valueOf(fac)+"'";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeleteTrongMuon(int idStudent,String date,String time){
        String query = "DELETE FROM `projectkoala`.`lateday` WHERE `Students_Id`='"+idStudent+"' and `LateDate`='"+date+"' and `Time` = '"+time+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateTrongMuon(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`lateday` SET `Semester`='"+semester+"', `year`="+year+" WHERE `Students_Id`='"+idStudent+"' and `isActive`='1';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "UPDATE `projectkoala`.`lateday` SET `isActive`='0' WHERE `Students_Id`='"+idStudent+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void InsertTrongMuon(int idStudent,int idFac,String date,int time){
        int id = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(Id) FROM lateday");
            while(rs1.next()){
                id = rs1.getInt(1);
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query ="INSERT INTO `projectkoala`.`lateday` (`Id`, `Faculties_Id`, `Students_Id`, `LateDate`, `Time`, `isActive`) VALUES ('"+id+"', '"+idFac+"', '"+idStudent+"', '"+date+"', '"+time+"', '1');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void DanhSachTrongMuon(int classes_id,JTable table){
         try {
            Object [] nameColumn = {"Mã số", "Họ và tên", "Ngày Sinh", "Số ngày"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("SELECT students.id,FullName,BrithDay ,count(students_id) from students, lateday where students.Id = Students_Id and students.Id in(select Students_Id from classes_has_students where Classes_Id = "+classes_id+") group by Students_Id");
            while(rs1.next()){
               
                Object str[] = new Object[4];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                str[3] = rs1.getString(4);         
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ThoiHoc(int idStudent, int idFac){
        String query = "UPDATE `projectkoala`.`students` SET `isactive`='0' WHERE `Id`='"+idStudent+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int GetDebt(int idStudent){
        int debt = 0;
         try {
             rs1 = statement.executeQuery("SELECT Debt FROM projectkoala.students where Id = "+ idStudent+";");
             while(rs1.next()){
                 debt = rs1.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
         }
         return debt;
    }
    public void InsertDebt(int idStudent, int idFac, int debt){
        String query = "UPDATE `projectkoala`.`students` SET `Debt`='"+debt+"' WHERE `Id`='"+idStudent+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void XoaTrongMuon(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`lateday` SET `isActive`='1', `Semester`='0', `year`=0 WHERE `Students_Id`='"+idStudent+"' and `Semester`='"+semester+"' and `year` = '"+year+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
