package com.example.demo;

import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Settings {
    //    init
    public static final int countOfColors = 4;
    static DecimalFormat decimalFormat = new DecimalFormat("##.########", new DecimalFormatSymbols(Locale.US));

    //    Screen settings
    public static final int padding = 4;
    public static final int screenWidth = 780 + 200 + padding * 2;
    public static final int screenHeight = 780 + padding * 2;
    public static final int leftPaneWidth = 200;
    public static final int rightPaneWidth = screenWidth - leftPaneWidth;
    public static final int scrollPaneWidth = leftPaneWidth - padding * 2;
    public static final int scrollPaneHeight = screenHeight - padding * 2;
    public static final int vBoxWidth = scrollPaneWidth - padding * 2 - 15;
    public static final int vBoxSpacing = 15;
    public static final int vBoxBlockSpacing = 3;
    public static final int textFieldHeight = 25;
    public static final int canvasWidth = rightPaneWidth - padding * 2;
    public static final int canvasHeight = screenHeight - padding * 2;

    //    Particles
    public static double sizeOfParticles = 4.5;
    public static double forceDistance = 300;
    public static double frictionForce = 0.02;
    public static double weightError = 0.15;
    public static double atomicForce = -0.1;
    public static double atomicForceDistance = 20;

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

    public static final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.PURPLE, Color.CYAN};
    public static final int[] countOfParticles = {280, 280, 280, 280, 280};
    public static double[] forces = {
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0
    };

    public static void randomSeed() {
        Random random = new Random();
        double minForce = -0.05;
        double maxForce = 0.05;
        System.out.print("= {");
        for (int i = 0; i < countOfColors * countOfColors; i++) {
            forces[i] = minForce + (maxForce - minForce) * random.nextDouble();
            Main.textFields.get(i).setText(String.valueOf(decimalFormat.format(forces[i])));
            System.out.print(forces[i]);
            if (i == countOfColors * countOfColors - 1)
                continue;
            System.out.print(", ");
        }
        System.out.println("}; // " + countOfColors + " colors");
    }


}
