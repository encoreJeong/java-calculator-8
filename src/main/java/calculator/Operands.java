package calculator;

import java.util.ArrayList;
import java.util.List;

public class Operands {

    private List<Integer> operands;

    private Operands(List<Integer> operands) {
        this.operands = operands;
    }

    public static Operands of(String rawInput, Delimiters delimiters) {
        String inputWithoutHeader;

        if(delimiters.hasCustomDelimiter()) {
            inputWithoutHeader = rawInput.split(delimiters.HEADER_CLOSE_TAG)[1];
        } else {
            inputWithoutHeader = rawInput;
        }

        String[] splitedInput = inputWithoutHeader.split(delimiters.toRegExp());

        List<Integer> operands = new ArrayList<>();

        try {
            for(int i = 0; i < splitedInput.length; i++) {
                if(splitedInput[i].isEmpty()) {splitedInput[i] = "0";}

                Integer parsedInput = Integer.parseInt(splitedInput[i]);
                if(parsedInput < 0)
                    throw new IllegalArgumentException("입력값은 양수여야 합니다. : " + splitedInput[i]);

                operands.add(parsedInput);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 입력이 포함 돼있습니다. " + e.getMessage());
        }

        return new Operands(operands);
    }

    public Integer count() {
        return operands.size();
    }

    public Integer sum() {
        return operands.stream().mapToInt(Integer::intValue).sum();
    }

}
