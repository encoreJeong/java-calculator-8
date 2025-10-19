package calculator;

import java.util.Optional;

public class Delimiter {
    private final Character delimiter;

    private Delimiter(Character delimiter) {
        this.delimiter = delimiter;
    }

    public static Optional<Delimiter> valueOf(String delimiter) {
        validateDelimiter(delimiter);

        if(delimiter.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Delimiter(delimiter.charAt(0)));
    }

    private static void validateDelimiter(String delimiter) {
        if(delimiter.length() > 2) {
            throw new IllegalArgumentException("구분자는 1개의 문자여야 합니다." + delimiter);
        }
    }

    public Character toCharacter() {
        return delimiter;
    }

    public boolean isLetterOrDigit() {
        return Character.isLetterOrDigit(delimiter);
    }
}
