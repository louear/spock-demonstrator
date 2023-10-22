package org.example.calculator

import org.example.calculator.impl.AuditServiceImpl
import spock.lang.Specification


class AuditServiceImplSpec extends Specification {
    def 'Audit service allows retrieval of the last #n saved operations'() {
        given:
        AuditService audit = new AuditServiceImpl()
        audit.addOperation(addOp(1.1, 2.2, 3.3))
        audit.addOperation(addOp(2.1, 3.2, 5.3))
        audit.addOperation(addOp(3.1, 4.2, 7.3))
        audit.addOperation(addOp(4.1, 5.2, 9.3))
        audit.addOperation(addOp(5.1, 6.2, 11.3))

        when:
        def result = audit.lastOperations(n)
        then:
        result == expected

        where:
        n    | expected
        0    | []      // empty list
        1    | [addOp(5.1, 6.2, 11.3)]
        2    | [addOp(5.1, 6.2, 11.3), addOp(4.1, 5.2, 9.3)]
        3    | [addOp(5.1, 6.2, 11.3), addOp(4.1, 5.2, 9.3), addOp(3.1, 4.2, 7.3)]
        4    | [addOp(5.1, 6.2, 11.3), addOp(4.1, 5.2, 9.3), addOp(3.1, 4.2, 7.3), addOp(2.1, 3.2, 5.3)]
        5    | [addOp(5.1, 6.2, 11.3), addOp(4.1, 5.2, 9.3), addOp(3.1, 4.2, 7.3), addOp(2.1, 3.2, 5.3), addOp(1.1, 2.2, 3.3)]
        6    | [addOp(5.1, 6.2, 11.3), addOp(4.1, 5.2, 9.3), addOp(3.1, 4.2, 7.3), addOp(2.1, 3.2, 5.3), addOp(1.1, 2.2, 3.3)]
        1000 | [addOp(5.1, 6.2, 11.3), addOp(4.1, 5.2, 9.3), addOp(3.1, 4.2, 7.3), addOp(2.1, 3.2, 5.3), addOp(1.1, 2.2, 3.3)]
    }

    private Operation addOp(BigDecimal a, BigDecimal b, BigDecimal result) {
        new Operation(OperationType.ADD, 2, [a, b], result)
    }

}