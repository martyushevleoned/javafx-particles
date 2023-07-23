package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class Model {
    GraphicsContext gc;

    public void init(GraphicsContext graphicsContext){
        gc = graphicsContext;

    }

    public void start(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> loop()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void loop(){
        System.out.println("asdads");
        gc.fillRect(20,20,20,20);
    }
}
