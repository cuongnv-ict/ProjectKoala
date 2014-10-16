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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author nguyen van cuong
 */

    
public class CostOfStudent {
     private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public CostOfStudent(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void InsertDSPhiCuaHS(int idStudent,String idCost,int idFac){
        String query ="INSERT INTO `projectkoala`.`students_has_cost` (`Students_Id`, `Cost_Id`, `Faculties_Id`, `IsDebt`) VALUES ('"+String.valueOf(idStudent)+"', '"+idCost+"', '"+String.valueOf(idFac)+"', '1');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeleteDSPhiCuaHs(int idStudent,int idCost, int idFac){
        String query = "DELETE FROM `projectkoala`.`students_has_cost` WHERE `Students_Id`='"+String.valueOf(idStudent)+"' and`Cost_Id`='"+idCost+"' and`Faculties_Id`='"+idFac+"'";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdatePhiCuaHs(int idStudent,int idCost, int idFac){
        String query = "UPDATE `projectkoala`.`students_has_cost` SET `IsDebt`='0' WHERE `Students_Id`='"+idStudent+"' and`Cost_Id`='"+idCost+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangThemHocPhiChoHocSinh(JTable table,int students_id,int idFac){
        int yearActive = 0;
        yearActive = new Get().getYearActive(idFac);
        try {
            Object [] nameColumn = {"Mã","Tên","Kì học","Năm học","Giá","Đánh dấu" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id not in(select cost_id from students_has_cost where students_id = "+students_id+") and Faculties_Id = "+idFac+" and cost.year >= "+yearActive+" Order by cost.year,cost.Semesters;");
            System.out.println(""+idFac);
            while(rs1.next()){
                Object str[] = new Object[6];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(4);
                //kiem tra dat coc
                boolean checkDatCoc = false;
                String ten = str[1].toString();
                ten = ten.toLowerCase();
                 if(((ten.indexOf("dat")!= -1)&&(ten.indexOf("coc")!= -1))||((ten.indexOf("đặt")!= -1)&&(ten.indexOf("cọc")!= -1))){
                    checkDatCoc = true;
                }
                 if(checkDatCoc){
                     int datcoc = new Get().GetIsDatCoc(students_id, idFac);
                     if(datcoc==1) continue;
                 }
                switch (rs1.getInt(3)) {
                    case 1:
                        str[2] = "Kỳ 1";
                        break;
                    case 2:
                        str[2] = "Kỳ 2";
                        break;
                    case 3:
                        str[2] = "Kỳ 3";
                        break;
                    case 4:
                        str[2] = "Kỳ hè";
                        break;
                    case 5:
                        str[2] = "Cả năm";
                        break;
                }
                str[3] = rs1.getString(6).substring(0, 4);
                int year = Integer.parseInt(String.valueOf(str[3]));
                year ++;
                str[3] = String.valueOf(year-1)+"-"+String.valueOf(year);
                str[4] = rs1.getString(5);        
                if(((String)str[4]).charAt(0)=='-'){
                    str[4] = ((String)str[4]).substring(1);
                }
                str[4] = XuLy.setMoney(str[4].toString());
                str[5] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
            if (data.size() == 0) {
                Object[][] name = new Object[][]{nameColumn};
                XuLy.resizeColumnWidth(table, XuLy.getSize(name));
            } else {
                XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangHocPhiCuaHocSinh(int students_id,int idFac,JTable table){
         try {
            Object [] nameColumn = {"Mã",  "Tên", "Kì học", "Năm học", "Giá" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" and IsDebt =1)");
            while(rs1.next()){
                Object str[] = new Object[5];
                str[0]= rs1.getString(1);
                str[1] = rs1.getString(4);
                //kiem tra xem có phai trong muon hay ko
                boolean check = false;
                //kiem tra xem co phai ky he khong
                boolean kiHe = false;
                //kiem tra xem co phai hoan hoc phi khong
                boolean hoanHocPhi = false;
                String ten = str[1].toString();
                ten = ten.toLowerCase();
                 if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    check = true;
                }
                 if(((ten.indexOf("hoc")!= -1)&&(ten.indexOf("he")!= -1))||((ten.indexOf("học")!= -1)&&(ten.indexOf("hè")!= -1))){
                    kiHe = true;
                }
                 if(ten.indexOf("hoan hoc phi")!= -1||ten.indexOf("hoàn học phí")!= -1){
                    hoanHocPhi = true;
                }
                switch (rs1.getInt(3)) {
                    case 1:
                        str[2] = "Kỳ 1";
                        break;
                    case 2:
                        str[2] = "Kỳ 2";
                        break;
                    case 3:
                        str[2] = "Kỳ 3";
                        break;
                    case 4:
                        str[2] = "Kỳ hè";
                        break;
                    case 5:
                        str[2] = "Cả năm";
                        break;
                }
                str[3] = rs1.getString(6).substring(0, 4);
                int year = Integer.parseInt(String.valueOf(str[3]));
                year ++;
                str[3] = String.valueOf(year-1)+"-"+String.valueOf(year);
                str[4] = rs1.getString(5);        
                if(((String)str[4]).charAt(0)=='-'){
                    str[4] = ((String)str[4]).substring(1);
                }
                if(check){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int phi = Integer.parseInt((String) str[4]);
                    int totalTime = new AStudentAndLateDay().LateDay(students_id,idFac,ki,nam);
                    phi = totalTime *phi;
                    if(phi==0){
                        JOptionPane.showMessageDialog(null, "Phí Trông Muộn Này Không Khả Dụng, Chương Trình Sẽ Tự Động Xóa");
                        DeleteDSPhiCuaHs(students_id, Integer.parseInt(str[0].toString()), idFac);
                        continue;
                    }
                    str[4] = phi;
                }
                if(kiHe){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int phi = Integer.parseInt((String) str[4]);
                    int totalTime = new Get().GetNumberSummerWeek(students_id,ki,nam);
                    phi = totalTime *phi;
                    if(phi==0){
                        JOptionPane.showMessageDialog(null, "Bạn Chưa Thêm Lịch Học Hè Cho Học Sinh, Chương Trình Sẽ Tự Động Xóa Phí Này");
                        DeleteDSPhiCuaHs(students_id, Integer.parseInt(str[0].toString()), idFac);
                        continue;
                    }
                    str[4] = phi;
                }
                if(hoanHocPhi){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int phi = Integer.parseInt((String) str[4]);
                    int totalTime = new Get().GetSoNgayNghiPhep(students_id,ki,nam);
                    phi = totalTime *phi;
                    if(phi==0){
                        JOptionPane.showMessageDialog(null, "Học Sinh Này Không Có Ngày Nghỉ Phép, Chương Trình Sẽ Tự Động Xóa Phí Này");
                        DeleteDSPhiCuaHs(students_id, Integer.parseInt(str[0].toString()), idFac);
                        continue;
                    }
                    str[4] = phi;
                }
                str[4] = XuLy.setMoney(str[4].toString());
                data.add(str);
            }
            //xem xem hoc sinh co bi no khong
            rs1 = statement.executeQuery("SELECT Debt FROM projectkoala.students where Id = "+students_id+" ");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= "0";
                str1[1] = "Nợ";
                str1[2] = "---";
                str1[3] = "---";
                str1[4] = XuLy.setMoney(rs1.getString(1));
                data.add(str1);
                }
            }
            //xem co phi xe bus không
            rs1 = statement.executeQuery("SELECT TienXe,StartDate,EndDate FROM projectkoala.buslist where idStudents = "+students_id+" and IsActive = 1;");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= "1";
                str1[1]="Xe Bus";
                str1[2] = "---";
                str1[3] = "---";
                str1[4] = XuLy.setMoney(rs1.getString(1));
                data.add(str1);
                }
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
