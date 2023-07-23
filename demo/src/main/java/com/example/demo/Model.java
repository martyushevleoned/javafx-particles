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
        particleEngine.createParticles(75, Color.CYAN);
        particleEngine.createParticles(200, Color.RED);
        particleEngine.createParticles(200, Color.GREEN);
        particleEngine.createParticles(300, Color.BLUE);
        particleEngine.createParticles(800, Color.PURPLE);

        particleEngine.createRule(Color.RED,Color.RED,-0.4);
        particleEngine.createRule(Color.RED,Color.GREEN,-0.1);
        particleEngine.createRule(Color.RED,Color.BLUE,0.01);
        particleEngine.createRule(Color.RED,Color.PURPLE,0);
        particleEngine.createRule(Color.RED,Color.CYAN,0);

        particleEngine.createRule(Color.GREEN,Color.RED,0);
        particleEngine.createRule(Color.GREEN,Color.GREEN,0.01);
        particleEngine.createRule(Color.GREEN,Color.BLUE,0.01);
        particleEngine.createRule(Color.GREEN,Color.PURPLE,0);
        particleEngine.createRule(Color.GREEN,Color.CYAN,-0.02);

        particleEngine.createRule(Color.BLUE,Color.RED,-0.05);
        particleEngine.createRule(Color.BLUE,Color.GREEN,-0.1);
        particleEngine.createRule(Color.BLUE,Color.BLUE,0.008);
        particleEngine.createRule(Color.BLUE,Color.PURPLE,-0.1);
        particleEngine.createRule(Color.BLUE,Color.CYAN,-0.03);

        particleEngine.createRule(Color.PURPLE,Color.RED,0.07);
        particleEngine.createRule(Color.PURPLE,Color.GREEN,-0.1);
        particleEngine.createRule(Color.PURPLE,Color.BLUE,-0.1);
        particleEngine.createRule(Color.PURPLE,Color.PURPLE,-0.08);
        particleEngine.createRule(Color.PURPLE,Color.CYAN,0);

        particleEngine.createRule(Color.CYAN,Color.RED,0);
        particleEngine.createRule(Color.CYAN,Color.GREEN,0.003);
        particleEngine.createRule(Color.CYAN,Color.BLUE,0.003);
        particleEngine.createRule(Color.CYAN,Color.PURPLE,0);
        particleEngine.createRule(Color.CYAN,Color.CYAN,-0.02);
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
