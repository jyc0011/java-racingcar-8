package racingcar.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.wrapper.CarName;
import racingcar.domain.wrapper.Position;
import racingcar.dto.CarState;

/**
 * {@code Car} 단위 테스트
 * <p>
 * 초기 상태(이름, 위치 0) 보장, 난수 값에 따른 이동(전진/멈춤), 위치 비교, DTO 변환을 확인
 */
@DisplayName("Car 테스트")
class CarTest extends NsTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private Car car;
    private CarName carName;
    private Position posZero;

    /**
     * 테스트 전, "pobi"라는 이름과 위치 0을 가진 {@code Car} 객체 생성
     */
    @BeforeEach
    void setUp() {
        carName = new CarName("pobi");
        posZero = new Position();
        car = new Car(carName, posZero);
    }

    /**
     * {@code Car}가 생성될 때 이름과 위치(0) 초기화가 제대로 되었는지 확인
     */
    @DisplayName("성공: 이름과 초기 위치(0)을 가진 객체 생성")
    @Test
    void create_Success_InitialState() {
        Car newCar = new Car(new CarName("pobi"), new Position());
        assertThat(newCar).isEqualTo(car);
        assertThat(newCar.toState()).isEqualTo(new CarState("pobi", 0));
    }

    /**
     * {@code move} 호출 시, 난수가 4 이상이면 위치가 +1 된 새 {@code Car} 객체를 반환
     */
    @DisplayName("성공: move 시 4 이상이면 1칸 전진")
    @Test
    void success_MoveForward() {
        assertRandomNumberInRangeTest(
                () -> {
                    Car movedCar = car.move();
                    assertThat(movedCar).isNotEqualTo(car);
                    Position posOne = posZero.increase();
                    assertThat(movedCar.isAtSameOrFurtherPosition(posOne)).isTrue();
                    assertThat(movedCar.toState().position()).isEqualTo(1);
                },
                MOVING_FORWARD
        );
    }

    /**
     * {@code move} 호출 시, 난수가 3 이하이면 원본 위치 객체를 반환
     */
    @DisplayName("성공: move 시 3 이하면 움직이지 않음")
    @Test
    void success_Stop() {
        assertRandomNumberInRangeTest(
                () -> {
                    Car movedCar = car.move();
                    assertThat(movedCar).isEqualTo(car);
                    assertThat(movedCar.toState().position()).isEqualTo(0);
                },
                STOP
        );
    }

    /**
     * {@code isAtSameOrFurtherPosition}메서드로 최대 위치와 자신의 위치을 비교
     */
    @DisplayName("성공: 위치 비교")
    @Test
    void compare_Position() {
        Position posOne = posZero.increase();
        Position posTwo = posOne.increase();
        Car carAtPosTwo = new Car(carName, posTwo);
        assertThat(carAtPosTwo.isAtSameOrFurtherPosition(posTwo)).isTrue();
        assertThat(carAtPosTwo.isAtSameOrFurtherPosition(posOne)).isTrue();
        assertThat(car.isAtSameOrFurtherPosition(posTwo)).isFalse();
    }

    /**
     * {@code toState}가 현재 {@code Car}에서 {@code CarState} DTO로 변환
     */
    @DisplayName("성공: toState가 DTO를 반환")
    @Test
    void toState_ReturnDto() {
        Position posOne = posZero.increase();
        Car movedCar = new Car(carName, posOne);
        CarState state = movedCar.toState();
        assertThat(state.name()).isEqualTo("pobi");
        assertThat(state.position()).isEqualTo(1);
    }

    @Override
    protected void runMain() {
    }
}