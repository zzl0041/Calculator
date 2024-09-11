import OperationStrategy.OperationStrategy;

import java.util.Map;

public class Calculator {
    //basic implementation for requirement 1, 2, 3, 6
    public Number calculate(basicOperations op, Number num1, Number num2){
        switch (op){
            case ADD:
                return num1.doubleValue() + num2.doubleValue();
            case SUBTRACT:
                return num1.doubleValue() - num2.doubleValue();
            case MULTIPLY:
                return num1.doubleValue() * num2.doubleValue();
            case DIVIDE:
                if(num2.doubleValue() == 0)
                    throw  new ArithmeticException("Cannot divide by zero");
                return num1.doubleValue() / num2.doubleValue();
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }
    }

    public Number chain(Number initial, basicOperations[] operations, Number[] numbers){
        if(operations.length!= numbers.length)
            throw new IllegalArgumentException("Operations and numbers size mismatch");
        Number result = initial;
        for(int i = 0; i < operations.length; i++)
            result = calculate(operations[i], result, numbers[i]);
        return result;
    }

    //Implementation for requirements 4, 5
    private final Map<String, OperationStrategy> operationMap;
    // Constructor Injection
    // Framework like Spring or Guice also helps Inversion of Control
    public Calculator(Map<String, OperationStrategy> operationMap){
        this.operationMap = operationMap;
    }
    public Number calculate(String operation, Number num1, Number num2){
        OperationStrategy strategy = operationMap.get(operation);
        if(strategy == null) throw new UnsupportedOperationException("Operation not supported: " + operation);
        return strategy.perform(num1, num2);
    }

    public Number chain(Number initial, String[] operations, Number[] numbers){
        if(operations.length != numbers.length)
            throw new IllegalArgumentException("Operations and numbers size mismatch");
        Number result = initial;
        for(int i = 0; i < operations.length; i++)
            result = calculate(operations[i], result, numbers[i]);
        return result;
    }

    //Ultimated solution for all clarification requirements
    public Number calculate(Operation op, Number num1, Number num2) {
        return op.getStrategy().perform(num1, num2);
    }

    public Number chain(Number initialValue, Operation[] operations, Number[] numbers) {
        if(operations.length!= numbers.length)
            throw new IllegalArgumentException("Operations and numbers size mismatch");
        Number result = initialValue;
        for (int i = 0; i < operations.length; i++)
            result = calculate(operations[i], result, numbers[i]);
        return result;
    }
}
