/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com.upbang;

/**
 *
 * @author Pham The Quyen
 */
public class XuLiXau {
    public String NgayThangNam(String s){
        String result = "";
        String[] a = s.split("-");
        result = a[2]+"-"+a[1]+"-"+a[0];
        return result;
    }
    public String NamThangNgay(String s){
        String result = "";
        String[] a = s.split("-");
        result = a[2]+"-"+a[1]+"-"+a[0];
        return result;
    }
}
