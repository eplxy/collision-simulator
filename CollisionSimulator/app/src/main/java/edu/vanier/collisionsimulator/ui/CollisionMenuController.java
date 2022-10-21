/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author sabri
 */
public class CollisionMenuController {
    @FXML
    Pane animationPane;
    @FXML
    Button saveBtn;
    @FXML
    Button wtvBtn;
     
    
    public void initialize(ArrayList<Shape> nodesList){
        animationPane.getChildren().addAll(nodesList);
        animationPane.setOnMouseClicked((event)->{
            System.out.println("dragged mouse over pane!");});
        saveBtn.setOnAction((event)->{
            System.out.println("it works!");}
        );
        wtvBtn.setOnAction((event)->{
            System.out.println("it works!");}
        );
        
       
    }
}
