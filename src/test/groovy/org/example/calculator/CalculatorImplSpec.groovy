package org.example.calculator

import org.example.calculator.impl.AuditServiceImpl
import org.example.calculator.impl.CalculatorImpl
import spock.lang.Specification
import spock.lang.Timeout
import spock.lang.Unroll

class CalculatorImplSpec extends Specification {

    def calculator

    def setup() {
        calculator = new CalculatorImpl(new AuditServiceImpl())
    }


    // human readable test method names
    @Unroll
    def 'Adding #a to #b should return #expectedSum'() {    // method name used as a template with test params used
//        given: 'a calculator'
//        def calculator = createCalculator(new OperationsHistoryImpl())     // semicolumns optional

//        when: 'I add two numbers'
//        def result = calculator.add(a, b)
//
//        then:
//        result == expectedSum

        expect:
        calculator.add(a, b) == expectedSum             // java Object.equals()

        where:
        // paramaterised tests
        a   | b || expectedSum
        1.0 | 2 || 3.0
        -1  | 1 || 0

// example error message in logs:
//
//        Condition not satisfied:
//
//        calculator.add(a, b) == expectedSum
//                |          |   |  |  |  |
//                |          3   1  2  |  4
//                |                    false
//                <org.example.calculator.impl.CalculatorImpl@424ebba3 operationsHistory=org.example.calculator.impl.OperationsHistoryImpl@4d722ac9>
    }

    def 'adding two numbers adds saves the operation in the AuditService'() {
        given:
        def history = Mock(AuditService)       // creates a mock implementation for interface OperationsHistory
        def calculator = new CalculatorImpl(history)
        
        when:
        def result = calculator.add(1.5, 0.5)
        then:
        result == 2.0
        // verify that interactions with mocks:
        // check that calculation has been added to history

//        1* history.addOperation(_)

        // when failure, good error reporting in the test logs
        1 * history.addOperation(new Operation(OperationType.ADD, 2, [1.5, 0.5] /* a list */, 2.0)) >> true     // contains both stubbed behaviour (returns true) Mocking ( check number of calls + call params)
        // cardinality range
//        (0..1)* history.addOperation(new Operation(OperationType.ADD, 2, [1.5,0.5], 2.0))

        // asserts mock method call  with a predicate on call  parameter
/*
        1 * history.addOperation({
            it.numParams == 2 &&
                    it.params == [1.5, 0.5] &&      // as in java .equals
                    it.type == OperationType.ADD
        })
*/

/*
        1 * history.addOperation({
            verifyAll (it, Operation) {
                numParams == 2
                params == [1.5, 0.5]
                type == OperationType.ADD
            }
        })
*/


        // assert on any method call:
//        1 * history._

        // can use hamcrest to match arguments too...


    }

    def 'adding two numbers adds an entry in the operations history (with real implementation)'() {

    }


    @Timeout(2)
    def 'test finishes within a second'() {

        when:
        Thread.sleep(1200)

        then:
        true
    }

    // example: expecting an exception to be thrown
    def 'dividing by zero throws an exception'() {
        when:
        calculator.divide(123, 0)
        then:
        def e = thrown(ArithmeticException)
        e.message == "Division by zero"
    }


    def 'Mocks with stubbed behaviour'() {
        given:
        def history = Mock(AuditService)       // creates a mock implementation for interface OperationsHistory
//        history.addOperation(_) >> true     // stubbed behaviour for the addOperation method
        history.addOperation(_) >>> [true, false]     // stubbed behaviour for the addOperation method with multiple calls
        def calculator = new CalculatorImpl(history)

        when:
        def result = calculator.add(1.5, 0.5)
        then:
        result == 2.0

        when:
        calculator.add(1.5, 0.5)
        then:
        def e = thrown(RuntimeException)
        e.message == "Failed to save operation with audit service"


    }
}