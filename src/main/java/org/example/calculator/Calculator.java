package org.example.calculator;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal add( BigDecimal a, BigDecimal b);
    BigDecimal subtract(BigDecimal a, BigDecimal b);
    BigDecimal multiply( BigDecimal a, BigDecimal b);
    BigDecimal divide( BigDecimal a, BigDecimal b);

}
