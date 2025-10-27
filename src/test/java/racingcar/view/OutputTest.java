package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.wrapper.CarName;
import racingcar.dto.CarState;

/**
 * {@code Output}를 위한 테스트
 *
 * <p>{@code System.out}으로 출력되는 모든 결과가 요구 사항의 출력 형식과 일치하는지 검증
 */
@DisplayName("출력(Output) 뷰 테스트")
class OutputTest extends NsTest {

    /**
     * {@code printRaceStart}가 실행 결과 출력 검증
     */
    @DisplayName("성공: '실행 결과' 출력")
    @Test
    void printRaceStart_Success() {
        Output outputView = new Output();
        outputView.printRaceStart();
        assertThat(output()).contains("실행 결과");
    }

    /**
     * {@code printRoundResult}가 {@code CarState} 목록을 받아 "이름 : ---" 형식 출력 검증
     */
    @DisplayName("성공: 라운드별 실행 결과 출력")
    @Test
    void printRoundResult_Success() {
        Output outputView = new Output();
        List<CarState> states = List.of(new CarState("pobi", 3), new CarState("woni", 0), new CarState("jun", 5));
        outputView.printRoundResult(states);
        String expectedOutput = "pobi : ---" + System.lineSeparator() + "woni : " + System.lineSeparator() + "jun : -----";
        assertThat(output()).contains(expectedOutput);
    }

    /**
     * {@code printWinners}가 단독 우승자를 "최종 우승자 : 이름" 형식 출력 검증
     */
    @DisplayName("성공: 단독 우승자 출력")
    @Test
    void printWinners_SingleWinner() {
        Output outputView = new Output();
        List<CarName> winners = List.of(new CarName("pobi"));
        outputView.printWinners(winners);
        assertThat(output()).contains("최종 우승자 : pobi");
    }

    /**
     * {@code printWinners}가 공동 우승자를 "최종 우승자 : 이름1, 이름2" 형식 출력 검증
     */
    @DisplayName("성공: 공동 우승자 출력")
    @Test
    void printWinners_JointWinners() {
        Output outputView = new Output();
        List<CarName> winners = List.of(new CarName("pobi"), new CarName("jun"));
        outputView.printWinners(winners);
        assertThat(output()).contains("최종 우승자 : pobi, jun");
    }

    @Override
    protected void runMain() {
    }
}