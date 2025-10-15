package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        String input = Console.readLine();

        String[] splitedInput = input.split("[,:]");

        int inputSize = splitedInput.length;

        Integer[] parsedInput = new Integer[inputSize];

        for(int i = 0; i < splitedInput.length; i++) {
            parsedInput[i] = Integer.parseInt(splitedInput[i]);
        }

        int sum = 0;
        for(int i = 0; i < inputSize; i++) {
            sum += parsedInput[i];
        }

        System.out.print("결과 : " + sum);
    }
}
