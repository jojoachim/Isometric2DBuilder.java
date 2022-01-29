package com.example.isometric2dmotor;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static java.lang.Integer.parseInt;

public class LoadMapWindow extends Stage {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    protected TextArea textArea;
    protected Button uploadBtn;
    protected Button searchMapBtn;

    public LoadMapWindow() {
        VBox layout = new VBox();
        HBox btnLayout = new HBox(10);
        btnLayout.setPadding(new Insets(10, 10, 10, 10));

        textArea = new TextArea();
        textArea.setPrefHeight(HEIGHT-100);
        uploadBtn = new Button("Upload");
        searchMapBtn = new Button("Search map");
        layout.getChildren().add(textArea);
        btnLayout.getChildren().add(uploadBtn);
        btnLayout.getChildren().add(searchMapBtn);
        layout.getChildren().add(btnLayout);
        Group root = new Group();
        root.getChildren().add(layout);

        this.setTitle("Loading Map Window");
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setScene(new Scene(root));

        uploadBtn.setOnAction( (event)-> {
            Main.getRoot().getChildren().clear();
            upload(textArea.getText());
            //Map.makeMapWithString(textArea.getText(), Main.getRoot());
            this.close();
        });
        searchMapBtn.setOnAction( (event)-> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            FileReader fr = null;

            if(selectedFile != null) {
                try {
                    fr = new FileReader(selectedFile.getAbsoluteFile());
                    BufferedReader br = new BufferedReader(fr);
                    StringBuilder description = new StringBuilder();
                    String line = br.readLine();
                    while(line != null) {
                        //System.out.println(line);
                        description.append(line);
                        description.append("\n");
                        line = br.readLine();
                    }
                    textArea.setText(description.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void upload(String description) {
        String[] raws = description.split("\n");
        String[] blocs;
        String[] bloc;
        for(int i = 0; i < raws.length; i++) {
            blocs = raws[i].split("-");
            for(int j = 0; j < blocs.length; j++) {
                System.out.println("taille : " + blocs[j].length());
                bloc = blocs[j].split(",");
                if(bloc.length == 3) {
                    Map.addBloc(new Bloc(Bloc.getX(), Bloc.getY(), parseInt(bloc[1]), parseInt(bloc[2]), getTypeWithCharValue(bloc[0]), Bloc.Status.ACTUAL, Main.getRoot()), j, i);
                }
            }
        }
    }


    public Bloc.Type getTypeWithCharValue(String s) {
        if(s.equals("0")) {
            return Bloc.Type.ELEMENTARY;
        }
        else if(s.equals("1")) {
            return Bloc.Type.DOLPHIN_LB;
        }
        else if(s.equals("2")) {
            return Bloc.Type.DOLPHIN_RB;
        }
        else if(s.equals("3")) {
            return Bloc.Type.DOLPHIN_LT;
        }
        else if(s.equals("4")) {
            return Bloc.Type.DOLPHIN_RT;
        }
        else if(s.equals("5")) {
            return Bloc.Type.SHARK_OPP_X;
        }
        else if(s.equals("6")) {
            return Bloc.Type.SHARK_OPP_Y;
        }
        else if(s.equals("7")) {
            return Bloc.Type.SHARK_ADJ_LEFT;
        }
        else if(s.equals("8")) {
            return Bloc.Type.SHARK_ADJ_TOP;
        }
        else if(s.equals("9")) {
            return Bloc.Type.SHARK_ADJ_RIGHT;
        }
        else if(s.equals("a")) {
            return Bloc.Type.SHARK_ADJ_BOTTOM;
        }
        else if(s.equals("b")) {
            return Bloc.Type.WHALE_LB;
        }
        else if(s.equals("c")) {
            return Bloc.Type.WHALE_RB;
        }
        else if(s.equals("d")) {
            return Bloc.Type.WHALE_LT;
        }
        else if(s.equals("e")) {
            return Bloc.Type.WHALE_RT;
        }
        else { //Dome
            return Bloc.Type.DOME;
        }
    }
}
