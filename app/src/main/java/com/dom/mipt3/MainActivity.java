package com.dom.mipt3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.dom.mipt3.calculator.CalculatorButtonManagement;
import com.dom.mipt3.calculator.CalculatorLogic;
import com.dom.mipt3.calculator.CalculatorScreenManagement;

public class MainActivity extends AppCompatActivity {

    protected CalculatorLogic calculator = new CalculatorLogic();

    protected static CalculatorButtonManagement BtnInstance;
    protected CalculatorScreenManagement ScreenInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScreenInstance = new CalculatorScreenManagement(findViewById(R.id.calcScreenTop), findViewById(R.id.calcScreenBottom));
        BtnInstance = new CalculatorButtonManagement(ScreenInstance);
        BtnInstance.initButtons(this);
    }


    private void onNumberKeyPress(int digit) {

    }


    private void initButtons() {


    }



}