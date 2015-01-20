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
 * @author nguyen van cuong
 */
     
public class HistoryManagerment {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public HistoryManagerment(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangLichSuDongTienCuaHocSinh(int students_id,JTable table){
         try {
            Object [] nameColumn = {"Số Hóa Đơn", "Học sinh","Lớp", "Người Thu", "Số Tiền Thu","Ngày Đóng", "Hình Thức Đóng","Chiết Khấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            int idFac = new Get().GetIDFac();
            
            rs1 = statement.executeQuery("select * from receipts where Students_Id ="+students_id);
            while(rs1.next()){
                Object str[] = new Object[8];
                int sott = rs1.getInt(5);
                String soHoaDon = null;
                switch(idFac){
            case 1: soHoaDon = "BT"+XuLy.getNumber4(String.valueOf(sott));break;
            case 2: soHoaDon = "DQ"+XuLy.getNumber4(String.valueOf(sott));break;
            case 3: soHoaDon = "KB"+XuLy.getNumber4(String.valueOf(sott));break;
            case 4: soHoaDon = "HT"+XuLy.getNumber4(String.valueOf(sott));break;
            }
                str[0] = soHoaDon;
                str[1] = rs1.getString(6);
                str[2] = rs1.getString(14);
                str[3] = rs1.getString(7);
                str[4] = XuLy.setMoney(rs1.getString(8));  
                str[5] = new XuLiXau().NamThangNgay(rs1.getString(11));
                switch(rs1.getInt(12)){
                    case 0:
                        str[6] = "Tiền mặt";
                        break;
                    case 1:
                        str[6] = "Chuyển khoản";
                        break;
                }
                if(rs1.getInt(13)==0)
                    str[7]= "Không";
                else
                    str[7]= "Có" ;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,false,false
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
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangLichSuHoaDon(JTable table,int nam){
          try {
            Object [] nameColumn = {"Số Hóa Đơn", "Học sinh","Lớp", "Người Thu", "Số Tiền Thu","Ngày Đóng", "Hình Thức Đóng","Chiết Khấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            int idFac = new Get().GetIDFac();
          if(nam == 0)  
            rs1 = statement.executeQuery("select * from receipts order by Id DESC;");
          else
            rs1 = statement.executeQuery("select * from receipts order by Id DESC;");
            while(rs1.next()){
                Object str[] = new Object[8];
                int sott = rs1.getInt(5);
                String soHoaDon = null;
                switch(idFac){
            case 1: soHoaDon = "BT"+XuLy.getNumber4(String.valueOf(sott));break;
            case 2: soHoaDon = "DQ"+XuLy.getNumber4(String.valueOf(sott));break;
            case 3: soHoaDon = "KB"+XuLy.getNumber4(String.valueOf(sott));break;
            case 4: soHoaDon = "HT"+XuLy.getNumber4(String.valueOf(sott));break;
            }
                str[0] = soHoaDon;
                str[1] = rs1.getString(6);
                str[2] = rs1.getString(14);
                str[3] = rs1.getString(7);
                str[4] = XuLy.setMoney(rs1.getString(8));  
                str[5] = new XuLiXau().NamThangNgay(rs1.getString(11));
                switch(rs1.getInt(12)){
                    case 0:
                        str[6] = "Tiền mặt";
                        break;
                    case 1:
                        str[6] = "Chuyển khoản";
                        break;
                }
                if(rs1.getInt(13)==0)
                    str[7]= "Không";
                else
                    str[7]= "Có" ;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,false,false
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
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void InsertLSHoaDon(int idStudent,String idFac,int NOofReciept, String tenHS,String NguoiThu,String SoTien,String Date,int HinhThucDong,String PhanTram,String lop){
        int i = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(id) FROM receipts;");
            while(rs1.next()){
                try{
                i= Integer.parseInt(rs1.getString(1));
                i++;
                }catch(java.lang.NumberFormatException e){
                    i=1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //get id account
        int idAccount=0;
        try {
            rs1 = statement.executeQuery("SELECT Id FROM projectkoala.accounts where UserName = \"admin\";");
            while(rs1.next()){
                idAccount = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //update
        String query ="INSERT INTO `projectkoala`.`receipts` (`Id`, `Faculties_Id`, `Accounts_Id`, `Students_Id`, `No`, `NamePayer`, `NameCasher`, `Number`, `CreateDate`, `IsTransfer`, `Percent`, `Reason`) VALUES ('"+i+"', '"+idFac+"', '"+idAccount+"', '"+idStudent+"', '"+NOofReciept+"', '"+tenHS+"', '"+NguoiThu+"', '"+SoTien+"', '"+Date+"', '"+HinhThucDong+"', '"+PhanTram+"', '"+lop+"');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            statement.close();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void InsertLSHoaDonNoIDStudent(String idFac,int NOofReciept, String NguoiDong,String NguoiThu,String SoTien,String Date,int HinhThucDong,String PhanTram,String LiDo){
        int i = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(id) FROM receipts;");
            while(rs1.next()){
                try{
                i= Integer.parseInt(rs1.getString(1));
                i++;
                }catch(java.lang.NumberFormatException e){
                    i=1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //get id account
        int idAccount=0;
        try {
            rs1 = statement.executeQuery("SELECT Id FROM projectkoala.accounts where UserName = \"admin\";");
            while(rs1.next()){
                idAccount = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        //update
        String query ="INSERT INTO `projectkoala`.`receipts` (`Id`, `Faculties_Id`, `Accounts_Id`, `No`, `NamePayer`, `NameCasher`, `Number`, `CreateDate`, `IsTransfer`, `Percent`, `Reason`) VALUES ('"+i+"', '"+idFac+"', '"+idAccount+"', '"+NOofReciept+"', '"+NguoiDong+"', '"+NguoiThu+"', '"+SoTien+"', '"+Date+"', '"+HinhThucDong+"', '"+PhanTram+"', '"+LiDo+"');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            statement.close();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangLichSuPhiDongCuaHocSinh(int students_id,int idFac,JTable table){
       try {
            Object [] nameColumn = {"Mã",  "Tên", "Kì học", "Năm học", "Giá" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+"  and IsDebt =0)order by cost.year,cost.Semesters;");
            while(rs1.next()){
                Object str[] = new Object[5];
                str[0]= rs1.getString(1);
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
                    str[4] = phi;
                }
                if(kiHe){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int number = new Get().GetNumberSummerWeek(students_id,ki,nam);
                    int phi = Integer.parseInt((String) str[4]);
                    phi = phi * number;
                    str[4] = phi;
                }
                if(hoanHocPhi){
                    String ki = rs1.getString(3);
                    String nam = rs1.getString(6).substring(0, 4);
                    int number = new Get().GetSoNgayNghiPhep(students_id,ki,nam);
                    int phi = Integer.parseInt((String) str[4]);
                    phi = phi * number;
                    str[4] = phi;
                }
                str[4] = XuLy.setMoney(str[4].toString());
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,false,false
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
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
