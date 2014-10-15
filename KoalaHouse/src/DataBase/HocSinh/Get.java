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
            //Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
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
            statement.close();
            connect.close();
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
            rs1 = statement.executeQuery("SELECT students.Id,students.Faculties_Id,students.FullName FROM students,classes_has_students where FullName = \""+name+"\" and Classes_Id = "+idClass+" and Id = Students_Id");
            if(rs1!= null)
            while(rs1.next()){
                if(name.equals(rs1.getString(3))){
                idStudent = rs1.getInt(1);
                idTrungTam = rs1.getInt(2);
                }
            }
            info.add(idStudent);
            info.add(idTrungTam);
            statement.close();
            connect.close();
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
            query +="and lateday.Students_Id = students.Id and lateday.isActive = 1 and students.isactive = 1 Order by classes.NameClass";
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
            Collections.sort(data,new TrongMuonComparator());
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
            statement.close();
            connect.close();
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nameClasses;
}
    public int GetNumberSummerWeek(int idStudent,String ki,String nam){
        int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT soTuanHoc FROM projectkoala.learnsummer where idStudents = "+idStudent+" and IsActive = 0 and Semester = "+ki+" and year="+nam+"");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
            statement.close();
            connect.close();
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }
     public int getYearActive(int idFac){
         int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT min(Year) FROM projectkoala.semesters where Faculties_Id = "+idFac+" and IsActivity= 1");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
            statement.close();
            connect.close();
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }
     public ArrayList HocHe(int idStudent){
            ArrayList a = new ArrayList();
            try {
            rs2 = statement.executeQuery("SELECT tuanhoc,soTuanHoc FROM projectkoala.learnsummer where idStudents = "+idStudent+";");
            if(rs2!= null)
            while(rs2.next()){
                String x = rs2.getString(1);
                String[] b = x.split(",");
                a.removeAll(a);
                for(int j=0;j<b.length;j++){
                    a.add(b[j]);
                }
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(TotalFeeManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
            return a;
        }
     public int GetSoTienXeBus(int idStudent){
         int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT TienXe FROM projectkoala.buslist where idStudents = "+idStudent+" and IsActive = 1;");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
     }
     public int GetSoTienXeBusDaThu(int idStudent,int idFac,int idSem,int year){
         int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT EndDate FROM projectkoala.buslist where idStudents = "+idStudent+" and IsActive = 0;");
            String date = null;
            while(rs1.next()){
                date = rs1.getString(1);
            }
            if(date!=null){
            int idSemester = new GetTotal().GetIdSemesterCheckYear(idFac,String.valueOf(year), date);
            if(idSemester!= idSem)
                return number;
            }
            rs1 = statement.executeQuery("SELECT TienXe FROM projectkoala.buslist where idStudents = "+idStudent+" and IsActive = 0;");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
     }
     public ArrayList getPhiHocSinh(int idStudent,int idTrungTam){
         ArrayList a =  new ArrayList();
        try {
            rs1 = statement.executeQuery("SELECT NameCost,Amount,cost.Semesters,cost.year FROM projectkoala.cost,students_has_cost\n" +
            "where students_has_cost.Students_Id = "+idStudent+" and students_has_cost.IsDebt = 1\n" +
            "and students_has_cost.Cost_Id = cost.Id");
            while(rs1.next()){
                Object[] str = new Object[2];
                str[0] = rs1.getString(1);
                int totalTime = 0;
                boolean checkTrongMuon = false;
                boolean checkHocHe = false;
                boolean checkHoanHocPhi = false;
                String tenp = str[0].toString().toLowerCase();
                if(tenp.indexOf("thu")!= -1 && tenp.indexOf("khác")!= -1){
                    str[0] = "Thu Khác";
                }
                if(str[0].toString().toLowerCase().indexOf("phí trông muộn")!= -1){
                    checkTrongMuon = true;
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(4).substring(0, 4);
                    totalTime = new AStudentAndLateDay().LateDay(idStudent,idTrungTam,ki,nam);
                }
                if(str[0].toString().toLowerCase().indexOf("phí học hè")!= -1){
                    checkHocHe = true;
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(4).substring(0, 4);
                    totalTime = new Get().GetNumberSummerWeek(idStudent,ki,nam);
                }
                if(str[0].toString().toLowerCase().indexOf("hoàn học phí")!= -1){
                    checkHoanHocPhi = true;
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(4).substring(0, 4);
                    totalTime = new Get().GetSoNgayNghiPhep(idStudent,ki,nam);
                }
                String thanhtien = rs1.getString(2);
                if(thanhtien.charAt(0)== '-'){
                    thanhtien = thanhtien.substring(1);
                }
                int money = Integer.parseInt(thanhtien);
                if(checkTrongMuon)
                    money = money * totalTime;
                if(checkHocHe)
                    money = money * totalTime;
                if(checkHoanHocPhi)
                    money = money * totalTime;
                str[1] = money;
                a.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            rs2 = statement.executeQuery("SELECT NameCost,Amount,cost.Semesters,cost.year FROM projectkoala.cost,students_has_cost\n" +
            "where students_has_cost.Students_Id = "+idStudent+" and students_has_cost.IsDebt = 0\n" +
            "and students_has_cost.Cost_Id = cost.Id and cost.NameCost = \"Phí Đặt Cọc\"");
            while(rs2.next()){
                Object[] str = new Object[2];
                str[0] = "Hoàn Phí Đặt Cọc";
                if(rs2.getString(2).charAt(0)== '-')
                    str[1] = rs2.getString(2).substring(1);
                else
                    str[1] = rs2.getString(2);
                a.add(str);
            }
            statement.close();
            connect.close();
        }catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
     }
     public ArrayList getPhiHocSinhDaThu(int idStudent,int idTrungTam,int idSem,int year){
         ArrayList a =  new ArrayList();
        try {
            if(idSem==1){
                rs1 = statement.executeQuery("SELECT NameCost,Amount,cost.Semesters,cost.year FROM projectkoala.cost,students_has_cost\n" +
"where students_has_cost.Students_Id = "+idStudent+" and students_has_cost.IsDebt = 0 and students_has_cost.Faculties_Id = "+idTrungTam+"\n" +
"and students_has_cost.Cost_Id = cost.Id and (cost.Semesters = 1 or cost.Semesters = 5) and cost.year ="+year+" ");
            }else
            {
                rs1 = statement.executeQuery("SELECT NameCost,Amount,cost.Semesters,cost.year FROM projectkoala.cost,students_has_cost\n" +
"where students_has_cost.Students_Id = "+idStudent+" and students_has_cost.IsDebt = 0 and students_has_cost.Faculties_Id = "+idTrungTam+"\n" +
"and students_has_cost.Cost_Id = cost.Id and cost.Semesters = "+idSem+" and cost.year ="+year+" ");
            }
            
            while(rs1.next()){
                Object[] str = new Object[2];
                str[0] = rs1.getString(1);
                int totalTime = 0;
                boolean checkTrongMuon = false;
                boolean checkHocHe = false;
                boolean checkHoanHocPhi = false;
                String tenp = str[0].toString().toLowerCase();
                if(tenp.indexOf("thu")!= -1 && tenp.indexOf("khác")!= -1){
                    str[0] = "Thu Khác";
                }
                if(str[0].toString().toLowerCase().indexOf("phí trông muộn")!= -1){
                    checkTrongMuon = true;
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(4).substring(0, 4);
                    totalTime = new AStudentAndLateDay().LateDay(idStudent,idTrungTam,ki,nam);
                }
                if(str[0].toString().toLowerCase().indexOf("phí học hè")!= -1){
                    checkHocHe = true;
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(4).substring(0, 4);
                    totalTime = new Get().GetNumberSummerWeek(idStudent,ki,nam);
                }
                if(str[0].toString().toLowerCase().indexOf("hoàn học phí")!= -1){
                    checkHoanHocPhi = true;
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(4).substring(0, 4);
                    totalTime = new Get().GetSoNgayNghiPhep(idStudent,ki,nam);
                }
                String thanhtien = rs1.getString(2);
                if(thanhtien.charAt(0)== '-'){
                    thanhtien = thanhtien.substring(1);
                }
                int money = Integer.parseInt(thanhtien);
                if(checkTrongMuon)
                    money = money * totalTime;
                if(checkHocHe)
                    money = money * totalTime;
                if(checkHoanHocPhi)
                    money = money * totalTime;
                str[1] = money;
                a.add(str);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
     }
     public int GetSoNgayNghiPhep(int idStudent,String ki, String nam){
         int number = 0;
        try {
            rs1 = statement.executeQuery("SELECT NumberOfDay FROM projectkoala.leaves where Students_Id = "+idStudent+" and IsActive = 0 and Semester = "+ki+" and year = "+nam+"");
            while(rs1.next()){
                number = rs1.getInt(1);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
     }
     public ArrayList getTimeSummer(int year){
         ArrayList a = new ArrayList();
         String temp1 = null;
         String temp2;
         String ntm;
         try {
            rs1 = statement.executeQuery("SELECT startDay,endDay FROM summerweek where startDay like '%2014%'");
            while(rs1.next()){
                temp1 = new XuLiXau().NgayThangNam(rs1.getString(1));
                temp2 = new XuLiXau().NgayThangNam(rs1.getString(2));
                ntm = " từ "+temp1+" đến "+temp2;
                a.add(ntm);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a;
     }
     public int GetIDFac(){
         int idFac = 0;
         try {
            rs1 = statement.executeQuery("SELECT Faculties_Id FROM projectkoala.accounts;");
            while(rs1.next()){
                idFac = rs1.getInt(1);
            }
            statement.close();
            connect.close();
            } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
         return idFac;
     }
     public ArrayList getInFoFac(){
         ArrayList a = new ArrayList();
         String temp1 = null;
         String temp2 = null;
         int idFac = 0;
         try {
            rs1 = statement.executeQuery("SELECT Faculties_Id FROM projectkoala.accounts;");
            while(rs1.next()){
                idFac = rs1.getInt(1);
            }
            rs1 = statement.executeQuery("SELECT Address,PhoneNumbers FROM projectkoala.faculties where Id = "+idFac+"; ");
            while(rs1.next()){
                temp1 = rs1.getString(1);
                temp2 = rs1.getString(2);
            }
            a.add(temp1);a.add(temp2);a.add(idFac);
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a;
     }
     public void UpdateInfoFac(int idFac,String addr, String sdt){
        String query = "UPDATE `projectkoala`.`faculties` SET `Address`='"+addr+"', `PhoneNumbers`='"+sdt+"' WHERE `Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public String getNgayNhapHoc(int idStudent){
         String a = null;
         try {
            rs1 = statement.executeQuery("SELECT NhapHoc FROM projectkoala.students where Id = "+idStudent+";");
            while(rs1.next()){
                a = rs1.getString(1);
            }
            statement.close();
            connect.close();
            a = new XuLiXau().NgayThangNam(a);
        } catch (SQLException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a;
     }
}
class TrongMuonComparator implements Comparator<Object[]> {

 public int compare(Object[] o1, Object[] o2) {
  if(!o1[1].equals(o2[1])){
      return 0;
  }
  String age1 = (String) o1[0];
  String[] x= age1.split(" ");
  String age2 = (String) o2[0];
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