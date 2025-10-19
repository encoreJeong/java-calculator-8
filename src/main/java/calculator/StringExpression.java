package calculator;

public class StringExpression {

    private final Delimiters delimiters;
    private final Operands operands;

    private StringExpression(String rawInput) {
        delimiters = Delimiters.from(rawInput);
        operands = Operands.of(rawInput, delimiters);
    }

    public static StringExpression valueOf(String rawInput) {
        return new StringExpression(rawInput);
    }

    public Operands getOperands() {
        return operands;
    }
}
