/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

import static DataBase.DataTable.user;
import edu.com.ThongTin;
import edu.com.XuLy;
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

public class SQLJTree {

    private Connection connect;
    ResultSet rs1,rs2;
    Statement statement,statement1;
    PreparedStatement Pstate;
    DefaultTableModel model;
    Object[][] rowColumn;

    public SQLJTree() {
        ConnectData c = new ConnectData();

        try {
            connect = c.connectionDatabase();
            statement = connect.createStatement();
            
        } catch (SQLException ex) {
            //Loi truy nhap db
        }
    }
    public boolean ratruong(int id_lop)
    {
        try{
            String query = "update students set isactive = -1 where Id  in (select Students_Id from classes_has_students where Classes_Id='"+id_lop+"' ) and Faculties_Id='"+ThongTin.trungtam+"'" ;
           
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
            query = "delete from classes_has_students where Classes_Id='"+id_lop+"'  and Faculties_Id='"+ThongTin.trungtam+"'";
           
            connect.prepareStatement(query);
            pstmt.executeUpdate();
        }
        catch(SQLException ex){
            
        }
        return false;
    }
    public boolean CheckAdmin(String user){
        boolean isAdmin = false;
        int isRoot=0;
        System.out.println(user);
        if(user.equals("root")){
            isAdmin = true;
        }
        else{
        try
        {
            rs1=statement.executeQuery("select isRoot from accounts where UserName='"+user+"'");
            while(rs1.next())
            {
                isRoot = rs1.getInt(1);
            }
            if(isRoot == 1)
                isAdmin = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return isAdmin;
    }

}

