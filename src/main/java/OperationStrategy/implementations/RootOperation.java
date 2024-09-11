package OperationStrategy.implementations;

import OperationStrategy.OperationStrategy;

public class RootOperation implements OperationStrategy {
    @Override
    public Number perform(Number num1, Number num2){
        if(num1.doubleValue() < 0 && num2.doubleValue() % 2 != 1)
            throw new ArithmeticException("Cannot compute even root of a negative number");
        return Math.pow(num1.doubleValue(), 1.0/num2.doubleValue());
    }
}
