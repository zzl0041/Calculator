import OperationStrategy.OperationStrategy;
import OperationStrategy.implementations.*;

public enum Operation {
    ADD(new AddOperation()),
    SUBTRACT(new SubtractOperation()),
    MULTIPLY(new MultiplyOperation()),
    DIVIDE(new DivideOperation()),
    MOD(new ModuloOperation()),
    POWER(new PowerOperation()),
    ROOT(new RootOperation());

    private final OperationStrategy strategy;

    Operation(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public OperationStrategy getStrategy() {
        return strategy;
    }
}
