package calculator;

public class Application {

    public static void main(String[] args) {

        String input = StringExpressionIOUtils.readLineWithHint();

        StringExpression stringExpression = StringExpression.valueOf(input);
        Calculator calculator = new Calculator();

        StringExpressionIOUtils.printWithHint(calculator.calculate(stringExpression));
    }
}
