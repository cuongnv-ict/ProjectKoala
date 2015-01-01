/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;

import DataBase.ConnectData;
import DataBase.DataTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pham The Quyen
 */
public class RecieptManagerment {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public RecieptManagerment(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int GetNumberReceipt(){
        int num = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(id) FROM receipts;");
            while(rs1.next()){
                num = rs1.getInt(1);
                num++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
    public void BangDSPhiHoaDon(int students_id,int idFac,JTable table){
       try {
            Object [] nameColumn = {"Tên", "Kì học", "Năm học", "Giá" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" and IsDebt =1)");
            while(rs1.next()){
                Object str[] = new Object[4];
                str[0] = rs1.getString(4);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(6);
                str[3] = rs1.getString(5);        
                if(((String)str[3]).charAt(0)=='-'){
                    str[3] = ((String)str[3]).substring(1);
                }
                Object ma = rs1.getString(1);
                if(ma.equals("13")||ma.equals("14")||ma.equals("15")||ma.equals("16")){
                    int phi = Integer.parseInt((String) str[3]);
                    int totalTime = new AStudentAndLateDay().LateDay(students_id,idFac);
                    phi = totalTime *phi;
                    str[3] = phi;
                }
                data.add(str);
            }
            //xem xem hoc sinh co bi no khong
            rs1 = statement.executeQuery("SELECT Debt FROM projectkoala.students where Id = "+students_id+" ");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= "Nợ";
                str1[1] = "---";
                str1[2] = "---";
                str1[3] = rs1.getString(1);
                data.add(str1);
                }
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
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
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
