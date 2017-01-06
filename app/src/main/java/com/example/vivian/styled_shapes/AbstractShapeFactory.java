package com.example.vivian.styled_shapes;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Vivian on 5/2/16.
 */

public abstract class AbstractShapeFactory {

    public static ShapeFactory getShapeFactory(int x, int y, int radius, int style, int ybot) {
        return new ShapeFactory(x, y, radius, style, ybot);
    }
}


