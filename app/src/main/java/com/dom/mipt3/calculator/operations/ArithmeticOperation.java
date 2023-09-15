package com.dom.mipt3.calculator.operations;

public interface ArithmeticOperation {

    public String operationSymbol();

    public String operationName();

    public default int numCount() {
        return 2;
    }

    public double calculate(double... digits);

}
