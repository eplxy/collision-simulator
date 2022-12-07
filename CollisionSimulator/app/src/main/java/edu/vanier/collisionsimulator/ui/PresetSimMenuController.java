/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.collisionsimulator.ui;

import com.opencsv.exceptions.CsvValidationException;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.PresetSim;
import edu.vanier.collisionsimulator.simulator.SavedSim;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

/**
 *
 * @author wassi
 */
public class PresetSimMenuController {
    Stage primaryStage;
    @FXML
    ToggleButton togbtn1;
    @FXML
    ToggleButton togbtn2;
    @FXML
    ToggleButton togbtn3;
    @FXML
    ToggleButton togbtn4;
    @FXML
    ToggleButton togbtn5;
    @FXML
    ToggleButton togbtn6;
    @FXML
    Button btnReturnToMenu;
    @FXML
    Button btnLoad;
     MainMenuController mmc;
    public PresetSimMenuController(MainMenuController mmc, Stage primaryStage) {
        this.mmc = mmc;
        this.primaryStage = primaryStage;
    }
    @FXML
    public void initialize(){
        ImageView curling = new ImageView(new Image("images/" + "curlingStone" + ".png"));
        curling.setFitHeight(107);
        curling.setFitWidth(179);
        togbtn1.setGraphic(curling); 
        ImageView pool = new ImageView(new Image("images/" + "8ball" + ".png"));
        pool.setFitHeight(107);
        pool.setFitWidth(179);
        togbtn2.setGraphic(pool);
        ImageView solar = new ImageView(new Image("buttonImages/" + "solarSystem" + ".png"));
        solar.setFitHeight(107);
        solar.setFitWidth(179);
        togbtn3.setGraphic(solar);
        ImageView marble = new ImageView(new Image("buttonImages/" + "marbles" + ".png"));
        marble.setFitHeight(107);
        marble.setFitWidth(179);
        togbtn4.setGraphic(marble);
        ImageView petanque = new ImageView(new Image("buttonImages/" + "petanque" + ".png"));
        petanque.setFitHeight(107);
        petanque.setFitWidth(179);
        togbtn5.setGraphic(petanque);
        ImageView protagonist = new ImageView(new Image("images/" + "tenet" + ".png"));
        protagonist.setFitHeight(107);
        protagonist.setFitWidth(179);
        togbtn6.setGraphic(protagonist);
        btnReturnToMenu.setOnAction((event) -> {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
                MainMenuController menuController = new MainMenuController(primaryStage);
                loader.setController(menuController);

                BorderPane root = loader.load();

                Scene scene = new Scene(root);
                primaryStage.close();
                primaryStage.setScene(scene);
                primaryStage.setMaximized(true);

                primaryStage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
        });
        togbtn1.setOnAction((event) -> {
            if(togbtn1.isSelected()){
                togbtn2.setSelected(false);
                togbtn3.setSelected(false);
                togbtn4.setSelected(false);
                togbtn5.setSelected(false);
                togbtn6.setSelected(false);
                
            }
        });
        togbtn2.setOnAction((event) -> {
            if(togbtn2.isSelected()){
                togbtn1.setSelected(false);
                togbtn3.setSelected(false);
                togbtn4.setSelected(false);
                togbtn5.setSelected(false);
                togbtn6.setSelected(false);
                
            }
        });
        togbtn3.setOnAction((event) -> {
            if(togbtn3.isSelected()){
                togbtn2.setSelected(false);
                togbtn1.setSelected(false);
                togbtn4.setSelected(false);
                togbtn5.setSelected(false);
                togbtn6.setSelected(false);
                
            }
        });
        togbtn4.setOnAction((event) -> {
            if(togbtn4.isSelected()){
                togbtn2.setSelected(false);
                togbtn3.setSelected(false);
                togbtn1.setSelected(false);
                togbtn5.setSelected(false);
                togbtn6.setSelected(false);
                
            }
        });
        togbtn5.setOnAction((event) -> {
            if(togbtn5.isSelected()){
                togbtn2.setSelected(false);
                togbtn3.setSelected(false);
                togbtn4.setSelected(false);
                togbtn1.setSelected(false);
                togbtn6.setSelected(false);
                
            }
        });
        togbtn6.setOnAction((event) -> {
            if(togbtn6.isSelected()){
                togbtn2.setSelected(false);
                togbtn3.setSelected(false);
                togbtn4.setSelected(false);
                togbtn5.setSelected(false);
                togbtn1.setSelected(false);
                
            }
        });
        btnLoad.setOnAction((event) -> {
            try {
                handleLoad(event);
            } catch (IOException ex) {
                Logger.getLogger(PresetSimMenuController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PresetSimMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                                                
        
    
}
        @FXML
    public void handleLoad(ActionEvent event) throws IOException, FileNotFoundException, CsvValidationException {
       
        ArrayList<CollisionObject> objects = new ArrayList<>();
        String filePath="";
        int index;
            if(togbtn1.isSelected()){
                filePath = "curling";
            }
            else if(togbtn2.isSelected()){
                 filePath = "8-Ball";
            }
            else if(togbtn3.isSelected()){
                filePath = "solar";
                }
            else if(togbtn4.isSelected()){
                filePath = "marbles";
            }
            else if(togbtn5.isSelected()){
                filePath = "petanque";
            }
            else if(togbtn6.isSelected()){
                filePath = "theTeam";
            }
            else{
                return;
            }
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu3.fxml"));
            edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController(primaryStage);
            loader.setController(menuController);
            BorderPane root = loader.load();
            objects = PresetSim.load(filePath, menuController);
            Simulation sim = new Simulation(0, menuController);
            menuController.initialize(sim);
            
            primaryStage.close();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show(); 
            sim.loadSavedSim(objects, sim.cmc, sim.animationPane);
            sim.setFriction(PresetSim.frictionToPass);
            sim.isPresetSim = true;
            sim.lastLoaded = filePath;
        } catch (IOException e) {
            System.out.println(e);
        }
            
           
    
    }

}
