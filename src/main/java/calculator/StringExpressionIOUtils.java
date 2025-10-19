package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class StringExpressionIOUtils {

    private static final String INPUT_HINT = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_HINT = "결과 : ";

    public static String readLineWithHint() {
        System.out.println(INPUT_HINT);

        String input;

        try {
            input = Console.readLine();
        } catch (NoSuchElementException e) {
            input = "";
        }

        return input;
    }

    public static void printWithHint(Object obj) {
        System.out.print(RESULT_HINT + obj);
    }
}
