/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina Amoura, Wassim Yahia
 */
public class MainMenuController {
    Stage primaryStage;
    
    @FXML
    Button btnCollision;
    @FXML
    Button btnPresetSim;
    @FXML
    Button btnSavedSim;    
    
    /***
     * Sole constructor
     * @param primaryStage 
     */
    public MainMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
   /***
    * Sets the event handling methods for the menu buttons
    * @throws IOException 
    */
    public void initialize() throws IOException{
        btnCollision.setOnAction((event) -> {
            handleCollision(event, this.primaryStage);
        });
        btnPresetSim.setOnAction((event) -> {
            try {
                handlePresetSim(event, this.primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnSavedSim.setOnAction((event) -> {
            try {
                handleSavedSim(event, this.primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }   
    
    /***
     * replace the scene of primaryStage with the CollisionMenu
     * @param event
     * @param primaryStage 
     */
    public void handleCollision(ActionEvent event, Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu3.fxml"));
            edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController(primaryStage);
            loader.setController(menuController);
            BorderPane root = loader.load();
            Simulation sim = new Simulation(menuController);
            menuController.initialize(sim);
            
            primaryStage.close();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show(); 
            sim.createRandomObjects(2, sim.cmc, sim.animationPane);
            Simulation.isSavedSim = false;
            Simulation.isPresetSim = false;
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /***
     * Shows a modal window with the 6 preset simulations
     * @param event
     * @param primaryStage
     * @throws IOException 
     */
    public void handlePresetSim(ActionEvent event, Stage primaryStage) throws IOException{
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/presetSimMenu.fxml"));
        PresetSimMenuController presetsimMenuController = new PresetSimMenuController(this, primaryStage);
        loader.setController(presetsimMenuController);
        Pane root = loader.load();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    /***
     * Shows a modal window with the list of saved simulations
     * @param event
     * @param primaryStage
     * @throws IOException 
     */
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
    
    
}
