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
//            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList HocSinhA1(int ID){
    ArrayList infoStudent = new ArrayList();
    String str[] = new String[14];
        try {
            rs1 = statement.executeQuery("select students.Id,fullname,brithday,Father,PhoneNumberFather,"
                    + "Mother,PhoneNumberMother,HomePhone,students.Email,isclient,nameclass,"
                    + "faculties.name,students.isactive,DaiDien "
                    + "from students,classes,classes_has_students,faculties "
                    + "where students.id = students_id and classes.id=classes_id and students.id = "+ID+" "
                    + "and faculties.Id = students.Faculties_Id");
            while(rs1.next()){
                    str[0] = rs1.getString(1);
                    str[1] = rs1.getString(2);
                    str[2] = new XuLiXau().NgayThangNam(rs1.getString(3));
                    str[3] = rs1.getString(4);
                    str[4] = rs1.getString(5);
                    str[5] = rs1.getString(6);
                    str[6] = rs1.getString(7);
                    str[7] = rs1.getString(8);
                    str[8] = rs1.getString(9);
                    switch(rs1.getInt(10)){
                       case 0:
                           str[9] = "Chính Quy";
                           break;
                       case 1:
                            str[9] = "Chương Trình Bạn Là Khách";
                           break;
                 }
                    str[10] = rs1.getString(11);
                    str[11] = rs1.getString(12);
                    str[12] = rs1.getString(13);
                    str[13] = rs1.getString(14);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0;i<14;i++){
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
            statement.close();
            connect.close();
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
            statement.close();
            connect.close();
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
            Object [] nameColumn = {"Ngày","Số phút trông muộn","Tình Trạng"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from lateday where Students_Id = "+idStudent);
            while(rs1.next()){
                Object str[] = new Object[3];
                str[0]= new XuLiXau().NgayThangNam(rs1.getString(4));
                str[1] = rs1.getString(5);
                switch(rs1.getInt(6)){
                    case 0:
                        str[2] = "Đã Thanh Toán";break;
                    case 1:
                        str[2] = "Chưa Thanh Toán";break;
                }
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdataInfoStudent(int id,int fac,String name,String birthDay,String father,String phoneF,String mother,String phoneM,String homePhone,String email,String ngDD){
        String query = "UPDATE `projectkoala`.`students` SET `FullName`='"+name+"', `BrithDay`='"+birthDay+"', `PhoneNumberFather`='"+phoneF+"',"
                + " `Father`='"+father+"', `Mother`='"+mother+"', `PhoneNumberMother`='"+phoneM+"', `HomePhone`='"+homePhone+"',"
                + " `Email`='"+email+"', `DaiDien`='"+ngDD+"' WHERE `Id`='"+id+"' and`Faculties_Id`='"+fac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeleteTrongMuon(int idStudent,String date,String time){
        String query = "DELETE FROM `projectkoala`.`lateday` WHERE `Students_Id`='"+idStudent+"' and `LateDate`='"+date+"' and `Time` = '"+time+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateTrongMuon(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`lateday` SET `Semester`='"+semester+"', `year`="+year+", `isActive`='0' WHERE `Students_Id`='"+idStudent+"' and `isActive`='1';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String query = "UPDATE `projectkoala`.`lateday` SET `isActive`='0' WHERE `Students_Id`='"+idStudent+"' and `Semester`='"+semester+"' and `year`="+year+";";
//        try {
//            PreparedStatement pstmt = connect.prepareStatement(query);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    public void UpdateKiHe(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`learnsummer` SET `Semester`='"+semester+"', `year`="+year+", `isActive`='0' WHERE `idStudents`='"+idStudent+"' and `isActive`='1';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            System.out.println(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String query = "UPDATE `projectkoala`.`lateday` SET `isActive`='0' WHERE `Students_Id`='"+idStudent+"' and `Semester`='"+semester+"' and `year`="+year+";";
//        try {
//            PreparedStatement pstmt = connect.prepareStatement(query);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
     public void UpdateHoanHocPhi(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`leaves` SET `Semester`='"+semester+"', `year`="+year+", `isActive`='0' WHERE `Students_Id`='"+idStudent+"' and `isActive`='1';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            System.out.println(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String query = "UPDATE `projectkoala`.`lateday` SET `isActive`='0' WHERE `Students_Id`='"+idStudent+"' and `Semester`='"+semester+"' and `year`="+year+";";
//        try {
//            PreparedStatement pstmt = connect.prepareStatement(query);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    public void InsertTrongMuon(int idStudent,int idFac,String date,int time,String semester, String year){
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
        int isactive = 1;
//        try {
//            rs1 = statement.executeQuery("SELECT IsActive FROM projectkoala.lateday where Students_Id = "+idStudent+" and Semester = "+semester+" and year = "+year+";");
//            if(rs1.next()){
//                isactive = rs1.getInt(1);
//            }
//                
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String query ="INSERT INTO `projectkoala`.`lateday` (`Id`, `Faculties_Id`, `Students_Id`, `LateDate`, `Time`, `isActive`, `Semester`, `year`) VALUES ('"+id+"', '"+idFac+"', '"+idStudent+"', '"+date+"', '"+time+"', '"+isactive+"', '"+semester+"', '"+year+"');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            statement.close();
            connect.close();
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ThoiHoc(int idStudent, int idFac){
        String query = "UPDATE `projectkoala`.`students` SET `isactive`='0' WHERE `Id`='"+idStudent+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
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
             statement.close();
            connect.close();
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
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void XoaTrongMuon(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`lateday` SET `isActive`='1' WHERE `Students_Id`='"+idStudent+"' and `Semester`='"+semester+"' and `year` = '"+year+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void XoaPhiHocHe(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`learnsummer` SET `isActive`='1' WHERE `idStudents`='"+idStudent+"' and `Semester`='"+semester+"' and `year` = '"+year+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void XoaHoanHocPhi(int idStudent,String semester, String year){
        String query = "UPDATE `projectkoala`.`leaves` SET `isActive`='1' WHERE `Students_Id`='"+idStudent+"' and `Semester`='"+semester+"' and `year` = '"+year+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void setDatCoc(int idStudent, int idFac){
        String query = "UPDATE `projectkoala`.`students` SET `IsDatCoc`='1' WHERE `Id`='"+idStudent+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void UpdateNgayNhapHoc(int idStudent, int idFac, String ngay){
        String query = "UPDATE `projectkoala`.`students` SET `NhapHoc`='"+ngay+"' WHERE `Id`='"+idStudent+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
