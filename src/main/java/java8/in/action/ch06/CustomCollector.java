package java8.in.action.ch06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        // 수집 연산의 시작점
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        // 탐색한 항목을 누적하고 바로 누적자를 고친다.
        return List::add;
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        // 항등함수
        return Function.identity();
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        // 두번 째 list2 와 합쳐서
        // 첫번 째 누적자를 고친다.
        return (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        // 콜렉터 플래그를 IDENTITY_FINISH, CONCURRENT 로 설정한다.
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
