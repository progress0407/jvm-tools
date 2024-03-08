package philo.map;

import java.util.HashMap;
import java.util.Map;

public class MapMain {

  public static void main(String[] args) {
//    case1();
//    case2();
    case3();
  }

  private static void case3() {
    Map<String, Integer> map = new HashMap<>();
    map.put("a", 1);
    map.computeIfAbsent("a", k -> map.get(k) + 1);
    map.putIfAbsent("a", map.get("a") + 1);
    System.out.println("map = " + map);
  }

  private static void case2() {
    Map<String, Integer> map = new HashMap<>();
    map.put("a", 1);
    Integer cnt = map.get("a");
    map.put("a", cnt + 1); // ok
    System.out.println("map = " + map);
  }

  private static void case1() {
    Map<String, Integer> map = Map.of("a", 1);
    Integer cnt = map.get("a");
    map.put("a", cnt + 1); // error!
  }
}
