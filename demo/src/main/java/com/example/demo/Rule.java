package com.example.demo;

import javafx.scene.paint.Color;

public class Rule {
    private final double force;
    private final Color donorType;
    private final Color acceptorType;

    public Rule(Color acceptorType, Color donorType, double force) {
        this.force = force;
        this.donorType = donorType;
        this.acceptorType = acceptorType;
    }

    public double getForce() {
        return force;
    }

    public Color getDonorType() {
        return donorType;
    }

    public Color getAcceptorType() {
        return acceptorType;
    }
}
