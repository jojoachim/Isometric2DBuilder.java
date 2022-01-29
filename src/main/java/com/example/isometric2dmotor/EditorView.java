package com.example.isometric2dmotor;

import javafx.scene.Group;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EditorView {

    public static final int WIDTH = 800;
    public static final int PREF_WIDTH = 1800;
    public static final int PREF_HEIGHT = 1300;

    protected double x;
    protected double y;

    public EditorView(Pane root) {
        x = 0;
        y = 0;
        Map map = new Map(30, 30, Map.Mode.ALL_RECOMMANDED, root);
    }
}
