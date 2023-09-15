package com.dom.mipt3.calculator;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.dom.mipt3.R;
import com.dom.mipt3.calculator.operations.ArithmeticOperation;

import java.util.List;
import java.util.regex.Pattern;


public class CalculatorButtonManagement {

    private CalculatorScreenManagement ScreenInstance;

    public CalculatorButtonManagement(CalculatorScreenManagement instance) {
        ScreenInstance = instance;
    }

    private void onNumberKeyPress(int digit) {
        if (ScreenInstance.getTopScreenText().contains("=")) {
            CalculatorLogic.clearContent();
            ScreenInstance.setTopScreenText("");
        }
        ScreenInstance.appendBottomScreenText(String.valueOf(digit));
    }

    private void onKeyPress(String btnName) {
        switch (btnName) {
            case "clear" -> ScreenInstance.setBottomScreenText("");
            case "comma" -> ScreenInstance.appendBottomScreenText(".");
            case "backspace" -> ScreenInstance.backspace();
            case "plusminus" -> ScreenInstance.changeSign();
            case "clearEverything" -> ScreenInstance.clearScreens();
        }
    }

    private void onFunctionKeyPress(ArithmeticOperation operation) {

        resetWhenError();
        ArithmeticOperation lastOperation = CalculatorLogic.getLastOperator();
        String bottomText = ScreenInstance.getBottomScreenText();

        if (CalculatorLogic.removeEqualitySymbol()) {
            CalculatorLogic.clearContent();
            CalculatorLogic.appendDigitToContent(ScreenInstance.getBottomScreenText());
            CalculatorLogic.appendMathOperationToContent(operation);
            ScreenInstance.setBottomScreenText("");
            ScreenInstance.updateTopScreenText();
            return;
        };

        if (lastOperation != null && lastOperation.equals(operation)) return;
        if (bottomText.isEmpty()) {
            CalculatorLogic.removeLastOperator();
            CalculatorLogic.appendMathOperationToContent(operation);
        } else if (CalculatorLogic.calculatorContentIsEmpty()) {
            CalculatorLogic.appendDigitToContent(bottomText);
            CalculatorLogic.appendMathOperationToContent(operation);
            ScreenInstance.setBottomScreenText("");
        } else {
            onEqualBtnClick();
            CalculatorLogic.appendMathOperationToContent(operation);
        }

        ScreenInstance.updateTopScreenText();



        /*
        if (!topText.isEmpty()) {
            if (topText.charAt(topText.length() - 1) == arithmeticOperation.charAt(0)) return;
            if (!Character.isDigit(topText.charAt(topText.length() - 1))) {
                //Butu galima su .replace(...) Tačiau, ivyktų konfliktai su plius/minus mygtuku.
                topText = new StringBuilder(topText).deleteCharAt(topText.length() - 1).append(arithmeticOperation).toString();
                ScreenInstance.setTopScreenText(topText);
                return;
            }
        }*/

    }

    private void onEqualBtnClick() {
        if (ScreenInstance.getTopScreenText().contains("=")) return;

        resetWhenError();
        CalculatorLogic.appendDigitToContent(ScreenInstance.getBottomScreenText());
        String answer = CalculatorLogic.performCalculation();
        ScreenInstance.updateTopScreenText();
        ScreenInstance.setBottomScreenText(answer);
    }

    public void initButtons(AppCompatActivity app) {
        List<Button> numpadBtns = List.of(
                app.findViewById(R.id.num0), app.findViewById(R.id.num1), app.findViewById(R.id.num2),
                app.findViewById(R.id.num3), app.findViewById(R.id.num4), app.findViewById(R.id.num5),
                app.findViewById(R.id.num6), app.findViewById(R.id.num7), app.findViewById(R.id.num8),
                app.findViewById(R.id.num9));
        for (int i = 0; i < numpadBtns.size(); i++) {
            int numpadIndex = i;
            numpadBtns.get(i).setOnClickListener((event) -> onNumberKeyPress(numpadIndex));
        }

        app.findViewById(R.id.comma).setOnClickListener((event) -> onKeyPress("comma"));
        app.findViewById(R.id.backspace).setOnClickListener((event) -> onKeyPress("backspace"));
        app.findViewById(R.id.clear).setOnClickListener((event) -> onKeyPress("clear"));
        app.findViewById(R.id.plusMinus).setOnClickListener((event) -> onKeyPress("plusminus"));
        app.findViewById(R.id.clearEverything).setOnClickListener((event) -> onKeyPress("clearEverything"));

        app.findViewById(R.id.equal).setOnClickListener((event) -> onEqualBtnClick());


        List<Button> functionButtons = List.of(app.findViewById(R.id.plus), app.findViewById(R.id.minus),
                app.findViewById(R.id.division), app.findViewById(R.id.sqrt), app.findViewById(R.id.multiply));
        for (Button btn: functionButtons) {
            for (ArithmeticOperation operation: CalculatorLogic.LoadedOperationList) {
                if (!operation.operationSymbol().contentEquals(btn.getTag().toString())) continue;
                btn.setOnClickListener((event) -> onFunctionKeyPress(operation));
            }
        }

    }


    private void resetWhenError() {
        if (!ScreenInstance.getBottomScreenText().equalsIgnoreCase("klaida")) return;
        CalculatorLogic.clearContent();
        ScreenInstance.clearScreens();
        ScreenInstance.updateTopScreenText();
    }
}
