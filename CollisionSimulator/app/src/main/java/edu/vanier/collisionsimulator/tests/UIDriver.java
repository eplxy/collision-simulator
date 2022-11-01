package edu.vanier.collisionsimulator.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import edu.vanier.collisionsimulator.simulator.CircleObj;
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
            CircleObj circle1 = new CircleObj(menuController);
            
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}