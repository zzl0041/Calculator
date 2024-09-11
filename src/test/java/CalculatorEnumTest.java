import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorEnumTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator(new HashMap<>());
    }

    @Test
    public void testChainAllOperations() {
        Operation[] operations = {
                Operation.ADD,
                Operation.SUBTRACT,
                Operation.MULTIPLY,
                Operation.MOD,
                Operation.DIVIDE,
                Operation.POWER,
                Operation.ROOT
        };
        Number[] numbers = {3, 2, 2, 5, 2, 2, 2};
        assertEquals(1.0, calculator.chain(5, operations, numbers));
    }

    @Test
    public void testChainAllOperationsWithNegativeNumbers() {
        Operation[] operations = {
                Operation.SUBTRACT,
                Operation.MULTIPLY,
                Operation.MOD,
                Operation.DIVIDE,
                Operation.POWER,
                Operation.ROOT
        };
        Number[] numbers = {4, 3, 4, 2, 3, 2};
        assertEquals(1.0, calculator.chain(10, operations, numbers));
    }
}
