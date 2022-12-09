package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Steven Lam, Sabrina Amoura, Matthew Hantar, Wassim Yahia
 */
public class CircleObject extends CollisionObject {

    private double radius;
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

    public CircleObject(CollisionMenuController cmc, String imagePath) throws IOException {
        super(cmc);
        this.shape = new Circle(30);
        this.shape.setFill(Color.valueOf("303030"));
        this.collidingShape = this.shape;
        this.radius = 30;
        this.height = radius * 2;
        this.width = radius * 2;
        this.mass = 5;

        //NO DEFAULT WASSIMS REST IN PEACE WASSIM.
        this.setEffect(this.shape);
    }

    ImageView getImageViewNode() {
        return (ImageView) getNode();
    }

}
