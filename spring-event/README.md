# 스프링 내부 이벤트

## 상황

Pre-Service에서 Post-Service으로 스프링 내부 이벤트를 전달한다

## 종류

- Case 1  `@EventListener`

- Case 2  `@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)`

- Case 3  `@Async` + `@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)`

|            | Case 1  | Case 2  | Case 3  |
|------------|---------|---------|---------|
| 트랜잭션 분리 여부 | 같은 트랜잭션 | 다른 트랜잭션 | 다른 트란잭션 |
| 쓰레드 분리 여부  | 같은 쓰레드  | 같은 쓰레드  | 다른 쓰레드  |


