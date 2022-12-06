package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.controllers.CustomVector;
import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import edu.vanier.collisionsimulator.ui.ParametersController;
import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
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
    private String imgFilePath;
    private Image image;
    private VisualVector vv;
    protected CustomVector v;
    protected double posX, posY, mass;
    protected double speed;
    protected double direction;
    protected double size = 1;
    protected boolean sizeScaling;

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

    public void updateImage(String filePath){
        this.setImgFilePath(filePath);
        this.getShape().setFill(new ImagePattern(new Image("images/" + filePath + ".png")));
    }
    
    public void update() {

        double newPosX = posX + v.x;

        double newPosY = posY + v.y;
        this.setPosX(newPosX);
        this.setPosY(newPosY);
        this.getVv().update();

    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
        //this.shape.setScaleX((60 + (size - 1) * 40) / width);
        //this.shape.setScaleY((60 + (size - 1) * 40) / height);
        this.width = 60 + (size - 1) * 40;
        this.height = 60 + (size - 1) * 40;
        this.shape.resize(width, height);
        
    }

    public final void setMouseListener(CollisionMenuController cmc) {

        this.shape.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.highlight();
            cmc.getParametersPane().getChildren().setAll(parameters);
            parametersController.displayParameters();

            if (mouseEvent.getButton() == MouseButton.SECONDARY) {

                this.shape.getParent().setOnMouseClicked((MouseEvent event2) -> {
                    if (!this.getShape().getStroke().equals(Color.TRANSPARENT)) {
                        if (Math.sqrt(Math.pow(event2.getX() - this.getPosX(), 2) + (Math.pow(event2.getY() - this.getPosY(), 2))) < this.width) {
                        } else if (event2.getButton() == MouseButton.SECONDARY) {
                            CustomVector newVel = new CustomVector((event2.getSceneX() - this.getPosX()) * 0.05, (event2.getSceneY() - this.getPosY()) * 0.05);
                            if (newVel.computeLength() > 50) {
                                this.setV(newVel.normalize().scalarMult(50));
                            } else {
                                this.setV(newVel);
                            };

                            this.vv.update();
                        };
                    };
                });
            };

        });

    }

    ;

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
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
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
            };
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

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }
    
    
    
     public boolean isSizeScaling() {
        return sizeScaling;
    }

    public void setSizeScaling(boolean sizeScaling) {
        this.sizeScaling = sizeScaling;
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

        this.shape.setStrokeWidth(4);
        this.shape.setStroke(Color.valueOf("55CCFF"));

        KeyFrame kf1 = new KeyFrame(Duration.millis(3000), new KeyValue(this.shape.strokeProperty(), Color.TRANSPARENT, Interpolator.LINEAR));

        Timeline t = new Timeline(kf1);
        t.play();

    }
;

}
