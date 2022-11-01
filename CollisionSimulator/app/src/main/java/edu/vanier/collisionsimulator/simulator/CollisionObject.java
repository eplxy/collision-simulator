package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import edu.vanier.collisionsimulator.ui.ParametersController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 *
 * @author stella
 */
//mostly pulled from Assignment 2
public abstract class CollisionObject {

    ParametersController parametersController = new ParametersController(this);

    protected AnchorPane parameters;
    protected double width, height;
    protected int index;

    protected Shape shape;
    protected Shape collidingShape;

    protected Paint color;

    //FOR FUTURE USE IN APPLYING IMAGES ONTO OBJECTS
    private Image image;

    protected double posX, posY, vX, vY, mass;

    public boolean collide(CollisionObject other) {
        return collidingShape.getBoundsInParent().intersects(other.shape.getBoundsInParent());
    }

    public CollisionObject(CollisionMenuController cmc) throws IOException {
        vX = 0;
        vY = 0;
        this.parameters = createParametersPane();
    }

    public CollisionObject() {
        vX = 0;
        vY = 0;
    }

    public boolean intersects(CollisionObject s) {
        //return s.getBoundary().intersects(this.getBoundary());        
        Bounds sBounds = s.getShape().localToScene(s.getShape().getBoundsInLocal());
        return shape.intersects(sBounds);

    }

    public void update() {

        this.shape.setTranslateX(vX);
        this.shape.setTranslateX(vY);
        this.setPosX(posX + vX);
        this.setPosY(posY + vY);

    }

    private AnchorPane createParametersPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
        loader.setController(parametersController);
        parametersController.initialize();
        return loader.load();
    }

    public AnchorPane getParameters() {
        return parameters;
    }

    public void setParameters(AnchorPane parameters) {
        this.parameters = parameters;
    }

    public Image getImage() {
        return image;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        this.getShape().setLayoutX(posX);
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
        this.getShape().setLayoutY(posY);
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
        this.shape.setTranslateX(vX);
    }

    public double getVelocityY() {
        return vY;

    }

    public void setVelocityY(double velocityY) {
        this.vY = velocityY;
        this.shape.setTranslateY(vY);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getCollisionBounds() {
        return collidingShape;
    }

    public void setCollisionBounds(Shape collisionBounds) {
        this.collidingShape = collisionBounds;
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
//    public ParametersController getParametersController() {
//        return parametersController;
//    }
//
//    public void setParametersController(ParametersController parametersController) {
//        this.parametersController = parametersController;
//    }
//    

}
