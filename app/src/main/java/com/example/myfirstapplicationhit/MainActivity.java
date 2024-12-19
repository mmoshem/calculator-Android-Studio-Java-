package com.example.myfirstapplicationhit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView result;
    int num1=0,num2=0;
    char op='0';
    int flagEqual=0;

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
        result = findViewById(R.id.textViewResult);
        result.setText("");
    }

    @SuppressLint("SetTextI18n")
    public void buttonfunc(View view) {
        Button button = (Button) view;
        String clickedNow = button.getText().toString();

        //כשמנקים
        if(clickedNow.equals("clear")){
            result.setText("");
            op = '0';
            num1 = 0;
            num2 = 0;
            flagEqual = 0;
            return;
        }
        //נכנס כשלוחצים מספר
        if(!clickedNow.equals("+") &&!clickedNow.equals("-") &&!clickedNow.equals("*") &&
                !clickedNow.equals("/")&&!clickedNow.equals("="))
        {
            if(result.getText().toString().equals("Error")){
                result.setText("");
            }
            result.append(clickedNow);
        }
        else if (clickedNow.equals("-") && result.getText().toString().isEmpty()) {
            // Allow negative numbers at the start
            result.append("-");
        }
        else if (clickedNow.equals("-") && result.getText().toString().equals("-")||
                clickedNow.equals("*") && result.getText().toString().equals("-")||
                clickedNow.equals("/") && result.getText().toString().equals("-")||
                clickedNow.equals("+") && result.getText().toString().equals("-")) {
            // Allow negative numbers at the start
            result.setText("Error");
        }
        // נכנס כשלוחצים שווה
        else if(clickedNow.equals("=")){

            if(op!='0'&&!result.getText().toString().isEmpty()&&!result.getText().toString().equals("Error")) {
                if (flagEqual == 0){
                    num2 = Integer.parseInt(result.getText().toString());
                }
                else{
                    num1 = Integer.parseInt(result.getText().toString());
                }
                switch (op) {
                    case '+':
                        result.setText(Integer.toString(num1 + num2));
                        break;
                    case '-':
                        result.setText(Integer.toString(num1 - num2));
                        break;
                    case '*':
                        result.setText(Integer.toString(num1 * num2));
                        break;
                    case '/':
                        // Check for division by zero
                        if (num2 != 0) {
                            result.setText(Integer.toString(num1 / num2));
                        } else {
                            result.setText("Error");
                        }
                }
                    flagEqual =1;
            }
            else{
                result.setText("Error");
                op='0';
                flagEqual=0;
                num1=0;
                num2=0;
            }
        }
        //אם לנחץ אופרטור
        else{
            if(result.getText().toString().equals("Error")){
                op='0';
                result.setText("");
                flagEqual=0;
            }
            if((!result.getText().toString().isEmpty() && op == '0'||(flagEqual==1))){  // "" ->  not getting inside ; "345"->getting inside
                op = clickedNow.charAt(0);
                num1 = Integer.parseInt(result.getText().toString());
                result.setText("");
                if(flagEqual==1){
                    flagEqual=0;
                }
            }
            else{
                result.setText("Error");
            }
        }
    }
}
