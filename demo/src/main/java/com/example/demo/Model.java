package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Model {
    ParticleEngine particleEngine = new ParticleEngine();
    GraphicsContext gc;

    public void init(GraphicsContext graphicsContext) {
        gc = graphicsContext;
    }

    private void createWorld() {
        particleEngine.createParticles(20, Color.RED);
        particleEngine.createParticles(20, Color.GREEN);
        particleEngine.createParticles(20, Color.BLUE);

        particleEngine.createRule(Color.RED,Color.RED,0.2);
        particleEngine.createRule(Color.BLUE,Color.RED,0.3);
        particleEngine.createRule(Color.BLUE,Color.GREEN,-0.1);
        particleEngine.createRule(Color.GREEN,Color.GREEN,0.2);
        particleEngine.createRule(Color.GREEN,Color.BLUE,-0.1);
        particleEngine.createRule(Color.RED,Color.BLUE,-0.2);
        particleEngine.createRule(Color.RED,Color.GREEN,0.2);
        particleEngine.createRule(Color.BLUE,Color.BLUE,0.1);
    }

    public void start() {
        createWorld();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> loop()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loop() {
        update();
        draw();
    }

    private void draw() {
        gc.clearRect(0, 0, Settings.canvasWidth, Settings.canvasHeight);
        particleEngine.drawParticles(gc);
    }

    private void update() {
        particleEngine.updateParticles();
    }
}
