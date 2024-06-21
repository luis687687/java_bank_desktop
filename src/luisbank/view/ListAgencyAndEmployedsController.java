/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luisbank.view;

import java.io.IOException;
import luisbank.Core.Controller.Agency;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import luisbank.Core.Controller.Employed;
import luisbank.Core.Controller.Person;
import luisbank.Core.Controller.Software;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 *
 * @author CyberPunk
 */
public class ListAgencyAndEmployedsController implements Initializable{
    
    @FXML private VBox agencyfather, employedtable;
    @FXML private HBox btnaddagenc;
    @FXML private TextField inputagencyname;
    
    public FXMLLoader loader;
    public RowEmployedController controller;
    
    
    private GUIController parentController;
   
    @Override
    public void initialize(URL url, ResourceBundle rb){
       try{
            
            btnaddagenc.setOnMouseClicked(event -> {
                String agencyname = inputagencyname.getText();
                Software.appendAgency(new Agency(agencyname));
                updateShowingAgencies();
            });

            
            updateShowingAgencies();
          
            
           
       }
       catch(Exception ex){
           
       }
    }
    
    
    public void setParentController(GUIController parentController){
        this.parentController = parentController;
    }
    
    public GUIController getParent(){
        return this.parentController;
    }
    public void updateShowingAgencies(){
        agencyfather.getChildren().clear();
          for(Agency agency : Software.getAgencies().values()){
                elementagency("a", agency.getCode());
            }
    }
    

    
    public void elementagency(String image, String agency){
          
            HBox rowagency = new HBox();
          rowagency.setAlignment(Pos.CENTER_LEFT);
          rowagency.setStyle("-fx-spacing: 1 0; -fx-padding: 10 0 10 10;");
          AnchorPane img = new AnchorPane();
          img.setStyle("-fx-pref-width: 20; -fx-pref-height: 20; -fx-background-color: colorbase; -fx-shape: 'M810.1 161.6h-25.5v628.6c0 41.69999999999993-34 75.79999999999995-75.80000000000007 75.79999999999995h-465.4v23.299999999999955c2.842170943040401e-14 26.700000000000045 21.700000000000045 48.30000000000007 48.30000000000001 48.30000000000007h518.3c26.700000000000045 0 48.299999999999955-21.600000000000023 48.299999999999955-48.30000000000007v-679.5c0-26.599999999999966-21.5-48.299999999999955-48.19999999999993-48.19999999999996z m-101.39999999999998 676.8c26.699999999999932 0 48.299999999999955-21.600000000000023 48.299999999999955-48.299999999999955v-679.3000000000001c0-26.69999999999996-21.700000000000045-48.299999999999955-48.299999999999955-48.299999999999955h-345.30000000000007v16c0.20000000000004547 1.7000000000000028 0.30000000000001137 3.4000000000000057 0.30000000000001137 5.099999999999994v132.4c0 37.5-30.5 68.10000000000002-68.09999999999997 68.10000000000002h-132.40000000000003c-1.3999999999999773 0-2.799999999999983-0.10000000000002274-4.199999999999989-0.20000000000004547h-16.80000000000001v506.20000000000005c0 26.699999999999932 21.700000000000017 48.299999999999955 48.30000000000001 48.299999999999955h518.2z m-418.30000000000007-450.29999999999995h338c12.600000000000023 0 22.800000000000068 10.199999999999989 22.800000000000068 22.799999999999955s-10.200000000000045 22.80000000000001-22.800000000000068 22.80000000000001h-338c-12.599999999999966 0-22.799999999999955-10.199999999999989-22.799999999999955-22.80000000000001s10.199999999999989-22.799999999999955 22.799999999999955-22.799999999999955z m0 123.79999999999995h338c12.600000000000023 0 22.800000000000068 10.200000000000045 22.800000000000068 22.800000000000068 0 12.599999999999909-10.200000000000045 22.799999999999955-22.800000000000068 22.799999999999955h-338c-12.599999999999966 0-22.799999999999955-10.200000000000045-22.799999999999955-22.799999999999955s10.199999999999989-22.800000000000068 22.799999999999955-22.800000000000068z m-22.899999999999977 139.39999999999998c0-12.599999999999909 10.199999999999989-22.799999999999955 22.80000000000001-22.799999999999955h169c12.599999999999966 0 22.80000000000001 10.200000000000045 22.80000000000001 22.799999999999955s-10.300000000000011 22.800000000000068-22.900000000000034 22.800000000000068h-169c-12.399999999999977 0-22.69999999999999-10.200000000000045-22.69999999999999-22.800000000000068z m-104.30000000000001-394.59999999999997h132.7c22.100000000000023-0.19999999999998863 40-18.099999999999994 40.200000000000045-40.19999999999999v-132.9c0-12.199999999999989-10.100000000000023-20.39999999999999-20.5-20.39999999999999-5 0-10.100000000000023 1.7999999999999972-14.300000000000011 6l-152.60000000000002 152.7c-12.799999999999983 12.800000000000011-3.6999999999999886 34.80000000000001 14.5 34.80000000000001z' ");
          Label tes = new Label();
          tes.setText("Agência: "+agency);
          rowagency.getChildren().add(img);
          rowagency.getChildren().add(tes);
          
          agencyfather.getChildren().add(rowagency);
          rowagency.setOnMouseClicked( event -> { 
              Software.setActualAgency(agency);
               employedtable.getChildren().clear();
              try{
                for(Person emp : Software.getActualAgency().getEmplyeds().values()){
                    loader = new FXMLLoader(getClass().getResource("RowEmployed.fxml"));
                    controller = loader.getController();
                    //controller.setParentController(this);
                    Parent element = loader.load();
                    GridPane e = (GridPane)element;
                  Employed employed = (Employed)emp;
                  
                  e.getChildren().forEach(child -> { 
                      if(child.getStyleClass().contains("empemail"))
                      {
                          
                          ((Label)child).setText(employed.getEmail());
                      }
                      else{
                          if(child.getStyleClass().contains("empagency"))
                            {

                                ((Label)child).setText(Software.getActualAgency().getCode());
                            }
                          else{
                              if(child.getStyleClass().contains("empname"))
                                  ((Label)child).setText(employed.getName());
                              else{
                                  if(child.getStyleClass().contains("empphone"))
                                    ((Label)child).setText(employed.getPhone());
                              }
                          }
                      }
                           
                  });
                  HBox row = new HBox();
                  row.getChildren().add(e);
                  row.setOnMouseClicked(event2 -> {
                      Software.getActualAgency().setSelectedEmployed(employed.getEmail());
                      this.parentController.openEmplyedProfile();
                  });
                  employedtable.getChildren().add(row);
                }                  
              }catch(Exception ex){
                  
              }

          });
    }
}
