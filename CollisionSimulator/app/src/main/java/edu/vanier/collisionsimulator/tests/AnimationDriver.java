//
//FOR TEST PURPOSES ONLY. NEW MAIN CLASS -> UI.COLLISIONSIMULATORAPP
//
//

package edu.vanier.collisionsimulator.tests;

import edu.vanier.collisionsimulator.simulator.Simulation;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.layout.BorderPane;

public class AnimationDriver extends Application {

    private ArrayList<Shape> nodes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Drag circles around to see collisions");
        BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1800, 1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Animation Test Window");
            primaryStage.sizeToScene();
            primaryStage.show();
            
        Simulation testsim = new Simulation(20);
        root.getChildren().add(testsim.animationPane);
        testsim.loop.play();
    }

    public void setDragListeners(final Shape block) {
        final Delta dragDelta = new Delta();

        block.setOnMousePressed((MouseEvent mouseEvent) -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = block.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = block.getLayoutY() - mouseEvent.getSceneY();
            block.setCursor(Cursor.NONE);
        });
        block.setOnMouseReleased((MouseEvent mouseEvent) -> {
            block.setCursor(Cursor.HAND);
        });
        block.setOnMouseDragged((MouseEvent mouseEvent) -> {
            block.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            block.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            checkShapeIntersection(block);
        });
    }

    private void checkShapeIntersection(Shape block) {
        boolean collisionDetected = false;
        for (Shape static_bloc : nodes) {
            if (static_bloc != block) {
                static_bloc.setFill(Color.GREEN);

                Shape intersect = Shape.intersect(block, static_bloc);
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    collisionDetected = true;
                }
            }
        }

        if (collisionDetected) {
            block.setFill(Color.BLUE);
        } else {
            block.setFill(Color.GREEN);
        }
    }

    class Delta {

        double x, y;
    }
}
