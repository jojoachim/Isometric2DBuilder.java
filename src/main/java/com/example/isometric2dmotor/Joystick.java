package com.example.isometric2dmotor;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Joystick {

    protected Rectangle2D JOYSTICK_AREA;

    protected float center_x;
    protected float center_y;
    protected float baseRadius;
    protected float hatRadius;

    protected boolean onClickHold;
    protected boolean onScreen;

    public interface JoystickListener {
        void onJoystickMoved(float xPercent, float yPercent, int source);
    }

    public Joystick() {
        //super(context);
        //setOnTouchListener(this);
        //getHolder().addCallback(this);
        onScreen = false;
        onClickHold = false;
    }

    protected void init() {
        //JOYSTICK_AREA = new Rectangle2D(0, 2*getHeight()/3, getWidth()/3, getHeight());
        //baseRadius = (float)(Math.min(getWidth(), getHeight()))/20;
        hatRadius = baseRadius/2;
    }

    private void drawJoystick(float new_x, float new_y) {
        /*if(getHolder().getSurface().isValid()) {
            Canvas canvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            colors.setARGB(255, 50, 50, 50);
            canvas.drawCircle(center_x, center_y, baseRadius, colors);
            colors.setARGB(255, 200, 0, 0);
            canvas.drawCircle(new_x, new_y, hatRadius, colors);
            getHolder().unlockCanvasAndPost(canvas);
        }*/
    }

    private void hideJoystick() {
        /*Canvas canvas = this.getHolder().lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        getHolder().unlockCanvasAndPost(canvas);*/
    }
}
