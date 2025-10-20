package calculator;

public class Operand {

    private final Integer operand;

    private Operand(Integer operand) {
        this.operand = operand;
    }

    public static Operand valueOf(String value) {
        if (value.isEmpty()) {
            return new Operand(0);
        }

        validateOperand(value);

        return new Operand(Integer.parseInt(value));
    }

    private static void validateOperand(String value) {
        int parsedValue = parseToPositiveInt(value);
        if (parsedValue < 0) {
            throw new IllegalArgumentException("입력값은 양수여야 합니다. : " + value);
        }
    }

    private static int parseToPositiveInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 입력이 포함돼 있습니다. : " + value);
        }
    }

    public Integer toInteger() {
        return operand;
    }
}
