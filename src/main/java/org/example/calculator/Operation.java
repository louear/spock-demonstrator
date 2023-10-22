package org.example.calculator;

import java.math.BigDecimal;
import java.util.List;


public record Operation (OperationType type, int numParams, List<BigDecimal> params, BigDecimal results){}
