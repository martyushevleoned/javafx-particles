package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class Model {
    ParticleEngine particleEngine = new ParticleEngine();
    GraphicsContext gc;

    public void init(GraphicsContext graphicsContext) {
        gc = graphicsContext;
    }

    public void recreate() {
        particleEngine.restart();

        for (int i = 0; i < Settings.countOfColors; i++)
            particleEngine.createParticles(Settings.countOfParticles[i], Settings.colors[i]);

        int indexCounter = 0;
        for (int i = 0; i < Settings.countOfColors; i++)
            for (int j = 0; j < Settings.countOfColors; j++) {
                particleEngine.createRule(Settings.colors[i], Settings.colors[j], Settings.forces[indexCounter]);
                indexCounter++;
            }
    }

    public void resetParticles() {
        particleEngine.resetParticles();
    }

    public void start() {
        recreate();

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
