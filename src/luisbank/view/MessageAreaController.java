/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class MessageAreaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Label message;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setMessage(String message){
        this.message.setText(message);
    }
}
