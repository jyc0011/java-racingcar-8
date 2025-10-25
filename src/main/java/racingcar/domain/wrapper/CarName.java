package racingcar.domain.wrapper;

import java.util.Objects;

/**
 * 자동차 이름 Value Object
 *
 * <p>자동차 이름과 관련된 유효성 검증
 * {@value #MAX_NAME_LENGTH}자를 초과할 수 없으며, 공백이거나 비어 있을 수 없다.
 */
public class CarName {
    private static final int MAX_NAME_LENGTH = 5;
    private final String name;

    /**
     * 주어진 문자열로 {@code CarName}을 생성하며, 유효성 검증 수행
     *
     * @param name 자동차 이름 문자열
     * @throws IllegalArgumentException 유효성(길이, 공백)을 위반할 경우
     */
    public CarName(String name) {
        validate(name);
        this.name = name.trim();
    }

    private void validate(String name) {
        ensureIsNotBlank(name);
        ensureLengthIsWithinLimit(name);
    }

    private void ensureIsNotBlank(String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("공백 / 빈이름 불가");
        }
    }

    private boolean isBlank(String name) {
        return name == null || name.trim().isEmpty();
    }

    private void ensureLengthIsWithinLimit(String name) {
        if (isTooLong(name)) {
            throw new IllegalArgumentException("이름 길이 5자 초과 X");
        }
    }

    private boolean isTooLong(String name) {
        return name.trim().length() > MAX_NAME_LENGTH;
    }

    /**
     * {@code CarName} 객체를 다른 객체와 비교
     * <p> 동일한 이름인 경우 같다고 정의한다.
     *
     * @param o 비교할 객체
     * @return 동일하면 true, 아니면 false
     */
    @Override
    public boolean equals(Object o) {
        if (isSame(o)) {
            return true;
        }
        if (isNotCarName(o)) {
            return false;
        }
        return hasSameName((CarName) o);
    }

    private boolean isSame(Object o) {
        return this == o;
    }

    private boolean isNotCarName(Object o) {
        return o == null || getClass() != o.getClass();
    }

    private boolean hasSameName(CarName other) {
        return Objects.equals(this.name, other.name);
    }

    /**
     * {@code CarName} 객체 해시 코드 반환
     * <p> 이름 문자열을 기반으로 생성함
     *
     * @return 객체의 해시 코드
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}