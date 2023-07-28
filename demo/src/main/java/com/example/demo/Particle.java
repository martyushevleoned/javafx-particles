package com.example.demo;

import javafx.scene.paint.Color;

import java.util.Random;

public class Particle {
    private double x;
    private double y;
    private double velosityX;
    private double velosityY;
    private double weight;
    private Color color;

    public Particle(Color color) {
        this.color = color;
        reset();
    }

    public double randomValue(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    public void frictionForce() {
        velosityX *= (1 - Settings.frictionForce) / weight;
        velosityY *= (1 - Settings.frictionForce) / weight;
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
        if (x > Settings.canvasWidth - Settings.sizeOfParticles && velosityX > 0)
            velosityX *= -1;
        if (y < 0 && velosityY < 0)
            velosityY *= -1;
        if (y > Settings.canvasHeight - Settings.sizeOfParticles && velosityY > 0)
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

    public double getWeight() {
        return weight;
    }

    public void reset() {
        x = randomValue(0, Settings.canvasWidth - Settings.sizeOfParticles);
        y = randomValue(0, Settings.canvasHeight - Settings.sizeOfParticles);
        velosityX = randomValue(-2, 2);
        velosityY = randomValue(-2, 2);
        weight = randomValue(1, 1 + Settings.weightError);
    }
}
