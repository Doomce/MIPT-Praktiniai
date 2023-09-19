package com.dom.mipt3.calculator.operations;

import androidx.annotation.Size;
import com.google.auto.service.AutoService;

@AutoService(ArithmeticOperation.class)
public class SquareRoot implements ArithmeticOperation {

    public String operationSymbol() {
        return "√";
    }

    @Override
    public String operationName() { return "{sqrt}"; }

    @Override
    public int numCount() {
        return 1;
    }

    public double calculate(@Size(min = 1, max = 1) double... digits) throws ArithmeticException {
        if (digits[0] <= 0) throw new ArithmeticException();
        return Math.sqrt(digits[0]);
    }
}
