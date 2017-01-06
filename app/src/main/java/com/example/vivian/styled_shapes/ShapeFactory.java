package com.example.vivian.styled_shapes;
import android.content.Context;
import android.graphics.Paint;

/**
 * Created by Vivian on 3/21/16.
 */

public class ShapeFactory extends AbstractShapeFactory {
    int currentStyle;
    int xC;
    int yC;
    int radiusC;
    int ybotC;

    public ShapeFactory(int x, int y, int radius, int style, int ybot) {
        currentStyle = style;
        xC = x;
        yC = y;
        radiusC = radius;
        ybotC = ybot;
    }

    public Shape getShape(Context context, String shape){
        if(shape == null){
            return null;
        }
        if(shape.equals("circle")){
            return new Circle(context, currentStyle, xC, yC, radiusC, ybotC);

        } else if(shape.equals("rectangle")){
            return new Rectangle(context, currentStyle, xC, yC, radiusC, ybotC);
        }
        return null;
    }
}
