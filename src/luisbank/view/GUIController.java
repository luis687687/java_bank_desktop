/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package luisbank.view;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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
import luisbank.Core.Controller.IClient;
import luisbank.Core.Controller.Moviment;
import luisbank.Core.Controller.Software;
import sun.util.calendar.BaseCalendar;

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
    @FXML private BorderPane main, areabody, hometable;
    @FXML private VBox menubuttoncontainer, hometablebody;
    @FXML private GridPane rightarea;
    @FXML private AnchorPane btnlogout;
    @FXML private HBox btnsettings;
    
    
    
   
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        luisbank.LuisBank.mainElement = main;
        luisbank.LuisBank.mainController = this;

        logout();
       
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
        btnlogout.setOnMouseClicked( event -> {
            logoutAndSave();
        });
        btnsettings.setOnMouseClicked(event -> { 
            
            try{
                main.getChildren().remove(main.getCenter());
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("Settings.fxml"));
                Parent elem = fxml.load();
                main.setCenter(elem);
                
            }
            catch(Exception ex){
                System.out.println(ex+ "  ERRO AO ABRIR SETTINGS");
            }
        });
      
    }
    
    public void loadTable(){
        
        try{
            
        
            FXMLLoader fx = new FXMLLoader();
            Parent element = fx.load(getClass().getResource("RowHomeHistory.fxml"));
            hometable.getChildren().clear();
            
            hometable.setTop(element);
            hometable.setCenter(hometablebody);
            hometablebody.getChildren().clear();
            if(Software.getActualAgency() == null)
                    return;
            int cont  = 0;
            for(IClient client : Software.getActualAgency().getClients().values()){
                client.getAccount().getMoney(); //só para update
                
                for(Moviment mov : client.getAccount().getMoviments()){
                    cont++;
                   
                    FXMLLoader fx2 = new FXMLLoader();
                    Parent element1 = fx2.load(getClass().getResource("RowHomeHistory.fxml"));
                    System.out.println(mov);
                    GridPane el = (GridPane)element1;
                   if(cont == 4)
                       return;
                    for(Node child : el.getChildren()){
                         Label text = (Label)child;
                         
                    if(text.getStyleClass().contains("tbdescri")){
                        System.out.println("Descrição");
                        text.setText(mov.getType());
                    }
                    else{
                        if(text.getStyleClass().contains("tbinicio")){
                            text.setText(mov.getNormalDateStart());
                            System.out.println("Data1");
                        }
                        else{
                            if(text.getStyleClass().contains("tbtermin")){
                                text.setText(mov.getNormalDateEnd());
                                
                            }
                            else{
                                if(text.getStyleClass().contains("tbconta")){
                                    text.setText(client.getAccount().getIban());
                                    System.out.println("Conta");
                                }else{
                                    if(text.getStyleClass().contains("tbvalor")){
                                        text.setText(Double.toString(mov.getValue()));
                                        System.out.println("Valor");
                                    }
                                }
                            }
                        }
                        }
                     }
                     hometablebody.getChildren().add(el);
                     System.out.println("Printed'''");
                    
                   
                }
                return;
                
            }
        }
        catch(IOException ex){
            
        }
        
    }
    
    public void logout(){
        try{
            luisbank.LuisBank.centerElment = main.getCenter();
            luisbank.LuisBank.sideBar = main.getLeft();
            Parent loginFXML = FXMLLoader.load(super.getClass().getResource("LoginGUI.fxml"));           
            main.getChildren().clear();
            main.setCenter((Node)loginFXML);
        
        }
        catch(Exception ex) {
            System.out.println(ex + " Excepção");
        }
    }
    public void logoutAndSave(){
        logout();
        Software.saveAgencyState();
        Software.saveAdminState();
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
        main.setCenter(rightarea);
        try{
           switch(id){
            case "btnmenu2":
                fxml = FXMLLoader.load(getClass().getResource("CreateClient.fxml"));
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
                break;
            case "btnmenu4":
                fxml = FXMLLoader.load(getClass().getResource("CreateEmployed.fxml"));
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
                break;
          case "seeclients":
                
                fxml = FXMLLoader.load(getClass().getResource("ClientProfile.fxml"));
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
                break;
             case "listagency":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAgencyAndEmployeds.fxml"));
                fxml = loader.load();
                ListAgencyAndEmployedsController controller = loader.getController();
                controller.setParentController(this);
                
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
                break;
             case "profile_employed":
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("EmployedProfile.fxml"));
                fxml = loader2.load();
                EmployedProfileController controller2 = loader2.getController();
                controller2.setParent(this);
                areabody.getChildren().clear();
                areabody.setCenter(fxml);
               
                break;
             case "btnmenu1":
                areabody.getChildren().clear();
                areabody.setCenter(areabody1);
                loadTable();
                break;
            default:
                   break;
            }    
        }
        catch(Exception ex){
            System.out.println("Exception "+ex);
            
        }
    }
    public void openEmplyedProfile(){
        openScene("profile_employed");
    }

    
}
