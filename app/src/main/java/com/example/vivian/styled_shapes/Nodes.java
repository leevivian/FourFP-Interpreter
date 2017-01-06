package com.example.vivian.styled_shapes;

/**
 * Created by Vivian on 5/14/16.
 */

public class Nodes {
    public Nodes leftTree;
    public Nodes rightTree;
    public Object value;

    public Nodes(Object v) {
        leftTree = rightTree = null;
        value = v;
    }
}
