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
    public CircleObj() {
        super();
        this.shape = new Circle(50);
        this.collidingShape = this.shape;
        this.radius = 50;
        this.height = radius;
        this.width = radius;
        this.mass = 5;

    }
    //default circle object for ui testing

    public CircleObj(CollisionMenuController cmc) throws IOException {
        super(cmc);
        this.shape = new Circle(50);
        this.radius = 50;
        this.height = radius;
        this.width = radius;
        setMouseListener(cmc);
        cmc.addShape(shape);
    }

    private void setMouseListener(CollisionMenuController cmc) {
        this.shape.setOnMouseClicked((MouseEvent mouseEvent) -> {
            cmc.getParametersPane().getChildren().setAll(parameters);
        });
    }

}
