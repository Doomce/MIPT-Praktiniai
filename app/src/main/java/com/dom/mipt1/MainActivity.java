package com.dom.mipt1;

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
    }


    public void onBtn1Click() {
        TextView textBox = findViewById(R.id.textBox);
        textBox.setText("Hello VU KNF!");
    }
}