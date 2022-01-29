package com.example.isometric2dmotor;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class MainScene extends Scene {

    protected static Shape shapeMemory = null;
    protected static int xIndexMemory;
    protected static int yIndexMemory;

    protected Point2D targetIndexes;

    protected static Mode mode;

    public enum ClickType {
        NONE, LEFT, RIGHT;
    }
    public enum Mode {
        BUILD, TEXTURE;
    }

    protected ClickType clickToConsider;

    public BorderPane root;

    public MainScene(BorderPane root, int w, int h) {
        super(root, w, h);
        clickToConsider = ClickType.NONE;
        mode = Mode.BUILD;
        targetIndexes = new Point2D(-1, -1);
        xIndexMemory = -1;
        yIndexMemory = -1;
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getX() < EditorView.WIDTH) {
                    double x = (((ScrollPane)(((BorderPane)getRoot()).getChildren().get(0))).getHvalue()*(EditorView.PREF_WIDTH-EditorView.WIDTH) + event.getX());
                    double y = (((ScrollPane)(((BorderPane)getRoot()).getChildren().get(0))).getVvalue()*(EditorView.PREF_HEIGHT-Main.WINDOW_HEIGHT) + event.getY());

                    if(mode == Mode.BUILD) {

                        double xTopPoint = (x - Map.getStartingPoint().getX())/(Bloc.getX()/2)*(Bloc.getX()/2);
                        double yTopPoint = (y - Map.getStartingPoint().getY())/(Bloc.getY()/2)*(Bloc.getY()/2);

                        if(xIndexMemory != (int) ((xTopPoint*Bloc.getY()/Bloc.getX() + yTopPoint)/(Bloc.getY())) || yIndexMemory != (int) ((yTopPoint*Bloc.getX()/Bloc.getY() - xTopPoint)/(Bloc.getX()))) {
                            if(xIndexMemory >= 0 && xIndexMemory < Map.getBlocList().get(0).size() && yIndexMemory >= 0 && yIndexMemory < Map.getBlocList().size()) {
                                if (clickToConsider == ClickType.NONE) {
                                    if (!Map.isFilled(yIndexMemory, xIndexMemory)) {
                                        Map.deleteBloc(yIndexMemory, xIndexMemory);
                                    }
                                }
                                else {
                                    if (clickToConsider == ClickType.LEFT) {
                                        Map.setInBoolBlocList(yIndexMemory, xIndexMemory, 1);
                                        Map.putActualStatus(yIndexMemory, xIndexMemory);
                                    } else if (clickToConsider == ClickType.RIGHT) {
                                        Map.deleteBloc(yIndexMemory, xIndexMemory);
                                        Map.setInBoolBlocList(yIndexMemory, xIndexMemory, 0);
                                    }
                                    clickToConsider = ClickType.NONE;
                                }
                            }

                            xIndexMemory = (int) ((xTopPoint * Bloc.getY() / Bloc.getX() + yTopPoint) / (Bloc.getY()));
                            yIndexMemory = (int) ((yTopPoint * Bloc.getX() / Bloc.getY() - xTopPoint) / (Bloc.getX()));

                            if(xIndexMemory >= 0 && xIndexMemory < Map.getBlocList().get(0).size() && yIndexMemory >= 0 && yIndexMemory < Map.getBlocList().size()) {
                                if (!Map.isFilled(yIndexMemory, xIndexMemory)) {
                                    Map.addRepresentationBloc(new Bloc(Settings.getBlocPresentation()), yIndexMemory, xIndexMemory);
                                    targetIndexes = new Point2D(-1, -1);
                                } else {
                                    targetIndexes = new Point2D(xIndexMemory, yIndexMemory);
                                }

                                if (targetIndexes.getY() != -1) { //FIXME : version avec status targetted ?
                                    Map.redrawWithTarget((int) targetIndexes.getY(), (int) targetIndexes.getX());
                                } else {
                                    Map.redraw();
                                }
                            }
                        }
                    }
                    else if(mode == Mode.TEXTURE) {

                        for (ArrayList<Bloc> row : Map.getBlocList()) {
                            for (Bloc b : row) {
                                for (Shape s : b.getVisibleFaces()) {
                                    if (s.contains(new Point2D(x, y))) {
                                        // ON NE PEUT POSER UN BLOC QUE SUR UN ELEMENTARY
                                        if (shapeMemory == null || shapeMemory != s) {
                                            if (s.getFill() == Bloc.elementaryColor) { //FIXME : other test or bz not zero in blank.
                                                shapeMemory = s;
                                                if (clickToConsider == ClickType.NONE) {
                                                    if (!Map.isFilled(yIndexMemory, xIndexMemory)) {
                                                        Map.deleteBloc(yIndexMemory, xIndexMemory);
                                                        targetIndexes = new Point2D(-1, -1);
                                                    } else {
                                                        targetIndexes = new Point2D(xIndexMemory, yIndexMemory);
                                                    }

                                                } else {
                                                    if (clickToConsider == ClickType.LEFT) {
                                                        Map.setInBoolBlocList(yIndexMemory, xIndexMemory, 1);
                                                    } else if (clickToConsider == ClickType.RIGHT) {
                                                        Map.deleteBloc(yIndexMemory, xIndexMemory);
                                                        Map.setInBoolBlocList(yIndexMemory, xIndexMemory, 0);
                                                    }
                                                    clickToConsider = ClickType.NONE;
                                                }

                                                double xTopPoint = (x - Map.getStartingPoint().getX()) / (Bloc.getX() / 2) * (Bloc.getX() / 2);
                                                double yTopPoint = (y - Map.getStartingPoint().getY()) / (Bloc.getY() / 2) * (Bloc.getY() / 2);


                                                xIndexMemory = (int) ((xTopPoint * Bloc.getY() / Bloc.getX() + yTopPoint) / (Bloc.getY()));
                                                yIndexMemory = (int) ((yTopPoint * Bloc.getX() / Bloc.getY() - xTopPoint) / (Bloc.getX()));
                                                //System.out.println(xIndexMemory + ", " + yIndexMemory);

                                                //Map.addRepresentationBloc(Settings.getBlocPresentation(), yIndexMemory, xIndexMemory);
                                                if (!Map.isFilled(yIndexMemory, xIndexMemory)) {
                                                    Map.addRepresentationBloc(new Bloc(Settings.getBlocPresentation()), yIndexMemory, xIndexMemory);
                                                }

                                                if (targetIndexes.getY() != -1) {
                                                    Map.redrawWithTarget((int) targetIndexes.getY(), (int) targetIndexes.getX());
                                                } else {
                                                    Map.redraw();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        this.setOnMouseClicked(event ->  {
            if(event.getButton() == MouseButton.PRIMARY) {
                clickToConsider = ClickType.LEFT;
            }
            else if(event.getButton() == MouseButton.SECONDARY) {
                clickToConsider = ClickType.RIGHT;
            }
            //Map.addBloc(new Bloc(Map.startingPoint.getX(), Map.startingPoint.getY(), 0, 0, Bloc.Type.ELEMENTARY), 0, 0);
        });
    }

}