package edu.vanier.collisionsimulator.ui;

import edu.vanier.collisionsimulator.simulator.SavedSim;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina Amoura, Steven Lam, Matthew Hantar
 */
public class CollisionSimulatorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Main Menu");
        initializeSavedSimsList();
        initializeImageList();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            MainMenuController menuController = new MainMenuController(primaryStage);
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

    /***
     * Loads the saved simulation csv files into the observable list savedSimList in the class SavedSim
     * Part of the code is from https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
     */
    private void initializeSavedSimsList() {
        File[] files = new File("src/main/resources/savedSim/").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                SavedSim.getSavedSimList().add(file.getName().substring(0, file.getName().length()-4));
            }
        }
    }
    
    /***
     * Loads the files in the images folder into the observable list savedImagesList in the class ParametersController
     * Part of the code is from https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
     */
    private void initializeImageList() {
        File[] files = new File("src/main/resources/images/").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                ParametersController.getSavedImageList().add(file.getName().substring(0, file.getName().length()-4));
            }
        }
    }

}
