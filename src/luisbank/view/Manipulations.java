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
         
         System.out.println(element + " here !!");
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

}
