/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import edu.vanier.collisionsimulator.ui.ParametersController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author sabri
 */
public class CircleObj extends Circle{

    private double speed;
    private double mass;
    private AnchorPane parameters;
    
    ParametersController parametersController = new ParametersController();

    public CircleObj(double speed, double mass, double d, double d1, double d2, CollisionMenuController cmc) throws IOException {
        super(d, d1, d2);
        this.speed = speed;
        this.mass = mass;
        setMouseListener(this, cmc);
        this.parameters = createParametersPane();
        cmc.addShape(this);
        
    }

    private void setMouseListener(CircleObj circle, CollisionMenuController cmc){
        circle.setOnMouseClicked((MouseEvent mouseEvent) -> {
            System.out.println("You clicked circle");
            cmc.getParametersPane().getChildren().setAll(parameters);
            
        });
    } 
    
    private  AnchorPane createParametersPane() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
        loader.setController(new ParametersController());
        AnchorPane parameters = loader.load(); 
        return parameters;
    }
    
    private void display (AnchorPane parameters){
        
    }

    public AnchorPane getParameters() {
        return parameters;
    }

    public void setParameters(AnchorPane parameters) {
        this.parameters = parameters;
    }

    public ParametersController getParametersController() {
        return parametersController;
    }

    public void setParametersController(ParametersController parametersController) {
        this.parametersController = parametersController;
    }
    
    


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
}
