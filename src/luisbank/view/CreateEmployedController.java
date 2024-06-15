/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luisbank.view;

import luisbank.Core.Controller.Employed;
import luisbank.Core.Controller.Agency;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import luisbank.Core.Controller.Software;

/**
 *
 * @author CyberPunk
 */
public class CreateEmployedController implements Initializable {
    
    @FXML 
    private TextField inputemail, 
           
            inputname, inputlocal, 
            inputcode, inputcontact,
            inputcontact2;
    @FXML private ComboBox<String> inputagency;
    @FXML private PasswordField  inputpass;
    @FXML private DatePicker inputdate;
    @FXML private HBox btnsave;
    @FXML private Label smserror;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        new Software();
        loadAgencyList();
        
        
        inputagency.setOnAction(event -> {
            String code = inputagency.getValue();
            Software.setActualAgency(code);
        });
        btnsave.setOnMouseClicked(event -> {
            create();
        });
        
        
    }
    
    private void loadAgencyList(){
        for(Agency agency : Software.getAgencies().values()){
            System.out.println(agency.getCode()+"luis");
            inputagency.getItems().add(agency.getCode());
        }

    }
    
    public void create(){
        Agency agency = Software.getActualAgency();
        String email = inputemail.getText();
        String password = inputpass.getText();
        String phone = inputcontact.getText();
        String optionalphone = inputcontact2.getText();
        
        if(agency != null){
            
            Employed empl = new Employed(email, password, phone, optionalphone);
            if(Software.actualAgencyAppendEmployed(empl)){
                System.out.println("Sucesso!");
                
            }
            else{
                smserror.setText("Erro, o e-mail inserido já existe!");
            }
        }
    }

}
