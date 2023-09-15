package com.dom.mipt3.calculator.operations;

import androidx.annotation.Size;
import com.google.auto.service.AutoService;

@AutoService(ArithmeticOperation.class)
public class Minus implements ArithmeticOperation {

    public String operationSymbol() {
        return "-";
    }

    @Override
    public String operationName() { return "{minus}"; }

    public double calculate(@Size(min = 2, max = 2) double... digits) {
        return digits[0] - digits[1];
    }
}
