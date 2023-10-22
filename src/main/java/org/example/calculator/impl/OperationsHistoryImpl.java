package org.example.calculator.impl;

import org.example.calculator.Operation;
import org.example.calculator.OperationsHistory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class OperationsHistoryImpl implements OperationsHistory {

    private final Queue<Operation> lastOperations = new ConcurrentLinkedQueue<>();


    @Override
    public List<Operation> lastOperations(int count) {
        return lastOperations.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public void addOperation(Operation o) {
        lastOperations.add(o);
    }
}
