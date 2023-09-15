package com.dom.mipt3.calculator;

import android.util.Pair;
import com.dom.mipt3.calculator.operations.ArithmeticOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static com.dom.mipt3.calculator.Utils.textToMathExpression;

public class CalculatorLogic {

    public static final List<ArithmeticOperation> LoadedOperationList = new ArrayList<>();

    private static String CalculatorContent = "";

    public CalculatorLogic() {
        ServiceLoader<ArithmeticOperation> providers = ServiceLoader.load(ArithmeticOperation.class);
        for (ArithmeticOperation operation : providers) {
            LoadedOperationList.add(operation);
        }
    }

    public static void appendMathOperationToContent(ArithmeticOperation operation) {
        CalculatorContent += operation.operationName();
    }

    public static void appendDigitToContent(String digit) {
        if (digit.equalsIgnoreCase("klaida")) CalculatorContent = "";
        CalculatorContent += digit;
    }

    public static ArithmeticOperation getLastOperator() {
        return Utils.getLastOperator(CalculatorContent);
    }

    public static void removeLastOperator() {
        ArithmeticOperation lastOperator = getLastOperator();
        if (lastOperator == null) return;
        CalculatorContent = CalculatorContent.replace(lastOperator.operationName(), "");
    }

    public static boolean removeEqualitySymbol() {
        if (CalculatorContent.isEmpty()) return false;
        if (CalculatorContent.charAt(CalculatorContent.length()-1) != '=') return false;
        CalculatorContent = new StringBuilder(CalculatorContent).deleteCharAt(CalculatorContent.length()-1).toString();
        return true;
    }

    public static void clearContent() {
        CalculatorContent = "";
    }

    public static boolean calculatorContentIsEmpty() {
        return CalculatorContent.isEmpty();
    }

    public static String getHumanReadableExpression() {
        return Utils.convertToHumanReadableText(CalculatorContent);
    }

    public static String performCalculation() {
        if (CalculatorContent.isEmpty()) return "Klaida";
        Pair<double[], ArithmeticOperation> parsedExpression;
        try {
            parsedExpression = textToMathExpression(CalculatorContent);
        } catch (IndexOutOfBoundsException ex) {
            CalculatorContent = "";
            return "Klaida";
        }
        double answer = parsedExpression.second.calculate(parsedExpression.first);
        CalculatorContent += "=";
        return String.valueOf(answer);
    }

}
