/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import edu.com.ThongTin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author nguyen van cuong
 */
public class DataTable {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement,statement2;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object [][] rowColumn;
    public DataTable(){
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
            statement2 = connect.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, null, "Đăng nhập không thành công !!! \n Xem lại user name hoặc "
                    + "password", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void BangDanhSachLop(JTable table,int idtrungtam){
        int id;
        try {
            Object [] nameColumn = { "Tên Lớp","Trung tâm", "Trình độ", "Học kì", "Giáo Viên", "Ngày bắt đầu", "Ngày kết thúc", "Số học sinh", "Tối đa", "Trạng thái","Đánh dấu"};
            ArrayList<Object []> data = new ArrayList<Object []>();
           // rs2 = statement.executeQuery("select * ,count(Students_id) from classes,classes_has_students where classes.id= Classes_Id group by Classes_Id");
            if(idtrungtam==5) rs1= statement.executeQuery("select * from classes order by Faculties_Id ");
            else rs1=statement.executeQuery("select * from classes where Faculties_Id= '"+idtrungtam+"' order by Faculties_Id ");
            while(rs1.next()){
                Object str[] = new Object[11];
                str[0] = rs1.getString(6);
                    id= rs1.getInt(1);
                    switch(rs1.getInt(4)){
                    case 1:
                           str[2] = "NẮNG MAI (SUNSHINE)";
                        break;
                    case 2:
                            str[2] = "TỔ ONG (BEEHIVE)";
                        break;
                    case 3:
                            str[2] = "TỔ KÉN (CHRYSALIS)";
                        break;
                    case 4:
                            str[2] = "MẪU GIÁO (KINDERGARTEN)";
                        break;
                }
                   switch(rs1.getInt(2))
                   {
                       case 1: str[1]="Koala House Bà Triệu";
                               break;
                       case 2: str[1]="Koala House Hoàng Ngân";
                               break;
                       case 3: str[1]="Koala House Phan Kế Bình";
                               break;
                       case 4: str[1]="Koala House Nguyễn Huy Tưởng";
                               break;
                   }
                   str[3] = rs1.getString(3);
                   str[4] = rs1.getString(7);
                   str[5] = rs1.getString(8);
                   str[6] = rs1.getString(9);
                   str[8] = rs1.getString(11);
                   switch(rs1.getInt(12)){
                    case 0:
                           str[9] = "Đang giảng dạy";
                        break;
                    case 1:
                            str[9] = "Đã kết thúc";
                        break;
                }
                 str[10]=false;
                 rs2= statement2.executeQuery("select count(Students_Id) from classes_has_students where Classes_Id = '" + id + "'");
                 while(rs2.next())
                 {
                 
                     str[7] = rs2.getString(1);
                 }
                 
                      
                data.add(str);
            }
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
                boolean[] canEdit = new boolean [] {
                false,false, false, false, false, false,false,false,false,false,true
                    };
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
                    }
            Class[] types = new Class [] {
                java.lang.Object.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
   
     public void BangDanhSachHSDongDuPhi(JTable table) {
        try {
            Object[] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where students.id not in(select students_id from students_has_cost where isdebt = 1 group by students_id) and debt = 0");
            while (rs1.next()) {
                Object[] str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch (rs1.getInt(7)) {
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
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BangDanhSachHSDongDuPhi(int classes_id, JTable table) {
        try {
            Object[] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where  debt=0 and students.id in(select Students_Id from classes_has_students where classes_id = " + classes_id + " and Students_Id not in(select students_has_cost.students_id from students_has_cost,classes_has_students where isdebt = 1 and students_has_cost.students_id=classes_has_students.students_id and classes_id = " + classes_id + " group by students_has_cost.students_id))");
            while (rs1.next()) {
                Object[] str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch (rs1.getInt(7)) {
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
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BangDanhSachHocSinhNoPhi(JTable table) {
        try {
            Object[] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_id from students_has_cost where isdebt = 1 group by students_id) or debt!=0");
            while (rs1.next()) {
                Object[] str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch (rs1.getInt(7)) {
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
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BangDanhSachHocSinhNoPhi(int classes_id, JTable table) {
        try {
            Object[] nameColumn = {"Mã Số HS", "Họ Tên", "Ngày Sinh", "Hình Thức Học", "SĐT", "Người Đại Diện", "Đánh Dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from students where students.id in(select students_has_cost.students_id from students_has_cost,classes_has_students where isdebt = 1 and students_has_cost.students_id=classes_has_students.students_id and classes_id = " + classes_id + " group by students_has_cost.students_id) or debt!=0");
            while (rs1.next()) {
                Object[] str = new Object[7];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                switch (rs1.getInt(7)) {
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
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BangHocPhiCuaHocSinh(int students_id, JTable table) {
        try {
            Object[] nameColumn = {"Tên", "Kì học", "Năm học", "Giá"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from cost where id in(select cost_id from students_has_cost where students_id = " + students_id + " )");
            while (rs1.next()) {
                if (rs1.getInt(5) != 0) {
                    continue;
                }
                Object str[] = new Object[4];
                str[0] = rs1.getString(4);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(6);
                if (((String) str[3]).charAt(0) == '-') {
                    str[3] = ((String) str[3]).substring(1);
                }
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BangLichSuDongTienCuaHocSinh(int students_id, JTable table) {
        try {
            Object[] nameColumn = {"STT", "Nguoi Dong", "Nguoi Thu", "So Tien Dong", "Ngay Dong", "Hinh Thuc Dong"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from receipts");
            int count = 1;
            while (rs1.next()) {
                if (rs1.getInt(5) != 0) {
                    continue;
                }
                Object str[] = new Object[6];
                str[0] = count;
                str[1] = rs1.getString(6);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(8);
                str[4] = rs1.getString(11);
                switch (rs1.getInt(12)) {
                    case 0:
                        str[5] = "Tiền mặt";
                        break;
                    case 1:
                        str[5] = "Chuyển khoản";
                        break;
                }
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BangThemHocPhiChoHocSinh(JTable table, int students_id) {
        try {
            Object[] nameColumn = {"Tên", "Kì học", "Năm học", "Giá", "Đánh dấu"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from cost where id not in(select cost_id from students_has_cost where students_id = " + students_id + " )");
            while (rs1.next()) {
                if (rs1.getInt(5) != 0) {
                    continue;
                }
                Object str[] = new Object[5];
                str[0] = rs1.getString(4);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(7);
                str[3] = rs1.getString(6);
                if (((String) str[3]).charAt(0) == '-') {
                    str[3] = ((String) str[3]).substring(1);
                }
                str[4] = false;
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DanhSachTrongMuon(int classes_id, JTable table) {
        try {
            Object[] nameColumn = {"Mã số", "Họ và tên", "Ngày Sinh", "Số ngày"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT students.id,FullName,BrithDay ,count(students_id) from students, lateday where students.Id = Students_Id and students.Id in(select Students_Id from classes_has_students where Classes_Id = " + classes_id + ") group by Students_Id");
            while (rs1.next()) {

                Object str[] = new Object[4];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                str[3] = rs1.getString(4);
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, true
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DanhSachNghiPhep(int classes_id, JTable table) {
    }

    public DefaultTableModel BangDanhSachTaiKhoan(JTable table) {
        try {
            Object[] nameColumn = {"Mã Nhân Viên", "Tên Nhân Viên", "Tên Đăng Nhập", "Lựa Chọn"};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("select * from accounts");
            while (rs1.next()) {
                Object[] str = new Object[4];
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(3);
                str[2] = rs1.getString(4);
                str[3] = false;
                data.add(str);
            }
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                };
                table.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

    public ArrayList HocSinhA1(int ID) {
        ArrayList infoStudent = new ArrayList();
        String str[] = new String[9];
        try {
            rs1 = statement.executeQuery("select students.id,fullname,brithday,PhoneNumber,Representative,levels_id,nameclass,isclient,faculties.name from students,classes,classes_has_students,faculties where students.id = students_id and classes.id=classes_id and students.id = " + ID + " and faculties.Id = students.Faculties_Id");
            while (rs1.next()) {
                str[0] = rs1.getString(1);
                str[1] = rs1.getString(2);
                str[2] = rs1.getString(3);
                str[3] = rs1.getString(4);
                str[4] = rs1.getString(5);
                switch (rs1.getInt(6)) {
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
                switch (rs1.getInt(8)) {
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
        for (int i = 0; i < 9; i++) {
            infoStudent.add(str[i]);
        }

        return infoStudent;
    }

    public ArrayList LateDay(int ID) {
        int count = 0;
        ArrayList infoStudent = new ArrayList();
        String str[] = new String[365];
        try {
            rs1 = statement.executeQuery("select LateDate from lateday where Students_Id = " + ID);
            if (rs1 != null) {
                while (rs1.next()) {
                    str[count] = rs1.getString(1);
                    count++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < count; i++) {
            infoStudent.add(str[i]);
        }
        return infoStudent;
    }
    //Tra ve danh sach lop de phuc vu cho viec chuyen lop cua hoc sinh

    public DefaultComboBoxModel DanhSachLop(String NameClasses) {
        try {
            Vector vector = new Vector();
            rs1 = statement.executeQuery("select NameClass from classes where IsActive = 1 and NameClass != " + "\"" + NameClasses + "\" and Faculties_Id= '" + ThongTin.trungtam + "'");
            while (rs1.next()) {
                vector.add(rs1.getString(1));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean ThemLop(String trungtam,String kyhoc,String tenkhoi,String tenlop,String giaovien,String startdate,String enddate,String sohs){
        Calendar now = Calendar.getInstance();
        int Year=now.get(Calendar.YEAR);  
        int id=0;
        int test=0;
        try{
            rs1=statement.executeQuery("select * from classes");
            rs2=statement2.executeQuery("select * from classes where NameClass ='" + tenlop + "' and Faculties_Id= '" + trungtam +"' ");
            while(rs2.next()){
                test=rs2.getInt(1);
}
            if(test==0)
            {
                while(rs1.next()){id=rs1.getInt(1);}
                id+=1;
                statement.executeUpdate("INSERT INTO classes  VALUES "
                        + "('" + id + "','" + trungtam + "','" + kyhoc + "','" + tenkhoi + "','" + Year + "','" + tenlop + "','" + giaovien + "',"
                        + "'" + startdate + "','" + enddate + "',null,'" + sohs + "',0)");
                System.out.println("INSERT INTO classes  VALUES "
                        + "('" + id + "','" + trungtam + "','" + kyhoc + "','" + tenkhoi + "','" + Year + "','" + tenlop + "','" + giaovien + "',"
                        + "'" + startdate + "','" + enddate + "',null,'" + sohs + "',0)");

                return true;
            }
            else
            {
                String message =String.format( "ten lop da co trong he thong");
                JOptionPane.showMessageDialog( null, message );
                return false;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            String message =String.format( "Bạn chưa điền đúng thông tin xin mời xem lại!");
                JOptionPane.showMessageDialog( null, message );
            
        }  
        return false;
    }
    public boolean SuaLop(String oldnameclass,int idtrungtam,String trungtam,String kyhoc,String tenkhoi,String tenlop,String giaovien,String startdate,String enddate,String sohs,String trangthai)
    {
        int id=0,test=0;
       try
         { 
            System.out.println("select * from classes where NameClass = '" + oldnameclass + "' and Faculties_Id= '" + idtrungtam + "' ");
            rs1=statement.executeQuery("select * from classes where NameClass = '" + oldnameclass + "'and Faculties_Id= '" + idtrungtam + "'");
            /*rs2=statement2.executeQuery("select * from classes where NameClass ='" + tenlop + "' and Faculties_Id= '" + trungtam +"' ");
            while(rs2.next()){
                test=rs2.getInt(1);
            }
                    */
            //if(test==0)
            //{
            while(rs1.next())
            {
            id=rs1.getInt(1);
            }
            String query = "update classes  set Faculties_Id = '" + trungtam + "' , Semesters='" + kyhoc + "' , Levels_Id = '" + tenkhoi + "', NameClass = '" + tenlop + "', TeacherClass='" + giaovien + "',StartDate='" + startdate + "',EndDate='" + enddate + "',MaxNumber='" + sohs + "' ,IsActive='" + trangthai + "' where Id='" + id + "'";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();

            
            return true;
            /*}
            else
            {
                String message =String.format( "ten lop da co trong he thong");
                JOptionPane.showMessageDialog( null, message );
                return false;
            
            }
                    */
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
            String message =String.format( "Bạn chưa điền đúng thông tin xin mời xem lại!");
                JOptionPane.showMessageDialog( null, message );
            
        }  
        return false;
    }        
    public boolean XoaLop(JTable tableName,int colunmcheck)
    {
        try
        {
            Vector vector= new Vector();
            Vector vector2=new Vector();
            DefaultTableModel tableModel;
            tableModel = (DefaultTableModel) tableName.getModel();
            int i=0,a=0;
            boolean xoahoackhong=true;
           
            for(a= tableName.getRowCount();a>0;a--)
                {
                    if(Boolean.parseBoolean(tableName.getValueAt(a-1,colunmcheck).toString())== true&&Integer.parseInt(tableName.getValueAt(a-1,7).toString())>0)
                    {
                       xoahoackhong=false;
                       break;
                    }
                }
            if(xoahoackhong)
            {
                    for(a= tableName.getRowCount();a>0;a--)
                    {
                        if(Boolean.parseBoolean(tableName.getValueAt(a-1,colunmcheck).toString())== true)
                        {
                            vector.add(tableName.getValueAt(a-1, 0).toString());
                            System.out.println(tableName.getValueAt(a-1, 1).toString());
                            vector2.add(tableName.getValueAt(a-1, 1).toString());
                        }
                    }
                    //vector.add("hoasung");
                    vector.add(null);
                    i=0;
                    int id=0;
                    String name;
                    String idtrungtam;
                    String query;
                    PreparedStatement pstmt;
                    while(vector.get(i) !=null)
                    {
                        idtrungtam=vector2.get(i).toString();
                        if(idtrungtam.equals("Koala House Bà Triệu")) idtrungtam="1";
                        else if (idtrungtam.equals("Koala House Hoàng Ngân")) idtrungtam="2";
                        else if (idtrungtam.equals("Koala House Phan Kế Bình")) idtrungtam="3";
                        else idtrungtam="4";
                        name=vector.get(i).toString();
                        //System.out.println("select * from classes where NameClass = '" + name + "' and Faculties_Id= '" + idtrungtam + "'");
                        rs1=statement.executeQuery("select * from classes where NameClass = '" + name + "' and Faculties_Id= '" + idtrungtam + "'");
                        
                        while(rs1.next())
                        {
                        id=rs1.getInt(1);
                        }

                        query="delete from classes_has_students where Classes_Id = '" + id + "' and Faculties_Id= '" + idtrungtam + "'";
                        pstmt = connect.prepareStatement(query);
                        pstmt.executeUpdate();
                        query = "delete from classes where `Id`='" + id + "' and Faculties_Id= '" + idtrungtam + "'";
                        pstmt = connect.prepareStatement(query);
                        pstmt.executeUpdate();


                         i++;  
                    }
                return true;
            }
            else
            {
                String message =String.format( "Trong các lớp chọn có lớp vẫn đang tồn tại hs, hoặc có hs đặt chỗ hay học sinh xin tạm nghỉ , bạn cần xóa hs trong lớp hoặc chuyển lớp cho các hs!");
                JOptionPane.showMessageDialog( null, message );
                return false;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
        
    }
    public int LayIdTenLop(String nameclass)
    {
        int id=0;
        try
        {
            rs1=statement.executeQuery("select * from classes where NameClass = '" + nameclass + "' and Faculties_Id= '" + ThongTin.trungtam + "'");
                while(rs1.next())
                {
                id=rs1.getInt(1);
                }
                return id;
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public int LayTenTrungTam(String nameadmin)
    {
        int id=0;
        try
        {
            rs1=statement.executeQuery("select * from accounts where UserName='"+nameadmin+"'");
            while(rs1.next())
            {
                id=rs1.getInt(2);
            }
            return id;
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
} 