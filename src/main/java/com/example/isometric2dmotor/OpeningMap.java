package com.example.isometric2dmotor;

public class OpeningMap {

    protected boolean LB;
    protected boolean LT;
    protected boolean RB;
    protected boolean RT;

    public OpeningMap() {
        LB = false;
        LT = false;
        RB = false;
        RT = false;
    }

    public OpeningMap(Bloc.OPENING op) {
        LB = false;
        LT = false;
        RB = false;
        RT = false;

        if(op == Bloc.OPENING.LB) {
            LB = true;
        }
        else if(op == Bloc.OPENING.LT) {
            LT = true;
        }
        else if(op == Bloc.OPENING.RB) {
            RB = true;
        }
        else if(op == Bloc.OPENING.RT) {
            RT = true;
        }
        else if(op == Bloc.OPENING.ALL) {
            LB = true;
            LT = true;
            RB = true;
            RT = true;
        }

    }

    public void setLB(boolean LB) { this.LB = LB; }
    public void setLT(boolean LT) { this.LT = LT; }
    public void setRB(boolean RB) { this.RB = RB; }
    public void setRT(boolean RT) { this.RT = RT; }
}
