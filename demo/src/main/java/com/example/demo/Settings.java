package com.example.demo;

import javafx.scene.paint.Color;

import java.util.Random;

public class Settings {
    //    Game screen
    public static final int screenWidth = 810;
    public static final int screenHeight = 610;
    public static final int padding = 5;
    public static final int leftPaneWidth = 200;
    public static final int rightPaneWidth = screenWidth - leftPaneWidth;
    public static final int scrollPaneWidth = leftPaneWidth - padding * 2;
    public static final int scrollPaneHeight = screenHeight - padding * 2;
    public static final int vBoxWidth = scrollPaneWidth - 19;
    public static final int vBoxSpacing = 10;
    public static final int hBoxSpacing = 5;
    public static final int vBoxContentHeight = 25;
    public static final int canvasWidth = rightPaneWidth - padding * 2;
    public static final int canvasHeight = screenHeight - padding * 2;

    //    init
    public static final int countOfColors = 4;
    //    Particles
    public static double sizeOfParticles = 4.5;
    public static double forceDistance = 200;
    public static double frictionForce = 0.02;
    public static double weightError = 0.15;
    public static double atomicForce = -0.1;
    public static double atomicForceDistance = 7.5;

    public static void setAtomicForce(double atomicForce) {
        Settings.atomicForce = atomicForce;
    }

    public static void setAtomicForceDistance(double atomicForceDistance) {
        Settings.atomicForceDistance = atomicForceDistance;
    }

    public static void setSizeOfParticles(double sizeOfParticles) {
        Settings.sizeOfParticles = sizeOfParticles;
    }

    public static void setFrictionForce(double frictionForce) {
        Settings.frictionForce = frictionForce;
    }

    public static void setWeightError(double weightError) {
        Settings.weightError = weightError;
    }

    public static void setForceDistance(double forceDistance) {
        Settings.forceDistance = forceDistance;
    }

    //    public static final int countOfParticles = 200;
    public static final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.PURPLE, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK};
    public static final int[] countOfParticles = {200, 200, 200, 200, 200, 200, 200, 200};
    public static double[] forces = {0.03379879618309933, -0.008258922146098462, 0.03326852509034975, -0.02247943159917904, 0.05933866403495287, -0.04626611356365415, -0.01580320190411357, -0.01338442870970942, -0.005334334235954155, -0.0468057952362415, -0.014269948022992078, 0.028626660781576524, 0.01762533114806808, 0.03665879848933344, -0.03381834531566062, -0.04244446945231642}; // 4 colors


    public static void randomSeed() {
        Random random = new Random();
        double minForce = -0.06;
        double maxForce = 0.06;
        System.out.print("= {");
        for (int i = 0; i < countOfColors * countOfColors; i++) {
            forces[i] = minForce + (maxForce - minForce) * random.nextDouble();
            System.out.print(forces[i]);
            if (i == countOfColors * countOfColors - 1)
                continue;
            System.out.print(", ");
        }
        System.out.println("}; // " + countOfColors + " colors");
    }
}
