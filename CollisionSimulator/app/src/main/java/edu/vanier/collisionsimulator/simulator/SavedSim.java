/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author sabri
 */
public class SavedSim {
    int count = 0;

    //https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/#:~:text=Writing%20a%20CSV%20file%20is,()%20method%20of%20CSVWriter%20class.
    public static void newSavedSim(String filePath) {
        
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"Name", "Class", "Marks"};
            writer.writeNext(header);

            // add data to csv
            String[] data1 = {"Aman", "10", "620"};
            writer.writeNext(data1);
            String[] data2 = {"Suraj", "10", "630"};
            writer.writeNext(data2);

            // closing writer connection
            writer.close();
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //http://www.infybuzz.com/2019/06/how-to-create-csv-file-in-java.html
    public class CreateCsvFile {

        public static void main(String[] args) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Name").append(",").append("Website").append(",").append("Country").append("\n");

            stringBuilder.append("Raj").append(",").append("www.infybuzz.com").append(",").append("India").append("\n");

            stringBuilder.append("John").append(",").append("www.infybuzz.com").append(",").append("USA");

            try ( FileWriter fileWriter = new FileWriter("D:\\hello.csv")) {

                fileWriter.write(stringBuilder.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}