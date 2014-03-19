/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.upbang;

import java.awt.print.PrinterException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NguyenChi
 */
public class EditTable {
    private int indexOfColumn;
    private int indexOfRow;
    
    public int getIndexOfColumn() {
        return indexOfColumn;
    }
    
    public void setIndexOfColumn(int indexOfColumn) {
        this.indexOfColumn = indexOfColumn;
    }
    
    public int getIndexOfRow() {
        return indexOfRow;
    }
    
    public void setIndexOfRow(int indexOfRow) {
        this.indexOfRow = indexOfRow;
    }
    
    /*
     * phuong thuc them mot hang vao bang
     * Tham so dau vao: JTable truyen vao JTable cua bang can them hang
     *                  Vector: Mot vector chua cac gia tri cua hang can truyen vao.
    */
    public void addRowOfTable(JTable tableName, Vector vector) {
        DefaultTableModel tableModel;
        tableModel = (DefaultTableModel) tableName.getModel();
        tableModel.addRow(vector);
    }
    
    /*
    * phuong thuc xoa mot hang
    * Tham so dau vao: JTable truyen vao mot JTable cua bang can xoa hang
    * 
    */
    public void removeRowOfTable(JTable tableName) {
        DefaultTableModel tableModel;
        tableModel = (DefaultTableModel) tableName.getModel();
        indexOfRow = tableName.getSelectedRow();
        if(indexOfRow != -1) {
            tableModel.removeRow(indexOfRow);
        } else {
            // do something else
        }
    }
    public void removeManyRowOfTable(JTable tableName)
    {   Vector vector= new Vector();
        DefaultTableModel tableModel;
        tableModel = (DefaultTableModel) tableName.getModel();
        int i=0,a=0;
        a= tableName.getRowCount();
        while(i<a)
        {
            if(Boolean.parseBoolean(tableName.getValueAt(i,3).toString())==true)
            vector.add(i);
            i++;
        }
        vector.add(null);
        i=0;
        while(vector.get(i) !=null)
        {
         tableModel.removeRow(Integer.parseInt(vector.get(i).toString()));
         i++;  
        }
    }
    
    /*
    * Phuong thuc print bang
    * output: JTable la ten bang muon in.
    */
    public void printTable(JTable tableName) {
        try {
            boolean complete = tableName.print();
            if(complete) {
                System.out.println("Thanh cong");
            } else {
                System.out.println("That bai");
            }
        } catch (PrinterException ex) {
            Logger.getLogger(EditTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
