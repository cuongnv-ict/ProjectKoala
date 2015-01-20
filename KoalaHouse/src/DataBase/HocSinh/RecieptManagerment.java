/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;

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
            //Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int GetNextNumberReceipt(String date){
        int num = 0;
        try {
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.receipts where Id = (SELECT max(id) FROM receipts);");
            while(rs1.next()){
                if(date.substring(0, 4).equals(rs1.getString(11).substring(0, 4))){
                    num = rs1.getInt(5);
                }
                num++;
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
    public void BangDSPhiHoaDon(int students_id,int idFac,JTable table){
       try {
            Object [] nameColumn = {"STT","Tên Phí", "Thời Gian", "Thành Tiền"};
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
                if(ten.indexOf("hoàn học phí")!=-1 || ten.indexOf("hoan hoc phi")!= -1){
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
                data.add(str1);
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
            //sap xep lai ten phi
            for(int j=0;j<data.size();j++){
                Object[] s = data.get(j);
                String thoigian = s[1].toString()+" -> "+s[2].toString();
                s[1] = s[0].toString();
                s[2] = thoigian;
                s[0] = j+1;
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void BangDSPhiThongBaoTrongNam(int students_id,int idFac,JTable table,boolean isHe){
        int stt = 1;
       try {
            Object [] nameColumn = {"STT", "Nội dung", "Thời gian","Số Tiền"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            if(isHe){
            Object str[] = new Object[4];
            str[0] = stt;
            str[1] = "Học phí kỳ hè";
            int phi = 0;
            int year = new Get().getYearActive(idFac);
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.cost where year = "+year+" and NameCost = \"Phí Học Hè\";");
            while(rs1.next()){
                phi = rs1.getInt(5);
                phi = phi * 6;
                String tu = new XuLiXau().NamThangNgay(rs1.getString(7));
                String den = new XuLiXau().NamThangNgay(rs1.getString(8));
                str[2] = tu +" -> "+den;
            }
            str[3] = XuLy.setMoney(String.valueOf(phi));
            stt++;
            data.add(str);
        }
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" and IsDebt =1)");
            while(rs1.next()){
                Object str[] = new Object[4];
                str[0] = stt;
                str[1] = rs1.getString(4);
                //kiem tra xem có phai trong muon hay ko
                boolean check = false;
                boolean kiHe = false;
                boolean hoanHocPhi = false;
                String ten = str[1].toString();
                ten = ten.toLowerCase();
                 if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    check = true;
                }
                if(((ten.indexOf("hoc")!= -1)&&(ten.indexOf("he")!= -1))||((ten.indexOf("học")!= -1)&&(ten.indexOf("hè")!= -1))){
                    kiHe = true;
                }
                if(ten.indexOf("hoàn học phí")!=-1 || ten.indexOf("hoan hoc phi")!= -1){
                    hoanHocPhi = true;
                }
                String tu = new XuLiXau().NamThangNgay(rs1.getString(7));
                String den = new XuLiXau().NamThangNgay(rs1.getString(8));
                str[2] = tu +" -> "+den;
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
                stt++;
            }
            //xem xem hoc sinh co bi no khong
            rs1 = statement.executeQuery("SELECT Debt FROM projectkoala.students where Id = "+students_id+" ");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= stt;
                str1[1] = "Nợ";
                str1[2] = "";
                str1[3] = XuLy.setMoney(rs1.getString(1));
                data.add(str1);
                stt++;
                }
            }
            //xem co dong hoc phi dat coc khong
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.cost where Id = (SELECT max(Cost_Id) FROM projectkoala.students_has_cost where Students_Id = "+students_id+" and IsDebt = 0 and Cost_Id in (SELECT Id FROM cost where NameCost = \"Phí Đặt Cọc\"))");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0] = stt;
                str1[1]= rs1.getString(4)+" (Hoàn Trả)";
                String tu = new XuLiXau().NamThangNgay(rs1.getString(7));
                String den = new XuLiXau().NamThangNgay(rs1.getString(8));
                str1[2] = tu +" -> "+den;
                str1[3] = rs1.getString(5);        
                if(((String)str1[3]).charAt(0)=='-'){
                    str1[3] = ((String)str1[3]).substring(1);
                }
                str1[3] = XuLy.setMoney(str1[3].toString());
                data.add(str1);
                stt++;
                }
            }
            //xem co phi xe bus không
            rs1 = statement.executeQuery("SELECT TienXe,StartDate,EndDate FROM projectkoala.buslist where idStudents = "+students_id+" and IsActive = 1;");
            while(rs1.next()){
                Object str1[] = new Object[5];
                if(rs1.getInt(1)>0){
                str1[0]= stt;
                str1[1]= "Phí Xe Buýt";
                String tu = new XuLiXau().NamThangNgay(rs1.getString(2));
                String den = new XuLiXau().NamThangNgay(rs1.getString(3));
                str1[2] = tu +" -> "+den;
                str1[3] = XuLy.setMoney(rs1.getString(1));
                data.add(str1);
                stt++;
                }
            }
            Object str3[] = new Object[5];
            str3[0] = "";
            str3[1] = "Tổng";
            data.add(str3);
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void BangDSPhiThongBaoDauNam(int students_id,int idFac,JTable table){
        int stt = 1;
       try {
            Object [] nameColumn = {"STT","Nội Dung", "Thời Gian", "Số Tiền"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            Object str1[] = new Object[4];
            Object str2[] = new Object[4];
            Object str3[] = new Object[4];
            Object str4[] = new Object[4];
            Object str5[] = new Object[4];
            Object str6[] = new Object[4];
            Object str7[] = new Object[4];
            Object str8[] = new Object[4];
            Object str9[] = new Object[4];
            Object str10[] = new Object[4];
            Object str11[] = new Object[4];
            str1[0]= "1";
            str1[1]="Học phí kỳ 1";
            str1[2]="";
            str1[3]="";
            str2[0]= "2";
            str2[1]="Học phí 3 kỳ (đã giảm 5%)";
            str2[2]="";
            str2[3]="";
            str3[0]= "3";
            str3[1]="Học phí cả năm (đã giảm 7%)";
            str3[2]="";
            str3[3]="";
            str4[0]= "4";
            str4[1]="Cơ sở vật chất kì 1";
            str4[2]="";
            str4[3]="";
            str5[0]= "5";
            str5[1]="Cơ sở vật chất cả năm";
            str5[2]="";
            str5[3]="";
            str6[0]= "6";
            str6[1]="Xe buýt kỳ 1";
            str6[2]="";
            str6[3]="";
            str7[0]= "7";
            str7[1]="Xe buýt 3 kỳ";
            str7[2]="";
            str7[3]="";
            str8[0]= "8";
            str8[1]="Xe buýt cả năm";
            str8[2]="";
            str8[3]="";
            str9[0]= "9";
            str9[1]="Hoàn học phí";
            str9[2]="";
            str9[3]="";
            str10[0]= "10";
            str10[1]="Phí trông muộn";
            str10[2]="";
            str10[3]="";
            str11[0]= "11";
            str11[1]="Ghi chú khác";
            str11[2]="";
            str11[3]="";
            int year = new Get().getYearActive(idFac);
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.cost where year = "+year+";");
            while(rs1.next()){
                Object str[] = new Object[4];
                str[1] = rs1.getString(4);
                //kiem tra xem có phai trong muon hay ko
                boolean check = false;
                boolean kiHe = false;
                boolean hoanHocPhi = false;
                boolean check1 = false;
                boolean check2 = false;
                boolean check3 = false;
                boolean check4 = false;
                boolean check5 = false;
                boolean check6 = false;
                boolean check7 = false;
                boolean check8 = false;
                boolean check9 = false;
                boolean check10 = false;
                boolean check11 = false;
                
                String ten = str[1].toString();
                ten = ten.toLowerCase();
                if((ten.indexOf("học phí")!=-1||ten.indexOf("hoc phi")!=-1) && (ten.indexOf(" 1")!= -1||ten.indexOf(" i")!=-1)){
                              check1 = true;
                }
                if((ten.indexOf("học phí")!=-1||ten.indexOf("hoc phi")!=-1) && (ten.indexOf("3 kỳ")!= -1||ten.indexOf("3 ky")!=-1)){
                  check2 = true;
                }
                if((ten.indexOf("học phí")!=-1||ten.indexOf("hoc phi")!=-1) && (ten.indexOf("cả năm")!= -1||ten.indexOf("ca nam")!=-1)){
                  check3 = true;
                }
                
                if((ten.indexOf("cơ sở")!=-1||ten.indexOf("co so")!=-1)&&(ten.indexOf("vật chất")!=-1||ten.indexOf("vat chat")!=-1) && (ten.indexOf("1")!= -1||ten.indexOf(" i")!=-1)&&ten.indexOf("ii")==-1){
                    check4 = true;
                }
                if((ten.indexOf("cơ sở")!=-1||ten.indexOf("co so")!=-1)&&(ten.indexOf("vật chất")!=-1||ten.indexOf("vat chat")!=-1) && (ten.indexOf("ca nam")!= -1||ten.indexOf("cả năm")!=-1)){
                    check5 = true;
                }
                if((ten.indexOf("học")!=-1||ten.indexOf("hoc")!=-1)&&
                        (ten.indexOf("phí")!=-1||ten.indexOf("phi")!=-1)&&
                        (ten.indexOf("kỳ")!=-1||ten.indexOf("ky")!=-1)&&
                        (((ten.indexOf(" 1")!=-1||ten.indexOf(" i")!=-1))
                            ||ten.indexOf("kỳi")!=-1||ten.indexOf("kyi")!=-1)
                  )
                {
                    check1 = true;
                }
                /************************** **********************************************************************************/
                
                if((ten.indexOf("học")!=-1||ten.indexOf("hoc")!=-1)&&
                        (ten.indexOf("phí")!=-1||ten.indexOf("phi")!=-1)&&
                        (ten.indexOf("kỳ")!=-1||ten.indexOf("ky")!=-1)&&
                        (ten.indexOf("3")!=-1||ten.indexOf(" iii")!=-1)
                        )
                {
                    check2 = true;
                }
                
                
                if((ten.indexOf("học")!=-1||ten.indexOf("hoc")!=-1)&&
                    (ten.indexOf("phí")!=-1||ten.indexOf("phi")!=-1)&&
                    (ten.indexOf("cả")!=-1||ten.indexOf("ca")!=-1||ten.indexOf("toan")!=-1||ten.indexOf("toàn")!=-1)&&
                    (ten.indexOf("năm")!=-1||ten.indexOf("nam")!=-1)
                  )
                {
                    check3 = true;
                }
                
                
                
                if((ten.indexOf("cơ")!=-1||ten.indexOf("co")!=-1)&&
                        (ten.indexOf("sở")!=-1||ten.indexOf("sở")!=-1)&&
                        (ten.indexOf("vật")!=-1||ten.indexOf("vat")!=-1)&&
                        (ten.indexOf("chất")!=-1||ten.indexOf("chat")!=-1)&&
                        (ten.indexOf("kỳ")!=-1||ten.indexOf("ky")!=-1)&&
                               (((ten.indexOf("1")!=-1||ten.indexOf(" i")!=-1)&&ten.indexOf("ii")==-1)
                            ||ten.indexOf("kỳi")!=-1||ten.indexOf("kyi")!=-1)
                 
                   )
                {
                    check4 = true;
                    
                }
                
                
                
                if((ten.indexOf("cơ")!=-1||ten.indexOf("co")!=-1)&&
                        (ten.indexOf("sở")!=-1||ten.indexOf("sở")!=-1)&&
                        (ten.indexOf("vật")!=-1||ten.indexOf("vat")!=-1)&&
                        (ten.indexOf("chất")!=-1||ten.indexOf("chat")!=-1)&&
                        (ten.indexOf("cả")!=-1||ten.indexOf("ca")!=-1||ten.indexOf("toan")!=-1||ten.indexOf("toàn")!=-1)&&
                        (ten.indexOf("năm")!=-1||ten.indexOf("nam")!=-1)
                        )
                {
                    check5=true;
                }
                str[0] = stt;
                String tu = new XuLiXau().NamThangNgay(rs1.getString(7));
                String den = new XuLiXau().NamThangNgay(rs1.getString(8));
                str[2] = tu+" -> "+den;
                str[3] = rs1.getString(5);
                if(((String)str[3]).charAt(0)=='-'){
                    str[3] = ((String)str[3]).substring(1);
                }
                str[3] = XuLy.setMoney(str[3].toString());
                if(check1){
                str1[2] = str[2];
                str1[3] = str[3];
                }
                if(check2){
                str2[2] = str[2];
                str2[3] = str[3];
                }
                if(check3){
                str3[2] = str[2];
                str3[3] = str[3];
                }
                if(check4){
                str4[2] = str[2];
                str4[3] = str[3];
                }
                if(check5){
                str5[2] = str[2];
                str5[3] = str[3];
                }
            }
            
            //xem co phi xe bus không
            str6[3] = "0";
            str7[3] = "0";
            str8[3] = "0";
            rs1 = statement.executeQuery("SELECT TienXe,StartDate,EndDate FROM projectkoala.buslist where idStudents = "+students_id+" and IsActive = 1;");
            while(rs1.next()){
                if(rs1.getInt(1)>0){
                str6[3] = XuLy.setMoney(rs1.getString(1));
                }
            }
            str6[2] = str1[2];
            str7[2] = str2[2];
            str8[2] = str3[2];
            //xem trong muon khong
            str9[3] = "0";
            str10[3] = "0";
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" and IsDebt =1)");
            while(rs1.next()){
                String ten = rs1.getString(4).toString();
                ten = ten.toLowerCase();
                boolean checktrongmuon = false;
                boolean checkhoanhoc  = false;
                 if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    checktrongmuon = true;
                }
                 if(ten.indexOf("hoàn học phí")!=-1 || ten.indexOf("hoan hoc phi")!= -1){
                    checkhoanhoc = true;
                }
                 boolean checkHoan = false;
                if(ten.indexOf("hoàn")!=-1){
                    checkHoan = true;
                }
                 if(checktrongmuon){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int phi = Integer.parseInt(rs1.getString(5));
                    int totalTime = new AStudentAndLateDay().LateDay(students_id,idFac,ki,nam);
                    phi = totalTime *phi;
                    str10[3] = XuLy.setMoney(String.valueOf(phi));
                    String tu = new XuLiXau().NamThangNgay(rs1.getString(7));
                    String den = new XuLiXau().NamThangNgay(rs1.getString(8));
                    str10[2] = tu+" -> "+den;
                }
                  if(checkhoanhoc){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int number = new Get().GetSoNgayNghiPhep(students_id,ki,nam);
                    int phi = Integer.parseInt(rs1.getString(5));
                    phi = phi * number;
                    String sotien2 = XuLy.getMoney((String) str9[3]);
                    int tong  = phi + Integer.parseInt(sotien2);
                    str9[3] = XuLy.setMoney(String.valueOf(tong));
                }
                  if(checkhoanhoc==false && checkHoan){
                      String sotien = rs1.getString(5);
                if(((String)sotien).charAt(0)=='-'){
                    sotien = ((String)sotien).substring(1);
                }
                String sotien2 = XuLy.getMoney((String) str9[3]);
                int tong  = Integer.parseInt(sotien) + Integer.parseInt(sotien2);
                str9[3] = XuLy.setMoney(String.valueOf(tong));
                  }
            }
            //xem co dong hoc phi dat coc khong
            rs1 = statement.executeQuery("SELECT * FROM projectkoala.cost where Id = (SELECT max(Cost_Id) FROM projectkoala.students_has_cost where Students_Id = "+students_id+" and IsDebt = 0 and Cost_Id in (SELECT Id FROM cost where NameCost = \"Phí Đặt Cọc\"))");
            while(rs1.next()){
                
                if(rs1.getInt(1)>0){
                String sotien = rs1.getString(5);        
                if(((String)sotien).charAt(0)=='-'){
                    sotien = ((String)sotien).substring(1);
                }
                String sotien2 = XuLy.getMoney((String) str9[3]);
                int tong  = Integer.parseInt(sotien) + Integer.parseInt(sotien2);
                str9[3] = XuLy.setMoney(String.valueOf(tong));
                str11[3] = "Có hoàn phí đặt cọc";
                }
            }
            data.add(str1);
            data.add(str2);
            data.add(str3);
            data.add(str4);
            data.add(str5);
            data.add(str6);
            data.add(str7);
            data.add(str8);
            data.add(str9);
            data.add(str10);
            data.add(str11);
            stt = 12;
            //add phi khac
             rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" and IsDebt =1)");
            while(rs1.next()){
                String ten = rs1.getString(4).toString();
                ten = ten.toLowerCase();
                boolean checktrongmuon = false;
                boolean checkhoanhoc  = false;
                 if(((ten.indexOf("trong")!= -1)&&(ten.indexOf("muon")!= -1))||((ten.indexOf("trông")!= -1)&&(ten.indexOf("muộn")!= -1))){
                    checktrongmuon = true;
                }
                 if(ten.indexOf("hoàn học phí")!=-1 || ten.indexOf("hoan hoc phi")!= -1){
                    checkhoanhoc = true;
                }
                 boolean checkHoan = false;
                if(ten.indexOf("hoàn")!=-1){
                    checkHoan = true;
                }
                boolean check1 = false;
                boolean check2 = false;
                boolean check3 = false;
                boolean check4 = false;
                boolean check5 = false;
                boolean check6 = false;
                boolean check7 = false;
                boolean check8 = false;
                boolean check9 = false;
                boolean check10 = false;
                boolean check11 = false;
                if((ten.indexOf("học phí")!=-1||ten.indexOf("hoc phi")!=-1) && (ten.indexOf(" 1")!= -1||ten.indexOf(" i")!=-1)){
                              check1 = true;
                }
                if((ten.indexOf("học phí")!=-1||ten.indexOf("hoc phi")!=-1) && (ten.indexOf("3 kỳ")!= -1||ten.indexOf("3 ky")!=-1)){
                  check2 = true;
                }
                if((ten.indexOf("học phí")!=-1||ten.indexOf("hoc phi")!=-1) && (ten.indexOf("cả năm")!= -1||ten.indexOf("ca nam")!=-1)){
                  check3 = true;
                }
                
                if((ten.indexOf("cơ sở")!=-1||ten.indexOf("co so")!=-1)&&(ten.indexOf("vật chất")!=-1||ten.indexOf("vat chat")!=-1) && (ten.indexOf("1")!= -1||ten.indexOf(" i")!=-1)&&ten.indexOf("ii")==-1){
                    check4 = true;
                }
                if((ten.indexOf("cơ sở")!=-1||ten.indexOf("co so")!=-1)&&(ten.indexOf("vật chất")!=-1||ten.indexOf("vat chat")!=-1) && (ten.indexOf("ca nam")!= -1||ten.indexOf("cả năm")!=-1)){
                    check5 = true;
                }
                if((ten.indexOf("học")!=-1||ten.indexOf("hoc")!=-1)&&
                        (ten.indexOf("phí")!=-1||ten.indexOf("phi")!=-1)&&
                        (ten.indexOf("kỳ")!=-1||ten.indexOf("ky")!=-1)&&
                        (((ten.indexOf(" 1")!=-1||ten.indexOf(" i")!=-1))
                            ||ten.indexOf("kỳi")!=-1||ten.indexOf("kyi")!=-1)
                  )
                {
                    check1 = true;
                }
                /************************** **********************************************************************************/
                
                if((ten.indexOf("học")!=-1||ten.indexOf("hoc")!=-1)&&
                        (ten.indexOf("phí")!=-1||ten.indexOf("phi")!=-1)&&
                        (ten.indexOf("kỳ")!=-1||ten.indexOf("ky")!=-1)&&
                        (ten.indexOf("3")!=-1||ten.indexOf(" iii")!=-1)
                        )
                {
                    check2 = true;
                }
                
                
                if((ten.indexOf("học")!=-1||ten.indexOf("hoc")!=-1)&&
                    (ten.indexOf("phí")!=-1||ten.indexOf("phi")!=-1)&&
                    (ten.indexOf("cả")!=-1||ten.indexOf("ca")!=-1||ten.indexOf("toan")!=-1||ten.indexOf("toàn")!=-1)&&
                    (ten.indexOf("năm")!=-1||ten.indexOf("nam")!=-1)
                  )
                {
                    check3 = true;
                }
                
                
                
                if((ten.indexOf("cơ")!=-1||ten.indexOf("co")!=-1)&&
                        (ten.indexOf("sở")!=-1||ten.indexOf("sở")!=-1)&&
                        (ten.indexOf("vật")!=-1||ten.indexOf("vat")!=-1)&&
                        (ten.indexOf("chất")!=-1||ten.indexOf("chat")!=-1)&&
                        (ten.indexOf("kỳ")!=-1||ten.indexOf("ky")!=-1)&&
                               (((ten.indexOf("1")!=-1||ten.indexOf(" i")!=-1)&&ten.indexOf("ii")==-1)
                            ||ten.indexOf("kỳi")!=-1||ten.indexOf("kyi")!=-1)
                 
                   )
                {
                    check4 = true;
                    
                }
                
                
                
                if((ten.indexOf("cơ")!=-1||ten.indexOf("co")!=-1)&&
                        (ten.indexOf("sở")!=-1||ten.indexOf("sở")!=-1)&&
                        (ten.indexOf("vật")!=-1||ten.indexOf("vat")!=-1)&&
                        (ten.indexOf("chất")!=-1||ten.indexOf("chat")!=-1)&&
                        (ten.indexOf("cả")!=-1||ten.indexOf("ca")!=-1||ten.indexOf("toan")!=-1||ten.indexOf("toàn")!=-1)&&
                        (ten.indexOf("năm")!=-1||ten.indexOf("nam")!=-1)
                        )
                {
                    check5=true;
                }
                
                if(check1||check2||check3||check4||check5||checkhoanhoc||checktrongmuon||checkHoan){
                    
                }
                else{
                    Object str[] = new Object[4];
                    str[0] = stt;
                    str[1] = rs1.getString(4);
                    str[3] = XuLy.setMoney(rs1.getString(5));
                    String tu = new XuLiXau().NamThangNgay(rs1.getString(7));
                    String den = new XuLiXau().NamThangNgay(rs1.getString(8));
                    str[2] = tu+" -> "+den;
                    data.add(str);
                    stt++;
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
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public int PhiDatCoc(int idStudent){
        int total = 0;
        try {
            rs1 = statement.executeQuery("SELECT Amount FROM projectkoala.cost where Id = (SELECT max(Cost_Id) FROM projectkoala.students_has_cost where Students_Id = "+idStudent+" and IsDebt = 0 and Cost_Id in (SELECT Id FROM cost where NameCost = \"Phí Đặt Cọc\"))");
            while(rs1.next()){
                total = rs1.getInt(1);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    public int GetIdPhiDatCoc(int idStudent){
        int id = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(Cost_Id) FROM projectkoala.students_has_cost where Students_Id = "+idStudent+" and IsDebt = 0 and Cost_Id in (SELECT Id FROM cost where NameCost = \"Phí Đặt Cọc\")");
            while(rs1.next()){
                id = rs1.getInt(1);
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecieptManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void UpdateXeBus(int idStudent){
        String query = "UPDATE `projectkoala`.`buslist` SET `IsActive`='0' WHERE `idStudents`='"+idStudent+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateSummerWeek(int idStudent){
        String query = "UPDATE `projectkoala`.`buslist` SET `IsActive`='0' WHERE `idStudents`='"+idStudent+"';";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void ResetReciept(){
        String query = "DELETE FROM `projectkoala`.`receipts`";
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
