/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.collisionsimulator.simulator;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wassi
 */
public class PresetSim {
    static public double frictionToPass;
    static private String filePath = null;
    private static ArrayList<String> presetSimList = new ArrayList<>();


    public PresetSim(String fileName) {
        filePath = "src/main/resources/presetSim/" + fileName + ".csv";
        File file = new File(filePath);
    }
    

    public static ArrayList<CollisionObject> load(String fileName, CollisionMenuController cmc) throws FileNotFoundException, IOException, CsvValidationException {
        ArrayList<CollisionObject> objects = new ArrayList<>();
        // first create file object for file placed at location
        // specified by filepath
        filePath = "src/main/resources/presetSim/" + fileName + ".csv";
        File file = new File(filePath);

        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build();
        String[] objectParametersArr;

        frictionToPass = Double.parseDouble(reader.readNext()[0]);

        while ((objectParametersArr = reader.readNext()) != null) {
            CircleObject c = new CircleObject(cmc, ResourcesManager.INVADER_BEE);
            c.setPosX(Double.parseDouble(objectParametersArr[0]));
            c.setPosY(Double.parseDouble(objectParametersArr[1]));
            c.setMass(Double.parseDouble(objectParametersArr[2]));
            double speed = Double.parseDouble(objectParametersArr[3]);
            double direction = Double.parseDouble(objectParametersArr[4]);
            if (objectParametersArr[5].equals("TRUE")) {
                c.setSizeScaling(true);
            } else {
                c.setSizeScaling(false);
            }
            c.setSize(Double.parseDouble(objectParametersArr[6]));
            if (!objectParametersArr[7].equals("")) {
                c.updateImage(objectParametersArr[7]);
            }

            c.setVelocityX(speed * Math.cos(Math.toRadians(direction)));
            c.setVelocityY(speed * Math.sin(Math.toRadians(direction)));
            c.getVv().update();
            objects.add(c);
        }
        return objects;
    }


    public static ArrayList getPresetSimList() {
        return presetSimList;
    }


}
