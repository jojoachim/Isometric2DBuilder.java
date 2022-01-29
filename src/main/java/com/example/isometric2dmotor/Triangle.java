package com.example.isometric2dmotor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle extends Polygon {

    public Triangle(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y, Color color) {
        super();
        this.getPoints().addAll(p1x, p1y, p2x, p2y, p3x, p3y);
        this.setFill(color);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }
}
