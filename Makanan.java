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
public class Makanan extends kamar1 implements Harga{
    String mk;
    public Makanan(){
        mk = mk;
    }
       String Potato(){
         return "Potato Wedges";
     }
     
     String Nasgor(){
         return "Salad Buah";
     }
     String Salad(){
         return "Nasgor";
     }
     @Override
     public double Hitungharga(){
         if(total == 0){
             total =50000*banyak;
         }
         else if(total == 1){
         total = 60000*banyak;;
     }
         else {
             total = 40000*banyak;
         }
         return total;
     }
     
    
}
