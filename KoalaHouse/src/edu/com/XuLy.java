/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com;

import java.awt.Component;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nguyen van cuong
 */
public class XuLy {
    public static ArrayList temp;
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
                int x, y;
                String tr_x = "";
                String tr_y = "";
                x = StrArr_1.length - 1;
                y = StrArr_2.length - 1;
                if (StrArr_1[x].charAt(0) == '(') {
                    for (int i = x - 1; i >= 0; i--) {
                        tr_x += StrArr_1[i];
                    }
                } else {
                    for (int i = x; i >= 0; i--) {
                        tr_x += StrArr_1[i];
                    }
                }
                if (StrArr_2[y].charAt(0) == '(') {
                    for (int i = y - 1; i >= 0; i--) {
                        tr_y += StrArr_2[i];
                    }
                } else {
                    for (int i = y; i >= 0; i--) {
                        tr_y += StrArr_2[i];
                    }
                }
                return tr_x.compareTo(tr_y);
            }
        });
    }
    public static void SapXepTen(ArrayList data, final int column,final int column1) {
        Collections.sort(data, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
               
                if((o2[column1].toString()=="")||!o1[column1].toString().equalsIgnoreCase(o2[column1].toString())||(""==o1[column1].toString())){
                    return 1;
                }            
                String[] StrArr_1 = o1[column].toString().trim().toUpperCase().split(" ");
                String[] StrArr_2 = o2[column].toString().trim().toUpperCase().split(" ");
                int x=0, y=0;
                String tr_x = "";
                String tr_y = "";
                x = StrArr_1.length - 1;
                y = StrArr_2.length - 1;
                if (StrArr_1[x].charAt(0) == '(') {
                    for (int i = x - 1; i >= 0; i--) {
                        tr_x += StrArr_1[i];
                    }
                } else {
                    for (int i = x; i >= 0; i--) {
                        tr_x += StrArr_1[i];
                    }
                }
                if (StrArr_2[y].charAt(0) == '(') {
                    for (int i = y - 1; i >= 0; i--) {
                        tr_y += StrArr_2[i];
                    }
                } else {
                    for (int i = y; i >= 0; i--) {
                        tr_y += StrArr_2[i];
                    }
                }
                //System.out.println(StrArr_1.length+"-"+StrArr_2.length);
               return tr_x.compareTo(tr_y);
            }
        });
    }
public static void SapXepThang(ArrayList data, final int column) {
        Collections.sort(data, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                String[] StrArr_1 = o1[column].toString().toUpperCase().split("-");
                String[] StrArr_2 = o2[column].toString().toUpperCase().split("-");
                return StrArr_1[0].compareTo(StrArr_2[0]);
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
            String arr[] = str.split(",");
            try {
                for (int i = 0; i < arr.length; i++) {
                    Integer.parseInt(arr[i]);
                }
                str = "";
                for (int i = 0; i < arr.length; i++) {
                    str += arr[i];
                }
                int lenght = str.length();
                int q = lenght / 3;
                int r = lenght % 3;
                if (r == 0) {
                    r = 3;
                    q--;
                }
                int count = 0;
                StringBuilder msg = new StringBuilder(str);
                for (int i = 0; i < q; i++) {
                    msg.insert(i * 3 + r + count, ',');
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
            String arr[] = str.split(",");
            try {
                for (int i = 0; i < arr.length; i++) {
                    if(i==0 && arr[i].equals("-"))
                        continue;
                    Integer.parseInt(arr[i]);
                }
                str = "";
                for (int i = 0; i < arr.length; i++) {
                    str += arr[i];
                }
                return str;
            } catch (NumberFormatException e) {
                return str;
            }
        }
    }

    public static String getDate(String msg) {
        String str[] = msg.split("-");
        return str[2] + "-" + str[1] + "-" + str[0];
    }

    public static boolean getTime(String before, String after) {
        String arrBefore[] = before.split("-");
        String arrAfter[] = after.split("-");
        int x, y, z;
        x = Integer.parseInt(arrAfter[0]) - Integer.parseInt(arrBefore[0]);
        y = Integer.parseInt(arrAfter[1]) - Integer.parseInt(arrBefore[1]);
        z = Integer.parseInt(arrAfter[2]) - Integer.parseInt(arrBefore[2]);
        if (x < 0) {
            return false;
        }
        if (x > 0) {
            return true;
        }
        if (x == 0 && y < 0) {
            return false;
        }
        if (y > 0) {
            return true;
        }
        if (y == 0 && z < 0) {
            return false;
        }
        return true;
    }

    public static void resizeColumnWidth(JTable table, int arr[]) {
        final TableColumnModel columnModel = table.getColumnModel();
        int x = 0;
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 0;
            width = Math.max(arr[column] * 10, width);
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            Object value = tableColumn.getHeaderValue();
            TableCellRenderer renderer = tableColumn.getHeaderRenderer();
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
            width = Math.max(c.getPreferredSize().width, width);
            if (width > 400) {
                width = 400;
            }
            columnModel.getColumn(column).setPreferredWidth(width + 10);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        x = Toolkit.getDefaultToolkit().getScreenSize().width - x;
//        if (x < 250) {
//            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        } else {
//            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        }
    }

    public static int[] getSize(Object[][] o) {
        int arrSize[] = new int[o[0].length];
        for (int i = 0; i < o[0].length; i++) {
            arrSize[i] = 0;
        }
        for (int i = 0; i < o[0].length; i++) {
            int max = 0;
            for (int j = 0; j < o.length; j++) {
//                String s = String.valueOf(o[j][i].toString());
////                if (max < s.length()) {
////                    max = s.length();
////                }
                String s;
                if (o[j][i] == null) {
                    s = "";
                } else {
                    s = String.valueOf(o[j][i].toString());
                }
                if (max < s.length()) {
                    max = s.length();
                }
            }
            arrSize[i] = max;
        }
        return arrSize;
    }
   public static String getNumber4(String x){
       String y = new String(x);
       if(x.length()==1){
           y = "000"+x;
       }
       else if(x.length()==2){
           y = "00"+x;
       }
       else if(x.length()==3){
           y= "0" + x;
       }
       return y;
   }
       public static boolean sosanhngaythang( String ngaybd, String ngaykt)
    {
        String []bd;
        String []kt;
        kt = ngaykt.split("-");
        bd = ngaybd.split("-");
        int datebd= Integer.parseInt(bd[2]);
        int monthbd= Integer.parseInt(bd[1]);
        int yearbd= Integer.parseInt(bd[0]);
        int datekt = Integer.parseInt(kt[2]);
        int monthkt= Integer.parseInt(kt[1]);
        int yearkt= Integer.parseInt(kt[0]);
        if(yearkt>yearbd) return true;
        else if(yearkt==yearbd&&monthkt>monthbd) return true;
        else if(yearkt==yearbd&&monthkt==monthbd&&datekt>=datebd) return true;
        else return false;
    }
        
}
