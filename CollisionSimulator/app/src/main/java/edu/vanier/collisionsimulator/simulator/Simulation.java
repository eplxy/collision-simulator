package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.controllers.CollisionController;
import edu.vanier.collisionsimulator.ui.CollisionMenuController;
//import edu.vanier.sprites.ResourcesManager;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author 2186084
 */
public class Simulation {

    public VisualVectorManager vvm;
    public CollisionObjectManager com;
    public MediaPlayer mp;
    public Timeline loop;
    public int numberOfObj;
    public CollisionMenuController cmc;
    private double frameRate = 60;
    public double friction = 0;
    //associated pane
    public Pane animationPane;
    public static boolean isSavedSim = false;
    public static boolean isPresetSim = false;
    public static String lastLoaded = "";

    /**
     * Creates and runs a simulation with randomized circle objects. Does not
     * spawn objects that overlap. Takes an integer number of circle objects to
     * be added.
     *
     * @param numOfObjs
     */
    public Simulation(int numOfObjs) throws IOException {
        com = new CollisionObjectManager();
        vvm = new VisualVectorManager();
        loop = setLoop();

    }

    //attempt to link simulations and parameters
    public Simulation(int numOfObjs, CollisionMenuController cmc) throws IOException {
        this.numberOfObj = numOfObjs;
        this.vvm = new VisualVectorManager();
        this.com = new CollisionObjectManager();
        this.cmc = cmc;

        loop = setLoop();

    }

    private Timeline setLoop() {

        EventHandler<ActionEvent> onFinished = (event) -> {
            // check for collision.
            CollisionController.checkCollisions(com);
            // update actors
            CollisionController.updateCollisionObjects(com, this.animationPane, this.friction);
            // removed dead sprites.
            com.cleanupCollisionObjects();

        };

        final KeyFrame kf = new KeyFrame(Duration.millis((1000 / (float) this.frameRate)), onFinished);
        loop = new Timeline(kf);
        loop.setCycleCount(Animation.INDEFINITE);
        return loop;
    }

    public void setFrameRate(double frameRate) {
        this.loop.stop();
        this.frameRate = frameRate;
        this.loop = this.setLoop();
    }

    public void setFriction(double friction) {
        this.friction = friction;
        cmc.sliderFriction.setValue(friction);
    }

    public double getFriction() {
        return friction;
    }

    private boolean willSpawnIntersecting(double randX, double randY, CollisionObject[] objArray) {

        double objectWidth = 100 / 2;
        double bufferDist = 10;
        double minDist = objectWidth * 2 + bufferDist;
        for (CollisionObject obj : objArray) {

            if (obj == null) {
                continue;
            }
            double actualDistance = Math.sqrt(Math.pow((randX - obj.getPosX()), 2) + Math.pow(randY - obj.getPosY(), 2));

            animationPane.getBorder();

            if (actualDistance < minDist) {
                return true;
            }

        }

        return false;
    }

    public void loadSavedSim(ArrayList<CollisionObject> objects, CollisionMenuController cmc, Pane aPane) throws IOException {
        ArrayList<Shape> shapesToAdd = new ArrayList<>();
        CollisionObject[] objectsArr = new CollisionObject[objects.size()];

        for (CollisionObject obj : objects) {
            shapesToAdd.add(obj.getShape());
            obj.setDragListeners(cmc);
            obj.setMouseListener(cmc);
            shapesToAdd.add(obj.getVv().getVisVector());
            obj.getVv().setDragListeners();
            objectsArr[objects.indexOf(obj)] = obj;
        }

        this.com.addCollisionObjects(objectsArr);
        this.com.getAllColObjs().forEach((CollisionObject c) -> {
            this.vvm.addVisVectors(c.getVv().getVisVector());
        });
        this.animationPane.getChildren().addAll(shapesToAdd);
    }

    public void createRandomObjects2(int numOfObjs, CollisionMenuController cmc, Pane aPane) throws IOException {

        CollisionObject[] randomObjsToAdd = new CollisionObject[numOfObjs];
        ArrayList<Shape> shapesToAdd = new ArrayList<>();
        double randX, randY;
        double bufferX = 150;
        double bufferY = 150;

        for (int i = 0; i < numOfObjs; i++) {

            do {

                randX = (Math.random() * (aPane.getWidth() - bufferX) + 100);   // This Will Create A Random Number Inbetween Your Min And Max.
                randY = (Math.random() * (aPane.getHeight() - bufferY) + 100);

            } while (willSpawnIntersecting(randX, randY, randomObjsToAdd));
            CircleObject c = new CircleObject(cmc, ResourcesManager.INVADER_BEE);

            c.setPosX(randX);
            c.setPosY(randY);
            //c.setMass(Math.random() * (0.05));
            c.setVelocityX(Math.random() * (10 + 5) - 10);
            c.setVelocityY(Math.random() * (10 + 5) - 10);
            randomObjsToAdd[i] = c;
        }

        for (CollisionObject obj : randomObjsToAdd) {

            shapesToAdd.add(obj.getShape());
            obj.setDragListeners(cmc);
            obj.setMouseListener(cmc);
            shapesToAdd.add(obj.getVv().getVisVector());
            obj.getVv().setDragListeners();
        }

        this.com.addCollisionObjects(randomObjsToAdd);
        this.com.getAllColObjs().forEach((CollisionObject c) -> {
            this.vvm.addVisVectors(c.getVv().getVisVector());
        });
        this.animationPane.getChildren().addAll(shapesToAdd);
    }

    public void addObject(CollisionMenuController cmc, Pane aPane) throws IOException {

        CollisionObject[] currentObjs = new CollisionObject[this.com.getAllColObjs().size()];
        this.com.getAllColObjs().toArray(currentObjs);
        ArrayList<Shape> shapesToAdd = new ArrayList<>();
        double randX, randY;
        double bufferX = 150;
        double bufferY = 150;

        do {

            randX = (Math.random() * (aPane.getWidth() - bufferX) + 100);   // This Will Create A Random Number Inbetween Your Min And Max.
            randY = (Math.random() * (aPane.getHeight() - bufferY) + 100);

        } while (willSpawnIntersecting(randX, randY, currentObjs));
        CircleObject c = new CircleObject(cmc, ResourcesManager.INVADER_BEE);

        c.setPosX(randX);
        c.setPosY(randY);
        c.setVelocityX(Math.random() * (10 + 5) - 10);
        c.setVelocityY(Math.random() * (10 + 5) - 10);

        shapesToAdd.add(c.getShape());
        c.setDragListeners(cmc);
        c.setMouseListener(cmc);
        shapesToAdd.add(c.getVv().getVisVector());
        c.getVv().setDragListeners();

        this.com.addCollisionObjects(c);
        this.vvm.addVisVectors(c.getVv().getVisVector());
        this.animationPane.getChildren().addAll(shapesToAdd);
    }

    public void setAnimationPane(Pane pane) {
        this.animationPane = pane;
    }

    public CollisionObjectManager getCom() {
        return com;
    }
    
    public void setCom(CollisionObjectManager com) {
        this.com = com;
    }

    public VisualVectorManager getVvm() {
        return vvm;
    }

    public void setVvm(VisualVectorManager vvm) {
        this.vvm = vvm;
    }

}
