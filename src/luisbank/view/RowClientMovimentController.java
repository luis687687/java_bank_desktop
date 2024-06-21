/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package luisbank.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author CyberPunk
 */
public class RowClientMovimentController implements Initializable {
    @FXML private Label tipo, start, end, origin, destin, value, finalvalue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public Label getType(){ return tipo; }
    public Label getStarts(){ return start; }
    public Label getEnds(){ return end; }
    public Label getOrigin(){ return origin; }
    public Label getDestination(){ return destin; }
    public Label getValue(){ return value; }
    public Label getFinalValue(){ return finalvalue; }
    
}
