package com.dom.mipt3.calculator;

import android.widget.TextView;

public class CalculatorScreenManagement {

    private final TextView TOP_SCREEN;
    private final TextView BOTTOM_SCREEN;

    public CalculatorScreenManagement(TextView top, TextView bottom) {
        this.TOP_SCREEN = top;
        this.BOTTOM_SCREEN = bottom;
        clearScreens();
    }

    public void updateTopScreenText() {
        TOP_SCREEN.setText(CalculatorLogic.getHumanReadableExpression());
    }

    public void setTopScreenText(String text) {
        TOP_SCREEN.setText(text);
    }

    public void setBottomScreenText(String text) {
        BOTTOM_SCREEN.setText(text);
    }

    public void appendTopScreenText(String text) {
        String newText = TOP_SCREEN.getText() + text;
        TOP_SCREEN.setText(newText);
    }

    public void appendBottomScreenText(String text) {
        String newText = BOTTOM_SCREEN.getText() + text;
        BOTTOM_SCREEN.setText(newText);
    }

    public String getTopScreenText() {
        return TOP_SCREEN.getText().toString();
    }

    public String getBottomScreenText() {
        return BOTTOM_SCREEN.getText().toString();
    }

    public void clearScreens() {
        TOP_SCREEN.setText("");
        BOTTOM_SCREEN.setText("");
        CalculatorLogic.clearContent();
    }

    public void backspace() {
        String text = getBottomScreenText();
        if (text.equalsIgnoreCase("klaida")) return;
        if (text.isEmpty()) return;
        text = new StringBuilder(text).deleteCharAt(text.length()-1).toString();
        setBottomScreenText(text);
    }

    public void changeSign() {
        String text = getBottomScreenText();
        if (text.isEmpty()) return;
        if (text.charAt(0) == '-') text = new StringBuilder(text).deleteCharAt(0).toString();
        else text = new StringBuilder(text).insert(0, '-').toString();
        setBottomScreenText(text);
    }


}
