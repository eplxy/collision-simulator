/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.SavedSim;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class SaveDialogController {
    Simulation sim;
    CollisionMenuController cmc;

    public SaveDialogController(CollisionMenuController cmc) {
        this.cmc = cmc;
    }
    
    
    @FXML
    Button btnSave;
    @FXML
    TextField txtFieldName;
    @FXML
    Label labelError;
    
    public void initialize() throws IOException{
        btnSave.setOnAction((event) -> {
            handleSave(event);
        });
        labelError.setText("");
    }   
    
    public void handleSave(ActionEvent event){
        String fileName = txtFieldName.getText();
        if(SavedSim.getSavedSimNamesList().contains(fileName)){
            labelError.setText("This simulation already exists");
        }else if(checkFileName(fileName)){
            labelError.setText("Invalid name");
        }else{
            SavedSim.savedSimulation(this.sim, fileName);
        }
    }
    
    public boolean checkFileName(String name){
        return(name.contains("<")||name.contains(">")||name.contains(":")||name.contains("\"")||name.contains("/")||name.contains("\\")||name.contains("|")||name.contains("?")||name.contains("*"));
    }
    public Simulation getSim() {
        return sim;
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
    }
}
