package com.example.mycalculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button modulus = findViewById(R.id.modulus);
        Button division = findViewById(R.id.division);
        Button mul = findViewById(R.id.mul);
        Button sub = findViewById(R.id.sub);
        Button add = findViewById(R.id.add);
        Button equal = findViewById(R.id.equals);
        Button exit = findViewById(R.id.exit);

        Button zero = findViewById(R.id.zero);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button dot = findViewById(R.id.dot);

        final double[] firstNumber = {0};
        final String[] currentValue = {""};
        final String[] operator = {""};

        ac.setOnClickListener(v -> {
            currentValue[0] = "";
            textView.setText("0");
        });

        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            currentValue[0] += button.getText().toString();
            textView.setText(currentValue[0]);
        };

        // Assign numberClickListener to all number buttons
        zero.setOnClickListener(numberClickListener);
        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        three.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        dot.setOnClickListener(numberClickListener);

        add.setOnClickListener(operatorClickListener("+", firstNumber, currentValue, operator, textView));
        sub.setOnClickListener(operatorClickListener("-", firstNumber, currentValue, operator, textView));
        mul.setOnClickListener(operatorClickListener("*", firstNumber, currentValue, operator, textView));
        division.setOnClickListener(operatorClickListener("/", firstNumber, currentValue, operator, textView));
        modulus.setOnClickListener(operatorClickListener("%", firstNumber, currentValue, operator, textView));

        equal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (!currentValue[0].isEmpty() && !operator[0].isEmpty()) {
                    double secondNumber = Double.parseDouble(currentValue[0]);
                    double result = 0;

                    // Perform the calculation based on the operator
                    switch (operator[0]) {
                        case "+":
                            result = firstNumber[0] + secondNumber;
                            break;
                        case "-":
                            result = firstNumber[0] - secondNumber;
                            break;
                        case "*":
                            result = firstNumber[0] * secondNumber;
                            break;
                        case "/":
                            result = secondNumber != 0 ? firstNumber[0] / secondNumber : 0;
                            break;
                        case "%":
                            result = firstNumber[0] % secondNumber;
                            break;
                    }

                    // Display the result
                    textView.setText(firstNumber[0] + " " + operator[0] + " " + secondNumber + " = " + result);
                    currentValue[0] = "";
                    operator[0] = "";
                }
            }
        });

        del.setOnClickListener(v -> {
            if (!currentValue[0].isEmpty()) {
                if (operator[0].isEmpty()) {
                    currentValue[0] = currentValue[0].substring(0, currentValue[0].length() - 1);
                    textView.setText(currentValue[0].isEmpty() ? "0" : currentValue[0]);
                } else {
                    operator[0] = "";
                    textView.setText(String.valueOf(firstNumber[0]));
                    currentValue[0] = "";
                }
            }
        });

        exit.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }

    @SuppressLint("SetTextI18n")
    private View.OnClickListener operatorClickListener(final String op,
                                                       final double[] firstNumber,
                                                       final String[] currentValue,
                                                       final String[] operator,
                                                       final TextView textView) {
        return v -> {
            if (!currentValue[0].isEmpty()) {
                firstNumber[0] = Double.parseDouble(currentValue[0]);
                operator[0] = op;
                textView.setText(currentValue[0] + " " + operator[0]);
                currentValue[0] = "";
            }
        };
    }
}
