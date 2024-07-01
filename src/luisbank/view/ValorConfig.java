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
public class ValorConfig implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField maxcred, manutencao, lucro, limitelevantamento;
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
        maxcred.setText(Double.toString(Configurations.maximus_credite));
        manutencao.setText(Double.toString(Configurations.money_demand_corrent_account));
        lucro.setText(Double.toString(Configurations.money_received_financy_account));
        limitelevantamento.setText(Double.toString(Configurations.financy_account_limite_remove_money));
    }
    
    public void save(){
        Configurations.maximus_credite = Double.parseDouble(maxcred.getText());
        Configurations.money_demand_corrent_account = Double.parseDouble(manutencao.getText());
        Configurations.money_received_financy_account = Double.parseDouble(lucro.getText());
        Configurations.financy_account_limite_remove_money = Double.parseDouble(limitelevantamento.getText());
        
    }
    
 
    
}
