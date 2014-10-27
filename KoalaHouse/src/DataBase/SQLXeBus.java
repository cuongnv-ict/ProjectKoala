/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import edu.com.DataOfTableBus;
import edu.com.ThongTin;
import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Venus
 */
public class SQLXeBus 
{

    private Connection connect;
    ResultSet rs1,rs2,rs3;
    Statement statement,statement2,statement3;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLXeBus() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
            statement2= connect.createStatement();
            statement3= connect.createStatement();
        } catch (SQLException ex) {
            //Loi truy nhap db
        }
    }
    public void BandDanhSachXeBus(JTable table)
    {
        try
        {
             
           
            int idstudent=-1;
           
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6),model.getColumnName(7),model.getColumnName(8),model.getColumnName(9),model.getColumnName(10),model.getColumnName(11)};
            ArrayList<DataOfTableBus> data = new ArrayList<DataOfTableBus>();
            rs1 = statement.executeQuery("SELECT b1.idBusList,idStudents,LuotDi,GhiChu,TienXe,DiaChi,StartDate,EndDate,b1.IsActive,FullName,PhoneNumberFather FROM buslist b1,students  s1 where b1.idStudents=s1.Id order by  s1.FullName");
            
            
            while(rs1.next()) 
            {
                DataOfTableBus onerow= new DataOfTableBus();
                /*
                    Object[] str = new Object[12];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(2);
                    str[1]= rs1.getString(10);
                    str[4]= rs1.getString(11);
                */  
                    onerow.setId(rs1.getInt(1));
                    onerow.setIdStudents(rs1.getInt(2));
                    onerow.setFullname( rs1.getString(10));
                    String namegia="";
                    rs2 = statement2.executeQuery("Select NameClass,semesters From classes c1, classes_has_students c2 where c1.Id=c2.Classes_Id And c2.Students_Id= '"+onerow.getIdStudents()+"'");
                   
                    while(rs2.next())
                    {
                        onerow.setClasses(rs2.getString(1));
                        namegia=rs2.getString(1);
                    }
                    onerow.setDiaChi(rs1.getString(6));
                    onerow.setDienThoai(rs1.getString(11));
                    onerow.setLuotDi(rs1.getInt(3));
                    onerow.setTienXe(XuLy.setMoney(rs1.getString(5)));
                    onerow.setDanhDau(false);
                    onerow.setGhiChu(rs1.getString(4));
                    onerow.setNgayBatDau(new XuLiXau().NgayThangNam(rs1.getString(7)));
                    onerow.setNgayKetThuc(new XuLiXau().NgayThangNam(rs1.getString(8)));
                     if(rs1.getInt(9)==1) onerow.setTinhTrang("Chưa Thanh Toán");
                    else onerow.setTinhTrang("Đã Thanh Toán");
                    String temp[]=onerow.getFullname().split(" ");
                    namegia+= temp[temp.length-1]+onerow.getFullname();
                    onerow.setNameGia(namegia);
                    data.add(onerow);
                    /*
                    str[3]=rs1.getString(6);
                    str[5]=rs1.getString(3);
                    str[6]=XuLy.setMoney(rs1.getString(5));
                    str[7]=new XuLiXau().NgayThangNam(rs1.getString(7));
                    str[8]=new XuLiXau().NgayThangNam(rs1.getString(8));
                    str[9]=rs1.getString(4);
                    str[11]=false;
                    if(rs1.getInt(9)==1) str[10]="Chưa Thanh Toán";
                    else str[10]="Đã Thanh Toán";
                    data.add(str);
                    */
            }
            
            if(data.size()==0)
            {
                
                Object[][] rowColumn = new Object[1][12];
                for (int i = 0; i < 1; i++) {
                rowColumn[i][0] = "";
                rowColumn[i][1] = "";
                rowColumn[i][2] = "";
                rowColumn[i][3] = "";
                rowColumn[i][4] = "";
                rowColumn[i][5] = "";
                rowColumn[i][6] = "";
                rowColumn[i][7] = "";
                rowColumn[i][8] = "";
                rowColumn[i][9] = "";
                rowColumn[i][10] = "";
                rowColumn[i][11] = false;
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            else
            {
                Collections.sort(data,new Comparator<DataOfTableBus>() {

                    @Override
                    public int compare(DataOfTableBus dt1, DataOfTableBus dt2) {
                        return (dt1.getNameGia().compareTo(dt2.getNameGia()));
                    }
                });
            Object[][] rowColumn = new Object[data.size()][12];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i][0] = data.get(i).getId();
                rowColumn[i][1] = data.get(i).getFullname();
                rowColumn[i][2] = data.get(i).getClasses();
                rowColumn[i][3] = data.get(i).getDiaChi();
                rowColumn[i][4] = data.get(i).getDienThoai();
                rowColumn[i][5] = data.get(i).getLuotDi();
                rowColumn[i][6] = data.get(i).getTienXe();
                rowColumn[i][7] = data.get(i).getNgayBatDau();
                rowColumn[i][8] = data.get(i).getNgayKetThuc();
                rowColumn[i][9] = data.get(i).getGhiChu();
                rowColumn[i][10] = data.get(i).getTinhTrang();
                rowColumn[i][11] = data.get(i).getDanhDau();
                
                
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false,true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            //table.setModel(model);
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();

        }
        catch(SQLException exception)
        {
            
        }
    }
    
    
    public void themxebus(int idstudent,int luotdi,String ghichu,int tienxe,String diachi,String datebd,String datekt)
    {
        try{
            int id=0;
            rs1=statement.executeQuery("SELECT idBusList FROM buslist ");
            while(rs1.next())
            {
                id= rs1.getInt(1);
            }
            id+=1;
         
            statement.executeUpdate("INSERT INTO buslist  VALUES "
                        + "('" + id + "','" +idstudent+ "','" + Integer.toString(luotdi) + "','" + ghichu + "','" + Integer.toString(tienxe) + "','" + diachi + "',1,'"+datebd+"','"+datekt+"')");
            
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();
        }   
        catch(SQLException ex)
        {
            System.out.println("ban khong thanh cong tao them du lieu xebus");
        }
    }
    public void suaxebus(int oldIdStudent,int newIdStudent,int luotDi, String ghiChu,int tienXe,String diaChi,String datebd,String datekt,int idxebus)
    {
        try{
            String query = "update buslist  set idStudents = '" +newIdStudent+ "' , LuotDi='" + Integer.toString(luotDi) + "' , GhiChu = '" + ghiChu + "', TienXe = '" + Integer.toString(tienXe) + "', DiaChi='" + diaChi + "',StartDate='"+datebd+"',EndDate='"+datekt+"' where idStudents= '"+oldIdStudent+"' and idBusList='"+idxebus+"'";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate(); 
            
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();
        }
        catch(SQLException ex)
        {
        }
    }
    public int getIdStudent(String ten,String nameclasses) 
    {
        int id=0;
        try 
        {
        rs1 = statement.executeQuery("Select  Students_Id, FullName From students s, classes c, classes_has_students h where s.FullName= '"+ten+"' and c.NameClass= '"+nameclasses+"'"
                + " and  s.Id = h.Students_Id and h.Classes_Id = c.Id ");
        while(rs1.next())
        {
            if(ten.equals(rs1.getString(2)))
            {
            id=rs1.getInt(1);
            }
        }
        
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();
            return id;
       
        }
        catch(SQLException ex)
        {
            
        }
        return id;
    }
    public boolean xoaxebus(JTable table, int columncheck,ArrayList<Integer> idxebus)
    {
        try
        {
            Vector vector3= new Vector();
            DefaultTableModel tableModel;
            tableModel = (DefaultTableModel) table.getModel();
            int i=0,a=0;
            boolean xoahoackhong=true;
           
            
            if(xoahoackhong)
            {
                    for(a= table.getRowCount();a>0;a--)
                    {
                        if(Boolean.parseBoolean(table.getValueAt(a-1,columncheck).toString())== true)
                        {
                            vector3.add(idxebus.get(a-1));
                        }
                    }
                    //vector.add("hoasung");
                    vector3.add(null);
                    i=0;
                    int id=0;
                    String name;
                    String idtrungtam;
                    String query;
                    PreparedStatement pstmt;
                    while(vector3.get(i) !=null)
                    {
                        
                        query="delete from buslist  where  idBusList = '"+vector3.get(i)+"' ";
                        pstmt = connect.prepareStatement(query);
                        pstmt.executeUpdate();
                       // query = "delete from classes where `Id`='" + id + "' and Faculties_Id= '" + idtrungtam + "'";
                        //pstmt = connect.prepareStatement(query);
                        //pstmt.executeUpdate();


                         i++;  
                    }
                    
                statement.close();
                statement2.close();
                statement3.close();
                connect.close();
                return true;
            }
            else
            {
                String message =String.format( "Trong các lớp chọn có lớp vẫn đang tồn tại hs, hoặc có hs đặt chỗ hay học sinh xin tạm nghỉ , bạn cần xóa hs trong lớp hoặc chuyển lớp cho các hs!");
                JOptionPane.showMessageDialog( null, message );
                
                statement.close();
                statement2.close();
                statement3.close();
                connect.close();
                return false;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
        
    }
    public void BandDanhSachXeBus_timkiem(JTable table,String ten)
    {
        try
        {
             
           
            int idstudent=-1;
            
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6),model.getColumnName(7),model.getColumnName(8),model.getColumnName(9),model.getColumnName(10),model.getColumnName(11)};
            ArrayList<DataOfTableBus> data = new ArrayList<DataOfTableBus>();
            rs1 = statement.executeQuery("SELECT b1.idBusList,idStudents,LuotDi,GhiChu,TienXe,DiaChi,StartDate,EndDate,b1.IsActive,FullName,PhoneNumberFather FROM buslist b1,students  s1 where b1.idStudents=s1.Id and s1.FullName like '%"+ten+"%' order by  s1.FullName");
            
            
            while(rs1.next()) 
            {   DataOfTableBus onerow= new DataOfTableBus();
                /*
                    Object[] str = new Object[12];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(2);
                    str[1]= rs1.getString(10);
                    str[4]= rs1.getString(11);
                */  
                    onerow.setId(rs1.getInt(1));
                    onerow.setIdStudents(rs1.getInt(2));
                    onerow.setFullname( rs1.getString(10));
                    String namegia="";
                    rs2 = statement2.executeQuery("Select NameClass,semesters From classes c1, classes_has_students c2 where c1.Id=c2.Classes_Id And c2.Students_Id= '"+onerow.getIdStudents()+"'");
                   
                    while(rs2.next())
                    {
                        onerow.setClasses(rs2.getString(1));
                        namegia=rs2.getString(1);
                    }
                    onerow.setDiaChi(rs1.getString(6));
                    onerow.setDienThoai(rs1.getString(11));
                    onerow.setLuotDi(rs1.getInt(3));
                    onerow.setTienXe(XuLy.setMoney(rs1.getString(5)));
                    onerow.setDanhDau(false);
                    onerow.setGhiChu(rs1.getString(4));
                    onerow.setNgayBatDau(new XuLiXau().NgayThangNam(rs1.getString(7)));
                    onerow.setNgayKetThuc(new XuLiXau().NgayThangNam(rs1.getString(8)));
                     if(rs1.getInt(9)==1) onerow.setTinhTrang("Chưa Thanh Toán");
                    else onerow.setTinhTrang("Đã Thanh Toán");
                    String temp[]=onerow.getFullname().split(" ");
                    namegia+= temp[temp.length-1]+onerow.getFullname();
                    onerow.setNameGia(namegia);
                    data.add(onerow);
            }
           if(data.size()==0)
            {
                
                Object[][] rowColumn = new Object[1][12];
                for (int i = 0; i < 1; i++) {
                rowColumn[i][0] = "";
                rowColumn[i][1] = "";
                rowColumn[i][2] = "";
                rowColumn[i][3] = "";
                rowColumn[i][4] = "";
                rowColumn[i][5] = "";
                rowColumn[i][6] = "";
                rowColumn[i][7] = "";
                rowColumn[i][8] = "";
                rowColumn[i][9] = "";
                rowColumn[i][10] = "";
                rowColumn[i][11] = false;
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            else
            {
                Collections.sort(data,new Comparator<DataOfTableBus>() {

                    @Override
                    public int compare(DataOfTableBus dt1, DataOfTableBus dt2) {
                        return (dt1.getNameGia().compareTo(dt2.getNameGia()));
                    }
                });
            Object[][] rowColumn = new Object[data.size()][12];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i][0] = data.get(i).getId();
                rowColumn[i][1] = data.get(i).getFullname();
                rowColumn[i][2] = data.get(i).getClasses();
                rowColumn[i][3] = data.get(i).getDiaChi();
                rowColumn[i][4] = data.get(i).getDienThoai();
                rowColumn[i][5] = data.get(i).getLuotDi();
                rowColumn[i][6] = data.get(i).getTienXe();
                rowColumn[i][7] = data.get(i).getNgayBatDau();
                rowColumn[i][8] = data.get(i).getNgayKetThuc();
                rowColumn[i][9] = data.get(i).getGhiChu();
                rowColumn[i][10] = data.get(i).getTinhTrang();
                rowColumn[i][11] = data.get(i).getDanhDau();
                
                
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false,true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            //table.setModel(model);
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();

        }
        catch(SQLException exception)
        {
            
        }
    }
    
    public int  getIsActiveStudent(String ten,String nameclasses) 
    {
        int isactive = 0;
        int id=0;
        try 
        {
        rs1 = statement.executeQuery("Select  Students_Id , FullName From students s, classes c, classes_has_students h where s.FullName= '"+ten+"' and c.NameClass= '"+nameclasses+"'"
                + " and  s.Id = h.Students_Id and h.Classes_Id = c.Id ");
        while(rs1.next())
        {
            if(ten.equals(rs1.getString(2)))
            {
            id=rs1.getInt(1);
            }
        }
        rs1 = statement.executeQuery("Select IsActive From buslist where idStudents = '"+id+"' and isActive = 1 ");
        while(rs1.next())
        {
            isactive=rs1.getInt(1);
            
        }
        
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();
        return isactive;
        }
        catch(SQLException ex)
        {
            
        }
        return isactive;
    }
    public void BandDanhSachXeBus_TheoNam(JTable table,String year,String yearstart,String yearend)
    {
        try
        {
             
            String inputyear[]=year.split("-");
            int idstudent=-1;
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6),model.getColumnName(7),model.getColumnName(8),model.getColumnName(9),model.getColumnName(10),model.getColumnName(11)};
            ArrayList<DataOfTableBus> data = new ArrayList<DataOfTableBus>();
            if(yearstart!=null)
            {  
            rs1 = statement.executeQuery("SELECT b1.idBusList,idStudents,LuotDi,GhiChu,TienXe,DiaChi,StartDate,EndDate,b1.IsActive,FullName,PhoneNumberFather FROM buslist b1,students  s1 where b1.idStudents=s1.Id and ( b1.StartDate like '%"+inputyear[0]+"%' or b1.EndDate like '%"+inputyear[1]+"%') order by  s1.FullName");
            String querry = "SELECT b1.idBusList,idStudents,LuotDi,GhiChu,TienXe,DiaChi,StartDate,EndDate,b1.IsActive,FullName,PhoneNumberFather FROM buslist b1,students  s1 where b1.idStudents=s1.Id and b1.StartDate like '%"+year+"%' or b1.EndDate like '%"+year+"%' order by  s1.FullName" ;
              
            while(rs1.next()) 
            {
                if(XuLy.sosanhngaythang(yearstart,rs1.getString(7))&&XuLy.sosanhngaythang(rs1.getString(8), yearend))
                {
                     DataOfTableBus onerow= new DataOfTableBus();
                /*
                    Object[] str = new Object[12];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(2);
                    str[1]= rs1.getString(10);
                    str[4]= rs1.getString(11);
                */  
                    onerow.setId(rs1.getInt(1));
                    onerow.setIdStudents(rs1.getInt(2));
                    onerow.setFullname( rs1.getString(10));
                    String namegia="";
                    rs2 = statement2.executeQuery("Select NameClass,semesters From classes c1, classes_has_students c2 where c1.Id=c2.Classes_Id And c2.Students_Id= '"+onerow.getIdStudents()+"'");
                   
                    while(rs2.next())
                    {
                        onerow.setClasses(rs2.getString(1));
                        namegia=rs2.getString(1);
                    }
                    onerow.setDiaChi(rs1.getString(6));
                    onerow.setDienThoai(rs1.getString(11));
                    onerow.setLuotDi(rs1.getInt(3));
                    onerow.setTienXe(XuLy.setMoney(rs1.getString(5)));
                    onerow.setDanhDau(false);
                    onerow.setGhiChu(rs1.getString(4));
                    onerow.setNgayBatDau(new XuLiXau().NgayThangNam(rs1.getString(7)));
                    onerow.setNgayKetThuc(new XuLiXau().NgayThangNam(rs1.getString(8)));
                     if(rs1.getInt(9)==1) onerow.setTinhTrang("Chưa Thanh Toán");
                    else onerow.setTinhTrang("Đã Thanh Toán");
                    String temp[]=onerow.getFullname().split(" ");
                    namegia+= temp[temp.length-1]+onerow.getFullname();
                    onerow.setNameGia(namegia);
                    data.add(onerow);
                }
                }
            }
           if(data.size()==0)
            {
                
                Object[][] rowColumn = new Object[1][12];
                for (int i = 0; i < 1; i++) {
                rowColumn[i][0] = "";
                rowColumn[i][1] = "";
                rowColumn[i][2] = "";
                rowColumn[i][3] = "";
                rowColumn[i][4] = "";
                rowColumn[i][5] = "";
                rowColumn[i][6] = "";
                rowColumn[i][7] = "";
                rowColumn[i][8] = "";
                rowColumn[i][9] = "";
                rowColumn[i][10] = "";
                rowColumn[i][11] = false;
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            else
            {
                Collections.sort(data,new Comparator<DataOfTableBus>() {

                    @Override
                    public int compare(DataOfTableBus dt1, DataOfTableBus dt2) {
                        return (dt1.getNameGia().compareTo(dt2.getNameGia()));
                    }
                });
            Object[][] rowColumn = new Object[data.size()][12];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i][0] = data.get(i).getId();
                rowColumn[i][1] = data.get(i).getFullname();
                rowColumn[i][2] = data.get(i).getClasses();
                rowColumn[i][3] = data.get(i).getDiaChi();
                rowColumn[i][4] = data.get(i).getDienThoai();
                rowColumn[i][5] = data.get(i).getLuotDi();
                rowColumn[i][6] = data.get(i).getTienXe();
                rowColumn[i][7] = data.get(i).getNgayBatDau();
                rowColumn[i][8] = data.get(i).getNgayKetThuc();
                rowColumn[i][9] = data.get(i).getGhiChu();
                rowColumn[i][10] = data.get(i).getTinhTrang();
                rowColumn[i][11] = data.get(i).getDanhDau();
                
                
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false,true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            //table.setModel(model);
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();

        }
        catch(SQLException exception)
        {
            
        }
    }
    public void BandDanhSachXeBus_TheoNamVaTen(JTable table,String year,String name,String yearstart,String yearend)
    {
        try
        {
             
           
            int idstudent=-1;
            String inputyear[]=year.split("-");
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6),model.getColumnName(7),model.getColumnName(8),model.getColumnName(9),model.getColumnName(10),model.getColumnName(11)};
            ArrayList<DataOfTableBus> data = new ArrayList<DataOfTableBus>();
            rs1 = statement.executeQuery("SELECT b1.idBusList,idStudents,LuotDi,GhiChu,TienXe,DiaChi,StartDate,EndDate,b1.IsActive,FullName,PhoneNumberFather FROM buslist b1,students  s1 where b1.idStudents=s1.Id and (b1.StartDate like '%"+inputyear[0]+"%' or b1.EndDate like '%"+inputyear[1]+"%') and s1.FullName like '%"+name+"%'  order by  s1.FullName");
            
            
            while(rs1.next()) 
            {
                if(XuLy.sosanhngaythang(yearstart,rs1.getString(7) )&&XuLy.sosanhngaythang(rs1.getString(8), yearend))
                {
                     DataOfTableBus onerow= new DataOfTableBus();
                /*
                    Object[] str = new Object[12];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(2);
                    str[1]= rs1.getString(10);
                    str[4]= rs1.getString(11);
                */  
                    onerow.setId(rs1.getInt(1));
                    onerow.setIdStudents(rs1.getInt(2));
                    onerow.setFullname( rs1.getString(10));
                    String namegia="";
                    rs2 = statement2.executeQuery("Select NameClass,semesters From classes c1, classes_has_students c2 where c1.Id=c2.Classes_Id And c2.Students_Id= '"+onerow.getIdStudents()+"'");
                   
                    while(rs2.next())
                    {
                        onerow.setClasses(rs2.getString(1));
                        namegia=rs2.getString(1);
                    }
                    onerow.setDiaChi(rs1.getString(6));
                    onerow.setDienThoai(rs1.getString(11));
                    onerow.setLuotDi(rs1.getInt(3));
                    onerow.setTienXe(XuLy.setMoney(rs1.getString(5)));
                    onerow.setDanhDau(false);
                    onerow.setGhiChu(rs1.getString(4));
                    onerow.setNgayBatDau(new XuLiXau().NgayThangNam(rs1.getString(7)));
                    onerow.setNgayKetThuc(new XuLiXau().NgayThangNam(rs1.getString(8)));
                     if(rs1.getInt(9)==1) onerow.setTinhTrang("Chưa Thanh Toán");
                    else onerow.setTinhTrang("Đã Thanh Toán");
                    String temp[]=onerow.getFullname().split(" ");
                    namegia+= temp[temp.length-1]+onerow.getFullname();
                    onerow.setNameGia(namegia);
                    data.add(onerow);
                }
            }
           if(data.size()==0)
            {
                
                Object[][] rowColumn = new Object[1][12];
                for (int i = 0; i < 1; i++) {
                rowColumn[i][0] = "";
                rowColumn[i][1] = "";
                rowColumn[i][2] = "";
                rowColumn[i][3] = "";
                rowColumn[i][4] = "";
                rowColumn[i][5] = "";
                rowColumn[i][6] = "";
                rowColumn[i][7] = "";
                rowColumn[i][8] = "";
                rowColumn[i][9] = "";
                rowColumn[i][10] = "";
                rowColumn[i][11] = false;
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            else
            {
                Collections.sort(data,new Comparator<DataOfTableBus>() {

                    @Override
                    public int compare(DataOfTableBus dt1, DataOfTableBus dt2) {
                        return (dt1.getNameGia().compareTo(dt2.getNameGia()));
                    }
                });
            Object[][] rowColumn = new Object[data.size()][12];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i][0] = data.get(i).getId();
                rowColumn[i][1] = data.get(i).getFullname();
                rowColumn[i][2] = data.get(i).getClasses();
                rowColumn[i][3] = data.get(i).getDiaChi();
                rowColumn[i][4] = data.get(i).getDienThoai();
                rowColumn[i][5] = data.get(i).getLuotDi();
                rowColumn[i][6] = data.get(i).getTienXe();
                rowColumn[i][7] = data.get(i).getNgayBatDau();
                rowColumn[i][8] = data.get(i).getNgayKetThuc();
                rowColumn[i][9] = data.get(i).getGhiChu();
                rowColumn[i][10] = data.get(i).getTinhTrang();
                rowColumn[i][11] = data.get(i).getDanhDau();
                
                
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false, false, false,false,true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                table.setModel(model);
            }
            }
            //table.setModel(model);
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();

        }
        catch(SQLException exception)
        {
            
        }
    }
}
