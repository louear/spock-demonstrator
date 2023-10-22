package org.example.calculator.impl;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.example.calculator.Calculator;
import org.example.calculator.Operation;
import org.example.calculator.OperationType;
import org.example.calculator.OperationsHistory;

import java.math.BigDecimal;
import java.math.MathContext;

@RequiredArgsConstructor
public class CalculatorImpl implements Calculator {

    private final OperationsHistory operationsHistory;

    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.add(b);
        operationsHistory.addOperation(new Operation(OperationType.ADD,2, ImmutableList.of(a,b), result));
        return result;
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.subtract(b);
        operationsHistory.addOperation(new Operation(OperationType.SUBTRACT,2, ImmutableList.of(a,b), result));
        return result;

    }

    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.multiply(b);
        operationsHistory.addOperation(new Operation(OperationType.MULTIPLY,2, ImmutableList.of(a,b), result));
        return result;

    }

    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.divide(b, MathContext.DECIMAL64);
        operationsHistory.addOperation(new Operation(OperationType.DIVIDE,2, ImmutableList.of(a,b), result));
        return result;
    }
}
