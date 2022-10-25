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
public class CircleObj extends CollisionObject{

        private double radius;

    
    //default circle object for animation
    public CircleObj(){
        super();
        this.node = new Circle(50);
        this.radius = 50;
        this.height = radius;
        this.width = radius;
        this.mass = 5;
        
    }
     //default circle object for ui testing
    public CircleObj(CollisionMenuController cmc) throws IOException {
        super(cmc);
        this.node = new Circle(50);
        this.radius = 50;
        this.height = radius;
        this.width = radius;
        setMouseListener(cmc);
        cmc.addNode(node);
    }

    private void setMouseListener(CollisionMenuController cmc){
        this.node.setOnMouseClicked((MouseEvent mouseEvent) -> {
            cmc.getParametersPane().getChildren().setAll(parameters);
        });
    } 
    
}
