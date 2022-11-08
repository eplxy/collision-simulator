/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 *
 * @author sabri
 */
public class CircleObj extends CollisionObject {

    private double radius;

    //default circle object for animation
    public CircleObj() throws IOException {
        super();
        this.shape = new Circle(50);
        this.collidingShape = this.shape;
        this.radius = 50;
        this.height = radius*2;
        this.width = radius*2;
        this.mass = 5;
        

    }
//    //default circle object for ui testing
//
    public CircleObj(CollisionMenuController cmc) throws IOException {
        super(cmc);
        this.shape = new Circle(50);
        
        this.collidingShape = this.shape;
        this.radius = 50;
        this.height = radius*2;
        this.width = radius*2;
        this.mass = 5;
        

    }


}
