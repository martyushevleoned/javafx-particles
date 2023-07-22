package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Group group = new Group();
        Scene scene = new Scene(group);

        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        group.getChildren().add(content);

        stage.setTitle("Particles simulation");
        stage.setScene(scene);
        stage.show();
    }
}