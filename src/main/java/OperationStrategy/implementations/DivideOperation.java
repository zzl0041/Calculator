package OperationStrategy.implementations;

import OperationStrategy.OperationStrategy;

public class DivideOperation implements OperationStrategy {
    @Override
    public Number perform(Number num1, Number num2){
        if(num2.doubleValue() == 0) throw new ArithmeticException("Cannot divide by zero");
        return num1.doubleValue() / num2.doubleValue();
    }
}
