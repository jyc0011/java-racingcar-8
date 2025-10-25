package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.domain.wrapper.CarName;
import racingcar.domain.wrapper.Position;
import racingcar.dto.CarState;

/**
 * 자동차 목록
 *
 * <p>{@code List<Car>} 캡슐화, 이름 파싱, 중복 검증, 전체 이동, 우승자 찾기 등 처리
 */
public class Cars {

    private static final String NAME_DELIMITER = ",";

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * 사용자가 입력한 이름 목록 문자열(String)로 {@code Cars} 객체를 생성합니다.
     * <p>이 과정에서 파싱, 유효성 검증(공백, 중복)이 모두 이루어집니다.
     *
     * @param namesInput 사용자가 입력한 쉼표(,)로 구분된 이름 문자열
     * @return 유효성 검증이 완료된 {@code Cars} 객체
     */
    public static Cars fromNames(String namesInput) {
        String[] names = namesInput.split(NAME_DELIMITER, -1);
        List<String> trimmedNames = trimNames(names);
        List<CarName> carNames = toCarNames(trimmedNames);
        validateNoDuplicates(carNames);
        List<Car> initialCars = createInitialCars(carNames);
        return new Cars(initialCars);
    }

    private static List<String> trimNames(String[] names) {
        List<String> trimmed = new ArrayList<>();
        for (String name : names) {
            trimmed.add(name.trim());
        }
        return trimmed;
    }

    private static List<CarName> toCarNames(List<String> names) {
        List<CarName> carNames = new ArrayList<>();
        for (String name : names) {
            carNames.add(new CarName(name));
        }
        return carNames;
    }

    private static void validateNoDuplicates(List<CarName> carNames) {
        Set<CarName> uniqueNames = new HashSet<>(carNames);
        if (uniqueNames.size() != carNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private static List<Car> createInitialCars(List<CarName> carNames) {
        List<Car> carList = new ArrayList<>();
        // "Indent 2단계" 규칙 준수
        for (CarName name : carNames) {
            carList.add(new Car(name, new Position()));
        }
        return carList;
    }

    /**
     * 목록의 모든 자동차에 대해 {@code move} 호출, 새 {@code Cars} 객체 반환
     *
     * @return {@code Cars}
     */
    public Cars moveAll() {
        List<Car> movedCars = new ArrayList<>();
        for (Car car : this.cars) {
            movedCars.add(car.move());
        }
        return new Cars(movedCars);
    }

    /**
     * 모든 자동차의 상태를 DTO로 반환
     *
     * @return {@code List<CarState>}
     */
    public List<CarState> getStates() {
        List<CarState> states = new ArrayList<>();
        for (Car car : this.cars) {
            states.add(car.toState());
        }
        return states;
    }

    /**
     * 현재 자동차 목록에서 우승자를 찾아 {@code CarName} 목록으로 반환
     *
     * @return 우승한 자동차의 {@code CarName} 목록
     */
    public List<CarName> findWinnerNames() {
        Position maxPosition = findMaxPosition();
        return findWinnersAt(maxPosition);
    }

    private Position findMaxPosition() {
        Position max = new Position();
        for (Car car : this.cars) {
            max = car.getFarthest(max);
        }
        return max;
    }

    private List<CarName> findWinnersAt(Position maxPosition) {
        List<CarName> winners = new ArrayList<>();
        for (Car car : this.cars) {
            car.addIfWinner(maxPosition, winners);
        }
        return winners;
    }
}