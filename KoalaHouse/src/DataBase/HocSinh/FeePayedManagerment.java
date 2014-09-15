/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;

import DataBase.ConnectData;
import edu.com.XuLy;
import edu.com.upbang.XuLiXau;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pham The Quyen
 */
public class FeePayedManagerment {
    private Connection connect;
    ResultSet rs1,rs2,rs3;
    Statement statement;
    DefaultTableModel model;
    Object [][] rowColumn;
    public FeePayedManagerment(){
        ConnectData c = new ConnectData();
        connect = c.connectionDatabase();
        try {
            statement = connect.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(DebtList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void BangDanhSachTongPhi(int idFac,int semmester,int year,JTable table) {
        int stt = 1;
        try {
            ArrayList nameCol = getNameColumn(idFac,semmester,year);
            Object[] nameColumn = new Object[nameCol.size()];
            //cac bien kiem tra
            boolean hocHe = false;
            int t1 = nameCol.indexOf("T1");
            int t2 = nameCol.indexOf("T2");
            int t3 = nameCol.indexOf("T3");
            int t4 = nameCol.indexOf("T4");
            int t5 = nameCol.indexOf("T5");
            int t6 = nameCol.indexOf("T6");
            int xebus = nameCol.indexOf("Xe Bus");
            int phikyhe = nameCol.indexOf("Học Phí Kỳ Hè");
            int phitrongmuon = nameCol.indexOf("Phí Trông Muộn");
            int ptongphi = nameCol.indexOf("Tổng Cộng Học Phí");
            int ptonghoanphi = nameCol.indexOf("Tổng Hoàn HP");
            for(int i=0;i<nameCol.size();i++){
                nameColumn[i] = nameCol.get(i);
                if(nameCol.get(i).toString().equals("T1"))
                    hocHe = true;
            }
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            //doan lay cac truong.
            rs3 = statement.executeQuery("SELECT students.Id,students.FullName,classes.NameClass \n" +
            "FROM projectkoala.students,classes,classes_has_students\n" +
            "where students.isactive = 1 and students.Id = classes_has_students.Students_Id and classes.Id = classes_has_students.Classes_Id Order by classes.NameClass");
            if(rs3!= null)
            while (rs3.next()){
                Object[] str = new Object[nameCol.size()];
                for(int j=0;j<str.length;j++){
                    str[j]="";
                }
                str[0] = stt;
                stt++;
                str[1] = rs3.getString(2);
                str[2] = rs3.getString(3);
                int idStudent = rs3.getInt(1);
                //neu co hoc he thi goi ham hoc he
                if(hocHe){
                    //ham hoc he. arraylist tra lai so tien va cac tuan hoc
                    ArrayList hoche = new Get().HocHe(idStudent);
                    for(int j=0;j<hoche.size();j++){
                        switch(Integer.parseInt(hoche.get(j).toString())){
                            case 1: str[t1] = "x";break;
                            case 2: str[t2] = "x";break;
                            case 3: str[t3] = "x";break;
                            case 4: str[t4] = "x";break;
                            case 5: str[t5] = "x";break;
                            case 6: str[t6] = "x";break;    
                        }
                    }
                }
                //get so tien xe bus
                int tienxebus = new Get().GetSoTienXeBusDaThu(idStudent,idFac,semmester,year);
                if(tienxebus>0){
                    str[xebus] = XuLy.setMoney(String.valueOf(tienxebus));
                }
                //get so tien cac phi
                ArrayList cacPhi = new Get().getPhiHocSinhDaThu(idStudent, idFac, semmester, year);
                int tongHoanHP = 0;
                int tongPhi = 0;
                //map cac phi tuong ung cua hoc sinh nay
                for(int i=0;i<cacPhi.size();i++){
                    Object[] phi = new Object[2];
                    phi = (Object[]) cacPhi.get(i);
                    String tenPhi = phi[0].toString();
                    tenPhi = tenPhi.toLowerCase();
                    if(tenPhi.indexOf("hoàn")!= -1){
                        tongHoanHP += Integer.parseInt(phi[1].toString());
                    }
                    int x = nameCol.indexOf(phi[0].toString());
                    if(x == -1)
                        if(phi[0].equals("Phí Học Hè"))
                            x = phikyhe;
                    if(x >= 0){
                        if(str[x].equals(""))
                            str[x] = String.valueOf(phi[1]);
                        else{
                            int a = Integer.parseInt(XuLy.getMoney(str[x].toString()));
                            int b = Integer.parseInt(String.valueOf(phi[1]));
                            str[x] = String.valueOf(a+b);
                        }
                            }
                    tongPhi += Integer.parseInt(String.valueOf(phi[1]));
                    if(x>=0)
                        str[x]= XuLy.setMoney(str[x].toString());
                }
                tongPhi = tongPhi - 2*tongHoanHP;
                tongPhi += tienxebus;
                if(tongPhi!=0)
                str[ptongphi] = XuLy.setMoney(String.valueOf(tongPhi));
                if(tongHoanHP>=0)
                str[ptonghoanphi] = XuLy.setMoney(String.valueOf(tongHoanHP));
                data.add(str);
            }
            //sap xep ten o day
            Collections.sort(data, new TotalFreeComparator());
            for (int i = 0; i < data.size(); i++) {
                Object[] st;
                st = (Object[]) data.get(i);
                st[0] = i + 1;
            }
            //------------------------
            Object [][] rowColumn = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++){
            rowColumn[i] = data.get(i);
            model = new DefaultTableModel(rowColumn, nameColumn){
            };
            table.setModel(model);
            }
            if(data.size() ==0){
                model = new DefaultTableModel(nameColumn, 0);
                table.setModel(model);
            }
            if(data.size()==0){
                Object [][] o = new Object[][]{nameColumn};
               XuLy.resizeColumnWidth(table, XuLy.getSize(o));
            }else{
                 XuLy.resizeColumnWidth(table, XuLy.getSize(rowColumn));
            }
            statement.close();
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeePayedManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public ArrayList getNameColumn(int idFac,int semmester,int year){
            ArrayList nameColumn = new ArrayList();
            nameColumn.add("STT");
            nameColumn.add("Họ Tên");
            nameColumn.add("Lớp");
            int haveSummer =0;
        if(semmester== 4){
            nameColumn.add("Học Phí Kỳ Hè");
            nameColumn.add("T1");
            nameColumn.add("T2");
            nameColumn.add("T3");
            nameColumn.add("T4");
            nameColumn.add("T5");
            nameColumn.add("T6");
        }
        nameColumn.add("Xe Bus");
        try {
            if(semmester==1){
            rs1 = statement.executeQuery("SELECT NameCost FROM cost,students_has_cost\n" +
            "where cost.Id = students_has_cost.Cost_Id and students_has_cost.IsDebt = 0\n" +
            "and students_has_cost.Faculties_Id = "+idFac+" and cost.year = "+year+" and (cost.Semesters = 1 or cost.Semesters = 5) \n" +
            "group by NameCost");
            }else{
                rs1 = statement.executeQuery("SELECT NameCost FROM cost,students_has_cost\n" +
            "where cost.Id = students_has_cost.Cost_Id and students_has_cost.IsDebt = 0\n" +
            "and students_has_cost.Faculties_Id = "+idFac+" and cost.year = "+year+" and cost.Semesters = "+semmester+" \n" +
            "group by NameCost");
            }
            if(rs1!= null)
            while(rs1.next()){
                nameColumn.add(rs1.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeePayedManagerment.class.getName()).log(Level.SEVERE, null, ex);
        }
        nameColumn.add("Tổng Hoàn HP");
        nameColumn.remove("Phí Học Hè");
        nameColumn.remove("Phí Trông Muộn");
        nameColumn.add("Phí Trông Muộn");
        for(int i=0;i<nameColumn.size();i++){
            String x = new String(nameColumn.get(i).toString());
            x = x.toLowerCase();
            if(x.indexOf("thu")!= -1&& x.indexOf("khác")!= -1){
                nameColumn.remove(i);
                break;
            }
        }
        nameColumn.add("Thu Khác");
        nameColumn.add("Tổng Cộng Học Phí");
        nameColumn.add("Ghi Chú");
        return nameColumn;
        }
}