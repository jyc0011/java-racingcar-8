package racingcar.controller;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.wrapper.CarName;
import racingcar.domain.wrapper.TryCount;
import racingcar.view.Input;
import racingcar.view.Output;

/**
 * 레이싱 게임 전체 컨트롤러
 * <p>
 * {@code Input}과 {@code Output}로 입출력 제어, {@code Cars}와 {@code TryCount} 도메인 객체를 생성
 */
public class RaceGameController {

    private final Input inputView;
    private final Output outputView;

    /**
     * 컨트롤러 생성, View 객체({@link Input}, {@link Output}) 초기화
     */
    public RaceGameController() {
        this.inputView = new Input();
        this.outputView = new Output();
    }

    /**
     * 레이싱 게임 전체 흐름 제어
     * <ol>
     * <li>자동차 이름, 시도 횟수 입력, 도메인 객체 생성</li>
     * <li>입력된 횟수만큼 경주 라운드 실행, 중간 결과 출력</li>
     * <li>최종 우승자 판별, 출력</li>
     * </ol>
     * 예외({@link IllegalArgumentException})는 Application에서 처리
     */
    public void run() {
        Cars cars = createCarsFromInput();
        TryCount tryCount = createTryCountFromInput();
        Cars finalCars = runRounds(cars, tryCount);
        showWinners(finalCars);
    }

    /**
     * 입력으로 {@code Cars} 객체 생성
     * <p>입력은 {@link Input}, 파싱과 유효성 검증은 {@link Cars#fromNames(String)} 위임
     *
     * @return {@code Cars} 객체
     */
    private Cars createCarsFromInput() {
        String namesInput = inputView.readCarNames();
        return Cars.fromNames(namesInput);
    }

    /**
     * 입력으로 {@code TryCount} 객체 생성
     * <p>입력은 {@link Input}, 파싱 및 유효성 검증은 {@link TryCount#TryCount(String)} 위임
     *
     * @return {@code TryCount} 객체
     */
    private TryCount createTryCountFromInput() {
        String countInput = inputView.readTryCount();
        return new TryCount(countInput);
    }

    /**
     * 경주 실행
     * <p>{@link #runSingleRound(Cars, TryCount)} 호출, 실제 라운드 진행, 최종 자동차 상태 반환
     *
     * @param cars     초기 자동차 상태
     * @param tryCount 총 시도 횟수
     * @return 모든 라운드가 완료된 후의 최종 자동차 상태({@code Cars})
     */
    private Cars runRounds(Cars cars, TryCount tryCount) {
        outputView.printRaceStart();
        return runSingleRound(cars, tryCount);
    }

    /**
     * 하나의 라운드 실행, 남은 횟수가 있으면 재귀로 다음 라운드 실행
     * <p>라운드마다 모든 자동차 이동({@link Cars#moveAll()}), 중간 결과 출력({@link Output#printRoundResult(List)})
     *
     * @param cars     현재 라운드의 자동차 상태
     * @param tryCount 남은 시도 횟수
     * @return 마지막 라운드까지 완료된 최종 자동차 상태({@code Cars})
     */
    private Cars runSingleRound(Cars cars, TryCount tryCount) {
        if (!tryCount.hasMoreTries()) {
            return cars;
        }
        Cars movedCars = cars.moveAll();
        outputView.printRoundResult(movedCars.getStates());
        TryCount nextCount = tryCount.decrease();
        return runSingleRound(movedCars, nextCount);
    }

    /**
     * 최종 우승자 판별 결과 출력
     * <p>우승자 판별 {@link Cars#findWinnerNames()}, 출력은 {@link Output#printWinners(List)} 위임
     *
     * @param cars 모든 라운드가 완료된 최종 자동차 상태
     */
    private void showWinners(Cars cars) {
        List<CarName> winners = cars.findWinnerNames();
        outputView.printWinners(winners);
    }
}