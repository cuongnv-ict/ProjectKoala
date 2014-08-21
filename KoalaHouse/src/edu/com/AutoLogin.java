/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com;

import edu.com.Dialog.DangNhapVao;
import javax.swing.UIManager;

/**
 *
 * @author Pham The Quyen
 */
public class AutoLogin {
    static DangNhapVao dialog;
    public static void main(String[] args) {
        
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", false);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }
                dialog = new DangNhapVao(new javax.swing.JFrame(), true,true);
                dialog.setLocation(420, 130);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                if(!dialog.canLogin){
                    dialog.setVisible(true);
                }
            }
        });
    }
}
