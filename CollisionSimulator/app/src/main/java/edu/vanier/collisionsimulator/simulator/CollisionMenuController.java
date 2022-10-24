/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CircleObj;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

/**
 *
 * @author sabri
 */
public class CollisionMenuController {
    @FXML
    private Pane animationPane;
    @FXML
    private AnchorPane parametersPane;
    @FXML
    private VBox parametersVBox; 
    @FXML
    private ChoiceBox objectChoice;
    
    private static ObservableList<String> objectChoices = FXCollections.observableArrayList();

    public static ObservableList<String> getObjectChoices(){
        return objectChoices;
    }

    public static void setObjectChoices(ObservableList<String> newObjectChoices){
       objectChoices = newObjectChoices;
    }
    
    public void initialize(ArrayList<Shape> nodesList) throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
//        loader.setController(new ParametersController());
//        parametersVBox.getChildren().add(loader.load());
        
        animationPane.getChildren().addAll(nodesList);
        
        
    }   
    
    public void addShape(Shape shape) {
        animationPane.getChildren().add(shape);
    }
    

    public Pane getAnimationPane() {
        return animationPane;
    }

    public void setAnimationPane(Pane animationPane) {
        this.animationPane = animationPane;
    }

    public AnchorPane getParametersPane() {
        return parametersPane;
    }

    public void setParametersPane(AnchorPane parametersPane) {
        this.parametersPane = parametersPane;
    }

    public VBox getParametersVBox() {
        return parametersVBox;
    }

    public void setParametersVBox(VBox parametersVBox) {
        this.parametersVBox = parametersVBox;
    }

    public ChoiceBox getObjectChoice() {
        return objectChoice;
    }

    public void setObjectChoice(ChoiceBox objectChoice) {
        this.objectChoice = objectChoice;
    }

    
    
    
}
