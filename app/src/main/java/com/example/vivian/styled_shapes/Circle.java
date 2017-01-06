package com.example.vivian.styled_shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.util.DisplayMetrics;

/**
 * Created by Vivian on 3/21/16.
 */

public class Circle extends Shape {
    float alphaShape;

    // Gets device screen width and height
    DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
    final int width = metrics.widthPixels;
    final int height = metrics.heightPixels;

    int x = xS;
    int y = yS;
    int radius = radiusS;

    public Circle(Context context, int style, int x, int y, int radius, int ybot) {
        super(context, style, x, y, radius, ybot);
    }

    public String getShapeType() {
        return "circle";
    }

    @Override
    public void onDraw(Canvas canvas) {

        alphaShape = super.getShapeAlpha();
        setAlpha(alphaShape);

        switch (styleShape) {
            case 1: {
                paintStyle.setColor(Color.GREEN);
                paintStyle.setStyle(Paint.Style.FILL);
                canvas.drawCircle(x, y, radius, paintStyle);

                paintStyle.setColor(Color.BLUE);
                paintStyle.setStyle(Paint.Style.STROKE);
                paintStyle.setStrokeWidth(7);
                canvas.drawCircle(x, y, radius, paintStyle);
                break;
            }
            case 2: {
                paintStyle.setColor(Color.YELLOW);
                paintStyle.setStyle(Paint.Style.FILL);
                canvas.drawCircle(x, y, radius, paintStyle);

                paintStyle.setColor(Color.CYAN);
                paintStyle.setStyle(Paint.Style.STROKE);
                paintStyle.setStrokeWidth(18);
                canvas.drawCircle(x, y, radius, paintStyle);
                break;
            }
            case 3: {
                paintStyle.setColor(Color.GRAY);
                paintStyle.setStyle(Paint.Style.FILL);
                canvas.drawCircle(x, y, radius, paintStyle);

                paintStyle.setColor(Color.MAGENTA);
                paintStyle.setStyle(Paint.Style.STROKE);
                paintStyle.setStrokeWidth(10);
                canvas.drawCircle(x, y, radius, paintStyle);
                break;
            }
        }
    }
}