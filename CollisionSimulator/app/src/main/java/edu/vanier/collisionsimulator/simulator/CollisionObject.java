package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.controllers.CollisionController;
import edu.vanier.collisionsimulator.controllers.CustomVector;
import edu.vanier.collisionsimulator.tests.AnimationDriver;
import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import edu.vanier.collisionsimulator.ui.ParametersController;
import java.io.IOException;
import java.util.stream.Stream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author stella
 */
//mostly pulled from Assignment 2
public abstract class CollisionObject {

    protected ParametersController parametersController;

    protected Pane parameters;
    protected double width, height;
    protected int index;

    protected Shape shape;
    protected Shape collidingShape;

    protected Paint color;

    //FOR FUTURE USE IN APPLYING IMAGES ONTO OBJECTS
    private Image image;
    private VisualVector vv;
    protected CustomVector v;
    protected double posX, posY, mass;
    protected double speed;
    protected double direction;

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

    }

    public CollisionObject(CollisionMenuController cmc) throws IOException {

        v = new CustomVector(0, 0);
        this.vv = new VisualVector(this);
        this.parameters = createParametersPane(cmc);
    }

    public CollisionObject() throws IOException {
        v = new CustomVector(0, 0);
        VisualVector vv = new VisualVector(this);
    }

    public void update() {

        double newPosX = posX + v.x;
        double newPosY = posY + v.y;
        this.setPosX(newPosX);
        this.setPosY(newPosY);
        this.getVv().update();

    }

    public final void setMouseListener(CollisionMenuController cmc) {

        this.shape.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.highlight();
            cmc.getParametersPane().getChildren().setAll(parameters);
            parametersController.displayParameters();
        });
    }

    public final void setDragListeners(CollisionMenuController cmc) {
        final Delta dragDelta = new Delta();
        this.shape.setOnMousePressed((MouseEvent mouseEvent) -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = this.getPosX() - mouseEvent.getSceneX();
            dragDelta.y = this.getPosY() - mouseEvent.getSceneY();
            //this.shape.setCursor(Cursor.NONE);
        });
        this.shape.setOnMouseReleased((MouseEvent mouseEvent) -> {
            //this.shape.setCursor(Cursor.HAND);
        });
        this.shape.setOnMouseDragged((MouseEvent mouseEvent) -> {
            //stops dragging if the object is about to leave the animation pane
            if (mouseEvent.getSceneX() + dragDelta.x + width > cmc.getAnimationPane().getLayoutBounds().getMaxX()
                    || mouseEvent.getSceneX() + dragDelta.x - width < cmc.getAnimationPane().getLayoutBounds().getMinX()
                    || mouseEvent.getSceneY() + dragDelta.y + height > cmc.getAnimationPane().getLayoutBounds().getMaxY()
                    || mouseEvent.getSceneY() + dragDelta.y - height < cmc.getAnimationPane().getLayoutBounds().getMinY()) {
                return;
            }

            this.setPosX(mouseEvent.getSceneX() + dragDelta.x);
            this.setPosY(mouseEvent.getSceneY() + dragDelta.y);
            this.getVv().getVisVector().setStartX(this.getPosX());
            this.getVv().getVisVector().setStartY(this.getPosY());
            this.getVv().getVisVector().setEndX(this.getVv().getVisVector().getStartX() + this.getVelocityX() * 20);
            this.getVv().getVisVector().setEndY(this.getVv().getVisVector().getStartY() + this.getVelocityY() * 20);
        });
    }

    class Delta {

        double x, y;
    }

    private Pane createParametersPane(CollisionMenuController cmc) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
        ParametersController pctrl = new ParametersController(this, cmc);
        loader.setController(pctrl);
        this.parametersController = pctrl;
        return loader.load();

    }

    public VisualVector getVv() {
        return vv;
    }

    public void setVv(VisualVector vv) {
        this.vv = vv;
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

    public CustomVector getV() {
        return this.v;
    }

    public void setV(CustomVector v) {
        this.v = v;
    }

    public double getVelocityX() {
        return v.x;
    }

    public void setVelocityX(double velocityX) {
        this.v.x = velocityX;
        this.shape.setTranslateX(v.x);
    }

    public double getVelocityY() {
        return v.y;

    }

    public void setVelocityY(double velocityY) {
        this.v.y = velocityY;
        this.shape.setTranslateY(v.y);
    }

    public double getSpeed() {
        return Math.sqrt(Math.pow(this.v.x, 2) + Math.pow(this.v.y, 2));
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.v = v.normalize().scalarMult(speed);

    }

    public double getDirection() {
        this.direction = v.getAngle();

        return this.direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
        CustomVector newV = new CustomVector(true, this.getSpeed(), direction);
        this.v = newV;
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

    public void highlight() {
        Stop[] stops = new Stop[]{
            new Stop(0, Color.AQUA),
            new Stop(0.25, Color.RED),
            new Stop(0.5, Color.GREEN),
            new Stop(0.75, Color.YELLOW),
            new Stop(1, Color.AQUAMARINE)
        };

        RadialGradient rg = new RadialGradient(0.0, 0.0, this.posX, this.posY, this.width, true, CycleMethod.NO_CYCLE, stops);
        this.shape.setStroke(rg);

        this.shape.setStrokeWidth(4);
        EventHandler<ActionEvent> onFinished = (event) -> {
            this.shape.setStroke(Color.TRANSPARENT);
            this.shape.setStrokeWidth(1);
        };
        KeyFrame kf1 = new KeyFrame(Duration.millis(3000), onFinished);

        Timeline t = new Timeline(kf1);

        t.play();
    }
;

}
