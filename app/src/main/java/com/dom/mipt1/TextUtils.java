package com.dom.mipt1;

public class TextUtils {

    private final String Text;

    public TextUtils(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException();
        Text = text;
    }

    public int countWords() {
        return Text.split("\\s+").length;
    }

    public int countSymbols() {
        return Text.length();
    }

}
