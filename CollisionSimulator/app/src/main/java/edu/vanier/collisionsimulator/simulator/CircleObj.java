/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author sabri
 */
public class CircleObj extends Circle{

    private double speed;
    private double mass;

    public CircleObj(double speed, double mass, double d, double d1, double d2, Paint paint) {
        super(d, d1, d2, paint);
        this.speed = speed;
        this.mass = mass;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
}
