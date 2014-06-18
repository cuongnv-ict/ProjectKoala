/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

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
    private static String password = "akatsuki";
    
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
    
}
