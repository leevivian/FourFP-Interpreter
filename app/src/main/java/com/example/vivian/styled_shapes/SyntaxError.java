package com.example.vivian.styled_shapes;

/**
 * Created by Vivian on 5/11/16.
 */

public class SyntaxError {
    public boolean CheckForParentheses(String toTest) {
        char arrTemp[] = toTest.toCharArray();
        int counter = 0;
        for (int i = 0; i < arrTemp.length; i++) {
            if (arrTemp[i] == '(') {
                counter++;
            }
            if (arrTemp[i] == ')') {
                counter--;
            }
        }
        if (counter == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean opBalance(String toTest) {
        char arrTemp[] = toTest.toCharArray();
        int operand = 0;
        int operators = 0;

        for (int i = 0; i < arrTemp.length; i++) {
            if (arrTemp[i] == '+' || arrTemp[i] == '-' || arrTemp[i] == '*' || arrTemp[i] == '/') {
                operators++;
            }
            if (Character.toString(arrTemp[i]).matches("[a-z]+|[0-9]+")) {
                operand++;
            }
        }
        if (operand > operators) {
            return true;
        } else
            return false;
    }
}
