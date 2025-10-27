package racingcar;

import racingcar.controller.RaceGameController;

/**
 * 애플리케이션 Entry Point
 * <p>
 * {@code main} 메서드를 포함하며, {@link RaceGameController} 생성, 실행
 */
public class Application {
    public static void main(String[] args) {
        RaceGameController gameController = new RaceGameController();
        gameController.run();
    }
}