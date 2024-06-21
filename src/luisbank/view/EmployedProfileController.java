/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import luisbank.Core.Controller.Agency;
import luisbank.Core.Controller.Employed;
import luisbank.Core.Controller.Software;

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class EmployedProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label name, email, labelsmspass, labelsmsagency, labelsmssucess;
    @FXML private TextField inputname, inputagency, inputcontact1, inputcontact2, inputlocal;
    @FXML private ComboBox<String> comboagency;
    @FXML private HBox btntransfere, btnchangedata, btnpass;
    @FXML private PasswordField pass1, pass;
    @FXML private AnchorPane btnback;
    
    public Employed emp;
    private GUIController parentController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(Software.getActualAgency().getSelectEmployed());
        System.out.println("Luis Marquesfdsfdsf");
        
        emp = Software.getActualAgency().getSelectEmployed();
        name.setText(emp.getName());
        email.setText(emp.getEmail());
        inputname.setText(name.getText());
        inputagency.setText(Software.getActualAgency().getCode());
        inputcontact1.setText(emp.getPhone());
        inputcontact2.setText(emp.getPhone2());
        
        btnback.setOnMouseClicked(event -> { 
            gotoBack();
        });
        loadagencies();
        btntransfere.setOnMouseClicked(event -> {
            String agencycode = comboagency.getValue();
            Software.transfereEmployed(email.getText(), Software.getActualAgency(), Software.getAgency(agencycode));
            inputagency.setText(agencycode);
            labelsmsagency.setText("Funcionário "+emp.getName()+" transferido com sucesso!");
            Software.saveAgencyState();
        } ); 
        
        btnchangedata.setOnMouseClicked(event -> {
            
            emp.setName(inputname.getText());
            name.setText(emp.getName());
            emp.setPhone(inputcontact1.getText());
            emp.setPhone2(inputcontact2.getText());
            Software.saveAgencyState();
            labelsmssucess.setVisible(true);
        });
        
        btnpass.setOnMouseClicked( event -> {
            if(!Software.isAdmin())
                if(pass1.getText().isEmpty()){
                    labelsmspass.setText("Não es um administrador de sistema! Deves confirmar a senha antiga, se esqueceu, peça ajuda ao administrador");
                    return;
                }
                else
                    if(!pass1.getText().equals(emp.getPassword())){
                        labelsmspass.setText("A senha inserida não corresponde a senha da conta, por favor contacta o administrador caso esqueça");
                        return;
                    }
            emp.setPassword(pass.getText());
            labelsmspass.setText("Senha alterada com sucesso!");
            Software.saveAgencyState();
        } );
    }    
    
    public void loadagencies(){
        for(Agency agency : Software.getAgencies().values()){
            if(!Software.getActualAgency().getCode().equals(agency.getCode()))
                comboagency.getItems().add(agency.getCode());
        }
    }
    
    public void setParent(GUIController parentController){
        this.parentController = parentController;
    }
    public void gotoBack(){
        this.parentController.openScene("listagency");
    }
}
