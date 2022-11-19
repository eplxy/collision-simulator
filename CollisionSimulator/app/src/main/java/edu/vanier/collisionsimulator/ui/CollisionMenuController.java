/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.AnimationPane;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    Button btnSave;
    @FXML
    Button btnPlay;
    @FXML
    Button btnPause;
    @FXML
    Button btnAddObj;
    
    Simulation sim;

    public void initialize(Simulation sim) throws IOException {
        this.sim = sim;
        sim.setAnimationPane(animationPane);
        sim.createRandomObjects2(sim.numberOfObj, sim.cmc, sim.animationPane);
        for (CollisionObject obj : sim.com.getAllColObjs()) {
            //obj.setMouseListener(this);
            //obj.setDragListeners(this);
        }

        btnSave.setOnAction((event) -> {
            handleSave(event);
        });
        btnPlay.setOnAction((event) -> {
            handlePlay(event, sim);
        });
        btnPause.setOnAction((event) -> {
            handlePause(event, sim);
        });
        btnAddObj.setOnAction((event) -> {
            try {
                handleAddObj(event, sim);
            } catch (IOException ex) {
                Logger.getLogger(CollisionMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void handleSave(ActionEvent event) {
        System.out.println("save pressed");
    }

    private void handlePlay(ActionEvent event, Simulation sim) {
        sim.loop.play();
    }

    private void handlePause(ActionEvent event, Simulation sim) {
        sim.loop.pause();
    }
    
    public void handleAddObj(ActionEvent event, Simulation sim) throws IOException {
        sim.createRandomObjects2(1, this, animationPane);
        
    }

    public void updateParameters() {

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

    public Simulation getSim() {
        return sim;
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
    }
    
    

}
