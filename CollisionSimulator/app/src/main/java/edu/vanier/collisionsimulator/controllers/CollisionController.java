package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.CollisionObjectManager;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author 2186084
 */
public class CollisionController {

    final static double BORDER_BUFFER = 10;

    public static void checkCollisions(CollisionObjectManager com) {
        com.resetCollisionsToCheck();
        // check each sprite against other sprite objects.
        for (CollisionObject colObjA : com.getCollisionsToCheck()) {
            for (CollisionObject colObjB : com.getAllColObjs()) {
                if (handleCollision(colObjA, colObjB)) {
                    // The break helps optimize the collisions
                    //  The break statement means one object only hits another
                    // object as opposed to one hitting many objects.
                    // To be more accurate comment out the break statement.
                    break;
                }
            }
        }
    }

    static boolean handleCollision(CollisionObject colObjA, CollisionObject colObjB) {
        if (colObjA != colObjB) {
            if (colObjA.collide(colObjB)) {

                CustomVector[] velocities = PhysicsController.collidedSpeed(colObjA, colObjB);

                colObjA.setVelocityX(velocities[0].x);
                colObjA.setVelocityY(velocities[0].y);
                colObjB.setVelocityX(velocities[1].x);
                colObjB.setVelocityY(velocities[1].y);
                Color color = new Color(Math.random(), Math.random(), Math.random(), 1);
                
                /** 
                 * Change image while bouncing.
                 */
                /*
                Image map = new Image("images/desert.png");
                ImagePattern pattern = new ImagePattern(map);
                colObjA.getShape().setFill(pattern);
                
                Image map2 = new Image("images/methane-ice.png");
                ImagePattern pattern2 = new ImagePattern(map2);
                colObjB.getShape().setFill(pattern2);
                */
                colObjA.getShape().setStrokeWidth(5);
                colObjA.getShape().setStroke(color);

                colObjB.getShape().setStrokeWidth(5);
                colObjB.getShape().setStroke(color);
                

            }
        }
        return false;
    }

    public static void handleUpdate(CollisionObject colObj, Pane aPane) {
        bounceOffBorder(colObj, aPane);
        colObj.update();
        

    }

    public static void updateCollisionObjects(CollisionObjectManager com, Pane aPane) {
        for (CollisionObject colObj : com.getAllColObjs()) {
            handleUpdate(colObj, aPane);
        }
    }

    //TODO: SOLVE BOUNDS ISSUE (it is 3 am. i am tired.)
    //TODO: Solve top and bottom border bouncing teleportation
    public static boolean intersectsBorderX(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosX() + BORDER_BUFFER >= (aPane.getWidth() - colObj.getWidth() / 2))
                || (colObj.getPosX() <= (0 + colObj.getWidth() / 2) + BORDER_BUFFER));
    }

    public static boolean predictIntersectsBorderX(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosX() + colObj.getVelocityX() >= (aPane.getWidth()- colObj.getWidth() / 2))
                || (colObj.getPosX() + colObj.getVelocityX() <= (0 + colObj.getWidth() / 2)));
    }

    public static boolean intersectsBorderY(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosY() + BORDER_BUFFER >= (aPane.getHeight() - colObj.getHeight() / 2))
                || (colObj.getPosY() <= (0 + colObj.getHeight() / 2) + BORDER_BUFFER));
    }

    public static boolean predictIntersectsBorderY(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosY() + colObj.getVelocityY() >= (aPane.getHeight() - colObj.getHeight() / 2))
                || (colObj.getPosY() + colObj.getVelocityY() <= (0 + colObj.getHeight() / 2)));
    }

    public static void bounceOffBorder(CollisionObject colObj, Pane aPane) {

        //should be aPane.getBounds().getMinX() instead of "0" and maxX instead of "1800"
        if (intersectsBorderX(colObj, aPane)) {
            colObj.setVelocityX(colObj.getVelocityX() * -1);
        }

        if (intersectsBorderY(colObj, aPane)) {
            colObj.setVelocityY(colObj.getVelocityY() * -1);
        }
//    if ((colObj.getShape().getLayoutX() >= (aPane.getBoundsInLocal().getMaxX()- (colObj.getWidth())))
//                || (colObj.getShape().getLayoutX() <= (aPane.getBoundsInLocal().getMinX() + (colObj.getWidth())))) {
//            colObj.setVelocityX(colObj.getVelocityX() * -1);
//        }
//        if ((colObj.getShape().getLayoutY() >= (aPane.getBoundsInLocal().getMaxY() - (colObj.getShape().getLayoutBounds().getHeight())))
//                || (colObj.getShape().getLayoutY() <= (aPane.getBoundsInLocal().getMinY() + (colObj.getShape().getLayoutBounds().getHeight())))) {
//            colObj.setVelocityY(colObj.getVelocityY() * -1);
//        }

    }

}
