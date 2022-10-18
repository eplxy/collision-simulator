package edu.vanier.template;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import edu.vanier.UI.MainMenuController;
import javafx.scene.layout.AnchorPane;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 18 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/18/
 * @see: Build Scripts/build.gradle
 * @author Sleiman Rabah.
 */
public class MainApp extends Application {
//Test 
    /**
     * Lets use another template because we cant rename it.
     */
    
    private ArrayList<Shape> nodes;

  public static void main(String[] args) { launch(args); }

  @Override public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Drag circles around to see collisions");
    Group animation = new Group();

    nodes = new ArrayList<>();
    nodes.add(new Circle(15, 15, 30));
    nodes.add(new Circle(90, 60, 30));
    nodes.add(new Circle(40, 200, 30));
    for (Shape block : nodes) {
      setDragListeners(block);
    }
    animation.getChildren().addAll(nodes);
    checkShapeIntersection(nodes.get(nodes.size() - 1));

    
        try{
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollisionMenu.fxml"));
        AnchorPane root = loader.load();
        root.getChildren().add(animation);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("This is a JavaFX app template...");
        primaryStage.sizeToScene();
        primaryStage.show();}
        catch (Exception e){
            System.out.println(e);};
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

  class Delta { double x, y; }
}