/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class SavedSim {

    static private String filePath = null;
    static private List<String> SavedSimNamesList = new ArrayList<>();

    //https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/#:~:text=Writing%20a%20CSV%20file%20is,()%20method%20of%20CSVWriter%20class.
    public static void savedSimulation(Simulation sim, String fileName) {
        SavedSimNamesList.add(fileName);
        // first create file object for file placed at location
        // specified by filepath
        filePath = "src/main/resources/savedSim/"+ fileName + ".csv";
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            List<CollisionObject> colObjs = sim.getCom().getAllColObjs();
            for (CollisionObject obj : colObjs) {
                String[] parametersArr = {Double.toString(obj.getPosX()), Double.toString(obj.getPosY()), Double.toString(obj.getMass()), Double.toString(obj.getSpeed()), Double.toString(obj.getDirection())};
                writer.writeNext(parametersArr);
            }
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static List<String> getSavedSimNamesList() {
        return SavedSimNamesList;
    }

    public static void setSavedSimNamesList(List<String> SavedSimNamesList) {
        SavedSim.SavedSimNamesList = SavedSimNamesList;
    }
    
}
