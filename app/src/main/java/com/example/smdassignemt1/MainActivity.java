package com.example.smdassignemt1;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultDisplay;
    private String currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;
    private double secondNumber = 0;
    private boolean isOperationClicked = false;
    private double memoryValue = 0;
    private TextView tvMC, tvMR, tvMPlus, tvMMinus, tvMS, tvMArrow;
    private Button btnPercent, btnC, btnCE, btnBackspace, btnReciprocal, btnSquare, btnSqrt, btnDivide;
    private Button btnPlusMinus, btnDot, btnEqual, btnPlus, btnMinus, btnMultiply;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setListeners();
    }

    private void initUI(){
        resultDisplay = findViewById(R.id.result_display);
        tvMC = findViewById(R.id.MC);
        tvMR = findViewById(R.id.MR);
        tvMPlus = findViewById(R.id.Mplus);
        tvMMinus = findViewById(R.id.Mminus);
        tvMS = findViewById(R.id.Ms);
        tvMArrow = findViewById(R.id.Marrow);

        btnPercent = findViewById(R.id.btn_percent);
        btnC = findViewById(R.id.btn_c);
        btnCE = findViewById(R.id.btn_ce);
        btnBackspace = findViewById(R.id.btn_backspace);
        btnReciprocal = findViewById(R.id.btn_reciprocal);
        btnSquare = findViewById(R.id.btn_square);
        btnSqrt = findViewById(R.id.btn_sqrt);
        btnDivide = findViewById(R.id.btn_divide);
        btnPlusMinus = findViewById(R.id.btn_plusminus);
        btnDot = findViewById(R.id.btn_dot);
        btnEqual = findViewById(R.id.btn_equal);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
    }

    private void setListeners() {
        tvMC.setOnClickListener(v -> onMemoryClearClicked());
        tvMR.setOnClickListener(v -> onMemoryRecallClicked());
        tvMPlus.setOnClickListener(v -> onMemoryAddClicked());
        tvMMinus.setOnClickListener(v -> onMemorySubtractClicked());
        tvMS.setOnClickListener(v -> onMemoryStoreClicked());
        tvMArrow.setOnClickListener(v -> onMemoryViewClicked());

        btnPercent.setOnClickListener(v -> onPercentClicked());
        btnC.setOnClickListener(v -> onCClicked());
        btnCE.setOnClickListener(v -> onCEClicked());
        btnBackspace.setOnClickListener(v -> onBackspaceClicked());
        btnReciprocal.setOnClickListener(v -> onReciprocalClicked());
        btnSquare.setOnClickListener(v -> onSquareClicked());
        btnSqrt.setOnClickListener(v -> onSqrtClicked());
        btnDivide.setOnClickListener(v -> onOperationClicked("/"));
        btnPlusMinus.setOnClickListener(v -> onPlusMinusClicked());
        btnDot.setOnClickListener(v -> onDotClicked());
        btnEqual.setOnClickListener(v -> onEqualClicked());
        btnPlus.setOnClickListener(v -> onOperationClicked("+"));
        btnMinus.setOnClickListener(v -> onOperationClicked("-"));
        btnMultiply.setOnClickListener(v -> onOperationClicked("*"));
        btn0.setOnClickListener(v -> onNumberClicked("0"));
        btn1.setOnClickListener(v -> onNumberClicked("1"));
        btn2.setOnClickListener(v -> onNumberClicked("2"));
        btn3.setOnClickListener(v -> onNumberClicked("3"));
        btn4.setOnClickListener(v -> onNumberClicked("4"));
        btn5.setOnClickListener(v -> onNumberClicked("5"));
        btn6.setOnClickListener(v -> onNumberClicked("6"));
        btn7.setOnClickListener(v -> onNumberClicked("7"));
        btn8.setOnClickListener(v -> onNumberClicked("8"));
        btn9.setOnClickListener(v -> onNumberClicked("9"));
    }

    private void onNumberClicked(String number) {
        if (isOperationClicked) {
            currentNumber = "";
            isOperationClicked = false;
        }
        currentNumber += number;
        resultDisplay.setText(currentNumber);
    }

    private void onOperationClicked(String op) {
        if (!currentNumber.isEmpty()) {
            firstNumber = Double.parseDouble(currentNumber);
            operation = op;
            isOperationClicked = true;
        }
    }

    private void onEqualClicked() {
        if (!currentNumber.isEmpty() && !operation.isEmpty()) {
            secondNumber = Double.parseDouble(currentNumber);
            double result = 0;
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultDisplay.setText("Error");
                        return;
                    }
                    break;
            }
            resultDisplay.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            operation = "";
            isOperationClicked = true;
        }
    }

    private void onCClicked() {
        currentNumber = "";
        firstNumber = 0;
        secondNumber = 0;
        operation = "";
        resultDisplay.setText("0");
    }

    private void onCEClicked() {
        currentNumber = "";
        resultDisplay.setText("0");
    }

    private void onBackspaceClicked() {
        if (currentNumber.length() > 0) {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
            resultDisplay.setText(currentNumber);
        }
    }

    private void onPercentClicked() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = number / 100;
            resultDisplay.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
        }
    }

    private void onReciprocalClicked() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            if (number != 0) {
                double result = 1 / number;
                resultDisplay.setText(String.valueOf(result));
                currentNumber = String.valueOf(result);
            } else {
                resultDisplay.setText("Error");
            }
        }
    }

    private void onSquareClicked() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = number * number;
            resultDisplay.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
        }
    }

    private void onSqrtClicked() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            if (number >= 0) {
                double result = Math.sqrt(number);
                resultDisplay.setText(String.valueOf(result));
                currentNumber = String.valueOf(result);
            } else {
                resultDisplay.setText("Error");
            }
        }
    }

    private void onPlusMinusClicked() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            number = -number;
            resultDisplay.setText(String.valueOf(number));
            currentNumber = String.valueOf(number);
        }
    }

    private void onDotClicked() {
        if (!currentNumber.contains(".")) {
            currentNumber += ".";
            resultDisplay.setText(currentNumber);
        }
    }

    private void onMemoryClearClicked() {
        memoryValue = 0;
    }

    private void onMemoryRecallClicked() {
        resultDisplay.setText(String.valueOf(memoryValue));
        currentNumber = String.valueOf(memoryValue);
    }

    private void onMemoryAddClicked() {
        if (!currentNumber.isEmpty()) {
            memoryValue += Double.parseDouble(currentNumber);
        }
    }

    private void onMemorySubtractClicked() {
        if (!currentNumber.isEmpty()) {
            memoryValue -= Double.parseDouble(currentNumber);
        }
    }

    private void onMemoryStoreClicked() {
        if (!currentNumber.isEmpty()) {
            memoryValue = Double.parseDouble(currentNumber);
        }
    }

    private void onMemoryViewClicked() {
        if (!currentNumber.isEmpty()) {
            double temp = memoryValue;
            memoryValue = Double.parseDouble(currentNumber);
            currentNumber = String.valueOf(temp);
            resultDisplay.setText(currentNumber);
        }
    }
}