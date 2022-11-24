//
//FOR TEST PURPOSES ONLY. NEW MAIN CLASS -> UI.COLLISIONSIMULATORAPP
//
//

package edu.vanier.collisionsimulator.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import edu.vanier.collisionsimulator.simulator.CircleObject;
import edu.vanier.collisionsimulator.simulator.CollisionObject;
import edu.vanier.collisionsimulator.simulator.Simulation;
import javafx.scene.layout.BorderPane;

public class UIDriver extends Application {

    //private ArrayList<Shape> nodes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Main Menu");

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            edu.vanier.collisionsimulator.ui.MainMenuController menuController = new edu.vanier.collisionsimulator.ui.MainMenuController(primaryStage);
            loader.setController(menuController);
            BorderPane root = loader.load();
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            
            primaryStage.show(); 
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
}