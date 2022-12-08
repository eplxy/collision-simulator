/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import com.opencsv.exceptions.CsvValidationException;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class SavedSimMenuController {

    Stage primaryStage;

    @FXML
    ListView listViewSims;
    @FXML
    Button btnLoad;
    @FXML
    Button btnDelete;

    MainMenuController mmc;

    public SavedSimMenuController(MainMenuController mmc, Stage primaryStage) {
        this.mmc = mmc;
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        listViewSims.setItems(SavedSim.getSavedSimList());
        btnDelete.setOnAction((event) -> {
            handleDelete(event);
        });
//https://jenkov.com/tutorials/javafx/listview.html
        btnLoad.setOnAction(event -> {
            try {
                handleLoad(event);
            } catch (IOException ex) {
                Logger.getLogger(SavedSimMenuController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(SavedSimMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void handleLoad(ActionEvent event) throws IOException, FileNotFoundException, CsvValidationException {
        ObservableList selectedIndices = listViewSims.getSelectionModel().getSelectedIndices();
        ArrayList<CollisionObject> objects = new ArrayList<>();
        String filePath;

        for (Object o : selectedIndices) {
            int index = (int) o;
            filePath = (String) SavedSim.getSavedSimList().get(index);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu3.fxml"));
                edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController(primaryStage);
                loader.setController(menuController);
                BorderPane root = loader.load();
                objects = SavedSim.load(filePath, menuController);
                Simulation sim = new Simulation(0, menuController);
                menuController.initialize(sim);

                primaryStage.close();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setMaximized(true);
                primaryStage.show();
                sim.loadSavedSim(objects, sim.cmc, sim.animationPane);
                sim.setFriction(SavedSim.frictionToPass);
                Simulation.isSavedSim = true;
                Simulation.lastLoaded = filePath;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    public void handleDelete(ActionEvent event) {
        ObservableList selectedIndices = listViewSims.getSelectionModel().getSelectedIndices();
        String fileName = "";

        for (Object o : selectedIndices) {
            int index = (int) o;
            fileName = (String) SavedSim.getSavedSimList().get(index);
        }
        SavedSim.delete(fileName);
    }

}
