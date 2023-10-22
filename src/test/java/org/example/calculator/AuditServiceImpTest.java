package org.example.calculator;

import org.example.calculator.impl.AuditServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class AuditServiceImpTest {

    private record Expectations( int n, List<Operation> expectedOperations){}

    @ParameterizedTest
    @MethodSource
    @Test
    public void testAddAndGetOperations(Expectations expectations) {
        AuditService audit = new AuditServiceImpl();
        audit.addOperation(createOp("1.1", "2.2","3.3"));
        audit.addOperation(createOp("2.1", "3.2", "5.3"));
        audit.addOperation(createOp("3.1", "4.2", "7.3"));
        audit.addOperation(createOp("4.1", "5.2", "9.3"));
        audit.addOperation(createOp("5.1", "6.2", "11.3"));

        Operation[] expectedOps = expectations.expectedOperations.toArray(new Operation[0]);
        assertThat( audit.lastOperations(expectations.n), contains(expectedOps));
    }
    static Stream<Expectations> testAddAndGetOperations(){
        return Stream.of(
                new Expectations(0, List.of()),
                new Expectations(1, List.of(createOp("5.1", "6.2", "11.3"))),
                new Expectations(2, List.of(createOp("5.1", "6.2", "11.3"), createOp("4.1", "5.2", "9.3"))),
                new Expectations(3, List.of(createOp("5.1", "6.2", "11.3"), createOp("4.1", "5.2", "9.3"), createOp("3.1", "4.2", "7.3") )),
                new Expectations(4, List.of(createOp("5.1", "6.2", "11.3"), createOp("4.1", "5.2", "9.3"), createOp("3.1", "4.2", "7.3"), createOp("2.1", "3.2", "5.3") )),
                new Expectations(5, List.of(createOp("5.1", "6.2", "11.3"), createOp("4.1", "5.2", "9.3"), createOp("3.1", "4.2", "7.3"), createOp("2.1", "3.2", "5.3"), createOp("1.1", "2.2","3.3") )),
                new Expectations(10, List.of(createOp("5.1", "6.2", "11.3"), createOp("4.1", "5.2", "9.3"), createOp("3.1", "4.2", "7.3"), createOp("2.1", "3.2", "5.3"), createOp("1.1", "2.2","3.3") )),
                new Expectations(1000, List.of(createOp("5.1", "6.2", "11.3"), createOp("4.1", "5.2", "9.3"), createOp("3.1", "4.2", "7.3"), createOp("2.1", "3.2", "5.3"), createOp("1.1", "2.2","3.3") ))
        );
    }

   private static Operation createOp(String a, String  b, String result) {
        return new Operation(OperationType.ADD, 2, List.of(new BigDecimal(a), new BigDecimal(b)), new BigDecimal(result));
    }

}
