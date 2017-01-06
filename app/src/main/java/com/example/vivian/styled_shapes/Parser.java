package com.example.vivian.styled_shapes;

import java.lang.Integer;

public class Parser {

    private StringBuffer input;
    private int inputIndex;

    public int evaluate(String s) throws ParseException {
        inputIndex = 0;
        input = new StringBuffer(s);
        Nodes rootNode = parseAddSub();
        return evaluateParserTree(rootNode);
    }

    private int evaluateParserTree(Nodes tree) {
        if (tree == null) {
            Integer treeNull = null;
            return treeNull;
        }
        if (tree.value.toString().equals("+")) {
            return evaluateParserTree(tree.leftTree) + evaluateParserTree(tree.rightTree);
        } else if (tree.value.toString().equals("-")) {
            return evaluateParserTree(tree.leftTree) - evaluateParserTree(tree.rightTree);
        } else if (tree.value.toString().equals("*")) {
            return evaluateParserTree(tree.leftTree) * evaluateParserTree(tree.rightTree);
        } else if (tree.value.toString().equals("/")) {
            return evaluateParserTree(tree.leftTree) / evaluateParserTree(tree.rightTree);
        } else {
            return Integer.parseInt(tree.value.toString());
        }
    }

    private enum TokenType {
        number, add, substract, multiply, divide, openParen, closedParen, endOfTree
    }

    private TokenType getNextTokenType() throws ParseException {
        if (inputIndex == input.length()) {
            return TokenType.endOfTree;
        } else if (input.charAt(inputIndex) >= '0' && input.charAt(inputIndex) <= '9') {
            return TokenType.number;
        } else if (input.charAt(inputIndex) == '+') {
            inputIndex++;
            return TokenType.add;
        } else if (input.charAt(inputIndex) == '-') {
            inputIndex++;
            return TokenType.substract;
        } else if (input.charAt(inputIndex) == '*') {
            inputIndex++;
            return TokenType.multiply;
        } else if (input.charAt(inputIndex) == '/') {
            inputIndex++;
            return TokenType.divide;
        } else if (input.charAt(inputIndex) == '(') {
            inputIndex++;
            return TokenType.openParen;
        } else if (input.charAt(inputIndex) == ')') {
            inputIndex++;
            return TokenType.closedParen;
        } else {
            throw new ParseException(input.charAt(inputIndex) + " is not a valid token");
        }

    }

    private void lastToken() {
        inputIndex--;
    }

    private Nodes parseAddSub() throws ParseException {
        Nodes rootNode = parseMulDiv();
        TokenType nextToken = getNextTokenType();
        while (nextToken == TokenType.add || nextToken == TokenType.substract) {
            Nodes newRootNode;
            if (nextToken == TokenType.add) {
                newRootNode = new Nodes('+');
            } else {
                newRootNode = new Nodes('-');
            }
            newRootNode.leftTree = rootNode;
            newRootNode.rightTree = parseMulDiv();
            rootNode = newRootNode;
            nextToken = getNextTokenType();
        }
        lastToken();
        return rootNode;
    }

    private Nodes parseMulDiv() throws ParseException {
        Nodes rootNode = parseInput();
        TokenType nextToken = getNextTokenType();

        while (nextToken == TokenType.multiply || nextToken == TokenType.divide) {
            Nodes newRootNode;
            if (nextToken == TokenType.multiply) {
                newRootNode = new Nodes('*');
            } else {
                newRootNode = new Nodes('/');
            }
            newRootNode.leftTree = rootNode;
            newRootNode.rightTree = parseInput();
            rootNode = newRootNode;
            nextToken = getNextTokenType();
        }
        lastToken();
        return rootNode;
    }

    private Nodes parseInput() throws ParseException {
        TokenType nextToken = getNextTokenType();
        Nodes rootNode = null;
        if (nextToken == TokenType.number) {
            int num = nextValue();
            rootNode = new Nodes(num);
        } else if (nextToken == TokenType.openParen) {
            rootNode = parseAddSub();
            nextToken = getNextTokenType();
            if (nextToken != TokenType.closedParen) {
                throw new ParseException("A closing bracket is missing.");
            }
        }
        return rootNode;
    }

    private int nextValue() throws ParseException {
        int nextIndex;
        for (nextIndex = inputIndex; nextIndex < input.length(); nextIndex++) {
            if ((input.charAt(nextIndex) < '0' || input.charAt(nextIndex) > '9')) {
                break;
            }
        }
        if (nextIndex > input.length()) {
            throw new ParseException("Invalid numerical value.");
        }

        String sub = input.substring(inputIndex, nextIndex);
        int numberVal;
        try {
            numberVal = Integer.parseInt(sub);
        } catch (NumberFormatException ex) {
            throw new ParseException("Error has occured while parsing");
        }
        inputIndex = inputIndex + (nextIndex - inputIndex);
        return numberVal;
    }
}
