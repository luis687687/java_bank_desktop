/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import luisbank.Core.Controller.Software;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class RowEmployedController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private GridPane row;
    
    private ListAgencyAndEmployedsController parentController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        row.setOnMouseClicked( event -> {
            open();
        } );
    }    
    
    public void open(){
        try{
            
        }catch(Exception ex){
            
        }
    }
    
    public void setParentController(ListAgencyAndEmployedsController parentController){
        
        this.parentController = parentController;
    }
}
