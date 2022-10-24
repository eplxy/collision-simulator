package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.controllers.CollisionController;
import java.util.HashSet;
import java.util.Set;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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

        CircleObject c1 = new CircleObject();
        CircleObject c2 = new CircleObject();
        this.animationPane.getChildren().addAll(c1.node, c2.node);
        c1.setPosX(500);
        c1.setPosY(300);
        
        c1.setVelocityX(-5);
        c1.setVelocityY(-5);

        
        c2.setPosX(200);
        c2.setPosY(300);

        c2.setVelocityX(-3);
        c2.setVelocityY(7);
        com = new CollisionObjectManager();
        com.addCollisionObjects(c1, c2);
        loop.play();

    }

    private Timeline setLoop() {

        EventHandler<ActionEvent> onFinished = (event) -> {
            // update actors
            CollisionController.updateCollisionObjects(com, this.animationPane);
            // check for collision.
            //CollisionController.checkCollisions(com);
            // removed dead sprites.
            com.cleanupCollisionObjects();
            System.out.println(com.getAllColObjs().get(1).getPosX() + "|||" + this.animationPane.getBoundsInParent());
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
            nodes.add(circle);
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
