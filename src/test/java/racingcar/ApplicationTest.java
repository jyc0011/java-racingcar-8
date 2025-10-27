package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/**
 * {@code Application} 테스트
 * <p> 예상 시나리오 검증
 */
class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;
    /**
     * 단독 우승 검증
     */
    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }
    /**
     * 자동차 이름이 5자를 초과 오류 검증
     */
    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    /**
     * 여러 차 공동 우승 시나리오 검증
     */
    @DisplayName("성공: 공동 우승 시나리오")
    @Test
    void success_JointWinners() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "1");
                    assertThat(output()).contains(
                            "pobi : -",
                            "woni : ",
                            "jun : -",
                            "최종 우승자 : pobi, jun"
                    );
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD
        );
    }

    /**
     * 위치 누적 및 턴별 결과 출력 검증
     */
    @DisplayName("성공: 턴 진행 시나리오")
    @Test
    void success_MultipleTurns() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "2");
                    assertThat(output()).contains(
                            "pobi : -",
                            "woni : ",
                            "pobi : -",
                            "woni : -",
                            "최종 우승자 : pobi, woni"
                    );
                },
                MOVING_FORWARD, STOP,
                STOP, MOVING_FORWARD
        );
    }

    /**
     * 자동차 이름 중복 예외 검증
     */
    @DisplayName("오류: 자동차 이름 중복")
    @Test
    void exception_WhenNameDuplicated() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni,pobi", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /**
     * 자동차 이름 빈 값(,,), 공백 오류 검증
     */
    @DisplayName("오류: 자동차 이름 빈 값(,,) 또는 공백")
    @Test
    void exception_WhenNameBlank() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi, ,woni", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /**
     * 시도 횟수가 숫자 아닌 경우 검증
     */
    @DisplayName("오류: 시도 횟수가 숫자가 아님")
    @Test
    void exception_WhenTryCountNotNumber() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", "abc"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /**
     * 시도 횟수가 1 미만(0)일 경우 오류 발생
     */
    @DisplayName("오류: 시도 횟수가 1 미만 (0)")
    @Test
    void exception_WhenTryCountIsZero() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", "0"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
