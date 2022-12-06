package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author sabri
 */
public class CircleObject extends CollisionObject {

    private double radius;
    protected Node node;

    private Image map = new Image("images/protagonist.PNG");
    private ImagePattern pattern = new ImagePattern(map);


    
    
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
        //shape.setFill(pattern);

        setEffect(this.shape);
    }

    ImageView getImageViewNode() {
        return (ImageView) getNode();
    }

}
