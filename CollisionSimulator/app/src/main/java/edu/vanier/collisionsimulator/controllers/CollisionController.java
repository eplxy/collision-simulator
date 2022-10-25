package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.AnimationPane;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.CollisionObjectManager;

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
                //PhysicsController.collidedSpeed();
            }
        }
        return false;
    }

    public static void handleUpdate(CollisionObject colObj, AnimationPane aPane) {
        bounceOffBorder(colObj, aPane);
        colObj.update();

    }

    public static void updateCollisionObjects(CollisionObjectManager com, AnimationPane aPane) {
        for (CollisionObject colObj : com.getAllColObjs()) {
            handleUpdate(colObj, aPane);
        }
    }

    //TODO: SOLVE BOUNDS ISSUE (it is 3 am. i am tired.)
    public static void bounceOffBorder(CollisionObject colObj, AnimationPane aPane) {

        //should be aPane.getBounds().getMinX() instead of "0" and maxX instead of "1800"
        
        if ((colObj.getNode().getLayoutX() >= (1800 - colObj.getWidth()))
                || (colObj.getNode().getLayoutX() <= (0 + colObj.getWidth()))) {
            colObj.setVelocityX(colObj.getVelocityX() * -1);
        }
        if ((colObj.getNode().getLayoutY() >= (1000 - colObj.getHeight()))
                || (colObj.getNode().getLayoutY() <= (0 + colObj.getHeight()))) {
            colObj.setVelocityY(colObj.getVelocityY() * -1);
        }

    }

}
