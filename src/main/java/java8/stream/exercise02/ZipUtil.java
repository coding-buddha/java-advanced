package java8.stream.exercise02;

import java8.functional.ex01.BiFunction;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class ZipUtil {

    public static <T, R, U> Stream<U> zip(List<T> pre, List<R> post, BiFunction<? super T, ? super R, ? extends U> mapper) {
        if(pre.size() != post.size()) {
            throw new IllegalArgumentException("Difference list sizes");
        }

        return IntStream.range(0, pre.size())
                .mapToObj(idx -> mapper.apply(pre.get(idx), post.get(idx)));
    }
}
