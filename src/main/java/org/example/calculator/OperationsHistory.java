package org.example.calculator;

import java.util.List;

public interface OperationsHistory {
    List<Operation> lastOperations(int count);

    void addOperation(Operation o);
}
