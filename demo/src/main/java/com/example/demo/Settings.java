package com.example.demo;

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

//    Particles
    public static final double particleSize = 5;
    public static final double frictionForce = 0.97;
    public static final double forceDistance = 70;
}
