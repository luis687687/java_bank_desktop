/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import luisbank.Core.Controller.Configurations;

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class PercentConfigController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField cobrarcredito, multa;
    @FXML private HBox btnsave;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
        btnsave.setOnMouseClicked( e -> {
            save();
        });
        
        
        
        
    }
    public void load(){
        cobrarcredito.setText(Double.toString(Configurations.percent_credit_normal_account));
        multa.setText(Double.toString(Configurations.percent_mult_credit));
        
    }
    
    public void save(){
        Configurations.percent_credit_normal_account = Double.parseDouble(cobrarcredito.getText());
        Configurations.percent_mult_credit = Double.parseDouble(multa.getText());
     
        
    }
    
 
    
}
