/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;
import DataBase.ConnectData;
import DataBase.DataTable;
import edu.com.ThongTin;
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
            //Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void NghiPhepInfo(int idStudent,JTable table){
        try {
            Object [] nameColumn = {"STT","Ngày Bắt Đầu","Ngày Kết Thúc","Số Ngày","Trạng Thái"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("SELECT Id,StartDate,EndDate,NumberOfDay,IsActive FROM projectkoala.leaves where Students_Id = "+idStudent +" order by StartDate desc");
            while(rs1.next()){
                
                Object str[] = new Object[5];
                str[0] = rs1.getInt(1);
                str[1] = new XuLiXau().NgayThangNam( rs1.getString(2));
                str[2] = new XuLiXau().NgayThangNam( rs1.getString(3));
                str[3] = rs1.getString(4);
                if(rs1.getInt(5)==1) 
                    str[4]= "Chưa Hoàn Phí";
                else str[4]= "Đã Hoàn Phí";
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++){
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                 Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
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
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
              
            JOptionPane.showMessageDialog(null,"Có Lỗi phần nhập tên học sinh bạn làm ơn kiểm tra lại thông số đầu vào",null,JOptionPane.INFORMATION_MESSAGE);

        }
    }
    public void InsertNghiPhep(int idStudent,int idFac,String batdau,String ketthuc,String numberday){
        int id = 0;
        try {
            rs1 = statement.executeQuery("SELECT max(Id) FROM leaves");
            while(rs1.next()){
                id = rs1.getInt(1);
                id++;
            }
            String query ="INSERT INTO `projectkoala`.`leaves` (`Id`, `Faculties_Id`, `Students_Id`, `StartDate`, `EndDate`,`NumberOfDay`,`IsActive`) VALUES ('"+id+"', '"+ThongTin.trungtam+"', '"+idStudent+"', '"+batdau+"', '"+ketthuc+"','"+numberday+"',1);";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
               
        }
        
    }
    public void XoaNghiPhep(int idStudent,String batdau,String ketthuc,int id){
        String query ="DELETE FROM `projectkoala`.`leaves` WHERE `Students_Id`='"+idStudent+"' and `Id`= '"+id+"' and `StartDate`='"+new XuLiXau().NamThangNgay(batdau)+"' and `EndDate` = '"+new XuLiXau().NamThangNgay(ketthuc)+"';";
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
