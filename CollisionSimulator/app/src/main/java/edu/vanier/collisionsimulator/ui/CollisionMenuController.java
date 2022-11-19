/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.AnimationPane;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
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

    public void initialize(Simulation sim) throws IOException {
        sim.setAnimationPane(animationPane);
        sim.createRandomObjects2(sim.numberOfObj, sim.cmc, sim.animationPane);
        for (CollisionObject obj : sim.com.getAllColObjs()) {
            obj.setMouseListener(this);
            obj.setDragListeners(this);
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

}
