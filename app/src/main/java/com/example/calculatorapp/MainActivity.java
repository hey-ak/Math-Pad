package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private String number, operator;
    private boolean isOperatorClicked;
    private Double previousResult = null; // New variable to store the previous result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.tv_result);
        number = "";
        operator = "";
        isOperatorClicked = false;
    }

    public void numberClick(View view) {
        Button button = (Button) view;
        if (previousResult != null && !isOperatorClicked) {
            number = "";
            previousResult = null;
        }
        number += button.getText().toString();
        result.setText(number);
    }

    public void operationClick(View view) {
        if (previousResult != null && !isOperatorClicked) {
            number = String.valueOf(previousResult);
            previousResult = null;
        }
        isOperatorClicked = true;
        Button button = (Button) view;
        operator = button.getText().toString();
        number += operator;
        result.setText(number);
    }

    public void clearClick(View view) {
        number = "";
        operator = "";
        isOperatorClicked = false;
        previousResult = null;
        result.setText("");
    }

    public void equalsClick(View view) {
        if (isOperatorClicked) {
            String[] split = number.split(Pattern.quote(operator));
            double firstNum = Double.parseDouble(split[0]);
            double secondNum = Double.parseDouble(split[1]);
            double resultNum = 0;

            switch (operator) {
                case "+":
                    resultNum = firstNum + secondNum;
                    break;
                case "-":
                    resultNum = firstNum - secondNum;
                    break;
                case "*":
                    resultNum = firstNum * secondNum;
                    break;
                case "/":
                    if (secondNum != 0) {
                        resultNum = firstNum / secondNum;
                    } else {
                        result.setText("Error");
                        return;
                    }
                    break;
            }

            previousResult = resultNum; // Store the result
            number = String.valueOf(resultNum);
            operator = "";
            isOperatorClicked = false;
            result.setText(String.valueOf(resultNum));
        }
    }
}
