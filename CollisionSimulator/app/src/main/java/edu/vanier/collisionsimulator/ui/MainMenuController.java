/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author 2156586
 */
public class MainMenuController {
    Stage primaryStage;

    public MainMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    
    
    @FXML
    Button btnCollision;
    @FXML
    Button btnPresetSim;
    @FXML
    Button btnSavedSim;
    @FXML
    Button btnPool;
    
   
    public void initialize() throws IOException{
        btnCollision.setOnAction((event) -> {
            handleCollision(event, this.primaryStage);
        });
        btnPresetSim.setOnAction((event) -> {
            handlePresetSim(event);
        });
        btnSavedSim.setOnAction((event) -> {
            handleSavedSim(event);
        });
        btnPool.setOnAction((event) -> {
            handlePool(event);
        });
    }   
    
    public void handleCollision(ActionEvent event, Stage primaryStage){
        try {
            
            System.out.println("hehe");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu3.fxml"));
            edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController();
            loader.setController(menuController);
            BorderPane root = loader.load();
            Simulation sim = new Simulation(2, menuController);
            menuController.initialize(sim);
            
            primaryStage.close();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show(); 
            sim.createRandomObjects2(sim.numberOfObj, sim.cmc, sim.animationPane);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void handlePresetSim(ActionEvent event){

    }
    public void handleSavedSim(ActionEvent event){

    }
    public void handlePool(ActionEvent event){

    }
    

}
