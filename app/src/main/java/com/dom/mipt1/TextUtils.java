package com.dom.mipt1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    private final String Text;

    public TextUtils(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException();
        Text = text;
    }

    public int countWords() {
        Matcher matcher = Pattern.compile("\\w+").matcher(Text);

        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    public int countSymbols() {
        return Text.length();
    }

}
