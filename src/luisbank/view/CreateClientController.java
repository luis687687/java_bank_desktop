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
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import luisbank.Core.Controller.Account;
import luisbank.Core.Controller.AccountFinancy;
import luisbank.Core.Controller.BI;
import luisbank.Core.Controller.Comertial;
import luisbank.Core.Controller.IClient;
import luisbank.Core.Controller.PersonColective;
import luisbank.Core.Controller.PersonSingular;
import luisbank.Core.Controller.Software;


/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class CreateClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private HBox btnimg, btnsave1;
    @FXML private AnchorPane img, imgarea;
    @FXML private BorderPane main;
    @FXML private ComboBox<String> comboaccounts, comboclients;
    @FXML private Label title, labelclient, labelcode, labeldate, labellocal, sms;
    
    @FXML private TextField inputname1, inputcode1, inputlocal1, inputcontact1, inputcontact2;
    @FXML private DatePicker inputdate1;
    
    private String[] accounts = {"Conta poupança", "Conta crédito"};
    private String[] clients = {"Pessoa singular", "Pessoa Colectiva"};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         comboaccounts.getItems().addAll(accounts);
         comboclients.getItems().addAll(clients);
         comboclients.setValue(clients[0]);
        
        comboclients.setOnAction(event -> { 
            if(comboclients.getValue().equals(clients[0])){
                title.setText("Criar conta Bancária de Pessoa Singular");
                labelclient.setText("DADOS DO BI");
                labelcode.setText("Nº BI");
                labeldate.setText("Data de nascimento");
                labellocal.setText("Local");
                imgarea.setVisible(true);
            }else{
                if(comboclients.getValue().equals(clients[1])){
                    title.setText("Criar conta Bancária para Empresa");
                    labelclient.setText("DADOS DO COMERCIAL");
                    labelcode.setText("Nº COMERCIAL");
                    labeldate.setText("Data de criação");
                    labellocal.setText("Local");
                    imgarea.setVisible(false);
                }                
            }
        });
        
        btnsave1.setOnMouseClicked( event -> {
            sms.setText("");
           String name = inputname1.getText();
            LocalDate date = inputdate1.getValue();
            
           String local = inputlocal1.getText();
           String phone1 = inputcode1.getText();
           String phone2 = inputcontact2.getText();
           String code = inputcode1.getText();
           String accounttype = comboaccounts.getValue();
           
           String clienttype = comboclients.getValue();
           
           if(name.isEmpty()){
               sms.setText("ERRO: defina o nome do cliente");
               return;
           }

           if(local.isEmpty()){
               sms.setText("ERRO: defina o local");
               return;
           }           
           if(clienttype == null){
               sms.setText("ERRO: defina o tipo de cliente");
               return;
           }
           if(date == null){
               sms.setText("ERRO: defina uma data");
               return;
           }
           if(2024 - date.getYear() < 18){
                sms.setText("Erro: deve ter no mínimo 18 anos de idade");
                return;
            }
               
           BI bi = new BI(name, code, local, date.toString());
           
           if(!bi.valide()){
               
               sms.setText("ERRO: BI inválido");
               return;
           }
           if(accounttype == null ){
               sms.setText("ERRO: defina o tipo de conta");
               return;
           }
           
           Account account = new Account();
           if(accounttype.equals(accounts[0]))
               account = new AccountFinancy();
            
            IClient client;
            if(clienttype.equals(clients[0]))
                client = new PersonSingular(account, bi, phone1, phone2);
            else{
                System.out.println("Coleeeeeccccttttiva");
                Comertial comertial = new Comertial(code, name, phone1, phone2);
                client = new PersonColective(account, comertial);
            }
                
           
            
            Software.actualLoggedEmployedAgencyAppendClient(client);
            System.out.println(Software.getActualAgency().getClients());
            System.out.println("Poooooo");
            System.out.println(clienttype);
            System.out.println("Poooooo");
        });
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
