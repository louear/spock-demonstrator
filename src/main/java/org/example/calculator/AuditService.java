package org.example.calculator;

import java.util.List;

public interface AuditService {

    /**
     * returns the last count operations. newest operation is first in the list.
     */
    List<Operation> lastOperations(int count);

    void addOperation(Operation o);
}
