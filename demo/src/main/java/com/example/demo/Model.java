package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class Model {
    ParticleEngine particleEngine = new ParticleEngine();
    GraphicsContext graphicsContext;

    public void initGraphicsContext(GraphicsContext gc) {
        graphicsContext = gc;
    }

    public void resetParticles() {
        particleEngine.resetParticles();
    }

    public void recreate() {
        particleEngine.recreateArrays();

//          create particles
        for (int i = 0; i < Settings.countOfColors; i++)
            particleEngine.createParticles(Settings.countOfParticles[i], Settings.colors[i]);

//          create rules
        int indexCounter = 0;
        for (int i = 0; i < Settings.countOfColors; i++)
            for (int j = 0; j < Settings.countOfColors; j++) {
                particleEngine.createRule(Settings.colors[i], Settings.colors[j], Settings.forces[indexCounter]);
                indexCounter++;
            }
    }

    public void commitChanges() {
//        Commit particles
        particleEngine.commitParticle();
//        Commit rules
        particleEngine.commitRule();
    }

    ;

    public void start() {
        recreate();
//        Settings.randomSeed();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> loop()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loop() {
        particleEngine.updateParticles();
        graphicsContext.clearRect(0, 0, Settings.canvasWidth, Settings.canvasHeight);
        particleEngine.drawParticles(graphicsContext);
    }
}
