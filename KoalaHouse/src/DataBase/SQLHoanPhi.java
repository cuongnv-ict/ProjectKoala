/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Venus
 */
public class SQLHoanPhi {
    
    private Connection connect;
    ResultSet rs1,rs2,rs3;
    Statement statement,statement2,statement3;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLHoanPhi() {
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
    public void BandDanhSachHoanPhi(JTable table)
    {
        try
        {
             
           
            int idstudent=-1;
            
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6),model.getColumnName(7),model.getColumnName(8),model.getColumnName(9)};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT b1.id,s1.FullName,c2.NameClass,s1.PhoneNumberFather,s1.PhoneNumberMother,SoTien,b1.StartDate,b1.EndDate,Note,b1.IsActive,b1.idStudent FROM refund b1,students  s1, classes_has_students c1, classes c2 where b1.idStudent=s1.Id and c1.Classes_Id=c2.Id and c1.Students_Id= s1.Id order by  s1.FullName");
            
            
            while(rs1.next()) 
            {
                    Object[] str = new Object[10];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(11);
                    str[1]= rs1.getString(2);
                    str[2]= rs1.getString(3);
                    str[3]= XuLy.setMoney(rs1.getString(4))+" "+"/"+" "+XuLy.setMoney(rs1.getString(5));
                    str[4]= XuLy.setMoney(rs1.getString(6));
                    str[5]=new XuLiXau().NgayThangNam(rs1.getString(7));
                    str[6]=new XuLiXau().NgayThangNam(rs1.getString(8));
                    str[7]=rs1.getString(9);
                    str[9]=false;
                    if(rs1.getInt(10)==1) str[8]="Chưa Hoàn Phí";
                    else str[8]="Đã Hoàn Phí";
                    data.add(str);
            }
            if(data.size()==0)
            {
                Object[] str = new Object[10];
                str[0]="";
                str[1]="";
                str[2]="";
                str[3]="";
                str[4]="";
                str[5]="";
                str[6]="";
                str[7]="";
                str[8]="";
                str[9]=false;
                data.add(str);
                Object[][] rowColumn = new Object[data.size()][];
                for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false,false, true
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
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false, false, false, false, false, false,false,true
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
    
    public void themHoanPhi(int idstudent,String datebd,String datekt,String ghichu,int tienxe)
    {
        try{
            int id=0;
            rs1=statement.executeQuery("SELECT id FROM refund ");
            while(rs1.next())
            {
                id= rs1.getInt(1);
            }
            System.out.println(id);
            id+=1;
            statement.executeUpdate("INSERT INTO refund  VALUES "
                        + "('" + id + "','" +idstudent+ "','"+datebd+"','"+datekt+"','"+ghichu+"',1,'" + Integer.toString(tienxe) + "')");
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();
        }
        
        catch(SQLException ex)
        {
            
            System.out.println("ban khong thanh cong tao them HoanPhi" + ex.getMessage());
        }
    } 
    public void suaHoanPhi(int oldIdStudent,int newIdStudent,int id,String datebd,String datekt,String ghichu,int tienxe)
    {
        try{
            String query = "update refund  set idStudent = '" +newIdStudent+ "' , StartDate='" +datebd + "' , EndDate = '" + datekt + "', SoTien = '" + Integer.toString(tienxe) + "', Note='"+ghichu+"' where idStudent= '"+oldIdStudent+"' and id='"+id+"'";
            System.out.println(query);
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
        rs1 = statement.executeQuery("Select  Students_Id From students s, classes c, classes_has_students h where s.FullName= '"+ten+"' and c.NameClass= '"+nameclasses+"'"
                + " and  s.Id = h.Students_Id and h.Classes_Id = c.Id ");
    
        while(rs1.next())
        {
            id=rs1.getInt(1);
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
    public boolean xoaHoanPhi(JTable table, int columncheck,ArrayList<Integer> idxebus)
    {
        try
        {
            Vector vector= new Vector();
            Vector vector2=new Vector();
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
                            vector.add(table.getValueAt(a-1, 1).toString());
                         
                            vector2.add(table.getValueAt(a-1, 2).toString());
                            vector3.add(idxebus.get(a-1));
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
                        
                    rs1 = statement.executeQuery("Select  Students_Id From students s, classes c, classes_has_students h where s.FullName= '"+vector.get(i).toString()+"' and c.NameClass= '"+vector2.get(i).toString()+"'"
                    + " and  s.Id = h.Students_Id and h.Classes_Id = c.Id ");
                        while(rs1.next())
                        {
                        id=rs1.getInt(1);
                        }

                        query="delete from refund  where idStudent = '"+id+"' and id = '"+vector3.get(i)+"' ";
                        System.out.println(query);
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
        
    public int  getIsActiveStudent(String ten,String nameclasses) 
    {
        int isactive = 0;
        int id=0;
        try 
        {
        rs1 = statement.executeQuery("Select  Students_Id From students s, classes c, classes_has_students h where s.FullName= '"+ten+"' and c.NameClass= '"+nameclasses+"'"
                + " and  s.Id = h.Students_Id and h.Classes_Id = c.Id ");
        while(rs1.next())
        {
            id=rs1.getInt(1);
        }
        rs1 = statement.executeQuery("Select IsActive From refund  where idStudents = '"+id+"' and isActive = 1 ");
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
    public void BandDanhSachHoanPhi_TimKiem(JTable table,String ten)
    {
        try
        {
             
           
            int idstudent=-1;
            
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6),model.getColumnName(7),model.getColumnName(8),model.getColumnName(9)};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT b1.id,s1.FullName,c2.NameClass,s1.PhoneNumberFather,s1.PhoneNumberMother,SoTien,b1.StartDate,b1.EndDate,Note,b1.IsActive,b1.idStudent FROM refund b1,students  s1, classes_has_students c1, classes c2 where b1.idStudent=s1.Id and c1.Classes_Id=c2.Id and c1.Students_Id= s1.Id and s1.FullName like '%"+ten+"%' order by  s1.FullName");
            
            
            while(rs1.next()) 
            {
                    Object[] str = new Object[10];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(11);
                    str[1]= rs1.getString(2);
                    str[2]= rs1.getString(3);
                    str[3]= rs1.getString(4)+"/"+rs1.getString(5);
                    str[4]= XuLy.setMoney(rs1.getString(6));
                    str[5]=new XuLiXau().NgayThangNam(rs1.getString(7));
                    str[6]=new XuLiXau().NgayThangNam(rs1.getString(8));
                    str[7]=rs1.getString(9);
                    str[9]=false;
                    if(rs1.getInt(10)==1) str[8]="Chưa Hoàn Phí";
                    else str[8]="Đã Hoàn Phí";
                    data.add(str);
            }
            if(data.size()==0)
            {
                Object[] str = new Object[10];
                str[0]="";
                str[1]="";
                str[2]="";
                str[3]="";
                str[4]="";
                str[5]="";
                str[6]="";
                str[7]="";
                str[8]="";
                str[9]=false;
                data.add(str);
                Object[][] rowColumn = new Object[data.size()][];
                for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false, false, false,false, true
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
            Object[][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false, false, false, false, false, false,false,true
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