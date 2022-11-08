package edu.vanier.collisionsimulator.simulator;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 *
 * @author stella
 */
public class AnimationPane extends Pane {

    final double xMax, yMax;
    Bounds bounds;

    public AnimationPane(double width, double height) {
        this.yMax = height;
        this.xMax = width;
        bounds = this.getBoundsInLocal();
        
        this.setPrefSize(width, height);
    }

    public void changeBackground(String colorCode) {

        this.setStyle("-fx-background-color: #" + colorCode);

    }

    public double getxMax() {
        return xMax;
    }

    public double getyMax() {
        return yMax;
    }

    
    
    public Bounds getBounds() {
        return bounds;
    }

    
    
}
