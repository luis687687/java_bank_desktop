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

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private VBox quadro;
    @FXML private BorderPane father;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load("time");
        setActions();
        
        
    }
    
    public void setActions(){
       
        for(Node child : quadro.getChildren()){
            String id = child.getId();
           
            child.setOnMouseClicked(event -> {
                
                for(Node child2 : quadro.getChildren()){
                     child2.getStyleClass().clear();
                      child2.getStyleClass().add("line-option-light");
                }
                load(id);
                child.getStyleClass().clear();
                child.getStyleClass().add("line-option");
            });
        }
    }
    
    public void load(String id){
        
        try{
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("TimeConfig.fxml"));
            father.getChildren().remove(father.getCenter());
            switch(id){
                case "time":
                    fxml = new FXMLLoader(getClass().getResource("TimeConfig.fxml"));
                    break;
            case "money":
                fxml = new FXMLLoader(getClass().getResource("ValorConfig.fxml"));
                System.out.println("MMMMMMMM");
                break;
            case "percent":
                fxml = new FXMLLoader(getClass().getResource("PercentConfig.fxml"));
                break;

            }
            Parent element = fxml.load();
           
            father.setCenter(element);
            
        }
        catch(Exception ex) {
            System.out.println("Erro ao carregar seeting  options  "+ex);
        }
    }
    
}
