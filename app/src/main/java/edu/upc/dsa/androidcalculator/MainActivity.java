package edu.upc.dsa.androidcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean OperationPending = false;

    public void inputNumber (View v){
        Button clickedButton = (Button) v;
        String buttonText = clickedButton.getText().toString();
        TextView resultBox = findViewById(R.id.resultBox);
        String currentText = resultBox.getText().toString();
        resultBox.setText(currentText + "" + buttonText);
    }

    public void clear (View v){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText("");
        OperationPending = false;
    }

    public void inputOperation (View v){
        TextView resultBox = findViewById(R.id.resultBox);
        Button clickedButton = (Button) v;
        String buttonText = clickedButton.getText().toString();
        if (OperationPending){
            String[] parts = resultBox.getText().toString().split("([+-/*])"); // Splits by +, -, *, or /
            String operator = resultBox.getText().toString().replaceAll("[0-9]", ""); // Extracts the operator

            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            int result = 0;

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
                    result = num1 / num2;
                    break;
            }

            OperationPending=true;

            resultBox.setText(result + "" + buttonText);
        }

        else {
            String currentText = resultBox.getText().toString();
            resultBox.setText(currentText + "" + buttonText);
            OperationPending=true;
        }
    }

    public void equalOperation (View v) {
        if (OperationPending) {
            TextView resultBox = findViewById(R.id.resultBox);
            String[] parts = resultBox.getText().toString().split("([+-/*])"); // Splits by +, -, *, or /
            String operator = resultBox.getText().toString().replaceAll("[0-9]", ""); // Extracts the operator

            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            int result = 0;

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
                    result = num1 / num2;
                    break;
            }

            OperationPending = false;

            resultBox.setText(""+result);
        }
    }
}
