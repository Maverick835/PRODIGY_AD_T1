package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTextview, solutionTextView;
    MaterialButton btn_C, btn_Percentage, btn_Cut;
    MaterialButton btn_Divide, btn_Multiply, btn_Minus, btn_Plus, btn_Equal, btn_Dot;
    MaterialButton btn_DoubleZero, btn_SingleZero, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultTextview = findViewById(R.id.resultTextView);
        solutionTextView = findViewById(R.id.solutionTextView);

        assignId(btn_C,R.id.btn_C);
        assignId(btn_Percentage,R.id.btn_Percentage);
        assignId(btn_Cut,R.id.btn_Cut);
        assignId(btn_Divide,R.id.btn_Divide);
        assignId(btn_Multiply,R.id.btn_Multiply);
        assignId(btn_Minus,R.id.btn_Minus);
        assignId(btn_Plus,R.id.btn_Plus);
        assignId(btn_Equal,R.id.btn_Equal);
        assignId(btn_Dot,R.id.btn_Dot);
        assignId(btn_DoubleZero,R.id.btn_DoubleZero);
        assignId(btn_SingleZero,R.id.btn_SingleZero);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTextView.getText().toString();

        if(buttonText.equals("C")){
            solutionTextView.setText("");
            resultTextview.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTextView.setText(resultTextview.getText());
            return;
        }
        if (buttonText.equals("⌫")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        } else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTextView.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            resultTextview.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "Err";
        }
    }
}