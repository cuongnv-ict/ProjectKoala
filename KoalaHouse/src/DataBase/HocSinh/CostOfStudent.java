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
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void InsertDSPhiCuaHS(int idStudent,String idCost,int idFac){
        String query ="INSERT INTO `projectkoala`.`students_has_cost` (`Students_Id`, `Cost_Id`, `Faculties_Id`, `IsDebt`) VALUES ('"+String.valueOf(idStudent)+"', '"+idCost+"', '"+String.valueOf(idFac)+"', '1');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeleteDSPhiCuaHs(int idStudent,int idCost, int idFac){
        String query = "DELETE FROM `projectkoala`.`students_has_cost` WHERE `Students_Id`='"+String.valueOf(idStudent)+"' and`Cost_Id`='"+idCost+"' and`Faculties_Id`='"+idFac+"'";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdatePhiCuaHs(int idStudent,int idCost, int idFac){
        String query = "UPDATE `projectkoala`.`students_has_cost` SET `IsDebt`='0' WHERE `Students_Id`='"+idStudent+"' and`Cost_Id`='"+idCost+"' and`Faculties_Id`='"+idFac+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangThemHocPhiChoHocSinh(JTable table,int students_id,int idFac){
        try {
            Object [] nameColumn = {"Mã","Tên","Kì học","Năm học","Giá","Đánh dấu" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id not in(select cost_id from students_has_cost where students_id = "+students_id+") and Faculties_Id = "+idFac+" ");
            System.out.println(""+idFac);
            while(rs1.next()){
                Object str[] = new Object[6];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(4);
                str[2] = rs1.getString(3);
                str[3] = rs1.getString(6);
                str[4] = rs1.getString(5);        
                if(((String)str[4]).charAt(0)=='-'){
                    str[4] = ((String)str[4]).substring(1);
                }
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
                str[2] = rs1.getString(3);
                str[3] = rs1.getString(6);
                str[4] = rs1.getString(5);        
                if(((String)str[4]).charAt(0)=='-'){
                    str[4] = ((String)str[4]).substring(1);
                }
                //tinh so tien trong muon
                if(str[0].equals("13")||str[0].equals("14")||str[0].equals("15")||str[0].equals("16")){
                    int phi = Integer.parseInt((String) str[4]);
                    int totalTime = new AStudentAndLateDay().LateDay(students_id,idFac);
                    phi = totalTime *phi;
                    str[4] = phi;
                }
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
                str1[4] = rs1.getString(1);
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
        } catch (SQLException ex) {
            Logger.getLogger(CostOfStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}