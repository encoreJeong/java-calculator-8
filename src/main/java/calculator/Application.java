package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        String input = Console.readLine();

        String[] splitedInput = input.split("[,:]");

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
