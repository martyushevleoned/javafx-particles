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

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    Model model = new Model();
    public static ArrayList<TextField> textFields = new ArrayList<>();

    public void updateTextFields() {
        for (int i = 0; i < textFields.size(); i++) {
            Settings.forces[i] = Double.parseDouble(textFields.get(i).getText());
            model.commitChanges();
        }
    }

    @Override
    public void start(Stage stage) {

//        Загружаем сцену
        Group group = createGroup();
        Scene scene = new Scene(group, Settings.screenWidth, Settings.screenHeight);
        stage.setTitle("Particles simulation");
        stage.setScene(scene);
        stage.show();

//        Запуск модели
        model.start();
    }

    private Group createGroup() {
        Group group = new Group();

//        Основная панель
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(Settings.screenWidth, Settings.screenHeight);
        borderPane.setStyle("-fx-background-color: aabbcc");
        group.getChildren().add(borderPane);

//        Левая часть панели
        BorderPane leftPane = new BorderPane();
        leftPane.setPrefSize(Settings.leftPaneWidth, Settings.screenHeight);
        leftPane.setPadding(new Insets(Settings.padding, Settings.padding, Settings.padding, Settings.padding));
        borderPane.setLeft(leftPane);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(Settings.padding, Settings.padding, Settings.padding, Settings.padding));
        scrollPane.setPrefSize(Settings.scrollPaneWidth, Settings.scrollPaneHeight);
        leftPane.setCenter(scrollPane);

        VBox mainVBox = new VBox();
        mainVBox.setPrefWidth(Settings.vBoxWidth);
        mainVBox.setSpacing(Settings.vBoxSpacing);
        scrollPane.setContent(mainVBox);

//        Наполнение скрол панели
        Label label = new Label("Settings");
        label.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        label.setAlignment(Pos.CENTER);
        mainVBox.getChildren().add(label);

        Button button1 = new Button("Reset"); // переместить частицы в начальное положение
        button1.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        button1.setOnAction(actionEvent -> {
            model.resetParticles();
        });
        mainVBox.getChildren().add(button1);

        Button button2 = new Button("Random seed"); // случайные правила + пересоздать частицы
        button2.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        button2.setOnAction(actionEvent -> {
            Settings.randomSeed();
            model.commitChanges();
        });
        mainVBox.getChildren().add(button2);

//        generate VBoxes
        VBox baseSettingsVBox = createSettingsVBox();
        mainVBox.getChildren().add(baseSettingsVBox);

        VBox varilableVBox = createParticleRulesVBoxes();
        mainVBox.getChildren().add(varilableVBox);

