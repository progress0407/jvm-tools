package io.philo.app;

import static java.lang.System.out;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchAllEntitiesJava {

  @PersistenceContext
  private EntityManager entityManager;

  @PostConstruct
  public void init() {
    var entities = entityManager.getMetamodel().getEntities();

    List<String> allTableNames = entities.stream()
        .map(Type::getJavaType)
        .map(this::convertTableName)
        .toList();

    allTableNames.forEach(it -> out.println("it = " + it));

    List<String> dmls = allTableNames.stream()
        .map(tableName -> "update " + tableName + " set some_column = that where id = some_id")
        .collect(Collectors.toList());

    dmls.forEach(System.out::println);
  }

  /**
   * 아래와 같은 우선순위로 테이블 이름을 반환합니다.
   * <br /><br />
   * 1. 테이블 정보가 있다면, 테이블 이름 반환
   * <br />
   * 2. 그렇지 않다면 엔티티 클래스명을 스네이크 케이스로 변환
   */
  private String convertTableName(Class<?> entityMetaInfo) {
    Table tableInfo = entityMetaInfo.getAnnotation(Table.class);
    if (tableInfo != null) {
      return tableInfo.name()
    }
    return  toSnakeCase(entityMetaInfo.getSimpleName());
  }

  /**
   * 스네이크 케이스로 변환
   * <br /><br />
   * 단 첫번째 문자는 변환하지 않습니다.
   * <br />
   *   - Negative LookBehind: 조건이 참일 경우, 제외하고 처리
   */
  private String toSnakeCase(String input) {
    return input.replaceAll("(?<!^)([A-Z])", "_$1").toLowerCase();
  }
}