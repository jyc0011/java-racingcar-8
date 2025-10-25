package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Objects;
import racingcar.domain.wrapper.CarName;
import racingcar.domain.wrapper.Position;
import racingcar.dto.CarState;

/**
 * 자동차 1대에 대한 도메인 객체
 *
 * <p>이름과 위치를 가지며, immutable
 * 이동 여부 결정, 우승자 판별 로직 포함
 */
public class Car {

    private static final int MOVE_THRESHOLD = 4;
    private static final int MIN_RANDOM_NUM = 0;
    private static final int MAX_RANDOM_NUM = 9;

    private final CarName name;
    private final Position position;

    /**
     * 이름과 위치로 {@code Car} 생성
     *
     * @param name     자동차 이름 ({@code CarName} 객체)
     * @param position 자동차 위치 ({@code Position} 객체)
     */
    public Car(CarName name, Position position) {
        this.name = name;
        this.position = position;
    }

    /**
     * 이동 함수. 난수 값에 따라 전진/멈춤
     * <p> 전진 시 새로운 {@code Car} 객체 반환, 멈춤 시 원본 {@code this} 반환
     *
     * @return 이동 결과가 반영된 {@code Car} 객체
     */
    public Car move() {
        int randomValue = Randoms.pickNumberInRange(MIN_RANDOM_NUM, MAX_RANDOM_NUM);

        if (canMove(randomValue)) {
            return moveForward();
        }
        return this;
    }

    private boolean canMove(int randomValue) {
        return randomValue >= MOVE_THRESHOLD;
    }

    private Car moveForward() {
        Position nextPosition = this.position.increase();
        return new Car(this.name, nextPosition);
    }

    /**
     * 현재 차의 위치를 DTO로 변환 후 반환
     * <p> View 레이어로 DTO 전달.
     *
     * @return {@code CarState} DTO
     */
    public CarState toState() {
        return new CarState(name.name(), position.value());
    }

    /**
     * 최대 위치와 자신의 위치가 같다면, 우승자 목록에 자신을 추가
     *
     * @param maxPosition 최대 위치
     * @param winners     우승자 이름 목록(List)
     */
    public void addIfWinner(Position maxPosition, List<CarName> winners) {
        if (isAtSamePosition(maxPosition)) {
            winners.add(this.name);
        }
    }

    /**
     * 더 먼 위치 찾기
     *
     * @param otherPosition 비교할 다른 위치
     * @return 더 큰 {@code Position} 객체
     */
    public Position getFarthest(Position otherPosition) {
        if (this.position.isSameOrFurtherThan(otherPosition)) {
            return this.position;
        }
        return otherPosition;
    }

    private boolean isAtSamePosition(Position otherPosition) {
        return this.position.equals(otherPosition);
    }

    /**
     * 주어진 위치와 같거나 더 먼지 비교
     *
     * @param otherPosition 비교할 {@code Position}
     * @return 자신의 위치가 otherPosition보다 크거나 같으면 true
     */
    public boolean isAtSameOrFurtherPosition(Position otherPosition) {
        return this.position.isSameOrFurtherThan(otherPosition);
    }

    /**
     * 두 {@code Car} 객체가 동일 객체인지 확인
     */
    @Override
    public boolean equals(Object o) {
        if (isSameInstance(o)) {
            return true;
        }
        if (isNotCar(o)) {
            return false;
        }
        return hasSameState((Car) o);
    }

    private boolean isSameInstance(Object o) {
        return this == o;
    }

    private boolean isNotCar(Object o) {
        return o == null || getClass() != o.getClass();
    }

    private boolean hasSameState(Car other) {
        boolean nameEquals = this.name.equals(other.name);
        boolean positionEquals = this.position.equals(other.position);
        return nameEquals && positionEquals;
    }

    /**
     * 해시 코드 반환
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}