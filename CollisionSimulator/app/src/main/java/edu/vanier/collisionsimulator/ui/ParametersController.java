/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author sabri
 */
public class ParametersController {

    private static ObservableList savedImageList = FXCollections.observableArrayList();

    public static ObservableList getSavedImageList() {
        return savedImageList;
    }

    public static void setSavedImageList(ObservableList savedImageList) {
        ParametersController.savedImageList = savedImageList;
    }

    CollisionObject obj;
    CollisionMenuController cmc;
    @FXML
    TextField massTxtField;
    @FXML
    TextField speedTxtField;
    @FXML
    TextField posXTxtField;
    @FXML
    TextField posYTxtField;
    @FXML
    TextField directionTxtField;
    @FXML
    Button btnRemoveObj;
    @FXML
    CheckBox sizeCheck;
    @FXML
    Slider sizeSlider;
    @FXML
    TextArea txtAreaError;
    @FXML
    Button imageButton1;

    String errorMsg = "";

    @FXML
    ListView listViewImages;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    Button btnEnter;

    public ParametersController(CollisionObject obj, CollisionMenuController cmc) {
        this.obj = obj;
        this.cmc = cmc;
    }

    @FXML
    public void initialize() {

        btnEnter.setOnAction((event) -> {
            handleEnter(event);
        });
        btnRemoveObj.setOnAction((event) -> {
            handleRemove(event);
        });
        imageButton1.setOnAction((event) -> {
            handleImage1(event);
        });

        handleSizeCheck(sizeCheck.isSelected());
        sizeCheck.selectedProperty().addListener((obs, oldVal, newVal) -> {
            handleSizeCheck(sizeCheck.isSelected());
        });

        listViewImages.setItems(savedImageList);
        imageButton1.setOnAction((event) -> {
            handleImage1(event);
        });
    }

    @FXML
    public void handleEnter(ActionEvent event) {
        boolean[] validation = {inputValidationSpeed(Double.parseDouble(speedTxtField.getText())),
            inputValidationMass(Double.parseDouble(massTxtField.getText())),
            inputValidationPosX(Double.parseDouble(posXTxtField.getText())),
            inputValidationPosY(Double.parseDouble(posYTxtField.getText())),
            inputValidationDirection(Double.parseDouble(directionTxtField.getText()))
        };
        if (areAllTrue(validation)) {
            obj.setMass(Double.parseDouble(massTxtField.getText()));
            obj.setPosY(Double.parseDouble(posYTxtField.getText()));
            obj.setPosX(Double.parseDouble(posXTxtField.getText()));
            obj.setSpeed(Double.parseDouble(speedTxtField.getText()));
            obj.setDirection(Double.parseDouble(directionTxtField.getText()));
            obj.getVv().update();

            if (sizeCheck.isSelected()) {
                obj.setSizeScaling(true);
                obj.setSize((obj.getMass() / 1000) * 2 + 1);
                sizeSlider.setValue(obj.getSize());
            } else {
                obj.setSizeScaling(false);
                obj.setSize(sizeSlider.getValue());
            };
        }
        txtAreaError.setText(errorMsg);
        errorMsg = "";
    }

    //https://stackoverflow.com/questions/8260881/what-is-the-most-elegant-way-to-check-if-all-values-in-a-boolean-array-are-true
    public static boolean areAllTrue(boolean[] array) {
        for (boolean b : array) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public void handleImage1(ActionEvent event) {
        ObservableList selectedIndices = listViewImages.getSelectionModel().getSelectedIndices();

        for (Object o : selectedIndices) {
            int index = (int) o;
            this.obj.updateImage((String) savedImageList.get(index));
        }
    }

    public boolean inputValidationSpeed(double input) {
        if (input >= -50 && input <= 50) {
            return true;
        } else {
            errorMsg += ("\nThe speed must be between -50 and 50 m/s.");
            return false;
        }
    }

    private void handleSizeCheck(boolean isSelected) {
        if (isSelected == true || obj.isSizeScaling()) {
            sizeCheck.setSelected(true);
            sizeSlider.setDisable(true);
        } else if (isSelected == false || !obj.isSizeScaling()) {
            sizeCheck.setSelected(false);
            sizeSlider.setDisable(false);
        }
    }

    public boolean inputValidationMass(double input) {
        if (input > 0 && input <= 1000) {
            return true;
        } else {
            errorMsg += ("\nThe mass must be between 0 and 1000 kg.");
            return false;
        }
    }

    public boolean inputValidationPosX(double input) {
        if (input >= 0 && input <= cmc.getAnimationPane().getWidth()) {
            return true;
        } else {
            errorMsg += ("\nInvalid position x.");
            return false;
        }
    }

    public boolean inputValidationPosY(double input) {
        if (input >= 0 && input <= cmc.getAnimationPane().getHeight()) {
            return true;
        } else {
            errorMsg += ("\nInvalid position y.");
            return false;
        }
    }

    public boolean inputValidationDirection(double input) {
        if (input >= -360 && input <= 360) {
            return true;
        } else {
            errorMsg += ("\nThe direction must be between -360 and 360 degrees");
            return false;
        }
    }

    public void handleRemove(ActionEvent event) {
        cmc.getSim().getCom().addCollisionObjectsToBeRemoved(obj);
        cmc.getSim().getCom().cleanupCollisionObjects();
        cmc.getAnimationPane().getChildren().remove(obj.getVv().getVisVector());
        //int index = cmc.getAnimationPane().getChildren().indexOf(obj);
        cmc.getAnimationPane().getChildren().remove(obj.getShape());
        
        if(cmc.getSim().getCom().getAllColObjs().size()<30){
            cmc.btnAddObj.setDisable(false);
        }
        this.cmc.getParametersPane().getChildren().setAll(new Pane());
    }

    public void displayParameters() {
        posXTxtField.setText(df.format(obj.getPosX()));
        posYTxtField.setText(df.format(obj.getPosY()));
        massTxtField.setText(df.format(obj.getMass()));
        speedTxtField.setText(df.format(getNormSpeed()));
        directionTxtField.setText(df.format(obj.getDirection()));
        sizeSlider.setValue(obj.getSize());
    }

    public double getNormSpeed() {
        return (Math.sqrt(Math.pow(obj.getVelocityX(), 2) + Math.pow(obj.getVelocityY(), 2)));
    }

    @FXML
    public TextField getMassTxtField() {
        return massTxtField;
    }

    @FXML
    public void setMassTxtField(TextField massTxtField) {
        this.massTxtField = massTxtField;
    }

    @FXML
    public TextField getSpeedTxtField() {
        return speedTxtField;
    }

    @FXML
    public void setSpeedTxtField(TextField speedTxtField) {
        this.speedTxtField = speedTxtField;
    }

    @FXML
    public TextField getPosXTxtField() {
        return posXTxtField;
    }

    public void setPosXTxtField(TextField posXTxtField) {
        this.posXTxtField = posXTxtField;
    }

    public TextField getPosYTxtField() {
        return posYTxtField;
    }

    public void setPosYTxtField(TextField posYTxtField) {
        this.posYTxtField = posYTxtField;
    }

}
