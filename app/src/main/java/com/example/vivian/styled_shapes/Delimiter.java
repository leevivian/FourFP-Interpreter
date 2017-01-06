package com.example.vivian.styled_shapes;
import android.util.Log;

/**
 * Created by Vivian on 5/11/16.
 */

public class Delimiter {

    static String someInput;

    static String[] split(String input){
        someInput = input;

        String[] splited = someInput.split("\\s+");
        return splited;
    }

    static String[] commentSplit(String input) {
        someInput = input;

        String[] splited = someInput.split("\\#");
        return splited;
    }
}
