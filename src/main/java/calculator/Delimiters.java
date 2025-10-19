package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Delimiters {

    public static final String EMPTY = "";
    public static final String ESCAPE = "\\";
    public static final String HEADER_OPEN_TAG = "//";
    public static final String HEADER_CLOSE_TAG = "\\n";
    private static final List<Delimiter> DEFAULT_DELIMITERS = Arrays.asList(
                                                                Delimiter.valueOf(",").get(),
                                                                Delimiter.valueOf(":").get());

    private final List<Delimiter> delimiters;

    private Delimiters(List<Delimiter> delimiters) {
        this.delimiters = delimiters;
    }

    public static Delimiters from(String rawInput) {

        List<Delimiter> delimiters = new ArrayList<>(DEFAULT_DELIMITERS);

        getCustomDelimiter(rawInput).ifPresent(delimiters::add);

        return new Delimiters(delimiters);
    }

    private static Optional<Delimiter> getCustomDelimiter(String rawInput) {
        String delimiter = extractContentBetweenHeaderTags(rawInput);
        return Delimiter.valueOf(delimiter);
    }

    private static String extractContentBetweenHeaderTags(String rawInput) {
        validateHeaderFormat(rawInput);

        if(rawInput.startsWith(HEADER_OPEN_TAG)) {
            return rawInput.substring(rawInput.indexOf(HEADER_OPEN_TAG) + HEADER_OPEN_TAG.length(), rawInput.indexOf(HEADER_CLOSE_TAG));
        }

        return EMPTY;
    }

    public String toRegExp() {
        StringBuilder delimitersRegx = new StringBuilder("[");

        delimiters.forEach(delimiter -> {
            //커스텀 구분자가 알파벳이거나 숫자라면 정규식 표현상에서 이스케이프 문자를 앞에 붙이면 안된다.
            if(!delimiter.isLetterOrDigit()) {
                delimitersRegx.append(ESCAPE);
            }
            delimitersRegx.append(delimiter.toCharacter());
        });
        delimitersRegx.append("]");

        return delimitersRegx.toString();
    }

    public boolean hasCustomDelimiter() {
        return delimiters.size() > DEFAULT_DELIMITERS.size();
    }

    private static void validateHeaderFormat(String rawInput) {
        if(isContainsHeaderOpenTag(rawInput)) {
            validateHeaderContainsCloseTag(rawInput);
        }
    }

    private static boolean isContainsHeaderOpenTag(String rawInput) {
        return rawInput.startsWith(HEADER_OPEN_TAG);
    }

    private static void validateHeaderContainsCloseTag(String rawInput) {
        if (!rawInput.contains(HEADER_CLOSE_TAG))
            throw new IllegalArgumentException("커스텀 구분자 지정 형식이 잘못 됐습니다." + rawInput);
    }
}
