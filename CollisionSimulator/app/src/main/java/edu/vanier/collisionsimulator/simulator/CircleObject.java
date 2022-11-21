/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 *
 * @author sabri
 */
public class CircleObject extends CollisionObject {

    private double radius;
    private Image image; 
    protected Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
    
    
    
    //default circle object for animation
    public CircleObject() throws IOException {
        super();
        this.shape = new Circle(50.0f);
        this.collidingShape = this.shape;
        this.radius = 50;
        this.height = this.radius*2;
        this.width = this.radius*2;
        this.mass = 5;
        

    }
//    //default circle object for ui testing
//
     public void setImage(Image inImage) {
        image = inImage;
        width = inImage.getWidth();
        height = inImage.getHeight();
    }

    public void setImage(String filename) {
        Image image = new Image(filename);
        setImage(image);
    }
    
    public CircleObject(CollisionMenuController cmc, String imagePath) throws IOException {
        super(cmc);
        ImageView newCircle = new ImageView();
        Image circleImage = new Image(imagePath);
        newCircle.setImage(circleImage);
        
        this.node = newCircle;
        this.shape = new Circle(30);
        this.collidingShape = this.shape;
        this.radius = 30;
        this.height = radius*2;
        this.width = radius*2;
        this.mass = 5;
        

    }

    ImageView getImageViewNode() {
        return (ImageView) getNode();
    }


}
