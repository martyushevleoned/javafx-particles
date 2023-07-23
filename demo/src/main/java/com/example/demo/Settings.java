package com.example.demo;

public class Settings {
    public static final int screenWidth = 800;
    public static final int screenHeight = 600;
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
}
