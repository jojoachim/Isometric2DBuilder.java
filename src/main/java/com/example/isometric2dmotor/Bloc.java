package com.example.isometric2dmotor;


/* Each bloc has a trunck with a basis in diamond (x, y) and a height (bz). Thus,
he has the hat height (hz) on this trunck with opening directions.
*/

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class Bloc extends Shape {

    public enum OPENING {
        LB, LT, RB, RT,  ALL
    }

    public enum Status {
        ACTUAL, PRESENTATION, TARGETED;
    }

    public enum Type {
        ELEMENTARY, // Tile
        DOLPHIN_LB, DOLPHIN_LT, DOLPHIN_RB, DOLPHIN_RT, // 1 Opening
        SHARK_OPP_X, SHARK_OPP_Y, SHARK_ADJ_BOTTOM, SHARK_ADJ_LEFT, SHARK_ADJ_TOP, SHARK_ADJ_RIGHT, // 2 Openings
        WHALE_LB, WHALE_LT, WHALE_RB, WHALE_RT, // 3 Openings
        DOME // 4 Openings (no tile)
    }

    public static final Color elementaryColor = Color.GREEN;
    public static final Color actualOpenColor = Color.WHITE;
    public static final Color actualCloseColor = Color.GREY;
    public static final Color targetedOpenColor = Color.rgb(230, 20, 20);
    public static final Color targetedCloseColor = Color.rgb(150, 20, 20);
    public static final Color presentationOpenColor = Color.rgb(20, 130, 255);
    public static final Color presentationCloseColor = Color.rgb(30, 70, 250);

    protected static double x = 52;
    protected static double y = 30;

    protected Status status;

    protected int bz;
    protected int hz;

    protected ArrayList<Shape> visibleFaces;
    protected Type type;
    protected OpeningMap openingMap;


    public Bloc(double x, double y, int newBz, int newHz, Type type, Status status, Pane root) {
        bz = newBz;
        hz = newHz;
        this.type = type;
        visibleFaces = new ArrayList<>();
        this.status = status;
        attributeFaces(x, y);
        this.draw(root);
    }

    public Bloc(int newBz, int newHz, OPENING[] openingArray) {
        bz = newBz;
        hz = newHz;
        openingMap = new OpeningMap();
        for(OPENING op: openingArray) {
            if(op == OPENING.LB) {
                openingMap.setLB(true);
            }
            else if(op == OPENING.LT) {
                openingMap.setLT(true);
            }
            else if(op == OPENING.RB) {
                openingMap.setRB(true);
            }
            else if(op == OPENING.RT) {
                openingMap.setRT(true);
            }
            else if(op == OPENING.ALL) {
                openingMap.setLB(true);
                openingMap.setLT(true);
                openingMap.setRB(true);
                openingMap.setRT(true);
            }
        }
    }

    public Bloc(Bloc b) {
        this.bz = b.getBz();
        this.hz = b.getHz();
        this.status = b.status;
        this.visibleFaces = b.getVisibleFaces();
        this.type = b.getType();
        this.openingMap = b.getOpeningMap();
    }

    public void putTargetColors(boolean isTarget) { // choose targeted or untargeted
        for(Shape f : visibleFaces) {
            if(isTarget) {
                if (f.getFill() == actualOpenColor) {
                    f.setFill(targetedOpenColor);
                } else if (f.getFill() == actualCloseColor) {
                    f.setFill(targetedCloseColor);
                }
            }
            else {
                if (f.getFill() == targetedOpenColor) {
                    f.setFill(actualOpenColor);
                } else if (f.getFill() == targetedCloseColor) {
                    f.setFill(actualCloseColor);
                }
            }
        }
    }

    public void putPresentationColor(boolean isPrentation) {
        for(Shape f : visibleFaces) {
            if(isPrentation) {
                if (f.getFill() == actualOpenColor) {
                    f.setFill(presentationOpenColor);
                } else if (f.getFill() == actualCloseColor) {
                    f.setFill(presentationCloseColor);
                }
            }
            else {
                if (f.getFill() == presentationOpenColor) {
                    f.setFill(actualOpenColor);
                } else if (f.getFill() == presentationCloseColor) {
                    f.setFill(actualCloseColor);
                }
            }
        }
    }


    public void attributeFaces(double xTopPoint, double yTopPoint) {
        //Attribution of the colors :
        Color openColor = Color.BLACK;
        Color closeColor = Color.BLACK;
        if(status == Status.PRESENTATION) {
            openColor = presentationOpenColor;
            closeColor = presentationCloseColor;
        }
        else if(status == Status.ACTUAL) {
            openColor = actualOpenColor;
            closeColor = actualCloseColor;
        }
        else if(status == Status.TARGETED) {
            openColor = targetedOpenColor;
            closeColor = targetedCloseColor;
        }

        visibleFaces.clear();

        double hzSeen = hz*(x - y)/30.0;
        if(Math.abs(hzSeen) < 2) {
            hzSeen = 0;
        }

        if(bz != 0) {
            double bzSeen = bz*(x - y)/30.0;
            visibleFaces.add(new Quadrangle(xTopPoint-x/2, yTopPoint+y/2-bzSeen, xTopPoint, yTopPoint+y-bzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, closeColor));
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint+y-bzSeen, xTopPoint+x/2, yTopPoint+y/2-bzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
            yTopPoint -= bzSeen;
        }
        if(type == Type.ELEMENTARY) {
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, elementaryColor));
        }
        else if(type == Type.DOLPHIN_LB) {
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint-hzSeen, xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.DOLPHIN_LT) {
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y-hzSeen, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Quadrangle(xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, xTopPoint, yTopPoint+y-hzSeen, closeColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y-hzSeen, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.DOLPHIN_RB) {
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, closeColor));
        }
        else if(type == Type.DOLPHIN_RT) {
            visibleFaces.add(new Quadrangle(xTopPoint-x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y-hzSeen, openColor));
            visibleFaces.add(new Quadrangle(xTopPoint-x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, closeColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint+y-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.SHARK_OPP_X) {
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, xTopPoint-x/4, yTopPoint+y/4-hzSeen, openColor));
            visibleFaces.add(new Quadrangle(xTopPoint-x/4, yTopPoint+y/4-hzSeen, xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.SHARK_OPP_Y) {
            visibleFaces.add(new Quadrangle(xTopPoint, yTopPoint, xTopPoint+x/4, yTopPoint+y/4-hzSeen, xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Quadrangle(xTopPoint+x/4, yTopPoint+y/4-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, closeColor));
        }
        else if(type == Type.SHARK_ADJ_BOTTOM) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, openColor));
        }
        else if(type == Type.SHARK_ADJ_LEFT) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2, xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/2, yTopPoint+y/2-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.SHARK_ADJ_TOP) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2, xTopPoint, yTopPoint, xTopPoint, yTopPoint+y-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y-hzSeen, xTopPoint, yTopPoint+y, closeColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint+y-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.SHARK_ADJ_RIGHT) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint-x/2, yTopPoint+y/2-hzSeen, xTopPoint+x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/2, yTopPoint+y/2, xTopPoint-x/2, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2-hzSeen, xTopPoint-x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.WHALE_RB) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/2, yTopPoint+y/2, xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/4, yTopPoint+3*y/4-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.WHALE_LT) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint-x/4, yTopPoint+y/4-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/4, yTopPoint+y/4-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/4, yTopPoint+y/4-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, openColor));
        }
        else if(type == Type.WHALE_LB) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint-x/2, yTopPoint+y/2, xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, xTopPoint+x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/2, yTopPoint+y/2, xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint-x/4, yTopPoint+3*y/4-hzSeen, xTopPoint-x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, closeColor));
        }
        else if(type == Type.WHALE_RT) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint-x/2, yTopPoint+y/2, xTopPoint+x/4, yTopPoint+y/4-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/4, yTopPoint+y/4-hzSeen, xTopPoint-x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint+x/4, yTopPoint+y/4-hzSeen, xTopPoint, yTopPoint+y, xTopPoint+x/2, yTopPoint+y/2, openColor));
        }
        else if(type == Type.DOME) {
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y/2-hzSeen, openColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint, xTopPoint, yTopPoint+y/2-hzSeen, xTopPoint-x/2, yTopPoint+y/2, openColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint+y/2-hzSeen, xTopPoint+x/2, yTopPoint+y/2, xTopPoint, yTopPoint+y, openColor));
            visibleFaces.add(new Triangle(xTopPoint, yTopPoint+y/2-hzSeen, xTopPoint, yTopPoint+y, xTopPoint-x/2, yTopPoint+y/2, openColor));
        }
    }

    public void draw(Pane root) {
        for(Shape s: visibleFaces) {
            root.getChildren().add(s);
        }
    }

    /* GETTERS */
    public static double getX() { return x; }
    public static double getY() { return y; }
    public int getBz() { return bz; }
    public int getHz() { return hz; }
    public Type getType() { return type; }
    public ArrayList<Shape> getVisibleFaces() { return visibleFaces; }
    public OpeningMap getOpeningMap() { return openingMap; }

    /* SETTERS */
    public void setHz(int newHz) { hz = newHz; }
    public void setBz(int newBz) { bz = newBz; }
    public void setType(Type newType) { type = newType; }
    public void setStatus(Status s) { status = s; }
    public static void setDimensions(double newX, double newY) {
        x = newX;
        y = newY;
    }
}
