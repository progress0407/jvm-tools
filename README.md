# JVM 실험 및 뼈대 코드 모음

> 종종 실무나 토이 프로젝트를 하다 보면 중복된 코드나 내부의 작동 방식이 궁금할 때가 있습니다.  
> 매번 바퀴를 다시 만들지 않고 궁금증을 해소하기 위한 목적으로 만들었습니다.

## 각 모듈 설명

> 도커와 쿠버네티스 모듈은 [이곳](https://github.com/progress0407/infra-devops-labs)으로 옮겼습니다.

| 모듈 이름         | 설명                                        |
|---------------|-------------------------------------------|
| common        | 각 모듈이 사용하는 공통 모듈                          |
| kafka         | 카프카의 여러 응용 상황을 실험해보기 위함                   |
| lab           | 크지 않은 기능들, <br/> Kotlin 문법 등을 테스트하기 위한 용도 |
| reactive-x    | RxJava, 웹플럭스 등 뼈대 코드를 작성하고 테스트하기 위한 용도    |
| spring-batch  | 스프링 배치 뼈대 코드 작성                           |
| sql-generator | SQL 동적 생성기 구현 (넘블 프로젝트 당시)                |
| test-cdoe     | 테스트 코드 응용 상황에 대한 대표적인 예시 모음               |
| spring-event  | 스프링 내부 이벤트, 성능 테스트 코드 작성                  |
