package com.example.isometric2dmotor;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Downloader {

    protected Button downloadBtn;

    public Downloader(Pane root) {
        downloadBtn = new Button("Download map");
        root.getChildren().add(downloadBtn);

        downloadBtn.setOnAction( (event) -> {
            String description = "";
            for(ArrayList<Bloc> raw : Map.getBlocList()) {
                for(Bloc b : raw) {
                    description += getStringValue(b.getType()) + "," + b.getBz() + "," + b.getHz() + "-";
                }
                description += '\n';
            }

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            FileWriter fw = null;

            if(selectedFile != null) {
                try {
                    fw = new FileWriter(selectedFile.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(description);
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public char getStringValue(Bloc.Type type) {
        if(type == Bloc.Type.ELEMENTARY) {
            return '0';
        }
        else if(type == Bloc.Type.DOLPHIN_LB) {
            return '1';
        }
        else if(type == Bloc.Type.DOLPHIN_RB) {
            return '2';
        }
        else if(type == Bloc.Type.DOLPHIN_LT) {
            return '3';
        }
        else if(type == Bloc.Type.DOLPHIN_RT) {
            return '4';
        }
        else if(type == Bloc.Type.SHARK_OPP_X) {
            return '5';
        }
        else if(type == Bloc.Type.SHARK_OPP_Y) {
            return '6';
        }
        else if(type == Bloc.Type.SHARK_ADJ_LEFT) {
            return '7';
        }
        else if(type == Bloc.Type.SHARK_ADJ_TOP) {
            return '8';
        }
        else if(type == Bloc.Type.SHARK_ADJ_RIGHT) {
            return '9';
        }
        else if(type == Bloc.Type.SHARK_ADJ_BOTTOM) {
            return 'a';
        }
        else if(type == Bloc.Type.WHALE_LB) {
            return 'b';
        }
        else if(type == Bloc.Type.WHALE_RB) {
            return 'c';
        }
        else if(type == Bloc.Type.WHALE_LT) {
            return 'd';
        }
        else if(type == Bloc.Type.WHALE_RT) {
            return 'e';
        }
        else { //Dome
            return 'f';
        }
    }
}
