# java-racingcar-precourse

## 구현 기능 목록

1. 입력
    - 현재 입력의 경우 2가지가 주어져 있다.
    - [ ] 자동차 이름
        - [ ] 컴마로 각 차들을 구분한다.
        - [ ] 자동차 별 이름은 5자 이하여야 한다.
        - [ ] 인원 수에는 제한을 두지 않는다. 다만 1명 이상이어야 한다.
        - [ ] 동일한 이름은 2번 나올 수 없다.
    - [ ] 이동 횟수
        - [ ] 자연수로 제한한다.

2. 입력 검증
    - 위에서 입력된 사항과 관해 발생하는 예외들을 검증한다.
        - [ ] 자동차 이름
            - [ ] 한 자동차라도 이름이 0자 이하, 6장 이상일 수 없다.
            - [ ] 컴마 이외 다른 문자는 구분자가 될 수 없다.
            - [ ] 컴마와 컴마 사이에 공백만 존재할 수 없다. (예: 'car,carrrr,,carr')
        - [ ] 이동 횟수
            - [ ] 자연수 이외의 숫자, 문자, 공백등은 불가능하다.

3. 출력
    - [ ] 우승자 출력 전 이동과 관해 매 턴 출력한다.
        - [ ] 출력 형식은 `{이름} : {이동칸수만큼 -로 표기}` 이다.  
    - [ ] `최종 우승자 : {우승자 배열, 쉼표로 구분}` 으로 출력해야한다.

4. 이동 관련 함수
   - [ ] 위에서 지정한 turn이 1 증가할 때마다 이동 횟수는 1씩 감소하고, 0에 도달하면 종료한다.
   - [ ] 이동 횟수마다 각 자동차는 `pickNumberInRange(0,9) >= 4`인 경우에 1씩 증가한다.

5. 프로그래밍 요구사항 확인
    - [ ] indent의 깊이 2 이하
    - [ ] 3항 연산자는 불가능
    - [ ] 정리한 기능 목록 관련 테스트를 작성
    - [ ] 함수가 수행하는 기능은 1개
    - [ ] 자바 코드 컨벤션을 지킨다
    - [ ] api를 적극 활용한다.
        - [ ] 쉼표를 통해 문자열을 구분하는 경우 아래와 같다.
            ``` java
            var members = List.of("pobi", "jason");
            var result = String.join(",", members); // pobi,jason
            ```
        - [ ] 배열 대신 컬렉션을 사용
            ``` java
            var members = List.of("pobi", "jason");
            var result = members.contains("pobi"); // true
            ```
6. 테스트 코드
    - [ ] 단위 테스트
        - wrapper
            - [ ] 1자 이상 5자 이하의 이름으로 생성 시 성공한다. (예: "pobi", "abcde")
            - [ ] 동일한 이름이 두 번 입력될 시 시 IllegalArgumentException이 발생한다.
            - [ ] 5자를 초과하는 이름으로 생성 시 IllegalArgumentException이 발생한다. (예: "abcdef")
            - [ ] 이름이 0자(빈 문자열)일 경우 IllegalArgumentException이 발생한다. (예: "")
            - [ ] 이름이 공백 문자만 있을 경우 IllegalArgumentException이 발생한다. (예: " ")
            - [ ] 0명이 참여할 경우 IllegalArgumentException이 발생한다.
            - [ ] "1" 이상의 자연수 문자열로 생성 시 성공한다. (예: "1", "5")
            - [ ] "0" 또는 음수 문자열로 생성 시 IllegalArgumentException이 발생한다. (예: "0", "-1")
            - [ ] 숫자가 아닌 문자열로 생성 시 IllegalArgumentException이 발생한다. (예: "abc", " ")
            - [ ] 이동 횟수를 1 감소시킨다. (또는 불변 객체라면 1 감소된 새 객체를 반환한다)
            - [ ] 횟수가 1 이상이면 true, 0이면 false를 반환한다.
            - [ ] 위치 생성 시 0으로 초기화된다.
            - [ ] 위치를 1 증가시킨다. (또는 1 증가된 새 객체를 반환한다)
            - [ ] 다른 위치와 비교하여 더 멀리 있거나 같은지 판별한다.

        - domain
            - [ ] (난수 4 이상) 전진 시, 위치가 1 증가한 새 Car 객체를 반환한다. (불변성 확인)
            - [ ] (난수 3 이하) 멈춤 시, 위치가 그대로인 새 Car 객체를 반환한다.
            - [ ] 현재 이름과 위치를 가진 CarState DTO를 정확히 반환한다.
            - [ ] 우승자 판별을 위해 최대 위치와 자신의 위치를 비교한다.
            - [ ] 정상적인 이름 문자열로 Cars 객체를 생성한다.
            - [ ] 이름 목록에 빈 문자열이 포함되면 IllegalArgumentException이 발생한다.
            - [ ] 이름 목록에 공백만 있으면 IllegalArgumentException이 발생한다.
            - [ ] 이름 목록에 5자 초과 이름이 있으면 IllegalArgumentException이 발생한다.
            - [ ] 모든 Car 객체에게 move를 지시하고, 새 Cars 객체를 반환한다.
            - [ ] 가장 멀리 간 자동차가 1명일 때 (단독 우승) 해당 이름을 List로 반환한다.
            - [ ] 가장 멀리 간 자동차가 여러 명일 때 (공동 우승) 모든 이름을 List로 반환한다.
            - [ ] 모든 자동차의 CarState 목록을 List로 반환한다.

    - [ ] 기능, 통합 테스트
        - [ ] 단독 우승
            - 입력: pobi,woni / 1
            - 난수: 4 (전진), 3 (멈춤)
            - 출력: pobi : -, woni :, 최종 우승자 : pobi
        - [ ] 공동 우승
            - 입력: pobi,woni,jun / 1
            - 난수: 4 (전진), 2 (멈춤), 5 (전진)
            - 출력: pobi : -, woni :, jun : -, 최종 우승자 : pobi, ju
        - [ ] 여러 턴 진행
            - 입력: pobi,woni / 2 
            - 난수 (1턴): 4 (전진), 3 (멈춤)
            - 난수 (2턴): 3 (멈춤), 5 (전진)
            - 출력(1턴): pobi : -, woni :
            - 출력(2턴): pobi : -, woni : - 
            - 출력(최종): 최종 우승자 : pobi, woni
        - [ ] 자동차 이름 5자 초과
            - 입력: pobi,javaji / 1
        - [ ] 자동차 이름 0자 (빈 문자열)
            - 입력: pobi,,woni / 1
        - [ ] 자동차 이름 공백
            - 입력: pobi, ,woni / 1
        - [ ] 시도 횟수 (문자)
            - 입력: pobi,woni / abc
        - [ ] 시도 횟수 (0)
            - 입력: pobi,woni / 0
        - [ ] 시도 횟수 (음수)
            - 입력: pobi,woni / -1

---

## 프로젝트 구조

```
📁racingcar
├── Application.java
├──📁controller
│   └── RaceGameController.java
├──📁domain
│   ├── Car.java            // 자동차의 이름/위치
│   ├── Cars.java           // car의 First class collection
│   │
│   └──📁wrapper             // 유효성 검증
│       ├── CarName.java    // 이름
│       ├── Position.java   // 위치
│       └── TryCount.java   // 시도 횟수
├──📁dto
│   └── CarState.java       // 출력용 데이터 구조
└──📁view
    ├── Input.java        // 입력
    └── Output.java       // 출력

```

---