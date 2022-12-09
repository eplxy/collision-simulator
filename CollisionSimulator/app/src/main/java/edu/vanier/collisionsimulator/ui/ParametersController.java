/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.CollisionObject;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Sabrina Amoura, Steven Lam, Wassim Yahia
 */
public class ParametersController {

    private static ObservableList savedImageList = FXCollections.observableArrayList();
    private static final DecimalFormat df = new DecimalFormat("0.00");

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
    Button btnChooseImage;
    String errorMsg = "";
    @FXML
    ListView listViewImages;
    @FXML
    Button btnEnter;

    /***
     * Sole constructor
     * Links each parametersPane to the CollisionObject and the CollisionMenu
     * @param obj
     * @param cmc 
     */
    public ParametersController(CollisionObject obj, CollisionMenuController cmc) {
        this.obj = obj;
        this.cmc = cmc;
    }

    /***
     * Sets event handling methods for the buttons, sliders and checkbox
     * Load the image choices in a list view
     */
    public void initialize() {
        btnEnter.setOnAction((event) -> {
            handleEnter(event);
        });
        
        btnRemoveObj.setOnAction((event) -> {
            handleRemove(event);
        });
        
        btnChooseImage.setOnAction((event) -> {
            handleImage1(event);
        });

        sizeCheck.selectedProperty().addListener((obs, oldVal, newVal) -> {
            handleSizeCheck(sizeCheck.isSelected());
        });

        listViewImages.setItems(savedImageList);
        btnChooseImage.setOnAction((event) -> {
            handleImage1(event);
        });
    }

    /***
     * Validate the user input
     * Set the parameters of the selected CollisionObject to the contents of the text fields if they are all valid
     * Display error messages if the user entered an invalid value inside a change field
     * @param event 
     */
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
            obj.setDirection(Double.parseDouble(directionTxtField.getText()));
            obj.setSpeed(Double.parseDouble(speedTxtField.getText()));
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

    /***
     * returns true only if all the booleans in the array are true
     * code from : https://stackoverflow.com/questions/8260881/what-is-the-most-elegant-way-to-check-if-all-values-in-a-boolean-array-are-true
     * @param array array with the boolean value of the input validation of each text field
     * @return 
     */
    public static boolean areAllTrue(boolean[] array) {
        for (boolean b : array) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /***
     * Changes the image of a CollisionObject with the one selected from the list of choices
     * @param event 
     */
    public void handleImage1(ActionEvent event) {
        ObservableList selectedIndices = listViewImages.getSelectionModel().getSelectedIndices();

        for (Object o : selectedIndices) {
            int index = (int) o;
            this.obj.updateImage((String) savedImageList.get(index));
        }
    }

    /***
     * @param input value the user entered in the speed text field
     * @return true if the input is between -50 and 50
     */
    public boolean inputValidationSpeed(double input) {
        if (input >= -50 && input <= 50) {
            return true;
        } else {
            errorMsg += ("\nThe speed must be between -50 and 50 m/s.");
            return false;
        }
    }

    /***
     * Allows the user to scale the size of the object with the mass if selected
     * @param isSelected associated with the check box
     */
    private void handleSizeCheck(boolean isSelected) {
        if (isSelected == true || obj.isSizeScaling()) {
            sizeCheck.setSelected(true);
            sizeSlider.setDisable(true);
        } else if (isSelected == false || !obj.isSizeScaling()) {
            sizeCheck.setSelected(false);
            sizeSlider.setDisable(false);
        }
    }

    /***
     * @param input value the user entered in the mass text field
     * @return true if the input is between 0 and 1000
     */
    public boolean inputValidationMass(double input) {
        if (input > 0 && input <= 1000) {
            return true;
        } else {
            errorMsg += ("\nThe mass must be between 0 and 1000 kg.");
            return false;
        }
    }

    /***
     * @param input value the user entered in the position x text field
     * @return true if the input is within the bounds of the animation pane
     */
    public boolean inputValidationPosX(double input) {
        if (input >= 0 && input <= cmc.getAnimationPane().getWidth()) {
            return true;
        } else {
            errorMsg += ("\nInvalid position x.");
            return false;
        }
    }

    /***
     * @param input value the user entered in the position y text field
     * @return true if the input is within the bounds of the animation pane
     */
    public boolean inputValidationPosY(double input) {
        if (input >= 0 && input <= cmc.getAnimationPane().getHeight()) {
            return true;
        } else {
            errorMsg += ("\nInvalid position y.");
            return false;
        }
    }

    /***
     * @param input value the user entered in the direction text field
     * @return true if the input is between -360 and 360
     */
    public boolean inputValidationDirection(double input) {
        if (input >= -360 && input <= 360) {
            return true;
        } else {
            errorMsg += ("\nThe direction must be between -360 and 360 degrees");
            return false;
        }
    }

    /***
     * Removes the selected CollisionObject
     * @param event 
     */
    public void handleRemove(ActionEvent event) {
        cmc.getSim().getCom().addCollisionObjectsToBeRemoved(obj);
        cmc.getSim().getCom().cleanupCollisionObjects();
        cmc.getAnimationPane().getChildren().remove(obj.getVv().getVisVector());
        cmc.getAnimationPane().getChildren().remove(obj.getShape());
        
        if(cmc.getSim().getCom().getAllColObjs().size()<30){
            cmc.btnAddObj.setDisable(false);
        }
        this.cmc.getParametersPane().getChildren().setAll(new Pane());
    }

    /***
     * Display the current parameters of the selected CollisionObject in the text fields
     */
    public void displayParameters() {
        posXTxtField.setText(df.format(obj.getPosX()));
        posYTxtField.setText(df.format(obj.getPosY()));
        massTxtField.setText(df.format(obj.getMass()));
        speedTxtField.setText(df.format(getNormSpeed()));
        directionTxtField.setText(df.format(obj.getDirection()));
        sizeSlider.setValue(obj.getSize());
    }

    /***
     * @return the norm of the speed vector of the selected object
     */
    public double getNormSpeed() {
        return (Math.sqrt(Math.pow(obj.getVelocityX(), 2) + Math.pow(obj.getVelocityY(), 2)));
    }

    public TextField getMassTxtField() {
        return massTxtField;
    }

    public void setMassTxtField(TextField massTxtField) {
        this.massTxtField = massTxtField;
    }

    public TextField getSpeedTxtField() {
        return speedTxtField;
    }

    public void setSpeedTxtField(TextField speedTxtField) {
        this.speedTxtField = speedTxtField;
    }

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
    
    public static ObservableList getSavedImageList() {
        return savedImageList;
    }

    public static void setSavedImageList(ObservableList savedImageList) {
        ParametersController.savedImageList = savedImageList;
    }

}
