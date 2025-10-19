package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Delimiters {

    public static String HEADER_OPEN_TAG = "//";
    public static String HEADER_CLOSE_TAG = "\\\\n";

    private List<Character> delimiters;

    private Delimiters(List<Character> delimiters) {
        this.delimiters = delimiters;
    }

    public static Delimiters from(String rawInput) {

        List<Character> delimiters = new ArrayList<>(Arrays.asList(',',':'));

        //입력의 맨 앞부분에 "//"와 "\n" 사이에 커스텀 구분자로 사용할 문자를 입력할 수 있다.
        if(rawInput.startsWith(HEADER_OPEN_TAG)) {
            if (!rawInput.contains(HEADER_CLOSE_TAG))
                throw new IllegalArgumentException("커스텀 구분자 지정 형식이 잘못 됐습니다." + rawInput);

            String customDelimiter = rawInput.substring(rawInput.indexOf(HEADER_OPEN_TAG) + HEADER_OPEN_TAG.length(), rawInput.indexOf(HEADER_CLOSE_TAG));
            if (customDelimiter.length() > 1)
                throw new IllegalArgumentException("커스텀 구분자 지정 형식이 잘못 됐습니다." + customDelimiter);

            char verifiedCustomDelimiter = customDelimiter.charAt(0);

            delimiters.add(verifiedCustomDelimiter);
        }

        return new Delimiters(delimiters);
    }

    public String toRegExp() {
        StringBuilder delimitersRegx = new StringBuilder("[,:");

        delimiters.forEach(delimiter -> {
            //커스텀 구분자가 알파벳이거나 숫자라면 정규식 표현상에서 이스케이프 문자를 앞에 붙이면 안된다.
            if(!Character.isLetterOrDigit(delimiter) ) {
                delimitersRegx.append("\\");
            }
            delimitersRegx.append(delimiter);
        });
        delimitersRegx.append("]");

        return delimitersRegx.toString();
    }

    public boolean hasCustomDelimiter() {
        return delimiters.size() > 2;
    }
}
