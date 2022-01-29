package com.example.isometric2dmotor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Quadrangle extends Polygon {

    public Quadrangle(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y, double p4x, double p4y, Color color) {
        super();
        this.getPoints().addAll(p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y);
        this.setFill(color);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }
}
