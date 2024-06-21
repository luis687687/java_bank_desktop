/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class UserRowController implements Initializable {
    
    private @FXML Label nome, iban, code;
    private @FXML HBox area;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
    }    
    
    public HBox getArea(){
        return this.area;
    }
    public void setName(String text){
        
        this.nome.setText(text);
    }
    public void setIban(String text){
        this.iban.setText(text);
    }
    public void setCode(String text){
        this.code.setText(text);
    }
    
    
}
