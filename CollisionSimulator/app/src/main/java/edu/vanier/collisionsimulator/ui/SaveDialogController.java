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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class SaveDialogController {
    Simulation sim;

    
    @FXML
    Button btnSave;
    @FXML
    TextField txtFieldName;
    
    public void initialize() throws IOException{
        btnSave.setOnAction((event) -> {
            handleSave(event);
        });
    }   
    
    public void handleSave(ActionEvent event){
        String fileName = txtFieldName.getText();
        SavedSim.savedSimulation(this.sim, fileName);
    }
    
    public Simulation getSim() {
        return sim;
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
    }
}
