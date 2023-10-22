package org.example.calculator;

import java.util.List;

public interface AuditService {

    /**
     * returns the last count operations. newest operation is first in the list.
     */
    List<Operation> lastOperations(int count);

    /**
     *
     * @param o
     * @return true if the operation was successful
     */
    boolean addOperation(Operation o);
}
