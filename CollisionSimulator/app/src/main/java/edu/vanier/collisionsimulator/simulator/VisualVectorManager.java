/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.shape.Line;

/**
 *
 * @author 2154033
 */
public class VisualVectorManager {
    private final List<Line> visVectors = new ArrayList<>();
    private final List<Line> visVectorsToBeRemoved = new ArrayList<>();

    public List<Line> getVisVectors() {
        return visVectors;
    }

    public List<Line> getVisVectorsToBeRemoved() {
        return visVectorsToBeRemoved;
    }
    
    public void addVisVectors(Line... invisVectors) {
        visVectors.addAll(Arrays.asList(invisVectors));
    }
            
    public void removeVisVectors(Line... invisVectors) {
        visVectors.removeAll(Arrays.asList(invisVectors));
    }
    public void cleanupVisVectors() {

        visVectors.removeAll(visVectorsToBeRemoved);

        visVectorsToBeRemoved.clear();
    }
}
