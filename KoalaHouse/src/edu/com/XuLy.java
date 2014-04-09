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
                return  StrArr_1[StrArr_1.length-1].compareTo(StrArr_2[StrArr_2.length-1]);
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
}
