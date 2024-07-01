/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import luisbank.Core.Controller.Agency;
import luisbank.Core.Controller.IClient;
import luisbank.Core.Controller.Software;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import luisbank.Core.Controller.AccountFinancy;
import luisbank.Core.Controller.Admin;
import luisbank.Core.Controller.Configurations;
import luisbank.Core.Controller.Employed;
import luisbank.Core.Controller.Moviment;
import luisbank.Core.Controller.PairClientAgency;
import luisbank.Core.Controller.Person;
import luisbank.Core.Controller.PersonSingular;
/**
 *
 * @author CyberPunk
 */
public class ClientProfileController implements Initializable{
    
    @FXML private VBox columnclients, textarea, tablebody;
    @FXML private TextField inputvalor, inputdestino, inputname, inputbi, inputsearch, inputcontact1, inputcontact2, inputagency;
    @FXML private Label name, saldo, iban, btnchoice, labeldivida, timecont, timeconttransference, labellimit;
    @FXML private HBox btndeposita, btnlevanta, btncredito, btntransfere,
            btnaccountstatus, btncardstatus, btnaddcheck, btnchangedata;
    @FXML private AnchorPane btnsearch, arealimit, btneye, addfinanceiroarea;
    @FXML private ComboBox<String> combofinanceiro;
    
