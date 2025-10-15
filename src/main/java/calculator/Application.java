package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        String input = Console.readLine();

        boolean isInputIncludeHeader = false;

        //입력의 맨 앞부분에 "//"와 "\n" 사이에 커스텀 구분자로 사용할 문자를 입력할 수 있다.
        StringBuilder delimitersRegx = new StringBuilder("[,:");
        if(input.startsWith("//")) {
            isInputIncludeHeader = true;

            char customDelimiter = input.charAt(2);

            //커스텀 구분자가 알파벳이라면 정규식 표현상에서 이스케이프 문자를 앞에 붙이면 안된다.
            if(!Character.isAlphabetic(customDelimiter)) {
                delimitersRegx.append("\\");
            }
            delimitersRegx.append(customDelimiter);
        }
        delimitersRegx.append("]");

        String inputWithoutHeader;

        if(isInputIncludeHeader) {
            inputWithoutHeader = input.split("\\\\n")[1];
        } else {
            inputWithoutHeader = input;
        }

        String[] splitedInput = inputWithoutHeader.split(delimitersRegx.toString());

        int inputSize = splitedInput.length;

        Integer[] parsedInput = new Integer[inputSize];

        try {
            for(int i = 0; i < splitedInput.length; i++) {
                parsedInput[i] = Integer.parseInt(splitedInput[i]);
                if(parsedInput[i] < 0)
                    throw new IllegalArgumentException("입력값은 양수여야 합니다. : " + splitedInput[i]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 입력이 포함 돼있습니다. " + e.getMessage());
        }

        int sum = 0;
        for(int i = 0; i < inputSize; i++) {
            sum += parsedInput[i];
        }

        System.out.print("결과 : " + sum);
    }
}
