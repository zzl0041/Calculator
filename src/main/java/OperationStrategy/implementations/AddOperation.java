package OperationStrategy.implementations;

import OperationStrategy.OperationStrategy;

public class AddOperation implements OperationStrategy {
    @Override
    public Number perform(Number num1, Number num2){
        return num1.doubleValue() + num2.doubleValue();
    }
}
