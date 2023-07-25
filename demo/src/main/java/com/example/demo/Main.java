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
    Model model = new Model();

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
        scrollPane.setPrefSize(Settings.scrollPaneWidth, Settings.scrollPaneHeight);
        scrollPane.setStyle("-fx-background-color: bbaaff");
        leftPane.setCenter(scrollPane);

        VBox mainVBox = new VBox();
        mainVBox.setPrefWidth(Settings.vBoxWidth);
        mainVBox.setSpacing(Settings.vBoxSpacing);
        mainVBox.setStyle("-fx-background-color: abfbaf");
        scrollPane.setContent(mainVBox);

//        Наполнение скрол панели
        Label label = new Label("Settings");
        label.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        label.setAlignment(Pos.CENTER);
        mainVBox.getChildren().add(label);

        Button button1 = new Button("Reset"); // переместить частицы в начальное положение
        button1.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        button1.setOnAction(actionEvent -> {
            model.resetParticles();
        });
        mainVBox.getChildren().add(button1);

        Button button2 = new Button("Random seed"); // случайные правила + пересоздать частицы
        button2.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        button2.setOnAction(actionEvent -> {
            Settings.randomSeed();
            model.recreate();
        });
        mainVBox.getChildren().add(button2);

//        generate VBoxes
        VBox baseSettingsVBox = createBaseSettingsVBox();
        mainVBox.getChildren().add(baseSettingsVBox);

        VBox varilableVBox = createVariableVBox(Settings.countOfColors);
        mainVBox.getChildren().add(varilableVBox);

//        Commit
        ToggleButton toggleButton = new ToggleButton("Auto commit");
        toggleButton.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        mainVBox.getChildren().add(toggleButton);

        Button button3 = new Button("Commit");
        button3.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        mainVBox.getChildren().add(button3);

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

//        Загружаем сцену
        Scene scene = new Scene(group, Settings.screenWidth, Settings.screenHeight);
        stage.setTitle("Particles simulation");
        stage.setScene(scene);
        stage.show();

