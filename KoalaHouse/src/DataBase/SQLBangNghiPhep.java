/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Venus
 */
public class SQLBangNghiPhep {
    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public SQLBangNghiPhep(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(AStudentAndLateDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BangNghiPhep(JTable table)
    {
        try
        {
             
           
            int idstudent=-1;
            model=(DefaultTableModel) table.getModel();
            Object[] nameColumn = {model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4)
                                    ,model.getColumnName(5),model.getColumnName(6)};
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            rs1 = statement.executeQuery("SELECT l1.Id,s1.FullName,c1.NameClass,l1.StartDate,l1.EndDate,l1.NumberOfDay,l1.IsActive FROM \n" +
            "projectkoala.leaves l1,projectkoala.classes c1, projectkoala.students s1, projectkoala.classes_has_students h1\n" +
            "where l1.Students_Id=s1.Id and s1.Id=h1.Students_Id and h1.Classes_Id=c1.Id order by l1.StartDate desc");
            
            
            while(rs1.next()) 
            {
                    Object[] str = new Object[7];
                    str[0]=rs1.getInt(1);
                    /*
                    rs2 = statement2.executeQuery("SELECT FullName,PhoneNumberFather FROM students where id ='"+ idstudent+"' ");
            
                    while(rs2.next())
                    {
                        str[1]= rs2.getString(1);
                        str[4]= rs2.getString(2);
                    }
                    */
                    str[1]= rs1.getString(2);
                    str[2]= rs1.getString(3);
                    str[3]= new XuLiXau().NgayThangNam (rs1.getString(4));
                    str[4]=new XuLiXau().NgayThangNam( rs1.getString(5));
                    str[5]=(rs1.getString(6));
                    if(rs1.getInt(7)==0)
                        str[6]="Đã Hoàn Phí";
                    else 
                        str[6]="Chưa Hoàn Phí";
                    data.add(str);
            }
            if(data.size()==0)
            {
                Object[] str = new Object[12];
                str[0]="";
                str[1]="";
                str[2]="";
                str[3]="";
                str[4]="";
                str[5]="";
                data.add(str);
                Object[][] rowColumn = new Object[data.size()][];
                for (int i = 0; i < data.size(); i++) {
                rowColumn[i] = data.get(i);
                model = new DefaultTableModel(rowColumn, nameColumn) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                        java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false,false
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
                        java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{
                        false,false,false,false,false, false,false
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
            connect.close();

        }
        catch(SQLException exception)
        {
            
        }
    }
    
}
