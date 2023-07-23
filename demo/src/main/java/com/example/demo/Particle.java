package com.example.demo;

import javafx.scene.paint.Color;

import java.util.Random;

public class Particle {
    private double x;
    private double y;
    private double velosityX;
    private double velosityY;

    private Color color;

    public Particle(Color color) {
        this.x = randomValue(0, Settings.canvasWidth - Settings.particleSize);
        this.y = randomValue(0, Settings.canvasHeight - Settings.particleSize);
        this.velosityX = randomValue(-2, 2);
        this.velosityY = randomValue(-2, 2);
        this.color = color;
    }

    public double randomValue(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    public void frictionForce(){
        velosityX *= Settings.frictionForce;
        velosityY *= Settings.frictionForce;
    }

    public void addVelocity() {
        x += velosityX;
        y += velosityY;
    }

    public void addVelocityX(double a) {
        velosityX += a;
    }

    public void addVelocityY(double a) {
        velosityY += a;
    }

    public void magicCenterForce() {
        if (x < 0 && velosityX < 0)
            velosityX *= -1;
        if (x > Settings.canvasWidth - Settings.particleSize && velosityX > 0)
            velosityX *= -1;
        if (y < 0 && velosityY < 0)
            velosityY *= -1;
        if (y > Settings.canvasHeight - Settings.particleSize && velosityY > 0)
            velosityY *= -1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
