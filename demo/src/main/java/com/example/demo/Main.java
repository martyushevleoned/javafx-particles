package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Group group = new Group();

//        Основная панель
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(Settings.screenWidth, Settings.screenHeight);
        borderPane.setStyle("-fx-background-color: ccbbaa");
        group.getChildren().add(borderPane);

//        Левая часть панели
        BorderPane leftPane = new BorderPane();
        leftPane.setPrefSize(Settings.leftPaneWidth, Settings.screenHeight);
        leftPane.setPadding(new Insets(Settings.padding, Settings.padding, Settings.padding, Settings.padding));
        leftPane.setStyle("-fx-background-color: ffaaff");
        borderPane.setLeft(leftPane);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(Settings.scrollPaneWidth,Settings.scrollPaneHeight);
        scrollPane.setStyle("-fx-background-color: bbaaff");
        leftPane.setCenter(scrollPane);

        VBox vBox = new VBox();
        vBox.setPrefWidth(Settings.vBoxWidth);
        vBox.setSpacing(Settings.vBoxSpacing);
        vBox.setStyle("-fx-background-color: abfbaf");
        scrollPane.setContent(vBox);

//        Наполнение скрол панели
        Label label = new Label("Settings");
        label.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        label.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label);

        Button button1 = new Button("Reset");
        button1.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        vBox.getChildren().add(button1);

        Button button2 = new Button("Random seed");
        button2.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        vBox.getChildren().add(button2);

        HBox hBox1 = new HBox();
        hBox1.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox1.setSpacing(Settings.hBoxSpacing);
        hBox1.setStyle("-fx-background-color: aabbcc");
        Label labelHBox1 = new Label("Center force");
        labelHBox1.setPrefSize(150, Settings.vBoxContentHeight);
        hBox1.getChildren().add(labelHBox1);
        TextField textFieldHBox1 = new TextField();
        textFieldHBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox1.getChildren().add(textFieldHBox1);
        vBox.getChildren().add(hBox1);

        ToggleButton toggleButton = new ToggleButton("Auto commit");
        toggleButton.setPrefSize(Settings.vBoxWidth,Settings.vBoxContentHeight);
        vBox.getChildren().add(toggleButton);

        Button button3 = new Button("Commit");
        button3.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        vBox.getChildren().add(button3);

//        Провая часть панели
        BorderPane rightPane = new BorderPane();
        rightPane.setPrefSize(Settings.rightPaneWidth, Settings.screenHeight);
        rightPane.setPadding(new Insets(Settings.padding, Settings.padding, Settings.padding, Settings.padding));
        rightPane.setStyle("-fx-background-color: aabbcc");
        borderPane.setRight(rightPane);

        Canvas canvas = new Canvas();
        canvas.setWidth(Settings.canvasWidth);
        canvas.setHeight(Settings.canvasHeight);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        graphicsContext.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 40, 40);
        rightPane.setCenter(canvas);


        Scene scene = new Scene(group, Settings.screenWidth, Settings.screenHeight);
        stage.setTitle("Particles simulation");
        stage.setScene(scene);
        stage.show();

//        Запуск модели
        Model model = new Model();
        model.init(graphicsContext);
        model.start();
    }
}

