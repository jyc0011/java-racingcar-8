package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.domain.wrapper.CarName;
import racingcar.dto.CarState;

/**
 * 모든 출력을 담당
 * <p>
 * 도메인 객체로부터 DTO를 전달받아, 콘솔에 출력
 */
public class Output {

    private static final String MSG_RACE_START = "\n실행 결과";
    private static final String MSG_WINNER_PREFIX = "최종 우승자 : ";
    private static final String POSITION_BAR = "-";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String WINNER_DELIMITER = ", ";

    /**
     * 기본 생성자
     */
    public Output() {
    }

    /**
     * 실행 결과 출력
     */
    public void printRaceStart() {
        System.out.println(MSG_RACE_START);
    }

    /**
     * 라운드 1번을 실행한 결과 출력
     *
     * @param roundStates 현재 라운드의 모든 {@code CarState} 목록
     */
    public void printRoundResult(List<CarState> roundStates) {
        for (CarState state : roundStates) {
            String formattedResult = formatPosition(state);
            System.out.println(formattedResult);
        }
        System.out.println();
    }

    /**
     * {@code CarState}를 "이름 : ---" 로 변환
     *
     * @param state 자동차 상태 DTO
     * @return 포매팅된 출력 문자열
     */
    private String formatPosition(CarState state) {
        String name = state.name();
        String bars = formatBars(state.position());
        return name + NAME_POSITION_SEPARATOR + bars;
    }

    /**
     * 위치(position) 값을 하이픈(-)로 변환
     *
     * @param position 위치 값
     * @return 하이픈 문자열 (예: 3 -> "---")
     */
    private String formatBars(int position) {
        // "점(.) 1개" 규칙 준수
        return POSITION_BAR.repeat(position);
    }

    /**
     * 최종 우승자 목록 출력
     *
     * @param winners 우승한 {@code CarName} 목록
     */
    public void printWinners(List<CarName> winners) {
        String formattedNames = formatWinnerNames(winners);
        System.out.println(MSG_WINNER_PREFIX + formattedNames);
        Console.close();
    }

    /**
     * {@code CarName}목록을 "이름1, 이름2"로 변환
     *
     * @param winners 우승자 목록
     * @return 쉼표로 구분된 우승자 이름 문자열
     */
    private String formatWinnerNames(List<CarName> winners) {
        List<String> names = convertCarNamesToStrings(winners);
        return String.join(WINNER_DELIMITER, names);
    }

    /**
     * {@code CarName} 목록을 {@code String} 목록으로 변환
     *
     * @param carNames {@code CarName} 목록
     * @return {@code String} 이름 목록
     */
    private List<String> convertCarNamesToStrings(List<CarName> carNames) {
        List<String> names = new java.util.ArrayList<>();
        for (CarName carName : carNames) {
            names.add(carName.name());
        }
        return names;
    }
}