package OperationStrategy.implementations;

import OperationStrategy.OperationStrategy;

public class PowerOperation implements OperationStrategy {
    @Override
    public Number perform(Number num1, Number num2){
        return Math.pow(num1.doubleValue(), num2.doubleValue());
    }
}
