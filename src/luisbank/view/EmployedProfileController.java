/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(Software.getActualAgency().getSelectEmployed());
        System.out.println("Luis Marquesfdsfdsf");
    }    
    
}
