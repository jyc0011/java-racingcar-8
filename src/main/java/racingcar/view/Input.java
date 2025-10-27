package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자 입력 담당
 * <p>
 * {@code Console.readLine()} 사용해 입력을 받고, 입력 프롬프트 출력
 */
public class Input {

    private static final String MSG_READ_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String MSG_READ_TRY_COUNT = "시도할 횟수는 몇 회인가요?";

    /**
     * 기본 생성자
     */
    public Input() {
    }

    /**
     * 자동차 이름 입력 프롬프트 출력, 사용자 입력 문자열 반환
     *
     * @return 사용자 입력
     */
    public String readCarNames() {
        System.out.println(MSG_READ_CAR_NAMES);
        return Console.readLine();
    }

    /**
     * 시도 횟수 입력 프롬프트 출력, 사용자 입력 문자열 반환
     *
     * @return 사용자 입력
     */
    public String readTryCount() {
        System.out.println(MSG_READ_TRY_COUNT);
        return Console.readLine();
    }
}