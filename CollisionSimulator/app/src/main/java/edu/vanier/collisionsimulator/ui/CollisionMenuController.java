/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.controllers.CollisionController;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class CollisionMenuController {

    Stage primaryStage;

    //Stage primaryStage;
    public CollisionMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    ;

    
    
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
    @FXML
    Slider timelineSlider;
    @FXML
    Slider sliderFriction;
    @FXML
    ToggleButton btnToggleVisVector;
    Simulation sim;
    @FXML
    Button btnReturnMenu;
    @FXML
    CheckBox checkBoxShowDirection;
    @FXML
    CheckBox checkBoing;

    public void initialize(Simulation sim) throws IOException {
        this.sim = sim;
        sim.setAnimationPane(animationPane);

        animationPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            recenterObjects();
        });
        animationPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            recenterObjects();
        });

        timelineSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            handleTimeline(sim, timelineSlider.getValue());
        });

        sliderFriction.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateFriction(sim, sliderFriction.getValue());
        });

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
        checkBoxShowDirection.setOnAction((event) -> {
            if (checkBoxShowDirection.isSelected()) {
                sim.getCom().getAllColObjs().forEach((t) -> {
                    animationPane.getChildren().add(t.getVv().getVisVector());
                });
            } else {
                sim.getCom().getAllColObjs().forEach((t) -> {
                    animationPane.getChildren().remove(t.getVv().getVisVector());
                });
            }
        });

        checkBoing.setOnAction((event) -> {
            if (checkBoing.isSelected()) {
                CollisionController.boingEnabled = true;
            } else {
                CollisionController.boingEnabled = false;
            }
        });

        btnReturnMenu.setOnAction((event) -> {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
                MainMenuController menuController = new MainMenuController(primaryStage);
                loader.setController(menuController);

                BorderPane root = loader.load();

                Scene scene = new Scene(root);

                sim.loop.stop();
                primaryStage.close();
                primaryStage.setScene(scene);
                primaryStage.setMaximized(true);

                primaryStage.show();

            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public void handleSave(ActionEvent event) {
    }

    private void handleTimeline(Simulation sim, double value) {
        sim.setFrameRate(60 * value);
    }

    private void handlePlay(ActionEvent event, Simulation sim) {
        sim.loop.play();
    }

    private void handlePause(ActionEvent event, Simulation sim) {
        sim.loop.pause();
    }

    public void handleAddObj(ActionEvent event, Simulation sim) throws IOException {
        sim.addObject(this, animationPane);

    }

    public boolean getBoing() {
        return checkBoing.isSelected();
    }

    public void updateFriction(Simulation sim, double friction) {
        sim.setFriction(friction);
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

    private void recenterObjects() {
        final Bounds bounds = animationPane.getLayoutBounds();
        double paneWidth = animationPane.getWidth();
        //double paneWidth = bounds.getMaxX();
        double paneHeight = animationPane.getHeight();
        //double paneHeight = bounds.getMaxY();

        for (CollisionObject obj : sim.com.getAllColObjs()) {
            if (obj.getPosX() + obj.getWidth() > paneWidth) {
                obj.setPosX(paneWidth - 2 * obj.getWidth());
            }
            if (obj.getPosY() + obj.getHeight() > paneHeight) {
                obj.setPosY(paneHeight - 2 * obj.getHeight());
            }
        }

    }

}
