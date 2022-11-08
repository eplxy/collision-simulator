/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author sabri
 */
public class ParametersController {
    CollisionObject obj;
    CollisionMenuController cmc;
    @FXML
    TextField massTxtField;
    TextField speedTxtField;
    TextField posXTxtField;
    TextField posYTxtField;
    
//    double massInput;
//    double speedInput;
//    double posXInput;
//    double posYInput;
    @FXML
    Button btnEnter;

    public ParametersController(CollisionObject obj, CollisionMenuController cmc) {
        this.obj = obj;
        this.cmc = cmc;
    }
    @FXML
    public  void initialize() {
         btnEnter.setOnAction((event) -> {
            handleEnter(event);
        });
    }      
    
    @FXML
    public void handleEnter(ActionEvent event){
        //obj.setMass(Double.parseDouble(massTxtField.getText()));
        //obj.setPosY(Double.parseDouble(posYTxtField.getText()));
        System.out.println("hehe");
        obj.getShape().setLayoutX(20); 
        //obj.getShape().setLayoutX(Double.parseDouble(posXTxtField.getText()));
    }
    @FXML
    public TextField getMassTxtField() {
        return massTxtField;
    }
    @FXML
    public void setMassTxtField(TextField massTxtField) {
        this.massTxtField = massTxtField;
    }
    @FXML
    public TextField getSpeedTxtField() {
        return speedTxtField;
    }
    @FXML
    public void setSpeedTxtField(TextField speedTxtField) {
        this.speedTxtField = speedTxtField;
    }
    @FXML
    public TextField getPosXTxtField() {
        return posXTxtField;
    }

    public void setPosXTxtField(TextField posXTxtField) {
        this.posXTxtField = posXTxtField;
    }

    public TextField getPosYTxtField() {
        return posYTxtField;
    }

    public void setPosYTxtField(TextField posYTxtField) {
        this.posYTxtField = posYTxtField;
    }
    
}
