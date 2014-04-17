/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;
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
 * @author Pham The Quyen
 */
public class NghiPhep {
     private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public NghiPhep(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void NghiPhepInfo(int idStudent,JTable table){
        try {
            Object [] nameColumn = {"Ngày Bắt Đầu","Ngày Kết Thúc"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.leaves where Students_Id = "+idStudent);
            while(rs1.next()){
                Object str[] = new Object[2];
                str[0]= rs1.getString(4);
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
    public void InsertNghiPhep(int idStudent,int idFac,String batdau,String ketthuc){
        int id = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(Id) FROM leaves");
            while(rs1.next()){
                id = rs1.getInt(1);
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query ="INSERT INTO `projectkoala`.`leaves` (`Id`, `Faculties_Id`, `Students_Id`, `StartDate`, `EndDate`) VALUES ('"+id+"', '"+idFac+"', '"+idStudent+"', '"+batdau+"', '"+ketthuc+"');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void XoaNghiPhep(int idStudent,String batdau,String ketthuc){
        String query ="DELETE FROM `projectkoala`.`leaves` WHERE `Students_Id`='"+idStudent+"' and `StartDate`='"+batdau+"' and `EndDate` = '"+ketthuc+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