//        Провая часть панели
        BorderPane rightPane = new BorderPane();
        rightPane.setPrefSize(Settings.rightPaneWidth, Settings.screenHeight);
        rightPane.setPadding(new Insets(Settings.padding, Settings.padding, Settings.padding, Settings.padding));
        borderPane.setRight(rightPane);

        Canvas canvas = new Canvas();
        canvas.setWidth(Settings.canvasWidth);
        canvas.setHeight(Settings.canvasHeight);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        model.initGraphicsContext(graphicsContext);
        rightPane.setCenter(canvas);

        return group;
    }

    private VBox createSettingsVBox() {
        VBox vBox = new VBox();
        vBox.setSpacing(Settings.vBoxBlockSpacing);
        vBox.setStyle("-fx-background-color: aabbcc");
        int textWidth = 95;

        HBox hBox2 = new HBox();
        hBox2.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        hBox2.setStyle("-fx-background-color: ffcccc");
        Label labelHBox2 = new Label("Size of particles:");
        labelHBox2.setPrefSize(textWidth, Settings.textFieldHeight);
        hBox2.getChildren().add(labelHBox2);
        TextField textFieldHBox2 = new TextField(Double.toString(Settings.sizeOfParticles));
        textFieldHBox2.setPrefWidth(Settings.vBoxWidth - textWidth);
        textFieldHBox2.setOnAction(actionEvent -> {
            Settings.setSizeOfParticles(Double.parseDouble(textFieldHBox2.getText()));
        });
        textFieldHBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox2.getChildren().add(textFieldHBox2);
        vBox.getChildren().add(hBox2);

        HBox hBox3 = new HBox();
        hBox3.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        hBox3.setStyle("-fx-background-color: ffcccc");
        Label labelHBox3 = new Label("Force distance:");
        labelHBox3.setPrefSize(textWidth, Settings.textFieldHeight);
        hBox3.getChildren().add(labelHBox3);
        TextField textFieldHBox3 = new TextField(Double.toString(Settings.forceDistance));
        textFieldHBox3.setPrefWidth(Settings.vBoxWidth - textWidth);
        textFieldHBox3.setOnAction(actionEvent -> {
            Settings.setForceDistance(Double.parseDouble(textFieldHBox3.getText()));
        });
        textFieldHBox3.setAlignment(Pos.CENTER_RIGHT);
        hBox3.getChildren().add(textFieldHBox3);
        vBox.getChildren().add(hBox3);

        HBox hBox4 = new HBox();
        hBox4.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        hBox4.setStyle("-fx-background-color: ffcccc");
        Label labelHBox4 = new Label("Friction force:");
        labelHBox4.setPrefSize(textWidth, Settings.textFieldHeight);
        hBox4.getChildren().add(labelHBox4);
        TextField textFieldHBox4 = new TextField(Double.toString(Settings.frictionForce));
        textFieldHBox4.setPrefWidth(Settings.vBoxWidth - textWidth);
        textFieldHBox4.setOnAction(actionEvent -> {
            Settings.setFrictionForce(Double.parseDouble(textFieldHBox4.getText()));
        });
        textFieldHBox4.setAlignment(Pos.CENTER_RIGHT);
        hBox4.getChildren().add(textFieldHBox4);
        vBox.getChildren().add(hBox4);

        HBox hBox5 = new HBox();
        hBox5.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        hBox5.setStyle("-fx-background-color: ffcccc");
        Label labelHBox5 = new Label("Weigh error:");
        labelHBox5.setPrefSize(textWidth, Settings.textFieldHeight);
        hBox5.getChildren().add(labelHBox5);
        TextField textFieldHBox5 = new TextField(Double.toString(Settings.weightError));
        textFieldHBox5.setPrefWidth(Settings.vBoxWidth - textWidth);
        textFieldHBox5.setOnAction(actionEvent -> {
            Settings.setWeightError(Double.parseDouble(textFieldHBox5.getText()));
            model.commitChanges();
        });
        textFieldHBox5.setAlignment(Pos.CENTER_RIGHT);
        hBox5.getChildren().add(textFieldHBox5);
        vBox.getChildren().add(hBox5);

        HBox hBox6 = new HBox();
        hBox6.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        hBox6.setStyle("-fx-background-color: ffcccc");
        Label labelHBox6 = new Label("Atomic force:");
        labelHBox6.setPrefSize(textWidth, Settings.textFieldHeight);
        hBox6.getChildren().add(labelHBox6);
        TextField textFieldHBox6 = new TextField(Double.toString(Settings.atomicForce));
        textFieldHBox6.setPrefWidth(Settings.vBoxWidth - textWidth);
        textFieldHBox6.setOnAction(actionEvent -> {
            Settings.setAtomicForce(Double.parseDouble(textFieldHBox6.getText()));
        });
        textFieldHBox6.setAlignment(Pos.CENTER_RIGHT);
        hBox6.getChildren().add(textFieldHBox6);
        vBox.getChildren().add(hBox6);

        HBox hBox7 = new HBox();
        hBox7.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
        hBox7.setStyle("-fx-background-color: ffcccc");
        Label labelHBox7 = new Label("Atomic distance:");
        labelHBox7.setPrefSize(textWidth, Settings.textFieldHeight);
        hBox7.getChildren().add(labelHBox7);
        TextField textFieldHBox7 = new TextField(Double.toString(Settings.atomicForceDistance));
        textFieldHBox7.setPrefWidth(Settings.vBoxWidth - textWidth);
        textFieldHBox7.setOnAction(actionEvent -> {
            Settings.setAtomicForceDistance(Double.parseDouble(textFieldHBox7.getText()));
        });
        textFieldHBox7.setAlignment(Pos.CENTER_RIGHT);
        hBox7.getChildren().add(textFieldHBox7);
        vBox.getChildren().add(hBox7);

        return vBox;
    }

    private VBox createParticleRulesVBoxes() {
        VBox vBox = new VBox();
        vBox.setSpacing(Settings.vBoxSpacing);
        vBox.setStyle("-fx-background-color: aabbcc");
        int counter = 0;

        for (int i = 0; i < Settings.countOfColors; i++) {
            VBox tempVBox = new VBox();
            tempVBox.setSpacing(Settings.vBoxBlockSpacing);
            tempVBox.setStyle("-fx-background-color: aabbcc");

            for (int j = 0; j < Settings.countOfColors; j++) {
                int textWidth = 75;
                HBox hBox1 = new HBox();
                hBox1.setPrefSize(Settings.vBoxWidth, Settings.textFieldHeight);
                hBox1.setStyle("-fx-background-color: ffcccc");
                Label labelHBox1 = new Label("color " + (i + 1) + " to " + (j + 1) + ":");
                labelHBox1.setPrefSize(textWidth, Settings.textFieldHeight);
                hBox1.getChildren().add(labelHBox1);
                textFields.add(new TextField(Settings.decimalFormat.format(Settings.forces[counter])));
                textFields.get(counter).setAlignment(Pos.CENTER_RIGHT);
                textFields.get(counter).setPrefWidth(Settings.vBoxWidth - textWidth);
                textFields.get(counter).setOnAction(actionEvent -> {
                    updateTextFields();
                });
                hBox1.getChildren().add(textFields.get(counter));
                tempVBox.getChildren().add(hBox1);
                counter++;
            }
            vBox.getChildren().add(tempVBox);
        }
        return vBox;
    }
}

