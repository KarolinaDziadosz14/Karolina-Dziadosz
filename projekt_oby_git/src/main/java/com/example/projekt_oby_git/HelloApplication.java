package com.example.projekt_oby_git;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static AnchorPane root;//publiczny odnosnik do planszyh
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root= fxmlLoader.load();
        Scene scene = new Scene(root, 944, 634);
        stage.setTitle("Hello!");
        stage.show();
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}

