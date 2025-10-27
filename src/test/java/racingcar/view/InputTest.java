package racingcar.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * {@code Input} 테스트
 *
 * <p>{@code NsTest}를 상속해 {@code Console.readLine()} 입력과 {@code System.out} 검증
 */
@DisplayName("입력(Input) 테스트")
class InputTest extends NsTest {
    private static String testMethod;
    private static String returnValue;

    /**
     * {@code readCarNames} 메시지 출력, 입력 반환 검증
     */
    @DisplayName("성공: 자동차 이름 입력 프롬프트를 출력, 입력 반환")
    @Test
    void readCarNames_Success() {
        testMethod = "readCarNames";
        assertSimpleTest(() -> {
            run("pobi,woni,jun");
            assertThat(output()).contains("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            assertThat(returnValue).isEqualTo("pobi,woni,jun");
        });
    }

    /**
     * {@code readTryCount} 메시지 출력, 입력 반환 검증
     */
    @DisplayName("성공: 시도 횟수 입력 프롬프트 출력, 입력 반환")
    @Test
    void readTryCount_Success() {
        testMethod = "readTryCount";
        assertSimpleTest(() -> {
            run("5");
            assertThat(output()).contains("시도할 횟수는 몇 회인가요?");
            assertThat(returnValue).isEqualTo("5");
        });
    }

    /**
     * {@code NsTest}의 {@code runMain}을 override해 선택된 테스트 메서드만 실행, 리턴 값 출력
     */
    @Override
    public void runMain() {
        Input inputView = new Input();
        if ("readCarNames".equals(testMethod)) {
            returnValue = inputView.readCarNames();
        }
        if ("readTryCount".equals(testMethod)) {
            returnValue = inputView.readTryCount();
        }
    }
}