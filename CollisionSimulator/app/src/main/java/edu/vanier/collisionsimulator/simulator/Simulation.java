package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.controllers.CollisionController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public Simulation() {

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
        c6.setPosY(600);
        c6.setVelocityX(-4);
        c6.setVelocityY(0);

        com = new CollisionObjectManager();
        //com.addCollisionObjects(c1, c2, c3, c4);
        com.addCollisionObjects(c5, c6);
        loop.play();

    }

    public Simulation(int numOfObjs) {
        com = new CollisionObjectManager();
        this.animationPane = new AnimationPane(1800, 1000);
        loop = setLoop();

        for (int i = 0; i < numOfObjs; i++) {
            CircleObj c = new CircleObj();
            c.setPosX(Math.random() * (1700 - 100) + 100);
            c.setPosY(Math.random() * (900 - 100) + 100);

            c.setVelocityX(Math.random() * (5 + 5) - 5);
            c.setVelocityY(Math.random() * (5 + 5) - 5);
            com.addCollisionObjects(c);
            this.animationPane.getChildren().add(c.shape);
        }

        loop.play();

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
