package com.example.vivian.styled_shapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Arrays;
import java.util.HashMap;
import android.view.KeyEvent;
import android.widget.EditText;
import android.view.View.OnKeyListener;

public class MainActivity extends AppCompatActivity {

    static int x, y, radius, style, ybot;
    static String var;
    static int varInt;
    Shape shapeCreated;
    int i = 0;
    VarName currentVar;
    SyntaxError syntaxError = new SyntaxError();
    HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
    EditText editText;
    Button submit;
    TextView textView;
    TextView mathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        final RelativeLayout shapesLayout = (RelativeLayout) findViewById(R.id.shapesLayout);

        textView = (TextView) findViewById(R.id.textView);
        mathView = (TextView) findViewById(R.id.textView2);

        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapesLayout.removeAllViewsInLayout();
                textView.setText("Most Recent Input");
                mathView.setText("Calculated Arithmetic Expression");
                editText.setText("");
                hashMap.clear();
            }
        });

        editText = (EditText) findViewById(R.id.editText);
        submit = (Button) findViewById(R.id.submit);

        editText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    submit.performClick();
                    return true;
                }
                return false;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = editText.getText().toString();
                textView.setText("Input: " + input);

                if (input.contains("#")) {
                    String[] splitComment = Delimiter.commentSplit(input);
                    input = splitComment[0];
                    Log.i("log", input);
                }
                String[] splitInput = Delimiter.split(input);

                try {
                    if (input.matches("\\s*circle(\\s+([0-9]+|[a-z]+)){4}\\s*;\\s*")) {
                        try { // go to class, pass in input as parameter, class should use delimiter

                            if (!splitInput[0].equals("circle")) {
                                splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                            }
                            if (splitInput[4].contains(";")) {
                                splitInput[4] = splitInput[4].replace(";", "");
                            }

                            for (int i = 1; i < splitInput.length; i++) {

                                if (hashMap.containsKey(splitInput[i])) {
                                    splitInput[i] = Integer.toString(hashMap.get(splitInput[i]));
                                }
                            }
                            x = Integer.parseInt(splitInput[1]);
                            y = Integer.parseInt(splitInput[2]);
                            radius = Integer.parseInt(splitInput[3]);
                            ybot = 0;

                            style = Integer.parseInt(splitInput[4]);
                            if (style < 1 || style > 3) {
                                textView.setText("Error: Style does not exist");
                                mathView.setText("");
                            }

                            ShapeFactory shapeFactory =
                                    AbstractShapeFactory.getShapeFactory(x, y, radius, style, ybot);
                            shapeCreated = shapeFactory.getShape(view.getContext(), "circle");
                            shapesLayout.addView(shapeCreated);
                            mathView.setText("");
                        } catch (Exception e) {
                            textView.setText("Error: No such variable exists.");
                            mathView.setText("");
                        }
                    } else if (input.matches("\\s*rect(\\s+([0-9]+|[a-z]+)){5}\\s*;\\s*")) {
                        try {// go to class, pass in input as parameter, class should use delimiter

                            if (!splitInput[0].equals("rect")) {
                                splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                            }
                            for (int i = 1; i < splitInput.length - 1; i++) {
                                if (hashMap.containsKey(splitInput[i])) {
                                    splitInput[i] = Integer.toString(hashMap.get(splitInput[i]));
                                }
                            }
                            x = Integer.parseInt(splitInput[1]);
                            y = Integer.parseInt(splitInput[2]);
                            radius = Integer.parseInt(splitInput[3]);
                            ybot = Integer.parseInt(splitInput[4]);

                            if (splitInput[5].contains(";")) {
                                splitInput[5] = splitInput[5].replace(";", "");
                            }
                            style = Integer.parseInt(splitInput[5]);
                            if (style < 1 || style > 3) {
                                textView.setText("Error: Style does not exist");
                                mathView.setText("");
                            }
                            if ((x == y) || (radius == ybot)) {

                            }
                            ShapeFactory shapeFactory =
                                    AbstractShapeFactory.getShapeFactory(x, y, radius, style, ybot);
                            shapeCreated = shapeFactory.getShape(view.getContext(), "rectangle");
                            shapesLayout.addView(shapeCreated);
                            mathView.setText("");

                        } catch (Exception e) {
                            textView.setText("Error: No such variable exists.");
                            mathView.setText("");
                        }

                    } else if (input.matches("\\s*int\\s+[a-z]+\\s+=\\s+[0-9]+\\s*;\\s*")) {
                        if (!splitInput[0].equals("int")) {
                            splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                        }

                        var = splitInput[1];
                        if (splitInput[3].contains(";")) {
                            splitInput[3] = splitInput[3].replace(";", "");
                        }
                        if (hashMap.containsKey(var)) {
                            mathView.setText("Error: Variable \"" + var + "\" has already been declared.");
                        } else {

                            varInt = Integer.parseInt(splitInput[3]);
                            currentVar = new VarName(var, varInt);
                            hashMap.put(currentVar.returnName(), currentVar.returnInt());
                            mathView.setText("Variable \"" + var + "\" has been set to " + varInt);
                        }

                    } else if (input.matches("\\s*[a-z]+\\s+=\\s+[0-9]+\\s*;\\s*") && !input.contains("int")) {

                        if (!splitInput[0].matches("[a-z]+")) {
                            splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                        }
                        if (splitInput[2].contains(";")) {
                            splitInput[2] = splitInput[2].replace(";", "");
                        }
                        var = splitInput[0];
                        varInt = Integer.parseInt(splitInput[2]);
                        currentVar = new VarName(var, varInt);
                        hashMap.put(currentVar.returnName(), currentVar.returnInt());
                        mathView.setText("Variable \"" + var + "\" has been set to " + varInt);

                    } else if (input.matches("\\s*.+\\s+.+\\s*;\\s*") &&
                            syntaxError.CheckForParentheses(input) &&
                            syntaxError.opBalance(input) &&
                            (input.contains("+") || input.contains("-") || input.contains("*")
                                    || input.contains("/") || input.contains("(") || input.contains(")"))) {

                        splitInput[splitInput.length - 1] = splitInput[splitInput.length - 1].replace(";", "");

                        if (input.contains("=")) { // for variable assignment

                            if (input.contains("int")) {
                                if (!splitInput[0].equals("int")) {
                                    splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                                }
                                var = splitInput[1];
                                for (i = 0; i < 3; i++) {
                                    splitInput[i] = splitInput[i].replace(splitInput[i], "");
                                }

                            } else { // Ex) a = a + 1 ;
                                if (!splitInput[0].matches("[a-z]+")) {
                                    splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                                }
                                var = splitInput[0];
                                for (i = 0; i < 2; i++) {
                                    splitInput[i] = splitInput[i].replace(splitInput[i], "");
                                }
                            }

                            for (int i = 0; i < splitInput.length; i++) {
                                if (hashMap.containsKey(splitInput[i])) {
                                    splitInput[i] = Integer.toString(hashMap.get(splitInput[i]));
                                }
                            }

                            String math = TextUtils.join("", splitInput);
                            Parser pit = new Parser();
                            int finpit = pit.evaluate(math);
                            varInt = finpit;
                            currentVar = new VarName(var, varInt);
                            hashMap.put(currentVar.returnName(), currentVar.returnInt());
                            mathView.setText("Variable \"" + var + "\" has been set to " + finpit);

                        } else { // evaluate just an expression, no assignment to a variable

                            for (int i = 0; i < splitInput.length; i++) {
                                if (hashMap.containsKey(splitInput[i])) {
                                    splitInput[i] = Integer.toString(hashMap.get(splitInput[i]));
                                }
                            }

                            String mathExpression = TextUtils.join("", splitInput);
                            Parser newTreeParser = new Parser();
                            int mathAnswer = newTreeParser.evaluate(mathExpression);
                            mathView.setText("Output: " + Integer.toString(mathAnswer));
                        }

                    } else { // if input doesn't match anything
                        textView.setText("Invalid input: " + input);
                        mathView.setText("");
                    }
                } catch (Exception e) {
                    textView.setText("Invalid statement: " + input);
                    mathView.setText("");
                }
                editText.setText("");
            }
        });
    }
}
