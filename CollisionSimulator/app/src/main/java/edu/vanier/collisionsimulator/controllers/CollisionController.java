package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.AnimationPane;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.CollisionObjectManager;
import javafx.scene.paint.Color;

/**
 *
 * @author 2186084
 */
public class CollisionController {

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
                colObjA.getShape().setFill(color);
                colObjB.getShape().setFill(color);

            }
        }
        return false;
    }

    public static void handleUpdate(CollisionObject colObj, AnimationPane aPane) {
        bounceOffBorder(colObj, aPane);
        colObj.update(aPane);

    }

    public static void updateCollisionObjects(CollisionObjectManager com, AnimationPane aPane) {
        for (CollisionObject colObj : com.getAllColObjs()) {
            handleUpdate(colObj, aPane);
        }
    }

    //TODO: SOLVE BOUNDS ISSUE (it is 3 am. i am tired.)
    //TODO: Solve top and bottom border bouncing teleportation
    public static boolean intersectsBorderX(CollisionObject colObj, AnimationPane aPane) {
        return ((colObj.getPosX() >= (aPane.getxMax() - colObj.getWidth()/2))
                || (colObj.getPosX() <= (0 + colObj.getWidth()/2)));
    }
    public static boolean predictIntersectsBorderX(CollisionObject colObj, AnimationPane aPane) {
        return ((colObj.getPosX()+colObj.getVelocityX() >= (aPane.getxMax() - colObj.getWidth()/2))
                || (colObj.getPosX()+colObj.getVelocityX() <= (0 + colObj.getWidth()/2)));
    }
    public static boolean intersectsBorderY(CollisionObject colObj, AnimationPane aPane) {
        return ((colObj.getPosY() >= (aPane.getyMax() - colObj.getHeight()/2))
                || (colObj.getPosY() <= (0 + colObj.getHeight()/2)));
    }
        public static boolean predictIntersectsBorderY(CollisionObject colObj, AnimationPane aPane) {
        return ((colObj.getPosY()+colObj.getVelocityY() >= (aPane.getyMax() - colObj.getHeight()/2))
                || (colObj.getPosY()+colObj.getVelocityY() <= (0 + colObj.getHeight()/2)));
    }


    public static void bounceOffBorder(CollisionObject colObj, AnimationPane aPane) {

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
