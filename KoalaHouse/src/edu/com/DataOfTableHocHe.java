/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.com;

/**
 *
 * @author Venus
 */
public class DataOfTableHocHe {
    private int id,idstudent,tongsotuan;
    private String fullname,classes,tinhtrang,tuanhoc,nam;
    private String namegia;
    private boolean danhdau;
     public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setIdStudents(int idstudent)
    {
        this.idstudent=idstudent;
    }
    public int getIdStudents()
    {
        return idstudent;
    }
    public void setFullname(String fullname)
    {
        this.fullname=fullname;
    }
    public String getFullname()
    {
        return fullname;
    }
    public void setClasses(String classes)
    {
        this.classes=classes;
    }
    public String getClasses()
    {
        return classes;
    }
    public void setNameGia(String nameGia)
    {
        this.namegia=nameGia;
    }
    public String getNameGia()
    {
        return namegia;
    }
    public void setDanhDau(boolean danhdau)
    {
        this.danhdau=danhdau;
    }
    
    public boolean getDanhDau()
    {
        return danhdau;
    }
    public void setTuanHoc(String tuanhoc)
    {
        this.tuanhoc=tuanhoc;
    }
    public String getTuanHoc()
    {
        return tuanhoc;
    }
    public void setTongSoTuan(int tongsotuan)
    {
        this.tongsotuan=tongsotuan;
    }
    public int getTongSoTuan()
    {
        return tongsotuan;
    }
     public void setTinhTrang(String tinhtrang)
    {
        this.tinhtrang=tinhtrang;
    }
    public String getTinhTrang()
    {
        return tinhtrang;
    }
     public void setNam(String nam)
    {
        this.nam=nam;
    }
    public String getNam()
    {
        return nam;
    }
}
