/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import javafx.scene.shape.Line;

/**
 *
 * @author 2154033
 */
public class VisualVector{
    Line visVector;
    CollisionObject owner;
    double posX;
    double posY;
    int direction;
    public VisualVector(CollisionObject colObj) {
        this.owner = colObj;
        visVector = new Line();
        
        visVector.setStartX( colObj.getPosX());
        visVector.setStartY( colObj.getPosY());
        visVector.setEndX(visVector.getStartX()+ owner.getVelocityX()*20);
        visVector.setEndY(visVector.getStartY()+ owner.getVelocityY()*20);
    }
    public void update(){
        this.visVector.setStartX( this.owner.getPosX());
        this.visVector.setStartY( this.owner.getPosY());
        this.visVector.setEndX(visVector.getStartX()+ this.owner.getVelocityX()*20);
        this.visVector.setEndY(visVector.getStartY()+ this.owner.getVelocityY()*20);
    }
    public Line getVisVector() {
        return visVector;
    }

    public void setVisVector(Line visVector) {
        this.visVector = visVector;
    }
    
    
}