//        Запуск модели
        model.init(graphicsContext);
        model.start();
    }

    private VBox createBaseSettingsVBox(){
        VBox vBox = new VBox();
        vBox.setSpacing(4);
        vBox.setStyle("-fx-background-color: aabbcc");

        HBox hBox1 = new HBox();
        hBox1.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox1.setSpacing(Settings.hBoxSpacing);
        hBox1.setStyle("-fx-background-color: ffcccc");
        Label labelHBox1 = new Label("Count of colors:");
        labelHBox1.setPrefSize(175, Settings.vBoxContentHeight);
        hBox1.getChildren().add(labelHBox1);
        TextField textFieldHBox1 = new TextField(Integer.toString(Settings.countOfColors));
        textFieldHBox1.setOnAction(actionEvent -> {
//            Settings set count of colors
        });
        textFieldHBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox1.getChildren().add(textFieldHBox1);
        vBox.getChildren().add(hBox1);

        HBox hBox2 = new HBox();
        hBox2.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox2.setSpacing(Settings.hBoxSpacing);
        hBox2.setStyle("-fx-background-color: ffcccc");
        Label labelHBox2 = new Label("Size of particles:");
        labelHBox2.setPrefSize(175, Settings.vBoxContentHeight);
        hBox2.getChildren().add(labelHBox2);
        TextField textFieldHBox2 = new TextField(Double.toString(Settings.sizeOfParticles));
        textFieldHBox2.setOnAction(actionEvent -> {
            Settings.setSizeOfParticles(Double.parseDouble(textFieldHBox2.getText()));
        });
        textFieldHBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox2.getChildren().add(textFieldHBox2);
        vBox.getChildren().add(hBox2);

        HBox hBox3 = new HBox();
        hBox3.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox3.setSpacing(Settings.hBoxSpacing);
        hBox3.setStyle("-fx-background-color: ffcccc");
        Label labelHBox3 = new Label("Force distance:");
        labelHBox3.setPrefSize(175, Settings.vBoxContentHeight);
        hBox3.getChildren().add(labelHBox3);
        TextField textFieldHBox3 = new TextField(Double.toString(Settings.forceDistance));
        textFieldHBox3.setOnAction(actionEvent -> {
            Settings.setForceDistance(Double.parseDouble(textFieldHBox3.getText()));
        });
        textFieldHBox3.setAlignment(Pos.CENTER_RIGHT);
        hBox3.getChildren().add(textFieldHBox3);
        vBox.getChildren().add(hBox3);

        HBox hBox4 = new HBox();
        hBox4.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox4.setSpacing(Settings.hBoxSpacing);
        hBox4.setStyle("-fx-background-color: ffcccc");
        Label labelHBox4 = new Label("Friction force:");
        labelHBox4.setPrefSize(175, Settings.vBoxContentHeight);
        hBox4.getChildren().add(labelHBox4);
        TextField textFieldHBox4 = new TextField(Double.toString(Settings.frictionForce));
        textFieldHBox4.setOnAction(actionEvent -> {
            Settings.setFrictionForce(Double.parseDouble(textFieldHBox4.getText()));
        });
        textFieldHBox4.setAlignment(Pos.CENTER_RIGHT);
        hBox4.getChildren().add(textFieldHBox4);
        vBox.getChildren().add(hBox4);

        HBox hBox5 = new HBox();
        hBox5.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox5.setSpacing(Settings.hBoxSpacing);
        hBox5.setStyle("-fx-background-color: ffcccc");
        Label labelHBox5 = new Label("Weigh error:");
        labelHBox5.setPrefSize(175, Settings.vBoxContentHeight);
        hBox5.getChildren().add(labelHBox5);
        TextField textFieldHBox5 = new TextField(Double.toString(Settings.weightError));
        textFieldHBox5.setOnAction(actionEvent -> {
            Settings.setWeightError(Double.parseDouble(textFieldHBox5.getText()));
            model.resetParticles();
        });
        textFieldHBox5.setAlignment(Pos.CENTER_RIGHT);
        hBox5.getChildren().add(textFieldHBox5);
        vBox.getChildren().add(hBox5);

        HBox hBox6 = new HBox();
        hBox6.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox6.setSpacing(Settings.hBoxSpacing);
        hBox6.setStyle("-fx-background-color: ffcccc");
        Label labelHBox6 = new Label("Atomic force:");
        labelHBox6.setPrefSize(175, Settings.vBoxContentHeight);
        hBox6.getChildren().add(labelHBox6);
        TextField textFieldHBox6 = new TextField(Double.toString(Settings.atomicForce));
        textFieldHBox6.setOnAction(actionEvent -> {
            Settings.setAtomicForce(Double.parseDouble(textFieldHBox6.getText()));
        });
        textFieldHBox6.setAlignment(Pos.CENTER_RIGHT);
        hBox6.getChildren().add(textFieldHBox6);
        vBox.getChildren().add(hBox6);

        HBox hBox7 = new HBox();
        hBox7.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
        hBox7.setSpacing(Settings.hBoxSpacing);
        hBox7.setStyle("-fx-background-color: ffcccc");
        Label labelHBox7 = new Label("Atomic distance:");
        labelHBox7.setPrefSize(175, Settings.vBoxContentHeight);
        hBox7.getChildren().add(labelHBox7);
        TextField textFieldHBox7 = new TextField(Double.toString(Settings.atomicForceDistance));
        textFieldHBox7.setOnAction(actionEvent -> {
            Settings.setAtomicForceDistance(Double.parseDouble(textFieldHBox7.getText()));
        });
        textFieldHBox7.setAlignment(Pos.CENTER_RIGHT);
        hBox7.getChildren().add(textFieldHBox7);
        vBox.getChildren().add(hBox7);

        return vBox;
    }
    public VBox createVariableVBox(int numberOfColors) {
        VBox vBox = new VBox();
        vBox.setSpacing(8);
        vBox.setStyle("-fx-background-color: aabbcc");

        for (int i = 0; i < numberOfColors; i++) {
            VBox tempVBox = new VBox();
            tempVBox.setStyle("-fx-background-color: ffcccc");
            tempVBox.setSpacing(1);
            for (int j = 0; j < numberOfColors; j++) {
                HBox hBox1 = new HBox();
                hBox1.setPrefSize(Settings.vBoxWidth, Settings.vBoxContentHeight);
                hBox1.setSpacing(Settings.hBoxSpacing);
                Label labelHBox1 = new Label("color " + (i + 1) + " to " + (j + 1) + ":");
                labelHBox1.setPrefSize(150, Settings.vBoxContentHeight);
                hBox1.getChildren().add(labelHBox1);
                TextField textFieldHBox1 = new TextField("force");
                textFieldHBox1.setAlignment(Pos.CENTER_RIGHT);
                hBox1.getChildren().add(textFieldHBox1);
                tempVBox.getChildren().add(hBox1);
            }
            vBox.getChildren().add(tempVBox);
        }
        return vBox;
    }
}

