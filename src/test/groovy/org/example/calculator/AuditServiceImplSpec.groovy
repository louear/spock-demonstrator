package org.example.calculator

import org.example.calculator.impl.AuditServiceImpl
import spock.lang.Specification


class AuditServiceImplSpec extends Specification {
    def 'Audit service allows retrieval of the last #n saved operations'() {
        given:
        AuditService audit = new AuditServiceImpl()
        audit.addOperation(createOp(1.1, 2.2, 3.3))
        audit.addOperation(createOp(2.1, 3.2, 5.3))
        audit.addOperation(createOp(3.1, 4.2, 7.3))
        audit.addOperation(createOp(4.1, 5.2, 9.3))
        audit.addOperation(createOp(5.1, 6.2, 11.3))

        when:
        def result = audit.lastOperations(n)
        then:
        result == expected

        where:
        n    | expected
        0    | []      // empty list
        1    | [createOp(5.1, 6.2, 11.3)]
        2    | [createOp(5.1, 6.2, 11.3), createOp(4.1, 5.2, 9.3)]
        3    | [createOp(5.1, 6.2, 11.3), createOp(4.1, 5.2, 9.3), createOp(3.1, 4.2, 7.3)]
        4    | [createOp(5.1, 6.2, 11.3), createOp(4.1, 5.2, 9.3), createOp(3.1, 4.2, 7.3), createOp(2.1, 3.2, 5.3)]
        5    | [createOp(5.1, 6.2, 11.3), createOp(4.1, 5.2, 9.3), createOp(3.1, 4.2, 7.3), createOp(2.1, 3.2, 5.3), createOp(1.1, 2.2, 3.3)]
        6    | [createOp(5.1, 6.2, 11.3), createOp(4.1, 5.2, 9.3), createOp(3.1, 4.2, 7.3), createOp(2.1, 3.2, 5.3), createOp(1.1, 2.2, 3.3)]
        1000 | [createOp(5.1, 6.2, 11.3), createOp(4.1, 5.2, 9.3), createOp(3.1, 4.2, 7.3), createOp(2.1, 3.2, 5.3), createOp(1.1, 2.2, 3.3)]
    }

    private Operation createOp(BigDecimal a, BigDecimal b, BigDecimal result) {
        new Operation(OperationType.ADD, 2, [a, b], result)
    }

}