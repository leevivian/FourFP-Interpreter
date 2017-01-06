package com.example.vivian.styled_shapes;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.graphics.Canvas;

/**
 * Created by Vivian on 3/21/16.
 */

public abstract class Shape extends View {

    float initialAlpha = 1.0f;
    float newAlpha = 0.99f;

    int xS, yS, radiusS, styleShape, ybotS;
    Paint paintStyle;

    public Shape(Context context, int style, int x, int y, int radius, int ybot) {
        super(context);
        styleShape = style;
        xS = x;
        yS = y;
        radiusS = radius;
        ybotS = ybot;
        paintStyle = new Paint();
    }

    // Function that sets the transparency of a shapeCreated
    void setShapeAlpha(float alpha) {
        newAlpha = alpha;
        paintStyle = new Paint();
    }

    // Function returns the alpha of a shapeCreated
    float getShapeAlpha() {
        if (newAlpha < 0.99f) {
            return newAlpha;
        } else {
            return initialAlpha;
        }
    }

    // Function that makes the shape disappear
    // Set visibility to gone
    void removeShape() {
        setVisibility(View.GONE);
    }

    // returns indication of shapeCreated type, method implemented in Rect and Circle
    abstract String getShapeType();

    //implemented by child classes
    @Override
    public abstract void onDraw(Canvas canvas);

}
