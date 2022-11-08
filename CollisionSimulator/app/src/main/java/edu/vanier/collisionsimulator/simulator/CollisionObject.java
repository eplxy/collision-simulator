package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import edu.vanier.collisionsimulator.ui.ParametersController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 *
 * @author stella
 */
//mostly pulled from Assignment 2
public abstract class CollisionObject {

    ParametersController parametersController;

    protected Pane parameters;
    protected double width, height;
    protected int index;

    //TODO: define these as position values, replace usage of getShape().LayoutX/Y
    protected double centerX;
    protected double centerY;

    protected Shape shape;
    protected Shape collidingShape;

    protected Paint color;

    //FOR FUTURE USE IN APPLYING IMAGES ONTO OBJECTS
    private Image image;

    protected double posX, posY, vX, vY, mass;

    public boolean collide(CollisionObject other) {

        Shape otherSphere = other.collidingShape;
        Shape thisSphere = collidingShape;
        Point2D otherCenter = otherSphere.localToScene(otherSphere.getLayoutX(), otherSphere.getLayoutY());
        Point2D thisCenter = thisSphere.localToScene(thisSphere.getLayoutX(), thisSphere.getLayoutY());
        double dx = otherCenter.getX() - thisCenter.getX();
        double dy = otherCenter.getY() - thisCenter.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double minDist = this.getWidth() + other.getWidth();

        return (distance < minDist);

        //FIXME: Redefine collision detection by using width/height & most importantly, direct vector distance for circle objects.
        //return collidingShape.getBoundsInParent().intersects(other.shape.getBoundsInParent());
    }

    public CollisionObject(CollisionMenuController cmc) throws IOException {
        vX = 0;
        vY = 0;
        this.parameters = createParametersPane(cmc);
        //setMouseListener(cmc);
    }

    public CollisionObject() throws IOException {
        vX = 0;
        vY = 0;
    }

    public boolean intersects(CollisionObject s) {
        //return s.getBoundary().intersects(this.getBoundary());        
        Bounds sBounds = s.getShape().localToScene(s.getShape().getBoundsInLocal());
        return shape.intersects(sBounds);

    }

    public void update() {

        /*FIXME: issue with border rendering. if velocity is greater than
        the distance to border, then it will send it outside the borders for a frame
        and bounce it back.
        
        check if new position will be within bounds or not and handle accordingly
        */
        double newPosX = posX + vX;
        double newPosY = posY + vY;

        this.setPosX(posX + vX);
        this.setPosY(posY + vY);

    }

    public final void setMouseListener(CollisionMenuController cmc) {
        this.shape.setOnMouseClicked((MouseEvent mouseEvent) -> {
            cmc.getParametersPane().getChildren().setAll(parameters);
        });
    }

    private Pane createParametersPane(CollisionMenuController cmc) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
        ParametersController pctrl = new ParametersController(this, cmc);
        loader.setController(pctrl);
        return loader.load();

    }

    public Pane getParameters() {
        return parameters;
    }

    public void setParameters(Pane parameters) {
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
        return this.shape;
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
