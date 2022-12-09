package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.CollisionObjectManager;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Steven Lam, Sabrina Amoura, Matthew Hantar, Wassim Yahia
 */
public class CollisionController {

    final static double BORDER_BUFFER = 10;
    private static final String BOING = "/sounds/boing.mp3";
    public static boolean boingEnabled;

    
    /**
     * Iterates through the list of objects present in simulation and checks for collisions.
     * @param com 
     */
    public static void checkCollisions(CollisionObjectManager com) {
        com.resetCollisionsToCheck();
        
        // check each sprite against other sprite objects.
        for (CollisionObject colObjA : com.getCollisionsToCheck()) {
            for (CollisionObject colObjB : com.getAllColObjs()) {
                if (handleCollision(colObjA, colObjB, com)) {

                    break;
                }
            }
        }
    }

    /**
     * Runs actions on collision.
     * Calculates and sets new velocities using PhysicsController calculations.
     * Randomizes stroke color.
     * Plays audio.
     * @param colObjA First collision object
     * @param colObjB Second collision object.
     * @param com
     * @return false, but execute actions if the collision is detected.
     */
    static boolean handleCollision(CollisionObject colObjA, CollisionObject colObjB, CollisionObjectManager com) {
        if (colObjA != colObjB) {
            if (colObjA.collide(colObjB)) {

                CustomVector[] velocities = PhysicsController.collidedSpeed(colObjA, colObjB);

                colObjA.setVelocityX(velocities[0].x);
                colObjA.setVelocityY(velocities[0].y);
                colObjB.setVelocityX(velocities[1].x);
                colObjB.setVelocityY(velocities[1].y);
                Color color = new Color(Math.random(), Math.random(), Math.random(), 1);

                boing();

                com.mp.play();
                com.mp.seek(Duration.ZERO);
                DropShadow ds1 = new DropShadow();
                ds1.setOffsetY(4.0f);
                ds1.setOffsetX(4.0f);
                ds1.setColor(color);

                colObjA.getShape().setStrokeWidth(5);
                colObjA.getShape().setStroke(color);
                colObjA.getShape().setEffect(ds1);

                colObjB.getShape().setStrokeWidth(5);
                colObjB.getShape().setStroke(color);
                colObjB.getShape().setEffect(ds1);

            }
        }
        return false;
    }

    
    /**
     * Reduces speed based on friction calculations.
     * @param colObj The object to affect.
     * @param friction The friction coefficient.
     */
    private static void applyFriction(CollisionObject colObj, double friction) {

        double speedMult = colObj.getV().computeLength() - friction * 0.0001 * colObj.getMass();
        if (speedMult > 0) {
            colObj.setV(colObj.getV().normalize().scalarMult(speedMult));
        } else {
            colObj.setV(new CustomVector(0, 0));
        }

    }

    /**
     * Actions to occur at each frame.
     * Border and friction interaction.
     * @param colObj The object to update
     * @param aPane Associated animation pane
     * @param friction Friction coefficient
     */
    public static void handleUpdate(CollisionObject colObj, Pane aPane, double friction) {
        bounceOffBorder(colObj, aPane);
        applyFriction(colObj, friction);
        colObj.update();

    }

    
    /**
     * Iterates through the list of collision objects to update.
     * @param com
     * @param aPane
     * @param friction 
     */
    public static void updateCollisionObjects(CollisionObjectManager com, Pane aPane, double friction) {
        for (CollisionObject colObj : com.getAllColObjs()) {
            handleUpdate(colObj, aPane, friction);
        }
    }

    /**
     * Checks if the given collision object is intersecting either the left or right borders.
     * @param colObj
     * @param aPane
     * @return 
     */
    public static boolean intersectsBorderX(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosX() + BORDER_BUFFER >= (aPane.getWidth() - colObj.getWidth() / 2))
                || (colObj.getPosX() <= (0 + colObj.getWidth() / 2) + BORDER_BUFFER));
    }

    /**
     * Checks if the given collision object is intersecting either the top or bottom borders.
     * @param colObj
     * @param aPane
     * @return 
     */
    public static boolean intersectsBorderY(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosY() + BORDER_BUFFER >= (aPane.getHeight() - colObj.getHeight() / 2))
                || (colObj.getPosY() <= (0 + colObj.getHeight() / 2) + BORDER_BUFFER));
    }

    
    /**
     * Adjusts velocity when border collision is detected.
     * @param colObj
     * @param aPane 
     */
    public static void bounceOffBorder(CollisionObject colObj, Pane aPane) {

        if (intersectsBorderX(colObj, aPane)) {
            colObj.setVelocityX(colObj.getVelocityX() * -1);
        }

        if (intersectsBorderY(colObj, aPane)) {

            colObj.setVelocityY(colObj.getVelocityY() * -1);
        }

    }

    /***
     * Plays a "boing" sound effect on a 5% chance upon collision.
     * Sound actress credits go to Steven's girlfriend.
     */
    private static void boing() {
        if (boingEnabled) {
            if (Math.random() <= 0.1) {
                Media a = new Media(CollisionController.class.getResource(BOING).toExternalForm());
                MediaPlayer m = new MediaPlayer(a);
                m.play();
            }
        }
    }
}
