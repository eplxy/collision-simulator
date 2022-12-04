package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.CollisionObjectManager;
import javafx.animation.PauseTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author 2186084
 */
public class CollisionController {

    final static double BORDER_BUFFER = 10;
    private static final String BOING = "/sounds/boing.mp3";
    public static boolean boingEnabled;

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

    static boolean handleCollision(CollisionObject colObjA, CollisionObject colObjB, CollisionObjectManager com) {
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
                //m = new Media(CollisionController.class.getResource(ResourcesManager.SOUND_LASER).getPath());
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
    
    
    private static void applyFriction(CollisionObject colObj, double friction){
        
        double speedMult = colObj.getV().computeLength()-friction*0.0001*colObj.getMass();
        if(speedMult > 0){
            colObj.setV(colObj.getV().normalize().scalarMult(speedMult));
        } else {
            colObj.setV(new CustomVector(0, 0));
        }
        
        
        
    }

    public static void handleUpdate(CollisionObject colObj, Pane aPane, double friction) {
        bounceOffBorder(colObj, aPane);
        applyFriction(colObj, friction);
        
        colObj.update();

    }

    public static void updateCollisionObjects(CollisionObjectManager com, Pane aPane, double friction) {
        for (CollisionObject colObj : com.getAllColObjs()) {
            handleUpdate(colObj, aPane, friction);
        }
    }

    //TODO: SOLVE BOUNDS ISSUE (it is 3 am. i am tired.)
    //TODO: Solve top and bottom border bouncing teleportation
    public static boolean intersectsBorderX(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosX() + BORDER_BUFFER >= (aPane.getWidth() - colObj.getWidth() / 2))
                || (colObj.getPosX() <= (0 + colObj.getWidth() / 2) + BORDER_BUFFER));
    }

    public static boolean predictIntersectsBorderX(CollisionObject colObj, Pane aPane) {
        return ((colObj.getPosX() + colObj.getVelocityX() >= (aPane.getWidth() - colObj.getWidth() / 2))
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

    }

    
    
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
