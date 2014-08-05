/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyen van cuong
 */
public class ConnectData {
    private Connection conn;
    public static String user = "root";
    private String url = "jdbc:mysql://localhost/projectkoala";
    private static String password = "baobka2010";
    
    public void setUrl(String strUrl) {
        this.url = strUrl;
    }
    
    public void setUser(String strUser) {
        this.user = strUser;
    }
    
    public void setPassword(String strPass) {
        this.password = strPass;
    }
    
    public Connection connectionDatabase() {
        try {       
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url + "?user=" + user + "&password=" + password); 
            Statement statement = conn.createStatement();
            writeFile();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(ConnectData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Đăng nhập không thành công !!! \n Xem lại user name hoặc password", "ERROR !!!", 
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }catch (SQLException ex) {
                //Logger.getLogger(ConnectData.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Đăng nhập không thành công !!! \n Xem lại user name hoặc password", "ERROR !!!", 
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
        return conn;
    }
    private void writeFile(){
        try{
            File file = new File("Login.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fw);
            String xau = user+" "+password;
            String xaucon = new String(xau);
            br.write(xaucon);
            br.close();
            fw.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error to save file");
        }
    }
}
