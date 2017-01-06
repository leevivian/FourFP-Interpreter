package com.example.vivian.styled_shapes;

/**
 * Created by Vivian on 5/11/16.
 */

public class VarName {

    String name;
    public int value;

    public VarName(String NameOfVar, int valueIn) {
        name = NameOfVar;
        value = valueIn;
    }

    public String returnName() {
        return name;
    }

    public int returnInt() {
        return value;
    }
}
