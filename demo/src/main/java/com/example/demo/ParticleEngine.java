package com.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ParticleEngine {
    private ArrayList<Particle> particles;
    private ArrayList<Rule> rules;

    public void createParticles(int n, Color color) {
        for (int i = 0; i < n; i++) {
            particles.add(new Particle(color));
        }
    }

    public void createRule(Color acceptor, Color donor, double force) {
        if (force != 0)
            rules.add(new Rule(acceptor, donor, force));
    }

    public void drawParticles(GraphicsContext gc) {
        for (Particle p : particles) {
            gc.setFill(p.getColor());
            gc.fillRect(p.getX(), p.getY(), Settings.sizeOfParticles, Settings.sizeOfParticles);
        }
    }

    public void updateParticles() {

//        rule force
        for (Rule r : rules) {
            for (Particle p1 : particles) {
                if (p1.getColor() != r.getDonorType())
                    continue;
                for (Particle p2 : particles) {
                    if (p2.getColor() != r.getAcceptorType())
                        continue;
                    changeVelocity(p1, p2, r.getForce());
                }
            }
        }

//        atomic force
        if (Settings.atomicForce != 0)
            for (Particle p1 : particles)
                for (Particle p2 : particles)
                    atomicForce(p1, p2);

//        Move
        for (Particle p : particles) {
            p.magicCenterForce();
            p.frictionForce();
            p.addVelocity();
        }
    }

    private void changeVelocity(Particle donor, Particle acceptor, double force) {

        if (donor.getX() == acceptor.getX() && donor.getY() == acceptor.getY())
            return;

        double dx = donor.getX() - acceptor.getX();
        double dy = donor.getY() - acceptor.getY();
        double distance = Math.sqrt((dx * dx) + (dy * dy));

        if (distance >= Settings.forceDistance)
            return;

//        double distanceK = Settings.forceDistance / distance;
        double distanceK = (Settings.forceDistance - distance) / Settings.forceDistance;

        double weightK = donor.getWeight() / acceptor.getWeight();

        acceptor.addVelocityX(force * (dx / distance) * (distanceK) * (weightK));
        acceptor.addVelocityY(force * (dy / distance) * (distanceK) * (weightK));
    }

    private void atomicForce(Particle donor, Particle acceptor) {
        if (Settings.atomicForce == 0)
            return;

        if (donor.getX() == acceptor.getX() && donor.getY() == acceptor.getY())
            return;

        double dx = donor.getX() - acceptor.getX();
        double dy = donor.getY() - acceptor.getY();
        double distance = Math.sqrt((dx * dx) + (dy * dy));

        if (distance >= Settings.atomicForceDistance)
            return;

        acceptor.addVelocityX(Settings.atomicForce * dx / distance / acceptor.getWeight());
        acceptor.addVelocityY(Settings.atomicForce * dy / distance / acceptor.getWeight());
    }

    public void recreateArrays() {
        particles = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public void resetParticles() {
        for (Particle p : particles)
            p.reset();
    }
}
