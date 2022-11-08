/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.tests;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 2156586
 */
public class fxmlDriver extends Application{
   


public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, RuntimeException {
FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
   TestController controller = new TestController();
    loader.setController(controller);
    
    Pane topPane  =  loader.load(); 
    //controller.initialize();

    
    loader = new FXMLLoader(getClass().getResource("/fxml/objectParameters.fxml"));
    TestController controller2 = new TestController();
    loader.setController(controller2);
    Pane bottomPane  =  loader.load();

    Scene scene = new Scene(new VBox(topPane, bottomPane));
    primaryStage.setScene(scene);
    primaryStage.show();
}}