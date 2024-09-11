import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorBasicTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator(new HashMap<>());
    }

    @Test
    public void testChainAllOperations() {
        basicOperations[] operations = {
                basicOperations.ADD,
                basicOperations.SUBTRACT,
                basicOperations.MULTIPLY,
                basicOperations.DIVIDE,
        };
        Number[] numbers = {3, 2, 2, 3};
        assertEquals(4.0, calculator.chain(5, operations, numbers));
    }

    @Test
    public void testChainAllOperationsWithNegativeNumbers() {
        basicOperations[] operations = {
                basicOperations.SUBTRACT,
                basicOperations.MULTIPLY,
                basicOperations.DIVIDE,
        };
        Number[] numbers = {50, 3, 4};
        assertEquals(-30.0, calculator.chain(10, operations, numbers));
    }
}
