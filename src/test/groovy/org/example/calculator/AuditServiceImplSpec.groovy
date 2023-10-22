package org.example.calculator

import org.example.calculator.impl.AuditServiceImpl
import spock.lang.Specification


class AuditServiceImplSpec extends Specification {
    def 'Audit service allows retrieval of the last #n saved operations'() {
        given:
        AuditService audit = new AuditServiceImpl()
        audit.addOperation(new Operation(OperationType.ADD, 2, [1.1, 2.2], 3.3))
        audit.addOperation(new Operation(OperationType.ADD, 2, [2.1, 3.2], 5.3))
        audit.addOperation(new Operation(OperationType.ADD, 2, [3.1, 4.2], 7.3))
        audit.addOperation(new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3))
        audit.addOperation(new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3))


        when:
        def result = audit.lastOperations(n)
        then:
        result == expected

        where:
        n    | expected
        0    | []      // empty list
        1    | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3)]
        2    | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3), new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3)]
        3    | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3), new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3), new Operation(OperationType.ADD, 2, [3.1, 4.2], 7.3)]
        4    | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3), new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3), new Operation(OperationType.ADD, 2, [3.1, 4.2], 7.3), new Operation(OperationType.ADD, 2, [2.1, 3.2], 5.3)]
        5    | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3), new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3), new Operation(OperationType.ADD, 2, [3.1, 4.2], 7.3), new Operation(OperationType.ADD, 2, [2.1, 3.2], 5.3), new Operation(OperationType.ADD, 2, [1.1, 2.2], 3.3)]
        6    | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3), new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3), new Operation(OperationType.ADD, 2, [3.1, 4.2], 7.3), new Operation(OperationType.ADD, 2, [2.1, 3.2], 5.3), new Operation(OperationType.ADD, 2, [1.1, 2.2], 3.3)]
        1000 | [new Operation(OperationType.ADD, 2, [5.1, 6.2], 11.3), new Operation(OperationType.ADD, 2, [4.1, 5.2], 9.3), new Operation(OperationType.ADD, 2, [3.1, 4.2], 7.3), new Operation(OperationType.ADD, 2, [2.1, 3.2], 5.3), new Operation(OperationType.ADD, 2, [1.1, 2.2], 3.3)]

    }

}