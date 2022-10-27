package edu.vanier.collisionsimulator.simulator;

import edu.vanier.collisionsimulator.ui.CollisionMenuController;
import edu.vanier.collisionsimulator.ui.ParametersController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 *
 * @author stella
 */
//mostly pulled from Assignment 2
public abstract class CollisionObject {
    
    ParametersController parametersController = new ParametersController(this) ;

    protected AnchorPane parameters;
    protected double width, height;
    protected int index;
    
    //TODO: issues with node =/= shape =/= circle
    
    protected Node node;
    protected Node collidingNode;
    
    
    protected Paint color;

    //FOR FUTURE USE IN APPLYING IMAGES ONTO OBJECTS
    private Image image;

    protected double posX, posY, vX, vY, mass;

    public boolean collide(CollisionObject other) {
        return collidingNode.getBoundsInParent().intersects(other.node.getBoundsInParent());
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
        Bounds sBounds = s.getNode().localToScene(s.getNode().getBoundsInLocal());
        return node.intersects(sBounds);

    }

    public void update(){
        
        this.node.setTranslateX(vX);
        this.node.setTranslateX(vY);
        this.setPosX(posX+vX);
        this.setPosY(posY+vY);
        
    }
    
    
    
    private  AnchorPane createParametersPane() throws IOException{
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
//    public ParametersController getParametersController() {
//        return parametersController;
//    }
//
//    public void setParametersController(ParametersController parametersController) {
//        this.parametersController = parametersController;
//    }
//    

    
}
