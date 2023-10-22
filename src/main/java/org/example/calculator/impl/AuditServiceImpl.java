package org.example.calculator.impl;

import org.example.calculator.AuditService;
import org.example.calculator.Operation;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AuditServiceImpl implements AuditService {
    private final Deque<Operation> lastOperations = new ConcurrentLinkedDeque<>();


    @Override
    public List<Operation> lastOperations(int count) {
        Iterable<Operation> iterable = lastOperations::descendingIterator;
        Stream<Operation> s = StreamSupport.stream(iterable.spliterator(), false);
        return s.limit(count).collect(Collectors.toList());
    }

    @Override
    public void addOperation(Operation o) {
        lastOperations.add(o);
    }
}
