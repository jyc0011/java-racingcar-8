package racingcar.domain.wrapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@code CarName} 단위 테스트
 *
 * <p> {@code CarName} 생성 시 유효성 검증(이름 길이, 공백 여부)과 Value Object로서의 동작 검증
 */

@DisplayName("CarName 테스트")
class CarNameTest {

    /**
     * 자동차 이름이 1자에서 5자 사이인 경우 성공
     */
    @DisplayName("성공: 이름 길이 1자 이상 5자 이하")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "woni", "jun", "abcde"})
    void create_Success(String validName) {
        assertThatCode(() -> new CarName(validName))
                .doesNotThrowAnyException();
    }

    /**
     * 자동차 이름이 6자 이상인 경우 {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 이름 길이 6이상")
    @Test
    void create_Fail_WhenNameTooLong() {
        assertThatThrownBy(() -> new CarName("abcdef"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * 자동차 이름이 빈 문자열("")이나 공백(" ")인 경우 {@code IllegalArgumentException} 발생
     */
    @DisplayName("오류: 빈문자열 또는 공백 입력")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void create_Fail_WhenNoCarNames(String invalidName) {
        assertThatThrownBy(() -> new CarName(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * {@code CarName}이 Value Object로서 이름이 같으면 동일한 객체로,
     * 다르면 다른 객체로 취급되는지(equals, hashCode) 확인
     */
    @DisplayName("성공: 같은 이름은 동일한 값으로 취급")
    @Test
    void equals_And_HashCode_Test() {
        CarName name1 = new CarName("pobi");
        CarName name2 = new CarName("pobi");
        CarName name3 = new CarName("woni");

        assertThat(name1).isEqualTo(name2);
        assertThat(name1.hashCode()).isEqualTo(name2.hashCode());
        assertThat(name1).isNotEqualTo(name3);
    }
}