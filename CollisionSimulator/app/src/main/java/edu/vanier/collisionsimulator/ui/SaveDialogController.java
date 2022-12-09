package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.SavedSim;
import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina Amoura
 */
public class SaveDialogController {
    Simulation sim;
    CollisionMenuController cmc;
    Stage stage;
    
    @FXML
    Button btnSave;
    @FXML
    TextField txtFieldName;
    @FXML
    Label labelError;
    
    /***
     * Sole constructor
     * @param cmc
     * @param stage 
     */
    public SaveDialogController(CollisionMenuController cmc, Stage stage) {
        this.cmc = cmc;
        this.stage = stage;
    }
    
    /***
     * Sets event handling method for the button and initial content of the error label
     * @throws IOException 
     */
    public void initialize() throws IOException{
        btnSave.setOnAction((event) -> {
            handleSave(event);
        });
        labelError.setText("");
    }   
    
    /***
     * Creates a new saved simulation if the name entered is valid
     * @param event 
     */
    public void handleSave(ActionEvent event){
        String fileName = txtFieldName.getText();
        if(SavedSim.getSavedSimList().contains(fileName)){
            labelError.setText("This simulation already exists");
        }else if(checkFileName(fileName)){
            labelError.setText("Invalid name");
        }else{
            SavedSim.savedSimulation(this.sim, fileName);
            stage.close();
        }
    }
   
    /***
     * @param name user input
     * @return true if the name contains any of the characters not allowed
     */
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
