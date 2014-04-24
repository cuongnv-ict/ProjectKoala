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
public class GetTotal {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public GetTotal(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GetTotal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int GetTotalMoney(int students_id,int idFac){
    int total=0;
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
                String ten = str[1].toString();
                ten = ten.toLowerCase();
                 if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    check = true;
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
            //tinh tong so tien
            for(int i=0;i<data.size();i++){
                Object str[] = new Object[5];
                str = data.get(i);
                total += Integer.parseInt(str[4].toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetTotal.class.getName()).log(Level.SEVERE, null, ex);
        }
         return total;
    }
}
