/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.simulator;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author 2154033
 */
public class VisualVector {

    Line visVector;
    CollisionObject owner;
    double posX;
    double posY;
    int direction;

    public VisualVector(CollisionObject colObj) {
        this.owner = colObj;
        visVector = new Line();

        visVector.setStartX(colObj.getPosX());
        visVector.setStartY(colObj.getPosY());
        visVector.setEndX(visVector.getStartX() + owner.getVelocityX() * 20);
        visVector.setEndY(visVector.getStartY() + owner.getVelocityY() * 20);
        visVector.setStrokeType(StrokeType.CENTERED);
        visVector.setStroke(Color.BLACK);
        visVector.setStrokeWidth(4);
        visVector.setFill(Color.BLACK);
    }

    public void update() {
        this.visVector.setStartX(this.owner.getPosX());
        this.visVector.setStartY(this.owner.getPosY());
        this.visVector.setEndX(visVector.getStartX() + this.owner.getVelocityX() * 20);
        this.visVector.setEndY(visVector.getStartY() + this.owner.getVelocityY() * 20);

    }

    public final void setDragListeners() {
        final Delta dragDelta = new Delta();
        this.visVector.setOnMousePressed((MouseEvent mouseEvent) -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = this.getVisVector().getStartX() - mouseEvent.getSceneX();
            dragDelta.y = this.getVisVector().getStartY() - mouseEvent.getSceneY();
            //this.shape.setCursor(Cursor.NONE);
        });
        this.visVector.setOnMouseDragged((MouseEvent mouseEvent) -> {
            //this.visVector.setEndX(mouseEvent.getSceneX() + dragDelta.x);
            this.visVector.setEndX(mouseEvent.getSceneX());
            //this.visVector.setEndY(mouseEvent.getSceneY() + dragDelta.y);
            this.visVector.setEndY(mouseEvent.getSceneY());

            this.visVector.setOnMouseReleased((MouseEvent mE) -> {
                this.getOwner().setVelocityX((this.getVisVector().getEndX() - this.getVisVector().getStartX()) / 20);
                this.getOwner().setVelocityY((this.getVisVector().getEndY() - this.getVisVector().getStartY()) / 20);
            });
        });
    }

    class Delta {

        double x, y;
    }

    public Line getVisVector() {
        return visVector;
    }

    public void setVisVector(Line visVector) {
        this.visVector = visVector;
    }

    public CollisionObject getOwner() {
        return owner;
    }

    public void setOwner(CollisionObject owner) {
        this.owner = owner;
    }

}
