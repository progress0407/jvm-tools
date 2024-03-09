package philo.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {

        List<Map<String, String>> list = List.of(
            Map.of("keyA", "valA", "keyB", "123"),
            Map.of("keyA", "valA", "keyB", "456")
        );

        Map<String, List<String>> result = list.stream()
            .collect(Collectors.groupingBy(
                item -> item.get("keyA"), // Key extractor for grouping
                Collectors.mapping(item -> item.get("keyB"), Collectors.toList()) // Value mapper to list
            ));

        System.out.println(result);
    }
}
