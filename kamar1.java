/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bab10;

import Bab9.*;
import Bab8.*;
import Bab7.*;
import Bab3.*;

/**
 *
 * @author USER
 */
public class kamar1 implements Harga{
     int mlm,banyak,total,harga,malam;
     String nama,NIK,jk,jenis;
     
     
     public kamar1(){
         jenis = "StandartRoom";
         nama = "Malik Abdullah";
         NIK = "2218091";
     }
    int total(){
    total =  harga * malam;  
    return total;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String Nama){
        this.nama = nama;
    }

    public String getNik(){
        return NIK;
    }
    public void setNik(String NIK){
        this.NIK = NIK;
    }
    
    String kmr1(){
        harga = 150000;
        malam = malam;
        total();
        return "StandartRoom";
    }  
    String kmr2(){
        harga = 500000;
         malam = malam;
        total();
         return "Deluxe";
     }
    String kmr3(){
        harga = 300000;
        malam = malam;
        total();
        return "Family Room";
    }
    @Override
    public double Hitungharga(){  
           total = harga*malam;
     return total;         
    }
    boolean CekData(String nama , String NIK){
        if (nama.equals(getNama()) && NIK.equals(getNik())){
            return true;
        }
        return false;
    }
}
