/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JTable;

/**
 *
 * @author nguyen van cuong
 */
public class XuLy {
    /*
     * Sử dụng để xắp xếp dữ liệu
     * Đầu vào gồm bảng sắp xếp và vị trí cột muốn sắp xếp tính từ 1;
     */

    public static void SapXepTen(ArrayList data, final int column) {
        Collections.sort(data, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                String[] StrArr_1 = o1[column].toString().toUpperCase().split(" ");
                String[] StrArr_2 = o2[column].toString().toUpperCase().split(" ");
                return StrArr_1[StrArr_1.length - 1].compareTo(StrArr_2[StrArr_2.length - 1]);
            }
        });
    }

    public static void setID(ArrayList id, JTable table, int column) {
        id.removeAll(id);
        for (int i = 0; i < table.getRowCount(); i++) {
            id.add(Integer.parseInt((table.getModel()).getValueAt(i, column).toString()));
            (table.getModel()).setValueAt(i + 1, i, column);
        }
    }

    public static String setMoney(String str) {
        if (str == null) {
            return null;
        } else {           
            String arr[] = str.split("\\.");
            try {
                for (int i = 0; i < arr.length; i++) {
                    Integer.parseInt(arr[i]);
                }
                str="";
                for (int i = 0; i < arr.length; i++) {
                    str += arr[i];
                }
                int lenght = str.length();
                int q = lenght / 3;
                int r = lenght % 3;
                if(r==0){
                    r=3;
                    q--;
                }
                int count = 0;
                StringBuilder msg = new StringBuilder(str);
                for (int i = 0; i < q; i++) {
                    msg.insert(i * 3 + r + count, '.');
                    count++;
                }
                return msg.toString();
            } catch (NumberFormatException e) {           
                return str;
            }
        }
    }
     public static String getMoney(String str) {
        if (str == null) {
            return null;
        } else {           
            String arr[] = str.split("\\.");
            System.out.println(arr.length);
            try {
                for (int i = 0; i < arr.length; i++) {
                    Integer.parseInt(arr[i]);
                }
                str="";
                for (int i = 0; i < arr.length; i++) {
                    str += arr[i];
                }     
                return str;
            } catch (NumberFormatException e) {           
                return null;
            }
        }
    }
}
