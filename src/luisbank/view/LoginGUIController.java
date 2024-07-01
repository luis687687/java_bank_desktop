/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import luisbank.Core.Controller.Software;
import luisbank.LuisBank;

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class LoginGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML  private BorderPane main;
    @FXML private AnchorPane btntheme;
    @FXML private Button btnlogin;
    @FXML private PasswordField inputpass;
    @FXML private TextField inputemail;
    @FXML private Label loginsms;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        
        btntheme.setOnMouseClicked( event -> {
            Manipulations.isLight = !Manipulations.isLight;
            if(!(new Manipulations()).changeTheme(main)){
                btntheme.getStyleClass().remove("btn-sun");
                btntheme.getStyleClass().add("btn-moon"); 
            }else{
                btntheme.getStyleClass().remove("btn-moon");
                btntheme.getStyleClass().add("btn-sun");
            }
        });
        
        btnlogin.setOnMouseClicked(event -> {
            String email = inputemail.getText();
            String password = inputpass.getText();
            
            System.out.println("kk "+email+" e "+password);
            if(Software.login(email, password)){
                loginsms.setText("Bem sucedido!");
                loginsms.setStyle("-fx-text-fill: syscolor");
                BorderPane main = ((BorderPane)LuisBank.mainElement);
                (new Manipulations()).changeTheme(main);
                main.getChildren().clear();
                main.setCenter(LuisBank.centerElment);
                main.setLeft(LuisBank.sideBar);
                LuisBank.mainController.loadTable();
                luisbank.LuisBank.mainController.getNameTop().setText(Software.getLoggedEmployed().getName());
            }else{
               loginsms.setText("Dados inválidos !");
                loginsms.setStyle("-fx-text-fill: red");
            }
            loginsms.visibleProperty().set(true);
            
        });
    }
    
    
    
}
