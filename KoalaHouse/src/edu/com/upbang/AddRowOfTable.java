/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package koalaproject;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NguyenChi
 */
public class AddRowOfTable {
    
    /*
     * phuong thuc them mot hang vao bang
     * Tham so dau vao: DefaultTableModel truyen vao tableModel cua bang can them hang
     *                  Vector: Mot vector chua cac gia tri cua hang can truyen vao.
    */
    public void addRowOfTable(DefaultTableModel tableModel, Vector vector) {
        tableModel.addRow(vector);
    }
    
}
