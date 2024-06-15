/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class CreateSingularController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private HBox btnimg;
    @FXML private AnchorPane img;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnimg.setOnMouseClicked( event -> {
            FileChooser filer = new FileChooser();
           
            filer.setTitle("Escolha uma imagem");
            filer.getExtensionFilters().add(new ExtensionFilter("Ficheiros de imagem", "*.png"));
            filer.getExtensionFilters().add(new ExtensionFilter("Ficheiros de imagem", "*.jpg"));
            
            File selec =  filer.showOpenDialog(null);
            if(selec != null){
                
                
                img.getStyleClass().clear();
                String path = selec.toString().replace("\\","/");
                String stringstyle = "-fx-background-image: url('file:/"
                        +path+"')";
                img.getStyleClass().add("avatar-image");
                img.setStyle(stringstyle);
                
                System.out.println("Printed... "+stringstyle);
            }
            
        });
    }    
    
}
