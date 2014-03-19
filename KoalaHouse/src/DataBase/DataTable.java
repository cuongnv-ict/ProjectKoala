/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author nguyen van cuong
 */
public class DataTable {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public DataTable(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachLop(JTable table){
        try {
            Object [] nameColumn = { "Tên Lớp", "Trình độ", "Học kì", "Giáo Viên", "Ngày bắt đầu", "Ngày kết thúc", "Số học sinh", "Tối đa", "Trạng thái","Đánh dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * ,count(Students_id) from classes,classes_has_students where classes.id= Classes_Id group by Classes_Id");
            while(rs1.next()){
                Object str[] = new Object[10];
                    str[0] = rs1.getString(6);
                   switch(rs1.getInt(4)){
                       case 1:
                           str[1] = "NẮNG MAI (SUNSHINE)";
                           break;
                       case 2:
                            str[1] = "TỔ ONG (BEEHIVE)";
                           break;
                       case 3:
                            str[1] = "TỔ KÉN (CHRYSALIS)";
                           break;
                       case 4:
                            str[1] = "MẪU GIÁO (KINDERGARTEN)";
                           break;
                   }
                   str[2] = rs1.getString(3);
                   str[3] = rs1.getString(7);
                   str[4] = rs1.getString(8);
                   str[5] = rs1.getString(9);
                   str[6] = rs1.getString(17);
                   str[7] = rs1.getString(11);
                   switch(rs1.getInt(12)){
                       case 0:
                           str[8] = "Đang giảng dạy";
                           break;
                       case 1:
                            str[8] = "Đã kết thúc";
                           break;
                 }       
                 str[9]=false;
                 data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,false,false,false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHocSinh(JTable table){
        try {
            Object [] nameColumn = {  "ID", "Tên", "Ngay Sinh","Trình Độ", "Lớp", "Hình Thức Học" };
            ArrayList<String []> data = new ArrayList<String []>();
            rs1 = statement.executeQuery("select students.id,fullname,brithday,levels_id,nameclass,isclient  from students,classes,classes_has_students where students.id = students_id and classes.id=classes_id");
            while(rs1.next()){
                String str[] = new String[6];
                    str[0] = rs1.getString(1);
                    str[1] = rs1.getString(2);
                    str[2] = rs1.getString(3);
                   switch(rs1.getInt(4)){
                       case 1:
                           str[3] = "NẮNG MAI (SUNSHINE)";
                           break;
                       case 2:
                            str[3] = "TỔ ONG (BEEHIVE)";
                           break;
                       case 3:
                            str[3] = "TỔ KÉN (CHRYSALIS)";
                           break;
                       case 4:
                            str[3] = "MẪU GIÁO (KINDERGARTEN)";
                           break;
                   }
                   
                 
                   str[4] = rs1.getString(5);
                   switch(rs1.getInt(6)){
                       case 1:
                           str[5] = "Chính Quy";
                           break;
                       case 0:
                            str[5] = "Chương Trình Bạn Là Khách";
                           break;
                 }       
                 data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachPhi(JTable table){
        try {
            Object [] nameColumn = {  "Tên", "Kì học", "Năm học", "Giá","Đánh dấu" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost");
            while(rs1.next()){
                if(rs1.getInt(5)!=0) continue;
                Object str[] = new Object[5];
                str[0] = rs1.getString(4);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(6);        
                if(((String)str[3]).charAt(0)=='-'){
                    str[3] = ((String)str[3]).substring(1);
                }
                str[4] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
           }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhHSLop(int classes_id,JTable table){
        try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from classes_has_students where classes_id = "+classes_id+ " )");
            while(rs1.next()){
                Object[]str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch(rs1.getInt(7)){
                       case 0:
                           str[3] = "Chính Quy";
                           break;
                       case 1:
                            str[3] = "Chương Trình Bạn Là Khách";
                           break;
                 }       
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhHSLop_Khach(int classes_id,JTable table){
        try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from classes_has_students where classes_id = "+classes_id+ " )");
            while(rs1.next()){
                Object[]str = new Object[7];
                if(rs1.getInt(7)==0){
                    continue;
                }
                else{
                    str[3] = "Chương trình bạn là khách";
                }
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhHSLop_ChinhQuy(int classes_id,JTable table){
        try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from classes_has_students where classes_id = "+classes_id+ " )");
            while(rs1.next()){
                Object[]str = new Object[7];
                if(rs1.getInt(7)==1){
                    continue;
                }
                else{
                    str[3] = "Chính Quy";
                }
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHocSinhDuCho(JTable table){
        
    }
    public void BangDanhSachHSDongDuPhi(JTable table){
         try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where students.id not in(select students_id from students_has_cost where isdebt = 1 group by students_id) and debt = 0");
            while(rs1.next()){
                Object[]str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch(rs1.getInt(7)){
                       case 1:
                           str[3] = "Chính Quy";
                           break;
                       case 0:
                            str[3] = "Chương Trình Bạn Là Khách";
                           break;
                 }       
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHSDongDuPhi(int classes_id,JTable table){
         try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where  debt=0 and students.id in(select Students_Id from classes_has_students where classes_id = "+classes_id+" and Students_Id not in(select students_has_cost.students_id from students_has_cost,classes_has_students where isdebt = 1 and students_has_cost.students_id=classes_has_students.students_id and classes_id = "+classes_id+" group by students_has_cost.students_id))");
            while(rs1.next()){
                Object[]str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch(rs1.getInt(7)){
                       case 1:
                           str[3] = "Chính Quy";
                           break;
                       case 0:
                            str[3] = "Chương Trình Bạn Là Khách";
                           break;
                 }       
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHocSinhNoPhi(JTable table){
         try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from students_has_cost where isdebt = 1 group by students_id) or debt!=0");
            while(rs1.next()){
                Object[]str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch(rs1.getInt(7)){
                       case 1:
                           str[3] = "Chính Quy";
                           break;
                       case 0:
                            str[3] = "Chương Trình Bạn Là Khách";
                           break;
                 }       
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangDanhSachHocSinhNoPhi(int classes_id,JTable table){
        try {
            Object [] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_has_cost.students_id from students_has_cost,classes_has_students where isdebt = 1 and students_has_cost.students_id=classes_has_students.students_id and classes_id = "+classes_id+" group by students_has_cost.students_id) or debt!=0");
            while(rs1.next()){
                Object[]str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch(rs1.getInt(7)){
                       case 1:
                           str[3] = "Chính Quy";
                           break;
                       case 0:
                            str[3] = "Chương Trình Bạn Là Khách";
                           break;
                 }       
                str[4] = rs1.getString(5); 
                str[5] = rs1.getString(6);
                str[6] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    public void BangHocPhiCuaHocSinh(int students_id,JTable table){
         try {
            Object [] nameColumn = {  "Tên", "Kì học", "Năm học", "Giá" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = "+students_id+" )");
            while(rs1.next()){
                if(rs1.getInt(5)!=0) continue;
                Object str[] = new Object[4];
                str[0] = rs1.getString(4);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(6);        
                if(((String)str[3]).charAt(0)=='-'){
                    str[3] = ((String)str[3]).substring(1);
                }       
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangLichSuDongTienCuaHocSinh(int students_id,JTable table){
         try {
            Object [] nameColumn = {"STT", "Nguoi Dong", "Nguoi Thu", "So Tien Dong", "Ngay Dong", "Hinh Thuc Dong"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from receipts");
            int count =1;
            while(rs1.next()){
                if(rs1.getInt(5)!=0) continue;
                Object str[] = new Object[6];
                str[0] = count;
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
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangThemHocPhiChoHocSinh(JTable table,int students_id){
        try {
            Object [] nameColumn = {  "Tên", "Kì học", "Năm học", "Giá","Đánh dấu" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from cost where id not in(select cost_id from students_has_cost where students_id = "+students_id+" )");
            while(rs1.next()){
                if(rs1.getInt(5)!=0) continue;
                Object str[] = new Object[5];
                str[0] = rs1.getString(4);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(6);        
                if(((String)str[3]).charAt(0)=='-'){
                    str[3] = ((String)str[3]).substring(1);
                }
                str[4] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DanhSachTrongMuon(int classes_id,JTable table){
         try {
            Object [] nameColumn = {"Mã số", "Họ và tên", "Ngày Sinh", "Số ngày"};
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("SELECT students.id,FullName,BrithDay ,count(students_id) from students, lateday where students.Id = Students_Id and students.Id in(select Students_Id from classes_has_students where Classes_Id = "+classes_id+") group by Students_Id");
            while(rs1.next()){
               
                Object str[] = new Object[4];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                str[3] = rs1.getString(4);         
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            table.setModel(model);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DanhSachNghiPhep(int classes_id,JTable table){
    }
    public DefaultTableModel BangDanhSachTaiKhoan(JTable table){
        try {
            Object [] nameColumn = {  "Mã Nhân Viên", "Tên Nhân Viên", "Tên Đăng Nhập", "Lựa Chọn" };
            ArrayList<Object []> data = new ArrayList<Object []>();
            rs1 = statement.executeQuery("select * from accounts");
            while(rs1.next()){
                 Object[]str = new Object[4];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                str[3] = false;
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            };
            table.setModel(model);
           }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
         return model;
        
    }
public ArrayList HocSinhA1(int ID){
    ArrayList infoStudent = new ArrayList();
    String str[] = new String[9];
        try {
            rs1 = statement.executeQuery("select students.id,fullname,brithday,PhoneNumber,Representative,levels_id,nameclass,isclient,faculties.name from students,classes,classes_has_students,faculties where students.id = students_id and classes.id=classes_id and students.id = "+ID+" and faculties.Id = students.Faculties_Id");
            while(rs1.next()){
                    str[0] = rs1.getString(1);
                    str[1] = rs1.getString(2);
                    str[2] = rs1.getString(3);
                    str[3] = rs1.getString(4);
                    str[4] = rs1.getString(5);
                   switch(rs1.getInt(6)){
                       case 1:
                           str[5] = "NẮNG MAI (SUNSHINE)";
                           break;
                       case 2:
                            str[5] = "TỔ ONG (BEEHIVE)";
                           break;
                       case 3:
                            str[5] = "TỔ KÉN (CHRYSALIS)";
                           break;
                       case 4:
                            str[5] = "MẪU GIÁO (KINDERGARTEN)";
                           break;
                   }
                   
                 
                   str[6] = rs1.getString(7);
                   switch(rs1.getInt(8)){
                       case 1:
                           str[7] = "Chính Quy";
                           break;
                       case 0:
                            str[7] = "Chương Trình Bạn Là Khách";
                           break;
                 }
                   str[8] = rs1.getString(9);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0;i<9;i++){
            infoStudent.add(str[i]);
            }
        
        return infoStudent;
}
public ArrayList LateDay(int ID){
    int count = 0;
    ArrayList infoStudent = new ArrayList();
    String str[] = new String[365];
        try {
            rs1 = statement.executeQuery("select LateDate from lateday where Students_Id = "+ID);
            if(rs1!= null)
            while(rs1.next()){
                    str[count] = rs1.getString(1);
                    count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0;i<count;i++){
            infoStudent.add(str[i]);
            }
        return infoStudent;
}
}