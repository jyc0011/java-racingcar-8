package racingcar.dto;

/**
 * View로 자동차의 현재 상태(이름, 위치)를 전달하기 위한 DTO
 *
 * @param name     자동차의 이름
 * @param position 자동차의 현재 위치 (0부터 시작)
 */
public record CarState(String name, int position) {
}