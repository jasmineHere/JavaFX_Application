package org.example.javafx_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskView.fxml"));
            Parent root = loader.load();


            TaskController controller = loader.getController();
            controller.setTaskManager(new TaskManager());
            System.out.println("Jiggly is jigglying");

            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.setTitle("Task Manager");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();// Rethrow the exception for further analysis
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
