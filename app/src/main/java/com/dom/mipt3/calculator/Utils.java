package com.dom.mipt3.calculator;

import android.util.Log;
import android.util.Pair;
import com.dom.mipt3.calculator.operations.ArithmeticOperation;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Pair<double[], ArithmeticOperation> textToMathExpression(String text) throws IndexOutOfBoundsException {
        for (ArithmeticOperation operation : CalculatorLogic.LoadedOperationList) {
            if (!text.contains(operation.operationName())) continue;
            String regex = Pattern.quote(operation.operationName());
            String[] splitExpression = text.split(regex);

            double[] operands = new double[operation.numCount()];
            for (int i = 0; i < operation.numCount(); i++) {
                operands[i] = Double.parseDouble(splitExpression[i]);
            }

            return new Pair<>(operands, operation);
        }
        return null;
    }

    public static String convertToHumanReadableText(String expression) {
        for (ArithmeticOperation operation : CalculatorLogic.LoadedOperationList) {
            String regex = Pattern.quote(operation.operationName());
            expression = expression.replaceAll(regex, operation.operationSymbol());
        }
        Log.w("Parser", expression);
        return expression;
    }

    public static ArithmeticOperation getLastOperator(String fullExpression) {
        if (fullExpression.isEmpty()) return null;
        Matcher parts = Pattern.compile("(\\{(?:[^\\{\\}]{1,99})\\}+)\\s*(\\d*(?:\\.\\d+)?)").matcher(fullExpression);
        parts.find();
        if (!parts.group(2).isEmpty()) return null;

        for (ArithmeticOperation operation : CalculatorLogic.LoadedOperationList) {
            if (parts.group(1).contentEquals(operation.operationName())) return operation;
        }
        return null;
    }


    public static void removeLastOperator(String fullExpression) {
        ArithmeticOperation lastOperator = getLastOperator(fullExpression);
        if (lastOperator == null) return;

    }


    /**
     * @param expression A string expression from user;
     * @return A pair with arithmetic operation class and their operands;
     * @throws ClassNotFoundException When math operation is not found.
     */
    public static Pair<double[], ArithmeticOperation> splitExpressionToObjects(String expression) throws ClassNotFoundException, InvalidParameterException {

        Matcher parts = Pattern.compile("^(\\d+(?:\\.\\d+)?)\\s*(\\{(?:[^\\{\\}]{1,99})\\}+)?\\s*(\\d*(?:\\.\\d+)?)").matcher(expression);
        parts.find();
        if (parts.group(2) == null) throw new InvalidParameterException();
        for (ArithmeticOperation operation : CalculatorLogic.LoadedOperationList) {
            if (!parts.group(2).equals(operation.operationName().replaceAll("\\\\", ""))) continue;
            try {
                double[] operands = new double[2];
                operands[0] = Double.parseDouble(parts.group(1));

                if (operation.numCount() > 1) {
                    if (parts.group(3) == null) throw new InvalidParameterException();
                    operands[1] = Double.parseDouble(parts.group(3));
                }

                return new Pair<>(operands, operation);

            } catch (NumberFormatException e) {
                return null;
            }
        }
        throw new ClassNotFoundException();
    }

}
