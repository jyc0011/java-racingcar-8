package racingcar.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.wrapper.CarName;
import racingcar.dto.CarState;

/**
 * {@code Cars} 단위 테스트
 * <p>
 * 이름 문자열 파싱 및 유효성 검증, 모든 자동차의 동시 이동 처리,우승자 판별, DTO 상태 반환을 확인
 */
@DisplayName("Cars 테스트")
class CarsTest extends NsTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    /**
     * 이름 목록으로 객체 생성
     */
    @DisplayName("성공: 이름 목록으로 객체 생성")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,woni,jun", "pobi"})
    void create_Success(String validName) {
        assertThatCode(() -> Cars.fromNames(validName))
                .doesNotThrowAnyException();
    }

    /**
     * 이름 목록에 5자를 초과하는 이름이 포함될 경우 {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 5자 초과 이름 포함")
    @Test
    void create_Fail_HasLongName() {
        assertThatThrownBy(() -> Cars.fromNames("pobi,abcdef"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * 이름 목록에 빈 문자열(,,)이나 공백(" ")이 포함될 경우 {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 목록에 빈/공백 이름 포함")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,,woni", ",pobi,woni", "pobi,woni,", "pobi, ,woni"})
    void create_Fail_HasEmptyOrBlank(String invalidName) {
        assertThatThrownBy(() -> Cars.fromNames(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * 이름 목록에 중복된 이름이 포함될 경우 {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 목록에 이름 중복")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,woni,pobi", "pobi,pobi,pobi"})
    void create_Fail_Duplicates(String invalidName) {
        assertThatThrownBy(() -> Cars.fromNames(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * 이름 목록이 빈 경우 (0명) {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 0명 입력")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void create_Fail_Empty(String invalidName) {
        // [추가됨] 0명 입력 케이스
        assertThatThrownBy(() -> Cars.fromNames(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * {@code moveAll} 호출 시, 모든 자동차가 정해진 난수 값에 따라 전진/멈춤 수행
     * <p>(pobi: 전진, woni: 멈춤)
     */
    @DisplayName("성공: moveAll로 모든 차를 전진/멈춤")
    @Test
    void success_MoveAllCars() {
        assertRandomNumberInRangeTest(
                () -> {
                    Cars cars = Cars.fromNames("pobi,woni");
                    Cars movedCars = cars.moveAll();
                    List<CarState> states = movedCars.getStates();
                    assertThat(cars).isNotEqualTo(movedCars);
                    assertThat(states.get(0).position()).isEqualTo(1);
                    assertThat(states.get(1).position()).isEqualTo(0);
                },
                MOVING_FORWARD,
                STOP
        );
    }

    /**
     * {@code findWinnerNames}는 단독 우승자 반환
     */
    @DisplayName("성공: 단독 우승자 찾기")
    @Test
    void success_FindSingleWinner() {
        assertRandomNumberInRangeTest(
                () -> {
                    Cars initialCars = Cars.fromNames("pobi,woni");
                    Cars movedCars = initialCars.moveAll();
                    List<CarName> winners = movedCars.findWinnerNames();
                    assertThat(winners).containsExactly(new CarName("pobi"));
                },
                MOVING_FORWARD, STOP
        );
    }

    /**
     * {@code findWinnerNames}는 공동 우승자 모두 반환
     */
    @DisplayName("성공: 공동 우승자 찾기")
    @Test
    void success_FindManyWinners() {
        assertRandomNumberInRangeTest(
                () -> {
                    Cars initialCars = Cars.fromNames("pobi,woni,jun");
                    Cars movedCars = initialCars.moveAll();
                    List<CarName> winners = movedCars.findWinnerNames();
                    assertThat(winners).containsExactlyInAnyOrder(
                            new CarName("pobi"),
                            new CarName("jun")
                    );
                },
                MOVING_FORWARD,
                STOP,
                MOVING_FORWARD
        );
    }

    /**
     * {@code getStates}가 현재 모든 자동차의 상태를 DTO로 반환
     */
    @DisplayName("성공: getStates가 DTO 반환")
    @Test
    void getStates_ReturnDtoList() {
        Cars cars = Cars.fromNames("pobi,woni");
        List<CarState> states = cars.getStates();
        assertThat(states).hasSize(2);
        assertThat(states.get(0).name()).isEqualTo("pobi");
        assertThat(states.get(0).position()).isEqualTo(0);
        assertThat(states.get(1).name()).isEqualTo("woni");
        assertThat(states.get(1).position()).isEqualTo(0);
    }

    @Override
    protected void runMain() {
    }
}