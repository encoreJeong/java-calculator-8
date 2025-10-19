package calculator;

public class StringExpression {

    private Delimiters delimiter;
    private Operands operands ;

    private StringExpression (String rawInput) {
        delimiter = Delimiters.from(rawInput);
        operands = Operands.of(rawInput, delimiter);
    }

    public static StringExpression valueOf(String rawInput) {
        return new StringExpression(rawInput);
    }

    public Operands getOperands() {
        return operands;
    }
}
