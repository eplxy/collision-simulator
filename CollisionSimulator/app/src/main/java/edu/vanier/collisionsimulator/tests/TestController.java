/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.tests;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author 2156586
 */
public class TestController {

    @FXML
    Button btnEnter;

    @FXML
    public  void initialize() {
        btnEnter.setOnAction((event) -> {
            handleEnter(event);
        });
    }      
    @FXML
    public void handleEnter(ActionEvent event){
        System.out.println("hehe");
    }
   
}
