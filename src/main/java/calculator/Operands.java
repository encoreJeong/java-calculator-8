package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operands {

    private final List<Operand> operands;

    private Operands(List<Operand> operands) {
        this.operands = operands;
    }

    public static Operands of(String rawInput, Delimiters delimiters) {
        String inputWithoutHeader = getInputWithoutHeader(rawInput);

        String[] splitedInput = splitWithDelimiters(inputWithoutHeader, delimiters);

        List<Operand> operands = mapToOperands(splitedInput);

        return new Operands(operands);
    }

    private static List<Operand> mapToOperands(String[] splitedInput) {
        List<Operand> operands = new ArrayList<>();

        Arrays.stream(splitedInput).forEach(input ->
                operands.add(Operand.valueOf(input)));

        return operands;
    }

    private static String[] splitWithDelimiters(String inputWithoutHeader, Delimiters delimiters) {
        return inputWithoutHeader.split(delimiters.toRegExp());
    }

    private static String getInputWithoutHeader(String rawInput) {
        String inputWithoutHeader;

        if (hasHeader(rawInput)) {
            //substring의 인자로 가능한 최대 크기는 해당 문자열의 길이이다. 이 경우, "" 빈 문자열이 리턴된다.
            inputWithoutHeader = rawInput.substring(getFirstIdxOfOperandPart(rawInput));
        } else {
            inputWithoutHeader = rawInput;
        }
        return inputWithoutHeader;
    }

    private static int getFirstIdxOfOperandPart(String rawInput) {
        return rawInput.indexOf(Delimiters.HEADER_CLOSE_TAG) + Delimiters.HEADER_CLOSE_TAG.length();
    }

    private static boolean hasHeader(String rawInput) {
        return rawInput.contains(Delimiters.HEADER_CLOSE_TAG);
    }

    public Integer count() {
        return operands.size();
    }

    public Integer sum() {
        return operands.stream().mapToInt(Operand::toInteger).sum();
    }

}
