package com.dom.mipt1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        initComponents();
    }

    private void initComponents() {
        //First button changes TextBox content.
        Button textChangeBtn = findViewById(R.id.btn1);
        textChangeBtn.setOnClickListener((event) -> onBtn1Click());

        //Second button changes TextBox color to green. Otherwise, text color will be set to Black.
        Button colorChangeBtn = findViewById(R.id.btn2);
        colorChangeBtn.setOnClickListener((event) -> onBtn2Click());
    }


    public void onBtn1Click() {
        TextView textBox = findViewById(R.id.textBox);
        textBox.setText("Hello VU KNF!");
    }

    public void onBtn2Click() {
        TextView textBox = findViewById(R.id.textBox);
        if (textBox.getCurrentTextColor() != Color.GREEN) {
            textBox.setTextColor(Color.GREEN);
            return;
        }
        textBox.setTextColor(Color.BLACK);
    }
}