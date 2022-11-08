package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.controllers.CollisionController;
import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author 2186084
 */
public class Simulation {

    public CollisionObjectManager com;
    public Timeline loop;
    //associated pane
    public AnimationPane animationPane;

    //default simulation (test simulation)
    public Simulation() throws IOException {

        this.animationPane = new AnimationPane(1800, 1000);
        loop = setLoop();

        CircleObj c1 = new CircleObj();
        c1.setMass(69);
        CircleObj c2 = new CircleObj();
        c2.setMass(40);
        CircleObj c3 = new CircleObj();
        c3.setMass(500);
        CircleObj c4 = new CircleObj();
        c4.setMass(10);

        CircleObj c5 = new CircleObj();
        c3.setMass(10);
        CircleObj c6 = new CircleObj();
        c4.setMass(10);
        //this.animationPane.getChildren().addAll(c1.shape, c2.shape, c3.shape, c4.shape);
        this.animationPane.getChildren().addAll(c5.shape, c6.shape);
        c1.setPosX(1500);
        c1.setPosY(300);
        c1.setVelocityX(-6);
        c1.setVelocityY(2);

        c2.setPosX(300);
        c2.setPosY(300);
        c2.setVelocityX(6);
        c2.setVelocityY(2);

        c3.setPosX(300);
        c3.setPosY(700);
        c3.setVelocityX(6);
        c3.setVelocityY(-2);

        c4.setPosX(1500);
        c4.setPosY(700);
        c4.setVelocityX(-6);
        c4.setVelocityY(-2);

        c5.setPosX(300);
        c5.setPosY(700);
        c5.setVelocityX(0);
        c5.setVelocityY(0);

        c6.setPosX(1500);
        c6.setPosY(601);
        c6.setVelocityX(-4);
        c6.setVelocityY(0);

        com = new CollisionObjectManager();
        //com.addCollisionObjects(c1, c2, c3, c4);
        com.addCollisionObjects(c5, c6);
        loop.play();

    }

    
    /**
     * Creates and runs a simulation with randomized circle objects.
     * Does not spawn objects that overlap. Takes an integer number of circle objects to be added.
     * @param numOfObjs 
     */
    public Simulation(int numOfObjs) throws IOException {
        com = new CollisionObjectManager();
        this.animationPane = new AnimationPane(1000, 400);
        this.animationPane.changeBackground("0000FF");
        loop = setLoop();
        createRandomObjects(numOfObjs);
        

        //loop.play();

    }
    
    //attempt to link simulations and parameters
    public Simulation(int numOfObjs, CollisionMenuController cmc) throws IOException {
        com = new CollisionObjectManager();
        this.animationPane = new AnimationPane(1000, 400);
        this.animationPane.changeBackground("0000FF");
        loop = setLoop();
        createRandomObjects2(numOfObjs, cmc);

    }

    private Timeline setLoop() {

        EventHandler<ActionEvent> onFinished = (event) -> {
            // check for collision.
            CollisionController.checkCollisions(com);
            // update actors
            CollisionController.updateCollisionObjects(com, this.animationPane);
            // removed dead sprites.
            com.cleanupCollisionObjects();
        };

        final KeyFrame kf = new KeyFrame(Duration.millis((1000 / (float) 60)), onFinished);
        loop = new Timeline(kf);
        loop.setCycleCount(Animation.INDEFINITE);
        return loop;
    }

