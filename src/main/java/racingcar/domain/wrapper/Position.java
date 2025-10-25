package racingcar.domain.wrapper;

import java.util.Objects;

/**
 * 자동차 위치 Value Object
 * <p> 위치 0부터 시작, {@code increase}를 통해 1씩 증가
 */
public class Position {

    private static final int DEFAULT_POSITION = 0;

    private final int value;

    /**
     * 기본 생성자 위치 0으로 초기화
     */
    public Position() {
        this(DEFAULT_POSITION);
    }

    private Position(int value) {
        this.value = value;
    }

    /**
     * 현재 위치 + 1인 새로운 {@code Position} 리턴
     *
     * @return 현재 위치 + 1 된 위치를 가진 새 객체
     */
    public Position increase() {
        return new Position(this.value + 1);
    }

    /**
     * 현재 위치가 다른 위치 이상인지 비교
     *
     * @param other 비교하려는 {@code Position}
     * @return 현재 위치가 other 이상이면 true
     */
    public boolean isSameOrFurtherThan(Position other) {
        return this.value >= other.value;
    }

    /**
     * 두 {@code Position}이 같은 위치인지 비교
     */
    @Override
    public boolean equals(Object o) {
        if (isSameInstance(o)) {
            return true;
        }
        if (isNotPosition(o)) {
            return false;
        }
        return hasSameValue((Position) o);
    }

    private boolean isSameInstance(Object o) {
        return this == o;
    }

    private boolean isNotPosition(Object o) {
        return o == null || getClass() != o.getClass();
    }

    private boolean hasSameValue(Position other) {
        return this.value == other.value;
    }

    /**
     * 해시 코드 반환
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * 데이터 전달을 위해 캡슐화된 위치(value)를 반환
     *
     * @return 자동차 이름
     */
    public int value() {
        return this.value;
    }
}