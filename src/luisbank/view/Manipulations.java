/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luisbank.view;

import javafx.scene.layout.BorderPane;


/**
 *
 * @author CyberPunk
 */
public class Manipulations {
     
    public static boolean isLight = true;
    
     public boolean changeTheme(BorderPane element){
         
         
        if(!isLight){
            element.getStylesheets().add(getClass().getResource("dark.css").toExternalForm());
            element.getStylesheets().remove(getClass().getResource("light.css").toExternalForm());
        }
        else{
            element.getStylesheets().add(getClass().getResource("light.css").toExternalForm());
            element.getStylesheets().remove(getClass().getResource("dark.css").toExternalForm());
        }
        return isLight;
    }
     
     public static String formatValue(String value){
         String v = value;
         int posdot = v.indexOf(".");
         
         if(posdot != -1){
             
             int last = posdot+3;
             while(last >= value.length()){
                 last-=1;
             }
             v = v.substring(0, last);
         }
         else
             v = v+",00";
         v = v.replace(".", ",");
         return v;
     }
     
     public static String formatMoney(String money){
         String mon = formatValue(money);
         
         int dotpos = mon.indexOf(",");
         String mon2 = "";
         if(dotpos != -1)
            mon2 = mon.substring(0, dotpos);
         else
             mon2 = mon;
         
         
         String total = "";
         int cont = 0;
         int size = mon2.length();
         if(size > 3){
             for(int i = size; i > 0; i--){
                 cont ++;
                 total += mon2.substring(i-1, i);
                 if(cont % 3 == 0){
                     cont = 0;
                     if(i != 1)
                        total += ".";
                 }
             }
             mon2 = "";
             for(int i = total.length(); i > 0; i --){
                 mon2 += total.substring(i - 1, i);
             }
         }
         

         return mon2;
     }
}
