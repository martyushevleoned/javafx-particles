package com.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ParticleEngine {
    private ArrayList<Particle> particles = new ArrayList<>();
    private ArrayList<Rule> rules = new ArrayList<>();

    public void createParticles(int n, Color color) {
        for (int i = 0; i < n; i++) {
            particles.add(new Particle(color));
        }
    }

    public void createRule(Color acceptor,Color donor,  double force) {
        rules.add(new Rule(acceptor,donor,  force));
    }

    public void drawParticles(GraphicsContext gc) {
        for (Particle p : particles) {
            gc.setFill(p.getColor());
            gc.fillRect(p.getX(), p.getY(), Settings.particleSize, Settings.particleSize);
        }
    }

    public void updateParticles() {
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
//        Move
        for (Particle p : particles) {
            p.magicCenterForce();
            p.addVelocity();
            p.frictionForce();
        }
    }

    private void changeVelocity(Particle donor, Particle acceptor, double force) {

        double dx = donor.getX() - acceptor.getX();
        double dy = donor.getY() - acceptor.getY();

        double distance = Math.sqrt((dx * dx) + (dy * dy));

        if (distance < Settings.forceDistance) {
            if (donor.getX() < acceptor.getX())
                acceptor.addVelocityX(-force * Math.abs(dx / distance));

            else if (donor.getX() > acceptor.getX())
                acceptor.addVelocityX(force * Math.abs(dx / distance));

            if (donor.getY() < acceptor.getY())
                acceptor.addVelocityY(-force * Math.abs(dy / distance));

            else if (donor.getY() > acceptor.getY())
                acceptor.addVelocityY(force * Math.abs(dy / distance));
        }

    }

}
