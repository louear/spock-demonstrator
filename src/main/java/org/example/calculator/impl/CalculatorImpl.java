package org.example.calculator.impl;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.example.calculator.AuditService;
import org.example.calculator.Calculator;
import org.example.calculator.Operation;
import org.example.calculator.OperationType;

import java.math.BigDecimal;
import java.math.MathContext;

@RequiredArgsConstructor
public class CalculatorImpl implements Calculator {

    private final AuditService auditService;

    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.add(b);
        auditService.addOperation(new Operation(OperationType.ADD,2, ImmutableList.of(a,b), result));
        return result;
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.subtract(b);
        auditService.addOperation(new Operation(OperationType.SUBTRACT,2, ImmutableList.of(a,b), result));
        return result;

    }

    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.multiply(b);
        auditService.addOperation(new Operation(OperationType.MULTIPLY,2, ImmutableList.of(a,b), result));
        return result;

    }

    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.divide(b, MathContext.DECIMAL64);
        auditService.addOperation(new Operation(OperationType.DIVIDE,2, ImmutableList.of(a,b), result));
        return result;
    }
}
