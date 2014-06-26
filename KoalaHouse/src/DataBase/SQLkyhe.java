/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

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
public class SQLkyhe {
    private Connection connect;
    ResultSet rs1,rs2,rs3;
    Statement statement,statement2,statement3;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLkyhe() {
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
    public void BangDanhSachDanKyTuanHe(JTable table)
    {
        try
        {
            model= (DefaultTableModel) table.getModel();
            
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2)};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs3= statement3.executeQuery("SELECT tuanhoc,startDay,endDay FROM summerweek");
            System.out.println("SELECT tuanhoc,startDay,endDay FROM summerweek");
            while(rs3.next())
            {
                Object[] str = new Object[3];
                str[0]=rs3.getInt(1);
                str[1]=new XuLiXau().NgayThangNam(rs3.getString(2));
                
                str[2]=new XuLiXau().NgayThangNam(rs3.getString(3));
                System.out.println(str[0].toString()+str[1].toString()+str[2].toString());
                data.add(str);
            }
            
            if(data.size()==0) 
            {
                rowColumn = new Object[data.size()][];
                model = new DefaultTableModel( nameColumn,3); 
                table.setModel(model);
            }
            else
            {
                 Object[][] rowColumn = new Object[data.size()][];
              for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
            
               table.setModel(model);
           
            }
            }
           
            
            statement.close();
            statement2.close();
            statement3.close();
            connect.close();
        }
        catch(SQLException ex)
        {
            System.out.println("gia tri bi sai truy van");
        }
    }
    public void UpdateDk(String ngaybd1,String ngaykt1,String ngaybd2, String ngaykt2, String ngaybd3, String ngaykt3,
                            String ngaybd4,String ngaykt4,String ngaybd5,String ngaykt5,String ngaybd6,String ngaykt6,
                            String ngaybd7,String ngaykt7, String ngaybd8,String ngaykt8)
    {
        try
        {
            Object [][] ngaythang= new Object[2][8];
            ngaythang[0][0]= ngaybd1;
            ngaythang[1][0]= ngaykt1;
            ngaythang[0][1]= ngaybd2;
            ngaythang[1][1]= ngaykt2;
            ngaythang[0][2]= ngaybd3;
            ngaythang[1][2]= ngaykt3;
            ngaythang[0][3]= ngaybd4;
            ngaythang[1][3]= ngaykt4;
            ngaythang[0][4]= ngaybd5;
            ngaythang[1][4]= ngaykt5;
            ngaythang[0][5]= ngaybd6;
            ngaythang[1][5]= ngaykt6;
            ngaythang[0][6]= ngaybd7;
            ngaythang[1][6]= ngaykt7;
            ngaythang[0][7]= ngaybd8;
            ngaythang[1][7]= ngaykt8;
            for(int i=1;i<9;i++)
            {
            String query = "update summerweek  set startDay = '" +ngaythang[0][i-1]+ "',endDay = '"+ngaythang[1][i-1]+"'  where tuanHoc= '"+i+"'";
            System.out.println(query);
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate(); 
            }
        }
        catch(SQLException ex)
        {
            
        }
        
    }
            
    public void BandDanhSachDangKyHocHe(JTable table)
    {
        try
        {
             
           
            int idstudent=-1;
            
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5)};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT id,idStudents,tuanhoc,soTuanHoc FROM learnsummer");

            while(rs1.next()) 
            {       
                    
                    Object[] str = new Object[6];
                    str[0]=rs1.getInt(1);
                    idstudent=rs1.getInt(2);
                    
                    rs2 = statement2.executeQuery("SELECT FullName,PhoneNumberFather FROM students where id ='"+ idstudent+"' ");
            
                    while(rs2.next())
                    {
                        str[1]= rs2.getString(1);
                        //str[4]= rs2.getString(2);
                    }
                    
                    rs2 = statement2.executeQuery("Select NameClass,semesters From classes c1, classes_has_students c2 where c1.Id=c2.Classes_Id And c2.Students_Id= '"+idstudent+"'");
                    
            
                    while(rs2.next())
                    {
                        str[2]= rs2.getString(1);
                    }
                    //str[2]=rs1.getString(2);
                    str[3]=rs1.getString(3);
                    str[4]=rs1.getString(4);
                    //str[7]=new XuLiXau().NgayThangNam(rs1.getString(7));
                    //str[8]=new XuLiXau().NgayThangNam(rs1.getString(8));
                    //str[9]=rs1.getString(4);
                    str[5]=false;
                    data.add(str);
            }     
            if(data.size()==0) 
            {
                Object[] str = new Object[6];
                    str[0]="";
                    str[1]="";
                    str[2]="";
                    str[3]="";
                    str[4]="";
                    str[5]=false;
                    data.add(str);
               Object[][] rowColumn = new Object[1][];
                for (int i = 0; i < 1; i++) {
                    
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class,java.lang.String.class, java.lang.String.class,java.lang.String.class,java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false,true
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
                        java.lang.Integer.class, java.lang.String.class,java.lang.String.class, java.lang.String.class,java.lang.String.class,java.lang.Boolean.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false,true
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
    
    public void themDkHocHe(int idstudent,String tuanhoc,int sotuanhoc)
    {
        try{
            int id=0;
            rs1=statement.executeQuery("SELECT id FROM learnsummer ");
            while(rs1.next())
            {
                id= rs1.getInt(1);
            }
            id+=1;
            String sb="duc son dep trai";
            statement.executeUpdate("INSERT INTO learnsummer  VALUES "
                        + "('" + id + "','" +idstudent+ "','"+tuanhoc +"','"+sotuanhoc+"','"+sb+"',1)");
        }   
        catch(SQLException ex)
        {
            System.out.println("ban khong thanh cong tao them du lieu xebus");
        }
    }
    
    public void suadkhocbus(int oldIdStudent,int newIdStudent,String tuanhoc,int sotuanhoc)
    {
        try{
            String query = "update learnsummer  set idStudents = '" +newIdStudent+ "' , tuanhoc = '"+tuanhoc+"', soTuanHoc= '"+sotuanhoc+"'  where idStudents= '"+oldIdStudent+"'";
            System.out.println(query);
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate(); 
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
        return id;
        }
        catch(SQLException ex)
        {
            
        }
        return id;
    }
    public boolean xoaDkHe(JTable table, int columncheck)
    {
        try
        {
            Vector vector= new Vector();
            Vector vector2=new Vector();
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
                        System.out.println("Select  Students_Id From students s, classes c, classes_has_students h where s.FullName= '"+vector.get(i).toString()+"' and c.NameClass= '"+vector2.get(i).toString()+"'"
                    + " and  s.Id = h.Students_Id and h.Classes_Id = c.Id ");
                        while(rs1.next())
                        {
                        id=rs1.getInt(1);
                        }

                        query="delete from learnsummer  where idStudents = '"+id+"'";
                        System.out.println(query);
                        pstmt = connect.prepareStatement(query);
                        pstmt.executeUpdate();
                       // query = "delete from classes where `Id`='" + id + "' and Faculties_Id= '" + idtrungtam + "'";
                        //pstmt = connect.prepareStatement(query);
                        //pstmt.executeUpdate();


                         i++;  
                    }
                return true;
            }
            else
            {
                String message =String.format( "bạn chưa xóa thành công! xin mời thử lại");
                JOptionPane.showMessageDialog( null, message );
                return false;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }  
        catch (Exception ex)
        {
            
        }
        return false;
        
    }
}
