/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;

import DataBase.ConnectData;
import DataBase.DataTable;
import edu.com.XuLy;
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
                //kiem tra xem có phai trong muon hay ko
                boolean check = false;
                String ten = str[0].toString();
                ten = ten.toLowerCase();
                 if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    check = true;
                }
                switch (rs1.getInt(3)) {
                    case 1:
                        str[1] = "Kỳ 1";
                        break;
                    case 2:
                        str[1] = "Kỳ 2";
                        break;
                    case 3:
                        str[1] = "Kỳ 3";
                        break;
                    case 4:
                        str[1] = "Kỳ hè";
                        break;
                    case 5:
                        str[1] = "Cả năm";
                        break;
                }
                str[2] = rs1.getString(6).substring(0, 4);
                str[3] = rs1.getString(5);        
                if(((String)str[3]).charAt(0)=='-'){
                    str[3] = ((String)str[3]).substring(1);
                }
                if(check){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int phi = Integer.parseInt((String) str[3]);
                    int totalTime = new AStudentAndLateDay().LateDay(students_id,idFac,ki,nam);
                    phi = totalTime *phi;
                    str[3] = phi;
                }
                str[3] = XuLy.setMoney(str[3].toString());
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
                str1[3] = XuLy.setMoney(rs1.getString(1));
                data.add(str1);
                }
            }
            //xem co dong hoc phi dat coc khong
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.cost where Id in(select Cost_Id from students_has_cost where Students_Id = "+students_id+" and IsDebt = 0 and Cost_Id = 1);");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= rs1.getString(4);
                switch (rs1.getInt(3)) {
                    case 1:
                        str1[1] = "Kỳ 1";
                        break;
                    case 2:
                        str1[1] = "Kỳ 2";
                        break;
                    case 3:
                        str1[1] = "Kỳ 3";
                        break;
                    case 4:
                        str1[1] = "Kỳ hè";
                        break;
                    case 5:
                        str1[1] = "Cả năm";
                        break;
                }
                str1[2] = rs1.getString(6).substring(0, 4);
                str1[3] = rs1.getString(5);        
                if(((String)str1[3]).charAt(0)=='-'){
                    str1[3] = ((String)str1[3]).substring(1);
                }
                str1[3] = XuLy.setMoney(str1[3].toString());
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
    public int PhiDatCoc(int idStudent){
        int total = 0;
        try {
            rs1 = statement.executeQuery("SELECT Amount FROM projectkoala.cost where Id in(select Cost_Id from students_has_cost where Students_Id = "+idStudent+" and IsDebt = 0 and Cost_Id = 1);");
            while(rs1.next()){
                total = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
