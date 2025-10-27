package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.wrapper.CarName;
import racingcar.dto.CarState;

/**
 * {@code Output} 뷰 클래스를 위한 단위 테스트 클래스.
 *
 * <p>이 클래스는 {@code NsTest}를 상속받아,
 * {@code System.out}으로 출력되는 모든 결과가
 * 요구 사항의 출력 형식과 일치하는지 검증합니다.
 */
@DisplayName("출력(Output) 뷰 테스트")
class OutputTest extends NsTest {

    /**
     * {@code printRaceStart}가 "실행 결과" 머리글을
     * 올바르게 출력하는지 검증합니다.
     */
    @DisplayName("성공: '실행 결과' 머리글을 출력한다")
    @Test
    void printRaceStart_Success() {
        Output outputView = new Output();
        outputView.printRaceStart();
        assertThat(output()).contains("실행 결과");
    }

    /**
     * {@code printRoundResult}가 {@code CarState} 목록을 받아
     * "이름 : ---" 형식으로 올바르게 변환하여 출력하는지 검증합니다.
     */
    @DisplayName("성공: 라운드별 실행 결과를 형식에 맞게 출력한다")
    @Test
    void printRoundResult_Success() {
        // Given
        Output outputView = new Output();
        List<CarState> states = List.of(
                new CarState("pobi", 3),
                new CarState("woni", 0),
                new CarState("jun", 5)
        );
        outputView.printRoundResult(states);
        String expectedOutput = "pobi : ---" + System.lineSeparator() +
                "woni : " + System.lineSeparator() +
                "jun : -----";

        assertThat(output()).contains(expectedOutput);
    }

    /**
     * {@code printWinners}가 단독 우승자를
     * "최종 우승자 : 이름" 형식으로 올바르게 출력하는지 검증합니다.
     */
    @DisplayName("성공: 단독 우승자를 형식에 맞게 출력한다")
    @Test
    void printWinners_SingleWinner() {
        // Given
        Output outputView = new Output();
        List<CarName> winners = List.of(new CarName("pobi"));

        // When
        outputView.printWinners(winners);

        // Then
        assertThat(output()).contains("최종 우승자 : pobi");
    }

    /**
     * {@code printWinners}가 공동 우승자를
     * "최종 우승자 : 이름1, 이름2" 형식(쉼표 구분)으로 올바르게 출력하는지 검증합니다.
     */
    @DisplayName("성공: 공동 우승자를 쉼표로 구분하여 형식에 맞게 출력한다")
    @Test
    void printWinners_JointWinners() {
        // Given
        Output outputView = new Output();
        List<CarName> winners = List.of(
                new CarName("pobi"),
                new CarName("jun")
        );
        outputView.printWinners(winners);
        assertThat(output()).contains("최종 우승자 : pobi, jun");
    }

    @Override
    protected void runMain() {
    }
}