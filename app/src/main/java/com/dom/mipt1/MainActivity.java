package com.dom.mipt1;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSpinner();
        initComponents();
    }

    private void initSpinner() {
        List<String> variants = List.of("Žodžiai", "Ženklai");

        Spinner spinnerElement = findViewById(R.id.userSelection);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, variants);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerElement.setAdapter(adapter);
    }


    private void initComponents() {
        Button calculateButton = findViewById(R.id.calcButton);
        calculateButton.setOnClickListener((event) -> onCalcBtnClick());
    }

    public void onCalcBtnClick() {
        Spinner spinnerElement = findViewById(R.id.userSelection);
        EditText textBox = findViewById(R.id.textBox);
        TextView infoBar = findViewById(R.id.infoBar);
        try {
            TextUtils calculator = new TextUtils(textBox.getText().toString());
            if (spinnerElement.getSelectedItem().equals("Žodžiai")) {
                infoBar.setText(getString(R.string.word_count) + calculator.countWords());
                return;
            }
            infoBar.setText(getString(R.string.symbols_count) + calculator.countSymbols());

        } catch (IllegalArgumentException ex) {
            Toast.makeText(this, getString(R.string.text_empty_err), Toast.LENGTH_SHORT).show();
        }

    }
}