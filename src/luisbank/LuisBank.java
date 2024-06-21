/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package luisbank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import luisbank.Core.Controller.Software;
import luisbank.view.GUIController;

/**
 *
 * @author CyberPunk
 */
public class LuisBank extends Application {
    
    private static Stage mainStage;
    private static Scene scene2;
    private static Scene mainScene;
 
    public static Node centerElment;
    public static Node mainElement;
    public static Node sideBar;
    public static GUIController mainController;
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
     
      
      Parent elemento = FXMLLoader.load(super.getClass().getResource("view/MainGUI.fxml"));
      
      new Software();
      mainScene = new Scene(elemento);
      mainStage = stage;
      mainStage.setScene(mainScene);
      mainStage.initStyle(StageStyle.TRANSPARENT);
      mainStage.show();
       
       
      
      
    }
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
