package edu.vanier.collisionsimulator.tests;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import edu.vanier.collisionsimulator.simulator.CircleObj;
import edu.vanier.collisionsimulator.ui.ParametersController;
import javafx.scene.layout.BorderPane;

public class UIDriver extends Application {

    //private ArrayList<Shape> nodes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Drag circles around to see collisions");

        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu3.fxml"));
            edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController();
            loader.setController(menuController);
            BorderPane root = loader.load();
            CircleObj circle1 = new CircleObj(15, 20, 35, 50, 54, menuController);
            
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}