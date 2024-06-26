/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author Sabrina Amoura
 */
public class SavedSim {

    static public double frictionToPass;
    static private String filePath = null;
    private static ObservableList savedSimList = FXCollections.observableArrayList();

    /***
     * Creates a csv file in the folder savedSim with one line per CollisionObject
     * All parameters are saved for the ColisionObjects
     * Names the file with name entered by the user
     * Part of the code is from: https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/#:~:text=Writing%20a%20CSV%20file%20is,()%20method%20of%20CSVWriter%20class.
     * @param sim simulation to be saved
     * @param fileName name entered by the user 
     */
    public static void savedSimulation(Simulation sim, String fileName) {
        savedSimList.add(fileName);
        filePath = "src/main/resources/savedSim/" + fileName + ".csv";
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            List<CollisionObject> colObjs = sim.getCom().getAllColObjs();
            writer.writeNext(new String[]{Double.toString(sim.getFriction())});
            for (CollisionObject obj : colObjs) {
                String[] parametersArr = {Double.toString(obj.getPosX()), Double.toString(obj.getPosY()), Double.toString(obj.getMass()), Double.toString(obj.getSpeed()), Double.toString(obj.getDirection()), Boolean.toString(obj.isSizeScaling()), Double.toString(obj.getSize()), obj.getImgFilePath()};
                writer.writeNext(parametersArr);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /***
     * Parse the file associated to the chosen saved simulation and create CollisionObjects with the same parameters
     * @param fileName name chosen from the list view of saved simulations 
     * @param cmc CollisionMenuController to link to each CollisionObject
     * @return an ArrayList of the objects to add to the simulation
     * @throws FileNotFoundException
     * @throws IOException
     * @throws CsvValidationException 
     */
    public static ArrayList<CollisionObject> load(String fileName, CollisionMenuController cmc) throws FileNotFoundException, IOException, CsvValidationException {
        ArrayList<CollisionObject> objects = new ArrayList<>();
        filePath = "src/main/resources/savedSim/" + fileName + ".csv";

        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build();
        String[] objectParametersArr;

        frictionToPass = Double.parseDouble(reader.readNext()[0]);

        while ((objectParametersArr = reader.readNext()) != null) {
            CircleObject c = new CircleObject(cmc);
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

    /***
     * Deletes the csv file of a saved simulation 
     * @param fileName name selected from the list view of saved simulations
     */
    public static void delete(String fileName) {
        filePath = "src/main/resources/savedSim/" + fileName + ".csv";
        File file = new File(filePath);
        file.delete();
        savedSimList.remove(fileName);
    }

    public static ObservableList getSavedSimList() {
        return savedSimList;
    }

    public static void setSavedSimList(ObservableList savedSimList) {
        SavedSim.savedSimList = savedSimList;
    }
}
