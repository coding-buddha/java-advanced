package java8.stream.exercise01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercise01 {

    public static void main(String[] args) {
        /**
         * List.of("Good", "Old", "Stream", "API")
         *
         * =========================================================================================
         *
         * (0, "Good"), (1, "Old"), (2, "Stream"), (3, "API")
         */

        List<String> list = Arrays.asList("Good", "Old", "Stream", "API");
        withIndices(list).forEach(System.out::println);
    }

    private static <T> Stream<IndexedValue<T>> withIndices(final List<T> list) {
        return IntStream.range(0, list.size())
                .mapToObj(idx -> new IndexedValue<>(idx, list.get(idx)));
    }
}
