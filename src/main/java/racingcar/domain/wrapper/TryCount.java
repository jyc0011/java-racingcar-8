package racingcar.domain.wrapper;

import java.util.Objects;

/**
 * 게임의 시도 횟수
 *
 * <p>시도 횟수가 1 이상의 자연수인지 검증
 */
public class TryCount {
    private static final String NATURAL_NUMBER_REGEX = "\\d+";
    private static final int MINIMUM_TRY_COUNT = 1;
    private static final int NO_MORE_TRIES = 0;
    private final int count;

    /**
     * 사용자 입력을(String) 받아 {@code TryCount} 생성
     *
     * @param input 사용자가 입력한 시도 횟수 문자열
     * @throws IllegalArgumentException 입력이 1 이상의 자연수가 아닐 경우
     */
    public TryCount(String input) {
        this.count = parseAndValidate(input);
    }

    private TryCount(int count) {
        this.count = count;
    }

    private int parseAndValidate(String input) {
        ensureIsNumber(input);
        int number = Integer.parseInt(input);
        ensureIsPositive(number);
        return number;
    }

    private void ensureIsNumber(String input) {
        if (isNotNumber(input)) {
            throw new IllegalArgumentException("시도 횟수는 숫자(자연수)여야 합니다.");
        }
    }

    private boolean isNotNumber(String input) {
        return !input.matches(NATURAL_NUMBER_REGEX);
    }

    private void ensureIsPositive(int number) {
        if (isNotPositive(number)) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 자연수여야 합니다.");
        }
    }

    private boolean isNotPositive(int number) {
        return number < MINIMUM_TRY_COUNT;
    }

    /**
     * 시도 횟수가 1 이상인지 확인
     *
     * @return 1 이상이면 true, 0이면 false
     */
    public boolean hasMoreTries() {
        return this.count > NO_MORE_TRIES;
    }

    /**
     * 시도 횟수를 1 차감한 새 객체 반환
     *
     * @return 1 차감된 {@code TryCount} 객체
     * @throws IllegalStateException 횟수가 이미 0일 경우
     */
    public TryCount decrease() {
        if (isZero()) {
            throw new IllegalStateException("시도 횟수가 0이므로 더 이상 차감할 수 없습니다.");
        }
        return new TryCount(this.count - 1);
    }

    private int calculateNextCount() {
        if (isZero()) {
            return NO_MORE_TRIES;
        }
        return this.count - 1;
    }

    private boolean isZero() {
        return this.count == NO_MORE_TRIES;
    }

    /**
     * 두 {@code TryCount} 객체가 동일한지 확인
     */
    @Override
    public boolean equals(Object o) {
        if (isSameInstance(o)) {
            return true;
        }
        if (isNotTryCount(o)) {
            return false;
        }
        return hasSameValue((TryCount) o);
    }

    private boolean isSameInstance(Object o) {
        return this == o;
    }

    private boolean isNotTryCount(Object o) {
        return o == null || getClass() != o.getClass();
    }

    private boolean hasSameValue(TryCount other) {
        return this.count == other.count;
    }

    /**
     * 해시 코드 반환
     */
    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}