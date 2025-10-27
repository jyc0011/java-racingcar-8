package racingcar;

import racingcar.controller.RaceGameController;

/**
 * 애플리케이션 Entry Point
 * <p>
 * {@code main} 메서드를 포함하며, {@link RaceGameController} 생성, 실행
 */
public class Application {
    /**
     * 기본 생성자
     */
    public Application() {
    }

    /**
     * 애플리케이션을 시작하는 메인 메서드
     * @param args 커맨드 라인 인자
     */
    public static void main(String[] args) {
        RaceGameController gameController = new RaceGameController();
        gameController.run();
    }
}