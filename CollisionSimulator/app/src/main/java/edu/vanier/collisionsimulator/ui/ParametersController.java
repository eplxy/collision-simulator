/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author sabri
 */
public class ParametersController {
    
    CollisionObject obj;
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

    public ParametersController(CollisionObject obj) {
        this.obj = obj;
    }
    
    public  void initialize() {
       btnEnter.setOnAction((event)->{
           obj.setMass(Double.parseDouble(massTxtField.getText()));
           //obj.setVelocityX(Double.parseDouble(speedTxtField.getText()));
           //obj.setVelocityY(massInput);
           obj.setPosX(Double.parseDouble(posXTxtField.getText()));
           obj.setPosY(Double.parseDouble(posYTxtField.getText()));
           System.out.println(obj.getMass());
       });
    }      

    public TextField getMassTxtField() {
        return massTxtField;
    }

    public void setMassTxtField(TextField massTxtField) {
        this.massTxtField = massTxtField;
    }

    public TextField getSpeedTxtField() {
        return speedTxtField;
    }

    public void setSpeedTxtField(TextField speedTxtField) {
        this.speedTxtField = speedTxtField;
    }

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
