package calculator;

public class Calculator {

    public Integer calculate(StringExpression expression) {
        Operands operands = expression.getOperands();
        return operands.sum();
    }
}
