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
    public class TimeConfigController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField interbank, comum, bloqueio, esperadivida, manutencao;
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
        interbank.setText(Long.toString(Configurations.milisseconds_time_transf_aubsent_client/1000));
        manutencao.setText(Long.toString(Configurations.second_time_to_apply_policy));
        comum.setText(Long.toString(0));
        bloqueio.setText(Long.toString(Configurations.milisseconds_time_pause_withdrow_account/1000));
        esperadivida.setText(Long.toString(Configurations.second_time_waiting_to_pay_credite_account));
    }
    
    public void save(){
        Configurations.milisseconds_time_transf_aubsent_client = Long.parseLong(interbank.getText())*1000;
        Configurations.second_time_to_apply_policy = Long.parseLong(manutencao.getText());
        Configurations.milisseconds_time_pause_withdrow_account = Long.parseLong(bloqueio.getText()) * 1000;
        Configurations.second_time_waiting_to_pay_credite_account =  Long.parseLong(esperadivida.getText());
        
    }
    
 
    
}
