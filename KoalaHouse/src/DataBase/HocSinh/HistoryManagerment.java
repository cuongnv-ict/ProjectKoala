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
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangLichSuDongTienCuaHocSinh(int students_id,JTable table){
         try {
            Object [] nameColumn = {"Số Hóa Đơn", "Người Đóng", "Người Thu", "Số Tiền Thu","Ngày Đóng", "Hình Thức Đóng","Chiết Khấu","Lí Do CK"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from receipts where Students_Id ="+students_id);
            while(rs1.next()){
                Object str[] = new Object[8];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(6);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(8);  
                str[4] = rs1.getString(11);
                switch(rs1.getInt(12)){
                    case 0:
                        str[5] = "Tiền mặt";
                        break;
                    case 1:
                        str[5] = "Chuyển khoản";
                        break;
                }
                str[6]= rs1.getString(13);
                str[7]= rs1.getString(14);
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
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void InsertLSHoaDon(int idStudent,String idFac, String NguoiDong,String NguoiThu,String SoTien,String Date,int HinhThucDong,String PhanTram,String LiDo){
        int i = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(id) FROM receipts;");
            while(rs1.next()){
                i=Integer.parseInt(rs1.getString(1));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query ="INSERT INTO `projectkoala`.`receipts` (`Id`, `Faculties_Id`, `Accounts_Id`, `Students_Id`, `No`, `NamePayer`, `NameCasher`, `Number`, `CreateDate`, `IsTransfer`, `Percent`, `Reason`) VALUES ('"+i+"', '"+idFac+"', '1', '"+idStudent+"', '"+i+"', '"+NguoiDong+"', '"+NguoiThu+"', '"+SoTien+"', '"+Date+"', '"+HinhThucDong+"', '"+PhanTram+"', '"+LiDo+"');";
        try {
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangLichSuPhiDongCuaHocSinh(int students_id,int idFac,JTable table){
       try {
            Object [] nameColumn = {"Mã",  "Tên", "Kì học", "Năm học", "Giá" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+"  and IsDebt =0)");
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
        } catch (SQLException ex) {
            Logger.getLogger(HistoryManagerment.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
