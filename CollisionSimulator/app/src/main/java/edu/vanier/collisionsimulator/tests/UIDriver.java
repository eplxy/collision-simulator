package edu.vanier.collisionsimulator.tests;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.layout.AnchorPane;

public class UIDriver extends Application {

    private ArrayList<Shape> nodes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Drag circles around to see collisions");
        Group animation = new Group();

        nodes = new ArrayList<>();
        nodes.add(new Circle(500, 400, 50));
        nodes.add(new Circle(250, 600, 50));
        nodes.add(new Circle(40, 200, 50));
        for (Shape block : nodes) {
            setDragListeners(block);
        }
        animation.getChildren().addAll(nodes);
        checkShapeIntersection(nodes.get(nodes.size() - 1));

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu.fxml"));
            AnchorPane root = loader.load();
            root.getChildren().add(animation);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        };
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
