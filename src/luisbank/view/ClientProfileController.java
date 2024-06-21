/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import luisbank.Core.Controller.Agency;
import luisbank.Core.Controller.IClient;
import luisbank.Core.Controller.Software;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import luisbank.Core.Controller.Moviment;
import luisbank.Core.Controller.PairClientAgency;
import luisbank.Core.Controller.PersonSingular;
/**
 *
 * @author CyberPunk
 */
public class ClientProfileController implements Initializable{
    
    @FXML private VBox columnclients, textarea, tablebody;
    @FXML private TextField inputvalor, inputdestino, inputname, inputbi, inputiban, inputcontact1, inputcontact2, inputagency;
    @FXML private Label name, saldo, iban;
    @FXML private HBox btndeposita, btnlevanta, btncredito, btntransfere;
    
    public PairClientAgency selected_client;
    
    public void initialize(URL url, ResourceBundle rb){
        
        showAllClients();
        
        btndeposita.setOnMouseClicked(event -> { 
            if(Software.getActualAgency().getSelectedClient() == null){
                System.out.println("Sem user");
                return;
            }
            
            double valor = Double.parseDouble(inputvalor.getText());
            Software.getActualAgency().getSelectedClient().getAccount().depositMoney(valor);
            showClient();
            
            
        });
        btnlevanta.setOnMouseClicked(event -> {
             if(Software.getActualAgency().getSelectedClient() == null){
                System.out.println("Sem user");
                return;
            }
            double valor = Double.parseDouble(inputvalor.getText());
            Software.getActualAgency().getSelectedClient().getAccount().removeMoney(valor);
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
                    
            Software.transfereMoney(inputdestino.getText(), Double.parseDouble(inputvalor.getText())); //use internamente actual_agency e o selectedClient
            showClient();
                
           
            
        });
    }
    public void showAllClients(){
        
        try{
            
            for(Agency agency : Software.getAgencies().values()){

                for(IClient client : agency.getClients().values()){
                    FXMLLoader load = new FXMLLoader(getClass().getResource("UserRow.fxml"));
                    Parent element = load.load();
                    UserRowController controller = load.getController();
                    
                    controller.setName(client.getName());
                    controller.setIban(client.getAccount().getIban());
                    controller.getArea().setOnMouseClicked(event -> {
                        selected_client = new PairClientAgency(client, agency);
                        setOneClient(selected_client);
                    });
                    
                    String clienttype = "Empresa: ";
                    if(client instanceof PersonSingular)
                        clienttype = "Pessoa singular: ";
                    controller.setCode(clienttype+client.getCode());
                    
                    columnclients.getChildren().add(element);
                    
                }
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
        if(Software.getActualAgency().getSelectedClient() == null)
            return;
        
        name.setText(Software.getActualAgency().getSelectedClient().getName());
        inputname.setText(Software.getActualAgency().getSelectedClient().getName());
        inputagency.setText(Software.getActualAgency().getCode());
        inputiban.setText(Software.getActualAgency().getSelectedClient().getAccount().getIban());
        inputbi.setText(Software.getActualAgency().getSelectedClient().getCode());
        saldo.setText(Manipulations.formatValue(Double.toString(Software.getActualAgency().getSelectedClient().getAccount().getMoney())));
        iban.setText(Software.getActualAgency().getSelectedClient().getAccount().getIban());
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