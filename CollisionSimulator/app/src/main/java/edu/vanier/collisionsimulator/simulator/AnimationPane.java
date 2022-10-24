package edu.vanier.collisionsimulator.simulator;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 *
 * @author stella
 */
public class AnimationPane extends Pane {

    final double width, height;
    Bounds bounds;

    public AnimationPane(double width, double height) {
        this.height = height;
        this.width = width;
        bounds = this.getBoundsInLocal();
        
        this.setPrefSize(width, height);
    }

    public void changeBackground(String colorCode) {

        this.setStyle("-fx-background-color: #" + colorCode);

    }

    public Bounds getBounds() {
        return bounds;
    }

    
    
}
