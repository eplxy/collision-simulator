/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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

    @Override
    public void setSize(double size) {
        this.size = size;
        this.width = 60 + (size - 1) * 40;
        this.height = 60 + (size - 1) * 40;
        this.radius = this.width / 2;
        Circle c = (Circle) this.shape;
        c.setRadius(radius);
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setEffect(Node node) {
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(4.0f);
        ds1.setColor(Color.BLACK);

        node.setEffect(ds1);
    }

    //default circle object for animation
    public CircleObject() throws IOException {
        super();
        this.shape = new Circle(50.0f);
        this.shape.setStroke(Color.TRANSPARENT);
        this.collidingShape = this.shape;
        this.radius = 50;
        this.height = this.radius * 2;
        this.width = this.radius * 2;
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
        this.shape = new Circle(30);
        this.collidingShape = this.shape;
        this.radius = 30;
        this.height = radius * 2;
        this.width = radius * 2;
        this.mass = 5;

        Image map = new Image("images/Capture.PNG");
        ImagePattern pattern = new ImagePattern(map);
        shape.setFill(pattern);

        setEffect(this.shape);
    }

    ImageView getImageViewNode() {
        return (ImageView) getNode();
    }

}
