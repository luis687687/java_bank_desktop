/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author CyberPunk
 */
public class GUIController implements Initializable {
    
    @FXML
    private Label texto;
    @FXML
    private Label texto2;
    @FXML
    private GridPane area1, areabody1;
    @FXML private BorderPane main, areabody;
    @FXML private VBox menubuttoncontainer;
    @FXML private GridPane rightarea;
    
    
   
    
   
    
    @FXML

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        luisbank.LuisBank.mainElement = main;

//        try{
//            luisbank.LuisBank.centerElment = main.getCenter();
//            luisbank.LuisBank.sideBar = main.getLeft();
//            Parent loginFXML = FXMLLoader.load(super.getClass().getResource("LoginGUI.fxml"));           
//            main.getChildren().clear();
//            main.setCenter((Node)loginFXML);
//            
//        }
//        catch(Exception ex) {
//            System.out.println(ex + " Excepção");
//        }
        openScene("btnmenu4");
        menubuttoncontainer.getChildren().forEach(child -> {
          
            child.setOnMouseClicked(event -> {
               openScene(child.getId());
               menubuttoncontainer.getChildren().forEach(child2 -> {
                   if(child2.getId().equals(child.getId()))
                       activa(child);
                   else
                        desactiva(child2);
               });
            });
            
        });
        
      
    }
    public void activa(Node element){
        element.setStyle("-fx-border-color: syscolor");
        AnchorPane pane = ((AnchorPane)element);
        AnchorPane filho = (AnchorPane)pane.getChildren().get(0);
        filho.getStyleClass().clear();
        filho.getStyleClass().add("btn-menu-icon-active");
    }
    public void desactiva(Node element){
        element.setStyle("-fx-border-color: transparent");
        AnchorPane pane = ((AnchorPane)element);
        AnchorPane filho = (AnchorPane)pane.getChildren().get(0);
        filho.getStyleClass().clear();
        filho.getStyleClass().add("btn-menu-icon");
    }
    public void openScene(String id){
        Parent fxml;
        
        try{
           switch(id){
            case "btnmenu2":
                fxml = FXMLLoader.load(getClass().getResource("CreateSingular.fxml"));
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
                break;
            case "btnmenu4":
                fxml = FXMLLoader.load(getClass().getResource("CreateEmployed.fxml"));
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
                break;
            case "btnmenu1":
                areabody.getChildren().clear();
                areabody.setCenter(areabody1);
                break;
            default:
                   break;
            }    
        }
        catch(Exception ex){
            System.out.println("Exception "+ex);
            
        }
    }
    
}