/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase.HocSinh;

/**
 *
 * @author Pham The Quyen
 */
public class Convert {
    public String chuyentien(String money){
        String Money = money;
        String result = null;
        String[] a = Money.split("\\.");
        int l = a.length;
        switch(a.length){
            case 1: result = DocSo(Money)+ " đồng";break;
            case 2: {
                result = DocSo(a[0]) +" nghìn ";
                if(DocSo(a[1]).length()!=0)
                    result += DocSo(a[1])+ "đồng";
                else
                    result += "đồng";
            } break;
            case 3: {
                    result = DocSo(a[0]) +" triệu ";
                    if(DocSo(a[1]).length()!=0){
                        result += DocSo(a[1])+" nghìn ";
                    }
                    if(DocSo(a[2]).length()!=0){
                        result += DocSo(a[2])+ " đồng";
                    }
                    else
                        result +="đồng";
                     }break;
            case 4: {
                    result = DocSo(a[0]) +" tỉ ";
                    if(DocSo(a[1]).length()!=0){
                        result += DocSo(a[1])+" triệu ";
                    }
                    if(DocSo(a[2]).length()!=0){
                        result += DocSo(a[2])+ " nghìn ";
                    }
                    if(DocSo(a[3]).length()!=0){
                        result += DocSo(a[3])+ " đồng ";
                    }
                    else
                        result +="đồng";
                     }break;
        }
        return result; 
    }
    public String DocSo(String so){
        String result = null;
        if(so.length()==1){
            result = Doc1So(so);
        }
        else if(so.length()==2){
            result = Doc2so(so);
        }
        else 
            result = Doc3so(so);
        return result;
    }
    public String Doc3so(String baso){
        String soTien = baso;
        String result = "";
        char so1 = soTien.charAt(0);
        String s1 = String.valueOf(so1);
        char so2 = soTien.charAt(1);
        String s2 = String.valueOf(so2);
        char so3 = soTien.charAt(2);
        String s3 = String.valueOf(so3);
        //bat dau doc
        if(s1.equals("0") && s2.equals("0")&& s3.equals("0")){
            return result = "";
        }
        result = Doc1So(s1);
        //doc chu so thu 2
        if(s2.equals("0")&& s3.equals("0")){
            result += " trăm";
            return result;
        }
        if(s2.equals("0")){
            result += " trăm";
            result += " linh";
        }
        else
        if(s2.equals("1")){
            result += " trăm ";
            result += "mười"; 
        }else{
            result += " trăm ";
            result += Doc1So(s2);
            result += " mươi";  
        }
        //doc so thu 3
        if(s3.equals("0")){
            return result;
        }
        if(s2.equals("0")||s2.equals("1"))
            result += " "+Doc1So(s3);
        else
            result += " "+DocSoCuoi(s3);
        return result;
    }
    public String Doc2so(String haiso){
        String soTien = haiso;
        String result = "";
        char so1 = soTien.charAt(0);
        String s1 = String.valueOf(so1);
        char so2 = soTien.charAt(1);
        String s2 = String.valueOf(so2);
        if(s1.equals("1")){
            
            if(s2.equals("0")){
                result = "mười";
            }
            else{
                result = "mười ";
                result += Doc1So(s2);
            }
        }
        else{
            result += Doc1So(s1) + " ";
            if(!s2.equals("0"))
                result += "mươi ";
            result += DocSoCuoi(s2);
        } 
        
        return result;
    }
    public String Doc1So(String so){
        int soT = Integer.parseInt(so);
        String x = null;
        switch(soT){
            case 0:x ="không";break;
            case 1:x ="một";break;
            case 2:x ="hai";break;
            case 3:x ="ba";break;
            case 4:x ="bốn";break;
            case 5:x ="năm";break;
            case 6:x ="sáu";break;
            case 7:x ="bảy";break;
            case 8:x ="tám";break;
            case 9:x ="chín";break;
        }
        return x;
    }
    public String DocSoCuoi(String so){
        int soT = Integer.parseInt(so);
        String x = null;
        switch(soT){
            case 0:x ="mươi";break;
            case 1:x ="mốt";break;
            case 2:x ="hai";break;
            case 3:x ="ba";break;
            case 4:x ="bốn";break;
            case 5:x ="năm";break;
            case 6:x ="sáu";break;
            case 7:x ="bảy";break;
            case 8:x ="tám";break;
            case 9:x ="chín";break;
        }
        return x;
    }
}
