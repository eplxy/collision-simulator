package edu.vanier.collisionsimulator.simulator;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 *
 * @author stella
 */
//mostly pulled from Assignment 2
public abstract class CollisionObject {

    protected double width, height;
    protected int index;
    protected Node node;
    protected Node collidingNode;
    protected Paint color;

    //FOR FUTURE USE IN APPLYING IMAGES ONTO OBJECTS
    private Image image;

    protected double posX, posY, vX, vY, mass;

    public boolean collide(CollisionObject other) {
        return collidingNode.getBoundsInParent().intersects(other.node.getBoundsInParent());
    }

    public CollisionObject() {
        vX = 0;
        vY = 0;
    }

    public boolean intersects(CollisionObject s) {
        //return s.getBoundary().intersects(this.getBoundary());        
        Bounds sBounds = s.getNode().localToScene(s.getNode().getBoundsInLocal());
        return node.intersects(sBounds);

    }

    public void update(){
        
        this.node.setTranslateX(vX);
        this.node.setTranslateX(vY);
        this.setPosX(posX+vX);
        this.setPosY(posY+vY);
        
        
    }
    
    public Image getImage() {
        return image;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        this.getNode().setLayoutX(posX);
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
        this.getNode().setLayoutY(posY);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getVelocityX() {
        return vX;
    }

    public void setVelocityX(double velocityX) {
        this.vX = velocityX;
        this.node.setTranslateX(vX);
    }

    public double getVelocityY() {
        return vY;

    }

    public void setVelocityY(double velocityY) {
        this.vY = velocityY;
        this.node.setTranslateY(vY);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getCollisionBounds() {
        return collidingNode;
    }

    public void setCollisionBounds(Node collisionBounds) {
        this.collidingNode = collisionBounds;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    
}