    public PairClientAgency selected_client;
    public boolean choosedActualAgency = false;
    private boolean eyeopen = false;
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        if(Software.getActualAgency() == null)
            return;
        if(Software.getActualAgency().getSelectedClient() != null){
            timecont.setText(Software.getActualAgency().getSelectedClient().getAccount().getTimeToPermit());
            timeconttransference.setText(Software.getActualAgency().getSelectedClient().getAccount().getLeftTimeTransference());
        
            timecont.setStyle(timecont.getText().contains("h") 
                    ? "-fx-text-fill: red" : "-fx-text-fill: colortext");
        }
           
        
    }));
            
    public void initialize(URL url, ResourceBundle rb){
        
        showAllClients("");
        
        btndeposita.setOnMouseClicked(event -> { 
            if(Software.getActualAgency().getSelectedClient() == null){
                System.out.println("Sem user");
                return;
            }
            IClient client = Software.getActualAgency().getSelectedClient();
            if(!client.getAccount().isActive()){
                return;
            }

            double valor = Double.parseDouble(inputvalor.getText());
            Software.getActualAgency().depositMoney(valor);
            showClient();



        });
        btnlevanta.setOnMouseClicked(event -> {
             if(Software.getActualAgency().getSelectedClient() == null){
                System.out.println("Sem user");
                return;
            }
           IClient client = Software.getActualAgency().getSelectedClient();
            if(!client.getAccount().isActive()){
                return;
            }
            double valor = Double.parseDouble(inputvalor.getText());
            Software.getActualAgency().removeMoney(valor);
            showClient();

        });
        btntransfere.setOnMouseClicked(event -> {
            System.out.println("Clicccc");
            if(Software.getActualAgency().getSelectedClient() == null){
                   System.out.println("Sem user");
                   return;
            }
            if(Software.isSelectedClientIbanOrCode(inputdestino.getText()))
                return;
            IClient client = Software.getActualAgency().getSelectedClient();
            if(!client.getAccount().isActive()){
                return;
            }     
            Software.transfereMoney(inputdestino.getText(), Double.parseDouble(inputvalor.getText())); //use internamente actual_agency e o selectedClient
            showClient();
                
           
            
        });
        btnchoice.setOnMouseClicked(event -> {
            choosedActualAgency = !choosedActualAgency;
            if(choosedActualAgency){
                btnchoice.getStyleClass().add("btn-choosed");
            }else{
                btnchoice.getStyleClass().remove("btn-choosed");
            }
            showAllClients(inputsearch.getText());
             showClient();
            
        });
        btnaccountstatus.setOnMouseClicked(event -> { 
           IClient client = Software.getActualAgency().getSelectedClient();
           if(client == null){
               return;
           }
           client.getAccount().setActive(!client.getAccount().isActive() );
           showClient();
           
       });
        btneye.setOnMouseClicked(event -> {
            this.setOpenEye(!this.isEyeOpened());
            btneye.getStyleClass().clear();
            btneye.getStyleClass().add(isEyeOpened() ? "icon-eye" : "icon-eye-closed");
            showClient();
        });
        
        btnchangedata.setOnMouseClicked(event -> { 
            String financeiro = combofinanceiro.getValue();
            if(!financeiro.isEmpty()){
                Software.getActualAgency().getEmployed(financeiro).setClientsChildren(Software.getActualAgency().getSelectedClient());
            }
        });
       
        btnaddcheck.setOnMouseClicked(event -> {
            Software.getActualAgency().setSelectedEmployedCheck(Double.parseDouble(inputvalor.getText()));
        });
       btncardstatus.setOnMouseClicked(event -> { 
           IClient client = Software.getActualAgency().getSelectedClient();
           if(client == null){
               return;
           }

            if(!client.getAccount().isActive()){
                return;
            }
           client.getAccount().setCard(!client.getAccount().hasCard());
           showClient();
           
       });
       btncredito.setOnMouseClicked(event -> {
           IClient client = Software.getActualAgency().getSelectedClient();
           if(client == null){
               return;
           }

            if(!client.getAccount().isActive()){
                return;
            }
            client.getAccount().setCredite(Double.parseDouble(inputvalor.getText()));
            showClient();
       });
        
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        inputsearch.setOnKeyReleased(   event -> { 
            System.out.println("IIIIIIIIIIIIIIIIIIIII   "+event);
            
            String searchtext = inputsearch.getText();
           
            showAllClients(searchtext);
        });
        
      
        
    }
    
    public void setOpenEye(boolean open){
        this.eyeopen = open;
    }
    public boolean isEyeOpened(){
        return this.eyeopen;
    }
    public void showAllClients(String searchtext){
         String agencysearch = choosedActualAgency ? Software.getLoggedEmployedAgency().getCode() : "";
        ArrayList<PairClientAgency> pairs = Software.searchClients(searchtext, agencysearch);
        try{
             columnclients.getChildren().clear();
            for(PairClientAgency pair : pairs){
                Agency agency = pair.agency;
                IClient client = pair.client;
                FXMLLoader load = new FXMLLoader(getClass().getResource("UserRow.fxml"));
                Parent element = load.load();
                UserRowController controller = load.getController();
                controller.setName(client.getName());
                controller.setIban(client.getAccount().getIban());
                controller.getArea().setOnMouseClicked(event -> {
                    setOneClient(new PairClientAgency(client, agency));
                    
                });

                String clienttype = "Empresa: ";
                if(client instanceof PersonSingular)
                    clienttype = "Pessoa singular: ";
                controller.setCode(clienttype+client.getCode());

                columnclients.getChildren().add(element);

                
            }
            
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }
    
    public void setOneClient(PairClientAgency elemento){
        if(elemento == null)
            return;
        IClient client = elemento.client;
        Agency agency = elemento.agency;
        Software.setActualAgency(agency.getCode());
        Software.getActualAgency().setSelectedClient(client.getCode());
        showClient();
        
        
    }
    
    public void showClient(){
        IClient client = Software.getActualAgency().getSelectedClient();
        if(Software.getActualAgency().getSelectedClient() == null)
            return;
        boolean isAccountActive = client.getAccount().isActive();
        boolean hasCard = Software.getActualAgency().getSelectedClient().getAccount().hasCard();
        
        btnaccountstatus.getStyleClass().clear();
        btncardstatus.getStyleClass().clear();
        
        btnaccountstatus.getStyleClass().add( isAccountActive ? "btn-red" : "btn-default");
        ((Label)btnaccountstatus.getChildren().get(0)).setText(isAccountActive ? "Cancelar conta" : "Activar");
        
        
        boolean isAccountFinancy = client.getAccount() instanceof AccountFinancy;
        boolean isAgencyOfClient = Software.getLoggedEmployedAgency().getCode().equals(Software.getActualAgency().getCode());
        
        btncardstatus.getStyleClass().add( (isAccountFinancy || hasCard) ? "btn-transparent-red" : "btn-transparent");
        ((Label)btncardstatus.getChildren().get(0)).setText(hasCard ? "Cancelar cartão" : "Fornecer Cartão");
        name.setText(Software.getActualAgency().getSelectedClient().getName());
        labeldivida.setText("Dívida actual: AKZ "+Manipulations.formatValue(Double.toString(client.getAccount().getCredite())));
        if(isAccountFinancy)
            ((Label)btncardstatus.getChildren().get(0)).setText("INDESPONÍVEL");
        if(isAccountFinancy)
            btncredito.setVisible(false);
        else
            btncredito.setVisible(true);
       
        if(!isAgencyOfClient && !hasCard){
            btncardstatus.setVisible(false);
        }else{
            btncardstatus.setVisible(true);
        }
        inputagency.setText(Software.getActualAgency().getCode());
        
        inputbi.setText(Software.getActualAgency().getSelectedClient().getCode());
        saldo.setText(!isEyeOpened() ? " ******* " : Manipulations.formatMoney(Double.toString(Software.getActualAgency().getSelectedClient().getAccount().getMoney())));
        iban.setText(Software.getActualAgency().getSelectedClient().getAccount().getIban());
        
        
        Agency clientagency = Software.getActualAgency();
        combofinanceiro.getItems().clear();
        for(Person emplyed : clientagency.getEmplyeds().values()){
            Employed employed1 = (Employed)emplyed;
            combofinanceiro.getItems().add(employed1.getEmail());
        }
        //verificar se pode ou nar adicionar financeiro
        if(! (Software.getLoggedEmployed() instanceof Admin) )
            addfinanceiroarea.setVisible(false);
        else
            addfinanceiroarea.setVisible(true);
        //verificar se é financeiro da conta
        boolean isItClientChildren = false;
        for(IClient children : Software.getLoggedEmployed().getClientsChildren().values()){
            if(children.getCode().equals(client.getCode()))
            {
                isItClientChildren = true;
                break;
            }
        }
        btnaddcheck.setVisible(isItClientChildren);
        loadTableBody();
    }
    
    public void loadTableBody(){
        
        try{
        if(Software.getActualAgency().getSelectedClient() == null)
            return;
        
        tablebody.getChildren().clear();
        for(Moviment moviment : Software.getActualAgency().getSelectedClient().getAccount().getMoviments()){
            String type = moviment.getType();
            double value = moviment.getValue();
            double finalvalue = moviment.getFinalMoney();
            String origin = moviment.getTransfereOrigin();
            String destination = moviment.getTransfereDestin();
            String starts = moviment.getNormalDateStart();
            String ends = moviment.getNormalDateEnd();
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RowClientMoviment.fxml"));
            Parent parent = loader.load();
            RowClientMovimentController controller = loader.getController();
           
            
            controller.getType().setText(type);
            controller.getEnds().setText(ends);
            controller.getStarts().setText(starts);
            controller.getDestination().setText(destination == null ? " -- " : destination);
            controller.getOrigin().setText(origin == null ? " -- " : origin);
            
            if(value < 0){
                controller.getValue().setStyle("-fx-text-fill: #F30000;");
                controller.getValue().setText(Manipulations.formatValue( Double.toString(value)));    
            }else{
                controller.getValue().setStyle("-fx-text-fill: #239443;");
                controller.getValue().setText("+"+Manipulations.formatValue( Double.toString(value)));
            }
            controller.getFinalValue().setText(Manipulations.formatValue(Double.toString(finalvalue)));
            controller.getFinalValue().setStyle("-fx-text-fill: syscolor");
           
            tablebody.getChildren().add(parent);
            
        }
       
        
        }
         catch(Exception ex){
             System.out.println("Função load table clientt moviment "+ex);     
          }
        
        
    }
}
