package com.example.carculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/*
* Ejercicios propuestos
*   1.Propuestas pendientes
*   2.Declaracion de atributos masivos NO LISTADO
*   3.Enlace de objetos con Vista con Bucle
*   4. Asignacion de eventos en bucles *
*   5. On click -simblificado
* */
class Carculadora extends AppCompatActivity {

    private TextView display;
    private double num1 = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        //Setup all button click listeners
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        int[] numericButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener numericClickListener = v -> {
            Button button = (Button) v;
            String text = display.getText().toString();

            if (isOperatorPressed) {
                display.setText(button.getText().toString());
                isOperatorPressed = false;
            } else {
                if (text.equals("0")) {
                    display.setText(button.getText().toString());
                } else {
                    display.append(button.getText().toString());
                }
            }
        };

        for (int id : numericButtonIds) {
            findViewById(id).setOnClickListener(numericClickListener);
        }

        //operation buttons
        findViewById(R.id.btnSumar).setOnClickListener(v -> performOperation("+"));
        findViewById(R.id.btnRestar).setOnClickListener(v -> performOperation("-"));
        findViewById(R.id.btnMulti).setOnClickListener(v -> performOperation("*"));
        findViewById(R.id.btnDivision).setOnClickListener(v -> performOperation("/"));
        findViewById(R.id.btnIgual).setOnClickListener(v -> calculateResult());

        //decimal point button
        findViewById(R.id.btnPunto).setOnClickListener(v -> {
            if (!display.getText().toString().contains(".")) {
                display.append(".");
            }
        });

        //clear button
        findViewById(R.id.btnClear).setOnClickListener(v -> display.setText("0"));
    }

    private void performOperation(String operator) {
        num1 = Double.parseDouble(display.getText().toString());
        this.operator = operator;
        isOperatorPressed = true;  //marca que se ha presionado un operador
    }

    private void calculateResult() {
        double num2 = Double.parseDouble(display.getText().toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        isOperatorPressed = false;  // Reset the flag
    }
}
