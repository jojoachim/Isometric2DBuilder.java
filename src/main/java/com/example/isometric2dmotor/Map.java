package com.example.isometric2dmotor;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Map {

    public enum Mode {
        LENGTH_RECOMMANDED, RATIO_RECOMMADED, ALL_RECOMMANDED
    }

    protected static int xMapLength;
    protected static int yMapLength;
    protected static Point2D startingPoint;
    protected static ArrayList<ArrayList<Bloc>> blocList;
    protected static ArrayList<ArrayList<Integer>> blocListBooleanRepresentation; // 0 for empty, 1 for filled.

    public Map(int xLength, int yLenght, Mode mode, Pane root) {

        startingPoint = new Point2D(700, 50);
        blocList = new ArrayList<>();
        xMapLength = xLength;
        yMapLength = yLenght;

        booleanMapInit(xLength, yLenght);

        if(mode == Mode.ALL_RECOMMANDED) {
            for (int i = 0; i < yLenght; i++) {
                addBlocLine(xLength, i, root);
            }
        }
     }

     public static void booleanMapInit(int w, int h) {
        blocListBooleanRepresentation = new ArrayList<>();
        for(int i = 0; i < h; i++) {
            blocListBooleanRepresentation.add(new ArrayList<>());
            for(int j = 0; j < w; j++) {
                blocListBooleanRepresentation.get(i).add(0);
            }
        }
     }

    protected static void makeMapWithString(String description, Pane root) {
        blocList.clear();
        if(!description.equals("")) {
            String[] lines = description.split("\n");
            for(int i = 0; i < lines.length; i++) {
                addBlocLineWithString(lines[i], i, root);
            }
        }
    }

    /* MAP DESCRIPTOR :
    1 BLOC = 5 NUMBERS : 1 (TYPE) - 2 (BZ) - 2 (H2)
    ex : 40530 -> DOLPHIN_RT, bz = 5, hz = 30
     */



    protected static void addBlocLineWithString(String lineDescription, int i, Pane root) {

        ArrayList<Bloc> row = new ArrayList<>();
        int bz = 0;
        int hz = 0;
        for(int j = 0; j < lineDescription.length(); j++) {
            bz = (lineDescription.charAt(j*5+1)-48)*10 + lineDescription.charAt(j*5+2)-48;
            hz = (lineDescription.charAt(j*5+3)-48)*10 + lineDescription.charAt(j*5+4)-48;
            switch (lineDescription.charAt(j)) {
                case '0' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.ELEMENTARY, Bloc.Status.ACTUAL, root));
                }
                case '1' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.DOLPHIN_LB, Bloc.Status.ACTUAL,  root));
                }
                case '2' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.DOLPHIN_RB, Bloc.Status.ACTUAL,  root));
                }
                case '3' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.DOLPHIN_LT, Bloc.Status.ACTUAL,  root));
                }
                case '4' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.DOLPHIN_RT, Bloc.Status.ACTUAL,  root));
                }
                case '5' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.SHARK_OPP_X, Bloc.Status.ACTUAL,  root));
                }
                case '6' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.SHARK_OPP_Y, Bloc.Status.ACTUAL,  root));
                }
                case '7' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.SHARK_ADJ_LEFT, Bloc.Status.ACTUAL,  root));
                }
                case '8' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.SHARK_ADJ_TOP, Bloc.Status.ACTUAL,  root));
                }
                case '9' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.SHARK_ADJ_RIGHT, Bloc.Status.ACTUAL,  root));
                }
                case 'a' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.SHARK_ADJ_BOTTOM, Bloc.Status.ACTUAL,  root));
                }
                case 'b' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.WHALE_LB, Bloc.Status.ACTUAL,  root));
                }
                case 'c' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.WHALE_RB, Bloc.Status.ACTUAL,  root));
                }
                case 'd' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.WHALE_LT, Bloc.Status.ACTUAL,  root));
                }
                case 'e' -> {
                    row.add(new Bloc((int) startingPoint.getX() + 26 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.WHALE_RT, Bloc.Status.ACTUAL,  root));
                }
                case 'f' -> {
                    row.add(new Bloc((int) startingPoint.getX() + Bloc.getX()/2 * (i - j), (int) startingPoint.getY() + Bloc.getY()/2 * (i + j), bz, hz, Bloc.Type.DOME, Bloc.Status.ACTUAL,  root));
                }
            }
        }
        blocList.add(row);
    }

    protected void addBlocLine(int xLength, int i, Pane root) { // Que des elementary.
        ArrayList<Bloc> row = new ArrayList<>();
        for (int j = 0; j < xLength; j++) {
            row.add(new Bloc(startingPoint.getX() + Bloc.getX()/2 * (i - j), startingPoint.getY() + Bloc.getY()/2 * (i + j), 0, 0, Bloc.Type.ELEMENTARY, Bloc.Status.ACTUAL,  root));
        }
        blocList.add(row);
    }

    public static void addRepresentationBloc(Bloc bloc, int xIndex, int yIndex) {
        if(yIndex < blocList.size()) {
            if(xIndex < blocList.get(yIndex).size()) {
                blocList.get(yIndex).set(xIndex, bloc);
            }
        }
    }

    public static void setInBoolBlocList(int xIndex, int yIndex, Integer value) {
        blocListBooleanRepresentation.get(yIndex).set(xIndex, value);
    }

    public static void addBloc(Bloc bloc, int xIndex, int yIndex) {
        if(yIndex < blocList.size()) {
            if(xIndex < blocList.get(yIndex).size()) {
                blocList.get(yIndex).set(xIndex, bloc);
                if(bloc.getType() != Bloc.Type.ELEMENTARY) { //FIXME Verify utility
                    blocListBooleanRepresentation.get(yIndex).set(xIndex, 1);
                }
            }
        }
    }

    public static void deleteBloc(int xIndex, int yIndex) {
        if(yIndex < blocList.size()) {
            if(xIndex < blocList.get(yIndex).size()) {
                blocList.get(yIndex).set(xIndex, new Bloc(startingPoint.getX() + Bloc.getX()/2 * (yIndex - xIndex), startingPoint.getY() + Bloc.getY()/2 * (yIndex + xIndex), 0, 0, Bloc.Type.ELEMENTARY, Bloc.Status.ACTUAL,  Main.getRoot()));
                blocListBooleanRepresentation.get(yIndex).set(xIndex, 0);
            }
        }
    }

    public static void draw() {
        for(int i = 0; i < blocList.size(); i++) {
            for(int j = 0; j < blocList.get(i).size(); j++) {
                blocList.get(i).get(j).attributeFaces(startingPoint.getX() + Bloc.getX()/2 * (i - j), startingPoint.getY() + Bloc.getY()/2 * (i + j));
                blocList.get(i).get(j).draw(Main.getRoot());
            }
        }
    }

    public static void drawWithTarget(int xIndex, int yIndex) {
        for(int i = 0; i < blocList.size(); i++) {
            for(int j = 0; j < blocList.get(i).size(); j++) {
                blocList.get(i).get(j).attributeFaces(startingPoint.getX() + Bloc.getX()/2 * (i - j), startingPoint.getY() + Bloc.getY()/2 * (i + j));
                if(j == xIndex && i == yIndex) {
                    blocList.get(i).get(j).putTargetColors(true);
                }
                blocList.get(i).get(j).draw(Main.getRoot());
            }
        }
    }

    public static boolean isFilled(int xIndex, int yIndex) {
        //return (blocList.get(yIndex).get(xIndex).getType() != Bloc.Type.ELEMENTARY);
        return (blocListBooleanRepresentation.get(yIndex).get(xIndex) == 1);
    }

    public static ArrayList<ArrayList<Bloc>> getBlocList() { return blocList; }

    public static void changeDimensions(double newX, double newY) {
        Bloc.setDimensions(newX, newY);
        Main.getRoot().getChildren().clear();
        draw();
    }

    public static void redraw() {
        Main.getRoot().getChildren().clear();
        draw();
    }

    public static void redrawWithTarget(int xIndex, int yIndex) {
        Main.getRoot().getChildren().clear();
        drawWithTarget(xIndex, yIndex);
    }

    public static void putActualStatus(int xIndex, int yIndex) {
        blocList.get(yIndex).get(xIndex).setStatus(Bloc.Status.ACTUAL);
    }

    public static Point2D getCenter() {
        return new Point2D(Bloc.getX()/2*xMapLength, Bloc.getX()/2*yMapLength);
    }

    public static Point2D getStartingPoint() { return startingPoint; }

}
