import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import OperationStrategy.OperationStrategy;
import OperationStrategy.implementations.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorMapTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        Map<String, OperationStrategy> operationsMap = new HashMap<>();
        operationsMap.put("ADD", new AddOperation());
        operationsMap.put("SUBTRACT", new SubtractOperation());
        operationsMap.put("MULTIPLY", new MultiplyOperation());
        operationsMap.put("DIVIDE", new DivideOperation());
        operationsMap.put("MOD", new ModuloOperation());
        operationsMap.put("POWER", new PowerOperation());
        operationsMap.put("ROOT", new RootOperation());

        calculator = new Calculator(operationsMap);
    }

    @Test
    public void testChainAllOperations() {
        String[] operations = {
                "ADD",
                "SUBTRACT",
                "MULTIPLY",
                "MOD",
                "DIVIDE",
                "POWER",
                "ROOT"
        };
        Number[] numbers = {3, 2, 2, 5, 2, 2, 2};
        assertEquals(1.0, calculator.chain(5, operations, numbers));
    }

    @Test
    public void testChainAllOperationsWithNegativeNumbers() {
        String[] operations = {
                "SUBTRACT",
                "MULTIPLY",
                "MOD",
                "DIVIDE",
                "POWER",
                "ROOT"
        };
        Number[] numbers = {4, 3, 4, 2, 3, 2};
        assertEquals(1.0, calculator.chain(10, operations, numbers));
    }
}
