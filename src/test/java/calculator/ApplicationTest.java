package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 기본기능_성공() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본기능_두자리숫자_성공() {
        assertSimpleTest(() -> {
            run("10,20:30");
            assertThat(output()).contains("결과 : 60");
        });
    }

    @Test
    void 기본기능_구분자사이가_비어있는경우_성공() {
        assertSimpleTest(() -> {
            run("1,,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본기능_구분자가_먼저_등장_성공() {
        assertSimpleTest(() -> {
            run(",1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본기능_구분자가_마지막에_등장_성공() {
        assertSimpleTest(() -> {
            run("1,2,3,");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 커스텀_구분자_알파벳_사용() {
        assertSimpleTest(() -> {
            run("//a\\n1a2a3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_특수문자_사용() {
        assertSimpleTest(() -> {
            run("//.\\n1.2.3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_숫자_사용() {
        assertSimpleTest(() -> {
            run("//1\\n213");
            assertThat(output()).contains("결과 : 5");
        });
    }

    @Test
    void 음수입력_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test()
    void 커스텀구분자_2개이상_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//abc\\n1a2a3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test()
    void 커스텀구분자_형식_이상_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//abc1a2a3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
