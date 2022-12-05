/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
            try {
                handleSavedSim(event, this.primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnPool.setOnAction((event) -> {
            handlePool(event);
        });
    }   
    
    public void handleCollision(ActionEvent event, Stage primaryStage){
        try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu3.fxml"));
            edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController(primaryStage);
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
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    
    public void handlePresetSim(ActionEvent event){

    }
    public void handleSavedSim(ActionEvent event, Stage primaryStage) throws IOException{
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/savedSimMenu.fxml"));
        SavedSimMenuController savedsimMenuController = new SavedSimMenuController(this, primaryStage);
        loader.setController(savedsimMenuController);
        BorderPane root = loader.load();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    public void handlePool(ActionEvent event){

    }
    

}