    private void createRandomObjects(int numOfObjs) throws IOException {

        CollisionObject[] randomObjsToAdd = new CollisionObject[numOfObjs];
        ArrayList<Shape> shapesToAdd = new ArrayList<>();
        double randX, randY;

        for (int i = 0; i < numOfObjs; i++) {

            do {

                randX = Math.random() * (1700 - 100) + 100;
                randY = Math.random() * (900 - 100) + 100;

            } while (willSpawnIntersecting(randX, randY, randomObjsToAdd));
            CircleObj c = new CircleObj();
            c.setPosX(randX);
            c.setPosY(randY);
            c.setMass(Math.random() * (100 - 10) + 10);
            c.setVelocityX(Math.random() * (10 + 10) - 10);
            c.setVelocityY(Math.random() * (10 + 10) - 10);
            randomObjsToAdd[i] = c;
        }

        for (CollisionObject obj : randomObjsToAdd) {

            shapesToAdd.add(obj.getShape());

        }

        this.com.addCollisionObjects(randomObjsToAdd);
        this.animationPane.getChildren().addAll(shapesToAdd);
    }

    private boolean willSpawnIntersecting(double randX, double randY, CollisionObject[] objArray) {

        double objectWidth = 100/2;
        double bufferDist = 10;
        double minDist = objectWidth * 2 + bufferDist;
        for (CollisionObject obj : objArray) {
            
            
            if (obj == null) {
                continue;
            }
            double actualDistance = Math.sqrt(Math.pow((randX - obj.getPosX()), 2) + Math.pow(randY - obj.getPosY(), 2));

            if (actualDistance < minDist) {
                return true;
            }
        }
        return false;
    }
    private void createRandomObjects2(int numOfObjs, CollisionMenuController cmc) throws IOException {

        CollisionObject[] randomObjsToAdd = new CollisionObject[numOfObjs];
        ArrayList<Shape> shapesToAdd = new ArrayList<>();
        double randX, randY;
        double xLayout = this.animationPane.width;
        double yLayout = this.animationPane.height;

        for (int i = 0; i < numOfObjs; i++) {

            do {

                randX = (Math.random() * ((xLayout - 50) + 1)) + 50;   // This Will Create A Random Number Inbetween Your Min And Max.
                randY = (Math.random() * ((yLayout - 50) + 1)) + 50; 

            } while (willSpawnIntersecting(randX, randY, randomObjsToAdd));
            CircleObj c = new CircleObj(cmc);
            c.setPosX(randX);
            c.setPosY(randY);
            c.setMass(Math.random() * (100 - 10) + 10);
            c.setVelocityX(Math.random() * (10 + 10) - 10);
            c.setVelocityY(Math.random() * (10 + 10) - 10);
            randomObjsToAdd[i] = c;
        }

        for (CollisionObject obj : randomObjsToAdd) {

            shapesToAdd.add(obj.getShape());

        }

        this.com.addCollisionObjects(randomObjsToAdd);
        this.animationPane.getChildren().addAll(shapesToAdd);
    }

}


/*
Circle circle = new Circle(10, 10, 10);
            circle.setLayoutX(20);
            circle.setLayoutY(20);
            shapes.add(circle);
            root.getChildren().add(circle);

            
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {

                double deltaX = 2;
                double deltaY = 2;
                Bounds bounds = root.getBoundsInLocal();
                
                
                @Override
                public void handle(ActionEvent actionEvent) {
                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY() + deltaY);

                    boolean rightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                    boolean leftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                    boolean bottomBorder = circle.getLayoutY() <= (bounds.getMinY() - circle.getRadius());
                    boolean topBorder = circle.getLayoutY() >= (bounds.getMaxY() + circle.getRadius());

                    /*debug
                    System.out.println("boundsXmin=" + bounds.getMinX() + " ,boundsXmax=" + bounds.getMaxX() + " ,boundsYmin=" + bounds.getMinY() + " ,boundsYmax=" + bounds.getMaxY());
                    System.out.println("cX=" + circle.getLayoutX() + " ,cY=" + circle.getLayoutY() + " ,radius=" + circle.getRadius());
                    System.out.println("top=" + topBorder + " ,bottom=" + bottomBorder + " ,left=" + leftBorder + " ,right=" + rightBorder);
 */
 /*
                    if (rightBorder || leftBorder) {
                        deltaX *= -1;
                    }
                    if (bottomBorder || topBorder) {
                        deltaY *= -1;
                    }
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
 */
