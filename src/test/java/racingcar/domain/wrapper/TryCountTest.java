package racingcar.domain.wrapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * {@code TryCount} 단위 테스트
 *
 * <p> {@code TryCount} 생성 시 유효성 검증(자연수 여부), 횟수 차감, 남은 횟수 확인 검증
 */
class TryCountTest {

    /**
     * 1 이상의 자연수 문자열일 경우 생성 성공
     */
    @DisplayName("성공: 1 이상 자연수이면 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "100"})
    void create_Success(String input) {
        assertThatCode(() -> new TryCount(input)).doesNotThrowAnyException();
    }

    /**
     * 시도 횟수가 0, 음수, 숫자가 아닌 문자, 공백, 실수 등 유효하지 않을 경우 {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 0, 음수, 숫자 아닌 문자, 공백이면 생성 불가")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-5", "abc", " 1", "1 ", " ", "", "1.5"})
    void create_Fail_WithInvalidInput(String input) {
        assertThatThrownBy(() -> new TryCount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * {@code hasMoreTries}는 1 이상일 경우 {@code true}, 0일 경우 {@code false}를 반환
     */
    @DisplayName("성공: 남은 횟수가 1 이상이면 hasMoreTries가 true, 0이면 false 반환")
    @Test
    void check_HasMoreTries_right() {
        TryCount countOfOne = new TryCount("1");
        assertThat(countOfOne.hasMoreTries()).isTrue();
        TryCount countOfTwo = new TryCount("2");
        assertThat(countOfTwo.hasMoreTries()).isTrue();
        TryCount countAfterDecrease = countOfOne.decrease();
        assertThat(countAfterDecrease.hasMoreTries()).isFalse();
    }

    /**
     * {@code decrease}는 1 감소된 새로운 객체 반환하며 0 이하로는 감소하지 않음
     */
    @DisplayName("성공: decrease 호출 시 1 감소된 새 객체 반환.")
    @Test
    void check_ReturnsNewObject_Decrese() {
        TryCount originalCount = new TryCount("3");
        TryCount decreasedOnce = originalCount.decrease();
        assertThat(decreasedOnce.hasMoreTries()).isTrue();
        assertThat(originalCount).isNotEqualTo(decreasedOnce);
        TryCount decreasedTwice = decreasedOnce.decrease();
        assertThat(decreasedTwice.hasMoreTries()).isTrue();
        TryCount countIsZero = decreasedTwice.decrease();
    }

    /**
     * {@code decrease}를 0일 때 호출하면, 오류
     */
    @DisplayName("실패: decrease 호출 시 0은 오류 발생")
    @Test
    void fail_InZero_Decrese() {
        TryCount originalCount = new TryCount("1");
        TryCount decreasedZero = originalCount.decrease();
        assertThat(decreasedZero.hasMoreTries()).isFalse();
        assertThatThrownBy(decreasedZero::decrease)
                .isInstanceOf(IllegalStateException.class);
    }
}