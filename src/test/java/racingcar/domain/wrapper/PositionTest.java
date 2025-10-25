package racingcar.domain.wrapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * {@code Position} 단위 테스트
 *
 * <p> {@code Position}의 0 초기화, 1 증가, 위치 비교 검증
 */
@DisplayName("위치(Position) 단위 테스트")
class PositionTest {

    private Position posZero;

    /**
     * 각 테스트 실행 전 위치 0을 나타내는 {@code Position} 객체를 초기화
     */
    @BeforeEach
    void setUp() {
        posZero = new Position();
    }

    /**
     * {@code Position} 객체가 기본 생성자로 생성될 때 위치 값이 0 초기화되는지 검증
     *
     * <p>{@code equals} 메서드가 올바르게 동작하는지도 검증
     */
    @DisplayName("성공: 첫 생성시 0")
    @Test
    void create_Success_Initializes() {
        Position newPosition = new Position();
        assertThat(newPosition).isEqualTo(posZero);
    }

    /**
     * {@code increase} 메서드 호출 시, 값이 1 증가하는지, 원본 객체 변경 대신 새로운 객체가 반환되는지 검증
     */
    @DisplayName("성공: increase 호출 시 1 증가, 새 객체 반환")
    @Test
    void check_ReturnsNewObject_onIncrease() {
        Position posOne = posZero.increase();
        assertThat(posZero).isNotEqualTo(posOne);
        assertThat(posOne.isSameOrFurtherThan(posZero)).isTrue();
        assertThat(posZero.isSameOrFurtherThan(posOne)).isFalse();
        Position posTwo = posOne.increase();
        assertThat(posTwo.isSameOrFurtherThan(posOne)).isTrue();
        assertThat(posOne.isSameOrFurtherThan(posTwo)).isFalse();
    }

    /**
     * {@code isSameOrFurtherThan}로 위치 비교 결과 확인
     */
    @DisplayName("성공: isSameOrFurtherThan이 위치를 정확히 비교한다")
    @Test
    void check_isSameOrFurtherThan() {
        Position posOne = posZero.increase();
        Position posTwo = posOne.increase();
        Position posThree = posTwo.increase();

        Position anotherPosOne = new Position().increase();
        Position anotherPosTwo = anotherPosOne.increase();
        Position anotherPosThree = anotherPosTwo.increase();

        assertThat(posThree.isSameOrFurtherThan(posOne)).isTrue();
        assertThat(posOne.isSameOrFurtherThan(posThree)).isFalse();
        assertThat(posThree.isSameOrFurtherThan(anotherPosThree)).isTrue();
        assertThat(anotherPosThree.isSameOrFurtherThan(posThree)).isTrue();
        assertThat(posOne.isSameOrFurtherThan(posZero)).isTrue();
        assertThat(posZero.isSameOrFurtherThan(posZero)).isTrue();
        assertThat(posZero.isSameOrFurtherThan(posOne)).isFalse();
    }
}