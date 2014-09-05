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
            //Logger.getLogger(GetTotal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int GetTotalMoney(int students_id,int idFac){
    int total=0;
         try {
            Object [] nameColumn = {"Mã",  "Tên", "Kì học", "Năm học", "Giá" };
            ArrayList<Object []> data = new ArrayList<Object []>();
             rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" and IsDebt =1)");
            while(rs1.next()){
                Object str[] = new Object[4];
                str[0] = rs1.getString(4);
                //kiem tra xem có phai trong muon hay ko
                boolean check = false;
                boolean kiHe = false;
                boolean hoanHocPhi = false;
                String ten = str[0].toString();
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
                str[1] = new XuLiXau().NamThangNgay(rs1.getString(7));
                str[2] = new XuLiXau().NamThangNgay(rs1.getString(8));
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
                if(kiHe){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int number = new Get().GetNumberSummerWeek(students_id,ki,nam);
                    int phi = Integer.parseInt((String) str[3]);
                    phi = phi * number;
                    str[3] = phi;
                }
                if(hoanHocPhi){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int number = new Get().GetSoNgayNghiPhep(students_id,ki,nam);
                    int phi = Integer.parseInt((String) str[3]);
                    phi = phi * number;
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
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.cost where Id = (SELECT max(Cost_Id) FROM projectkoala.students_has_cost where Students_Id = "+students_id+" and IsDebt = 0 and Cost_Id in (SELECT Id FROM cost where NameCost = \"Phí Đặt Cọc\"))");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= rs1.getString(4)+" (Hoàn Trả)";
                str1[1] = new XuLiXau().NamThangNgay(rs1.getString(7));
                str1[2] = new XuLiXau().NamThangNgay(rs1.getString(8));
                str1[3] = rs1.getString(5);        
                if(((String)str1[3]).charAt(0)=='-'){
                    str1[3] = ((String)str1[3]).substring(1);
                }
                str1[3] = XuLy.setMoney(str1[3].toString());
                //data.add(str1);
                }
            }
            //xem co phi xe bus không
            rs1 = statement.executeQuery("SELECT TienXe,StartDate,EndDate FROM projectkoala.buslist where idStudents = "+students_id+" and IsActive = 1;");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= "Xe Bus";
                str1[1] = new XuLiXau().NamThangNgay(rs1.getString(2));
                str1[2] = new XuLiXau().NamThangNgay(rs1.getString(3));
                str1[3] = XuLy.setMoney(rs1.getString(1));
                data.add(str1);
                }
            }
            //tinh tong so tien
            for(int i=0;i<data.size();i++){
                Object str[] = new Object[5];
                str = data.get(i);
                if(str[0].toString().toLowerCase().indexOf("hoàn")== -1)
                    total += Integer.parseInt(XuLy.getMoney(str[3].toString()));
                else
                    total -= Integer.parseInt(XuLy.getMoney(str[3].toString()));
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetTotal.class.getName()).log(Level.SEVERE, null, ex);
        }
         return total;
    }
    public int GetIdSemester(int idFac,String nam,String date){
    int idSemester =0;
        try {
            rs1 = statement.executeQuery("SELECT min(SemesterNumber) FROM projectkoala.semesters where Faculties_Id = "+idFac+" and StartDate <= '"+date+"' and EndDate >= '"+date+"'");
            if(rs1!= null)
            while(rs1.next()){
                idSemester = rs1.getInt(1);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetTotal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idSemester;
}
}
