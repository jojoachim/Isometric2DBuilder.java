package com.example.isometric2dmotor;

import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int WINDOW_WIDTH = EditorView.WIDTH + Settings.PREF_WIDTH;
    public static final int WINDOW_HEIGHT = 700;
    private static Stage stage;
    private static Pane root;

    public void start(Stage primaryStage){

        HBox content = new HBox(5);
        content.setPrefSize(EditorView.PREF_WIDTH, EditorView.PREF_HEIGHT);
        ScrollPane scroller = new ScrollPane(content);
        root = new Pane();
        EditorView editorView = new EditorView(root);
        content.getChildren().add(root);

        HBox content2 = new HBox(5);
        content2.setPrefSize(Settings.PREF_WIDTH, WINDOW_HEIGHT);
        content2.setStyle("-fx-background-color: grey");
        Pane root2 = new Pane();
        Settings settings = new Settings(root2);
        content2.getChildren().add(root2);

        stage = primaryStage;
        stage.setTitle("ISOMETRIC 2D MOTOR");
        stage.setResizable(false);
        stage.setScene(new MainScene(new BorderPane(scroller, null, content2, null, null), WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }

    public static Pane getRoot() {
        return root;
    }

}
