package com.example.vivian.styled_shapes;

import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by Vivian on 3/21/16.
 */

public class Rectangle extends Shape {

    Rect rectangle;
    float alphaShape;

    DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
    final int width = metrics.widthPixels;
    final int height = metrics.heightPixels;

    int xleft = xS;
    int xright = yS;
    int ytop = radiusS;
    int ybottom = ybotS;

    public Rectangle (Context context, int style, int x, int y, int radius, int ybot) {
        super(context, style, x, y, radius, ybot);
    }

    public String getShapeType() {
        return "rectangle";
    }

    public void onDraw(Canvas canvas) {
        rectangle = new Rect(xleft, ytop, xright, ybottom);

        alphaShape = super.getShapeAlpha();
        setAlpha(alphaShape);

        switch (styleShape) {
            case 1: {
                paintStyle.setColor(Color.GREEN);
                paintStyle.setStyle(Paint.Style.FILL);
                canvas.drawRect(rectangle, paintStyle);

                paintStyle.setColor(Color.BLUE);
                paintStyle.setStyle(Paint.Style.STROKE);
                paintStyle.setStrokeWidth(7);
                canvas.drawRect(rectangle, paintStyle);
                break;
            }
            case 2: {
                paintStyle.setColor(Color.YELLOW);
                paintStyle.setStyle(Paint.Style.FILL);
                canvas.drawRect(rectangle, paintStyle);

                paintStyle.setColor(Color.CYAN);
                paintStyle.setStyle(Paint.Style.STROKE);
                paintStyle.setStrokeWidth(18);
                canvas.drawRect(rectangle, paintStyle);
                break;
            }
            case 3: {
                paintStyle.setColor(Color.GRAY);
                paintStyle.setStyle(Paint.Style.FILL);
                canvas.drawRect(rectangle, paintStyle);

                paintStyle.setColor(Color.MAGENTA);
                paintStyle.setStyle(Paint.Style.STROKE);
                paintStyle.setStrokeWidth(10);
                canvas.drawRect(rectangle, paintStyle);
                break;
            }
        }

    }
}
