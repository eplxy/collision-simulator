package edu.vanier.collisionsimulator.simulator;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author sabri
 */
public class CircleObject extends CollisionObject{
    
    private double radius;
    
    //default circle object
    public CircleObject(){
        super();
        this.node = new Circle(50);
        this.radius = 50;
        this.height = radius;
        this.width = radius;
        this.mass = 5;
    }
    
    public CircleObject(double r) {
        super();
        this.node = new Circle(r);
        this.radius = r;
        this.height = radius;
        this.width = radius;
    }




}
